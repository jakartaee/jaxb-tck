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

# Partial Makefile for TCK lib files


#----------------------------------------------------------------------
#
# Precompile the classes required when building TCK
#
#----------------------------------------------------------------------


#----------------------------------------------------------------------
#
# CONVER_TO_SOURCE macro helps to convert the class name into the
# model build file name
#
CONVERT_TO_SOURCE = $(SED) -e 's|\.|/|g' -e 's|$$|.java|' -e 's|^|$(TOPDIR)/src/share/classes/|'


#----------------------------------------------------------------------
#
# Precompile testfilter plugin
#
TESTFILTER_PLUGIN = com.sun.tgxml.tools.filter.plugins.XMLSchemaTestFilter

TESTFILTER_PLUGIN_SRC.sh = echo $(TESTFILTER_PLUGIN) | $(CONVERT_TO_SOURCE)
TESTFILTER_PLUGIN_SRC = $(shell $(TESTFILTER_PLUGIN_SRC.sh))

precompile-testfilter-plugin.ok: $(TESTFILTER_PLUGIN_SRC)
	@ $(TEST) -d classes || $(MKDIR) -p classes
	@echo precompile testfilter plugin class
	CLASSPATH=$(TCKDIR)/classes $(GENERAL_JAVAC) -d classes \
		$(TESTFILTER_PLUGIN_SRC)
	echo testfilter plugin precompiled at `date` > $@

CLEANFILES += classes

#----------------------------------------------------------------------
#
# Precompile XMLSchemaTestEmitter plugin
#

TESTEMITTER_PLUGIN = com.sun.tgxml.tools.testgen.processors.emitter.XMLSchemaTestEmitter

TESTEMITTER_PLUGIN_SRC.sh = echo $(TESTEMITTER_PLUGIN) | $(CONVERT_TO_SOURCE)
TESTEMITTER_PLUGIN_SRC = $(shell $(TESTEMITTER_PLUGIN_SRC.sh))

precompile-testemitter-plugin.ok: $(TESTEMITTER_PLUGIN_SRC) javatest.ok jtlegacy.ok precompile-bundle-testgenfilter.ok
	@ $(TEST) -d classes || $(MKDIR) -p classes
	@echo precompile testemitter plugin class
	CLASSPATH=$(JAXB_CLASSPATH):$(JAVATEST_JAR):$(TCKDIR)/classes:classes \
	$(GENERAL_JAVAC) -d classes \
		$(TESTEMITTER_PLUGIN_SRC) \
		$(TOPDIR)/src/share/classes/com/sun/tgxml/tools/testgen/processors/ir/JCKIRPFactorySpi.java \
		$(TOPDIR)/src/share/classes/com/sun/tgxml/tools/testgen/processors/ir/XMLSchemaTestsIRProcessor.java \
		$(TOPDIR)/src/share/classes/com/sun/tgxml/tools/testgen/processors/ir/ConveyerIRProcessor.java \
		$(TOPDIR)/src/share/classes/javasoft/sqe/jck/lib/MultiTestExt.java \
		$(TOPDIR)/src/share/classes/javasoft/sqe/jck/lib/ArrayStringValue.java \
		$(TOPDIR)/src/share/classes/javasoft/sqe/jck/lib/StringValue.java \
		$(TOPDIR)/src/share/classes/javasoft/sqe/jck/lib/URIValue.java
	echo testemitter plugin precompiled at `date` > $@

CLEANFILES += classes

#----------------------------------------------------------------------
#
# Precompile J2XTestEmitter plugin
#

J2X_TESTEMITTER_PLUGIN = com.sun.tgxml.tools.testgen.processors.emitter.J2XTestEmitter

J2X_TESTEMITTER_PLUGIN_SRC.sh = echo $(J2X_TESTEMITTER_PLUGIN) | $(CONVERT_TO_SOURCE)
J2X_TESTEMITTER_PLUGIN_SRC = $(shell $(J2X_TESTEMITTER_PLUGIN_SRC.sh))

J2X_TESTEMITTER_PLUGIN_2 = com.sun.tgxml.tools.testgen.processors.emitter.J2XRuntimeEmitter
J2X_TESTEMITTER_PLUGIN_SRC_2.sh = echo $(J2X_TESTEMITTER_PLUGIN_2) | $(CONVERT_TO_SOURCE)
J2X_TESTEMITTER_PLUGIN_SRC_2 = $(shell $(J2X_TESTEMITTER_PLUGIN_SRC_2.sh))

J2X_TESTEMITTER_PLUGIN_3 = com.sun.tgxml.tools.testgen.processors.emitter.J2XTestWTBEmitter
J2X_TESTEMITTER_PLUGIN_SRC_3.sh = echo $(J2X_TESTEMITTER_PLUGIN_3) | $(CONVERT_TO_SOURCE)
J2X_TESTEMITTER_PLUGIN_SRC_3 = $(shell $(J2X_TESTEMITTER_PLUGIN_SRC_3.sh))

J2X_TESTEMITTER_PLUGIN_4 = com.sun.tgxml.tools.testgen.processors.emitter.J2XEmitterBase
J2X_TESTEMITTER_PLUGIN_SRC_4.sh = echo $(J2X_TESTEMITTER_PLUGIN_4) | $(CONVERT_TO_SOURCE)
J2X_TESTEMITTER_PLUGIN_SRC_4 = $(shell $(J2X_TESTEMITTER_PLUGIN_SRC_4.sh))

precompile-j2x-testemitter-plugin.ok: $(J2X_TESTEMITTER_PLUGIN_SRC) javatest.ok $(J2X_TESTEMITTER_PLUGIN_SRC_2) $(J2X_TESTEMITTER_PLUGIN_SRC_3) $(J2X_TESTEMITTER_PLUGIN_SRC_4)
	@ $(TEST) -d classes || $(MKDIR) -p classes
	@echo precompile java-to-schema testemitter plugin class
	CLASSPATH=$(TCKDIR)/classes:$(JAVATEST_JAR) \
	$(JAVAC_6) -d classes \
		$(J2X_TESTEMITTER_PLUGIN_SRC) $(J2X_TESTEMITTER_PLUGIN_SRC_2) $(J2X_TESTEMITTER_PLUGIN_SRC_3)  $(J2X_TESTEMITTER_PLUGIN_SRC_4) \
		$(TOPDIR)/src/share/classes/com/sun/tgxml/tools/testgen/processors/emitter/J2XProcessor.java
	echo java-to-schema testemitter plugin precompiled at `date` > $@

CLEANFILES += classes

#----------------------------------------------------------------------
#
# Precompile libfilter plugin
#
LIBFILTER_PLUGIN = com.sun.tgxml.tools.filter.plugins.RuntimeFilter

LIBFILTER_PLUGIN_SRC.sh = echo $(LIBFILTER_PLUGIN) | $(CONVERT_TO_SOURCE)
LIBFILTER_PLUGIN_SRC = $(shell $(LIBFILTER_PLUGIN_SRC.sh))

precompile-libfilter-plugin.ok: $(LIBFILTER_PLUGIN_SRC)
	@ $(TEST) -d classes || $(MKDIR) -p classes
	@echo precompile libfilter plugin class
	CLASSPATH=$(TCKDIR)/classes \
	$(GENERAL_JAVAC) -d classes \
		$(LIBFILTER_PLUGIN_SRC)
	echo testfilter plugin precompiled at `date` > $@

#----------------------------------------------------------------------
#
# Precompile JCKIndexGenerator classes
#
JCK_INDEX_GENERATOR = com.sun.tgxml.tools.indexgen.JCKIndexGenerator

JCK_INDEX_GENERATOR_SRC.sh = echo $(JCK_INDEX_GENERATOR) | $(CONVERT_TO_SOURCE)
JCK_INDEX_GENERATOR_SRC = $(shell $(JCK_INDEX_GENERATOR_SRC.sh))

JCK_INDEX_GEN_CLASS = com.sun.tgxml.tools.indexgen.JCKIndexGen
JCK_INDEX_GEN_CLASS_SRC.sh = echo $(JCK_INDEX_GEN_CLASS) | $(CONVERT_TO_SOURCE)
JCK_INDEX_GEN_CLASS_SRC = $(shell $(JCK_INDEX_GEN_CLASS_SRC.sh))


JCK_INDEX_HTTLFILE = com.sun.tgxml.tools.indexgen.JCKHtmlFile
JCK_INDEX_HTTLFILE_SRC.sh = echo $(JCK_INDEX_HTTLFILE) | $(CONVERT_TO_SOURCE)
JCK_INDEX_HTTLFILE_SRC = $(shell $(JCK_INDEX_HTTLFILE_SRC.sh))


precompile-jck-index-generator.ok: $(JCK_INDEX_GENERATOR_SRC) \
				   $(JCK_INDEX_GEN_CLASS_SRC) \
				   $(JCK_INDEX_HTTLFILE_SRC)
	@ $(TEST) -d classes || $(MKDIR) -p classes
	@echo precompile JCKIndexGenerator class
	CLASSPATH=$(TCKDIR)/classes \
	$(GENERAL_JAVAC) -d classes \
		$(JCK_INDEX_GENERATOR_SRC) \
		$(JCK_INDEX_GEN_CLASS_SRC) \
		$(JCK_INDEX_HTTLFILE_SRC)
	echo JCKIndexGenerator precompiled at `date` > $@

#----------------------------------------------------------------------
#
#----------------------------------------------------------------------
#
# Precompile BundleTestGenFilter classes
#
BUNDLE_TEST_GEN_FILTER = com.sun.tgxml.tools.testgen.BundleTestGenFilter

BUNDLE_TEST_GEN_FILTER_SRC.sh = echo $(BUNDLE_TEST_GEN_FILTER) | $(CONVERT_TO_SOURCE)
BUNDLE_TEST_GEN_FILTER_SRC = $(shell $(BUNDLE_TEST_GEN_FILTER_SRC.sh))

precompile-bundle-testgenfilter.ok: $(BUNDLE_TEST_GEN_FILTER_SRC)
	@ $(TEST) -d classes || $(MKDIR) -p classes
	@echo precompile BundleTestGenFilter class
	CLASSPATH=$(TCKDIR)/classes \
	$(GENERAL_JAVAC) -d classes \
		$(BUNDLE_TEST_GEN_FILTER_SRC)
	echo "BundleTestGenFilter precompiled at `date`" > $@

#----------------------------------------------------------------------
#
# Precompile BundleTestGenFilter classes
#
JTI_GEN = com.sun.jaxb_tck.util.JtiGen

JTI_GEN_SRC.sh = echo $(JTI_GEN) | $(CONVERT_TO_SOURCE)
JTI_GEN_SRC = $(shell $(JTI_GEN_SRC.sh))

precompile-jti-gen.ok: $(BUNDLE_TEST_GEN_FILTER_SRC)
	@ $(TEST) -d classes || $(MKDIR) -p classes
	@echo precompile JtiGen class
	CLASSPATH=$(JAVATEST_JAR_LOC)/javatest.jar \
	$(GENERAL_JAVAC) -d classes \
		$(JTI_GEN_SRC)
	echo "JtiGen precompiled at `date`" > $@

