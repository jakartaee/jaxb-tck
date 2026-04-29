#
# Copyright (c) 2026 Contributors to the Eclipse Foundation.
# Copyright (c) 1999, 2018 Oracle and/or its affiliates. All rights reserved.
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

# Partial Makefile for TCK lib files

#----------------------------------------------------------------------
#
# the classes required when building TCK
#
#----------------------------------------------------------------------

TESTFILTER_PLUGIN = com.sun.tgxml.tools.filter.plugins.XMLSchemaTestFilter

#----------------------------------------------------------------------
#
# XMLSchemaTestEmitter plugin
#

TESTEMITTER_PLUGIN = com.sun.tgxml.tools.testgen.processors.emitter.XMLSchemaTestEmitter

#----------------------------------------------------------------------
#
# J2XTestEmitter plugin
#
J2X_TESTEMITTER_PLUGIN = com.sun.tgxml.tools.testgen.processors.emitter.J2XTestEmitter
J2X_TESTEMITTER_PLUGIN_2 = com.sun.tgxml.tools.testgen.processors.emitter.J2XRuntimeEmitter
J2X_TESTEMITTER_PLUGIN_3 = com.sun.tgxml.tools.testgen.processors.emitter.J2XTestWTBEmitter
J2X_TESTEMITTER_PLUGIN_4 = com.sun.tgxml.tools.testgen.processors.emitter.J2XEmitterBase

#----------------------------------------------------------------------
#
# libfilter plugin
#
LIBFILTER_PLUGIN = com.sun.tgxml.tools.filter.plugins.RuntimeFilter

#----------------------------------------------------------------------
#
# JCKIndexGenerator classes
#
JCK_INDEX_GENERATOR = com.sun.tgxml.tools.indexgen.JCKIndexGenerator
JCK_INDEX_GEN_CLASS = com.sun.tgxml.tools.indexgen.JCKIndexGen
JCK_INDEX_HTTLFILE = com.sun.tgxml.tools.indexgen.JCKHtmlFile

#----------------------------------------------------------------------
#
# BundleTestGenFilter classes
#
BUNDLE_TEST_GEN_FILTER = com.sun.tgxml.tools.testgen.BundleTestGenFilter

#----------------------------------------------------------------------
#
# JtiGen classes
#
JTI_GEN = com.sun.jaxb_tck.util.JtiGen
