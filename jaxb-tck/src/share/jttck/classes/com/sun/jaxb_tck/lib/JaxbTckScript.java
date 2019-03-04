/*
 * Copyright (c) 2008, 2018 Oracle and/or its affiliates. All rights reserved.
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
package com.sun.jaxb_tck.lib;

import java.util.StringTokenizer;
import java.io.File;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.regex.Pattern;

import com.sun.javatest.Script;
import com.sun.javatest.TestDescription;
import com.sun.javatest.TestEnvironment;
import com.sun.jaxb_tck.interview.JAXBTCKParameters;

import com.sun.javatest.Status;
import com.sun.javatest.util.StringArray;

/**
 * Script to execute JAXB tests.
 *
 * @author  Evgueni M. Astigueevitch
 * @author  Leonid Kuskov
 * @version 1.63
 */
public class JaxbTckScript extends Script {

    protected final boolean debug = false;

    public static final String SCHEMA_NAME = "schemaName";

    public static final String KWD_SCHEMA = "schema";

    public static final String KWD_DOCUMENT = "document";

    public static final String KWD_NEGATIVE = "negative";

    public static final String KWD_POSITIVE = "positive";

    public static final String KWD_RUNTIME = "runtime";

    public static final String KWD_JAXB_NOT_REQUIRED = "jaxb_not_required";

    public static final String KWD_SCHEMAGEN_REQUIRED = "java_to_schema";

    public static final String KWD_RTGEN = "rtgen";
    
    public static final String KWD_EMPTY_OUT = "empty_output";

    // also used by javasoft.sqe.tests.api.javax.xml.bind.SchemaGenTest::getSchemaSources(String schemaNames
    public static final String SCHEMA_SEPARATOR = ";";

    private static final int INVOCATION_LIMIT = 50;
    
    // File extension that used in test suite to identify xml schema files. 
    public static final String[] ALLOWED_SCHEMA_EXTESIONS = { 
    	".xsd",
    	".inc",
    	".imp",
    	".imp2",
    	".red"
    };
    

    /**
     * Counter of the number of test runs.
     */
    private static volatile Counter counter = new Counter();

    protected static String testSuiteRootDir;

    protected String outDir = "";

    protected ArrayList<String> schemaSrcList = new ArrayList<String>();
    
    protected String schemaSources = null;

    protected String pkg = "";

    protected boolean isSchema;

    protected boolean isDocument;

    protected boolean isNegative;

    protected boolean isPositive;

    protected boolean isRuntime;

    protected boolean isJaxbNotRequired;

    protected boolean defaultOperationMode;

    protected boolean isSchemaGenRequired;

    protected boolean isSameJVM;
    
    protected boolean isEmptyOut; // KWD_EMPTY_OUT = "empty_output"

    protected boolean needCompile = true;

    protected boolean isRtgen;

    // schema name as it is passed from test description
    protected String schemaName = "";
    
    static final int LRU_CAPACITY = 25;
    
    /**
     * Cache of compiled schemata.
     * It is static because an instance of this class is created for each run of every test.
     */
     static Map<String, Status[]> schemaCache = Collections.synchronizedMap(
    		 new LinkedHashMap<String, Status[]>() {
    		      // (an anonymous inner class)
    		      private static final long serialVersionUID = 1;
    		      @Override protected boolean removeEldestEntry (Map.Entry<String, Status[]> eldest) {
    		         return size() > LRU_CAPACITY; }});  
    
    public static class InitializationException extends Exception {
        private static final long serialVersionUID = 3977022838043587382L;

        public InitializationException(String message) {
            super(message);
        }
    }

    public Status run(String[] args, TestDescription td, TestEnvironment env) {
        counter.inc();
        try {
            initTestRun(td, env);

            if (isJaxbNotRequired && !defaultOperationMode) {
                return Status.passed("Test requires the default operation mode.");
            }

            Status status = error_noActionSpecified;

            // Initialize the output directory
            initMacroOutDirValue();

            // SchemaGen test.
            if (isSchemaGenRequired){
                status = generate(); // contains both schemagen.jxc and execute
                if( !status.isPassed() || ! isDefaultMappingJ2Stest(td) ) {
                    return status;
                }
            }

            // SchemaCompile test.
            if (needCompileStage()) {
                initMacroCompileValues();
                if( !(status = compile()).isPassed() ) return status;
                if(isEmptyOut) {
                    // check that no java files have been produced.
                    if (getGeneratedSources(".java") == null) {
                        return Status.passed("No java sources were generated as expected");
                    } else {
                        return Status.failed("Unexpected java sources were generated");
                    }
                }
            }

            // API tests
            if (needExecuteStage()) {
                String fileSeparator = getFileSeparator("java");
                if (!File.separator.equals(fileSeparator)) {
                    changeFileSeparatorInMacroTestURL(fileSeparator);
                }
                String pathSeparator = getPathSeparator("java");
                if (!File.pathSeparator.equals(pathSeparator)) {
                    changePathSeparatorInMacroJaxbClasses(pathSeparator);
                }
                status = execute();
            }
            return status;
        } catch (InitializationException e) {
            return Status.error(e.getMessage());
        } finally {
            clearVariables();
        }
    }

    protected String getFileSeparator(String cmdPrefix) {
        try {
            return env.lookup(cmdPrefix + "_map_file_separator")[0];
        } catch (Exception e) {
            return File.separator;
        }
    }

    protected String getPathSeparator(String cmdPrefix) {
        try {
            return env.lookup(cmdPrefix + "_map_path_separator")[0];
        } catch (Exception e) {
            return File.pathSeparator;
        }
    }

    protected void changeFileSeparatorInMacroTestURL(String fileSeparator) {
        String macroValue = "file://"
                + (testSuiteRootDir.startsWith("/") ? "" : "/")
                + testSuiteRootDir
                + fileSeparator
                + "tests"
                + fileSeparator
                + td.getRootRelativePath()
                        .replace('/', fileSeparator.charAt(0));
        env.put("testURL", macroValue);
    }

    protected void changePathSeparatorInMacroJaxbClasses(String pathSeparator) {
        try {
            String macroValue = env.lookup("jaxbClasses")[0];
            macroValue = macroValue.replace(File.pathSeparatorChar,
                    pathSeparator.charAt(0));
            env.put("jaxbClasses", macroValue);
        } catch (Exception e) {
        }
    }

    protected void initMacroCompileValues() {
        StringBuilder envSchema = new StringBuilder();
        ArrayList<String> xsdSourceList = new ArrayList<String>();
        for(String _s : schemaSrcList) {
            if(isSameJVM) {
                envSchema.append( (envSchema.length() > 0 ? "\u0085" : "") + _s); 
            } else {
                xsdSourceList.add(_s);
            }
        }
        // Add an array of schema files in multiJVM, so it will be separate
        // arguments for external script
        if (isSameJVM) {
            env.put("schema", envSchema.toString()); 
        } else {
            env.put("schema", (String[]) xsdSourceList.toArray(new String[xsdSourceList.size()]));
        }
        // fill $empty_output_placeholder
        if (isEmptyOut)
          env.put("empty_output_placeholder", "-"+KWD_EMPTY_OUT );
    }

    protected void initMacroOutDirValue() {
        String macroValue = workDir.getFile(outDir).getAbsolutePath();
        env.put("outDir", macroValue);
    }

    protected void clearVariables() {
        outDir = "";
        schemaSrcList = new ArrayList<String>();
        pkg = "";
        isSchema = false;
        isDocument = false;
        isNegative = false;
        isPositive = false;
        isRuntime = false;
        isJaxbNotRequired = false;
        defaultOperationMode = false;
        isSchemaGenRequired = false;
        isEmptyOut = false; 

        if (counter.getValue() >= INVOCATION_LIMIT) {
            counter.reset();
            System.runFinalization();
            System.gc();
        }
    }

    protected void initTestRun(TestDescription td, TestEnvironment env)
            throws InitializationException {
        
        initKeywords(td);
        
        if( debug ){
            System.out.printf( "JaxbTckScript.initTestRun: '%s' keywords:", td.getId() );
                Set<?> keywords = td.getKeywordTable();
            for( java.util.Iterator<?> it = keywords.iterator(); it.hasNext(); ){
                System.out.printf( " '%s'", it.next() );
            }
            System.out.println();
        }

        if (!env.equals(this.env))
            initTestEnvironment(env);
        
        if (!td.equals(this.td))
            initTestDescription(td);

        try {
            if (testSuiteRootDir == null)
                testSuiteRootDir = env.lookup("testSuiteRootDir")[0];
        } catch (Exception e) {
            throw new InitializationException(e.toString());
        }

        try {
            String str = env.lookup("xjc_default_operation_mode")[0];
            defaultOperationMode = ("true".equals(str));
        } catch (Exception e) {
            defaultOperationMode = false;
        }

        isSameJVM = false;
        try {
            String str = env.lookup("xjc_sameOther")[0];
            isSameJVM = (String.valueOf(JAXBTCKParameters.MODE_SAME).equals(str));
        } catch (Exception e) {
        }

        // Package && Schema initialization is not needed for SchemaGen test.
        if (!isSchemaGenRequired) {
            initTestPackage();
            initSchema();
        }

        initOutputDir();
    }

    protected void initKeywords(TestDescription td) {
        Set<?> keywords = td.getKeywordTable();
        isSchema = keywords.contains(KWD_SCHEMA);
        isDocument = keywords.contains(KWD_DOCUMENT);
        isNegative = keywords.contains(KWD_NEGATIVE);
        isPositive = keywords.contains(KWD_POSITIVE);
        isRuntime = keywords.contains(KWD_RUNTIME);
        isJaxbNotRequired = keywords.contains(KWD_JAXB_NOT_REQUIRED);
        isSchemaGenRequired = keywords.contains(KWD_SCHEMAGEN_REQUIRED);
        isRtgen = keywords.contains(KWD_RTGEN);
        isEmptyOut = keywords.contains(KWD_EMPTY_OUT); 
    }

    /**
     *  Initializes output Directory for any kinds of tests.
     */
    protected void initOutputDir() throws InitializationException {
        //  Initialization of the output directory for SchemaGen
        if (isSchemaGenRequired) {
            outDir = createOutputDirName4Jxc();
        } else {
            // Initialization of the output directory for SchemaCompiler
            outDir = createOutputDirName4Xjc();
        }
        if (outDir.length() == 0)
            return;
        
        if (needPersonalDir()) {
            String name = td.getName();
            if (name != null) {
            	if (! outDir.endsWith(File.separator) ) {
            		outDir += File.separator;
            	}
                outDir +=  name;
            }
        }
        File dir = workDir.getFile(outDir);
        if (!mkdirs(dir)) {
            throw new InitializationException("Can't create the directory: "
                    + dir.getAbsolutePath());
        }
    }

    /**
     * Get Output Directory for SchemaCompiler
     */
    protected String createOutputDirName4Jxc() {
        return "schemas" + File.separator + getRelativePath();
    }

    /**
     * Get Output directory for Schema Compiler
     */
    protected String createOutputDirName4Xjc() {
        String prefix = "javasoft.sqe.tests.";
        int pos = pkg.indexOf(prefix);
        if ((pos == -1) || (pkg.length() == prefix.length())) {
            return pkg.replace('.', File.separatorChar);
        }

        String name = pkg.substring(pos + prefix.length());
        name = deleteCharFromStr('_', name);
        name = "classes" + File.separator
                + name.toLowerCase().replace('.', File.separatorChar);
        return name;
    }

    private String deleteCharFromStr(char ch, String str) {
        StringBuilder strBuffer = new StringBuilder(str);
        for (int i = 0; i < strBuffer.length(); i++) {
            if (strBuffer.charAt(i) == ch)
                strBuffer.deleteCharAt(i--);
            }
        return strBuffer.toString();
    }

    protected boolean needPersonalDir() {
        return isSchemaGenRequired || isRtgen;
    }

    /**
     * Initializes package name for SchemaCompiler/RTime only.
     */
    protected void initTestPackage() {
        String p = td.getParameter("package");
        // SchemaCompiler
        if (p == null || p.length() == 0) {
            p = getDefaultPackage();
        }
        this.pkg = p;
        env.put("package", p);
    }

    /*
     * Construct package name from test URL
     */
    private String getDefaultPackage() {
    	String name = getRelativePath().replaceAll("_",  ".").
                replaceAll(Pattern.quote(".." + File.separator), "");
    	return name.replaceAll(Pattern.quote(File.separator),  ".").toLowerCase();
    } 
    
    /*
     * Get relative path
     */
    private String getRelativePath() {
        String absPath = td.getDir().getAbsolutePath();
        String testDirName = File.separator + "tests" + File.separator;
        int pos = absPath.indexOf(testSuiteRootDir);
        pos = absPath.indexOf(File.separator, testSuiteRootDir.length()
                + testDirName.length() + pos);
        absPath = absPath.substring(pos + 1);
        return absPath;
    }
    
    protected void initSchema() {
        String[] sources = td.getSources();
        ArrayList<String> schemaSources = new ArrayList<String>();
        ArrayList<String> xsdSourceList = new ArrayList<String>();
        
        if (sources.length == 0) {
            return;
        }
        
        for (int i = 0; i < sources.length; i++){
        	for(String ext : ALLOWED_SCHEMA_EXTESIONS) {
        		if (sources[i].endsWith(ext)) {
        			schemaSources.add(sources[i]);
        			break;
        		}
        	}
        }
        
        if ( schemaSources.size() == 0 ) {
            String sn = td.getParameter(SCHEMA_NAME); 
            if ( sn == null) {
                return;
            }
            for(String s : StringArray.split(sn)) {
                schemaSources.add(s);
            }
            needCompile = false;
        }
        
        schemaName = arrayListAsString(schemaSources);
        StringBuilder envSchema = new StringBuilder();
        String fileSeparator = getFileSeparator("xjc");
        
        for(String str : schemaSources) {
            String schema = td.getRootRelativeFile().getParent() + File.separator + str;
            schema = testSuiteRootDir + fileSeparator + "tests" + fileSeparator + 
            ((fileSeparator.equals(File.separator)) ? schema : schema.replace(File.separatorChar, fileSeparator.charAt(0)));
            
            schemaSrcList.add(schema);
            
            if(isSameJVM) {
                envSchema.append( (envSchema.length() > 0 ? "\u0085" : "") + schema); 
            } else {
                xsdSourceList.add(schema);
            }
        }
        // Add an array of schema files in multiJVM, so it will be separate
        // arguments for external script
        if (isSameJVM) {
            env.put("schema", envSchema.toString()); 
        } else {
            env.put("schema", (String[]) xsdSourceList.toArray(new String[xsdSourceList.size()]));
        }
    }

    protected boolean needCompileStage() {
        return (isSchema || isDocument) && needCompile;
    }

    protected boolean needExecuteStage() {
        return (!(isSchema && (isNegative || isEmptyOut)) || isRtgen);
    }
    
    protected boolean isDefaultMappingJ2Stest(TestDescription td) {
        return td.getParameter( "testSource") != null;
    }

    /**
     * Schema generation
     * this method converts java sources into a set of schema file (xsd)
     */
    protected Status generate() {
        StringBuilder     javaSource = new StringBuilder();
        ArrayList<String> javaSourceList = new ArrayList<String>();
        StringBuilder     args = new StringBuilder();
    	
    	String            executeArgs = td.getParameter("executeArgs");
        StringTokenizer   tokenizer = new StringTokenizer(executeArgs, " ", false);

        while (tokenizer.hasMoreElements()) {
            String str = tokenizer.nextToken();
            if ("-j".equals(str) && tokenizer.hasMoreElements()) {
                str = tokenizer.nextToken();
                
                // initializes JavaFiles
                File testDir = td.getDir();
                StringTokenizer colonizer = new StringTokenizer(str, ":", false);

                while (colonizer.hasMoreElements()) {
                    String source = colonizer.nextToken();
                    File file = new File(testDir, source);
                    
                    if (isSameJVM) {
                        if (javaSource.length() > 0)
                            javaSource.append('\u0085');
                        javaSource.append(file.getAbsolutePath());
                    } else {
                        javaSourceList.add(file.getAbsolutePath());
                    }
                }

            } else {
                if (args.length() > 0) {
                	args.append(' ');
                }
                args.append(str);
            }
        }
        // Add an array of java files in multiJVM, so it will be separate
        // arguments for external script
        if (isSameJVM) {
            env.put("java_source_files", javaSource.toString());
        } else {
            env.put("java_source_files", (String[]) javaSourceList.toArray(new String[javaSourceList.size()]));
        }


        // call schemagen
        Status result = super.invokeCommand("schemagen.jxc");

        if (isNegative) {
            return result.isPassed() ? fail_compSuccUnexp : pass_compFailExp;
        }

        if (result.isPassed() && !isDefaultMappingJ2Stest(td)) {
            // add just generated schema to execute args if !isEmptyOut
        	schemaSources = getGeneratedSources(".xsd");
        	
        	if ( isEmptyOut ) { 
        		// No schema(s) have to be produced.
                if ( schemaSources == null ) {
                	result = Status.passed("No schemas were generated as expected");
                } else {
                	result = Status.failed("Unexpected schemas were generated");
                }
        	} else {
	            // validate schema against xml
	            // add just generated schema(s) to execute args.
	            if ( schemaSources == null ) {
	            	result = Status.failed("No schemas were generated");
	            } else {
	            	//call java
			        args.append(" -schemas ").append(adjustForSpace(schemaSources));
		            String executeClass = td.getParameter("executeClass");
		            result = super.execute("execute", executeClass, args.toString()); 
	            }
        	}
        }
        return result;
    }

    /**
    * this method checks whether schema compiled successfully
    */
    protected Status compileSchema() {
        Status status = super.invokeCommand("compile.xsd");

        if (isSchema && isNegative) {
            switch (status.getType()) {
            case Status.PASSED:
                status = fail_compSuccUnexp;
                break;

            case Status.FAILED:
                status = pass_compFailExp;
                break;

            default:
                break;
            }
        }
        return status;
    }
    
    protected Status compile() {
        if (isSchema && (isNegative || isEmptyOut)) {
            // skip if a test either produces no java files or is negative. 
            return compileSchema();
        }
        
        Status[] status;
        synchronized (schemaCache) {
            status = schemaCache.get(arrayListAsString(schemaSrcList));
            if (status == null) {
                status = new Status[1];
                schemaCache.put(arrayListAsString(schemaSrcList), status);
            }
        }
        
        synchronized (status) {
            if ( status[0] != null && status[0].isPassed() ) {
                trOut.println("Using compiled schema(s):");
                for(String str : schemaSrcList) {
                    trOut.println(str);
                }
            } else {
                // Compile schema and put it into the cache if it have not been compiled yet.
                status[0] = compileSchema();
            }
            return status[0];
        }
    }
    

    protected Status execute() {
        String executeArgs = td.getParameter("executeArgs");
        
        if (isDocument && !isRuntime ){        
            // Adds schema if test contains keywords: 
        	// 1. document validation_checker
        	// 2. document bindinfo
        	// 3. document 
        	// See XMLSchemaTestEmitter,java.getExecuteArgs.else if ( tcType.equals(KEYWORD_RUNTIME) )
            executeArgs += " -schema " + schemaName;
        } else if (isRtgen) {
            env.put("testWorkDir", 
                    adjustForSpace(workDir.getFile(outDir).getAbsolutePath()));
        }

        if (!defaultOperationMode && (isSchema && isPositive))
            executeArgs += " -EnableSuperSet";
        
        //
        if ( isSchemaGenRequired ) {
            schemaSources = getGeneratedSources(".xsd");
            if ( schemaSources != null ){
                executeArgs += " -schemas " + adjustForSpace(schemaSources);

                // remove '-j' argument if present
                {
                    StringTokenizer tokenizer = new StringTokenizer(executeArgs, " ", false);
                    StringBuilder args = new StringBuilder();
                    while (tokenizer.hasMoreElements()) {
                        String str = tokenizer.nextToken();
                        if ("-j".equals(str) && tokenizer.hasMoreElements()) {
                            tokenizer.nextToken();
                        } else {
                        	args.append(" " + str);
                        }
                    }

                    executeArgs = "";
                    if( args.length() > 0 ) {
                        executeArgs = args.toString().substring( 1 );
                    }
                }
            }
        }

        String executeClass = td.getParameter("executeClass");
        return super.execute("execute", executeClass, executeArgs);
    }

    /**
     * Tries to create directory with path specified by File object
     */
    public static boolean mkdirs(File dir) {
        if (dir.exists())
            return dir.isDirectory();

        File f = dir.isAbsolute() ? dir : dir.getAbsoluteFile();
        File p = f.getParentFile();
        if (!mkdirs(p))
            return false;

        return f.mkdir() || f.isDirectory();
    }

    /**
     * Gets sources with extension that were just generated  
     */
    private String getGeneratedSources(String ext) {
        Arguments args = new Arguments();
        File absDir    = workDir.getFile(outDir);
        args.addFileNames(absDir, ext);
        if (args.size() != 0) {
            return args.getArgsAsString(SCHEMA_SEPARATOR);
        }
        return null;
    }
    
    // if pathname has ' ' in <code>s</code>, add quotations
    static String adjustForSpace(String s) {
        if (s != null && s.indexOf(" ") > 0)
            return "'" + s + "'";
        else
            return s;
    }    

    private String arrayListAsString(ArrayList<String> src) {
        if(src == null || src.size() == 0) 
            return "";
        return StringArray.join(src.toArray(new String[src.size()]));
    }
}
