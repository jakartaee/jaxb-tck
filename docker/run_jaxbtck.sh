#!/bin/bash -x

#
# Copyright (c) 2019, 2021 Oracle and/or its affiliates. All rights reserved.
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

TOP_GLASSFISH_DIR="glassfish6"
TCK_NAME=xml-binding-tck



if ls ${WORKSPACE}/bundles/*xml-binding-tck*.zip 1> /dev/null 2>&1; then
  echo "Using stashed bundle for xml-binding-tck created during the build phase"
  unzip -o -q ${WORKSPACE}/bundles/*xml-binding-tck*.zip -d ${WORKSPACE}/

  sed -i "s#^finder=.*#finder=com.sun.javatest.finder.BinaryTestFinder -binary ${WORKSPACE}/${TCK_NAME}/tests/testsuite.jtd#g" ${WORKSPACE}/${TCK_NAME}/testsuite.jtt
  if [[ "$RUNTIME" == "Glassfish" ]]; then
    sed -i "s#^jck.env.jaxb.classes.jaxbClasses=.*#jck.env.jaxb.classes.jaxbClasses=${WORKSPACE}/${TOP_GLASSFISH_DIR}/glassfish/modules/jakarta.xml.bind-api.jar ${WORKSPACE}/${TOP_GLASSFISH_DIR}/glassfish/modules/jaxb-osgi.jar ${WORKSPACE}/${TOP_GLASSFISH_DIR}/glassfish/modules/jersey-media-jaxb.jar ${WORKSPACE}/${TOP_GLASSFISH_DIR}/glassfish/modules/jakarta.activation.jar ${WORKSPACE}/checker.jar#g" ${WORKSPACE}/${TCK_NAME}/lib/javasoft-multiJVM.jti
  else
    sed -i "s#^jck.env.jaxb.classes.jaxbClasses=.*#jck.env.jaxb.classes.jaxbClasses=${WORKSPACE}/checker.jar ${WORKSPACE}/jaxb-ri/mod/jakarta.xml.bind-api.jar ${WORKSPACE}/jaxb-ri/mod/jaxb-impl.jar ${WORKSPACE}/jaxb-ri/mod/jaxb-jxc.jar ${WORKSPACE}/jaxb-ri/mod/jakarta.activation.jar#g" ${WORKSPACE}/${TCK_NAME}/lib/javasoft-multiJVM.jti
  fi

fi

if [ -z "$ANT_HOME" ]; then
  export ANT_HOME=/usr/share/ant/
fi

export JAVA_HOME=${JDK11_HOME}

if [[ "$JDK" == "JDK12" || "$JDK" == "jdk12" ]];then
  export JAVA_HOME=${JDK12_HOME}
elif [[ "$JDK" == "JDK13" || "$JDK" == "jdk13" ]];then
  export JAVA_HOME=${JDK13_HOME}
elif [[ "$JDK" == "JDK14" || "$JDK" == "jdk14" ]];then
  export JAVA_HOME=${JDK14_HOME}
elif [[ "$JDK" == "JDK15" || "$JDK" == "jdk15" ]];then
  export JAVA_HOME=${JDK15_HOME}
elif [[ "$JDK" == "JDK16" || "$JDK" == "jdk16" ]];then
  export JAVA_HOME=${JDK16_HOME}
elif [[ "$JDK" == "JDK17" || "$JDK" == "jdk17" ]];then
  export JAVA_HOME=${JDK17_HOME}
fi  

sed -i "s#^jck.env.jaxb.testExecute.cmdAsFile=.*#jck.env.jaxb.testExecute.cmdAsFile=${JAVA_HOME}/bin/java#g" ${WORKSPACE}/${TCK_NAME}/lib/javasoft-multiJVM.jti
sed -i "s#^WORKDIR=.*#WORKDIR=${WORKSPACE}/${TCK_NAME}/batch-multiJVM/work/#g" ${WORKSPACE}/${TCK_NAME}/lib/javasoft-multiJVM.jti
sed -i "s#^TESTSUITE=.*#TESTSUITE=${WORKSPACE}/${TCK_NAME}/#g" ${WORKSPACE}/${TCK_NAME}/lib/javasoft-multiJVM.jti


if [[ "$RUNTIME" == "Glassfish" ]]; then
  sed -i "s#^jck.env.jaxb.testExecute.otherEnvVars=.*#jck.env.jaxb.testExecute.otherEnvVars=JAVA_HOME\=${JAVA_HOME} JAXB_HOME=${WORKSPACE}/${TOP_GLASSFISH_DIR}/glassfish#g" ${WORKSPACE}/${TCK_NAME}/lib/javasoft-multiJVM.jti
else
  sed -i "s#^jck.env.jaxb.testExecute.otherEnvVars=.*#jck.env.jaxb.testExecute.otherEnvVars=JAVA_HOME\=${JAVA_HOME} JAXB_HOME=${WORKSPACE}/jaxb-ri#g" ${WORKSPACE}/${TCK_NAME}/lib/javasoft-multiJVM.jti
fi

export PATH=$JAVA_HOME/bin:$ANT_HOME/bin:$PATH

cat ${WORKSPACE}/${TCK_NAME}/lib/javasoft-multiJVM.jti


cd ${WORKSPACE}
export BASEDIR=`pwd`

WGET_PROPS="--progress=bar:force --no-cache"

if [ -z "${JAXB_RI_BUNDLE_URL}" ]; then
  export JAXB_RI_BUNDLE_URL='https://ci.eclipse.org/jaxb-impl/job/jaxb-ri-master-build/lastSuccessfulBuild/artifact/jaxb-ri/bundles/ri/target/jaxb-ri.zip'
fi

echo "JAXB_RI_BUNDLE_URL=${JAXB_RI_BUNDLE_URL}"

wget ${WGET_PROPS} ${JAXB_RI_BUNDLE_URL} -O jaxb-ri.zip && unzip -q -o ${WORKSPACE}/jaxb-ri.zip


if [ -z "$GF_HOME" ]; then
  export GF_HOME=$BASEDIR
fi

echo "ANT_HOME=$ANT_HOME"
echo "export JAVA_HOME=$JAVA_HOME"
echo "export MAVEN_HOME=$MAVEN_HOME"
echo "export PATH=$PATH"

mkdir -p ${HOME}/.m2

cd ${WORKSPACE}

cd $BASEDIR
if [ -z "$GF_BUNDLE_URL" ]; then
  echo "Using default url for GF bundle: $DEFAULT_GF_BUNDLE_URL"
  export GF_BUNDLE_URL=$DEFAULT_GF_BUNDLE_URL
fi
export TCK_ROOT=${WORKSPACE}
export  JAVA_TOOL_OPTIONS="-Dfile.encoding=UTF8"

wget $WGET_PROPS $GF_BUNDLE_URL -O latest-glassfish.zip

wget ${WGET_PROPS} https://repo1.maven.org/maven2/org/checkerframework/checker/3.5.0/checker-3.5.0.jar -O ${WORKSPACE}/checker.jar

unzip -q -o latest-glassfish.zip
chmod -R 777 ${TOP_GLASSFISH_DIR}

mkdir -p JAXB_REPORT/JAXB-TCK

cd ${TCK_NAME}/tests/api/signaturetest

####RUN tests with GLassfish JAXB
if [[ "$RUNTIME" == "Glassfish" ]]; then
	$JAVA_HOME/bin/java -jar ${WORKSPACE}/${TCK_NAME}/lib/javatest.jar -batch -testsuite ${WORKSPACE}/${TCK_NAME} -open ${WORKSPACE}/${TCK_NAME}/lib/javasoft-multiJVM.jti -workdir -create ${WORKSPACE}/batch-multiJVM/work -set jck.env.jaxb.xsd_compiler.skipValidationOptional Yes -set jck.env.jaxb.xsd_compiler.testCompile.xjcCmd "/bin/sh ${WORKSPACE}/${TCK_NAME}/linux/bin/xjc.sh" -set jck.env.jaxb.schemagen.run.jxcCmd "/bin/sh ${WORKSPACE}/${TCK_NAME}/linux/bin/schemagen.sh" -set jck.env.jaxb.testExecute.otherEnvVars "JAVA_HOME=${JAVA_HOME} JAXB_HOME=${WORKSPACE}/${TOP_GLASSFISH_DIR}/glassfish" -set jck.env.jaxb.classes.jaxbClasses "${WORKSPACE}/${TOP_GLASSFISH_DIR}/glassfish/modules/jakarta.xml.bind-api.jar ${WORKSPACE}/${TOP_GLASSFISH_DIR}/glassfish/modules/jaxb-osgi.jar ${WORKSPACE}/${TOP_GLASSFISH_DIR}/glassfish/modules/jersey-media-jaxb.jar ${WORKSPACE}/${TOP_GLASSFISH_DIR}/glassfish/modules/jakarta.activation.jar ${WORKSPACE}/checker.jar" -set jck.env.jaxb.classes.needJaxbClasses Yes -runtests
	$JAVA_HOME/bin/java -jar ${WORKSPACE}/${TCK_NAME}/lib/javatest.jar -batch -testsuite ${WORKSPACE}/${TCK_NAME} -open ${WORKSPACE}/${TCK_NAME}/lib/javasoft-multiJVM.jti -workdir ${WORKSPACE}/batch-multiJVM/work -set jck.env.jaxb.xsd_compiler.skipValidationOptional Yes -set jck.env.jaxb.xsd_compiler.testCompile.xjcCmd "/bin/sh ${WORKSPACE}/${TCK_NAME}/linux/bin/xjc.sh" -set jck.env.jaxb.schemagen.run.jxcCmd "/bin/sh ${WORKSPACE}/${TCK_NAME}/linux/bin/schemagen.sh" -set jck.env.jaxb.testExecute.otherEnvVars "JAVA_HOME=${JAVA_HOME} JAXB_HOME=${WORKSPACE}/${TOP_GLASSFISH_DIR}/glassfish" -set jck.env.jaxb.classes.jaxbClasses "${WORKSPACE}/${TOP_GLASSFISH_DIR}/glassfish/modules/jakarta.xml.bind-api.jar ${WORKSPACE}/${TOP_GLASSFISH_DIR}/glassfish/modules/jaxb-osgi.jar ${WORKSPACE}/${TOP_GLASSFISH_DIR}/glassfish/modules/jersey-media-jaxb.jar ${WORKSPACE}/${TOP_GLASSFISH_DIR}/glassfish/modules/jakarta.activation.jar ${WORKSPACE}/checker.jar" -set jck.env.jaxb.classes.needJaxbClasses Yes -set jck.priorStatus.needStatus Yes -set jck.priorStatus.status not_run -runtests
else 
####RUN tests with standalone JAXB RI
	$JAVA_HOME/bin/java -jar ${WORKSPACE}/${TCK_NAME}/lib/javatest.jar -batch -testsuite ${WORKSPACE}/${TCK_NAME} -open ${WORKSPACE}/${TCK_NAME}/lib/javasoft-multiJVM.jti -workdir -create ${WORKSPACE}/batch-multiJVM/work -set jck.env.jaxb.xsd_compiler.skipValidationOptional Yes -set jck.env.jaxb.xsd_compiler.testCompile.xjcCmd "/bin/sh ${WORKSPACE}/${TCK_NAME}/linux/bin/xjc.sh" -set jck.env.jaxb.schemagen.run.jxcCmd "/bin/sh ${WORKSPACE}/${TCK_NAME}/linux/bin/schemagen.sh" -set jck.env.jaxb.testExecute.otherEnvVars "JAVA_HOME=${JAVA_HOME} JAXB_HOME=${WORKSPACE}/jaxb-ri" -set jck.env.jaxb.classes.jaxbClasses "${WORKSPACE}/checker.jar ${WORKSPACE}/jaxb-ri/mod/jakarta.xml.bind-api.jar ${WORKSPACE}/jaxb-ri/mod/jaxb-impl.jar ${WORKSPACE}/jaxb-ri/mod/jaxb-jxc.jar ${WORKSPACE}/jaxb-ri/mod/jakarta.activation.jar" -set jck.env.jaxb.classes.needJaxbClasses Yes -runtests
	$JAVA_HOME/bin/java -jar ${WORKSPACE}/${TCK_NAME}/lib/javatest.jar -batch -testsuite ${WORKSPACE}/${TCK_NAME} -open ${WORKSPACE}/${TCK_NAME}/lib/javasoft-multiJVM.jti -workdir ${WORKSPACE}/batch-multiJVM/work -set jck.env.jaxb.xsd_compiler.skipValidationOptional Yes -set jck.env.jaxb.xsd_compiler.testCompile.xjcCmd "/bin/sh ${WORKSPACE}/${TCK_NAME}/linux/bin/xjc.sh" -set jck.env.jaxb.schemagen.run.jxcCmd "/bin/sh ${WORKSPACE}/${TCK_NAME}/linux/bin/schemagen.sh" -set jck.env.jaxb.testExecute.otherEnvVars "JAVA_HOME=${JAVA_HOME} JAXB_HOME=${WORKSPACE}/jaxb-ri" -set jck.env.jaxb.classes.jaxbClasses "${WORKSPACE}/checker.jar ${WORKSPACE}/jaxb-ri/mod/jakarta.xml.bind-api.jar ${WORKSPACE}/jaxb-ri/mod/jaxb-impl.jar ${WORKSPACE}/jaxb-ri/mod/jaxb-jxc.jar ${WORKSPACE}/jaxb-ri/mod/jakarta.activation.jar" -set jck.env.jaxb.classes.needJaxbClasses Yes -set jck.priorStatus.needStatus Yes -set jck.priorStatus.status not_run -runtests
fi

  $JAVA_HOME/bin/java -jar ${WORKSPACE}/${TCK_NAME}/lib/javatest.jar -workdir ${WORKSPACE}/batch-multiJVM/work -writereport ${WORKSPACE}/JAXB_REPORT/JAXB-TCK


export HOST=`hostname -f`
echo "1 JAXB-TCK ${HOST}" > ${WORKSPACE}/args.txt
mkdir -p ${WORKSPACE}/results/junitreports/
${JAVA_HOME}/bin/java -Djunit.embed.sysout=true -jar ${WORKSPACE}/docker/JTReportParser/JTReportParser.jar ${WORKSPACE}/args.txt ${WORKSPACE}/JAXB_REPORT ${WORKSPACE}/results/junitreports/
rm -f ${WORKSPACE}/args.txt
cd ${WORKSPACE}
tar zcvf jaxbtck-results.tar.gz ${WORKSPACE}/JAXB_REPORT ${WORKSPACE}/batch-multiJVM/work ${WORKSPACE}/results/junitreports/
