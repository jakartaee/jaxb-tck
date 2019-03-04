<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:tckutil="xalan://com.sun.xsl.tools.TckUtil"
  extension-element-prefixes="tckutil"
  version="1.0">

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
  <xsl:output method="xml" indent="yes" />

  <xsl:param name="test-path" />
  <xsl:param name="importFile" />
  <xsl:param name="propertyFile" />
  <xsl:param name="path-separator" />
  <xsl:param name="keywords-file" />
  <xsl:param name="build-dir" />
  <xsl:param name="jct-dir" />
  <xsl:variable name="testgroup">
    <xsl:value-of select="//TestGroup/@ID" />
  </xsl:variable>

  <xsl:variable name="sourcePath">
    <xsl:value-of select="//ExternalSupportClass/@SourceName"></xsl:value-of>
  </xsl:variable>

  <xsl:variable name="sourceName">
     <xsl:call-template name="last-string-component">
       <xsl:with-param name="strToConvert" select="$sourcePath" />
       <xsl:with-param name="delimiter">/</xsl:with-param>
     </xsl:call-template>
  </xsl:variable>

  <xsl:variable name="valid">
    <xsl:value-of
      select="count(//AttrElem[@Name='validity' and .='1'])" />
  </xsl:variable>
  <xsl:variable name="valid-xml-cases"
    select='//TestCase[count(.//AttrElem[@Name="validity" and .="1"])>0 and count(.//ExternalData[@Type="iodata"])>0]' />
  <xsl:variable name="package-name">
    <xsl:value-of select="tckutil:getPackage($test-path,$sourcePath)"/>
  </xsl:variable>


  <xsl:variable name="keywords" select="document($keywords-file, /)" />
  <xsl:template match="/">
    <project default="generate">
      <xsl:attribute name="name">
        <xsl:value-of select="//AttrElem[@Name='scInfo']"></xsl:value-of>
      </xsl:attribute>
      <property >
        <xsl:attribute name="file">
          <xsl:value-of select="$propertyFile"></xsl:value-of>
        </xsl:attribute>
      </property>
      <property name="source">
        <xsl:attribute name="value">
          <xsl:value-of select="$sourcePath"></xsl:value-of>
        </xsl:attribute>
      </property>
      <property name="testgroup">
        <xsl:attribute name="value">
          <xsl:value-of select="$testgroup"></xsl:value-of>
        </xsl:attribute>
      </property>
      <property name="packagename">
        <xsl:attribute name="value">
          <xsl:value-of select="$package-name" />
        </xsl:attribute>
      </property>
      <property name="testname">
        <xsl:attribute name="value">
          <xsl:value-of select="substring-before($sourceName,'.')" />
        </xsl:attribute>
      </property>

      <xsl:if
        test="boolean(//TargetSpec/@ID[.='JAXB_NOT_REQUIRED']) or ($valid=0 and count(//AttrElem[@Name='validity' and .='0'])>0) ">
        <property name="notrequired" value="true" />
      </xsl:if>

      <import>
        <xsl:attribute name="file">
          <xsl:value-of select="$importFile" />
        </xsl:attribute>
      </import>
      <target name="serialize.jbxml">
        <xsl:apply-templates select="$valid-xml-cases" />
      </target>
    </project>
  </xsl:template>

  <xsl:template name="lowercase">
    <xsl:param name="strToConvert" />
    <xsl:value-of
      select="translate($strToConvert,'ABCDEFGHIJKLMNOPQRSTUVWXYZ',
                                         'abcdefghijklmnopqrstuvwxyz')">
    </xsl:value-of>

  </xsl:template>

  <xsl:template name="string2package">
    <xsl:param name="strToConvert" />
    <xsl:value-of
      select="translate($strToConvert,'/-',
                                         '._')">
    </xsl:value-of>

  </xsl:template>

  <xsl:template name="last-string-component">
    <xsl:param name="strToConvert" />
    <xsl:param name="delimiter" />
    <xsl:choose>
      <xsl:when test="contains($strToConvert,$delimiter)">
        <xsl:call-template name="last-string-component">
          <xsl:with-param name="strToConvert"
            select="substring-after($strToConvert,$delimiter)" />
          <xsl:with-param name="delimiter" select="$delimiter" />
        </xsl:call-template>
      </xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="$strToConvert" />
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

  <xsl:template name="replace-keywords">
    <xsl:param name="keywords" />
    <xsl:param name="strToConvert" />
    <xsl:if test="not($keywords)">
      <xsl:value-of select="$strToConvert" />
    </xsl:if>
    <xsl:if test="$keywords">
      <xsl:variable name="current-keyword" select="$keywords[1]" />
      <xsl:choose>
        <xsl:when
          test="contains($strToConvert, concat('.',$current-keyword,'.'))">
          <xsl:call-template name="replace-keywords">
            <xsl:with-param name="keywords"
              select="$keywords[. != $current-keyword]" />
            <xsl:with-param name="strToConvert"
              select="concat(substring-before($strToConvert, $current-keyword), '_', $current-keyword, substring-after($strToConvert, $current-keyword))">
            </xsl:with-param>
          </xsl:call-template>
        </xsl:when>
        <xsl:otherwise>
          <xsl:call-template name="replace-keywords">
            <xsl:with-param name="keywords"
              select="$keywords[. != $current-keyword]" />
            <xsl:with-param name="strToConvert"
              select="$strToConvert">
            </xsl:with-param>
          </xsl:call-template>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:if>
  </xsl:template>

  <xsl:template match="TestCase">
    <xsl:variable name="xml-file-path"
      select=".//ExternalData/@SourceName" />
    <xsl:variable name="xml-file-name">
       <xsl:call-template name="last-string-component">
         <xsl:with-param name="strToConvert" select="$xml-file-path" />
         <xsl:with-param name="delimiter">/</xsl:with-param>
       </xsl:call-template>
    </xsl:variable>
    <jbxml-generate>
      <xsl:attribute name="package">
        <xsl:value-of select="$package-name" />
      </xsl:attribute>
      <xsl:attribute name="jbfile">
        <xsl:call-template name="last-string-component">
          <xsl:with-param name="strToConvert" select="$package-name" />
          <xsl:with-param name="delimiter">.</xsl:with-param>
        </xsl:call-template>
        <xsl:value-of select="concat('-',$xml-file-name,'.jb.xml')" />
      </xsl:attribute>
      <xsl:attribute name="xmlfile">
        <xsl:value-of select="$xml-file-path" />
      </xsl:attribute>
      <xsl:attribute name="compilepath">
        <xsl:text>${compile.dir}</xsl:text>
      </xsl:attribute>
    </jbxml-generate>
  </xsl:template>


</xsl:stylesheet>
