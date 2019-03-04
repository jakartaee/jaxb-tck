#
# Copyright (c) 2004, 2018 Oracle and/or its affiliates. All rights reserved.
#
# This program and the accompanying materials are made available under the
# terms of the Eclipse Public License v. 2.0, which is available at
# http://www.eclipse.org/legal/epl-2.0.
#
# This Source Code may also be made available under the following Secondary
# Licenses when the conditions for such availability set forth in the
# Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
# version 2 with the GNU Classpath Exception, which is available at
# https://www.gnu.org/software/classpath/license.html.
#
# SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
#

#
# This script checks validity of copyright notices
# in TCK source files.
#
# Ident: @(#)crn_check.pl	1.1 05/12/16
#

use strict 'vars';
use strict 'subs';
use strict 'defs';

use crnlib;
use findfile;
use misc;

my $PROG_NAME = `basename $0`;
chop $PROG_NAME;

my $env = misc::init($PROG_NAME, @ARGV);
my $area;
my $props = $$env{"props"};

findfile::findfiles($env, \&file_find_hook);
misc::close_logs();

chdir($$env{"root"});
my $x;

foreach $x (keys %{ $$env{"areas_met"} }) {
    $area = $x;
    my $inp = misc::create_log("include", "<", $area, $env);

    while ($_ = <$inp>) {
        my ($path, $class) = split;

        my $cbeg = $$props{"comment_beg.".$class};
        my $cend = $$props{"comment_end.".$class};
        my $ceol = $$props{"comment_eol.".$class};
        my $cstr = $$props{"comment_str.".$class};

        if ($cbeg || $ceol || $cstr) {
            process_file_comments($path, $class, $cbeg, $cend, $ceol, $cstr);
        } else {
            process_file($path, $class);
        }
    }
    $inp->close();
}
misc::dump_cross_ref($env);
misc::close_logs();

sub get_crn_keys {
    my $class = $_[0];
    my @crn_keys = propfile::get_property_keys($props, $class);
    misc::error("no crn patterns for class $class") if ($#crn_keys < 0);
    return @crn_keys;
}

sub process_file {
    my ($path, $class) = @_;

    my @valid_crn_keys = get_crn_keys($class);
    my @valid_crn_patterns = map { $$props{$_} } @valid_crn_keys;
    my @matched_crn_keys = ();
    my $i;
    my $pattern;

    # read file contents
    open(INP_FILE, "$path") || error("can't open $path");
    my $text = "";

    while (<INP_FILE>) {
        $text .= $_;
    }
    close(INP_FILE);

    # match CRN patterns
    my $found = 0;
    my @years = ();

    for ($i = 0; $i <= $#valid_crn_patterns; $i++) {
        my $pattern = $valid_crn_patterns[$i];

        while ($text =~ /$pattern/) {
            push(@years, $1);
            push(@matched_crn_keys, $valid_crn_keys[$i]);
            $text = $`.$';
            $found = 1;
        }
    }
    if (!$found) {
        misc::log_msg("err.no_crn", $path, 0, $area, $env);
        return;
    }
    if (!years_ok(@years)) {
        misc::log_msg("err.bad_year", $path, join(" ", @years), $area, $env);
    }
    if ($#matched_crn_keys == 0) {
        misc::log_msg("OK", $path, $matched_crn_keys[0], $area, $env);
        return;
    }
    misc::log_msg("err.multi_crn", $path, join(" ", @matched_crn_keys), $area, $env);
}

sub process_file_comments {
    my ($file, $class, $cbeg, $cend, $ceol, $cstr) = @_;

    my $hash = crnlib::parse_comments($file, $cbeg, $cend, $ceol, $cstr);
    my @comments = @{ @{ $hash->{"COMMENT"} }[0] };
    my $crn_tags = "sun|microsystems|license|lisence|all[ \t]+rights[ \t]+reserved";
    my @crns = grep { /copyright/i && /$crn_tags/i } @comments;
    my @part_crns;

    if ($#crns < 0) {
        misc::log_msg("err.no_crn", $file, 0, $area, $env);
        return;
    }
    if ($#crns > 1) {
        @part_crns = grep { /Portions[ \t]+Copyright/ } @crns;
        if ($#part_crns != $#crns - 1) {
            misc::log_msg("err.multi_crn", $file, 0, $area, $env);
        }
    } else {
        # check only Sun's copyrights then
        @crns = grep { /sun\s+microsystems/i } @crns;
    }
    my $crn;
    my @valid_crn_keys = get_crn_keys($class);
    my @valid_crn_patterns = map { $$props{$_} } @valid_crn_keys;
    my @matched_crn_keys = ();
    my @years = ();
    my $crn_at_top = 0;

    foreach $crn (@crns) {
        my $i;
        my $found = 0;

        for ($i = 0; $i <= $#valid_crn_patterns; $i++) {
            my $pattern = $valid_crn_patterns[$i];

            if ($crn =~ /$pattern/) {
                push(@years, $1);
                $found = 1;
                $crn_at_top = ($` =~ /^\s*$/) if (!$crn_at_top);
            }
        }
        if (!$found) {
            misc::log_msg("err.bad_crn", $file, 0, $area, $env);
            return;
        }
        push(@matched_crn_keys, $valid_crn_keys[$i]);
    }
    if (!years_ok(@years)) {
        misc::log_msg("err.bad_year", $file, join(" ", @years), $area, $env);
        return;
    }
    misc::log_msg("OK", $file, join(" ", @matched_crn_keys), $area, $env);

    if (!$crn_at_top) {
        misc::log_msg("err.not_top", $file, join(" ", @matched_crn_keys), $area, $env);
    }
}

sub years_ok {
    my @years = @_;
    my $year;

    foreach $year (@years) {
        if ($year =~ /^\d{4}$/ && ($year < 1995 || $year > get_cur_year())) {
            return 0;
        }
    }
    return 1;
}

my $CUR_YEAR;

sub get_cur_year {
    if (not defined $CUR_YEAR) {
        $CUR_YEAR = `date '+%Y'`;
    }
    return $CUR_YEAR;
}

sub file_find_hook {
    findfile::classify_path($_, $File::Find::name, $env);
}
