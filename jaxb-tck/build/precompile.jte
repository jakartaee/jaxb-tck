#
# Copyright (c) 2002, 2018 Oracle and/or its affiliates. All rights reserved.
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

###############################################################################
#
# Precompile environment
#

env.precompile.script=com.sun.jaxb_tck.lib.PrecompileJaxbTckScript

env.precompile.command.compile.java=com.sun.javatest.lib.JavaCompileCommand \
        -classpath $java.class.path:$testClassDir:$outDir \
        -d $testClassDir $testSource


env.precompile.command.compile.xsd=com.sun.jaxb_tck.lib.JaxbCommand \
        -xjc com.sun.jaxb_tck.lib.SchemaCompiler - -p $package -d $outDir $schema

