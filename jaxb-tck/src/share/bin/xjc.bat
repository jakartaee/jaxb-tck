@echo off
REM
REM  Copyright (c) 2007, 2018 Oracle and/or its affiliates. All rights reserved.
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

set SCHEMACOMPILER_REALIZATION=com.sun.jaxb_tck.lib.SchemaCompiler

if "%JAXB_HOME%" == "" (
   echo JAXB_HOME must be set before running this script
)

if "%JAXB_ENDORSED%" == "" (
   echo JAXB_ENDORSED must be set before running this script
)

echo %JAVA_HOME%\bin\java -Djava.endorsed.dirs=%JAXB_ENDORSED% -cp %CLASSPATH% com.sun.jaxb_tck.lib.JaxbCommand -xjc %SCHEMACOMPILER_REALIZATION%  - %* 
%JAVA_HOME%\bin\java -Djava.endorsed.dirs=%JAXB_ENDORSED% -cp %CLASSPATH% com.sun.jaxb_tck.lib.JaxbCommand -xjc %SCHEMACOMPILER_REALIZATION%  - %* 

exit %ERRORLEVEL%
