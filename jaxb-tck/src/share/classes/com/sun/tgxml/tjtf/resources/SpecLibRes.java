/*
 * Copyright (c) 2002, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package com.sun.tgxml.tjtf.resources; 

import java.util.ListResourceBundle;

public class SpecLibRes extends ListResourceBundle {

    public Object[][] getContents() {
        return contents;
    }

    static final Object[][] contents = {


        // ---------------------------------------------------------------------------------
        //   Build Version
        // ---------------------------------------------------------------------------------
        {"speclib.version", "1.0n, (1/13/2000)"},

        // ---------------------------------------------------------------------------------
        //   API Messages
        // ---------------------------------------------------------------------------------

        {"api.tg.adddup.lib", "Duplicate Library {0}:{1}."},
        {"api.tg.adddup.tc", "Duplicate TestCase {0}:{1}."},
        {"api.tg.id.null", "Null ID."},
        {"api.tg.lib.varid.null", "Null Library VarID."},
        {"api.tg.tcid.null", "Null TestCase ID."},
        {"api.tg.libid.null", "Null Library ID."},
        {"api.tg.tc.varid.null", "Null TestCase VarID."},
        {"api.varid.null", "Null VarOrder."},
        {"api.varid.toosmall", "VarOrder is too small: {0} (minimal {1})"},
        {"api.varid.toolarge", "VarOrder is too large: {0} (maximum {1})"},
        {"api.varid.toomanydigits", "too many digits after decimal point: {0} (maximum {1})"},
        {"api.varid.cannotparse", "cannot parse VarID {0}"},
        {"api.varid.missedcolon", "colon sign missed in varID: {0}"},
        {"api.code.targetspec.vers.ill", "Illegal TargetSpec version:{0}."},
        {"api.code.targetspec.vers.numb.ill", "Illegal TargetSpec version number-token: {0}."},

        {"api.attributes.testattributes.timeout.bad", "Illegal timeout number-token: {0}."},
        {"api.attributes.testattributes.timeout.neg", "Illegal negative timeout number: {0}."},

        {"api.doc.assertionref.id.null", "Null Assertion Ref."},
        {"api.doc.assertionref.id.invalid", "Invalid Assertion Ref: {0}."},
        {"api.doc.inlineassertion.value.null", "Null InlineAssertion text."},
        {"api.doc.expectresval.value.null", "Null ExpectedResultValue value."},
        {"api.doc.expectressideeffect.value.null", "Null ExpectedResultSideEffect value."},
        {"api.doc.expectresexcpt.value.null", "Null ExpectedResultException value."},
        {"api.code.libdep.id.null", "Null Library ID."},
        {"api.namevalpair.name.null", "Null name."},
        {"api.namevalpair.val.null", "Null value."},

        // ---------------------------------------------------------------------------------
        //   Generic Parser Messages
        // ---------------------------------------------------------------------------------
        {"parser.error.internal.config", "Internal Error: Could not create XML parser."},
        {"parser.error.invalidroot", "Configuration Error: Invalid root directory {0} for path {1}."},

        {"parser.error.generic", "** Error: line: {0}, uri {1}  "},
        {"parser.warn.generic", "** Warning: line: {0}, uri {1}  "},
        {"parser.error.parsing", "** Parsing: line: {0}, uri {1}  "},
        {"parser.error.handler", "{0}{1}(File: {2}; Line: {3}){4}"},
        {"parser.error.handler1", "{0} ** Parsing Error (File: {1}; Line: {2}){3}    {4}{5}"},

        {"parser.warning.inner", "Line={0}{1}     (DTD={2}){3} Warning:  {4}"},
        {"parser.error.inner", "Line={0}{1}     (DTD={2}){3} Error:  {4}"},
        {"parser.fatal.inner", "Fatal Error:  Line={0}{1}"},

        // ---------------------------------------------------------------------------------
        //   Parse Messages
        // ---------------------------------------------------------------------------------
        {"parser.error.taghandlertable.nullentry", "Program Error: Tried to enter a null TagHandler into TagHandlerTable."},
        {"parser.error.taghandlertable.nullname", "Program Error: Tried to enter a TagHandler with a null name into TagHandlerTable."},
        {"parser.error.taghandlertable.dupentry", "Program Error: Tried to enter a TagHandler with a duplicate name ({0}) into TagHandlerTable."},

        {"parser.error.taghandlertable.nullfetch", "Program Error: Tried to get a TagHandler with a null name."},

        {"parser.error.nullstring", "Expected a non-null string value for {0}."},

        {"parser.error.multipleRoot", "A root tag has already been defined."},
        {"parser.error.nullstackitem", "Stack contained a null item."},
        {"parser.error.nonemptystack", "Stack should be empty."},
        {"parser.error.tg.nonemptystack", "Tried to push TestGroup onto a non-empty stack."},
        {"parser.error.tg.library.dupvariant", "TestGroup has a duplicate Library variant - ID: {0}, VarID: {1}."},
        {"parser.error.tg.tc.dupvariant", "TestGroup has a duplicate TestCase variant - ID: {0}, VarID: {1}."},
        {"parser.error.lib.nonemptystack", "Tried to push Library onto a non-empty stack."},
        {"parser.error.emptystack.push", "Tried to push entity onto an empty stack."},
        {"parser.error.emptystack.pop", "Tried to pop entity from an empty stack."},
        {"parser.error.inconsistentstack", "Tried to pop an entity that does not match begin tag."},
        {"parser.error.unknownTDAttr", "Attribute is unknown in this context: {0}."},

        {"parser.error.tc.nontg", "Tried to put TestCase into something other than a TestGroup."},
       
        {"parser.error.header", "Parsing Errors: {0}    File: {1}{2}   (DTD: {3}){4}"},
        {"parser.error.syntax.linecol", "  Line: {0} (Col: {1}),{2}   Error: {3}{4}"},
        {"parser.error.syntax.line", "  Line: {0},{1}   Error: {2}{3}"},
        {"parser.error.taghandlertable.undefTag", "Illegal entity: <{0}>."},
        {"parser.error.taghandlertable.undefTagEnd", "Illegal entity: </{0}>."},
        {"parser.error.unknownSpecAttr", "Illegal attribute: {0}."},
        {"parser.error.code.supportclass.inl.nullClassID", "InlineSupportClass was given a Null ClassID."},
        {"parser.error.code.supportclass.ext.nullClassID", "ExternalSupportClass was given a Null ClassID."},
        {"parser.error.invcontext", "Tried to put {0} into something other than a {1}."},
        {"parser.error.invcontext2", "Tried to put {0} into something other than a {1} or {2}."},
        {"parser.error.invcontext3", "Tried to put {0} into something other than a {1}, {2}, or {3}."},
        {"parser.error.invcontext4", "Tried to put {0} into something other than a {1}, {2}, {3}, or {4}."},
        {"parser.error.invcontext5", "Tried to put {0} into something other than a {1}, {2}, {3}, {4}, or {5}."},
        {"parser.error.inconsistentstack", "Inconsistent Stack, expected a {0}."},
        {"parser.error.text.null", "Null Text flow, \"{0}\" requires a text value."},

        {"parser.error.attribute.enum.illval", "Illegal \"{0}\" attribute value: {1}."},
        {"parser.error.nullpath", "Null filepath specified."},

        {"parser.altparser.error.missing", "Missing alternate-parser class argument."},
        {"parser.altparser.error.class.missing", "Alternate-parser class: \"{0}\" is not in the classpath."},


        {"parser.error.token.extra", "Single token expected: got \"{0}\"."},

        // ---------------------------------------------------------------------------------
        //   Emitter Messages
        // ---------------------------------------------------------------------------------
        {"emitter.error.taghandlertable.undefTag", "Illegal entity: <{0}>."},
        {"emitter.error.invObj", "Emitter error: Expected class {0}, cannot cast {1}."},
        {"emitter.error.text.null", "Null Text flow, \"{0}\" requires a text value."},

        // ---------------------------------------------------------------------------------
        //   Validator Messages
        // ---------------------------------------------------------------------------------
        {"nullvalidator.error.irobj.null", "Tried to validate a null tree."},
        {"nullvalidator.error.type.notaccept", "Validator can not accept tree whose root is type \"{0}\"."},

        {"baseutdvalidator.error.variant.duplicate", "Duplicate {0} variant: ID: \"{1}\" VarID: \"{2}\" ."},
        {"baseutdvalidator.error.tg.id.null", "Null {0} ID."},
        {"baseutdvalidator.error.tg.id.invalid", "Invalid {0} ID: \"{1}\"."},
        {"baseutdvalidator.error.tg.varid.invalid", "Invalid {0} VarID: \"{1}\"."},
        {"baseutdvalidator.error.assertionref.id.invalid", "Invalid AssertionRef ID: \"{0}\"."},
        {"baseutdvalidator.error.testedpackage.invalidjavaident", "Invalid Java identifier for package name: \"{0}\"."},
        {"baseutdvalidator.error.testedclass.invalidjavaident", "Invalid Java identifier for class name: \"{0}\"."},
        {"baseutdvalidator.error.mapping.invalid", "Invalid {0} name-value pair: name=\"{1}\" value=\"{2}\"."},
        {"baseutdvalidator.error.libdep.ID.invalid", "Invalid LibraryDependency ID: \"{0}\"."},
        {"baseutdvalidator.error.libdep.ID.null", "Null LibraryDependency ID."},
        {"baseutdvalidator.error.import.name.invalid", "Invalid Import string: \"{0}\"."},
        {"baseutdvalidator.error.baseclass.name.invalid", "Invalid BaseClass name: \"{0}\"."},
        {"baseutdvalidator.error.export.name.invalid", "Invalid Export string: \"{0}\"."},
        {"baseutdvalidator.error.sourcelang.invalid", "Invalid SourceLang type: \"{0}\"."},
        {"baseutdvalidator.error.sourcename.invalid", "Invalid SourceName file: \"{0}\"."},
        {"baseutdvalidator.error.sourcename.null", "Null SourceName."},
        {"baseutdvalidator.error.input.name.invalid", "Invalid Input name: \"{0}\"."},
        {"baseutdvalidator.error.keyword.invalid", "Invalid Keyword: \"{0}\"."},
        {"baseutdvalidator.error.timeout.invalid", "Invalid Timeout: \"{0}\"."},

        // ---------------------------------------------------------------------------------
        //   Dependence Analyzer Messages
        // ---------------------------------------------------------------------------------
        {"dependenceanalyzer.warning.dependency.cyclic", "Warning: Cyclic dependency: "},
        {"dependenceanalyzer.warning.dependency.no", "Warning: No dependencies"},
        {"dependenceanalyzer.warning.library.duplicate", "Warning: Duplicate library: "},
        {"dependenceanalyzer.warning.library.no.starting", "Warning: No starting libraries"},
        {"dependencednalyzertool.error.args.dependency.file.not.specified", "dependencies_file_name is not specified."},
        {"dependencednalyzertool.error.args.dependency.file.not.specified.d", "Specify dependencies_file_name after -d option."},
        {"dependencednalyzertool.error.args.dependency.file.specified.twice", "dependencies_file_name is specified twice."},
        {"dependencednalyzertool.error.args.library.file.not.specified.l", "Specify library_list_file after -l option."},
        {"dependencednalyzertool.error.args.library.file.specified.twice", "library_list_file is specified twice."},
        {"dependencednalyzertool.error.args.library.not.specified", "Neither library_list_file nor LibID is specified."},
        {"dependencednalyzertool.title1", "Usage: {0} [<options>] [LibID ...]" },
        {"dependencednalyzertool.title2", "  -d dependencies_file_name     name of file with library"},
        {"dependencednalyzertool.title3", "                                dependecies"},
        {"dependencednalyzertool.title4", "  -l library_list_file          name of file with a list"},
        {"dependencednalyzertool.title5", "                                of libraries"},
        {"dependencednalyzertool.title6", "  LibID                         library identifier"},

        {"dependencednalyzertool.usage.header", "Usage: {0} [<options>] [lib1 lib2 ...] {1} where options include:"},
        {"dependencednalyzertool.option.d.mnem", "-d"},
        {"dependencednalyzertool.option.d.desc", "  -d <filename>  dependencies file name (obligatory)"},
        {"dependencednalyzertool.option.l.mnem", "-l"},
        {"dependencednalyzertool.option.l.desc", "  -l <fillname>  library list file"},
        {"dependencednalyzertool.operands.0", "Operands: "},
        {"dependencednalyzertool.operands.1", "  lib1 lib2 ... "},
        {"dependencednalyzertool.option.missed.0", "Specify dependencies_file_name after {0} option"},
        {"dependencednalyzertool.option.missed.1", "Specify library_list_file_name after {0} option"},


        {"jckvalidator.error.missingcomp", "Invalid {1}, {0} requires {1} to contain a {2}."},
        {"jckvalidator.error.needs1comp", "Invalid {1}, {0} requires {1} to contain at least one {2}."},
        {"jckvalidator.error.needsonlycomp", "Invalid {1}, {0} requires {1} to contain classes of type {2} only."},
        {"jckvalidator.error.inlinedata.targtype", "Both TargetName and Type need to be defined, or both need to be null."},


        // ---------------------------------------------------------------------------------
        //   Visitor Error Messages
        // ---------------------------------------------------------------------------------
        {"visitor.nullObj", "Tried to visit a null object."},
        {"visitor.illObj", "Visitor does not handle a {0}."},

        // ---------------------------------------------------------------------------------
        //   SpecFile Error Messages
        // ---------------------------------------------------------------------------------
        {"specfile.error.illFileSpec", "invalid File Spec: {0}."},
        {"specfile.error.nullRootPack", "null root package."},
        {"specfile.error.illVersNum", "invalid version number: {0}."},
        {"specfile.error.illSpecType", "invalid spec type: {0}."},
        {"specfile.error.illMajVers", "invalid spec (Major) version number: {0}."},
        {"specfile.error.illMinVers", "invalid spec (Minor) version number: {0}."},
        {"specfile.error.illDotVers", "invalid spec (dot) version number: {0}."},


        // ---------------------------------------------------------------------------------
        //   PackageRef Error Messages
        // ---------------------------------------------------------------------------------
        {"packageref.error.dupPack", "Duplicate Package: {0}."},
        {"packageref.error.dupClass", "Duplicate Class: {0}."},


        // ---------------------------------------------------------------------------------
        //   FileEntry Error Messages
        // ---------------------------------------------------------------------------------
        {"file.error.ioerror", "IOError: {0}."},
        {"file.error.illSpecPath", "Invalid specpath: {0}."},
        {"file.error.notCreateDir", "Could not create directory: {0}."},
        {"file.error.illJarPath", "Invalid jar path: {0}."},
        {"file.error.illPath", "Invalid  path: {0}."},
        {"file.error.illXMLSpecPath", "Invalid XML spec path: {0}."},
        {"file.error.notOpenXMLFile", "Could not open XML file: {0}."},
        {"path.error.notOpenSpecFile", "Could not open spec file: {0}."},



        // ---------------------------------------------------------------------------------
        //    Default Tool-BaseClass messages
        // ---------------------------------------------------------------------------------
        {"parsestr.lineval", "line: {0}: error: "},
        {"basetool.outofmem", "{0}: Out of memory, try expanding the heap to {1}M."},

        {"basetool.err.unknownarg", "{0}: Unknown Argument {1}."},
        {"basetool.err.badarg", "{0}: Bad argument {1}."},
        {"basetool.version.message.default", "   {0}: Version {1}, Copyright (c) 1994, 2018 Oracle and/or its affiliates. All rights reserved."},
        {"basetool.name", "ToolBase"},
        {"basetool.version", "1.0 (alpha)"},


        {"basetool.usage", "Usage: {0} [<options>]  "},
        {"basetool.usage1", "where options include:"},
        {"basetool.usage2", "  -help                         print this message"},
        {"basetool.usage3", "  -version                      print the build version"},
        {"basetool.verbose.usage", "  -verbose                      print verbose information."},

        {"basetool.helparg.mnem", "-help"},
        {"basetool.helparg.mnem1", "-h"},
        {"basetool.helparg.mnem2", "-?"},
        {"basetool.helparg.mnem3", "-/help"},
        {"basetool.helparg.mnem4", "-/h"},
        {"basetool.helparg.mnem5", "/?"},
        {"basetool.helparg.mnem6", "?"},
        {"basetool.verbosearg.mnem", "-v"},
        {"basetool.versionarg.mnem", "-version"},
        {"basetool.loggingarg.mnem", "-log"},
        {"basetool.logfilearg.mnem", "-logfile"},

        {"basetool.helparg.desc", " print this message"},
        {"basetool.verbosearg.desc", "  verbose mode"},
        {"basetool.versionarg.desc", "  print version info"},
        {"basetool.loggingarg.desc", "  enable logging"},
        {"basetool.logfilearg.desc", " <logfile>   log file argument"},


        {"basetool.log.cantread", "{0}: Can not read logfile: \"{1}\"."},
        {"basetool.log.cantwrite", "{0}: Can not write logfile: \"{1}\"."},
        {"basetool.log.retry.exceeded", "{0}: Can not write logfile (max retry exceeded): \"{1}\"."},

        {"xmlbasetool.name", "XMLToolBase"},
        {"xmlbasetool.filearg.mnem", "-file"},
        {"xmlbasetool.filearg.desc", "  -file <xml filename>  input xml file name (obligatory) "},
        {"xmlbasetool.error.dtd.linecol", "{0} ** DTD Error (DTD: {1}; Line: {2}; Column: {3}){4}    {5}{6}"},
        {"xmlbasetool.error.dtd.line", "{0} ** DTD Error (DTD: {1}; Line: {2}){3}    {4}{5}"},
        {"xmlbasetool.error.xml.linecol", "{0} Parsing Error (File: {1}; Line: {2}; Column: {3}){4}    {5}{6}"},
        {"xmlbasetool.error.xml.line", "{0} Parsing Error (File: {1}; Line: {2}){3}    {4}{5}"},
        {"xmlbasetool.error.xml", "{0} Syntax Error(s){1}{2}"},
        {"xmlbasetool.error.xml.locator", "{0}(File: {1}){2}(DTD: {3}){4}{5}"},
        {"xmlbasetool.error.xmlparse.line", "(File: {0};){1}    {2}{3}"},
        {"xmlbasetool.file.error.ioerror", "IOError in {0}"},
        {"xmlbasetool.filearg.error.missing", "{0}: missing file argument."},
        {"xmlbasetool.filearg.error.null", "{0}: null file argument."},
        {"xmlbasetool.filearg.error.notxml", "{0}: File is not an xml file: {1}."},
        {"xmlbasetool.filearg.error.notpresent", "{0}: Can not find xml file: {1}."},
        {"xmlbasetool.filearg.error.isdir", "{0}: Xml file: {1} is a directory."},
        {"xmlbasetool.logfilearg.error.missing", "{0}: missing log-file argument."},

        {"xmlvalbasetool.name", "XMLValToolBase"},

        {"standardtoolbase.error.createparser.nooverride", "Program Error: A subclass needs to override createParser()."},
        {"standardtoolbase.error.createemitter.nooverride", "Program Error: A subclass needs to override createEmitter()."},
        {"standardtoolbase.name", "StandardToolBase"},
        {"standardtoolbase.debugarg.mnem", "-debug"},
        {"standardtoolbase.debugarg.desc", "  -debug   debug mode"},


        {"emitter.error.outstream.null", "Program Error: Emitter was not setup with an output stream."},
        {"emitter.error.generic.numouts", "Program Error: Number of Files does not match  the number of  IRs."},
        {"emitter.error.generic.notroot", "Object: {0} is not a root of the document."},
        {"tht.error.root.null", "Null root object."},
        {"tht.error.rootclass.null", "Null root class."},
        {"tht.error.roottag.null", "Null root-association tag."},
        {"tht.error.docname.null", "Document name has not been set."},
        {"tht.error.docuri.null", "Document URI has not been set."},


        {"tools.equiv.genlist.uneq", "Unequal {0} List:  L1: {2} L2: {3}."},
        {"tools.equiv.genlist.len", "Unequal {0}  List Length:  L1: {1}[{2}] L2: {3}[{4}]."},

        {"tools.equiv.id.uneq", "Unequal IDs: {0} ID1: \"{1}\" ID2: \"{2}\"."},
        {"tools.equiv.genobj.uneq", "Unequal {0}:  1: \"{1}\" 2: \"{2}\"."},
        {"tools.equiv.genstr.uneq", "Unequal {0}:  1: \"{1}\" 2: \"{2}\"."},
        {"tools.equiv.targetspec.vers.uneq", "Unequal TargetSpec (ID: \"{0}\") {0} Version:  1: {2} 2: {3}."},
        {"tools.equiv.targetspec.versmod.uneq", "Unequal TargetSpec  (ID: \"{0}\") Version {0}:  1: {2} 2: {3}."},

        {"tools.equiv.type.uneq", "Unequal or unknown UTD objects:  1: \"{0\"} 2: \"{1}\"."},
        
        // ---------------------------------------------------------------------------------
        //    testgen messages
        // ---------------------------------------------------------------------------------
        
        {"testgen.error.codecopy", "Unable to copy file : {0}"},
        {"testgen.error.testcase.codenotfound", "No code found for testcase : {0}"},
        {"testgen.error.testcase.langnotsupported", "Source language {0} found in testcase {1} is not supported"},
        {"testgen.error.testcase.nodoc", "No documentation found for testcase {0}"},
        {"testgen.error.test.multipleclasses", "Testing multiple classes is not supported"},
        {"testgen.error.test.multiplepackages", "Testing multiple packages is not supported"},
        {"testgen.error.test.nullpackage", "package is not specified, probably 'testType' attrElem is not specified for ExternalTest"},
        {"testgen.error.unknownIR", "Unknown IR object : {0}"},
        {"testgen.error.noroot", "Root object not "},
        {"testgen.error.multiplelinks", "Multiple testsuite links are not supported"},
        {"testgen.error.creategenerator", "Failed to create generator : {0}"},
        {"testgen.error.generatornotfound", "Generator not found for : {0}"},
        {"testgen.error.parsernotfound", "Parser not found for : {0}"},
        {"testgen.error.multilang", "Multiple languages for inline code are not supported"},
        {"testgen.error.parsefail", "Failed to parse file : {0}"},
        {"testgen.error.no_sourcepath", "No source path defined for TestGroup {0}."},
        {"testgen.error.no_outputdir", "No output dir defined for TestGroup {0}."},
        {"testgen.error.no_rel_sourcepath", "No relative source path defined for TestGroup {0}."},
        {"testgen.error.invalid_sourcepath", "Invalid source path defined for TestGroup {0}."},
        {"testgen.error.invalid_rel_sourcepath", "Invalid relative source path defined for TestGroup {0}."},
        {"testgen.error.resource.invalid_rootContainer", "Can not determine valid root container, Library or Testgroup, for resource."},
        {"testgen.error.resource.no_pkg", "Can not determine package name for resource."},
        {"testgen.error.resource.no_sourcefile_external", "Can not determine name of source file for external data resource."},
        {"testgen.error.resource.no_targetname_inline", "Can not determine target name of inline data resource file."},
        {"testgen.error.resource.ioerror_install", "An IO error was encoutered while installing resource {0}. {1}"},
        {"testgen.error.resource.nullsrc_install", "Source file for resource file installation is null"},
        {"testgen.error.resource.nulldest_install", "Destination file for resource file installation is null"},
        {"testgen.error.resource.nulldata_install", "data parameter for resource file installation is null"},
                
        // ---------------------------------------------------------------------------------
        //    jmppconv messages
        // ---------------------------------------------------------------------------------

        {"jmppconv.error.createdir", "Unable to create directory : {0}"},
        {"jmppconv.error.multiplefiles", "Multiple input files are not supported"},
        {"jmppconv.error.inputstream", "Input stream is not supported"},
        {"jmppconv.error.filenotfound", "File not found : {0}"},
        {"jmppconv.error.fileread", "Error while reading a file : {0}"},
        {"jmppconv.error.genclass", "Unable to create generator class : {0}"},
        {"jmppconv.error.targetspec", "Malformed target spec : {0}"},
        {"jmppconv.error.noOptsInHash", "No options are specified in the Hashtable"},
        {"jmppconv.error.setPropNotInvoked", "Arguments are not passed: setProperties(Hashtable) is not invoked"},

        // ---------------------------------------------------------------------------------
        //    indexgen messages
        // ---------------------------------------------------------------------------------

        {"indexgen.error.nulltitle", "null title"},
        {"indexgen.error.contentformat", "incorrect content format"},

        // ---------------------------------------------------------------------------------
        //    elgen messages
        // ---------------------------------------------------------------------------------

        {"elgen.error.no_rel_sourcepath", "No relarive source path specified for TestItem {0}"},
        {"elgen.error.invalid_rel_sourcepath", "Invalid relarive source path specified for TestItem {0} : {1}"},
        
        // ---------------------------------------------------------------------------------
        //    filter messages
        // ---------------------------------------------------------------------------------
        {"filter.option.testfilter.out.mnem", "-out"},
        {"filter.option.testfilter.out", "  -out <filename>  output file name (obligatory)"},
        {"filter.option.testfilter.in.mnem", "-in"},
        {"filter.option.testfilter.in", "  -in <file.xml> input xml file name (obligatory)"},
        {"filter.error.testfilter.badTestRootType", "Bad TestRoot type : {0}"},
        {"filter.error.testfilter.dirCreateError", "can not create directory : {0}"},
        {"filter.error.testfilter.outputError", "Output error for the file : {0}, {1}"},

        {"filter.error.tfc.noErrorStream", "Not defined err print stream "
							+ "for TestFilterConveyer constructor"},
        {"filter.error.tfc.badExList", "Bad exclude list file :{0}\n{1}"},
        {"filter.error.tfc.badLibList", "Bad libId list file :{0}\n{1}"},
        {"filter.error.tfc.badPlugin", "Bad plugin :{0}\n{1}"},
        {"filter.error.tfc.elMarker", "EL Marker error: {0}"},
        {"filter.error.tfc.filtering", "Filtering error : {0}"},
        {"filter.error.tfc.libIdExtract", "LibID extractor error : {0}"},
        {"filter.option.tfc.plugin.mnem", "-plugin"},
        {"filter.option.tfc.plugin", "  -plugin <pluginname>   plugin class name (obligatory)"},
        {"filter.option.tfc.exlist.mnem", "-exlist"},
        {"filter.option.tfc.exlist", "  -exlist <file.jtx>   input exclude list file (obligatory)"},
        {"filter.option.tfc.liblist.mnem", "-liblist"},
        {"filter.option.tfc.liblist", "  -liblist <filename>  library ID list file"},
        {"filter.option.tfc.liblistOut.mnem", "-liblistOut"},
        {"filter.option.tfc.liblistOut", "  -liblistOut <filename>  file for library ID list updates"},
        {"filter.error.factory.loadPlugin", "Load plugin: {0}"},
        {"filter.error.testfilter.repFilteredTGroup", "Reporting filtered TestGroup: {0}"},
        {"filter.error.testfilter.repFilteredTCase",  "Reporting filtered TestCase: {0}"},
        {"filter.error.testfilter.excludedTGroup", "Check excluded TestGroup: {0}"},
        {"filter.error.testfilter.excludedTCase",  "Check excluded TestCase: {0}"},
        {"filter.error.testfilter.duplicatedTCase", "Duplicated TestCase ID: {0}"},
        {"filter.error.libfilter.notSingleImpl", "Not single implementation of library : {0}"
						+" remains after filtering" },
        {"filter.error.libfilter.duplicatedSClass", "Duplicated SupportClass :{0}"
						+" for TestItem :{1}" },
        {"filter.error.libfilter.noErrorStream", "Not defined err print stream "
							+ "for LibFilterConveyer constructor"},
        {"filter.error.libfilter.badDepLibList", "Bad libDep list file : {0}\n{1}"},
        {"filter.error.libfilter.badPlugin", "Bad plugin :{0}\n{1}"},
        {"filter.error.libfilter.noLibImpl", "Not found implementations for :{0}\n in {1}"},
        {"filter.error.libfilter.allLibsFilteredOut", "All library implementations are filtered out for :{0}"},
        {"filter.error.libfilter.libXMLParser", "parser: IOException during parsing of an array"
                                                + " of XML files of the library: {0}"},
        {"filter.error.libfilter.badTestRoot", "Bad TestRoot type :{0}\n in file {1}"},
        {"filter.error.libfilter.badLibID", "Library with ID '{0}' instead of '{1}' is found in file : {2}"},
        {"filter.error.libfilter.libFilterError", "Filtering error during filtering of the library: {0}, {1}"},
        {"filter.error.libfilter.libIDExtrError", "LibID extractor error : {0}"},
        {"filter.error.libfilter.libDepCloseError", "IOException during LibDepExtractor.close()"},
        {"filter.error.libfilter.noTCKProperty", "Property '{0}' is not defined"},
        {"filter.error.libfilter.emptyAttrElem", "Empty value of the {0} AttrElem\n"},
        {"filter.error.libfilter.noAttrElem", "Empty value of the {0} AttrElem\n"},
        {"filter.error.libfilter.ioException", "IOException thrown by {0} for {1} \n {2}"},
        {"filter.error.libfilter.ioException2", "IOException for {1} \n {2}"},
        {"filter.error.libfilter.badTCKDir", "Value of the property {0} : {1} \n is not a root dir for {2} value : {3}"},
        {"filter.error.libfilter.DirCreateError", "Can not create directory:  {0}"},
        {"filter.error.libfilter.libNoAttributes", "No attributes in Library :  {0}"},
        {"filter.error.libfilter.badMapList", "Bad list of xml: missing filename before separator in pos {0}"},
        {"filter.error.libfilter.mappingNotFound", "Not found mapping for library file  {0}"
                                               + ", in string {1}"},
        {"filter.error.libfilter.mappingFileNotFound", "Not found mapping for libID {0}"
                                               + ", library file  {1}, in mapfile {2}"},
        {"filter.error.libfilter.wrongReposSeparator", "unable to get repository path for libID {0}"
                                               + " in file {1},  using repository path delimiter {2}"},

        {"filter.libmap2File.mnem", "-libmap2File"},
        {"filter.libmap2File.desc", "  -libmap2File <filename>   libmap2File file name"},
        {"filter.libID.mnem", "-libID"},
        {"filter.libID.desc", "  -libID <id>   lib id (obligatory)"},
        {"filter.libmap.mnem", "-libmap"},
        {"filter.libmap.desc", "  -libmap \"space_separated_list_of_xml_files\"  mapping of ID to xml files (obligatory)"},
        {"filter.liblist.mnem", "-liblist"},
        {"filter.liblist.desc", "  -liblist <filename>  lib list file name "},
        {"filter.liblistOut.mnem", "-liblistOut"},
        {"filter.liblistOut.desc", "  -liblistOut <filename>  lib list out file name "},
        {"filter.plugin.mnem", "-plugin"},
        {"filter.plugin.desc", "  -plugin <pluginname>   plugin name (obligatory)"},
        {"filter.out.mnem", "-out"},
        {"filter.out.desc", "  -out <dirname>  output dir name i.e. root path to built TCK (obligatory)"},

        // ---------------------------------------------------------------------------------
        //    diff tool messages
        // ---------------------------------------------------------------------------------

        {"utddifftool.file1arg.mnem", "-file1"},
        {"utddifftool.file1arg.desc", "  -file1 <xml filename>  input xml file1 name (obligatory) "},
        {"utddifftool.file2arg.mnem", "-file2"},
        {"utddifftool.file2arg.desc", "  -file2 <xml filename>  input xml file2 name (obligatory) "},
        {"utddifftool.error.null.file", "Error: Can not find file \"{0}\"."},
        {"utddifftool.error.dir.file", "Error: File \"{0}\" is a directory."},
        {"utddifftool.error.notread.file", "Error: Can not read file \"{0}\"."},

        // ---------------------------------------------------------------------------------
        //     Redundancy Filtering messages
        // ---------------------------------------------------------------------------------

        {"filter.redundancy.error.externaMultiTestExclusion", "Can not exclude the following TestGroup component from a Testgroup of ExternalMultiTest type:\n    {0}"},
        {"filter.redundancy.error.unexpectedExcepton", "Unexpected exception has been thrown: {0}"},
        {"filter.redundancy.error.btx.invalidentry", "The generation exclude list contains invalid entry: {0}"},
       // ---------------------------------------------------------------------------------
        //   TestItem Log Diff diff tool messages
        // ---------------------------------------------------------------------------------
        {"testitemlogdiff.usage.header", "Usage: com.sun.tgxml.tools.filter.testitemdiff.TestItemListDiff -oldlist <file name> -newlist <file name> [<options>] "},
        {"testitemlogdiff.option.new.mnem", "-new_items"},
        {"testitemlogdiff.option.new", "  -new_items <on|off> turn on/off the reporting of a new TestItems "},
        {"testitemlogdiff.option.variants.mnem", "-variants"}, 
        {"testitemlogdiff.option.variants", "  -variants <on|off> turn on/off the reporting of a changed TestItem variant names "},
        {"testitemlogdiff.option.attributes.mnem", "-attributes"}, 
        {"testitemlogdiff.option.attributes", "  -attributes <on|off> turn on/off the reporting of a changed TestItem attributes names"},

        {"testitemlogdiff.option.oldlist.mnem", "-oldlist"},
        {"testitemlogdiff.option.oldlist", "  -oldlist <file name> (mandatory) defines name of a old TestItem list file"},
        {"testitemlogdiff.option.newlist.mnem", "-newlist"},
        {"testitemlogdiff.option.newlist", "  -newlist <file name> (mandatory) defines name of a new TestItem list file"},

        {"testitemlogdiff.option.exclude.mnem", "-excludeByAttributes"},
        {"testitemlogdiff.option.exclude", "  -excludeByAttributes <attribute1> ... <attributeN> excludes the TestItems with the given attributes from verifictation. For example -excludeByAttributes type=ExternalLibrary type=inlineLibrary turn off the reporting of the changes of libraries."},

        {"testitemlogdiff.onoffviolation", "{0} option allows only on or off values"},
        {"testitemlogdiff.report.missing.header", "The following entries are missed in the new log file:\n"},
        {"testitemlogdiff.report.missing.summary", "Missed entries:        {0}\n"},
        {"testitemlogdiff.report.added.header", "The following entries are added in the new log file:\n"},
        {"testitemlogdiff.report.added.summary", "New entries:           {0}\n"},
        {"testitemlogdiff.report.changedvariants.header", "The following variant names are changed in the new log file:\n"},
        {"testitemlogdiff.report.changedvariants.summary", "Changed Variant Names: {0}\n"},
        {"testitemlogdiff.report.changedattributes.header", "The following attributes are changed in the new log file:\n"},
        {"testitemlogdiff.report.changedattributes.summary", "Changed Attributes:    {0}\n"}

    };
}


