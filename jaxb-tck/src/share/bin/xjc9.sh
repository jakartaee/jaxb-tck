#
# Copyright (c) 2011, 2018 Oracle and/or its affiliates. All rights reserved.
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

# Make sure that environment JAVA_HOME variable is set
if [ -z "$JAVA_HOME" ]
then
    echo "JAVA_HOME must be set before running this script"
    exit 1
fi

if [ -z "$JAXB_HOME" ]
then
    echo "JAXB_HOME must be set before running this script"
    exit 1
fi

# Process arguments
xjcArgs=""
while [ $# -gt 0 ]
do
   if [ "$1" == "-cp" ]
   then
       XJC_CLASSPATH=${XJC_CLASSPATH:=$2}
       shift
   else
       xjcArgs="${xjcArgs} \"$1\""
   fi
   shift
done

if [ -z "$XJC_CLASSPATH" ]
then
    XJC_CLASSPATH=.
fi

[[ ! "$XJC_CLASSPATH" = *tools.jar* ]] && XJC_CLASSPATH="$XJC_CLASSPATH:$JAVA_HOME/lib/tools.jar"

SCHEMACOMPILER_REALIZATION=com.sun.jaxb_tck.lib.SchemaCompiler

# Invoke schema compiler provided by JAXB RI via java interface
echo eval "\"$JAVA_HOME/bin/java\" $TCK_JAXB_OPTIONS -cp $CLASSPATH:$XJC_CLASSPATH:$JAXB_HOME/mod/jaxb-xjc.jar com.sun.jaxb_tck.lib.JaxbCommand $TCK_JAXB_OTHER_OPTIONS -xjc $SCHEMACOMPILER_REALIZATION - ${xjcArgs}"
eval "\"$JAVA_HOME/bin/java\" $TCK_JAXB_OPTIONS -cp $CLASSPATH:$XJC_CLASSPATH:$JAXB_HOME/mod/jaxb-xjc.jar com.sun.jaxb_tck.lib.JaxbCommand $TCK_JAXB_OTHER_OPTIONS -xjc $SCHEMACOMPILER_REALIZATION - ${xjcArgs}"


