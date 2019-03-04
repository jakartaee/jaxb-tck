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
while(<>) {
  print,next if /^#|^$/;  #don't process comments an empty lines
  if(/^(\S+)\s+(.+)/){
    my $entry = $1;
    my $tail = $2;
    if($entry =~ /([^\[]+)\[?([^\]]*)\]?/) {
      $url = $1;
      $tcs = $2;
      if( ($url =~ m|^api/javax_xml/bind/JAXBContext/java2schema/CustomizedMapping|) || ($url =~ m|^xml_schema|) || ($url =~ m|^java2schema/CustomizedMapping|) ) {
         # the xml_schema and xmlCore tests use other test emitter
         ($url, $tcs) = split(/#/, $url);  # and have no MultiTest testcases
         $url =~ s|\.html||;
         if($tcs eq 'Negative') {
           printf "%-129s %s\n", "${url}_n[$tcs]", $tail;  # put additional possible TG name 
         }
	     if($tcs eq 'Positive') {
           printf "%-129s %s\n", "${url}_p[$tcs]", $tail;  # put additional possible TG name 
         }
      } else {
         # if entry contains a test without anchor but with the list of test cases, then
         # its file basename treated as a test group. Example:
         # lang/ICLS/icls007/icls00718/icls00718.html[runSignatureTest] => 
         # lang/ICLS/icls007/icls00718/icls00718[runSignatureTest]
         if ($entry =~ /\.html\[/) {
            $url =~ s|\.html||;
         }
         $url =~ s|[^/]*\.html\#||;
      }
      foreach(split /,/, $tcs) {
        printf "%-129s %s\n", "${url}[$_]", $tail;
      }
      if($tcs eq '') {           
        $url =~ s|\.html||;
        printf "%-129s %s\n", $url, $tail;
      }
    } else {
      die "Cannot parse EL entry:$_\n";
    }
  } else {
    die "Cannot parse EL entry:$_\n";
  }
}
