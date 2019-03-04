<?xml version="1.0" encoding="UTF-8"?>
<!--

    Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.

    This program and the accompanying materials are made available under the
    terms of the Eclipse Public License v. 2.0, which is available at
    http://www.eclipse.org/legal/epl-2.0.

    This Source Code may also be made available under the following Secondary
    Licenses when the conditions for such availability set forth in the
    Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
    version 2 with the GNU Classpath Exception, which is available at
    https://www.gnu.org/software/classpath/license.html.

    SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0

-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:redirect="http://xml.apache.org/xalan/redirect"
    extension-element-prefixes="redirect"
    version="1.0">

  <xsl:output  method="xml" indent="yes" />

  <xsl:param name="filePrefix" />
  <xsl:param name="importFile" />
  <xsl:param name="dirName" />
  
  <xsl:variable name="threadsPerProcessor" select="//parallel/@threadsPerProcessor" />
  <xsl:variable name="failonany" select="//parallel/@failonany"/>
  <xsl:variable name="forkantNodes" select="//parallel/forkant" />
  <xsl:variable name="elemCount" select="count($forkantNodes)"/>
  <xsl:variable name="numberOfParts" select="(($elemCount - ($elemCount mod 1000)) div 1000)+1"></xsl:variable>

  <xsl:template match="/" >
               <xsl:text >
      Version :  </xsl:text>
               <xsl:value-of select = "system-property('xsl:version')" />
               <xsl:text >
        Vendor :  </xsl:text>
               <xsl:value-of select = "system-property('xsl:vendor')" />
               <xsl:text >
        URL :  </xsl:text>
               <xsl:value-of select = "system-property('xsl:vendor-url')" />
               <xsl:text >
        Number of parts :  </xsl:text>
               <xsl:value-of select = "$numberOfParts" />
      <xsl:call-template name="write-part">
        <xsl:with-param name="partsCounter" select="0" />
      </xsl:call-template>
  </xsl:template>

  <xsl:template name="write-part">
    <xsl:param name="partsCounter" />
    <xsl:if test="$partsCounter &lt; $numberOfParts">
    <xsl:variable name="fileName">
      <xsl:value-of select="concat($dirName,'/',$filePrefix,'-',string($partsCounter),'.part.xml')"></xsl:value-of>
    </xsl:variable>
    <xsl:message>
      <xsl:value-of select="$fileName"></xsl:value-of>
    </xsl:message>
    <redirect:write file="{$fileName}">
      <project name="GenProject" default="bunch">
        <property file="build.properties" />
        <import>
          <xsl:attribute name="file">
            <xsl:value-of select="$importFile"></xsl:value-of>
          </xsl:attribute>
        </import>
        <target name="bunch">
          <parallel>
            <xsl:attribute name="threadsPerProcessor">
              <xsl:value-of select="$threadsPerProcessor"></xsl:value-of>
            </xsl:attribute>
            <xsl:attribute name="failonany">
              <xsl:value-of select="$failonany"></xsl:value-of>
            </xsl:attribute>
          <xsl:copy-of select="$forkantNodes[(position() &gt;= ($partsCounter * 1000)) and (position() &lt; (($partsCounter + 1) * 1000))]"></xsl:copy-of>
          </parallel>
        </target>
      </project>
    </redirect:write>
      <xsl:call-template name="write-part">
        <xsl:with-param name="partsCounter" select="$partsCounter + 1" />
      </xsl:call-template>
    </xsl:if>
  </xsl:template>
</xsl:stylesheet>
