#! /bin/ksh 
#
# Copyright (c) 2007, 2018 Oracle and/or its affiliates. All rights reserved.
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
# This script is to process a make log into a tree of HTML files
# using mlprint.
# It will normally be easiest to use this scripts as
#	ksh log2html.ksh [-o output-dir] [-t title] log-file

usage() {
    echo "`basename \`which $0\`` [options] log-file"
    echo "Options:"
    echo "-o dir      output directory; default is log-file.mlp"
    echo "-t title    title for generated files; default is log-file"
}

buildDir=`dirname \`which $0\``


findJava() {
    cd $buildDir
    file=/tmp/$$.1.mk
    cat <<'EOF' > $file
    BUILDDIR= .
    include Defs.mk
general-java:
	@echo $(GENERAL_JAVA)
EOF
    /usr/ccs/bin/make -f $file general-java
}

findJavaTest() {
    cd $buildDir
    file=/tmp/$$.2.mk
    cat <<'EOF' > $file
    BUILDDIR= .
    include Defs.mk
java-test:
	@echo $(JAVATEST_JAR_LOC)
EOF
    /usr/ccs/bin/make -f $file java-test
}


genJava=`findJava`
if [ -z "$genJava" ]; then
    echo "cannot determine location of general java"
    exit 1
fi

javaTest=`findJavaTest`
if [ -z "$javaTest" ]; then
    echo "cannot determine location of java test jar"
    exit 1
fi

if [ $# -eq 0 ]; then
    usage
    exit 0
fi

while [ $# -gt 0 ]; do
    case $1 in 
    -o ) outDir=$2 ; shift ;;
    -t ) title="$2" ; shift ;;
    -* ) echo "bad option: $1" ; usage ; exit 1 ;;
    * )	 break ;;
    esac
    shift
done

case $# in
    0 ) echo "no log file specified" ; usage ; exit 1 ;;
    1 ) logFile=$1 ;;
    * ) echo "too many files specified" ; usage ; exit 1 ;;
esac

if [ -z "$title"]; then
    title=$logFile
fi

if [ -z "$outDir" ]; then
    outDir=${logFile}.mlp
fi

test -d $outDir || mkdir $outDir

#${jctTools}/solaris/bin/mlprint -J-mx128m 
CLASSPATH=${javaTest}/javatest.jar \
    ${genJava} -Xmx512m -XX:MaxPermSize=512M com.sun.jck.utils.mlprint.Main \
    -b `cd ${buildDir}; pwd` \
    -c ${buildDir}/tck.mlc \
    -m ${buildDir}/tck.mlm \
    -o ${outDir} \
    -t "${title}" \
    $logFile
