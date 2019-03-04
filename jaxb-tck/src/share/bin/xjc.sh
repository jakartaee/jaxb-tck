#
# Copyright (c) 2017, 2018 Oracle and/or its affiliates. All rights reserved.
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

SCHEMACOMPILER_REALIZATION=com.sun.jaxb_tck.lib.SchemaCompiler

#######################################################################################
#
# Make sure that env variables JAXB_HOME is set
#

if [ -z "$JAVA_HOME" ]
then
    echo " JAVA_HOME must be set before running this script"
	exit 1
fi

if [ -z "$JAXB_ENDORSED" ]
then
    echo " JAXB_ENDORSED must be set before running this script"
	exit 1
fi

# Invoke the schema compiler
echo $JAVA_HOME/bin/java -Djava.endorsed.dirs=$JAXB_ENDORSED -cp $CLASSPATH com.sun.jaxb_tck.lib.JaxbCommand -xjc $SCHEMACOMPILER_REALIZATION - $@
$JAVA_HOME/bin/java -Djava.endorsed.dirs=$JAXB_ENDORSED -cp $CLASSPATH com.sun.jaxb_tck.lib.JaxbCommand -xjc $SCHEMACOMPILER_REALIZATION - $@

