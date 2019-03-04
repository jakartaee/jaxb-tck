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
>
    
    <xsl:variable name="mode">jaxp</xsl:variable>
    <xsl:variable name="testType">XMLSchemaTest</xsl:variable>
    <xsl:variable name="specId">XMLSchema</xsl:variable>
    <xsl:variable name="specVersion">1.0-</xsl:variable>
    <xsl:variable name="urlCorrection">../</xsl:variable>
    
    <xsl:template name="libs">
        <xsl:element name="Dependency">
            <xsl:element name="Lib">xml_schema.TestRun</xsl:element>
        </xsl:element>
    </xsl:template>

    <xsl:include href="main.xsl"/>
    
    

    

</xsl:stylesheet>
