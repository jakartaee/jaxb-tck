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

# This is a Perl library for finding and classifying files,
# used by SCCS/CRN checkers.
#
# Ident  : @(#)findfile.pm	1.1 05/12/16
# Author : Konst Bobrovsky
#

use strict 'vars';
use strict 'subs';
use strict 'defs';

use File::Find;

use misc;
use propfile;

package findfile;

# finds files in directories specified by given envirnment hash,
# and creates list files, used for further processing
sub findfiles {
    my ($env, $file_find_hook) = @_;

    my $cur_dir = `pwd`;
    chop $cur_dir;

    my $dir;
    my $root = $$env{"root"};
    my $qroot = $$env{"qroot"};
    chdir($root);

    if ($$env{"-"}) {
        while ($_ = <>) {
            chop;
            next if (/^\s*$/);
            my $f = $_;
            s|^$qroot/(.*)|$1|;

            if (! -f "$_") {
                misc::warning("file not found or beyond root dir: $f");
                next;
            }
            m|/?([^/]+)$| || misc::warning("bad line in stdin: $_");
            classify_path($1, $_, $env);
        }
    }
    foreach $dir (@{ $$env{"dirs"} }) {
        if (-f $dir) {
            # is a file - ok, process it differently
            my $bdir = `basename $dir`;
            chop $bdir;
            $_ = $bdir;
            $File::Find::name = $dir;
            &$file_find_hook();
            next;
        }
        File::Find::find($file_find_hook, $dir);
    }
    chdir($cur_dir);
}

sub classify_path {
    my ($base_name, $full_name, $env) = @_;

    my $qroot = $$env{"qroot"};

    $full_name =~ s|^\./+||;
    $full_name =~ s|^$qroot/||;

    my @qexcl_dirs = @{ $$env{"x"} };
    my $area = propfile::get_area($full_name, $$env{"areas"});
    my $dir;

    if (-d $base_name) {
        if ($full_name =~ m|/SCCS/?$|) {
            $File::Find::prune = 1;
            return 0;
        }
        foreach $dir (@qexcl_dirs) {
            if ($full_name =~ /^$dir/) {
                $File::Find::prune = 1;
                $$env{"areas_met"}{$area} = 1;
                misc::log_msg("excl_dirs", $full_name, 0, $area, $env);
                return 0;
            }
        }
        return 0;
    }
    $$env{"areas_met"}{$area} = 1;
    return classify_file($full_name, $base_name, $env);
}

sub classify_file {
    my ($full_name, $base_name, $env) = @_;

    my $class;
    my $x_pattern = $$env{$propfile::EXCL_PROP};
    my $i_pattern = $$env{$propfile::INCL_PROP};
    my $area = propfile::get_area($full_name, $$env{"areas"});

    # check if filename matches inclusion pattern
    if ($i_pattern && $full_name !~ m!$i_pattern!) {
        misc::log_msg("exclude", $full_name, 0, $area, $env);
        return 0;
    }
    # check if filename matches exclusion pattern
    if ($x_pattern && $full_name =~ m!$x_pattern!) {
        misc::log_msg("exclude", $full_name, 0, $area, $env);
        return 0;
    }

    # check if filename matches per-directory filters
    # and it is not present in exclude list.
    $full_name =~ m!/[^/]+$!;

    if (!misc::matches_filter($`, $base_name, $env)) {
        misc::log_msg("exclude", $full_name, 0, $area, $env);
        return 0;
    }

    # check if filename matches one of the class patterns
    # and assign a class to it
    foreach $class (@{ $$env{"classes"} }) {
        my $pattern = $$env{"props"}{$propfile::CLASS_PREF.$class};

        if ($full_name =~ /$pattern/) {
            my $s = ($1 eq "") ? $class : $class." ".$1;
            misc::log_msg("include", $full_name, " $s", $area, $env);
            return 1;
        }
    }
    # no matching class patterns found
    misc::log_msg("exclude", $full_name, 0, $area, $env);
    return 0;
}

return 1;

