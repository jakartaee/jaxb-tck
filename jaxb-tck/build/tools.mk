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
# Partial Makefile for TCK tools
#----------------------------------------------------------------------
#
# JavaTest

TOOLS_JAVAC=$(PRECOMPILE_JAVAC) -target 1.8

JAVAFILES.com.sun.jaxb_tck.interview = \
	JAXBTCKParameters.java \
	SchemagenInterview.java \
	StandardInterview.java \
	CompileInterview.java

RESOURCES.com.sun.jaxb_tck.interview = \
    tckwiz.properties \
    moreInfo/images/blueListBull.gif \
    moreInfo/images/hg_proc.gif \
    moreInfo/images/hg_note.gif \
    moreInfo/images/nextButtonSmall.gif \
    moreInfo/default/tckwiz.jhm \
    moreInfo/default/Common/prolog.html \
    moreInfo/default/Common/envName.html \
    moreInfo/default/Common/description.html \
    moreInfo/default/Common/sameJVM.html \
    moreInfo/default/Common/local.html \
    moreInfo/default/Common/agentType.html \
    moreInfo/default/Common/agentPassiveHost.html \
    moreInfo/default/Common/useAgentPortDefault.html \
    moreInfo/default/Common/agentPassivePort.html \
    moreInfo/default/Common/fileSeparator.html \
    moreInfo/default/Common/pathSeparator.html \
    moreInfo/default/Common/mapArgs.html \
    moreInfo/default/Common/cmdAsFile.html \
    moreInfo/default/Common/optOther.html \
    moreInfo/default/Common/otherEnvVars.html \
    moreInfo/default/Common/jaxbClasses.html \
    moreInfo/default/Common/needJaxbClasses.html \
    moreInfo/default/Compile/intro.html \
    moreInfo/default/Compile/defaultOperationMode.html \
    moreInfo/default/Compile/xjcCmd.html \
    moreInfo/default/Compile/compilerWrapperClass.html \
    moreInfo/default/Compile/skip.html \
    moreInfo/default/Schemagen/intro.html \
    moreInfo/default/Schemagen/skip.html \
    moreInfo/default/Schemagen/class.html \
    moreInfo/default/Schemagen/jxcCmd.html \
    moreInfo/default/Common/parameters.html \
    moreInfo/default/Common/epilog.html \
    moreInfo/tckwiz.hs
	
com.sun.jaxb_tck.interview.ok: com.sun.jaxb_tck.all.ok
	echo "interview classes build at `date`" > $@

TARGETS.com.sun.jaxb_tck.interview += com.sun.jaxb_tck.interview.ok \
				 $(RESOURCES.com.sun.jaxb_tck.interview:%=$(JTPLUGINCLASSDIR)/com/sun/jaxb_tck/interview/%)
ZIP.files += $(TARGETS.com.sun.jaxb_tck.interview)

#-----------------persistence lib-------------------------------------------

JAVAFILES.com.sun.jaxb_tck.lib.persistence = \
	ObjectsWrapper.java \
	BeansPersistenceDelegate.java \
	Comparator.java \
	JaxbTreeDeserializer.java \
	JaxbTreeSerializer.java \
	TCKJaxbTreeSerializer.java \
	TCKJaxbTreeDeserializer.java \
	PersistenceFactory.java \
	JBContentBuild.java

com.sun.jaxb_tck.lib.persistence.ok: javatest.ok jtlegacy.ok $(JTPLUGINCLASSDIR) \
			    $(JAVAFILES.com.sun.jaxb_tck.lib.persistence:%=$(JTPLUGINSRCDIR)/classes/com/sun/jaxb_tck/lib/persistence/%)
	CLASSPATH=$(JAVATEST_JAR):$(BUILDCLASSDIR):$(JTPLUGINCLASSDIR):$(JAXB_CLASSPATH) \
	$(TOOLS_JAVAC) -d $(JTPLUGINCLASSDIR) \
	$(JAVAFILES.com.sun.jaxb_tck.lib.persistence:%=$(JTPLUGINSRCDIR)/classes/com/sun/jaxb_tck/lib/persistence/%)
	echo "lib classes build at `date`" > $@

ZIP.files += com.sun.jaxb_tck.lib.persistence.ok

com.sun.jaxb_tck.lib.persistence.copy_to_src.ok: precompile-src.ok \
			$(JAVAFILES.com.sun.jaxb_tck.lib.persistence:%=$(JTPLUGINSRCDIR)/classes/com/sun/jaxb_tck/lib/persistence/%)
	$(TEST) -d $(TCKDIR)/src/share/classes/com/sun/jaxb_tck/lib/persistence/ || $(MKDIR) -p $(TCKDIR)/src/share/classes/com/sun/jaxb_tck/lib/persistence/
	$(RM) $(TCKDIR)/src/share/classes/com/sun/jaxb_tck/lib/persistence/*.java
	for i in `echo "$(JAVAFILES.com.sun.jaxb_tck.lib.persistence:%=$(JTPLUGINSRCDIR)/classes/com/sun/jaxb_tck/lib/persistence/%)"` ; do \
		$(CP) $$i $(TCKDIR)/src/share/classes/com/sun/jaxb_tck/lib/persistence/ ; \
	done
	echo "com.sun.jaxb_tck.lib.persistence classes are copied into TCKDIR/src" > $@
	
ZIP.files += com.sun.jaxb_tck.lib.persistence.copy_to_src.ok

#---------------------------main lib----------------------------
JAVAFILES.com.sun.jck.lib = \
	Expr.java \
	
com.sun.jck.lib.ok: javatest.ok $(JTPLUGINCLASSDIR) \
			    $(JAVAFILES.com.sun.jck.lib:%=$(JTPLUGINSRCDIR)/classes/com/sun/jck/lib/%)
	CLASSPATH=$(JAVATEST_JAR):$(JTPLUGINCLASSDIR):$(JAXB_CLASSPATH) \
	$(TOOLS_JAVAC) -d $(JTPLUGINCLASSDIR) \
	$(JAVAFILES.com.sun.jck.lib:%=$(JTPLUGINSRCDIR)/classes/com/sun/jck/lib/%)
	echo "lib classes build at `date`" > $@
ZIP.files += com.sun.jck.lib.ok

JAVAFILES.com.sun.jck.utils.jtxedit = Main.java

precompile-jckutils-jtxedit-class.ok: javatest.ok $(BUILDCLASSDIR) \
				$(JAVAFILES.com.sun.jck.utils.jtxedit:%=$(TOPDIR)/src/share/classes/com/sun/jck/utils/jtxedit/%)
	@echo precompile jckutils jtxedit classes
	CLASSPATH=$(JAVATEST_JAR):$(BUILDCLASSDIR):$(JAXB_CLASSPATH) \
        $(TOOLS_JAVAC) -d $(BUILDCLASSDIR) \
        $(JAVAFILES.com.sun.jck.utils.jtxedit:%=$(TOPDIR)/src/share/classes/com/sun/jck/utils/jtxedit/%)
	echo "jckutils jtxedit class precompiled at `date`" > $@
ZIP.files += precompile-jckutils-jtxedit-class.ok

JAVAFILES.com.sun.jck.utils.installer = \
		Installer.java \
		MegaZipFile.java \
		Node.java \
		Root.java \
		Zipper.java \

precompile-jckutils-installer-class.ok: javatest.ok $(BUILDCLASSDIR) \
                                $(JAVAFILES.com.sun.jck.utils.installer:%=$(TOPDIR)/src/share/classes/com/sun/jck/utils/installer/%)
	@echo precompile jckutils installer classes
	CLASSPATH=$(JAVATEST_JAR):$(BUILDCLASSDIR):$(JAXB_CLASSPATH) \
	$(TOOLS_JAVAC) -d $(BUILDCLASSDIR) \
	$(JAVAFILES.com.sun.jck.utils.installer:%=$(TOPDIR)/src/share/classes/com/sun/jck/utils/installer/%)
	echo "jckutils installer class precompiled at `date`" > $@
ZIP.files += precompile-jckutils-installer-class.ok

JAVAFILES.com.sun.jck.utils.jckfilecheck = \
		Checker.java \
		ClusterChecker.java \
		Comparable.java \
		DirectoryEntry.java \
		FileSystemParser.java \
		HTMLReportGenerator.java \
		ISOChecker.java \
		ISODataBase.java \
		LongChecker.java \
		Main.java \
		OrderedList.java \
		ShortChecker.java \
		TestOrderedList.java \
		ZipListParser.java \

precompile-jckutils-jckfilecheck-class.ok: javatest.ok $(BUILDCLASSDIR) precompile-javasoft-sqe-harness-class.ok  \
				$(JAVAFILES.com.sun.jck.utils.jckfilecheck:%=$(TOPDIR)/src/share/classes/com/sun/jck/utils/jckfilecheck/%)
	@echo precompile jckutils jckfilecheck classes
	CLASSPATH=$(JAVATEST_JAR):$(BUILDCLASSDIR):$(JAXB_CLASSPATH) \
	$(TOOLS_JAVAC) -d $(BUILDCLASSDIR) \
	$(JAVAFILES.com.sun.jck.utils.jckfilecheck:%=$(TOPDIR)/src/share/classes/com/sun/jck/utils/jckfilecheck/%)
	echo "jckutils jckfilecheck class precompiled at `date`" > $@
ZIP.files += precompile-jckutils-jckfilecheck-class.ok

JAVAFILES.javasoft.sqe.harness = \
		Test.java \
		Status.java \

precompile-javasoft-sqe-harness-class.ok: $(BUILDCLASSDIR) \
				$(JAVAFILES.javasoft.sqe.harness:%=$(TOPDIR)/src/share/classes/javasoft/sqe/harness/%)
	@echo precompile javasoft sqe harness classes
	CLASSPATH=$(BUILDCLASSDIR) \
	$(TOOLS_JAVAC) -d $(BUILDCLASSDIR) \
	$(JAVAFILES.javasoft.sqe.harness:%=$(TOPDIR)/src/share/classes/javasoft/sqe/harness/%)
	echo "javasoft sqe harness classes precompiled at `date`" > $@
ZIP.files += precompile-javasoft-sqe-harness-class.ok

JAVAFILES.javasoft.sqe.javatest = \
		Status.java \
		Test.java \

precompile-javasoft-sqe-javatest.ok: javatest.ok $(BUILDCLASSDIR) \
			$(JAVAFILES.javasoft.sqe.javatest:%=$(TOPDIR)/src/share/classes/javasoft/sqe/javatest/%)
	@echo precompile javasoft sqe javatest classes
	CLASSPATH=$(JAVATEST_JAR):$(BUILDCLASSDIR) \
	$(TOOLS_JAVAC) -d $(BUILDCLASSDIR) \
	$(JAVAFILES.javasoft.sqe.javatest:%=$(TOPDIR)/src/share/classes/javasoft/sqe/javatest/%)
	echo "javasoft sqe javatest classes precompiled at `date`" > $@
ZIP.files += precompile-javasoft-sqe-javatest.ok

JAVAFILES.javasoft.sqe.javatest.lib = \
		StdTestScript.java \
		SlaveCommand.java \
		SimpTestScript.java \
		RmicTestScript.java \
		ProcessCommand.java \
		NotApplicTestScript.java \
		MultiTest.java \
		JavaCompileCommand.java \
		ExecStdTestSameJVMCmd.java \
		ExecStdTestOtherJVMCmd.java \
		EchoCommand.java \
		APIScript.java \
		UmbrellaTest.java \
		TestCases.java \
		SerializeTest.java \
		ReportScript.java \
		NegExecWrapper.java \
		MultiStatus.java \
		KeywordScript.java \
		InteractiveTest.java \
		ExecStdTestOtherJVMCmd.java \
		ExecSimpTestOtherJVMCmd.java \
		Deprecated.java \

precompile-javasoft-sqe-javatest-lib.ok: javatest.ok $(BUILDCLASSDIR) precompile-javasoft-sqe-javatest.ok \
				$(JAVAFILES.javasoft.sqe.javatest.lib:%=$(TOPDIR)/src/share/classes/javasoft/sqe/javatest/lib/%)
	@echo precompile javasoft sqe javatest lib classes
	CLASSPATH=$(JAVATEST_JAR):$(BUILDCLASSDIR) \
	$(TOOLS_JAVAC) -d $(BUILDCLASSDIR) \
	$(JAVAFILES.javasoft.sqe.javatest.lib:%=$(TOPDIR)/src/share/classes/javasoft/sqe/javatest/lib/%)
	echo "javasoft sqe javatest lib classes precompiled at `date`" > $@
ZIP.files += precompile-javasoft-sqe-javatest-lib.ok

JAVAFILES.javasoft.sqe.javatest.lib.apitest = \
		SummaryReporter.java \
		SingletonGenerator.java \
		Reporter.java \
		PrintReporter.java \
		ImmutableObjectFactory.java \
		GridGenerator.java \
		Generator.java \
		Factory.java \
		AssertionTest.java \

precompile-javasoft-sqe-javatest-lib-apitest.ok: javatest.ok $(BUILDCLASSDIR) precompile-javasoft-sqe-javatest-lib.ok \
				$(JAVAFILES.javasoft.sqe.javatest.lib.apitest:%=$(TOPDIR)/src/share/classes/javasoft/sqe/javatest/lib/apitest/%)
	@echo precompile javasoft sqe javatest lib apitest classes
	CLASSPATH=$(JAVATEST_JAR):$(BUILDCLASSDIR) \
	$(TOOLS_JAVAC) -d $(BUILDCLASSDIR) \
	$(JAVAFILES.javasoft.sqe.javatest.lib.apitest:%=$(TOPDIR)/src/share/classes/javasoft/sqe/javatest/lib/apitest/%)
	echo "javasoft sqe javatest lib apitest classes precompiled at `date`" > $@
ZIP.files += precompile-javasoft-sqe-javatest-lib-apitest.ok

precompile-tckbtools-lib-classes.ok:
	@echo precompile tckbtools lib classes
	CLASSPATH=$(JAVATEST_JAR):$(BUILDCLASSDIR) \
	$(TOOLS_JAVAC) -d $(BUILDCLASSDIR) $(TOPDIR)/src/share/classes/javasoft/sqe/jckutils/lib/JmppScript.java
	find $(TOPDIR)/src/share/classes/com/sun/jmpp -name "*.java" > source_files.txt
	find $(TOPDIR)/src/share/classes/com/sun/tgxml -name "*.java" >> source_files.txt
	CLASSPATH=$(JAVATEST_JAR):$(BUILDCLASSDIR) \
	$(TOOLS_JAVAC) -d $(BUILDCLASSDIR) @source_files.txt
	echo "tckbtools lib classes precompiled at `date`" > $@
ZIP.files += precompile-tckbtools-lib-classes.ok

precompile-apache-river-jini.ok:
	@echo precompile apache river jini classes
	find $(TOPDIR)/src/share/classes/org/apache/river -name "*.java" > river_source_files.txt
	find $(TOPDIR)/src/share/classes/net/jini -name "*.java" >> river_source_files.txt
	CLASSPATH=$(GENERAL_JAVAHOME)/lib/tools.jar:$(ASM_JAR_LOCATION)/asm-7.0.jar:$(ASM_JAR_LOCATION)/asm-commons-7.0.jar \
	$(TOOLS_JAVAC) -source 6 -d $(BUILDCLASSDIR) @river_source_files.txt
	$(MKDIR) -p $(TCKDIR)/classes/org/apache/river/tool/resources
	$(MKDIR) -p $(TCKDIR)/classes/org/apache/river/impl
	$(CP) $(TOPDIR)/src/share/classes/org/apache/river/tool/resources/classdep.properties $(TCKDIR)/classes/org/apache/river/tool/resources/
	$(CP) $(TOPDIR)/src/share/classes/org/apache/river/impl/messages.properties $(TCKDIR)/classes/org/apache/river/impl/
	echo "apache river classes precompiled at `date`" > $@
ZIP.files += precompile-apache-river-jini.ok

JAVAFILES.com.sun.jaxb_tck.lib = \
		JaxbTckScript.java \
		JaxbTckTestSuite.java \
		DirsClassLoader.java \
		ExecTestSameJVMCmd.java \
		Xjc.java \
		Counter.java \
		Arguments.java \
		CompositeInvoker.java \
		Invoker.java \
		Jxc.java \
		PrecompileJaxbTckScript.java \
		JaxbCommand.java \
		SchemaGenInvoker.java \
		SchemaGenTool.java \
		SchemaGen.java \
		JaxbTestFilter.java \
		SigtestSetupRun.java \
		ToolsJarClassLoader.java \
		JavacInvoker.java \
		SchemaCompiler.java \
		SchemaCompilerInvoker.java \
		SchemaCompilerTool.java \

com.sun.jaxb_tck.lib.ok: com.sun.jaxb_tck.all.ok
	echo "lib classes build at `date`" > $@
ZIP.files += com.sun.jaxb_tck.lib.ok

com.sun.jaxb_tck.all.ok: javatest.ok jtlegacy.ok $(JTPLUGINCLASSDIR) \
			    $(JAVAFILES.com.sun.jaxb_tck.interview:%=$(JTPLUGINSRCDIR)/classes/com/sun/jaxb_tck/interview/%) \
	                    $(JAVAFILES.com.sun.jaxb_tck.lib:%=$(JTPLUGINSRCDIR)/classes/com/sun/jaxb_tck/lib/%) \
	                    com.sun.jck.lib.ok precompile-jckutils-jtxedit-class.ok precompile-jckutils-installer-class.ok precompile-jckutils-jckfilecheck-class.ok precompile-javasoft-sqe-javatest-lib-apitest.ok precompile-tckbtools-lib-classes.ok precompile-apache-river-jini.ok
	CLASSPATH=$(JAVATEST_JAR):$(BUILDCLASSDIR):$(JTPLUGINCLASSDIR):$(JAXB_CLASSPATH) \
	$(TOOLS_JAVAC) -d $(JTPLUGINCLASSDIR) \
	$(JAVAFILES.com.sun.jaxb_tck.interview:%=$(JTPLUGINSRCDIR)/classes/com/sun/jaxb_tck/interview/%) \
	$(JAVAFILES.com.sun.jaxb_tck.lib:%=$(JTPLUGINSRCDIR)/classes/com/sun/jaxb_tck/lib/%)
	echo "interview and lib classes built at `date`" > $@
ZIP.files += com.sun.jaxb_tck.all.ok

com.sun.jaxb_tck.lib.copy_to_src.ok: precompile-src.ok \
			$(JAVAFILES.com.sun.jaxb_tck.lib:%=$(JTPLUGINSRCDIR)/classes/com/sun/jaxb_tck/lib/%)
	$(TEST) -d $(TCKDIR)/src/share/classes/com/sun/jaxb_tck/lib/ || $(MKDIR) -p $(TCKDIR)/src/share/classes/com/sun/jaxb_tck/lib/
	$(RM) $(TCKDIR)/src/share/classes/com/sun/jaxb_tck/lib/*.java
	for i in `echo "$(JAVAFILES.com.sun.jaxb_tck.lib:%=$(JTPLUGINSRCDIR)/classes/com/sun/jaxb_tck/lib/%)"` ; do \
		$(CP) $$i $(TCKDIR)/src/share/classes/com/sun/jaxb_tck/lib/ ; \
	done
	echo "com.sun.jaxb_tck.lib classes are copied into TCKDIR/src" > $@

ZIP.files += com.sun.jaxb_tck.lib.copy_to_src.ok
#----------------------------------------------------------------------
#
# copy property files
#
$(JTPLUGINCLASSDIR)/com/sun/jaxb_tck/%: $(JTPLUGINSRCDIR)/classes/com/sun/jaxb_tck/%
	$(RM) $@
	$(MKDIR) -p $(@D)
	echo "TCKDIR=$(TCKDIR)"
	echo "BUILDCLASSDIR=$(BUILDCLASSDIR)"
	echo "JTPLUGINCLASSDIR=$(JTPLUGINCLASSDIR)"
	echo "TOPDIR=$(TOPDIR)"
	echo "JTPLUGINSRCDIR=$(JTPLUGINSRCDIR)"
	echo "target=" $@
	echo $(CP) $(@:$(JTPLUGINCLASSDIR)/com/sun/jaxb_tck/%=$(JTPLUGINSRCDIR)/classes/com/sun/jaxb_tck/%) $@
	$(CP) $(@:$(JTPLUGINCLASSDIR)/com/sun/jaxb_tck/%=$(JTPLUGINSRCDIR)/classes/com/sun/jaxb_tck/%) $@

# don't try and unzip anything else while unzipping javatest
.NO_PARALLEL: javatest.ok

javatest.ok: unpack_sigtest_jar.ok
	cd $(TCKDIR); $(MKDIR) lib; cd lib;  $(CP) $(JAVATEST_JAR_LOC)/javatest.jar .
	echo "$(RELDIR) built at " `date` > $@

ZIP.files += javatest.ok

jtlegacy.ok: 
	echo "legacy files extracted on `date`" > $@

ZIP.files += jtlegacy.ok

#----------------------------------------------------------------------
$(TCKDIR)/solaris/bin:
	$(TEST) -d $(TCKDIR)/solaris/bin || $(MKDIR) -p $(TCKDIR)/solaris/bin

$(TCKDIR)/linux/bin:
	$(TEST) -d $(TCKDIR)/linux/bin || $(MKDIR) -p $(TCKDIR)/linux/bin

$(TCKDIR)/macos/bin:
	$(TEST) -d $(TCKDIR)/macos/bin || $(MKDIR) -p $(TCKDIR)/macos/bin

$(TCKDIR)/win32/bin:
	$(TEST) -d $(TCKDIR)/win32/bin || $(MKDIR) -p $(TCKDIR)/win32/bin

gettest.ok: javatest.ok $(TCKDIR)/solaris/bin
	$(RM) $(TCKDIR)/solaris/bin/gettest
	$(CP) $(TOPDIR)/src/share/bin/gettest $(TCKDIR)/solaris/bin
	$(CHMOD) +x $(TCKDIR)/solaris/bin/gettest
	echo "gettest is copied at " `date` > $@

#ZIP.files += gettest.ok

xjc.sh.ok: javatest.ok $(TCKDIR)/solaris/bin $(TCKDIR)/linux/bin $(TCKDIR)/macos/bin
	$(RM) $(TCKDIR)/solaris/bin/xjc*.sh ; $(RM) $(TCKDIR)/linux/bin/xjc*.sh ; $(RM) $(TCKDIR)/macos/xjc*.sh
	$(CP) $(TOPDIR)/src/share/bin/xjc*.sh $(TCKDIR)/solaris/bin
	$(CP) $(TOPDIR)/src/share/bin/xjc*.sh $(TCKDIR)/linux/bin
	$(CP) $(TOPDIR)/src/share/bin/xjc*.sh $(TCKDIR)/macos/bin
	$(CP) $(TOPDIR)/src/share/bin/javatest $(TCKDIR)/macos/bin
	$(CHMOD) +x $(TCKDIR)/solaris/bin/xjc*.sh
	$(CHMOD) +x $(TCKDIR)/linux/bin/xjc*.sh
	$(CHMOD) +x $(TCKDIR)/macos/bin/xjc*.sh
	echo "xjc.sh is copied at " `date` > $@

ZIP.files += xjc.sh.ok

compareER.pl.ok: javatest.ok $(TCKDIR)/solaris/bin
	$(RM) $(TCKDIR)/solaris/bin/compareER.pl
	$(CP) $(TOPDIR)/src/share/bin/compareER.pl $(TCKDIR)/solaris/bin
	$(CHMOD) +x $(TCKDIR)/solaris/bin/compareER.pl
	echo "compareER.pl is copied at " `date` > $@

#ZIP.files += compareER.pl.ok

#----------------------------------------------------------------------

xjc.bat.ok: javatest.ok $(TCKDIR)/win32/bin
	$(RM) $(TCKDIR)/win32/bin/xjc*.bat
	$(CP) $(TOPDIR)/src/share/bin/xjc*.bat $(TCKDIR)/win32/bin/
	echo "xjc.bat is copied at " `date` > $@

ZIP.files += xjc.bat.ok

#----------------------------------------------------------------------
#----------------------------------------------------------------------

schemagen.bat.ok: javatest.ok $(TCKDIR)/win32/bin
	$(RM) $(TCKDIR)/win32/bin/schemagen*.bat
	$(CP) $(TOPDIR)/src/share/bin/schemagen*.bat $(TCKDIR)/win32/bin/
	echo "schemagen.bat is copied at " `date` > $@

ZIP.files += schemagen.bat.ok

schemagen.sh.ok: javatest.ok $(TCKDIR)/solaris/bin
	$(RM) $(TCKDIR)/solaris/bin/schemagen*.sh $(TCKDIR)/linux/bin/schemagen*.sh $(TCKDIR)/macos/bin/schemagen*.sh
	$(CP) $(TOPDIR)/src/share/bin/schemagen*.sh $(TCKDIR)/solaris/bin/
	$(CP) $(TOPDIR)/src/share/bin/schemagen*.sh $(TCKDIR)/linux/bin/
	$(CP) $(TOPDIR)/src/share/bin/schemagen*.sh $(TCKDIR)/macos/bin/
	$(CHMOD) +x $(TCKDIR)/solaris/bin/schemagen*.sh $(TCKDIR)/linux/bin/schemagen*.sh $(TCKDIR)/macos/bin/schemagen*.sh
	echo "schemagen.sh is copied at " `date` > $@

ZIP.files += schemagen.sh.ok

#----------------------------------------------------------------------
#-----------------------------------------------------------------------
#

