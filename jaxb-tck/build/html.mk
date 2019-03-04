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

#---------------------------------------------------------------------
#
# Rules to copy files from the tests tree to $(TEMPTESTSTORAGE)
#
# Generate directory-level index files
html.ok: testsources.ok $(TEMPTESTSTORAGE)/tests/testsuite.doc.xml precompile-jck-index-generator.ok
	CLASSPATH=classes:$(TCKDIR)/classes \
	${GENERAL_JAVA} \
	  -Dtck.build.propfile=../tck.properties \
	  $(JCK_INDEX_GENERATOR) \
		-log \
		-xmldir $(TEMPTESTSTORAGE)/tests \
		-jckdir $(TCKDIR)/tests \
		-copyrightLink "../COPYRIGHT-jaxbtck.html"
	echo "Directory level html files generated at " `date` > $@

ZIP.files += html.ok

CLEANFILES += html.ok


# install .doc.xml files
$(TEMPTESTSTORAGE)/tests/%.doc.xml: install $(ABSTOPDIR)/tests/%.doc.xml
	./install $(ABSTOPDIR) $(TEMPTESTSTORAGE) $(@:$(TEMPTESTSTORAGE)/%=%)
# install testsuite.doc.xml
# for integrated JAXB-TCK the file should be taken from integrating TCK
$(TEMPTESTSTORAGE)/tests/testsuite.doc.xml: install $(SHARED_ABSTOPDIR)/tests/testsuite.doc.xml
	./install $(SHARED_ABSTOPDIR) $(TEMPTESTSTORAGE) $(@:$(TEMPTESTSTORAGE)/%=%)
