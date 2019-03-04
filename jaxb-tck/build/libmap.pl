#!/usr/local/bin/perl
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

#
# Library Map generator
# 
# usage: libmap.pl { [[repository_path]:][library_path/]filename.lib.xml } ...
# 
# For a given list of all .lib.xml files being built
# the libmap genarates a map from LibID to list of .lib.xml files
# of the library implementations correspondent to the library 
# identifier. Format of the generated map is following:
#
# LibId repository_path/library_path/filename.lib.xml ...
#

foreach $name (@ARGV) {
    $path = $name;
    $path =~ s|:|/|;
    open(LIBXML, $path) || die "Can not open file: $path";

    while(<LIBXML>) {
	if (/<Library\s+ID\s*=\s*"([^"]+)"\s*>/) {
    	    $map{$1} = $map{$1} . " " . $name;
	}
    }
    close(LIBXML);
}

foreach $key (keys(%map)) {
    print "$key$map{$key}\n";
}
