#!/bin/bash -x
#
# Copyright (c) 2019 Oracle and/or its affiliates. All rights reserved.
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


if [ -z "$ANT_HOME" ]; then
  export ANT_HOME=/usr/share/ant/
fi

if [ -z "$JAVA_HOME" ]; then
  export JAVA_HOME=/opt/jdk1.8.0_171
fi

export PATH=$JAVA_HOME/bin:$ANT_HOME/bin:$PATH

cd $WORKSPACE
export BASEDIR=`pwd`

if [ -z "$GF_HOME" ]; then
  export GF_HOME=$BASEDIR
fi

echo "ANT_HOME=$ANT_HOME"
echo "export JAVA_HOME=$JAVA_HOME"
echo "export MAVEN_HOME=$MAVEN_HOME"
echo "export PATH=$PATH"

mkdir -p ${HOME}/.m2

cd $WORKSPACE
WGET_PROPS="--progress=bar:force --no-cache"
if [ -z "$JAF_BUNDLE_URL" ];then
  export JAF_BUNDLE_URL=http://central.maven.org/maven2/com/sun/activation/jakarta.activation/1.2.1/jakarta.activation-1.2.1.jar
fi
#wget $WGET_PROPS $JAF_BUNDLE_URL -O jakarta.activation.jar

cd $BASEDIR
if [ -z "$GF_BUNDLE_URL" ]; then
  echo "Using default url for GF bundle: $DEFAULT_GF_BUNDLE_URL"
  export GF_BUNDLE_URL=$DEFAULT_GF_BUNDLE_URL
fi
#wget $WGET_PROPS $GF_BUNDLE_URL -O latest-glassfish.zip
#unzip -o latest-glassfish.zip
#ls -l $GF_HOME/glassfish5/glassfish/

#if [ ! -z "$GF_VERSION_URL" ]; then
#  wget --progress=bar:force --no-cache $GF_VERSION_URL -O glassfish.version
#  cat glassfish.version
#fi

echo "jaxb-tck is coming soon" > filename.txt
zip -r jaxb-tck-2.3_latest.zip filename.txt
mkdir -p ${WORKSPACE}/bundles
chmod 777 ${WORKSPACE}/*.zip
for entry in `ls jaxb*.zip`; do
  #date=`echo "$entry" | cut -d_ -f2`
  #strippedEntry=`echo "$entry" | cut -d_ -f1`
  #echo "copying ${WORKSPACE}/$entry to ${WORKSPACE}/bundles/${strippedEntry}_latest.zip"
  #cp ${WORKSPACE}/$entry ${WORKSPACE}/bundles/${strippedEntry}_latest.zip
  #chmod 777 ${WORKSPACE}/bundles/${strippedEntry}_latest.zip
  cp ${WORKSPACE}/$entry ${WORKSPACE}/bundles/
done
