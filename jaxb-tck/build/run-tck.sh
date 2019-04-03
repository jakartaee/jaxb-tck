#!/bin/ksh
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

#
# command macros
SED=/usr/ucb/sed

#Determine unique name for javatest batch command file
THIS=$$
BATCH_COM_FILE=`pwd`/"${THIS}.jtb"
while [ -a "${BATCH_COM_FILE}" ];
do
    THIS=`expr ${THIS} + 1`
    BATCH_COM_FILE=`pwd`/"${THIS}.jtb"
done

while [ $# -gt 0 ] ; do
  case $1 in 
    -tck ) TCKDIR=`(cd $2; pwd)` ; shift ;;
    -javaHome )  JAVA_HOME=$2 ;	shift ;;
    -jaxbHome )   JAXB_HOME=$2 ;  shift ;;
    -agent ) needAgent=1;;
    -workdir) WORKDIR=$2; shift;;
    -concurrency) CONCURRENCY=$2; shift;;
    -tests) shift; INITIALURLS="$@"; break;; 
    * ) echo bad option: $1 ; exit 1 ;;
  esac
  shift
done

JAVA=$JAVA_HOME/bin/java
JAVAC=$JAVA_HOME/bin/javac

# quickly validate args
if [ -z "${TCKDIR}" ]; then
	echo "no TCK testsuite was specified."
	exit 1
fi 

if [ -z  "${WORKDIR}" ]; then 
	WORKDIR="${TCKDIR}"/work
fi

# guess a (hopefully free) set of ports for now
portBase=${portBase:-`expr \( $$ % 32768 \) + 32768`}
agentPoolPort=${agentPoolPort:-$portBase}

jtRuntimeArgs="-verify -ms64m -mx512m"

if [ "$needAgent" ]; then
  jtAppArgs="$jtAppArgs -startAgentPool -agentPoolPort $agentPoolPort"
  jtStartupDesc=", agent-pool port $agentPoolPort"
  mode=singleJVM
else
  mode=multiJVM
fi

JTI_FILE=${TCKDIR}/lib/javasoft-$mode.jti
JAXB_JAR_FILES=`find $JAXB_HOME/mod -name '*.jar' -print`
JAXB_JAR_FILES=`echo $JAXB_JAR_FILES`

echo "testsuite ${TCKDIR}" > "${BATCH_COM_FILE}"
echo "workdirectory -overwrite ${WORKDIR}" >> "${BATCH_COM_FILE}"
echo "open ${JTI_FILE}" >> "${BATCH_COM_FILE}"
if [ "$needAgent" != 1 ]; then 
    echo "set jck.concurrency.concurrency $CONCURRENCY" >> "${BATCH_COM_FILE}"
fi
#
#    echo "set jaxb_tck.jaxb_tck.xsd_compiler $JAXB_HOME/bin/xjc.sh" >> "${BATCH_COM_FILE}"
#    echo "set jaxb_tck.jaxb_tck.otherEnvVars \"JAVA_HOME=$JAVA_HOME JAXB_HOME=$JAXB_HOME\"" >> "${BATCH_COM_FILE}"
#    echo "set jaxb_tck.java_compiler $JAVAC" >> "${BATCH_COM_FILE}"
#    echo "set jaxb_tck.jvm $JAVA" >> "${BATCH_COM_FILE}"
#    echo "set jaxb_tck.jaxb \"$JAXB_JAR_FILES\"" >> "${BATCH_COM_FILE}"
#echo ${INITIALURLS:+ set jaxb_tck.tests.tests '"'${INITIALURLS}'"'} >> "${BATCH_COM_FILE}"
#echo "concurrency $CONCURRENCY" >> "${BATCH_COM_FILE}"
#
echo "runtests" >> "${BATCH_COM_FILE}"
echo "writeReport -create ${WORKDIR}/report" >> "${BATCH_COM_FILE}"

JAXB_JAR_CP=""
for i in `echo $JAXB_JAR_FILES` ; do
    JAXB_JAR_CP=$JAXB_JAR_CP:$i
done


cd $TCKDIR

# start JavaTest Agent if necessary
if [ "$needAgent" = 1 ]; then 

  # while loop provides for automatic restart if agent dies
  while echo "Starting JavaTest agent, connecting to JavaTest port $agentPoolPort"  
  do
    CLASSPATH=./lib/javatest.jar:./classes:`dirname $JAVA`/../lib/tools.jar:$JAXB_JAR_CP \
    DISPLAY=${TCKDISPLAY} \
	  ${JAVA}  -ms128m -mx256m \
	-Djava.security.policy=./lib/tck.policy \
	com.sun.javatest.agent.AgentMain -activeHost `uname -n` -activePort $agentPoolPort -concurrency $CONCURRENCY &
    agentPID=$!
    echo $agentPID > agent.pid # $agentPID not directly visible to outer shell
    wait $agentPID
    if [ -s agent.pid ]; then
        rm agent.pid
    fi
    echo "Agent exit on: " `date`
  done  &
  agentWatcherPID=$!
else
  jtRuntimeArgs=${jtRuntimeArgs}
fi


cleanup() {
  if [ "$needAgent" = 1 ]; then 
      # kill the agent watcher if it was started
      if [ ! -z "$agentWatcherPID" ]; then
        echo "Killing Agent watcher"
        kill -9 $agentWatcherPID
      fi
  
      # kill the agent if it was started
      if [ -s agent.pid ]; then
        echo "Killing Agent"
        kill -9 `cat agent.pid`
        rm agent.pid
      fi
  fi
}

trap cleanup 0 INT QUIT KILL TERM

# now run JavaTest itself
echo "Java:" `$JAVA -fullversion 2>&1`
echo "Starting JavaTest$jtStartupDesc"
echo "jtruntimeArgs is $jtRuntimeArgs."
echo "jtAppArgs is $jtAppArgs"
echo 
echo INITIALURLS=${INITIALURLS}
 	${JAVA} -classpath ./lib/javatest.jar:./classes:$JAVA_HOME/lib/tools.jar:$JAXB_JAR_CP \
	$jtRuntimeArgs com.sun.javatest.tool.Main \
	${jtAppArgs} -batch @"${BATCH_COM_FILE}"
exitCode=$?
echo "JavaTest complete"

exit $exitCode
