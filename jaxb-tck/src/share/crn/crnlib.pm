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
# This is a Perl module supporting the CRN checking script.
# It is used to extract comments and string literals from files.
#
# Ident  : @(#)crnlib.pm	1.1 05/12/16
# Author : Konst Bobrovsky
#

use strict 'vars';
use strict 'subs';
use strict 'defs';

package crnlib;


sub print_comment_hash {
    my $hash = $_[0];
    print "-------- COMMENT:\n";
    print_parsed_comments($hash->{"COMMENT"});
    print "-------- STRINGS:\n";
    print_parsed_comments($hash->{"STRING"});
}

sub parse_comments {
    my $file = $_[0];
    my $CBEG  = $_[1];
    my $CEND  = $_[2];
    my $CEOL  = $_[3];
    my $Q_QUOTE = $_[4];

    $Q_QUOTE = $Q_QUOTE ? "\\\\*\\\"" : 0;

    my $Q_CBEG  = quotemeta($CBEG);
    my $Q_CEND  = quotemeta($CEND);
    my $Q_CEOL  = quotemeta($CEOL);

    my $PATTERN = "";
    $PATTERN = $Q_CBEG."|" if ($CBEG && ($CBEG ne ""));
    $PATTERN .= $Q_CEOL."|" if ($CEOL && ($CEOL ne ""));
    $PATTERN .= $Q_QUOTE."|" if ($Q_QUOTE && ($Q_QUOTE ne ""));
    chop $PATTERN;

    my $S_START = 0;
    my $S_CBEG  = 1;
    my $S_QUOTE = 2;
    my $state = $S_START;

    open(CODE_FILE, "$file") || error("can't open $file");

    my $pos = 0;
    my $text = "";
    my @eols = (-1);
    my $count = 0;

    while (<CODE_FILE>) {
        $text .= $_;
        $pos += length($_);
        push(@eols, $pos - 1);
    }
    close(CODE_FILE);
    $pos = 0;
    my $tail = $text;
    my $token = "";
    my $region_start = 0;

    my @multi_comment = ();
    my @strings = ();
    my @eol_comment = ();

    my @multi_comment_indents = ();
    my @strings_indents = ();
    my @eol_comment_indents = ();

    my @multi_comment_lines = ();
    my @strings_lines = ();
    my @eol_comment_lines = ();

    while (1) {
        if ($state == $S_START) {
            last if ($tail !~ /$PATTERN/);
            $tail = $';
            $token = $&;
            $pos += length($`.$&);

            # get new state
            {
                if ($token eq $CBEG) {
                    $state = $S_CBEG;
                } elsif ($token =~ /$Q_QUOTE/) {
                    my $len = length($&) - 1;
                    $state = $len % 2 == 0 ? $S_QUOTE : $state;
                } elsif ($token ne $CEOL) {
                    error("bad state. token: <".$token.">");
                }
            }
            if ($token eq $CEOL) {
                $region_start = $pos - length($token);

                if ($tail =~ /\n/m) {
                    push(@eol_comment, $token.$`);
                    $pos += length($`.$&);
                    $tail = $';
                } else {
                    push(@eol_comment, $token.$tail);
                    $pos += length($`.$tail);
                    $tail = "";
                }
                my ($tab, $line) = get_str_head($text, $region_start, \@eols);
                push(@eol_comment_indents, $tab);
                push(@eol_comment_lines, $line);
                next;
            }
            if ($state == $S_CBEG) {
                $region_start = $pos - length($token);
            } elsif ($state == $S_QUOTE) {
                $region_start = $pos - 1;
            }
            next;
        }
        if ($state == $S_CBEG) {
            last if ($tail !~ /$Q_CEND/);
            $tail = $';	
            $token = $&;
            $pos += length($`.$token);
            $state = $S_START;
            my ($tab, $line) = get_str_head($text, $region_start, \@eols);
            push(@multi_comment, substr($text, $region_start, $pos - $region_start));
            push(@multi_comment_indents, $tab);
            push(@multi_comment_lines, $line);
            next;
        }
        if ($state == $S_QUOTE) {
            do {
                last if ($tail !~ /$Q_QUOTE/);
                $tail = $';
                $token = $&;
                $pos += length($`.$token);
            } while ((length($token) - 1) % 2 != 0);

            $state = $S_START;
            my ($tab, $line) = get_str_head($text, $region_start, \@eols);
            push(@strings, substr($text, $region_start, $pos - $region_start));
            push(@strings_indents, $tab);
            push(@strings_lines, $line);

            if ($#strings > 1000) {
                # stop further string processing - otherwise we'll freeze on
                # vm/classfmt/lmt/mthnum001/mthnum00101m1/mthnum00101m10p.jasm
                # which contains 64K strings.
                $Q_QUOTE = 0;
                $PATTERN = $Q_CBEG."|" if ($CBEG && ($CBEG ne ""));
                $PATTERN .= $Q_CEOL."|" if ($CEOL && ($CEOL ne ""));
                chop $PATTERN;
            }
            next;
        }
        error("internal error");
    }
    # congregate end-of-line comments in continious blocks
    my $i;
    my $prev_line = -2;
    my $cur_block_line = -1;
    my $cur_block_indent = "";
    my $cur_block = "";
    my @blocks = ();
    my @blocks_lines = ();
    my @blocks_indents = ();

    push(@eol_comment, "");
    push(@eol_comment_lines, $eol_comment_lines[$#eol_comment_lines] + 2);
    push(@eol_comment_indents, "");
    
    for ($i = 0; $i <= $#eol_comment; $i++) {
        my $text = $eol_comment[$i]."\n";
        my $line = $eol_comment_lines[$i];
        my $indent = $eol_comment_indents[$i];

        if ($indent !~ /^\s*$/ || $line > $prev_line + 1) {
            if ($i > 0) {
                push(@blocks, $cur_block);
                push(@blocks_lines, $cur_block_line);
                push(@blocks_indents, $cur_block_indent);
            }
            $cur_block = $text;
            $cur_block_indent = $indent;
            $cur_block_line = $line;
        } else {
            $cur_block .= $indent.$text;
        }
        $prev_line = $line;
    }

    push(@multi_comment, @blocks);
    push(@multi_comment_indents, @blocks_indents);
    push(@multi_comment_lines, @blocks_lines);

    return
      {
       "COMMENT"  => [\@multi_comment, \@multi_comment_indents, \@multi_comment_lines],
       "STRING"  => [\@strings, \@strings_indents, \@strings_lines]
      };
}

sub print_parsed_comments {
    my $ref = $_[0];

    my $i;
    my $texts   = $$ref[0];
    my $indents = $$ref[1];
    my $lines   = $$ref[2];

    for ($i = 0; $i <= $#$texts; $i++) {
        print "LINE: ".$$lines[$i]."\n";
        print "<".$$indents[$i]."|".$$texts[$i].">\n---\n";
    }
}

sub get_str_head {
    my $s = $_[0];
    my $pos = $_[1];
    my $eols = $_[2];
    my $start_pos = 0;
    my $i = bsearch($pos, 0, $#$eols, $eols, \&cmp_int);

    $start_pos = $i > 0 ? $$eols[$i - 1] + 1 : 0;
    return (substr($s, $start_pos, $pos - $start_pos), $i);
}

sub error {
    print $0, ": ", $_[0], "\n";
    exit(1);
}

sub bsearch {
    use integer;

    my ($key, $beg, $end, $arr, $func) = @_;

    return ($end + 1) if ($$arr[$end] - $key) <= 0;
    return $beg if ($$arr[$beg] - $key) > 0;
    return $end if ($beg >= $end - 1);

    my $mid = $beg + ($end - $beg)/2;
    my $x = $$arr[$mid] - $key;

    return $x < 0 ?
      bsearch($key, $mid, $end, $arr, $func) :
      bsearch($key, $beg, $mid, $arr, $func);
}

return 1;
