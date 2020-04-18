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
# Makefile for a TCK product



#----------------------------------------------------------------------

TCK_SPECIFIC_FILES = \
	src/share/classes/javasoft/sqe/jck/lib/MultiTestExt.java \
	src/share/classes/javasoft/sqe/jck/lib/ArrayStringValue.java \
	src/share/classes/javasoft/sqe/jck/lib/StringValue.java \
	src/share/classes/javasoft/sqe/jck/lib/URIValue.java \
	src/share/classes/javasoft/sqe/jck/lib/SchemaLoader.java \
	src/share/classes/javasoft/sqe/tests/api/jakarta/xml/bind/JAXBTestBase.java \
	src/share/classes/javasoft/sqe/tests/api/jakarta/xml/bind/Logger2WriterHandler.java \
	src/share/classes/javasoft/sqe/tests/api/jakarta/xml/bind/JAXBTest.java \
	src/share/classes/javasoft/sqe/tests/api/jakarta/xml/bind/CTTest.java \
	src/share/classes/javasoft/sqe/tests/api/jakarta/xml/bind/SchemaGenTest.java \
	src/share/classes/javasoft/sqe/tests/api/jakarta/xml/bind/SchemaGenTestExt.java \
	src/share/classes/javasoft/sqe/tests/api/jakarta/xml/bind/ErrorCollector.java \
	src/share/classes/javasoft/sqe/tests/api/jakarta/xml/bind/J2XRuntimeTest.java \
	src/share/classes/javasoft/sqe/tests/api/jakarta/xml/bind/JAXBValidationCheckerTest.java \
	src/share/classes/com/sun/jaxb_tck/sigtest/JaxbPlugin.java \
	src/share/classes/com/sun/jaxb_tck/sigtest/JAXBTest.java


files-src.lst: testsources.ok $(TCK_SPECIFIC_FILES:%=$(TCKDIR)/%)
	@echo "+++ shared source files to be installed and compiled: $@"
	(cd $(TCKDIR); $(FIND) src/share/classes -name \*.java -type f -print) > $@

CLEANFILES += files-src.lst
TCKDIRFILES = $(TCK_SPECIFIC_FILES:%=$(TCKDIR)/%)
TOPDIRFILES = $(TCKDIRFILES:$(TCKDIR)/%=$(TOPDIR)/%)

$(TCK_SPECIFIC_FILES:%=$(TCKDIR)/%): install $(TOPDIRFILES)
	./install $(TOPDIR) $(TCKDIR) $(@:$(TCKDIR)/%=%)

#----------------------------------------------------------------------
#
# Precompile the javasoft shared test libraries.
#
# The set of files is product specific
$(BUILDCLASSDIR):
	@ $(TEST) -d $(BUILDCLASSDIR) || $(MKDIR) -p $(BUILDCLASSDIR)

precompile-src.ok: javatest.ok jtlegacy.ok files-src.lst $(BUILDCLASSDIR) com.sun.jaxb_tck.all.ok
	@echo precompile shared test libraries
	CLASSPATH=$(BUILDCLASSDIR):$(JAVATEST_JAR):$(JAXB_CLASSPATH):$(JTPLUGINCLASSDIR) \
	$(PRECOMPILE_JAVAC) -d $(BUILDCLASSDIR) \
	`$(CAT) files-src.lst | $(SED) -e "s|^|$(TCKDIR)/|"`
	echo shared test libraries precompiled at `date` > $@

ZIP.files += precompile-src.ok

