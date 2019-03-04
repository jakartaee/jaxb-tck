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

# Partial Makefile for TCK docs
#----------------------------------------------------------------------
#
# Release Note, Copyright, and index.html


ZIP.files += build-docs
build-docs:
	JAVA_HOME=$(PRECOMPILE_JAVAHOME) ../run-ant-script doc.ant.xml build-docs

#$(TCKDIR)/releaseNote.% : $(@:$(TCKDIR)/releaseNote.%=$(DOC_RELEASE_NOTE).%)
#	@ $(RM) $@
#	$(SED) -e 's|\%BUILD_ID%|'"`$(DATE) +'%d-%b-%Y'`"', build $(BUILD_ID)|' \
#	$(@:$(TCKDIR)/releaseNote.%=$(DOC_RELEASE_NOTE).%) > $@ ;
#
#BUILDRELEASENOTES.sh = \
#if [ -z "$(EXCLUDEDOCS)" ]; then \
#	echo $(TCKDIR)/releaseNote.txt $(TCKDIR)/releaseNote.html; \
#fi
#
#ZIP.files += $(BUILDRELEASENOTES.sh:sh)
#
#DOC_COPYRIGHT_HTML_FILE.sh = \
#	$(BASENAME) $(DOC_COPYRIGHT_HTML)
#DOC_COPYRIGHT_HTML_FILE = $(DOC_COPYRIGHT_HTML_FILE.sh:sh)
#$(TCKDIR)/$(DOC_COPYRIGHT_HTML_FILE) : $(DOC_COPYRIGHT_HTML)
#	@ $(RM) $@
#	$(CP) $(DOC_COPYRIGHT_HTML) $@
#
#ZIP.files += $(TCKDIR)/$(DOC_COPYRIGHT_HTML_FILE)
#
#$(TCKDIR)/Readme.% : $(@:$(TCKDIR)/Readme.%=$(DOC_README).%)
#	$(RM) $@
#	$(SED) -e 's|\%BUILD_ID%|'"`$(DATE) +'%d-%b-%Y'`"', build $(BUILD_ID)|' \
#	$(@:$(TCKDIR)/Readme.%=$(DOC_README).%) > $@
#
#BUILDREADME.sh = \
#if [ -z "$(EXCLUDEDOCS)" ]; then \
#    echo $(TCKDIR)/Readme.txt $(TCKDIR)/Readme.html; \
#fi
#
#ZIP.files += $(BUILDREADME.sh:sh)
#
#DOC_INDEX_HTML.sh = \
#if [ -z "$(JCK_JAXB)" ]; then \
#    echo $(DOC_INDEX_HTML); \
#else \
#	echo $(TOPDIR)/src/share/doc/index-jck.html; \
#fi
#DOC_INDEX_HTML2=$(DOC_INDEX_HTML.sh:sh)
#$(TCKDIR)/copyright.txt: $(DOC_COPYRIGHT_TXT)
#	@ $(RM) $@
#	$(CP) $(DOC_COPYRIGHT_TXT) $@
#$(TCKDIR)/index.html: $(DOC_INDEX_HTML2)
#	@ $(RM) $@
#	$(CP) $(DOC_INDEX_HTML2) $@
#
#ZIP.files += $(TCKDIR)/copyright.txt $(TCKDIR)/index.html
#
#
##----------------------------------------------------------------------
##
## 508 html files
##
#
#FIVEOH8_ROOT = $(TOPDIR)/src/share/doc/html
#
#FIVEOH8_FILES = \
#	index.html \
#	copyright.html \
#	preface.html \
#	debug.html \
#	install.html \
#	setup.html \
#        chapter2.html \
#	intro.html \
#	using.html \
#        a-faq.html \
#	document.css \
#        catalog.css \
#        images/diamond.gif \
#	images/indexx.gif \
#	images/nextx.gif \
#	images/toc.gif \
#        images/diamond_sm.gif \
#	images/introa.gif \
#	images/prev.gif \
#	images/tocx.gif \
#        images/index.gif \
#	images/next.gif \
#	images/prevx.gif \
#	images/triangle.gif
#
#
#
#FIVEOH8_TARGETS = $(FIVEOH8_FILES:%=$(TCKDIR)/doc/tck/HTML/%)
#
#$(FIVEOH8_TARGETS): $(@:$(TCKDIR)/doc/tck/HTML/%=$(FIVEOH8_ROOT)/%)
#	@ $(MKDIR) -p $(@D)
#	@ $(RM) $@
#	$(CP) $(@:$(TCKDIR)/doc/tck/HTML/%=$(FIVEOH8_ROOT)/%) $@
#	$(CHMOD) -w $@
#
#FIVEOH8.ok: $(FIVEOH8_TARGETS)
#	echo ":-) $(FIVEOH8_TARGETS) " > $@
#
#FIVEOH8.sh = \
#if [ -z "$(EXCLUDEDOCS)" ]; then \
#	echo FIVEOH8.ok; \
#fi
#
## comment this out for now since html files have been 
## removed from the ws for the time being
#ZIP.files += $(FIVEOH8.sh:sh)
#
##----------------------------------------------------------------------
## 
## The TCK Manual 
#
#MANSRCDIR = $(TOPDIR)/src/share/doc/manual
#MANUAL_PDF = $(TCKDIR)/doc/tck/JAXB-TCK_Users_Guide.pdf
#
#MANUAL_FM_FILES = \
#	JAXB-TCK_Users_Guide.book \
#	a-faq.fm \
#	chapter2.fm \
#	copyright.fm \
#	debug.fm \
#	install.fm \
#	intro.fm \
#	preface.fm \
#	setup.fm \
#	tckTOC.fm \
#	title.fm \
#	using.fm
#
## temp directory in which to put writable(!) copies of the
## frame files.  fmbatch does not like opening read-only files.
#MAN_TMP = manual_tmp
#
## File with print options set for printing PDF
#PRINT_PDF = printPDF.fm
#
## create necessary directories
#$(MAN_TMP):
#	$(TEST) -d $@ || $(MKDIR) -p $@
#
## copy files as necessary to temp directory, making file writable
## to keep fmbatch happy
#$(MAN_TMP)/%: $(MAN_TMP) $(MANSRCDIR)/%
#	d=`$(DIRNAME) $@` ; $(TEST) -d $$d || $(MKDIR) -p $$d
#	$(CP) -r $(@:$(MAN_TMP)/%=$(MANSRCDIR)/%) $@
#	$(CHMOD) +w $@
#
## copy files as necessary to temp directory, making file writable
## to keep fmbatch happy
#$(PRINT_PDF:%=$(MAN_TMP)/%): $(MAN_TMP) $$(@:$(MAN_TMP)/%=$(BUILDDIR)/%)
#	$(CP) $(@:$(MAN_TMP)/%=$(BUILDDIR)/%) $@
#	$(CHMOD) +w $@
#
## run fmbatch on writable files in tmp directory, then copy resultant
## PDF file to correct place in doc/
#$(MANUAL_PDF): $(MANUAL_FM_FILES:%=$(MAN_TMP)/%) $(PRINT_PDF:%=$(MAN_TMP)/%) $(BUILDDIR)/manual.fmbatch
#	(   echo "Open $(PRINT_PDF)"; \
#	    for i in $(MANUAL_FM_FILES); do echo "Open $$i"; done ; \
#	    echo "Update JAXB-TCK_Users_Guide.book"; \
#	    echo "Print JAXB-TCK_Users_Guide.book $(PRINT_PDF)") | \
#	( cd $(MAN_TMP); DISPLAY=$${DISPLAY:=`hostname`:0.0} $(FMBATCH) -v )
#	$(TEST) -d $(@D) || $(MKDIR) -p $(@D)
#	$(MV) $(MAN_TMP)/$(@F) $@
#	echo ":-) $@ created"
#
#CLEANFILES += $(MAN_TMP)
#
##----------------------------------------------------------------------
#BUILDMANUAL.sh = \
#if [ -z "$(EXCLUDEDOCS)" ]; then \
#	echo $(MANUAL_PDF); \
#fi
#
#ZIP.files += $(BUILDMANUAL.sh:sh)
#
##----------------------------------------------------------------------
#
#
## this does link checking of all files (ie INCLUDES tests)
## check results in linklint directory
#full-linklint.ok:
#	@echo "+++ Linklint report available in linklint/linklint.html"
#	$(LINKLINT) -htmlonly -error -orphan -quiet -warn \
#		-limit 20000 \
#		-doc linklint \
#		-root $(UNZIPDIR)/$(JCKVERSION)  /@
#	echo "$(RELDIR) tested at `date`" > $@
#
## TESTS += full-linklint.ok
#
#CLEANFILES += full-linklint.ok linklint
#
##---------------------------------------------------------------------
##
## Rules to copy files from the src/share/doc tree to $(TCKDIR)
#
#$(TCKDIR)/doc/%.html: $(TOPDIR)/src/share/doc/%.html
#	$(HTMLCHECK) $(@:$(TCKDIR)/doc/%.html=$(TOPDIR)/src/share/doc/%.html)
#	@ $(TEST) -d $(@D) || $(MKDIR) -p $(@D)
#	@ $(RM) $@
#	$(CP) $(@:$(TCKDIR)/doc/%=$(TOPDIR)/src/share/doc/%) $@
#	$(CHMOD) -w $@
#
#$(TCKDIR)/doc/%: $(TOPDIR)/src/share/doc/%
#	@ $(TEST) -d $(@D) || $(MKDIR) -p $(@D)
#	@ $(RM) $@
#	$(CP) $(@:$(TCKDIR)/doc/%=$(TOPDIR)/src/share/doc/%) $@
#	$(CHMOD) -w $@
