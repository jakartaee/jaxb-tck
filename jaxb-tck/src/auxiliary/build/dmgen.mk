#
# Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
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

include commands.mk

JAVA = java
JAVAC = javac
JAR = jar

CURDIR.SH = echo `${PWD}`

TCKROOT = ${CURDIR.SH:sh}/../../..
TEMPBUILDDIR = ${TCKROOT}/../tempbuilddir

LIB=/set/java/re/jct-tools/3.2.2/latest/binaries/lib
JAXB_HOME=/java/re/jaxb/2.1.2/promoted/fcs/latest/binaries

CLASSPATH = ${LIB}/jtlegacy.jar:${LIB}/javatest.jar
#JAXB_CLASSPATH.sh = (fl=`find $(JAXB_HOME)/mod -name '*.jar' -print` ; echo $$fl) | sed -e 's/ /:/g'
#JAXB_CLASSPATH = ${JAXB_CLASSPATH.sh:sh}

# target for DefaultMappingTests
DefaultMappingTest : ${TCKROOT}/tests/java2schema/DefaultMapping/DefaultMappingTest.test.xml
	${RM} ${TEMPBUILDDIR}

${TCKROOT}/tests/java2schema/DefaultMapping/DefaultMappingTest.test.xml : \
        	${TEMPBUILDDIR}/DefaultMappingTestsGenerator.jar ${TCKROOT}/tests/java2schema/DefaultMapping
	${JAVA} -cp ${CLASSPATH}:${TEMPBUILDDIR}/DefaultMappingTestsGenerator.jar \
		-Djava.endorsed.dirs=$(JAXB_HOME)/mod \
		tests.java2schema.defaultmapping.DefaultMappingTestsGenerator ${TCKROOT}/tests/java2schema/DefaultMapping
    
${TCKROOT}/tests/java2schema/DefaultMapping :
	${MKDIR} -p ${TCKROOT}/tests/java2schema/DefaultMapping

${TEMPBUILDDIR}/DefaultMappingTestsGenerator.jar : \
        	${TEMPBUILDDIR}/classes/tests/java2schema/defaultmapping/DefaultMappingTestsGenerator.class
	${JAR} -cf ${TEMPBUILDDIR}/DefaultMappingTestsGenerator.jar \
		-C ${TEMPBUILDDIR}/classes/. .
        
TESTGENFILES.sh = ${FIND} ${TCKROOT}/src/auxiliary/classes/tgxml/testgen/*.java -prune
TESTGENFILES = ${TESTGENFILES.sh:sh}
        
DMTSOURCES = \
    ${TCKROOT}/src/auxiliary/classes/tests/java2schema/DefaultMapping/DefaultMappingTestsGenerator.java \
    ${TESTGENFILES} \
    ${TCKROOT}/src/share/classes/javasoft/sqe/jck/lib/MultiTestExt.java \
    ${TCKROOT}/src/share/classes/javasoft/sqe/jck/lib/Pair.java \
    ${TCKROOT}/src/share/classes/javasoft/sqe/jck/lib/StringValue.java \
    ${TCKROOT}/src/share/classes/javasoft/sqe/tests/api/jakarta/xml/bind/SchemaGenTestExt.java \
    
${TEMPBUILDDIR}/classes/tests/java2schema/defaultmapping/DefaultMappingTestsGenerator.class : ${DMTSOURCES} ${TEMPBUILDDIR}/classes/.
	${RM} ${TEMPBUILDDIR}/classes/.
	${JAVAC} -cp ${CLASSPATH} -d ${TEMPBUILDDIR}/classes/. ${DMTSOURCES}
    
${TEMPBUILDDIR}/classes/. : 
	${MKDIR} -p ${TEMPBUILDDIR}/classes/.
