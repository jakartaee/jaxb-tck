#!/bin/bash -x
#
# Copyright (c) 2019 Oracle and/or its affiliates. All rights reserved.
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


if [ -z "$ANT_HOME" ]; then
  export ANT_HOME=/usr/share/ant/
fi

if [ -z "$JAVA_HOME" ]; then
  export JAVA_HOME=/opt/jdk1.8.0_171
fi

export PATH=$JAVA_HOME/bin:$ANT_HOME/bin:$PATH

cd $WORKSPACE
export BASEDIR=`pwd`

if [ -z "$GF_HOME" ]; then
  export GF_HOME=$BASEDIR
fi

echo "ANT_HOME=$ANT_HOME"
echo "export JAVA_HOME=$JAVA_HOME"
echo "export MAVEN_HOME=$MAVEN_HOME"
echo "export PATH=$PATH"

mkdir -p ${HOME}/.m2

cd $WORKSPACE
WGET_PROPS="--progress=bar:force --no-cache"
if [ -z "$JAF_BUNDLE_URL" ];then
  export JAF_BUNDLE_URL=http://central.maven.org/maven2/com/sun/activation/jakarta.activation/1.2.1/jakarta.activation-1.2.1.jar
fi
#wget $WGET_PROPS $JAF_BUNDLE_URL -O jakarta.activation.jar

cd $BASEDIR
if [ -z "$GF_BUNDLE_URL" ]; then
  echo "Using default url for GF bundle: $DEFAULT_GF_BUNDLE_URL"
  export GF_BUNDLE_URL=$DEFAULT_GF_BUNDLE_URL
fi
export TCK_ROOT=$WORKSPACE
export  JAVA_TOOL_OPTIONS="-Dfile.encoding=UTF8"
#wget $WGET_PROPS $GF_BUNDLE_URL -O latest-glassfish.zip
#getting jaxb-ri which is needed to build the JAXB TCK
#wget $WGET_PROPS https://jenkins.eclipse.org/jaxb-impl/job/jaxb-ri-master-build/lastBuild/artifact/jaxb-ri/bundles/ri/target/jaxb-ri.zip -O jaxb-ri.zip
wget $WGET_PROPS https://ci.eclipse.org/jaxb-impl/job/jaxb-ri-pr-build/1/artifact/jaxb-ri/bundles/ri/target/jaxb-ri.zip -O jaxb-ri.zip
unzip -o jaxb-ri.zip

ls -l jaxb-ri/mod
# Temporary overwrite of API package
#wget $WGET_PROPS https://search.maven.org/remotecontent?filepath=jakarta/xml/bind/jakarta.xml.bind-api/3.0.0-RC1/jakarta.xml.bind-api-3.0.0-RC1.jar -O jaxb-ri/mod/jakarta.xml.bind-api.jar

wget $WGET_PROPS https://repository.ow2.org/nexus/service/local/repositories/snapshots/content/org/ow2/asm/asm-commons/7.0-SNAPSHOT/asm-commons-7.0-20181027.133601-5.jar -O asm-commons-7.0.jar
wget $WGET_PROPS https://repository.ow2.org/nexus/service/local/repositories/snapshots/content/org/ow2/asm/asm/7.0-SNAPSHOT/asm-7.0-20181027.133552-5.jar -O asm-7.0.jar
#unzip -o latest-glassfish.zip
#ls -l $GF_HOME/glassfish5/glassfish/

#if [ ! -z "$GF_VERSION_URL" ]; then
#  wget --progress=bar:force --no-cache $GF_VERSION_URL -O glassfish.version
#  cat glassfish.version
#fi
which make
make -version
which ksh
#ksh -version
which awk
#awk -version

export TCK_ROOT=$BASEDIR
sed -i 's#^AWK\s*=\s*.*#AWK = /usr/bin/awk#g' $WORKSPACE/jaxb-tck/build/Defs.mk
sed -i 's#^BASENAME\s*=\s*.*#BASENAME = /usr/bin/basename#g' $WORKSPACE/jaxb-tck/build/Defs.mk
sed -i 's#^CHMOD\s*=\s*.*#CHMOD = /usr/bin/chmod#g' $WORKSPACE/jaxb-tck/build/Defs.mk
sed -i 's#^CAT\s*=\s*.*#CAT = /usr/bin/cat#g' $WORKSPACE/jaxb-tck/build/Defs.mk
sed -i 's#^CP\s*=\s*.*#CP = /usr/bin/cp#g' $WORKSPACE/jaxb-tck/build/Defs.mk
sed -i 's#^DATE\s*=\s*.*#DATE = /usr/bin/date#g' $WORKSPACE/jaxb-tck/build/Defs.mk
sed -i 's#^DIRNAME\s*=\s*.*#DIRNAME = /usr/bin/dirname#g' $WORKSPACE/jaxb-tck/build/Defs.mk
sed -i 's#^EGREP\s*=\s*.*#EGREP = /usr/bin/egrep#g' $WORKSPACE/jaxb-tck/build/Defs.mk
sed -i 's#^FIND\s*=\s*.*#FIND = /usr/bin/find#g' $WORKSPACE/jaxb-tck/build/Defs.mk
sed -i 's#^GREP\s*=\s*.*#GREP = /usr/bin/grep#g' $WORKSPACE/jaxb-tck/build/Defs.mk
sed -i 's#^KSH\s*=\s*.*#KSH = /usr/bin/ksh#g' $WORKSPACE/jaxb-tck/build/Defs.mk
sed -i 's#^LS\s*=\s*.*#LS = /usr/bin/ls#g' $WORKSPACE/jaxb-tck/build/Defs.mk
sed -i 's#^MKDIR\s*=\s*.*#MKDIR = /usr/bin/mkdir#g' $WORKSPACE/jaxb-tck/build/Defs.mk
sed -i 's#^MV\s*=\s*.*#MV = /usr/bin/mv#g' $WORKSPACE/jaxb-tck/build/Defs.mk
sed -i 's#^NAWK\s*=\s*.*#NAWK = /usr/bin/awk#g' $WORKSPACE/jaxb-tck/build/Defs.mk
sed -i 's#^PRINTF\s*=\s*.*#PRINTF = /usr/bin/printf#g' $WORKSPACE/jaxb-tck/build/Defs.mk
sed -i 's#^PS\s*=\s*.*#PS = /usr/bin/ps#g' $WORKSPACE/jaxb-tck/build/Defs.mk
sed -i 's#^PWD\s*=\s*.*#PWD = /usr/bin/pwd#g' $WORKSPACE/jaxb-tck/build/Defs.mk
sed -i 's#^RM\s*=\s*.*#RM = /usr/bin/rm -rf#g' $WORKSPACE/jaxb-tck/build/Defs.mk
sed -i 's#^SED\s*=\s*.*#SED = /usr/bin/sed#g' $WORKSPACE/jaxb-tck/build/Defs.mk
sed -i 's#^SORT\s*=\s*.*#SORT = /usr/bin/sort#g' $WORKSPACE/jaxb-tck/build/Defs.mk
sed -i 's#^SPLIT\s*=\s*.*#SPLIT = /usr/bin/split#g' $WORKSPACE/jaxb-tck/build/Defs.mk
sed -i 's#^TAR\s*=\s*.*#TAR = /usr/bin/tar#g' $WORKSPACE/jaxb-tck/build/Defs.mk
sed -i 's#^TEE\s*=\s*.*#TEE = /usr/bin/tee#g' $WORKSPACE/jaxb-tck/build/Defs.mk
sed -i 's#^TEST\s*=\s*.*#TEST = /usr/bin/test#g' $WORKSPACE/jaxb-tck/build/Defs.mk
sed -i 's#^TR\s*=\s*.*#TR = /usr/bin/tr#g' $WORKSPACE/jaxb-tck/build/Defs.mk
sed -i 's#^PERL\s*=\s*.*#PERL = /usr/bin/perl#g' $WORKSPACE/jaxb-tck/build/Defs.SFBay.mk
sed -i 's#^ZIP\s*=\s*.*#ZIP = /usr/bin/zip#g' $WORKSPACE/jaxb-tck/build/Defs.SFBay.mk
sed -i 's#^UNZIP\s*=\s*.*#UNZIP=/usr/bin/unzip#g' $WORKSPACE/jaxb-tck/build/Defs.SFBay.mk

sed -i 's#^GENERAL_JAVAHOME\s*=\s*.*#GENERAL_JAVAHOME=/opt/jdk1.8.0_191#g' $WORKSPACE/jaxb-tck/build/Defs.SFBay.mk
sed -i 's#^PWD\s*=\s*.*#PWD=/usr/bin/pwd#g' $WORKSPACE/jaxb-tck/build/Defs.SFBay.mk
sed -i 's#^JAXB_HOME\s*=\s*.*#JAXB_HOME = '"${WORKSPACE}"'/jaxb-ri#g' $WORKSPACE/jaxb-tck/build/Defs.SFBay.mk
sed -i 's#^JAXB_20_RI_HOME\s*=\s*.*#JAXB_20_RI_HOME = '"${WORKSPACE}"'/jaxb-ri#g' $WORKSPACE/jaxb-tck/build/Defs.SFBay.mk
sed -i 's#^JAVAHOME_6\s*=\s*.*#JAVAHOME_6 = /opt/jdk1.8.0_191#g' $WORKSPACE/jaxb-tck/build/Defs.SFBay.mk
sed -i 's#^SIGTEST_DIST\s*=\s*.*#SIGTEST_DIST = '"${WORKSPACE}"'/jaxb-tck#g' $WORKSPACE/jaxb-tck/build/Defs.SFBay.mk
sed -i 's#^ANT_HOME\s*=\s*.*#ANT_HOME = /usr/share/ant#g' $WORKSPACE/jaxb-tck/build/Defs.SFBay.mk

sed -i 's#^JAVATEST_JAR_LOC\s*=\s*.*#JAVATEST_JAR_LOC = '"${WORKSPACE}"'/jaxb-tck/lib#g' $WORKSPACE/jaxb-tck/build/Defs.mk
sed -i 's#^SIGTESTDEV_JAR_LOC\s*=\s*.*#SIGTESTDEV_JAR_LOC = '"${WORKSPACE}"'/jaxb-tck/lib#g' $WORKSPACE/jaxb-tck/build/Defs.mk
sed -i 's#^ASM_JAR_LOCATION\s*=\s*.*#ASM_JAR_LOCATION = '"${WORKSPACE}"'#g' $WORKSPACE/jaxb-tck/build/Defs.mk

echo '-[ Defs.mk ]----------------------------------------------------------'
cat $WORKSPACE/jaxb-tck/build/Defs.mk
echo '----------------------------------------------------------------------'
echo '-[ Defs.SFBay.mk ]----------------------------------------------------'
cat $WORKSPACE/jaxb-tck/build/Defs.SFBay.mk
echo '----------------------------------------------------------------------'

cd $TCK_ROOT/jaxb-tck/build

make REPOSITORIES=$TCK_ROOT/xml_schema clean
make REPOSITORIES=$TCK_ROOT/xml_schema nightly

echo *************Building JAXB TCK Userguide************
cd $WORKSPACE/userguide;mvn
mkdir -p ${WORKSPACE}/jaxb-tck-build/docs/userguide
cp -r $WORKSPACE/userguide/target/staging/*.html $WORKSPACE/jaxb-tck-build/docs/userguide
cp -r $WORKSPACE/userguide/target/generated-docs/*.pdf $WORKSPACE/jaxb-tck-build/docs/userguide
echo *************Completed Building JAXB TCK Userguide************

mkdir -p ${WORKSPACE}/bundles
cd $WORKSPACE/jaxb-tck-build
chmod 777 *.jar
TCK_NAME=xml-binding
if [[ "$LICENSE" == "EFTL" || "$LICENSE" == "eftl" ]]; then
	cp $TCK_ROOT/LICENSE_EFTL.md .
	zip -r eclipse-${TCK_NAME}-tck-2.3.0.zip JAXB-TCK-2.3.jar LICENSE_EFTL.md docs/
	cp ${WORKSPACE}/jaxb-tck-build/eclipse-${TCK_NAME}-tck-2.3.0.zip ${WORKSPACE}/bundles/
else
	cp $TCK_ROOT/LICENSE.md .
	zip -r ${TCK_NAME}-tck-2.3.0.zip JAXB-TCK-2.3.jar LICENSE.md docs/
	cp ${WORKSPACE}/jaxb-tck-build/${TCK_NAME}-tck-2.3.0.zip ${WORKSPACE}/bundles/
fi
