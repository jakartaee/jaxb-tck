@echo off
REM 
REM  Copyright (c) 2011, 2018 Oracle and/or its affiliates. All rights reserved.
REM
REM  This program and the accompanying materials are made available under the
REM  terms of the Eclipse Public License v. 2.0, which is available at
REM  http://www.eclipse.org/legal/epl-2.0.
REM
REM  This Source Code may also be made available under the following Secondary
REM  Licenses when the conditions for such availability set forth in the
REM  Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
REM  version 2 with the GNU Classpath Exception, which is available at
REM  https://www.gnu.org/software/classpath/license.html.
REM
REM  SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
REM

if "%JAVA_HOME%" == "" (
    echo JAVA_HOME must be set before running this script
    exit 1
)

if "%JAXB_HOME%" == "" (
    echo JAXB_HOME must be set before running this script
    exit 1
)

set SCHEMAGEN_REALIZATION=com.sun.jaxb_tck.lib.SchemaGen
set TCK_HOME=%~dp0..\..
set CLASSPATH=%TCK_HOME%\classes;%TCK_HOME%\lib\javatest.jar;%JAVA_HOME%\lib\tools.jar;%TCK_HOME%\lib\jtjck.jar

rem if "%TCK_JAXB_OPTIONS%" == "" (
    rem set TCK_JAXB_OPTIONS= --add-modules jdk.xml.bind --upgrade-module-path %JAXB_HOME%\lib\jaxb-api.jar
rem )

rem if "%TCK_JAXB_OTHER_OPTIONS%" == "" (
    rem set TCK_JAXB_OTHER_OPTIONS= --add-modules jdk.xml.bind --upgrade-module-path %JAXB_HOME%\lib\jaxb-api.jar
rem )

rem Invoke schemagen tool provided by JAXB RI via java interface
echo %JAVA_HOME%\bin\java.exe %TCK_JAXB_OPTIONS% -cp %CLASSPATH%;%JAXB_HOME%\mod\jaxb-jxc.jar com.sun.jaxb_tck.lib.JaxbCommand %TCK_JAXB_OTHER_OPTIONS% -jxc %SCHEMAGEN_REALIZATION% - %*
"%JAVA_HOME%\bin\java.exe" %TCK_JAXB_OPTIONS% -cp %CLASSPATH%;%JAXB_HOME%\mod\jaxb-jxc.jar com.sun.jaxb_tck.lib.JaxbCommand %TCK_JAXB_OTHER_OPTIONS% -jxc %SCHEMAGEN_REALIZATION% - %*

exit %ERRORLEVEL%
