#!/bin/sh
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

# $0 name of script, from which we deduce workspace root
# $1 directory in which to install file
# $2 file/directory to install, relative to workspace root,
#	OR "-" to read stdin for a list of such files/directories


install() {
  # figure out what to move and where to move it
  src=$1
  dst=$2

  # make sure target directory exists
  mkdir -p `dirname $dst`
  
  # make sure target does not exist
  if [ -r $dst ]; then
    /bin/rm -rf $dst
  fi

  # install it
  if [ -d $src ]; then
    cp -r $src $dst
  else    
    case $src in
      *.doc.xml ) /bin/sed -e 's|<a href="[./]*/spec/.*">\(.*\)</a>|\1|g' \
      		    -e 's|<a HREF="[./]*/spec/.*">\(.*\)</a>|\1|g' \
       		    -e 's|- <a name=.* href="[./]*/spec/.*">\[spec\]</a>||' \
       				$src > $dst	;;
      * )	cp $src $dst ;;
    esac
  fi
}

get_destination_path() {
    f=$1
    rmd=$2
    if [ ! -z "$rmd" ] ; then
        d=`echo "$f" | sed -e "s|$rmd|/|"`
    else 
        d=$f
    fi
    echo $d
}

# Validate args

if [ $# != 3 -a $# != 5 ]; then
	echo Wrong number of arguments
	exit 1
fi

if [ -z "$1" ]; then
	echo null source directory specified
	exit 1
fi

if [ -z "$2" ]; then
	echo null destination directory specified
	exit 1
fi

if [ -z "$3" ]; then
	echo null path specified
	exit 1
fi

srcdir=$1
dstdir=$2
rmOutDir=""
if [ "$3" != "-rmOutDir" ] ; then
    file=$3
else
    if [ -z "$4" ] ; then
        echo no remove subpath specified
        exit 1
    fi
    rmOutDir=$4
    if [ -z "$5" ]; then
        echo null path specified
        exit 1
    fi
    file=$5
fi

if [ "$file" = "-" ]; then
    while read file ; do 
        destdir=`get_destination_path $dstdir/$file $rmOutDir` ;
        install $srcdir/$file $destdir ;
    done
else 
    destdir=`get_destination_path $dstdir/$file $rmOutDir` ;
    install $srcdir/$file $destdir ;
fi

