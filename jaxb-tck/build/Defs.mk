#
# Copyright (c) 1998, 2018 Oracle and/or its affiliates. All rights reserved.
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


# Standard definitions for building the main TCK workspace
#
#----------------------------------------------------------------------
#
# JAXB Version under test
TEST_JAXB_VERSION = 2.3
TEST_JAXB_MILESTONE = fcs
TEST_JAXB_BUILD = b01

JAXB_ID = jaxb-$(TEST_JAXB_VERSION)-$(TEST_JAXB_MILESTONE)-$(TEST_JAXB_BUILD)

# TCK Version and release designation
TCKMM_STAND_ALONE = 23

TCKMM.sh = \
    if [ -z "$(JCKMM)" ]; then \
        echo $(TCKMM_STAND_ALONE) ; \
    else \
        echo $(JCKMM) ; \
    fi
TCKMM = $(shell $(TCKMM.sh))


# TCK Version and release designation
TCK.MM_STAND_ALONE = 2.3
TCK.MM.sh = \
    if [ -z "$(JCK.MM)" ]; then \
        echo $(TCK.MM_STAND_ALONE) ; \
    else \
        echo $(JCK.MM) ; \
    fi
TCK.MM = $(shell $(TCK.MM.sh))

TCK_MILESTONE=fcs

BUILD_NAME=b11

# JAXB TCK specific tests which use xml_schema repository's shared XSD and
# XML documents should be located in test's JAXB specific subdirectory 'prj.jaxb'
# in the repository.
# This TCK specific subdirectory name should be specified in PROJECT_SUBDIR
# to process files from these subdirectories correctly along with corresponding
# XSD and XML documents. Example: PROJECT_SUBDIR="prj.jaxb".

PROJECT_SUBDIR="prj.jaxb"

# xml_schema repository may contain tests specific not only for one TCK (JAXB TCK),
# but also for example JAXP TCK specific tests which should be located in 'prj.jaxp'
# subdirectories. To not process tests from other TCK specific subdirectories,
# all subdirectories should be specified in IGNORE_PROJECT_SUBDIRS.
# Example: IGNORE_PROJECT_SUBDIRS = "prj.jaxp prj.dom2 prj.dom3".

IGNORE_PROJECT_SUBDIRS=""

NORMALIZE_SUBDIR_NAME = (read d; normalized_d=\`echo $$d | sed -e 's|/*\(.*[^/]\)/*|\1|'\`; echo "/$$normalized_d/")

PRJ_DIR.sh = \
    s=`echo $(PROJECT_SUBDIR) | $(NORMALIZE_SUBDIR_NAME)` ; \
    echo $$s

PRJ_DIR = $(shell $(PRJ_DIR.sh))

IGNORE_PRJ_DIRS_FILTER.sh = \
    s=""; \
    for i in `echo $(IGNORE_PROJECT_SUBDIRS)` ; do \
        ni=`echo $$i | $(NORMALIZE_SUBDIR_NAME)` ; \
        s="$$s | $(GREP) -v '$$ni'" ; \
    done ; \
    echo $$s

IGNORE_PRJ_DIRS_FILTER = $(shell $(IGNORE_PRJ_DIRS_FILTER.sh))

#----------------------------------------------------------------------

PSR_COUNT = `/usr/sbin/psrinfo| wc -l`
JT_MULTIVM_CONCURRENCY.sh=/usr/bin/expr $(PSR_COUNT) \* 3 / 2
JT_MULTIVM_CONCURRENCY=$(shell $(JT_MULTIVM_CONCURRENCY.sh))
JT_SINGLEVM_CONCURRENCY=1

#----------------------------------------------------------------------

PARTIAL_TESTS.runtime = \
    test-runtime-batch-singleJVM.ok \
    test-runtime-batch-multiJVM.ok

SYSTEM_TESTS.runtime = \
    test-runtime-batch-singleJVM.ok \
    test-runtime-batch-multiJVM.ok

#----------------------------------------------------------------------


BUILD_ID = jaxb_tck-$(TCK.MM)-$(TCK_MILESTONE)-$(BUILD_NAME)

#determine where I am and determine Defs file for that domain
DOMAIN_DEFS.sh = \
    grep "^`domainname` " $(BUILDDIR)/Defs.map | cut -d " " -f2

DOMAIN_DEFS=$(shell $(DOMAIN_DEFS.sh))

include $(BUILDDIR)/Defs.SFBay.mk

#----------------------------------------------------------------------
#
# GENERAL_JAVAxxx are used to run Java programs like JavaTest, Jasm, Jcod, etc
# It is *not* used to run tests
#
GENERAL_JAVAC = $(GENERAL_JAVAHOME)/bin/javac -target 1.8
GENERAL_JAVA = $(GENERAL_JAVAHOME)/bin/java
GENERAL_JAVADOC = $(GENERAL_JAVAHOME)/bin/javadoc
GENERAL_JAVAG = $(GENERAL_JAVAHOME)/bin/java_g

#----------------------------------------------------------------------
#
# PRECOMPILE_JAVAxxx is used to generate any precompiled classes for release
# it is also used as the version of Java to be tested
#
PRECOMPILE_JAVAC = $(PRECOMPILE_JAVAHOME)/bin/javac -target 1.8
PRECOMPILE_JAVA = $(PRECOMPILE_JAVAHOME)/bin/java
PRECOMPILE_JAR = $(PRECOMPILE_JAVAHOME)/bin/jar
JAVATEST_JAR_LOC = 
SIGTESTDEV_JAR_LOC = 
ASM_JAR_LOCATION =
#----------------------------------------------------------------------
#
# General Utilities

AWK = /bin/awk
BASENAME = /usr/bin/basename
CHMOD = /bin/chmod
CAT = /bin/cat
CP = /bin/cp
DATE = /bin/date
DIRNAME = /usr/bin/dirname
EGREP = /bin/egrep
FIND = /bin/find
GREP = /bin/grep
KSH = /bin/ksh
LS = /bin/ls
MKDIR = /bin/mkdir
MV = /bin/mv
NAWK = /usr/local/bin/nawk
PRINTF = /usr/bin/printf
PS = /bin/ps
PWD = /bin/pwd
RM = /bin/rm -rf
SED = /bin/sed
SORT = /usr/bin/sort
SPLIT = /usr/bin/split
TAR = /bin/tar
TEE = /usr/bin/tee
TEST = /usr/bin/test
TR = /usr/bin/tr
UNAME = /bin/uname
LINE = /usr/bin/line

#----------------------------------------------------------------------
#
# Deduce PRODUCT from the subdirectory after build  (as in build/PRODUCT)
#
BUILDSUBDIR.sh = pwd | sed -e 's|^.*/[newBb]*uild/\([^/]*\)$\|\1|' -e 's|^.*/[newBb]*uild/\([^/]*\)/.*$\|\1|'
BUILDSUBDIR = $(shell $(BUILDSUBDIR.sh))

PRODUCT.sh = \
    if [ -z "$(JAXB_PRODUCT)" ]; then \
        echo $(BUILDSUBDIR) ; \
    else \
        echo $(JAXB_PRODUCT) ; \
    fi

PRODUCT = $(shell $(PRODUCT.sh))

# determine compiler/runtime by stripping off stuff up to and including -.
PRODUCT_BASE.sh = echo $(PRODUCT) | sed -e 's|^[^-]*-\([^-]*\).*|\1|'
PRODUCT_BASE = $(shell $(PRODUCT_BASE.sh))

#----------------------------------------------------------------------
#
# Version tags
TCKVERSION.sh = \
    if [ -z "$(JCKMM)" ]; then \
        echo $(PRODUCT)-$(TCK.MM) ; \
    else \
        echo $(PRODUCT)-$(TCK.MM) ; \
    fi

TCKVERSION = $(shell $(TCKVERSION.sh))


EXCLUDE_LIST.sh = \
    if [ -z "$(JCKMM)" ]; then \
        echo jaxb_tck$(TCKMM).jtx ; \
    else \
        echo jdk$(JCKMM).jtx ; \
    fi

EXCLUDE_LIST = $(shell $(EXCLUDE_LIST.sh))


#----------------------------------------------------------------------
#
# Tools
JTXCHECK = ../jtxcheck
JAVATEST_JAR = $(TCKDIR)/lib/javatest.jar
BUGTRAQ_SERVLET = http://javaweb.eng.sun.com/sqe/bugtraq/query

#----------------------------------------------------------------------
#
# the following runes figure out the main workspace directory
#
BDX = $(BUILDDIR)/
TOPDIR = $(BDX:./%=%)..
ABSTOPDIR.sh = cd $(TOPDIR); pwd
ABSTOPDIR = $(shell $(ABSTOPDIR.sh))

#----------------------------------------------------------------------
#
# RELDIR is used to help build useful .ok files
#
CWD.sh = pwd
RELDIR.sh = echo $(shell $(CWD.sh)) | $(SED) -e 's|^$(ABSTOPDIR)/||'
RELDIR = $(shell $(RELDIR.sh))

#----------------------------------------------------------------------
#
# Directory Macros
#
WSBASE.sh = /usr/bin/basename $(ABSTOPDIR)
WSBASE = $(shell $(WSBASE.sh))
TOPDIR_PARENT.sh = dirname $(ABSTOPDIR)

BUILDAREADIR.sh = \
    if [ -z "$(BUILDAREADIR)" ]; then \
        echo $(shell $(TOPDIR_PARENT.sh))/$(WSBASE)-build ; \
    else \
        echo $(BUILDAREADIR) ; \
    fi

BUILDAREA = $(shell $(BUILDAREADIR.sh))

TMPDIR = /tmp
TEMPBUILDAREA.sh = \
    if [ ! -z "$(USETMP)" ]; then \
      echo "$(TMPDIR)/$(WSBASE)$(shell $(BUILDNAME.sh))-build"; \
    else \
      echo $(BUILDAREA); \
    fi
TEMPBUILDAREA = $(shell $(TEMPBUILDAREA.sh))

BUILDCLASSDIR=$(TCKDIR)/classes

TEMPTESTSTORAGE = $(TEMPBUILDAREA)/tempTestStorage/$(PRODUCT)
TESTEXTRACTDIR = $(TEMPBUILDAREA)/testExtract/$(PRODUCT)
FILTEREDLIBS = $(TEMPBUILDAREA)/filteredLibs/$(PRODUCT)
PRECOMPILEDIR = $(TEMPBUILDAREA)/precompile/$(PRODUCT)
JMPPWORKDIR = $(TEMPBUILDAREA)/jmppWork/$(PRODUCT)
TCKDIR = $(TEMPBUILDAREA)/$(TCKVERSION)
TCKZIP = $(BUILDAREA)/$(TCKVERSION).jar
UNZIPDIR = $(TEMPBUILDAREA)/unzip
TESTDIR = $(TEMPBUILDAREA)/test
JTPLUGINBUILDDIR = $(TEMPBUILDAREA)/jtpluginWork
JTPLUGINCLASSDIR = $(BUILDCLASSDIR)
JTPLUGINSRCDIR = $(TOPDIR)/src/share/jttck
INSTALLER_EXPORT_FILE = $(BUILDAREA)/installer-classes.lst

#---------------------------------------------------------------------
#
# variables required by CFREF to unpack classes from jar files
#

IGNORE = java javax org

LEAFCLASSES = com.sun.javatest.lib.MultiTest com.sun.javatest.lib.MultiStatus




TOOLS_JAR=$(PRECOMPILE_JAVAHOME)/lib/tools.jar
JAXB_CLASSPATH.sh=(fl=`find $(JAXB_HOME)/mod -name '*.jar' -print` ; echo $$fl) | sed -e 's/ /:/g'
JAXB_CLASSPATH=$(shell $(JAXB_CLASSPATH.sh)):$(TOOLS_JAR)


#---------------------------------------------------------------------
#
# jmpp specific variables
#

LIBAPI_PROPERTIES = testjen.properties

#----------------------------------------------------------------------
#
# Model TCK build specific parameters
#

BTOOLS_TESTGEN_SA = $(JAXPLIBS_DIR)/testgen_sa.jar
BTOOLS_JAXP = $(JAXPLIBS_DIR)/jaxp.jar

# btools workspace
TGXMLTOOLS_WS.sh = \
    if [ -z "$(BTOOLS_WS)" ]; then \
        echo $(BTOOLS_WS_MODEL) ; \
    else \
        echo $(BTOOLS_WS) ; \
    fi

TGXMLTOOLS_WS = $(shell $(TGXMLTOOLS_WS.sh))

# Repository list to construct TCK from
REPOSITORIES = $(ABSTOPDIR)

TEST_REPOSITORIES.sh=echo "$(REPOSITORIES)" | $(SED) -e 's|/ | |g' -e 's|/$$||'
TEST_REPOSITORIES=$(shell $(TEST_REPOSITORIES.sh))

JMPP_FILES_IN_BUNDLE = 100
XML_DIRS_IN_BUNDLE = 150
PL_FILES_IN_BUNDLE = 100

JAXB_ID.sh=$(PRECOMPILE_JAVAHOME)/bin/java -jar $(JAXB_HOME)/mod/jaxb-xjc.jar -version 2>&1 | $(SED) -e 's/^[^"]*"//g' | $(SED) -e 's/".*$$//g'
JAXB_ID=$(shell $(JAXB_ID.sh))

# Integrated into JCK JAXB-TCK requires mk with shared variables
SHARED_MK.sh = \
    if [ ! -z "$(SHARED_VARS)" ]; then \
        echo " -f $(SHARED_VARS) "; \
    fi
SHARED_MK = $(shell $(SHARED_MK.sh))

# ABSTOPDIR of JCK for integrated JAXB-TCK or ABSTOPDIR of standalone JAXB-TCK
SHARED_ABSTOPDIR = $(ABSTOPDIR)

# various documentation files:
DOC_RELEASE_NOTE=$(TOPDIR)/src/share/doc/releaseNote
DOC_COPYRIGHT_TXT=$(TOPDIR)/src/share/doc/copyright.txt
DOC_COPYRIGHT_HTML=$(TOPDIR)/src/share/doc/COPYRIGHT-jaxbtck.html
DOC_README=$(TOPDIR)/src/share/doc/Readme
DOC_INDEX_HTML=$(TOPDIR)/src/share/doc/index.html

# variables for generating source bundle
SRCBUNDLE_TEMPDIR = $(BUILDAREA)/tempBundle/jaxb
# files and directories that should not be included into source bundle
SRCBUNDLE_RMFILES = build.txt COPYRIGHT-javatest.html README-javatest.html ReleaseNotes-javatest.html testsuite.jtt
SRCBUNDLE_RMFILES += lib linux solaris win32
SRCBUNDLE_RMFILES += tests/testsuite.jtd doc/javatest
# release documents that must be include into JAXB-TCK source bundle
SRCBUNDLE_RELEASE_DOCS =
SRCBUNDLE_DEST = $(BUILDAREA)/tempBundle
SRCBUNDLE_ZIP = $(SRCBUNDLE_DEST)/jaxb_tck-ro-src.zip

CLASSDEP = $(GENERAL_JAVA) -cp $(TCKDIR)/classes:$(ASM_JAR_LOCATION)/asm-7.0.jar:$(ASM_JAR_LOCATION)/asm-commons-7.0.jar:$(GENERAL_JAVAHOME)/lib/tools.jar org.apache.river.tool.ClassDep -in com -in javasoft

JAXB_LIBS.sh = for i in `$(LS) -1 $(JAXB_HOME)/mod/*.jar`; do JAXB_LIBS="$$JAXB_LIBS:$$i"; done; echo $$JAXB_LIBS;
JAXB_LIBS = $(shell $(JAXB_LIBS.sh))
JAVAC_6 = $(JAVAHOME_6)/bin/javac -target 1.8 -Xbootclasspath/p:$(JAXB_LIBS)
JAVA_6 = $(JAVAHOME_6)/bin/java -Xbootclasspath/p:$(JAXB_LIBS)

