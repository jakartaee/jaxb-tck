#!/usr/bin/perl
#
# Copyright (c) 2003, 2018 Oracle and/or its affiliates. All rights reserved.
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
# compares test run with exclude list
# usage: compareJR.pl jtx reportDir [-jtr workDir] outputDir

# check arguments
if (!(($#ARGV == 2) || (($#ARGV == 4) && ($ARGV[2] eq "-jtr")))) {
    printf STDERR "Compare Tool compares test run with exclude list.\n";
    printf STDERR "usage: $0 file.jtx reportDir [-jtr workDir] outputDir\n";
    exit 1;
}

# read arguments
$jtx=$ARGV[0];
$dirB=$ARGV[1];

if ($ARGV[2] eq "-jtr") {
    $workDir=$ARGV[3];
    $outDir=$ARGV[4];
} else {
    $outDir=$ARGV[2];
}
(-d $outDir) || mkdir($outDir,0775) or die "Cannot create directory $outDir\n";

(-f "$jtx") || die "$jtx not found";
(-f "$dirB/report.html") or die "No javatest report found in $dirB";

$footer="<hr><small>Report generated on ".localtime()."</small>\n";

# get test-suite root dir
# and work directory
# patterns are javatest version specific
open HTML, "$dirB/report.html" or die "Cannot read file $dirB/report.html\n";
while (<HTML>) {
    $report .= $_;
}
close HTML;

if (! ($report =~ m|<br>Using JavaTest 3\.1\.2;|s)) {
    printf STDERR "Error: the report is genarated by unexpected version of JavaTest, expected is 3.1.2";
    exit 1;
}

if ($report =~ m|<th scope=row>Test suite</th>.*?<a href="(.*?)">|s) {
    $testsuite = $1;
    $testsuite =~ s|/tests$||;
}

if (! (defined $workDir)) {
    if ($report =~ m|<th\s+scope=row>Work directory</th>\s*<td>\s*<a href="(.*?)">|s) {
        $workDir = $1;
    }
}

undef $report;

# read jtx
open JTX, "$jtx" or die "Cannot read file $jtx\n";
while(<JTX>) {
    if (/^(([^#][^[\s]*)(\[([^\s]*)\])?)\s+([^\s]*)\s+([^\s]*)/) {
        $jtx{$1} = $1;
        $jtxTest{$2} = $jtxTest{$2} . $4;
        $jtxBug{$1} = $5;
    }
}
close JTX;

# read summary
$otherCount=0;
$failedCount=0;
$passedCount=0;
$passedUnexpectedly=0;
$failedUnexpectedly=0;
open SMR, "$dirB/summary.txt" or die "Cannot read file $dirB/summary.txt\n";
while(<SMR>) {
    if (/^([^#][^\s]*)\s+([^\s]*)\.\s+([^\s]*)/) {
        $test = $1;
        $status = $2;
        $msg = $3;
        $jtr = $test;
        $jtr =~ s|\[.*\]||;
        $jtr =~ s|\.html?||;
        $jtr =~ s|#|_|;
        
        if (($msg eq "test") 
            && ($status ne "Passed" || defined($jtxTest{$test}))) { # need to read jtr for testcases
            open JTR, "$workDir/$jtr.jtr" or die "Cannot read file $workDir/$jtr.jtr\n";
reading_jtr:
            
            while (<JTR>) {
                if (/^#section:execute/) {
                    while(<JTR>) {
                        if (/^-+log:\(([0-9]*)/) {
                            $lineNum = $1;
                            $i = 0;
                            while (<JTR>) {
                                if (++$i == $lineNum) {
                                    last reading_jtr;
                                }
                                if (/^([^:\s]*):\s*([a-zA-Z]*)\./) {
                                    $status=$2;
                                    setTestResult($test, $1);
                                }
                            }
                        }
                    }
                }
            }
            close JTR;
        } else {
            setTestResult($test);
        }
    }
}
close SMR;

$clean = 1;
if ($passedUnexpectedly > 0) {
    genDiff("$outDir/passed.html", "Excluded testcases that passed", \@passedInJtx);
    $passed = "<font color=\"Red\"><a href=\"passed.html\">$passedUnexpectedly</a></font>";
} else {
    $passed = 0;
}

if ($failedCount > 0) {
    genDiff("$outDir/failedAll.html", "Failed testcases", \@failed);
    $failedAll = "<a href=\"failedAll.html\">$failedCount</a>";
} else {
    $failedAll = 0;
}

if ($failedUnexpectedly > 0) {
    genDiff("$outDir/failed.html", "Failed testcases that are not excluded", \@failedNotInJtx);
    $failed = "<font color=\"Red\"><a href=\"failed.html\">$failedUnexpectedly</a></font>";
    $clean = 0;
} else {
    $failed = 0;
}

if ($otherCount > 0) {
    genDiff("$outDir/other.html", "Testcases that had errors or were not run", \@other);
    $other = "<font color=\"Red\"><a href=\"other.html\">$otherCount</a></font>";
    $clean = 0;
} else {
    $other = 0;
}

$No = $failedUnexpectedly + $otherCount;
if ($No == 0) {
    $exitMsg = "No unexpected failures.";
} elsif ($No == 1) {
    $exitMsg = "$No unexpected failure.";
} else {
    $exitMsg = "$No unexpected failures.";
}

# generate report.html
open HTML, ">$outDir/report.html" or die "Cannot write to file $outDir/report.html\n";
printf HTML <<EOF
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2//EN">
<html>
<head>
<title>Compare Results</title>
</head>
<body>
<h1><a name=compare>Compare Parameters</a></h1>
<table  border=1>
<tr><th align=left>Report</th><th align=left><a href="$dirB/report.html">$dirB/report.html</a></td></tr>
<tr><th align=left>Work directory</th><th align=left><a href="$workDir">$workDir</a></td></tr>
<tr><th align=left>compared to</th><th align=left><a href="$jtx">$jtx</a></td></tr>
</table>

<h1><a name=compare>Compare Results</a></h1>
<table  border=1>
<tr bgcolor=gray><th align=left>testscases</th><th>#</th></tr> 
<tr><th align=left>passed</th><td align=right>$passedCount</td></tr>
<tr><th align=left>unexpectedly passed</th><td align=right>$passed</td></tr>
<tr><th align=left>failed</th><td align=right>$failedAll</td></tr>
<tr><th align=left>unexpectedly failed</th><td align=right>$failed</td></tr>
<tr><th align=left>not run or had errors</th><td align=right>$other</td></tr>
</table>
<p>Summary: $exitMsg
$footer
</body>
</html>
EOF
;
close HTML;

printf STDERR "Report written to $outDir/report.html\n";
if ($clean == 1) {
    printf STDERR "Passed: $exitMsg\n";
    exit 0;
} else {
    printf STDERR "Error: $exitMsg\n";
    exit 1;
}

##############################################################
# args: htmlFileName, title, \%tests
# generate html file with a given list of tests
sub genDiff {
    my($filename,$title,$keys,$bug);
    $filename=$_[0];
    $title=$_[1];
    $keys=$_[2];

    open HTML, ">$filename" or die "Cannot write to file $filename\n";
    printf HTML <<EOF
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2//EN">
<html>
<head>
<title>Compare Results: $title</title>
</head>
<body>
<h2>Compare Results: $title</a></h2>
<table  border=1>
<tr><th>status</th><th>bug</th><th>testcase</th></tr>
EOF
;
    $count=0;
    foreach (sort(@$keys)) {
        $bug = defined $jtxBug{$_} ? $jtxBug{$_}:"n/a";
        $href = $_;
        $href =~ s|\[[^\[]*$||;
        printf HTML "<tr><td>$status{$_}</td><td>$bug</td><td><a href=\"$testsuite/tests/$href\">$_</a></td></tr>\n";
    }

    printf HTML <<EOF
</table>
$footer
</body>
</html>
EOF
;
    close HTML;
    return $count;
}

# args: $test, $testcase
sub setTestResult {
    my $test, $testcase, $full;
    $test = $_[0];
    if (defined $_[1]) {
        $testcase = $_[1];
        $full = $test."[".$testcase."]";
    } else {
        $full = $test;
    }
    if ($status eq "Passed") {
       $passedCount++;
       if (defined($jtx{$full}) || (defined $jtx{$test})) {
           $passedUnexpectedly++;
           push @passedInJtx, $full;
       }
    } elsif ($status eq "Failed") {
       $failedCount++;
       push @failed, $full;
       if (!defined $jtx{$full}) {
           $failedUnexpectedly++;
           push @failedNotInJtx, $full;
       }
    } else {
       $otherCount++;
       push @other, $full;
    }
    $status{$full} = "<a href=\"$workDir/$jtr.jtr\">$status</a>";
}
