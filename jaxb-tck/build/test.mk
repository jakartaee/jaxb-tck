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

# Partial Makefile for TCK tests
#----------------------------------------------------------------------

$(TCKDIR)/tests/testsuite.jtd: javatest.ok html.ok
	$(GENERAL_JAVA) -cp $(JAVATEST_JAR) com.sun.javatest.finder.BinaryTestWriter -finder com.sun.javatest.finder.HTMLTestFinder -dirWalk -end $(TCKDIR)/tests/


SIGTEST_UNPACKED_DIR = sigtest_unpacked

unpack_sigtest_jar.ok: $(TCKDIR)/classes $(SHARED_ENV_MK)
	JAVA_HOME=$(PRECOMPILE_JAVAHOME) $(ANT_HOME)/bin/ant -f ../sigtest.ant.xml dist-classes

#unpack_sigtest_src.ok: precompile-src.ok unpack_sigtest_jar.ok
	#JAVA_HOME=$(PRECOMPILE_JAVAHOME) $(ANT_HOME)/bin/ant -f ../sigtest.ant.xml dist-src

#UNPACK_SIGTEST = unpack_sigtest_jar.ok unpack_sigtest_src.ok
UNPACK_SIGTEST = unpack_sigtest_jar.ok

ZIP.files += $(UNPACK_SIGTEST)

#----------------------------------------------------------------------

$(PRECOMPILEDIR)/classes:
	$(MKDIR) -p $@

unpack_sigtestdev_jar.ok: $(PRECOMPILEDIR)/classes
	(cd $(PRECOMPILEDIR)/classes ; $(PRECOMPILE_JAR) -xf $(SIGTESTDEV_JAR_LOC)/sigtestdev.jar ; $(RM) META-INF )
	echo "sigtestdev.jar is unpacked at `date`" > $@

SIGTEST_SETUP_RUN_SRC=$(TOPDIR)/src/share/jttck/classes/com/sun/jaxb_tck/lib/SigtestSetupRun.java

precompile_sigtest_setup_run.ok: $(PRECOMPILEDIR)/classes unpack_sigtestdev_jar.ok $(SIGTEST_SETUP_RUN_SRC) javatest.ok jtlegacy.ok
	CLASSPATH=$(PRECOMPILEDIR)/classes:$(TCKDIR)/classes:$(JAVATEST_JAR) $(PRECOMPILE_JAVAC) -d $(PRECOMPILEDIR)/classes $(SIGTEST_SETUP_RUN_SRC)
	echo "SigtestSetupRun.java is precompiled at `date`" > $@

#----------------------------------------------------------------------

precompile_testsuite_jtt.ok: $(TCKDIR)
	@ $(RM) $(TCKDIR)/testsuite.jtt
	echo "name=JAXB TCK testsuite" > $(TCKDIR)/testsuite.jtt
	echo "id=jaxb_tck22" >> $(TCKDIR)/testsuite.jtt
	echo "classpath=./classes/:$(PRECOMPILEDIR)/classes" >> $(TCKDIR)/testsuite.jtt
	echo "finder=com.sun.javatest.finder.BinaryTestFinder -binary $(TCKDIR)/tests/testsuite.jtd" >> $(TCKDIR)/testsuite.jtt
	echo "script=com.sun.jaxb_tck.lib.PrecompileJaxbTckScript" >> $(TCKDIR)/testsuite.jtt
	echo "testsuite.jtt for precompile built at `date`" > $@

TEST_EXECUTE_SCRIPT_SRC=$(TOPDIR)/src/share/jttck/classes/com/sun/jaxb_tck/lib/PrecompileJaxbTckScript.java

precompile_test_execute_script.ok: $(PRECOMPILEDIR)/classes com.sun.jaxb_tck.lib.ok com.sun.jaxb_tck.lib.persistence.ok
	CLASSPATH=$(PRECOMPILEDIR)/classes:$(JAVATEST_JAR):$(TCKDIR)/classes $(PRECOMPILE_JAVAC) -d $(PRECOMPILEDIR)/classes $(TEST_EXECUTE_SCRIPT_SRC)
	echo "PrecompileJaxbTckScript.java is precompiled at `date`" > $@

#----------------------------------------------------------------------
$(TCKDIR)/lib:
	$(TEST) -d $@ || $(MKDIR) -p $@

JAVATEST_CONCURRENCY.sh = \
	if [ ! "`echo $@ | $(GREP) 'singleJVM'`" != "" ] ; then \
		echo $(JT_MULTIVM_CONCURRENCY) ; \
	else \
		echo $(JT_SINGLEVM_CONCURRENCY) ; \
	fi

$(TCKDIR)/lib/javasoft-singleJVM.jti: precompile-jti-gen.ok $(@D)
	@ $(RM) $@
	CLASSPATH=classes:$(JAVATEST_JAR_LOC)/javatest.jar \
	$(GENERAL_JAVA) com.sun.jaxb_tck.util.JtiGen -single \
	-work $(BUILDAREA)/test/$(TCKVERSION)/batch-singleJVM/work \
	-testsuite $(UNZIPDIR)/$(TCKVERSION) \
	-ri_java_home $(PRECOMPILE_JAVAHOME) \
	-tests $(INITIALURLS) > $@
	@echo "**** javasoft-singleJVM.jti created at `date` ****"
	
$(TCKDIR)/lib/javasoft-multiJVM.jti: precompile-jti-gen.ok $(@D)
	@ $(RM) $@
	CLASSPATH=classes:$(JAVATEST_JAR_LOC)/javatest.jar \
	$(GENERAL_JAVA) com.sun.jaxb_tck.util.JtiGen -multi \
	-work $(BUILDAREA)/test/$(TCKVERSION)/batch-multiJVM/work \
	-testsuite $(UNZIPDIR)/$(TCKVERSION) \
	-tests $(INITIALURLS) \
	-ri_java_home $(PRECOMPILE_JAVAHOME) \
	-xsd_compiler "/bin/ksh solaris/bin/xjc.sh" \
	-schemagen "/bin/ksh solaris/bin/schemagen.sh" \
	-jaxb `echo $(JAXB_CLASSPATH) | sed 's/:/ /g'` \
	-otherEnv JAVA_HOME=$(PRECOMPILE_JAVAHOME) `if [ -z "$(JCK_JAXB)" ]; then echo "JAXB_HOME=$(JAXB_HOME)"; fi` > $@
	@echo "**** javasoft-multiJVM.jti created at `date` ****"

ZIP.files += $(TCKDIR)/lib/javasoft-multiJVM.jti $(TCKDIR)/lib/javasoft-singleJVM.jti

#----------------------------------------------------------------------
# 
# precompile the tests as necessary
# precompile-XXX.ok is written when TESTDIR/XXX tests have been successfully compiled
#
# carefully adjust INITIALURLS as necessary
# - if INITIALURLS is null or contains appropriate {api,lang,vm}/index.html  then
#	replace it by URL derived from SELECTED_DIR implicit in target
# and rely on caller to provide coverage across all SELECTED_DIRS;
# otherwise restrict it to URLs below XXX
#
# This part of the macro needs to be changed for dirWalk
# 	  echo $(TESTDIR)/$(@:precompile-%.ok=%)/index.html ; \
# Becomes
#	  echo $(TESTDIR)/$(@:precompile-%.ok=%) ; \
#

PRECOMPILE_INITIALURLS.sh = \
	testdir=`$(DIRNAME) $(@:precompile-%.ok=%)`; \
	while [ "`$(DIRNAME) $$testdir`" != "." ] ; do \
		testdir=`$(DIRNAME) $$testdir` ; \
	done ; \
	files="$(TESTSUITEFILES)"; \
	if echo $$files | $(GREP) $$testdir > /dev/null ; then \
	  echo $(@:precompile-%.ok=%) ; \
	else \
	  for f in $$files ; do echo $$f ; done | $(GREP) '^$(@:precompile-%.ok=%)' | $(CAT) ; \
	fi

$(PRECOMPILEDIR)/work/%:
	$(MKDIR) -p $@

PRECOMPILE_SUITESUBDIR.sh = $(DIRNAME) `echo $@`
PRECOMPILE_SUITESUBDIR=$(shell $(PRECOMPILE_SUITESUBDIR.sh))
PRECOMPILE_DIRS=$(DIRS:%=precompile-%.ok)
PRECOMPILE_WORK=$(@D:precompile-%=$(PRECOMPILEDIR)/work/%)
.SECONDEXPANSION:
$(PRECOMPILE_DIRS): html.ok $(BUILDDIR)/precompile.sh \
			$(TCKDIR)/tests/testsuite.jtd \
			$$(PRECOMPILE_WORK) \
			com.sun.jaxb_tck.lib.ok \
			com.sun.jaxb_tck.lib.persistence.ok \
			precompile_sigtest_setup_run.ok \
			precompile_test_execute_script.ok \
			precompile_testsuite_jtt.ok
	$(MKDIR) -p $(@D);
	if [ -d $(TCKDIR)/tests/$(@:precompile-%.ok=%) ] ; then \
		$(KSH) $(BUILDDIR)/precompile.sh \
			$(TCKDIR) \
			$(PRECOMPILEDIR) \
			$(@:precompile-%.ok=%) \
			"$(shell $(PRECOMPILE_INITIALURLS.sh))" \
			$(INTERNAL_JCTTOOLS) \
			$(PRECOMPILE_JAVAHOME) \
			$(JAXB_HOME); \
	fi
	echo ":-) $(@:precompile-%.ok=%) files precompiled at `date`" | $(TEE) $@

precompile-%:
	$(MKDIR) -p $@

CLEANFILES += $(PRECOMPILEDIR)

ZIP.files += $(SELECTED_DIRS:%=precompile-%.ok)

#----------------------------------------------------------------------
# 
# EXECUTE THE TESTS
#
#----------------------------------------------------------------------

# The following shell macro definitions are used within the test-%.ok rule coming up,
# so $@ always refers to the test-%.ok target.  The definitions are used to translate
# properties encoded in the test name to the various possible options for run-tck.sh.
# Using shell mecros like this, as compared to ` ` in the make file itself, leads
# IMHO to typographic cleanliness, and more importantly, more informative output 
# from make when the test-%.ok is echoed (since at that point these shell macros
# will have already been evalauated).

AGENT_OPTION.sh = case $@ in \
	*-singleJVM* ) echo '-agent' ;; \
	esac

KEYWORDS_OPTION.sh = case $@ in \
	*runtime-batch* ) echo 'runtime&!interactive' ;; \
	esac

# exercise TCK according to the parameters encoded in the test-%.ok target name
# This rule needs to be changed for dirWalk
#		-testsuite tests/testsuite.html \
# Becomes
#		-testsuite tests \
#

test-%.ok: $(BUILDDIR)/run-tck.sh unzipped.ok 
	$(MKDIR) -p $(BUILDAREA)/test/$(TCKVERSION)/$(@:test-%.ok=%/work)
	@echo 
	@echo "Executing the $(@:test-%.ok=%) tests ..."
	@echo "This may take a while. To monitor the progress, execute the command"
	@echo "	tail -f `cd $(UNZIPDIR)/../test/$(TCKVERSION)/$(@:test-%.ok=%/work); pwd`reports/harness.trace"
	@echo in a separate shell
	@echo
	$(KSH) $(BUILDDIR)/run-tck.sh \
	    -tck $(UNZIPDIR)/$(TCKVERSION) \
	    -javaHome $(PRECOMPILE_JAVAHOME) \
	    -jaxbHome $(JAXB_HOME) \
	    $(shell $(AGENT_OPTION.sh)) \
	    -workdir ../../test/$(TCKVERSION)/$(@:test-%.ok=%)/work \
	    -concurrency $(shell $(JAVATEST_CONCURRENCY.sh)) \
	    -tests  $(INITIALURLS)
	echo ":-) $(@:test-%.ok=%) tested at `date`" | $(TEE) $@

#----------------------------------------------------------------------
# 
# Which tests to execute?

# if INITIALURLS is set, select PARTIAL_TESTS.runtime, otherwise select SYSTEM_TESTS.runtime
SELECTED_TESTS.runtime.sh = \
	if [ -z "$(INITIALURLS)" ]; then \
	  echo $(SYSTEM_TESTS.runtime) ; \
	else \
	  echo $(PARTIAL_TESTS.runtime) ; \
	fi
TESTS.runtime = $(shell $(SELECTED_TESTS.runtime.sh))

TESTS += $(TESTS.runtime)


#----------------------------------------------------------------------
# 
# generate statistics for the TCK

stats/index.html: tck-zip.lst
	$(MKDIR) -p stats
	CLASSPATH=$(TCKDIR)/classes:$(JAVATEST_JAR) \
	$(GENERAL_JAVA) -mx256m com.sun.jck.utils.jckfilecheck.Main \
		-d $(UNZIPDIR)/$(TCKVERSION) -o stats/index.html -a -f -iso \
		-cbegin 4096 8192 16384 32768 -cend -extns COPYRIGHT README \
		au aif c class dat dtd gif gm gz h jasm java jcod jtp jtr jts jtx \
		html mid pdf rmf ser sh sig txt wav zip -end
	@echo "+++ TCK statistics: $@" 

# TESTS += stats/index.html

CLEANFILES += stats

#----------------------------------------------------------------------
# 
# check for ez-to-find bad code in tests

# special exception for test expr45402; would be nice to have a better way
# to handle such exceptions
System_checks.ok: unzipped.ok
	$(FIND) $(TCKDIR)/tests -name \*.java -print | \
		xargs -n 1 $(EGREP) '^[ 	]*(System\.out\.print|System\.err\.print|System\.printStackTrace\(\))' /dev/null \
		> $(@:%.ok=%.lst) || true
	if [ -s $(@:%.ok=%.lst) ]; then \
	    echo "Error: bad references to System calls" ; \
	    echo "+++ for more details, see: $(@:%.ok=%.lst)" ; \
	fi
	echo ":-) $@ checked at `date`" | $(TEE) $@

TESTS += System_checks.ok

#----------------------------------------------------------------------
# 
# test list

# when jcktestlist.Main is upgraded to generate a log,
# change tests.lst to tests.lst + testDescriptionErrors.log

# include -$(PRODUCT_BASE) when supported in JavaTest (?)
tests.lst + testDescriptionErrors.log: unzipped.ok
	@echo "+++ test list for $(PRODUCT): $@"
	CLASSPATH=$(JAVATEST_JAR_LOC)/javatest.jar \
	    $(GENERAL_JAVA) com.sun.jck.utils.jcktestlist.Main \
		-dirWalk  \
		-log testDescriptionErrors.log $(TCKDIR)/tests > tests.lst


CLEANFILES += tests.lst testDescriptionErrors.log

testDescriptions.ok: testDescriptionErrors.log
	@echo "test description errors: testDescriptionErrors.log"
	$(TEST) ! -s testDescriptionErrors.log
	echo ":-) test descriptions checked at `date`" | $(TEE) $@

# TESTS += testDescriptions.ok

#--------------------------------------------------------------------------------
# compareER.pl script run.

COMPARE_ER.sh = \
	echo $(TESTS.runtime) | $(SED) 's|test-|compareER-|g'
COMPARE_ER = $(shell $(COMPARE_ER.sh))

compareER-%.ok: test-%.ok
	outputDir=$(UNZIPDIR)/$(TCKVERSION)/../../test/compareER/$(@:compareER-%.ok=%) ; \
	$(TEST) -d $$outputDir || $(MKDIR) -p $$outputDir ; \
	$(PERL) $(UNZIPDIR)/$(TCKVERSION)/solaris/bin/compareER.pl $(UNZIPDIR)/$(TCKVERSION)/lib/jaxb_tck22.jtx \
	$(UNZIPDIR)/$(TCKVERSION)/../../test/$(TCKVERSION)/$(@:compareER-%.ok=%)/work/report $$outputDir ; \
	echo "compareER.pl for $(@:compareER-%.ok=test-%) test run executed, result is in $$outputDir" > $@

#TESTS += $(COMPARE_ER)

