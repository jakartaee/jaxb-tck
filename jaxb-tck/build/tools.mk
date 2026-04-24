#
# Copyright (c) 2026 Contributors to the Eclipse Foundation.
# Copyright (c) 1999, 2021 Oracle and/or its affiliates. All rights reserved.
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

