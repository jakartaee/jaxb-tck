#
# Copyright (c) 2001, 2018 Oracle and/or its affiliates. All rights reserved.
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

# Build Definitions for NSK domain

# FCS SQE Tools version and build: used for JavaTest in TCK
FCS_JCTTOOLS = /set/java/re/jct-tools/3.2.2/promoted/latest/bundles

# Internal SQE Tools: used for JCKUTILS to build TCK
INTERNAL_JCTTOOLS = /set/java/re/jct-tools/3.2.2/promoted/latest/binaries

# Location of CFREF, used to identify classes to be unpacked from jt.jar
# classInfo home
CLASSINFO_HOME = /set/java-sqe/promotions/classInfo_1.1

#Location of ClassInfo library needed by JCK since 1.5
CLASSINFOLIB = $(CLASSINFO_HOME)/lib/ClassInfoLib.jar

# Build platform detection: sparc or x86
BUILD_PLATFORM.sh = \
	uname -m;
BUILD_PLATFORM = $(BUILD_PLATFORM.sh:sh)
 
#Location of CFREF, used to identify classes to be unpacked from jt.jar
CFREF.sh = \
   if [ "${BUILD_PLATFORM}" = "sun4u" ] ; then \
       echo JAVA_HOME=/set/java/jdk1.3.1/solaris $(CLASSINFO_HOME)/bin/solaris/cfref; \
   else \
	    echo JAVA_HOME=/set/java/jdk1.3.1/x86 $(CLASSINFO_HOME)/bin/solaris/cfref; \
   fi
CFREF = $(CFREF.sh:sh)   

#Location of JAXB that should be tested
JAXB_HOME = /set/java/re/jaxb/2.1.3/latest/binaries

#used for ant tasks from the build
JAXB_20_RI_HOME = /set/java/re/jaxb/2.1.3/latest/binaries

JCTT_HOME=/set/java-sqe/promotions/jctt-1_2-beta

#SIGTEST_WORKSPACE=../../jck-sigtest13
#JAXB_SIGTEST_WS=../../jaxb-sigtest
SIGTEST_DIST=/java/re/sigtest/4.0/promoted/fcs/latest/binaries/sigtest-4.0

#----------------------------------------------------------------------
#
# GENERAL_JAVAxxx are used to run Java programs like JavaTest, Jasm, Jcod, etc
# It is *not* used to run tests
#
GENERAL_JAVAHOME.sh = \
	if [ "${BUILD_PLATFORM}" = "sun4u" ] ; then \
	    echo /set/java/re/jdk/1.5.0_08/latest/binaries/solaris-sparc; \
	else \
	    echo /set/java/re/jdk/1.5.0_08/latest/binaries/solaris-amd64; \
	fi
GENERAL_JAVAHOME = $(GENERAL_JAVAHOME.sh:sh)
#----------------------------------------------------------------------
#
# PRECOMPILE_JAVAxxx is used to generate any precompiled classes for release
# it is also used as the version of Java to be tested
#
PRECOMPILE_JAVAHOME.sh = \
	if [ "${BUILD_PLATFORM}" = "sun4u" ] ; then \
	    echo /set/java/re/jdk/1.5.0_08/latest/binaries/solaris-sparc; \
	else \
	    echo /set/java/re/jdk/1.5.0_08/latest/binaries/solaris-amd64; \
	fi
PRECOMPILE_JAVAHOME = $(GENERAL_JAVAHOME.sh:sh)

#-----------------------------------------------------------------------
#
# Java that is used for compilation java2schema tests in J2XEmitterBase
# (version >= 6)
#
JAVAHOME_6.sh = \
	if [ "${BUILD_PLATFORM}" = "sun4u" ] ; then \
	    echo /set/java/re/jdk/6/archive/fcs/binaries/solaris-sparc; \
	else \
	    echo /set/java/re/jdk/6/archive/fcs/binaries/solaris-amd64; \
	fi
JAVAHOME_6 = $(JAVAHOME_6.sh:sh)

# SQE Utilities
PKGS_BASEDIR = /set/java-sqe/tools
CURMUDGEON = $(PKGS_BASEDIR)/htmlcheck/bin/webCurmudgeon
HTML2PS = $(PKGS_BASEDIR)/html2ps/html2ps
HTMLCHECK = $(PKGS_BASEDIR)/htmlcheck/bin/htmlcheck
LINKLINT = $(PERL) $(PKGS_BASEDIR)/linklint/linklint_2.1/linklint
DISTILL = /export/ld10/FrameMaker_6.0/bin/distill
FMBATCH = /export/ld10/FrameMaker_6.0/bin/fmbatch

# Location of misc. tools
LOCAL_SPB.sh = \
	if [ "`${UNAME} -r`" = "5.8" -o "`${UNAME} -r`" = "5.9" -o "`${UNAME} -r`" = "5.10" ]; then \
	    echo "" ; \
	else \
	    echo "local\/"; \
	fi 
 
#location of misc. tools
UNZIP = /usr/$(LOCAL_SPB.sh:sh)bin/unzip
ZIP = /usr/$(LOCAL_SPB.sh:sh)bin/zip
PERL = /usr/$(LOCAL_SPB.sh:sh)bin/perl

#----------------------------------------------------------------------
#
# Model TCK build specific parameters
#

# btools workspace
BTOOLS_WS_MODEL = /set/java-sqe/promotions/TCK_BTools_1.3-beta

# btools-sqe-int workspace
BTOOLS_SQE_INT_WS = /set/java-sqe/btools13-sqe-int

#apache ant location
ANT_HOME =/set/java-sqe/tools/ant/apache-ant-1.6.5

# JAXP libraries directory
JAXPLIBS_DIR=/set/java/re/jaxp/1.4/promoted/fcs/latest/binaries/jaxp-1.4/lib

# workaround for bug in DefaultHtmlGenerator (it needs jdk1.3 to run, not jdk1.4)
JAVA_13 = /set/java/jdk1.3.0/solaris-sparc/bin/java

# tckbtools.jar produced by btools build
TCKBTOOLS_JAR_MODEL = $(BTOOLS_WS_MODEL)/lib/tckbtools.jar

JT_MULTIVM_CONCURRENCY_NBSP.sh= \
	if [ -z "$(INITIALURL)" -a -z "$(INITIALURLS)" ] ; then \
	    echo $(JT_MULTIVM_CONCURRENCY.sh:sh) ; \
	else \
	    echo $(PSR_COUNT) ; \
	fi

JT_MULTIVM_CONCURRENCY=$(JT_MULTIVM_CONCURRENCY_NBSP.sh:sh)

PARTIAL_TESTS.runtime = \
	test-runtime-batch-singleJVM.ok \
	test-runtime-batch-multiJVM.ok 

SYSTEM_TESTS.runtime = \
	test-runtime-batch-singleJVM.ok \
	test-runtime-batch-multiJVM.ok 

JINI_ZIP = /set/metck/jini/jini-2_0_002-src.zip

