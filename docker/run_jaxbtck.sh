#!/bin/bash -x

#
# Copyright (c) 2019, 2020 Oracle and/or its affiliates. All rights reserved.
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
cd $BASEDIR
if [ -z "$GF_BUNDLE_URL" ]; then
  echo "Using default url for GF bundle: $DEFAULT_GF_BUNDLE_URL"
  export GF_BUNDLE_URL=$DEFAULT_GF_BUNDLE_URL
fi
export TCK_ROOT=$WORKSPACE
export  JAVA_TOOL_OPTIONS="-Dfile.encoding=UTF8"

wget $WGET_PROPS $GF_BUNDLE_URL -O latest-glassfish.zip
unzip -o latest-glassfish.zip
chmod -R 777 glassfish5

mkdir -p JAXB_REPORT/JAXB-TCK

cd jaxb-tck-build/unzip/JAXB-TCK-2.3/lib

$JAVA_HOME/bin/java -jar javatest.jar -batch -testsuite $WORKSPACE/jaxb-tck-build/unzip/JAXB-TCK-2.3 -open javasoft-multiJVM.jti -workdir -create $WORKSPACE/batch-multiJVM/work -set jck.env.jaxb.xsd_compiler.skipValidationOptional Yes -set jck.env.jaxb.xsd_compiler.testCompile.xjcCmd "/bin/sh $WORKSPACE/jaxb-tck-build/unzip/JAXB-TCK-2.3/linux/bin/xjc.sh" -set jck.env.jaxb.schemagen.run.jxcCmd "/bin/sh $WORKSPACE/jaxb-tck-build/unzip/JAXB-TCK-2.3/linux/bin/schemagen.sh" -set jck.env.jaxb.testExecute.otherEnvVars "JAVA_HOME=/opt/jdk1.8.0_191 JAXB_HOME=$WORKSPACE/jaxb-ri JAXB_ENDORSED=$WORKSPACE/jaxb-ri/mod/jakarta.xml.bind-api.jar" -set jck.env.jaxb.testExecute.otherOpts "-Djava.endorsed.dirs=$WORKSPACE/glassfish5/glassfish/modules/endorsed/" -set jck.env.jaxb.classes.jaxbClasses "$JAVA_HOME/lib/tools.jar $WORKSPACE/jaxb-ri/mod/jakarta.xml.bind-api.jar $WORKSPACE/jaxb-ri/mod/jaxb-impl.jar $WORKSPACE/jaxb-ri/mod/jaxb-jxc.jar $WORKSPACE/jaxb-ri/mod/jakarta.activation.jar $WORKSPACE/glassfish5/glassfish/modules/jersey-media-jaxb.jar" -set jck.env.jaxb.classes.needJaxbClasses Yes -runtests

$JAVA_HOME/bin/java -jar javatest.jar -batch -testsuite $WORKSPACE/jaxb-tck-build/unzip/JAXB-TCK-2.3 -open javasoft-multiJVM.jti -workdir $WORKSPACE/batch-multiJVM/work -set jck.env.jaxb.xsd_compiler.skipValidationOptional Yes -set jck.env.jaxb.xsd_compiler.testCompile.xjcCmd "/bin/sh $WORKSPACE/jaxb-tck-build/unzip/JAXB-TCK-2.3/linux/bin/xjc.sh" -set jck.env.jaxb.schemagen.run.jxcCmd "/bin/sh $WORKSPACE/jaxb-tck-build/unzip/JAXB-TCK-2.3/linux/bin/schemagen.sh" -set jck.env.jaxb.testExecute.otherEnvVars "JAVA_HOME=/opt/jdk1.8.0_191 JAXB_HOME=$WORKSPACE/jaxb-ri JAXB_ENDORSED=$WORKSPACE/jaxb-ri/mod/jakarta.xml.bind-api.jar" -set jck.env.jaxb.testExecute.otherOpts "-Djava.endorsed.dirs=$WORKSPACE/glassfish5/glassfish/modules/endorsed/" -set jck.env.jaxb.classes.jaxbClasses "$JAVA_HOME/lib/tools.jar $WORKSPACE/jaxb-ri/mod/jakarta.xml.bind-api.jar $WORKSPACE/jaxb-ri/mod/jaxb-impl.jar $WORKSPACE/jaxb-ri/mod/jaxb-jxc.jar $WORKSPACE/jaxb-ri/mod/jakarta.activation.jar $WORKSPACE/glassfish5/glassfish/modules/jersey-media-jaxb.jar" -set jck.env.jaxb.classes.needJaxbClasses Yes -set jck.priorStatus.needStatus Yes -set jck.priorStatus.status not_run -runtests

$JAVA_HOME/bin/java -jar javatest.jar -workdir $WORKSPACE/batch-multiJVM/work -writereport $WORKSPACE/JAXB_REPORT/JAXB-TCK

export HOST=`hostname -f`
echo "1 JAXB-TCK ${HOST}" > ${WORKSPACE}/args.txt
mkdir -p ${WORKSPACE}/results/junitreports/
${JAVA_HOME}/bin/java -Djunit.embed.sysout=true -jar ${WORKSPACE}/docker/JTReportParser/JTReportParser.jar ${WORKSPACE}/args.txt $WORKSPACE/JAXB_REPORT ${WORKSPACE}/results/junitreports/
rm -f ${WORKSPACE}/args.txt
cd $WORKSPACE
tar zcvf jaxbtck-results.tar.gz $WORKSPACE/JAXB_REPORT $WORKSPACE/batch-multiJVM/work ${WORKSPACE}/results/junitreports/