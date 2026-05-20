#
# Copyright (c) 2026 Contributors to the Eclipse Foundation.
# Copyright (c) 1999, 2021 Oracle and/or its affiliates. All rights reserved.
#
# This program and the accompanying materials are made available under the
# terms of the Eclipse Public License v. 2.0, which is available at
# https://www.eclipse.org/legal/epl-2.0.
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

TOOLS_JAVAC=$(PRECOMPILE_JAVAC)  -Xlint:deprecation -Xlint:unchecked

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
$(TCKDIR)/bin:
	$(TEST) -d $(TCKDIR)/bin || $(MKDIR) -p $(TCKDIR)/bin

gettest.ok: javatest.ok $(TCKDIR)/bin
	$(RM) $(TCKDIR)/bin/gettest
	$(CP) $(TOPDIR)/src/share/bin/gettest $(TCKDIR)/bin
	$(CHMOD) +x $(TCKDIR)/bin/gettest
	echo "gettest is copied at " `date` > $@

ZIP.files += gettest.ok

compareER.pl.ok: javatest.ok $(TCKDIR)/bin
	$(RM) $(TCKDIR)/bin/compareER.pl
	$(CP) $(TOPDIR)/src/share/bin/compareER.pl $(TCKDIR)/bin
	$(CHMOD) +x $(TCKDIR)/bin/compareER.pl
	echo "compareER.pl is copied at " `date` > $@

ZIP.files += compareER.pl.ok

xjc.sh.ok: javatest.ok $(TCKDIR)/bin
	$(RM) $(TCKDIR)/bin/xjc*
	$(CP) $(TOPDIR)/src/share/bin/xjc* $(TCKDIR)/bin
	$(CHMOD) +x $(TCKDIR)/bin/xjc*.sh
	echo "xjc.sh|bat is copied at " `date` > $@

ZIP.files += xjc.sh.ok

schemagen.sh.ok: javatest.ok $(TCKDIR)/bin
	$(RM) $(TCKDIR)/solaris/bin/schemagen*
	$(CP) $(TOPDIR)/src/share/bin/schemagen* $(TCKDIR)/bin/
	$(CHMOD) +x $(TCKDIR)/bin/schemagen*.sh
	echo "schemagen.sh|bat is copied at " `date` > $@

ZIP.files += schemagen.sh.ok
