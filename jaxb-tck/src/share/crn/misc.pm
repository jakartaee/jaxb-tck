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
# This is a Perl module containing miscellaneous routines used
# by SCCS/CRN checking scripts.
#
#

use strict 'vars';
use strict 'subs';
use strict 'defs';

use FileHandle;
use propfile;

package misc;

my $PROG_NAME = "";
my %LOG = ();

##
# Parses command line options, checks them, fills in and returns
# the "environment" hash
##

sub init {
    $PROG_NAME = shift @_;
    my @args = @_;
    my $dirs_started = 0;
    my %env = ();
    my $x;

    while ($#args >= 0) {
        $_ = shift @args;
        
        if ($_ =~ /^-/) {
            error("dirs must not preceed options") if ($dirs_started);
            
            if ($_ eq "-res") {
                $env{"res"} && error("duplicated result directory");
                $x = shift @args;
                (-d "$x") || error("result directory not found: ".$x);
                $env{"res"} = $x;
            } elsif ($_ eq "-cfg") {
                $env{"cfg"} && error("duplicated config file");
                $x = shift @args;
                (-f "$x") || error("config file not found or unredable: ".$x);
                $env{"cfg"} = $x;
            } elsif ($_ eq "-acfg") {
                $env{"acfg"} && error("duplicated area config file");
                $x = shift @args;
                (-f "$x") || error("area config file not found or unredable: ".$x);
                $env{"acfg"} = $x;
            } elsif ($_ eq "-root") {
                $env{"root"} && error("duplicated root directory");
                $x = shift @args;
                (-d "$x") || error("root directory not found: ".$x);
                $env{"root"} = $x;
            } elsif ($_ eq "-x") {
                $x = shift @args;
                push(@{$env{"x"}}, $x);
            } elsif ($_ eq "-xlist") {
                $x = shift @args;
                ( -r "$x" ) && ( -f "$x" ) || error("exclude list file not found: ".$x); 
                $env{"xlist"} = read_xlist($x);
            } elsif ($_ eq "-") {
                $env{"-"} = 1;
            } elsif ($_ eq "-h" || $_ eq "-help") {
                usage();
                exit(0);
            } else {
                error("invalid option: $_");
            }
        } else {
            push(@{$env{"dirs"}}, $_);
            $dirs_started = 1;
        }
    }
    # Check input 
    
    $env{"cfg"} || error("-cfg option not specified");
    $env{"res"} || error("-res option not specified");

    my @dirs = @{$env{"dirs"}};
    push(@{$env{"dirs"}}, ".") if ($#dirs < 0 && !$env{"-"});

    my $root = $env{"root"};

    # check for non-existing exclude dirs
    @{$env{"x"}} = grep {
        my $adir = $root ? "$root/$_" : "$_";
        if (-d "$adir") {
            1;
        } else {
            warning("excluded directory not found: $adir");
            0;
        }
    } @{$env{"x"}};

    # check for non-existing input dirs
    @{$env{"dirs"}} = grep {
        my $adir = $root ? "$root/$_" : "$_";
        if (-d "$adir") {
            1;
        } else {
            warning("input directory not found: $adir");
            0;
        }
    } @{$env{"dirs"}};

    # prepare paths

    my $res = $env{"res"};
    $res = `cd $res; pwd`;
    chop $res;
    $env{"res"} = $res;

    $root = `cd $root; pwd`;
    chop $root;
    $env{"root"} = $root;
    $env{"qroot"} = quotemeta($root);

    my @quoted_x_dirs = map { quotemeta($_) } @{$env{"x"}};
    $env{"x"} = \@quoted_x_dirs;

    # load config files

    my $props = propfile::load_property_file($env{"cfg"});
    $env{"props"} = $props;
    my @classes = propfile::get_property_keys($props, $propfile::CLASS_PREF);
    grep { s/^${propfile::CLASS_PREF}// } @classes;
    $env{"classes"} = \@classes;

    my $aprops = propfile::load_property_file($env{"acfg"});
    $env{"areas"} = propfile::get_area_map($aprops);

    my $i_prop = $$props{$propfile::INCL_PROP};
    my $x_prop = $$props{$propfile::EXCL_PROP};

    if ($i_prop) {
        $env{$propfile::INCL_PROP} = $i_prop;
    }
    if ($x_prop) {
        $env{$propfile::EXCL_PROP} = $x_prop;
    }

    $env{"filter"} = create_filters($props);
    return \%env;
}

sub read_xlist {
    my $xlist = $_[0];
    my %res = ();

    open(XLIST, $xlist) || error("can't read exclude list $xlist");

    while ($_ = <XLIST>) {
        next if (/^\s*\#/ || /^\s*$/);
        s/^\s*(.*?)\s*$/$1/;
        $res{$_} = 1;
    }
    close(XLIST);
    return \%res;
}

sub create_filters {
    my $props = $_[0];
    my $INCL = $propfile::INCL_PROP;
    my $EXCL = $propfile::EXCL_PROP;
    my %filters = ();

    my @include_entries = propfile::get_property_keys($props, $INCL.".");
    grep { s/^$INCL\.// } @include_entries;
    map { $filters{"hash"}{$_}{$INCL} = $$props{$INCL.".".$_} } @include_entries;

    my @exclude_entries = propfile::get_property_keys($props, $EXCL.".");
    grep { s/^$EXCL\.// } @exclude_entries;
    map { $filters{"hash"}{$_}{$EXCL} = $$props{$EXCL.".".$_} } @exclude_entries;
    my @dirs = sort keys % {$filters{"hash"}};
    $filters{"dirs"} = \@dirs;

    return \%filters;
}

sub matches_filter {
    my ($dirname, $basename, $props) = @_;
    my $fullname = $dirname eq "" ? $basename : $dirname."/".$basename;

    if (exists $$props{"xlist"}{$fullname}) {
        return 0;
    }
    my $INCL = $propfile::INCL_PROP;
    my $EXCL = $propfile::EXCL_PROP;
    my @dirs = @{$$props{"filter"}{"dirs"}};
    my $d;

    foreach $d (@dirs) {
        my $dir = quotemeta($d);

        if ($dirname =~ m!^$dir(?:/.*)?$!) {
            my $rel_name = "$1/$basename";
            $rel_name =~ s!^/*!!;

            my $incl_pat = $$props{"filter"}{"hash"}{$d}{$INCL};
            my $excl_pat = $$props{"filter"}{"hash"}{$d}{$EXCL};

            if ($incl_pat && ($rel_name !~ m!$incl_pat!)) {
                return 0;
            }
            if ($excl_pat && ($rel_name =~ m!$excl_pat!)) {
                return 0;
            }
            return 1;
        }
    }
    return 1;
}

sub log_msg {
    my ($id, $file, $details, $area, $env) = @_;

    my $id1 = $area ? $id."|".$area : $id;
    my $h = $LOG{"HANDLES"}{$id1};

    if (not defined $h) {
        $h = create_log($id, ">", $area, $env);
    }
    $h || error("can't create log file for $id");
    $details = $details ? " $details" : "";
    print $h $file."$details\n";

    $area = "none" if !$area;
    return if ($id eq "OK");

    if (defined $LOG{"FILES"}{$area}{$file}) {
        $LOG{"MULTI"}{$area}{$file} = 1;
    }
    $LOG{"FILES"}{$area}{$file} .= "[$id:$details]";
}

sub is_logged {
    my ($path, $area) = @_;
    return defined $LOG{"FILES"}{$area}{$path};
}

sub create_log {
    my ($id, $mode, $area, $env, $ignore) = @_;

    my $res = $$env{"res"};
    my $d = "$res/$area";
    my $path = $area ? "$d/files.$id" : "$res/files.$id";

    if ($area && (! -d "$d")) {
        system("mkdir $d");
        ( -d "$d" ) || error("can not create dir: $d");
    }
    my $h = new FileHandle("$mode $path");

    if (not defined $h) {
        my $op = $mode eq ">" ? "writing" : "reading";

        if ($ignore) {
            return 0;
        } else {
            error("can't open file $path for $op");
        }
    }
    my $id1 = $area ? $id."|".$area : $id;
    $LOG{"HANDLES"}{$id1} = $h;
    return $h;
}

sub get_log_handle {
    my ($id, $area) = $_[0];
    my $id1 = $area ? $id."|".$area : $id;
    return $LOG{"HANDLES"}{"$id1"};
}

sub close_logs {
    my @ids = keys %{ $LOG{"HANDLES"} };
    my $h;

    map { $h = $LOG{"HANDLES"}{$_}; $h->close(); } @ids;
    %LOG = ();
}

sub dump_cross_ref {
    my $env = $_[0];
    my $area;

    foreach $area (keys %{ $LOG{"MULTI"} }) {
        my $file;

        foreach $file (keys %{ $LOG{"MULTI"}{$area} }) {
            my $s = $LOG{"FILES"}{$area}{$file};
            log_msg("cross_ref", $file, $s, $area, $env);
        }
    }
}

sub warning {
    my $msg = $_[0];
    print "$PROG_NAME WARNING: $msg\n";
}

sub info {
    my $msg = $_[0];
    print "$PROG_NAME info: $msg\n";
}

sub error {
    my $msg = $_[0];
    close_logs();
    print STDERR "*** $PROG_NAME ERROR: $msg\n";
    exit(1);
}

# recursively prints a Perl reference
sub print_ref {
    my $ref = $_[0];
    my $tab = $_[1];
    if (ref($ref) eq "HASH") {
        my $key;
        print $tab, "HASH {\n";
        my $max_len = 0;
        foreach $key (keys %$ref) {
            $max_len = length($key) if (length($key) > $max_len);
        }
        foreach $key (sort keys %$ref) {
            print $tab, "  ", $key, " " x ($max_len - length($key)), " --> ";
            my $val = $$ref{$key};
            if (ref($val) eq "ARRAY" || ref($val) eq "HASH") {
                print "\n";
                print_ref($val, $tab."    ");
            } else {
                print $val, "\n";
            }
        }
        print $tab, "}\n";
    } elsif (ref($ref) eq "ARRAY") {
        my @arr = @$ref;
        print $tab, "ARRAY (\n";
        for (my $i = 0; $i <= $#arr; $i++) {
            my $elem = $arr[$i];
            print $tab, "  ", $i, ": ";
            if (ref($elem) eq "ARRAY" || ref($elem) eq "HASH") {
                print "\n";
                print_ref($elem, $tab."    ");
            } else {
                print $elem, "\n";
            }
        }
        print $tab, ")\n";
    } else {
        print $tab.$ref;
    }
}

sub set_prog_name {
    $PROG_NAME = $_[0];
}

sub usage {
    print <<EOF
Usage:

% $PROG_NAME -res <result dir> -cfg <config file> -acfg <area config file> \\
             [-root <root dir>] [{ -x <exclude dir> }] [ -xlist <exclude list>] \\
             [-] <DIR> [ <DIR>, ... ]
EOF
}

return 1;
