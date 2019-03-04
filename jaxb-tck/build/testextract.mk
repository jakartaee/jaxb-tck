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
# Partial Makefile for TCK tests

#----------------------------------------------------------------------
#
# TESTSUITEFILES
#
# If INITIALURLS is set, use that
# else if INITIALURL is set, use that
# else ...

TESTSUITEFILES.sh = \
	if [ ! -z "$(INITIALURLS)" ]; then \
	  echo $(INITIALURLS) ; \
	elif [ ! -z "$(INITIALURL)" ]; then \
	  echo $(INITIALURL) ; \
	else \
	  ts=$(TOPDIR)/src/`basename \`pwd\``/tests/testsuite.html; \
	  if [ -r "$$ts" ] ; then \
	    $(GREP) -i 'href[ 	]*=[	]*["][a-zA-Z0-9_]*/' $$ts | \
	    $(SED) -e 's|^.*[hH][rR][eE][fF][ 	]*=[ 	]*["]\([a-zA-Z0-9_]*/[^\#>"]*\).*$$|\1|' \
	    -e 's|/[^/]*$$||'; \
	  else \
	    for i in `echo $(TEST_REPOSITORIES)`; do \
		(cd $$i/tests; $(LS) -1p | $(GREP) -v 'SCCS/$$' | $(GREP) '/$$'); \
	    done \
	  fi \
	fi | \
	sort -u

TESTSUITEFILES = $(shell $(TESTSUITEFILES.sh))

#----------------------------------------------------------------------
#
# TESTSUITEDIRS
#
# The directories containing the TESTSUITEFILES

TESTSUITEDIRS.sh = \
	for i in `echo $(TESTSUITEFILES)` ; do \
	    for j in `echo $(TEST_REPOSITORIES)`; do \
	      if [ -d "$$j/tests/$$i" ]; then \
		echo $$i; \
		break; \
	      elif [ -f "$$j/tests/$$i" ]; then \
		echo `$(DIRNAME) $$i`; \
		break; \
	      fi ; \
	    done\
	done | \
	sort -u

TESTSUITEDIRS = $(shell $(TESTSUITEDIRS.sh))

#----------------------------------------------------------------------
#
# SELECT_TESTSUITEFILES
# 
# This is a convenience macro for a command that can be put in a pipe to 
# restrict a set of file names to those in TESTSUITEDIRS
# The final "cat" is to force errors from egrep to be ignored if there
# are no matches

SELECT_TESTSUITEFILES = \
	$(EGREP) `echo '$(TESTSUITEDIRS:%=^tests/%/)' | $(SED) -e 's/ /|/'g | $(SED) -e 's/\/\//\//g'` | $(CAT)


#----------------------------------------------------------------------
#
# info about test suite

# second level subdirectories of tests/
DIRS = \
api/javax_xml \
api/signaturetest \
schema2java/nisttest/float \
schema2java/nisttest/double \
schema2java/nisttest/nonPositiveInteger \
schema2java/nisttest/negativeInteger \
schema2java/nisttest/boolean \
schema2java/nisttest/positiveInteger \
schema2java/nisttest/string \
schema2java/nisttest/anyURI \
schema2java/nisttest/base64Binary \
schema2java/nisttest/int \
schema2java/nisttest/NCName \
schema2java/nisttest/byte \
schema2java/nisttest/unsignedByte \
schema2java/nisttest/short \
schema2java/structures/SType \
schema2java/structures/AttrDecl \
schema2java/structures/ElemDecl \
java2schema schema_bind \
xml_schema/sunData xml_schema/nistData xml_schema/msData



# filter DIRS according to TESTSUITEDIRS, which is derived from
# INITIALURL(S) or tests/testsuite.html.
# Thus, this automatically adjusts to the product being built
# and/or the user's restrictions
SELECTED_DIRS.sh = \
	match() { \
	  d=$$1 ; shift ; \
	  for i in $$* ; do \
	    case $$i in \
	      $$d* ) echo $$d ; return ;; \
	      * ) case $$d in \
		    $$i* ) echo $$d ; return ;; \
		  esac ;; \
	    esac \
	  done \
	} ; \
	for i in $(DIRS) ; do \
	    match $$i $(TESTSUITEDIRS) ;\
	done

SELECTED_DIRS = $(shell $(SELECTED_DIRS.sh))

#----------------------------------------------------------------------
#
# REPOSITORY_IDENT
# 
# This is a macro for a command that can be put in a pipe to get
# the suffix of a repository name which would identify repository
# build targets. REPOSITORY_IDENT is the name of a directory immediately
# containing repository workspace. This name should be unique between
# repositories. If more complicated repository identifiers are needed -
# change this macro.

REPOSITORY_IDENT = \
	$(SED) -e 's|^\(.*\)/$$|\1|' -e 's|^.*/\(.\{1,\}\)$$|\1|'


# Check that repository identifiers are unique
CHECK_IDENT.sh = \
	    for i in $(TEST_REPOSITORIES); do \
		for j in $(TEST_REPOSITORIES); do \
		    if [ "$$i" != "$$j" ]; then \
			if [ `echo $$i | $(REPOSITORY_IDENT)` = `echo $$j | $(REPOSITORY_IDENT)` ]; then \
			    echo "ERROR: Repository identifiers are the same: $$i and $$j"; \
			    exit 1; \
			fi \
		    fi \
		done; \
		echo $$i | $(REPOSITORY_IDENT); \
		$(TEST) -d `echo $$i | $(REPOSITORY_IDENT)` || $(MKDIR) -p `echo $$i | $(REPOSITORY_IDENT)`; \
		$(TEST) -f `echo $$i | $(REPOSITORY_IDENT)`/repository.path || echo $$i > `echo $$i | $(REPOSITORY_IDENT)`/repository.path; \
	    done

# List of repository identifiers
REPOSITORY_IDENTS = $(shell $(CHECK_IDENT.sh))

# Macro that should be used in pipe to find repository path
# having a repository identifier. Should be used only inside `... | REPOSITORY_PATH`,
REPOSITORY_PATH = \
	(read name; \
	for i in $(TEST_REPOSITORIES); do \
	    if [ "\`echo $$i | $(REPOSITORY_IDENT)\`" = "$$name" ]; then \
		echo $$i; \
	    fi \
	done)

#----------------------------------------------------------------------
# 
# Copy all plain tests and extract generated tests into temporary directory

testextract.ok: $(REPOSITORY_IDENTS:%=%/testextract.ok) copy-common-files.ok
	echo "Tests are extracted at `date`" | $(TEE) $@

CLEANFILES += $(REPOSITORY_IDENTS)

$(TEMPTESTSTORAGE):
	@ $(TEST) -d $@ || $(MKDIR) -p $@

# Extract tests of one test repository. External make call nedded to avoid
# re-obtaining of repository path.
REP_IDENT_TESTEXTRACT=$(REPOSITORY_IDENTS:%=%/testextract.ok)
TEST_EXTRACT_COPY_NON_GEN=$(REP_IDENT_TESTEXTRACT:%/testextract.ok=%/copy-nongenerated.ok)
$(REPOSITORY_IDENTS:%=%/testextract.ok):%/testextract.ok:%/copy-nongenerated.ok
	echo "Tests of $(@:%/testextract.ok=%) repository are extracted at `date`" | $(TEE) $@

CLEANFILES += $(TEMPTESTSTORAGE)

#---------------------------------------------------------------------
#
# filter to get the list of a repository's second-level dirs, i.e.:
# REPOS_ID/api/java_lang, REPOS_ID/api/java_io, etc.
# usage: `echo REPOS_ID | REPOSITORY_SELECTED_DIRS`

GET_REPOSITORY_SELECTED_DIRS= \
	(read repos_id; \
	if [ ! -z "`echo $(SELECTED_DIRS)`" ] ; then \
		for d in `echo $(SELECTED_DIRS)` ; do \
			repos_dir=`echo $$repos_id | $(REPOSITORY_PATH)` ; \
			if [ -d "$$repos_dir/tests/$$d" ] ; then \
				echo $$repos_id/$$d ; \
			fi \
		done \
	fi)

#---------------------------------------------------------------------
#
# macros to get the list of all repositories' second-level dirs, i.e.:
# REPOS_ID1/api/java_lang, REPOS_ID2/lang/CLSS, etc.

ALL_REPOSITORIES_SELECTED_DIRS.sh = \
	if [ ! -z "`echo $(REPOSITORY_IDENTS)`" ] ; then \
		for r in `echo $(REPOSITORY_IDENTS)` ; do \
			echo $$r | $(GET_REPOSITORY_SELECTED_DIRS) ; \
		done \
	fi

ALL_REPOSITORIES_SELECTED_DIRS=$(shell $(ALL_REPOSITORIES_SELECTED_DIRS.sh))

#----------------------------------------------------------------------
# 
# copy all non-generated files of the given REPOSITORY in parallel
# by second-level subsuites, i.e. REPOS/api/java_lang, REPOS/api/java_io, etc.
#
#REP_IDENT_COPY_NONGEN=$(REPOSITORY_IDENTS:%=%/copy-nongenerated.ok)
#REPOSITORY_SELECTED_DIRS.sh=$(GET_REPOSITORY_SELECTED_DIRS)
#REPOSITORY_SELECTED_DIRS=$(REPOSITORY_SELECTED_DIRS.sh)
.SECONDEXPANSION:
$(REPOSITORY_IDENTS:%=%/copy-nongenerated.ok):%/copy-nongenerated.ok:install $$(foreach preq,$$(shell echo % | $$(GET_REPOSITORY_SELECTED_DIRS)),$$(preq)/copy-subsuite-nongenerated.ok)
	echo ":-) copied $(@:%/copy-nongenerated.ok=%) files at `date`" | $(TEE) $@

GET_REPOS_ID=(read repos_subdir; echo $$repos_subdir | $(SED) -e 's|^\([^/]*\).*|\1|')

$(ALL_REPOSITORIES_SELECTED_DIRS:%=%/copy-subsuite-nongenerated.ok):%/copy-subsuite-nongenerated.ok:%/files-subsuite-nongenerated.lst $(TEMPTESTSTORAGE)
	./install `$(CAT) $(foreach preqq,$(shell echo $@|$(GET_REPOS_ID)),$(preqq)/repository.path)` $(TEMPTESTSTORAGE) -rmOutDir $(PRJ_DIR) - < $(@:%/copy-subsuite-nongenerated.ok=%/files-subsuite-nongenerated.lst)
	echo ":-) copied $(@:%/copy-subsuite-nongenerated.ok=%) files at `date`" | $(TEE) $@

ALL_REPO_SEL_FIL_SUB_NON_GEN=$(ALL_REPOSITORIES_SELECTED_DIRS:%=%/files-subsuite-nongenerated.lst)
REPOS_IDN.sh=echo $(ALL_REPO_SEL_FIL_SUB_NON_GEN) | $(GET_REPOS_ID)
REPOS_IDN=$(shell $(REPOS_IDN.sh))
FILES_NON_GEN=$(REPOS_IDN:%=%/files-nongenerated.lst)
$(ALL_REPOSITORIES_SELECTED_DIRS:%=%/files-subsuite-nongenerated.lst): $$(foreach prequ,$$(shell echo $$@ | $$(GET_REPOS_ID)),$$(prequ)/files-nongenerated.lst)
	$(TEST) -d $(@D) | $(MKDIR) -p $(@D)
	subsuite=`echo $(@:%/files-subsuite-nongenerated.lst=%) | $(SED) -e 's|^[^/]*/||'` ; \
	$(GREP) $$subsuite $(foreach preqs,$(shell echo $@|$(GET_REPOS_ID)),$(preqs)/files-nongenerated.lst) | $(CAT) > $@
	echo "created the list of non-generated files to copy, repository: $(REPS_IDN)"

#---------------------------------------------------------------------
# construct the list of all non-generated files for the given REPOSITORY.
#
$(REPOSITORY_IDENTS:%=%/files-nongenerated.lst):%/files-nongenerated.lst:%/files.lst 
	$(GREP) -v '^tests/.*\.jmpp$$' $(@:%/files-nongenerated.lst=%/files.lst) | $(GREP) '^tests/.*$$' $(IGNORE_PRJ_DIRS_FILTER) | $(SELECT_TESTSUITEFILES)> $@ ; \
	if [ -r $(TOPDIR)/src/$(PRODUCT)/extras.lst ]; then \
		for i in `$(CAT) $(TOPDIR)/src/$(PRODUCT)/extras.lst` ; do \
			extra_file=`$(GREP) $$i $(@:%/files-nongenerated.lst=%/files.lst) | $(CAT)`; \
			if [ ! -z "$$extra_file" ] ; then \
				if [ -z "`$(GREP) $$extra_file $@ | $(CAT)`" ] ; then \
					echo $$extra_file >> $@ ; \
				fi \
			fi \
		done \
	fi

#---------------------------------------------------------------------
#
# create a list of all files in the workspace, based on the files known
# to CodeManager.
#
#FILE_LIST=$(REPOSITORY_IDENTS:%=%/files.lst)
NAMETABLE.sh=$(CAT) $(@:%/files.lst=%)/repository.path
NAMETABLE=$(shell $(NAMETABLE.sh))/build/nametable

$(REPOSITORY_IDENTS:%=%/files.lst):
	chmod 777 $(@:%/files.lst=%)/repository.path
	@ $(TEST) -d $(@:%/files.lst=%) || $(MKDIR) -p $(@:%/files.lst=%)
	@ echo "TESTSUITEDIRS = \"$(TESTSUITEDIRS)\"" 
	@ echo "Getting the list of repository files from \"$(foreach prequu,$(shell cat $(foreach prequs,$(@:%/files.lst=%),$(prequs)/repository.path)),$(prequu)/build/nametable)\"" ; \
	$(SED) -e '1d' -e 's| .*$$||' -e '/^DELETED_FILES\//d' -e '/^deleted_files\//d' \
		$(foreach prequu,$(shell cat $(foreach prequs,$(@:%/files.lst=%),$(prequs)/repository.path)),$(prequu)/build/nametable) | $(SORT) -u > $@

#----------------------------------------------------------------------
# Non-generated files are copied from all repositories into TEMPTESTSTORAGE in parallel
# divided by second-level directories, i.e. repos1/api/java_lang | repos2/api/java_awt |...
# Common for all repositories non-generated files from api/, lang/, vm/ directories 
# are copied separately not in parallel (since they are probably the same in all 
# repositories, this will allow to eliminate file creation conflicts).
#
UNIQ_TOP_DIRS.sh= \
	for d in `echo $(SELECTED_DIRS)` ; do \
		parent_dir=`dirname $$d` ; \
		while [ "$$parent_dir" != "." ] ; do \
			echo $$parent_dir ; \
			parent_dir=`dirname $$parent_dir` ; \
		done ; \
	done | $(SORT) -u

UNIQ_TOP_DIRS=$(shell $(UNIQ_TOP_DIRS.sh))

copy-common-files.ok: $(UNIQ_TOP_DIRS:%=copy-%/dir-files.ok)
	echo "Common files are copied at `date`" > $@

$(UNIQ_TOP_DIRS:%=copy-%/dir-files.ok): $$(@D)
	to_path=$(TEMPTESTSTORAGE)/tests/$(@:copy-%/dir-files.ok=%)/ ; \
	repos_ids=`for i in \`echo $(ALL_REPOSITORIES_SELECTED_DIRS)\` ; do echo $$i; done | $(GREP) "/$(@:copy-%/dir-files.ok=%)/" | $(SED) -e 's|^\([^/]*\).*|\1|' | $(SORT) -u` ; \
	for id in `echo $$repos_ids` ; do \
		from_path=`echo $$id | $(REPOSITORY_PATH)`/tests/$(@:copy-%/dir-files.ok=%)/ ; \
		files_to_copy=`$(LS) -1p $$from_path | $(GREP) -v '/$$' | $(GREP) -v '.*\.jmpp' | $(GREP) -v '.*\.pl' | $(CAT)` ; \
		for f in `echo $$files_to_copy` ; do \
			$(TEST) -d $$to_path || $(MKDIR) -p $$to_path ; \
			if [ ! -f $$to_path/$$f ] ; then \
				$(CP) $$from_path/$$f $$to_path/$$f ; \
			fi \
		done \
	done
	echo "Files from common for some repositories '$(@:copy-%/dir-files.ok=%)' dir are copied at `date`" > $@

$(UNIQ_TOP_DIRS:%=copy-%):
	$(TEST) -d $@ || $(MKDIR) -p $@

.NO_PARALLEL: $(UNIQ_TOP_DIRS:%=copy-%)
