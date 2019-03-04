<?xml version="1.0" encoding="ISO-8859-1"?>
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

<xsl:stylesheet version="2.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:test="http://www.w3.org/XML/2004/xml-schema-test-suite/"
xmlns:xlink="http://www.w3.org/1999/xlink"
xmlns:xsd="http://www.w3.org/2001/XMLSchema"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xmlns:fn="http://www.w3.org/2005/xpath-functions"
>
    
    
<xsl:output indent="yes" doctype-system="testgenspec.dtd"
cdata-section-elements="Title Description"
/>
<xsl:strip-space elements="test:documentation"/>

<xsl:template match="/">
    <xsl:apply-templates/>
</xsl:template>

<xsl:template name="getFileName">
    <xsl:param name="strToParse"/>
    <xsl:choose>
        <xsl:when test="contains($strToParse, '/')">
            <xsl:call-template name="getFileName">
                <xsl:with-param name="strToParse">
                    <xsl:value-of select="substring-after($strToParse, '/')"/>
                </xsl:with-param>
            </xsl:call-template>
         </xsl:when>
         <xsl:otherwise>
            <xsl:value-of select="$strToParse"/>
         </xsl:otherwise>
    </xsl:choose>
</xsl:template>
<xsl:template name="getDirName">
    <xsl:param name="strToParse"/>
    <xsl:param name="fileName">
        <xsl:call-template name="getFileName">
            <xsl:with-param name="strToParse">
                <xsl:value-of select="substring-after($strToParse, '/')"/>
            </xsl:with-param>
        </xsl:call-template>
    </xsl:param>
    <xsl:value-of select="substring-before($strToParse, $fileName)"/>
</xsl:template>
<xsl:template name="error">
    <xsl:param name="msg"/>
    <xsl:message>
        ERROR: <xsl:value-of select="$msg"/>
    </xsl:message>
</xsl:template>
<xsl:template name="errorIfEmpty">
    <xsl:param name="whole"/>
    <xsl:param name="part"/>
    <xsl:if test="not(contains($whole, $part))">
        <xsl:call-template name="error">
            <xsl:with-param name="msg">
                <xsl:value-of select="$whole"/> does not contain <xsl:value-of select="$part"/>
            </xsl:with-param>
        </xsl:call-template>
    </xsl:if>
</xsl:template>


<xsl:template match="test:testSet">
    <xsl:element name="TestSet">
    <xsl:apply-templates/>
    </xsl:element>
</xsl:template>
<xsl:template match="test:testGroup">
    <xsl:param name="tgName">
        <xsl:value-of select="@name"/>
    </xsl:param>

    <xsl:param name="fileName">
        <xsl:call-template name="getFileName">
            <xsl:with-param name="strToParse" select="test:schemaTest/test:schemaDocument[1]/@xlink:href"/>
        </xsl:call-template>
    </xsl:param>
    <xsl:param name="dirName">
        <xsl:call-template name="getDirName">
            <xsl:with-param name="strToParse" select="test:schemaTest/test:schemaDocument[1]/@xlink:href"/>
        </xsl:call-template>
    </xsl:param>
    val=<xsl:value-of select="$dirName"/>
    val=<xsl:value-of select="$fileName"/>
    val=<xsl:value-of select="$dirName"/>/<xsl:value-of select="$mode"/>/<xsl:value-of select="$tgName"/>.test.xml
    
    <xsl:if test="test:schemaTest/test:schemaDocument/@xlink:href">
    <xsl:result-document href="{$dirName}/{$mode}/{$tgName}.test.xml">
    <!-- Do not remove the following string
    -->
    <xsl:element name="TestGroup">
        <xsl:attribute name="ID">
            <xsl:value-of select="@name"/>
        </xsl:attribute>
        <xsl:if test="test:annotation">
            <xsl:element name="TestGroupDocumentation">
                <xsl:element name="Title">
                    <xsl:if test="not(test:annotation/test:documentation/test:Title)">
                        <xsl:value-of select="$tgName"/>
                    </xsl:if>
                    <xsl:apply-templates select="test:annotation/test:documentation/test:Title"/>
                </xsl:element>
                <xsl:element name="Description">
        &lt;p&gt;
                    <xsl:apply-templates select="test:annotation/test:documentation/test:Description|test:annotation/test:documentation/text()"/>
        &lt;/p&gt;
                    <xsl:apply-templates select="test:documentationReference[@xlink:href]"/>
                </xsl:element>
            </xsl:element>
        </xsl:if>
        <xsl:element name="TestGroupAttributes">
            <xsl:element name="AttrElem">
                <xsl:attribute name="Name">scInfo</xsl:attribute>
                <xsl:text>@(#)main.xsl	1.1 07/05/28</xsl:text>
            </xsl:element>
            <xsl:element name="AttrElem">
                <xsl:attribute name="Name">testType</xsl:attribute>
                <xsl:value-of select="$testType"/>
            </xsl:element>
            <xsl:element name="TargetSpec">
                <xsl:attribute name="ID"><xsl:value-of select="$specId"/></xsl:attribute>
                <xsl:attribute name="Version"><xsl:value-of select="$specVersion"/></xsl:attribute>
            </xsl:element>
            <xsl:call-template name="keywords"/>
            <!--
            <xsl:element name="Keyword">java_to_schema</xsl:element>
            -->
        </xsl:element>

        <xsl:apply-templates select="test:schemaTest">
            <xsl:with-param name="dirName">
                <xsl:value-of select="$dirName"/>
            </xsl:with-param>
        </xsl:apply-templates>

        <xsl:choose>
            <xsl:when test="$mode='jaxb'">
                <xsl:apply-templates select="test:instanceTest[test:expected/@validity='valid'][1]">
                    <xsl:with-param name="dirName">
                        <xsl:value-of select="$dirName"/>
                    </xsl:with-param>
                </xsl:apply-templates>
            </xsl:when>
            <xsl:otherwise>
                <xsl:apply-templates select="test:instanceTest">
                    <xsl:with-param name="dirName">
                        <xsl:value-of select="$dirName"/>
                    </xsl:with-param>
                </xsl:apply-templates>
            </xsl:otherwise>
        </xsl:choose>
        
    <!-- External Data { -->
    <!--
    <xsl:call-template name="externals">
        <xsl:with-param name="fileName">
            <xsl:value-of select="test:schemaTest/test:schemaDocument/@xlink:href"/>
        </xsl:with-param>
        <xsl:with-param name="dirName">
            <xsl:value-of select="$dirName"/>
        </xsl:with-param>
    </xsl:call-template>
    <xsl:call-template name="externals">
        <xsl:with-param name="fileName">
            <xsl:choose>
                <xsl:when test="$mode='jaxb'">
                    <xsl:value-of select="test:instanceTest[test:expected/@validity='valid'][1]/test:instanceDocument/@xlink:href"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:value-of select="test:instanceTest/test:instanceDocument/@xlink:href"/>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:with-param>
        <xsl:with-param name="dirName">
            <xsl:value-of select="$dirName"/>
        </xsl:with-param>
    </xsl:call-template>
    -->
    <!-- } External Data -->
    
    </xsl:element>
    <!-- Ident: @(#)main.xsl	1.1 07/05/28 -->
    <xsl:comment> Ident: @(#)main.xsl	1.1 07/05/28 </xsl:comment>

    </xsl:result-document>
    <!-- Do not remove the following string
    -->
    </xsl:if>
</xsl:template>

<xsl:template name="externals">
    <xsl:param name="fileName"/>
    <xsl:param name="dirName"/>
    <xsl:param name="mainSchemas"/>
    <!-- rule for xsd:redefine, xsd:include, import and all others that have schemaLocation -->
    <xsl:for-each select="document($fileName)//@schemaLocation|document($fileName)//@xsi:schemaLocation|document($fileName)//@xsi:noNamespaceSchemaLocation">
        <xsl:if test="not(contains($mainSchemas, .))">
        <xsl:call-template name="parseExternals">
            <xsl:with-param name="fileName">
                <xsl:value-of select="."/>
            </xsl:with-param>
            <xsl:with-param name="dirName">
                <xsl:value-of select="$dirName"/>
            </xsl:with-param>
            <xsl:with-param name="mainSchemas">
                <xsl:value-of select="$mainSchemas"/>
            </xsl:with-param>
        </xsl:call-template>
        </xsl:if>
    </xsl:for-each>
</xsl:template>
<xsl:template name="parseExternals">
    <xsl:param name="fileName"/>
    <xsl:param name="dirName"/>
    <xsl:param name="mainSchemas"/>
    <xsl:choose>
        <xsl:when test="contains($fileName, ' ')">
            <!-- print ExternalData with substring-before($fileName, ' ') -->
            <xsl:call-template name="printExternal">
                <xsl:with-param name="fileName">
                    <xsl:value-of select="substring-before($fileName, ' ')"/>
                </xsl:with-param>
                <xsl:with-param name="dirName">
                    <xsl:value-of select="$dirName"/>
                </xsl:with-param>
                <xsl:with-param name="mainSchemas">
                    <xsl:value-of select="$mainSchemas"/>
                </xsl:with-param>
            </xsl:call-template>
            <!-- call the template with substring-after($fileName, ' ') -->
            <xsl:call-template name="parseExternals">
                <xsl:with-param name="fileName">
                    <xsl:value-of select="substring-after($fileName, ' ')"/>
                </xsl:with-param>
                <xsl:with-param name="dirName">
                    <xsl:value-of select="$dirName"/>
                </xsl:with-param>
                <xsl:with-param name="mainSchemas">
                    <xsl:value-of select="$mainSchemas"/>
                </xsl:with-param>
            </xsl:call-template>
        </xsl:when>
        <xsl:otherwise>
            <!-- print ExternalData with $fileName -->
            <xsl:call-template name="printExternal">
                <xsl:with-param name="fileName">
                    <xsl:value-of select="$fileName"/>
                </xsl:with-param>
                <xsl:with-param name="dirName">
                    <xsl:value-of select="$dirName"/>
                </xsl:with-param>
                <xsl:with-param name="mainSchemas">
                    <xsl:value-of select="$mainSchemas"/>
                </xsl:with-param>
            </xsl:call-template>
        </xsl:otherwise>
    </xsl:choose>
</xsl:template>
<xsl:template name="printExternal">
    <xsl:param name="fileName"/>
    <xsl:param name="dirName"/>
    <xsl:param name="mainSchemas"/>
    <xsl:if test="not(contains($mainSchemas, $fileName))">
    <xsl:if test="fn:doc-available(concat($dirName, '/', $fileName))">

    <!-- print ExternalData -->
    <xsl:element name="ExternalData">
        <xsl:attribute name="Type">iodata</xsl:attribute>
        <xsl:attribute name="SourceName">
            <xsl:value-of select="$urlCorrection"/><xsl:value-of select="$fileName"/>
        </xsl:attribute>
    </xsl:element>
    <!-- call "externals" with $dirName/$fileName -->
    <xsl:call-template name="externals">
        <xsl:with-param name="fileName">
            <xsl:value-of select="$dirName"/>/<xsl:value-of select="$fileName"/>
        </xsl:with-param>
        <xsl:with-param name="dirName">
            <xsl:value-of select="$dirName"/>
        </xsl:with-param>
        <xsl:with-param name="mainSchemas">
            <xsl:value-of select="concat($mainSchemas, $fileName)"/>
        </xsl:with-param>
    </xsl:call-template>
    </xsl:if>
    </xsl:if>
</xsl:template>

<xsl:template name="keywords">
    <xsl:if test="$mode = 'jaxp'">
        <xsl:element name="Keyword">runtime</xsl:element>
    </xsl:if>
</xsl:template>
<!--xsl:template match="test:testGroup/test:annotation/test:documentation/*"-->
<xsl:template match="test:documentation/*">
    <xsl:value-of select="."/>
</xsl:template>
<xsl:template match="test:documentationReference[@xlink:href]">
    <xsl:if test="string-length(@xlink:href) > 0">
        <!--
        &lt;a href="<xsl:value-of select="@xlink:href"/>"&gt;
            <xsl:value-of select="@xlink:href"/>
        &lt;/a&gt;
        -->
        &lt;p&gt;
          &lt;a href="<xsl:value-of select="@xlink:href"/>"&gt;
            <xsl:value-of select="@xlink:href"/>
          &lt;/a&gt;
        &lt;/p&gt;
    </xsl:if>
</xsl:template>
<xsl:template match="test:schemaDocument">
    <xsl:param name="dirName"/>
        <xsl:element name="ExternalSupportClass">
            <xsl:attribute name="SourceName">
                <xsl:value-of select="$urlCorrection"/>
                <!--xsl:value-of select="substring-after(test:schemaDocument/@xlink:href, $dirName)"/-->
                <xsl:value-of select="substring-after(@xlink:href, $dirName)"/>
            </xsl:attribute>
        </xsl:element>
    <xsl:call-template name="errorIfEmpty">
        <xsl:with-param name="whole">
            <xsl:value-of select="@xlink:href"/>
        </xsl:with-param>
        <xsl:with-param name="part">
            <xsl:value-of select="$dirName"/>
        </xsl:with-param>
    </xsl:call-template>
</xsl:template>

<xsl:template match="test:schemaTest">
    <xsl:param name="dirName"/>
    <xsl:element name="CodeSet">
        <xsl:call-template name="libs"/>
        <xsl:apply-templates select="test:schemaDocument">
            <xsl:with-param name="dirName"><xsl:value-of select="$dirName"/></xsl:with-param>
        </xsl:apply-templates>
        
    <!-- External Data { -->
    <xsl:for-each select="test:schemaDocument">
    <xsl:call-template name="externals">
        <xsl:with-param name="fileName">
            <xsl:value-of select="@xlink:href"/>
        </xsl:with-param>
        <xsl:with-param name="dirName">
            <xsl:value-of select="$dirName"/>
        </xsl:with-param>
    </xsl:call-template>
    </xsl:for-each>
    
    <xsl:choose>
        <xsl:when test="$mode='jaxb'">
            <xsl:for-each select="../test:instanceTest[test:expected/@validity='valid'][1]/test:instanceDocument">
                <xsl:call-template name="externals">
                    <xsl:with-param name="fileName">
                        <xsl:value-of select="@xlink:href"/>
                    </xsl:with-param>
                    <xsl:with-param name="dirName">
                        <xsl:value-of select="$dirName"/>
                    </xsl:with-param>
                    <xsl:with-param name="mainSchemas">
                        <xsl:value-of select="../../test:schemaTest/test:schemaDocument/@xlink:href"/>
                    </xsl:with-param>
                 </xsl:call-template>
            </xsl:for-each>
        </xsl:when>
        <xsl:otherwise>
            <xsl:for-each select="../test:instanceTest/test:instanceDocument">
                <xsl:call-template name="externals">
                    <xsl:with-param name="fileName">
                        <xsl:value-of select="@xlink:href"/>
                    </xsl:with-param>
                    <xsl:with-param name="dirName">
                        <xsl:value-of select="$dirName"/>
                    </xsl:with-param>
                    <xsl:with-param name="mainSchemas">
                        <xsl:value-of select="../../test:schemaTest/test:schemaDocument/@xlink:href"/>
                    </xsl:with-param>
                 </xsl:call-template>
            </xsl:for-each>
        </xsl:otherwise>
    </xsl:choose>
    <!-- } External Data -->
        
    </xsl:element>
    <xsl:if test="test:expected">
        <!-- Test Case for testing schema itself -->
        <xsl:element name="TestCase">
            <xsl:attribute name="ID"><xsl:value-of select="parent::test:testGroup/@name"/></xsl:attribute>
            <xsl:element name="TestCaseAttributes">
                <xsl:apply-templates select="test:expected"/>
            </xsl:element>
        </xsl:element>
    </xsl:if>
</xsl:template>
<xsl:template match="test:instanceTest">
    <!-- TestCase -->
    <xsl:param name="dirName"/>
    <xsl:element name="TestCase">
        <xsl:attribute name="ID"><xsl:value-of select="@name"/></xsl:attribute>
        <xsl:element name="TestCaseAttributes">
            <xsl:apply-templates select="test:expected"/>
        </xsl:element>
        <xsl:element name="CodeSet">
            <xsl:apply-templates select="test:instanceDocument">
                <xsl:with-param name="dirName"><xsl:value-of select="$dirName"/></xsl:with-param>
            </xsl:apply-templates>
        </xsl:element>
    </xsl:element>
</xsl:template>
<xsl:template match="test:instanceDocument">
    <xsl:param name="dirName"/>
    <xsl:element name="ExternalData">
        <xsl:attribute name="SourceName">
            <xsl:value-of select="$urlCorrection"/>
            <xsl:value-of select="substring-after(@xlink:href, $dirName)"/>
        </xsl:attribute>
        <xsl:attribute name="Type">iodata</xsl:attribute>
    </xsl:element>
    <xsl:call-template name="errorIfEmpty">
        <xsl:with-param name="whole">
            <xsl:value-of select="@xlink:href"/>
        </xsl:with-param>
        <xsl:with-param name="part">
            <xsl:value-of select="$dirName"/>
        </xsl:with-param>
    </xsl:call-template>
</xsl:template>
<xsl:template match="test:expected">
    <xsl:element name="AttrElem">
        <xsl:attribute name="Name">validity</xsl:attribute>
        <xsl:choose>
            <xsl:when test="@validity = 'valid'">1</xsl:when>
            <xsl:when test="@validity = 'invalid'">0</xsl:when>
            <xsl:when test="@validity = 'notKnown'">notKnown</xsl:when>
            <xsl:otherwise><xsl:value-of select="@test:validity"/></xsl:otherwise>
        </xsl:choose>
    </xsl:element>
</xsl:template>

</xsl:stylesheet>
