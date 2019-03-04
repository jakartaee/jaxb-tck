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
# This is a Perl module for parsing property files
# in Java property file format.
#
# Ident  : @(#)propfile.pm	1.1 05/12/16
# Author : Konst Bobrovsky
#

use strict 'vars';
use strict 'subs';
use strict 'defs';

package propfile;

my $AREA_PATTERN = "area.pattern";
my $AREA_ORDER   = "area.order";
my $PATTERN_EXCL = "exclude";
my $PATTERN_INCL = "include";

local $propfile::CLASS_PREF;
local $propfile::EXCL_PROP;
local $propfile::INCL_PROP;

# Loads a property file.
# Adapted from B&I's pl2xml.pl
#
sub load_property_file {
    my $prop_file  = $_[0];
    my $quote_meta = $_[1];

    (-f $prop_file) && open (FILE, $prop_file) || die "Can't open $prop_file\n";

    my %props = ();
        
    # first pass - remove "\" (continuation)
    my($cur_has_bslash, $prev_had_bslash, @lines, $line);
    $prev_had_bslash = 0;

    while ($line = <FILE>) {
        chop;
        next if $line =~/^\s*(!|\#)/; # skip comment
        if ($line !~ /\S+/) { # skip empty lines
            $prev_had_bslash = 0;
            next;
        }

        if ($line =~ /(.*)\\\s*$/) {
            $line = $1;
            $cur_has_bslash = 1;
        } else {
            $cur_has_bslash = 0;
        }
        $line =~ s/\\ / /g;

        # whether this line is a continuation  of a previous one
        if ($prev_had_bslash) {                
            $lines[$#lines] .= $line;
        } else {
            push(@lines, $line);
        }
        $prev_had_bslash = $cur_has_bslash;
    }
    close(FILE);
        
    # second pass - parse contents        
    foreach $line (@lines) {
        if ($line =~ /(\S+)\s*(:|=|\s+)\s*(\S.*\S)\s*$/) {
            $props{$1} = $quote_meta ? quotemeta($3) : $3;
        }        
    }
    return \%props;
}

sub get_property_keys {
    my ($hash, $prefix) = @_;
    my $key;
    my @keys = ();

    foreach $key (sort (keys %$hash)) {
        push(@keys, $key) if ($key =~ /^$prefix/);
    }
    return @keys;
}

sub get_area_map {
    my $AREA_PROPS = $_[0];
    my %area_map = ();

    map {
        if (!m!^$AREA_PATTERN\.([^\.]+)\.($PATTERN_EXCL|$PATTERN_INCL)$!) {
            misc::error("bad area pattern key: $_");
        }
        $area_map{$1}{$AREA_PATTERN}{$2} = $$AREA_PROPS{$_};
    } get_property_keys($AREA_PROPS, $AREA_PATTERN.".");

    map {
        if (!m!^$AREA_ORDER\.([^\.]+)$!) {
            misc::error("bad area order key: $_");
        }
        exists $area_map{$1} || misc::error("unknown area: $1 ($_)");
        $area_map{$1}{$AREA_ORDER} = $$AREA_PROPS{$_};
    } get_property_keys($AREA_PROPS, $AREA_ORDER.".");

    my %res = ();
    $res{"hash"}  = \%area_map;
    $res{"names"} = get_sorted_area_names(\%area_map, keys %area_map);

    return \%res;
}

sub get_sorted_area_names {
    my $area_map = shift @_;

    my @area_names = sort {
        my $order_a = exists $$area_map{$a} ? $$area_map{$a}{$AREA_ORDER} : "~";
        my $order_b = exists $$area_map{$b} ? $$area_map{$b}{$AREA_ORDER} : "~";
        $order_a cmp $order_b
    } @_;

    return \@area_names;
}

sub get_area {
    my ($path, $area_map) = @_;
    my $area = 0;

    foreach $area (@{ $$area_map{"names"} }) {
        my $area_hash = $$area_map{"hash"}{$area};
        my $p_incl = $$area_hash{$AREA_PATTERN}{$PATTERN_INCL};
        my $p_excl = $$area_hash{$AREA_PATTERN}{$PATTERN_EXCL};

        next if ($p_excl && ($path =~ m!$p_excl!));
        next if ($p_incl && ($path !~ m!$p_incl!));
        return $area;
    }
    return get_default_area_name();
}

sub get_default_area_name {
    return "misc";
}

BEGIN {
    $propfile::CLASS_PREF = "class.";
    $propfile::EXCL_PROP  = "exclude";
    $propfile::INCL_PROP  = "include";
}

return 1;
