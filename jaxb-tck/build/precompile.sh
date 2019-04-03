#!/bin/sh
#
# Copyright (c) 1999, 2018 Oracle and/or its affiliates. All rights reserved.
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
# parameters
# note we are now up to 10, so can't use direct addressing ($1 $2 $3 etc)
tckDir=$1 ; shift
precompileDir=$1 ; shift 
subdir=$1 ; shift 
initialURLs=$1 ; shift 
#sqeTools=$1 ; shift 
javaHome=$1 ; shift
jaxbHome=$1 ; shift


#
# We had a problem with /usr/bin/sed. /usr/ucb/sed seems to work.
#
SED=/bin/sed
GREP=/bin/grep

# derived args
script=$0
buildDir=`dirname $0`
fullBuildDir=`(cd $buildDir; pwd)`
#problemsFile=$precompileDir/problem-files-$subdir.lst

#ensure precompile dir, report dir and class dir exist
mkdir -p $precompileDir $precompileDir/report/$subdir

PRECOMPILE_CONCURRENCY=1;

jdktools=$javaHome/lib/tools.jar
jtjava=$javaHome/bin/java

# precompile the tests
echo 
echo "Precompiling the tests."
echo "Tests: $initialURLs"
echo "This may take a while. To monitor the progress, execute the command"
echo "	tail -f `cd $precompileDir; pwd`/report/$subdir/harness.trace"
echo "in a separate shell"
echo

JAXB_JAR_FILES=`find $jaxbHome/mod -name '*.jar' -print`
JAXB_CLASSPATH=`echo $JAXB_JAR_FILES | sed -e 's/.jar/.jar:/g' | sed -e 's/: /:/g' | sed -e 's/:$//g'`
cd $tckDir

set -x

if CLASSPATH=$tckDir/classes:$tckDir/lib/javatest.jar:$jdktools:$JAXB_CLASSPATH:$precompileDir/classes \
   DEBUG_PROG="${TRUSS_PRECOMPILE+truss -a -topen,close  -s!all}" \
   $jtjava -ms32m -mx256m \
	-Dprecompile \
	-DsigtestDir=$precompileDir/classes \
	com.sun.javatest.tool.Main \
	-EsysProps -batch \
		-testSuite $tckDir/ \
		-workDirectory -overwrite ${precompileDir}/work/${subdir} \
		-keywords "runtime" \
		-envFiles ${fullBuildDir}/precompile.jte \
		-excludeList ${fullBuildDir}/precompile.jtx \
		-env precompile \
		-report ${precompileDir}/report/${subdir} \
		-tests $initialURLs \
	; then
  echo
  echo "JavaTest reports all ${initialURLs} tests compiled OK."
  echo
else
  echo
  echo "Precompile failed."
  echo "------------------"
  echo "Tests:    $initialURLs"
  echo "Report:   ${precompileDir}/reports/${subdir}"
  echo
  exit 1
fi
