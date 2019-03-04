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

package com.sun.jmpp.lib;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.Hashtable;
import java.text.DateFormat;
import javasoft.sqe.jckutils.lib.JmppScript;
import com.sun.jmpp.JmppException;
import com.sun.jmpp.share.Options;
import com.sun.jmpp.share.OptionDescr;

/**
 * Base class to be executed by Jmpp macro preprocessor for generating
 * final macroprocessor output for test generation.
 *
 * @author Oleg V. Ulyankin
 * @version @(#)JmppLibTest.java	1.33 05/04/08
 */

public class JmppLibTest
	extends com.sun.jmpp.JmppLib
	implements JmppScript.JmppLibrary {

    public final static String OPT_NO_NEG_COMPILER = "nonegcompiler";

    public final static String USG_NO_NEG_COMPILER =
        "    -" + OPT_NO_NEG_COMPILER + "\n" +
        "     do not generate negative compiler tests or their test descriptions\n";

    public final static OptionDescr DSC_NO_NEG_COMPILER =
        new OptionDescr(OPT_NO_NEG_COMPILER, "no negative compiler tests", USG_NO_NEG_COMPILER);


/**
 *   Deprecated variables for keywords.
 */
	public static final String
		posCompilerKeywords 			= "compiler positive",
		posCompilerSerialKeywords		= "compiler positive serial",
		negCompilerKeywords			= "compiler negative",
		negCompilerSerialKeywords		= "compiler negative serial",
		posRuntimeKeywords			= "runtime positive",
		posRuntimeJniKeywords			= "runtime positive jniinvocationapi needsharedclassloader",
		negRuntimeKeywords			= "runtime negative",
		negRuntimeJniKeywords			= "runtime negative jniinvocationapi needsharedclassloader",
		posRuntimeInteractiveKeywords		= "runtime positive interactive",
		posCompilerRuntimeKeywords		= "compiler runtime positive",
		posCompilerRuntimeSerialKeywords	= "compiler runtime positive serial";

/**
 *   Variables for context sensitive tests.
 */
	public static final String
		properties_nativeCodeSupported	= "platform.nativeCodeSupported";

	/* The following five fields hold various information about the location
	   and name of the currently generated source.
	 */

/**
 *    Name of the directory where a current test source is generated into
 *    (if sourceNameIsRelative is set to true then the source maybe generated
 *    into the dirName's subdirectory)
 */
	public String dirName;
/**
 *   Is new source file name (for current test) is specified relatively to
 *   current test directory
 */
	public boolean sourceNameIsRelative = false;
/**
 *   Contains name of currently generated source
 */
	protected String currentFileName;

/**
 *   Macro containing assertion name, e.g. `innr106`.
 */
	public String asrt;

/**
 *   Macro containing test name, e.g. `innr10607`.
 */
	public String test;

/**
 *   Macro containing test number, e.g. 106.
 */
	public int testInt;

/**
 *   Constant for specifying that test is positive.
 */
	public static final boolean POSITIVE = true;

/**
 *   Constant for specifying that test is negative.
 */
	public static final boolean NEGATIVE = false;

/**
 *   Variable specifying whether the test is positive, or negative.
 */
	public boolean testKind;

/**
 *   Variable, equal to (testInt-from), used for indexing arrays.
 */
	public int tID;

    public String getSccs() {
        return sccs;
    }

    /**
 *   SCCS template ident store.
 */
	public String sccs;

/**
 *   If true, HTML should be created as well.
 *   @see #makeHTML()
 */
	public boolean doHTML = true;

/**
 *   String, containing brief test description (title) to be inserted into
 *   generated (if it is generated) HTML source.
 */
	public String title;


/**
 *   String, containing long test description to be inserted into
 *   generated (if it is generated) HTML source.
 */
	public String description;

/**
 *   String, containing test comments to be inserted into test HTML.
 */
	public String testComments;

/**
 *   String array, containing keywords of the tests to be inserted into
 *   generated (if it is generated) HTML source.
 */
	public String keywords;

/**
 *   If false, keyword "only_once" should be added to keywords list.
 *   @see #createTest ()
 */
	public boolean rerunnable = true;


/**
 *   String array, containing context properties of the tests to be inserted into
 *   generated (if it is generated) HTML source.
 */
	public String properties;

/**
 *   String array, containing executeArgs to be passed to the tests during
 *   test execution. These args are supplied in the argv array of the main()
 *   method of the test.
 */
	public String executeArgs;

/**
 *   String array, containing executeClass to be executed.
 */
	public String executeClass;

/**
 *   Timeout value for the test. Set it to non-zero to define specific timeout.
 */
	public int testTimeout = 0;

/**
 *   String array, containing source names. Assign a list of sources to this
 *   variable, if you want to see some list in html files in some particular order,
 *   rather that in order, sources appear in sequence of newSource() method invocations.
 */
	public String[] sourceList = null;

/**
 *   String vector, containing source names to go to FILES section of the test description HTML.
 */
	public Vector filesSectionList = new Vector();

/**
 *   Vector, accumulating sources after invocations of newSource() method.
 */
	public Vector sourceFiles = new Vector();

/**
 *   Test developer name to be inserted into HTML (if HTML is generated).
 */
	public String testDeveloper = "anonymous";

/**
 *   Boolean, specifying whether to create directories, or not.
 */
	public boolean createDirs = true;

/**
 *   Boolean, specifying whether to insert comment saying this source was automatically
 *   generated with Jmpp in all sources and HTML, or not.
 */
	public boolean insertAutoGenerated;

/**
 *   Package head including assertion name, that will be used to set up package of the test.
 */
	public String packageHead = "javasoft.sqe.tests.";

/**
 *   Package name, that will be used to set up package of the test.
 */
	public String packageName;

/**
 *   Specifies if current source file should go to Vector of sources needed for JmppScript.
 */
	public boolean includeToSources;

/**
 *   Each vector contains sources of only one test.
 */
	public Vector sourcesOfCurrentTest;

/**
 *   Each vector contains Vector of sources of only one test.
 */
	public Vector allSources = new Vector();

/**
 *   Keywords of all tests.
 */
	public Vector allKeywords = new Vector();

/**
 *   Names of all tests.
 */
	public Vector allTestNames = new Vector();

/*
 * test description generator, it is used in makeHTML() to
 * create a test description.
 */
        public TDGenerator tdGenerator = null;

/**
 *   Equals to `from` parameter in make().
 *   @see #make
 */
	public int startIndex;

/**
 *   A default extension for generated sources.
 */
	public String defaultExtension = "java";

/**
 *   A macro for TEMP code.
 */
	public String statusTemp = "95/*STATUS_TEMP*/";

/**
 *   A macro for test failure return.
 */
	public String statusFailed = "2/*STATUS_FAILED*/";

/**
 *   A macro for passed test return.
 */
	public String statusPassed = "0/*STATUS_PASSED*/";

/**
 *   A macro for not applicable test return.
 */
	public String statusNotApplicable = "3/*STATUS_NOT_APPLICABLE*/";

/**
 *   A variable for specifying a blank-space separated list of libraries
 *   which generated test is dependent on. Should be used in template.
 */
        public String dependsOn = "";


/**
 *   A variable for specifying a list of target specifications to
 *   which generated test is conformant. For, example, "JDK:1.4-, JavaTV:-1.1".
 */
        public String targetSpec = "";

    /**
     * System property name for passing TCK copyright file location.
     * (must be relative to current output directory).
     */
    public final static String PROP_TCK_COPYRIGHT = "jmpp.tck.copyright";


    public JmppLibTest() {
        tckCopyrightFile = System.getProperty(PROP_TCK_COPYRIGHT);
    }

	public void generateProlog(PrintWriter out, String intermediateClassName) {
		out.println("package "+templatePackage+";\n");
		out.println("public class "+intermediateClassName+" extends "+getClass().getName()+" {");
	}

	public void generateEpilog(PrintWriter out, String intermediateClassName) {
		generateMain(out, intermediateClassName);
		out.println("}");
	}

	public void make(String sccs, int testsTotal)
	{ make(doHTML, createDirs, sccs, null, 1, testsTotal); }
	public void make(String sccs, String packageInBetween, int testsTotal)
	{ make(doHTML, createDirs, sccs, packageInBetween, 1, testsTotal); }
	public void make(String sccs, int from, int to)
	{ make(doHTML, createDirs, sccs, null, from, to); }
	public void make(String sccs, String category, int assertion, int to)
	{ make(doHTML, createDirs, sccs, null, 1, to); }
	public void make(String sccs, String category, int assertion, int from, int to)
	{ make(doHTML, createDirs, sccs, null, from, to); }

/**
 *   Use this call is you don't know how much tests will be generated from your template,
 *   or if you want to skip some tests.
 *   @see #make(boolean, boolean, String, String, int, int)
 */
	public void make(String sccs) {
		asrt = className.substring(0, className.length()-3);
		this.sccs = sccs;
		packageHead += "."+asrt;
		initAllTests();
		generateTemplateTableHead();
		startIndex = 1;
		int i = 1;
		while (generation)
			createTest(i++);
		generateTemplateTableTail();
	}

	public boolean generation = true;
	public boolean skipTest = false;

/**
 *   Call this method to start test generation. The method iterates thru
 *   specified number of generations, calling for each of them the
 *   createTest() method.
 *   @param doHTML if true, HTML will be created as well.
 *   @param createDirs if true, directoriy tree will be created.
 *   @param sccs first SCCS ident of source template for generation.
 *          This ident will be added to generated sources.
 *   @param packageInBetween in-between part of generated package name,
 *          e.g. 'classfmt.lmt', `aload`, etc.
 *   @param from number of the first test to be generated.
 *   @param to number of the last test to be generated.
 *   @see #createTest(int)
 */
	public void make(	boolean doHTML,
				boolean createDirs,
				String sccs,
				String packageInBetween,
				int from,
				int to)
	{
		this.doHTML=doHTML;
		this.createDirs=createDirs;
		this.sccs=sccs;

		if (className.endsWith("m"))
			asrt = className.substring(0, className.length()-3);
		else {
			makeTemplateHTML=false;
			asrt = className.substring(0, className.length()-2);
		}

		if (packageInBetween==null || packageInBetween.equals(""))
			packageHead+="." + asrt;
		else
			packageHead+="." + packageInBetween + "." + asrt;

		initAllTests();
		startIndex=from;
		generateTemplateTableHead();
		for (int i=from; i<=to; i++)
			createTest(i);
		generateTemplateTableTail();
	}

	public void initAllTests() {
		initTemplate();
		allSources		= new Vector();
		allKeywords		= new Vector();
		allTestNames		= new Vector();
		generation		= true;
	}

/**
 *   Method to be overridden for custom initialization of the test set to be generated.
 */
	public void initTemplate() {}

/**
 *   Routine to create sources for the test number testNumber.
 *   Normally, you neither call nor override this method.
 *   @param testNumber number of the test to create.
 */
	public void createTest(int testNumber) {
		generateTest(testNumber);
	}

	/**
         * Default implementation of the {@link #createTest(int)} method
	 *
	 * @param testNumber the number of test to generate
	 */
	public void generateTest(int testNumber) {
		preMakeTest(testNumber);
		newSource(true, test);
		packageName = packageHead+"."+test;
		if (!className.endsWith("m") && !packageHead.endsWith("."))	// subject to removal
			packageHead+=".";					// (inserted for backward
		if (!className.endsWith("m"))					// compatibility with
			packageName = packageHead+test;				// jck12beta3)

		makeTest();

		postMakeTest(testNumber);
		generateTemplateTable();
	}

/**
 *   Method to evaluate test name variable (macro) `test`. Returns evaluated value.
 *   After you call this method and till test number changes somehow, you may use
 *   result stored by this method in variable `test`.
 *   @return evauated value of #test
 */
	public String testName() {
		if (className.endsWith("m"))
			test = className + String.valueOf(testInt);
		else {
			test = asrt;
			String tmp=String.valueOf(testInt);
			for(int i=2; i>tmp.length(); i--)
				test+="0";
			test+=tmp;
		}
		return test;
	}

/**
 *   Creates directory with the name of the test (if it doesn't exist) and sets output file to
 *   fileName. You have to call this method when you have several source files in your test, to
 *   switch output to other file and to add (if needed) that file to source list in HTML.
 *   Note, that there's no way to append to previously created source. IOW, you can't switch
 *   to another source and then switch back to the first one.
 *   @param includeToSources tells if this file should be added to the sources list in HTML file.
 *   @param fileName name of the file to set the output to.
 *   @param extension extension to be used with the new output file, e.g. `c`, `html`, etc.
 *   @param dirName name of the directory to set the output to.
 */
	public void setOutput(boolean includeToSources, String fileName, String extension, String dirName) {
		super.setOutput(fileName);
		this.currentFileName = fileName+"."+extension;
		this.dirName = dirName;
		this.includeToSources = includeToSources;
	}

	public BufferedWriter createOutputWriter() throws IOException {
		String dir2Create = outputDir;
		if (outputDir==null || outputDir.equals(""))
			dir2Create = ".";
		if (createDirs && dirName!=null && dirName.length()>0)
			dir2Create += File.separator+dirName;
		File d = new File (dir2Create);
		if(!d.exists() && !mkdirs(d))
			throw new IOException("can't create dir: "+dir2Create);
		File outputFile = new File(dir2Create+File.separator+currentFileName);
		if (needSuite)
			findOutSuite(outputFile.getAbsolutePath());
		String tmp = currentFileName;
		if (sourceNameIsRelative) {
			String full_name = outputFile.toString();
			int pos = full_name.indexOf(test);
			tmp = full_name.substring(pos + test.length() + 1);
		}
		if (!currentFileName.endsWith("html"))
			filesSectionList.addElement(tmp);
		if (includeToSources) {
			sourceFiles.addElement(tmp);
			sourcesOfCurrentTest.addElement(outputFile);
		}
		return new BufferedWriter(new FileWriter(outputFile));
	}

	// the following definitions are needed for making references to
	// pos/neg images, used in HTML index of generated tests
	protected boolean needSuite = true;
	protected String suiteString = "unknown";
	protected void findOutSuite(String pathToFile) {
		final String testsTag = File.separator+"tests"+File.separator;
		int i = pathToFile.lastIndexOf(testsTag);
		if (i==-1)
			return;
		i += testsTag.length();
		int j = pathToFile.indexOf(File.separatorChar, i);
		if (j==-1)
			return;
		suiteString = pathToFile.substring(i, j);
		needSuite = false;
	}

	public void setOutput(boolean includeToSources, String fileName) {
		setOutput(includeToSources, fileName, defaultExtension, test);
	}

	public void setOutput(boolean includeToSources, String fileName, String extension) {
		setOutput(includeToSources, fileName, extension, test);
	}

	public void setOutput(String fileName) {
		setOutput(true, fileName, defaultExtension, test);
	}

	public void setOutput(String fileName, String extension) {
		setOutput(true, fileName, extension, test);
	}

/**
 *   Sets output to file `fileName.extension`, makes head.
 *   @param includeToSources parameter specifying if this source needs to be
 *          added to source list in HTML description.
 *   @param fileName name of the file to set the output to.
 *   @param extension extension of the file to set the output to.
 *   @param dirName name of the directory to set the output to.
 *   @see #setOutput
 *   @see #makeHead
 */
	public void newSource(	boolean includeToSources,
				String fileName,
				String extension,
				String dirName)
	{
		sourceNameIsRelative = false;
		setOutput(includeToSources, fileName, extension, dirName);
		beforeHead();
		makeHead();
	}

	public void newSource(	boolean includeToSources,
				String fileName,
				String extension)
	{ newSource(includeToSources, fileName, extension, test); }

	public void newSource(	boolean includeToSources,
				String fileName)
	{ newSource(includeToSources, fileName, defaultExtension, test); }

	public void newSource(	String fileName,
				String extension)
	{ newSource(true, fileName, extension, test); }

	public void newSource(	String fileName)
	{ newSource(true, fileName, defaultExtension, test); }

	/** creates new source file in current test's directory.
	 *  @param fileName name of the new source. May contain
	 *  subdirectories.
	 */
	public void newSourceInCurDir(String fileName)
	{
		int pos = fileName.lastIndexOf(File.separatorChar);
		if (pos == -1) {
			newSource(true, fileName, defaultExtension, test);
		} else {
			String dir = test + File.separator + fileName.substring(0, pos);
			sourceNameIsRelative = true;
			setOutput(true, fileName.substring(pos+1), defaultExtension, dir);
			beforeHead();
			makeHead();
		}
	}

/**
 *   Capitalizes first characted in given string.
 *   @param s a string to capitalize.
 */
	static public String capFirst(String s) {
		if (s == null)
			return null;
		return s.substring(0,1).toUpperCase()+s.substring(1);
	}

/**
 *   The main method to be implemented. Insert in this method all you want to
 *   insert to your main java source.
 */
	public void makeTest() {}

/**
 *   The method to be called by createTest to perform standard steps like initialization
 *   of variables defined in class JmppLibTest
 */
	public void preMakeTest(int testNumber) {
		skipTest		= false;
		testInt			= testNumber;
		tID			= testInt-startIndex;
		sourceFiles		= new Vector();
		filesSectionList	= new Vector();
		sourcesOfCurrentTest	= new Vector();
		testKind		= POSITIVE;
		keywords		= posCompilerRuntimeKeywords;
		executeArgs		= null;
		executeClass		= null;
		title			= null;
		description		= null;
		testComments		= null;
		testTimeout		= 0;
		test			= null;
		makeTags		= false;
		insertAutoGenerated	= true;
		tagMacro		= new DelayedMacro();
		tagMacro.value		= new String();
		properties = null;
		composeTest();
		if (test==null)
			test = testName();

	}

/**
 *   Method to be overridden for custom initialization of one single test to be generated.
 *   Vars like doHTML and makeTags may be initialized in desired manner.
 *   It may define, for example, name of the test to be generated, or something else.
 */
	public void composeTest() {}

	protected static final String defaultTitle="INSERT_TEST_TITLE_HERE";
	protected static final String defaultDescription="INSERT_TEST_DESCRIPTION_HERE";

/**
 *   The method to be called by createTest to perform standard steps after test is generated.
 */
	public void postMakeTest(int testNumber) {
		applyFilter();
		if (skipTest) {
			outBuffer = null;
			return;
		}

		if (title==null || title.length()==0)
			title=defaultTitle;
		if (description==null || description.length()==0)
			description=defaultDescription;

		if (executeClass==null)
			if (packageName != null && packageName.length() > 0)
				executeClass = packageName+"."+test;
			else
				executeClass = test;


		if (!rerunnable)				// if test is nonrerunnable
			keywords += " only_once";		// special keyword should be
								// added

		generateDescriptionTags();
		closeOut();
		makeHTML();
		allSources.addElement(sourcesOfCurrentTest);
		allKeywords.addElement(keywords);
		allTestNames.addElement(executeClass);
	}

	protected final java.util.GregorianCalendar c = new java.util.GregorianCalendar();
	protected final String dateString = DateFormat.getDateInstance(DateFormat.SHORT).format(new java.util.Date());



	protected static final String copyrightInfo =
        "Copyright (c) 2002, 2018 Oracle and/or its affiliates. All rights reserved.\n";

	public static final String copyrightInfoHtml =
        "&copy; 2002, 2018 Oracle and/or its affiliates. All rights reserved.<BR>" ;

        private static BuildPropertiesProvider
                defaultBPProvider = initBuildPropertiesProvider();

        private BuildPropertiesProvider
                bpProvider = defaultBPProvider;

        /**
         * BuildProperty provider interface.<br>
         * This interface is a bridge between JMPP library and
         * other B&I tools. It allows to avoid a dependency of JmppLibTest
         * from BuildProperties class.<br>
         * JmppLibTest provides its own implementation of this interface.
         * It will be used by default.
         */
        public interface BuildPropertiesProvider {

            /**
             * The name of the system property specifing
             * BuildPropertiesProvider implementation class name
             */
            public static final String CLASS_NAME_PROPERTY =
                "jmpp.JmppLibTest.BuildPropertiesProvider";

            /**
             * Returns the value of build property or null
             * if property is not set.
             */
            public String getBuildProperty(String name);

            /**
             * Returns the value of build property or 'default'
             * if property is not set.
             */
            public String getBuildProperty(String name, String defaultValue);

        }


        private static BuildPropertiesProvider initBuildPropertiesProvider() {

            String className = System.getProperty(
                    BuildPropertiesProvider.CLASS_NAME_PROPERTY);

            if ( className != null ) {
                try {
                    Class providerClass = Class.forName(className);
                    return (BuildPropertiesProvider)providerClass.newInstance();
                } catch (Exception e) {
                    System.err.println("Warning!\n" +
                        "Cannot create an instance of BuildPropertiesProvider: "
                        + e + "\nBuildPropertiesProvider is not set.");
                }
            }

            return null;
        }


        /**
         * Sets new BuildPropertiesProvider for the current JmppLibTest instance
         */
        public void setBuildPropertiesProvider(BuildPropertiesProvider bp) {
            bpProvider = bp;
        }

        /**
         * Returns BuildPropertiesProvider of the current JmppLibTest instance
         */
        public BuildPropertiesProvider getBuildPropertiesProvider() {
            return bpProvider;
        }


        /**
         * Sets BuildPropertiesProvider will be used for all new created
         * JmppLibTest instances by default. It's possible to set provider
         * for a concrete instance via setBuildPropertiesProvider() method.
         */
        public static void setDefaultBPProvider(BuildPropertiesProvider bp) {
            defaultBPProvider = bp;
        }

        /**
         * Returns BuildPropertiesProvider used for creating
         * new JmppLibTest instances by default.
         */
        public static BuildPropertiesProvider getDefaultBPProvider() {
            return defaultBPProvider;
        }


        /**
         * Returns copyrights to be inserted into the generated .java file.
         * <br>
         * Method output depends on whether BuildPropertiesProvider is set.
         * If yes, method returns "test.copyrightNotice" build property
         * value, otherwise default copyrights will be rerurned
         */
        public String getCopyrightInfo() {
            if (bpProvider != null) {
                return bpProvider.getBuildProperty
                    ("test.copyrightNotice", copyrightInfo);
            } else {
                return copyrightInfo;
            }
        }

        /**
         * Returns copyrights to be inserted into the generated .html file.
         * <br>
         * Method output depends on whether BuildPropertiesProvider is set.
         * If yes, method returns "html.copyrightNotice" build property
         * value, otherwise default html copyrights will be rerurned
         */
        public String getCopyrightInfoHtml() {
            return getCopyrightInfoHtml(tckCopyrightFile);
        }

        public String getCopyrightInfoHtml(String tck_crn_file) {
            String res = "";

            if (tck_crn_file != null) {
                res = "<a href=\"" + tck_crn_file + "\">Copyright</a> ";
            } else {
                res = "Copyright ";
            }
            if (bpProvider != null) {
                return res + bpProvider.getBuildProperty
                    ("html.copyrightNotice", copyrightInfoHtml);
            } else {
                return res + copyrightInfoHtml;
            }
        }

	protected void applyFilter() {
		if ((stepOneLib!=null) &&
		    ((JmppLibTest)stepOneLib).skipNegCompilerTests &&
		    (keywords.indexOf("negative")!=-1) &&
		    (keywords.indexOf("runtime")==-1) &&
		    (keywords.indexOf("execute")==-1))
			skipTest = true;
	}

    protected static String wrapInJavaCommentTags(String s) {
        if (s == null)
            return "/**/";
        String res = "/*\n";
        for(int ind = s.indexOf('\n'); ind >= 0;) {
            String substr = s.substring(0, ind);
            s = s.substring(ind + 1);
            ind = s.indexOf('\n');
            res += (substr.length() > 0 ? " * " + substr : " *") + "\n";
        }
        int len = s.length();
        if (len > 1 || len > 0 && s.charAt(0) != '\n') {
            res += " * " + s + "\n";
        }
        res += " */";
        return res;
    }

    protected static String replaceEOLs(String s, String r) {
        if (s == null)
            return null;
        String res = "";
        for (int i = s.indexOf('\n');
             i >= 0;
             res += s.substring(0, i) + r, s = s.substring(i + 1), i = s.indexOf('\n'));
        res += s;
        return res;
    }

	public void passDataTo(Object o) {
		super.passDataTo(o);
		JmppLibTest l = (JmppLibTest) o;
		l.doHTML		    = doHTML;
		l.allSources		= allSources;
		l.allKeywords		= allKeywords;
		l.allTestNames		= allTestNames;
		l.filesSectionList	= filesSectionList;
		l.sourceFiles		= sourceFiles;
		l.makeTemplateHTML	= makeTemplateHTML;
		l.createDirs		= createDirs;
		l.tdGenerator		= tdGenerator;
        l.tckCopyrightFile	= tckCopyrightFile;
        l.sccs          	= sccs;
	}

    /**
     *   Override this method to create your own header or skip creation of header at all.
     */
    public void makeHead() {
        String id = "Ident: @(#)" + currentFileName;
        if (insertAutoGenerated)
            id += " generated from: " + sccs;
        id += "\n\n";
        L(wrapInJavaCommentTags(id + getCopyrightInfo()));
        L("");
        LN(tagMacro.set());
    }

	public void sortFileArrayAccordingToSuppliedByTemplateSourceList() {
		Vector oldFileList = sourcesOfCurrentTest;
		if (oldFileList==null)
			return;
		if (oldFileList.size()<sourceList.length)
			// sourceList item number is larger than number of sources
			// included in sourcesOfCurrentTest Vector ... it's incorrect
			generationError(log, "incorrect source list in template.");
		sourcesOfCurrentTest = new Vector();
		Object o;
		for (int i=0; i<sourceList.length; i++) {
			for (int j=0; j<oldFileList.size(); j++) {
				o = oldFileList.elementAt(j);
				if (sourceList[i].equals(((File)o).getName()))
					sourcesOfCurrentTest.addElement(o);
			}
		}
		boolean found;
		for (int j=0; j<oldFileList.size(); j++) {
			found=false;
			o = oldFileList.elementAt(j);
			for (int i=0; i<sourceList.length; i++) {
				if (sourceList[i].equals(((File)o).getName()))
					found=true;
			}
			if (!found)
				sourcesOfCurrentTest.addElement(o);
		}
	}

/**
 *   Override this method to insert different user definitions
 *   in any place of your source template. This is called right before makeHead()
 *   @see #makeHead()
 */
	public void beforeHead() {}

/**
 *   The method uses TDGenerator instance which generates test descriptions.
 */
	public void makeHTML() {
		if (!doHTML || skipTest)
			return;
		if (tdGenerator != null){
			tdGenerator.makeTD(this);
		}
	}

// Definitions for generation of description tags.

/**
 *   If true, test description tags are generated into.
 *   @see #generateDescriptionTags()
 */
	public boolean makeTags;

/**
 *   Delayed macro for test description tags. Since we do not know if this is the last source of the test,
 *   we can't generate test description until we've got all the info (all the test sources, for example).
 *   That's why the mechanism of delayed macros is used.
 *   @see #generateDescriptionTags()
 */
	public DelayedMacro tagMacro;

/**
 *   Creates test description tags.
 */
	public void generateDescriptionTags() {
		if (!makeTags)
			return;

		// only one tag description per test
		makeTags = false;

		// make list of source files to insert to the HTML
		// add 1 for currentFileName, which is not in the sourceList yet
		// (until closeOut is called during the next setOutput())
		String sources[] = new String[sourceFiles.size() + 1];
		if (sourceList!=null) {
			sources=sourceList;
			sortFileArrayAccordingToSuppliedByTemplateSourceList();
		}
		else {
			sourceFiles.copyInto(sources);
			sources[sourceFiles.size()] = currentFileName;
		}
		String sourcesList="";
		for (int i=0; i<sources.length; i++)
			sourcesList += sources[i] + " ";
		sourcesList.trim();

		tagMacro.value  =
			"/**\n" +
			" * @test\n" +
			" * @title " + title + "\n" +
			" * @keywords " + keywords + "\n" +
			" * @source " + sourcesList + "\n" +
			" * @executeClass " + executeClass + "\n" +
			((executeArgs!=null) ? " * @executeArgs " + executeArgs + "\n" : "") +
			((testTimeout>0) ? " * @timeout " + testTimeout + "\n" : "") +
			((properties!=null) ? " * @properties " + properties + "\n" : "") +
			" */\n" +
			"\n";
	}

// Definitions for generation of template-related HTML follow.

    /**
     *   If true, template-related HTML file will be generated as well,
     *   containing table of tests generated from the given template.
     *   @see #generateTemplateTable()
     */
	public boolean makeTemplateHTML = true;

    /**
     *   String, containing template-related comments to be inserted into
     *   template-related HTML.
     */
	public String tmplComments;

    /**
     *   Variable to store StringBuffer output object for the index table.
     *   @see #generateTemplateTable()
     */
	public StringBuffer templateTableBuffer;

    /**
     * Name of the main TCK copyright file (if any). Assumed to be relative
     * to the current directory, where tests are generated.
     */
    protected String tckCopyrightFile;


/**
 *   Creates head of the table to be inserted to the assertion HTML or specification.
 */
	public void generateTemplateTableHead() {
		if (!makeTemplateHTML)
			return;
		StringBuffer savedBuffer = outBuffer;
		templateTableBuffer = new StringBuffer();
		outBuffer = templateTableBuffer;
        L("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 3.2//EN\">");
        L("<html>");
        L("<head>");
        L("<title>"+className+"</title>");
        L("</head>");
        L("<body>");
        L("<hr>");
        L("<p>");
        L("<b><big> TEST GROUP "+className+" </big></b>");
        L("<p>");
        L("<TABLE BORDER=1>");
        L("<TR>");
        L("<TD> Test </TD>");
        L("<TD> Description </TD>");
        L("</TR>");
		outBuffer = savedBuffer;
	}

/**
 *   Creates table to be inserted to the assertion HTML or specification.
 */
	public void generateTemplateTable() {
		if (!makeTemplateHTML || skipTest)
			return;
		StringBuffer savedBuffer = outBuffer;
		outBuffer = templateTableBuffer;
		String evenUpper = (!suiteString.equals("lang")) ? "../" : "";
        L("<TR>");
        L("<TD> <img src=\"../../../../" + evenUpper + "doc/glyphs/" +
          ((keywords.indexOf("negative") == -1) ? "pos" : "neg") +
          ".gif\"> <a href=\""+test+"/"+test+".html\"> "+test+" </a> </TD>");
        L("<TD> "+title+" </TD>");
        L("</TR>");
		outBuffer = savedBuffer;
	}

/**
 *   Creates tail of the table to be inserted to the assertion HTML or specification.
 */
	public void generateTemplateTableTail() {
		if (makeTemplateHTML) {
			setOutput(false, className, "html", null);
			outBuffer = templateTableBuffer;
            L("</TABLE>");
            L("<h3>COMMENTS</h3>");
            if (tmplComments!=null)
                L(tmplComments);
            L("<hr>");
            LN("<!-- File: @(#)"+className+".html");
            if (insertAutoGenerated)
                LN(" generated from: "+sccs);
            L("");
            L("Created by "+testDeveloper);
            L("-->");
            L(getCopyrightInfoHtml());
            L("</body>");
            L("</html>");
		}
		closeOut();
	}

    public String getWorkJavaDir() {
        return workJavaDir;
    }

    public String getCurrentFileName() {
        return currentFileName;
    }

    /**
     * @return full path to the source which will be generated
     *     on the next call to {@link com.sun.jmpp.JmppLib#closeOut() }.
     */
    public String getSourceToBeGenerated() {
        String out_dir = getOutputDir();
        out_dir = (out_dir == null || out_dir.equals("")) ? "." : out_dir;

        if (dirName != null && !dirName.equals("")) {
            out_dir += File.separator + dirName;
        }
        return out_dir + File.separator + getCurrentFileName();
    }

    /**
     * Parses given command line according to given descriptors of
     * all valid options. Invokes JmppLib's parseOptions(String[],OptionDescr[]),
     * checks the value of the OPT_NO_NEG_COMPILER option.
     * @param argv command line arguments
     * @param validOptions descriptors of all valid options.
     * @return an object describing all options set in the command line
     */
    protected Options parseOptions(String[] argv, OptionDescr[] validOptions) {
        Options opts = super.parseOptions(argv, validOptions);
        skipNegCompilerTests = opts.isSet(OPT_NO_NEG_COMPILER);
        return opts;
    }

    /**
     * Overriden JmppLib's method. Invokes super.getValidOptions(), adds
     * DSC_NO_NEG_COMPILER option to the list of valid options.
     * @return descriptors of all options which can be passed to this library
     */
    public OptionDescr[] getValidOptions() {
        OptionDescr[] superValidOpts = super.getValidOptions();
        OptionDescr[] validOpts = new OptionDescr[superValidOpts.length + 1];
        for (int i = 0; i < superValidOpts.length; ++i){
            validOpts[i] = superValidOpts[i];
        }
        validOpts[validOpts.length - 1] = DSC_NO_NEG_COMPILER;
        return validOpts;
    }

/**
 * The method gets necessary option values from the Hashtable, assignes them to
 * the corresponding variables.
 * @param hash a hashtable to get option values from
 */
        public void parseOptions(Hashtable hash) {
            String s = null;
            if ((s = (String)hash.get("-o")) != null){
                 outputDir = s;
            }
            if ((s = (String)hash.get("-w")) != null){
                 workJavaDir = s;
            }
            if ((s = (String)hash.get("-p")) != null){
                 doCompilation = s.equals("true") ? false : true;
            }
            if ((s = (String)hash.get("-nonegcompiler")) != null){
                 skipNegCompilerTests = s.equals("true");
            }
            if ((s = (String)hash.get("-inFile")) != null){
                 inName = s;
	         int lastSlash = inName.lastIndexOf(File.separator);
                 if (lastSlash != -1) {
                     inDir = inName.substring(0, lastSlash + 1);
                     inName = inName.substring(lastSlash + 1);
                 }
            }
        }

	protected boolean skipNegCompilerTests = false;

// Implementation of JmppLibrary, needed solely for JmppScript

	public void setOutputDir(String s) { outputDir = s; }
	public void setWorkJavaDir(String s) { workJavaDir = s; }
	public void setInFile(File f) { inFile = f; inName = f.getName(); }
	public void setInDir(String s) { inDir = s; }
	public void setScript(JmppScript jmppScript) {}
	public String getTemplatePackage() { return templatePackage; }
	public Vector getAllKeywords() { return allKeywords; }
	public Vector getAllTestNames() { return allTestNames; }
	public Vector getAllSources() { return allSources; }

/**
 * Set this variable to true to specify the old-style macros should be processed.
 */
        {
            processOldMacroInTextBlocks = true;
        }

/**
 * JmppLib's overriden method to initialize test description generator. It initializes
 * TDGenerator.
 */
        public void generate() {
            if (tdGenerator == null){
                initTDGenerator();
            }
            super.generate();
        }

/**
 * The method is overriden just to make it public, since libraries use it
 * applying to JmppLibTest instances, in new JmppLib the method is protected.
 */
        public void LN(String s) {
            super.LN(s);
        }

/**
 * The method is overriden just to make it public, since libraries use it
 * applying to JmppLibTest instances, in new JmppLib the method is protected.
 */
        public void L(String s) {
            super.L(s);
        }

/**
 * Initializes test description generator. Should be overriden in sub-libraries.
 */
        protected void initTDGenerator(){
            return;
        }
/**
 * The method is overriden just to make it public, since libraries use it
 * applying to JmppLibTest instances, in new JmppLib the method is protected.
 */
        public void runXLib() {
            super.runXLib();
        }

/**
 * The method is overriden just to make it public, since libraries use it
 * applying to JmppLibTest instances, in new JmppLib the method is protected.
 */
        public void runXLib(Class generatorClass) throws Exception {
            super.runXLib(generatorClass);
        }

/**
 * The method throws runtime exception JmppException constructed with the given string.
 */
	public void generationError(String msg) {
		String message = getClass().getName() + ": generation failed \n";
		message += "Reason: " + msg;
		throw new JmppException(message);
	}

/**
 * The method throws runtime exception JmppException constructed with the given Throwable.
 */
	public void generationError(Throwable e) {
                throw new JmppException(e);
	}


// The methods throw runtime exception JmppException constructed with the given string/throwable.
// The methods are declared just for compatibility with the existing libraries and templates.

	public void generationError(PrintWriter out, String msg) {
	    generationError(msg);
	}

	public void generationError(PrintStream out, String msg) {
	    generationError(msg);
	}

	public void generationError(PrintWriter out, Throwable e) {
	    generationError(e);
	}

	public void generationError(PrintStream out, Throwable e) {
	    generationError(e);
	}
}


