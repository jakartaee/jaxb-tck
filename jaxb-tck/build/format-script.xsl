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

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="text"/>

<xsl:template match="/">
    <xsl:text>&lt;?xml version="1.0" encoding="UTF-8"?&gt;&#x0A;</xsl:text>
    <xsl:apply-templates/>
    <xsl:text>&#x0A;</xsl:text>
</xsl:template>

<xsl:template match="*">    
    <!-- Indent to appripriate level -->
    <xsl:call-template name="indent"/>

    <!-- Make element node -->
    <xsl:text>&lt;</xsl:text>
    <xsl:value-of select="name()"/>

    <!-- Make attributes -->
    <xsl:if test="count(@*) > 0">
        <!-- There are attribute nodes -->
        <xsl:for-each select="@*">
            <xsl:if test="position() > 1">
                <xsl:call-template name="indent"/>
                <xsl:text>    </xsl:text>
            </xsl:if>
            <xsl:text> </xsl:text>
            <xsl:value-of select="name()"/>
            <xsl:text>="</xsl:text>
            <xsl:call-template name="replace-escape-symbols">
                <xsl:with-param name="string" select="."/>
            </xsl:call-template>
            <xsl:text>"</xsl:text>
        </xsl:for-each>
    </xsl:if>

    <!-- Make inner elements recursively -->
    <xsl:choose>
        <xsl:when test="(count(*) > 0) or (normalize-space(text()) != '')">
            <!-- There were inner elements -->
            <xsl:text>&gt;</xsl:text>

            <!-- Make inner elements recursively -->
            <xsl:apply-templates/>

            <xsl:if test="count(*) > 0">
                <xsl:call-template name="indent"/>
            </xsl:if>

            <xsl:text>&lt;/</xsl:text>
            <xsl:value-of select="name()"/>
            <xsl:text>&gt;</xsl:text>
        </xsl:when>
        <xsl:otherwise>
            <!-- There were no inner elements -->
            <xsl:text>/&gt;</xsl:text>
        </xsl:otherwise>
    </xsl:choose>
</xsl:template>

<xsl:template match="text()">
    <!-- Discard all text :) -->
    <xsl:variable name="text" select="normalize-space(.)"/>
    <xsl:if test="$text != ''">
        <xsl:call-template name="indent"/>
        <xsl:call-template name="replace-escape-symbols">
            <xsl:with-param name="string" select="$text"/>
        </xsl:call-template>
    </xsl:if>
</xsl:template>

<xsl:template match="comment()">
    <xsl:call-template name="indent"/>
    <xsl:text>&lt;!--</xsl:text>
    <xsl:value-of select="."/>
    <xsl:text>--&gt;</xsl:text>
</xsl:template>

<xsl:template name="replace-escape-symbols">
    <xsl:param name="string"/>
    <xsl:choose>
        <xsl:when test="contains($string, '&amp;')">
            <xsl:call-template name="replace-escape-symbols">
                <xsl:with-param name="string" select="substring-before($string, '&amp;')"/>
            </xsl:call-template>
            <xsl:text>&amp;amp;</xsl:text>
            <xsl:call-template name="replace-escape-symbols">
                <xsl:with-param name="string" select="substring-after($string, '&amp;')"/>
            </xsl:call-template>
        </xsl:when>
        <xsl:when test="contains($string, '&lt;')">
            <xsl:call-template name="replace-escape-symbols">
                <xsl:with-param name="string" select="substring-before($string, '&lt;')"/>
            </xsl:call-template>
            <xsl:text>&amp;lt;</xsl:text>
            <xsl:call-template name="replace-escape-symbols">
                <xsl:with-param name="string" select="substring-after($string, '&lt;')"/>
            </xsl:call-template>
        </xsl:when>
        <xsl:when test="contains($string, '&gt;')">
            <xsl:call-template name="replace-escape-symbols">
                <xsl:with-param name="string" select="substring-before($string, '&gt;')"/>
            </xsl:call-template>
            <xsl:text>&amp;gt;</xsl:text>
            <xsl:call-template name="replace-escape-symbols">
                <xsl:with-param name="string" select="substring-after($string, '&gt;')"/>
            </xsl:call-template>
        </xsl:when>
        <xsl:otherwise>
            <xsl:value-of select="$string"/>
        </xsl:otherwise>
    </xsl:choose>
</xsl:template>

<xsl:template name="indent">
    <!-- Enter + indent -->
    <xsl:text>&#x0A;</xsl:text>
    <xsl:call-template name="recursive-indent">
        <xsl:with-param name="node" select=".."/>
    </xsl:call-template>
</xsl:template>

<xsl:template name="recursive-indent">
    <!-- Recursive indent indent -->
    <xsl:param name="node"/>
    <xsl:if test="name($node) != ''">
        <xsl:text>    </xsl:text>
        <xsl:call-template name="recursive-indent">
            <xsl:with-param name="node" select="$node/.."/>
        </xsl:call-template>
    </xsl:if>
</xsl:template>

</xsl:stylesheet>
