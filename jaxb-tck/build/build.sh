#! /bin/ksh
#
# Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
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
make=make
shell=ksh

case `uname -n` in
    ruffles ) jobs=3 ;;
    sqeel   ) jobs=4 ;;
    spb-orion ) jobs=4 ;;
    spb-emerald ) jobs=4 ;;
    gzilla  ) jobs=2 ;;
    jck ) 
      export LANG="C"
      export LC_ALL="C"                     
      jobs=6
      make=/set/devtools/x86/Intel-S2/bin/dmake
    ;;
  sc22db01 )
      jobs=6
      make="/java/devtools/i386/packages/SS10/opt/SUNWspro/bin/dmake -m parallel -j $jobs"
    ;;
    *   ) 
      export LANG="C"
      export LC_ALL="C"                     
      make=dmake
      jobs=1 
    ;;
esac
make="/java/devtools/sparc/packages/SS10/opt/SUNWspro/bin/dmake -m parallel -j $jobs"

echo "$make ) Building with $jobs jobs"

# delete old files -atime +7
find . \( -name make.\*.log -o -name make.\*.mlp \) -atime +7 -exec rm -rf \{\} \;

# date stamp for unique log file names; use extra \ to avoid SCCS problems
stamp=`date +\%m\%d\%y-\%H\%M`

xml_schema="no"
while [ $# -gt 1 ]; do
  if echo "$1" | grep -q "REPOSITORIES.*xml_schema"; then
    xml_schema="$1"
  fi
  case "$1" in 
    -dmake ) make="dmake -m parallel -j $jobs" ; shift ;;
    -batch ) shell="at -k -m now" ; shift ;;
    * ) break ;;
  esac 
done
if [ "$xml_schema" == "no" ]; then
  if [ ! -d "../../xml_schema" ]; then
    echo "../../xml_schema repository not found"
  else
    cd "../../xml_schema"
    xml_schema="REPOSITORIES=$(pwd)"
    cd "../jaxb-tck/build"
  fi
  echo "xml_schema repository attached via $xml_schema"
else
  echo "$xml_schema"
  xml_schema=""
fi
echo $make $xml_schema $* 
# here we go
$shell <<- EOF
	# when complete, convert log file to html
	cleanup() {
	    if [ -r make.$stamp.log ]; then
        echo log written to make.$stamp.log
        ksh ./log2html.ksh -o make.$stamp.mlp make.$stamp.log
        echo analysis written to make.$stamp.mlp
        rm -f make.latest.log make.latest.mlp
        ln -s make.$stamp.log make.latest.log
        ln -s make.$stamp.mlp make.latest.mlp
	    fi
	}

	trap cleanup 0 KILL QUIT TERM

	( echo Started at `date`
	  echo "Command args: $xml_schema $*"
	  time $make $xml_schema $* 2>&1
	  echo Finished at `date`
	) | tee make.$stamp.log

	EOF

