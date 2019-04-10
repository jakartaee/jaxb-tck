#
# Copyright (c) 2017, 2018 Oracle and/or its affiliates. All rights reserved.
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
# Build Definitions for javasoft domain
#

# /java/re mapping goes sometimes bad in SCA so try to determine original path of the
# tools. Mapping problem leads to hanging the build.

# Have to define $(PWD) here since the original definition of $(PWD)
PWD = /usr/bin/pwd
# is below the line 'include Defs.<domain>.mk' in the Defs.mk



# Internal SQE Tools: used for JCKUTILS to build TCK
#INTERNAL_JCTTOOLS = /net/scanas411.us.oracle.com/export/java_re_archive/jdk-re/a/v06/jct-tools/3.2.2/fcs/binaries
# INTERNAL_JCTTOOLS = /java/re/jct-tools/4.1.1/archive/fcs/binaries



#Location of JAXB RI that should be tested
JAXB_HOME=

#used for ant tasks from the build
#JAXB_20_RI_HOME=



#SIGTEST_WORKSPACE=../../jck-sigtest13
#JAXB_SIGTEST_WS=../../jaxb-sigtest
SIGTEST_DIST=

#----------------------------------------------------------------------
#
# GENERAL_JAVAxxx are used to run Java programs like JavaTest, Jasm, Jcod, etc
# It is *not* used to run tests
#
GENERAL_JAVAHOME =
#----------------------------------------------------------------------
#
# PRECOMPILE_JAVAxxx is used to generate any precompiled classes for release
# it is also used as the version of Java to be tested
#
PRECOMPILE_JAVAHOME = $(GENERAL_JAVAHOME)
#-----------------------------------------------------------------------
#
# Java that is used for compilation java2schema tests in J2XEmitterBase
# (version >= 6)
#
JAVAHOME_6=

# SQE Utilities
PKGS_BASEDIR = /java/jct-tools/pkgs
# CURMUDGEON = $(PKGS_BASEDIR)/htmlcheck/bin/webCurmudgeon
# HTML2PS = $(PERL) $(PKGS_BASEDIR)/html2ps-1.0b1/bin/html2ps
HTMLCHECK = $(PKGS_BASEDIR)/htmlcheck_1.2.1/bin/htmlcheck
LINKLINT = $(PERL) $(PKGS_BASEDIR)/linklint_2.1/linklint
# DISTILL = /net/swpubs.sfbay/opt/local/framemaker,v7.0/bin/distill

# TODO FMBATCH = /net/swpubs.sfbay/opt/local/framemaker,v7.0/bin/fmbatch
FMBATCH = /java/jct-tools/pkgs/fm71/bin/fmbatch

ANT_HOME=
#location of misc. tools
UNZIP = /usr/bin/unzip
ZIP = /bin/zip
PERL = /usr/bin/perl

#----------------------------------------------------------------------
#
# Model TCK build specific parameters
#



# JAXP libraries directory
JAXPLIBS_DIR_SCA.sh = cd /; $(PWD)
JAXPLIBS_DIR = $(shell $(JAXPLIBS_DIR_SCA.sh))

#Location of Robot chart tools used to create wizard flow chart
ROBOCHART = /java/jct-tools/pkgs/robochart,v8.1

PARTIAL_TESTS.runtime = \
	test-runtime-batch-singleJVM.ok

SYSTEM_TESTS.runtime = \
	test-runtime-batch-singleJVM.ok


