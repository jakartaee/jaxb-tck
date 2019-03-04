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

package com.sun.tgxml.tjtf.tools;

// <importgen> Generated imports for class: cftools.ClassWriter
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.tools.options.Option;
import com.sun.tgxml.tjtf.tools.options.HelpOption;
import com.sun.tgxml.tjtf.tools.options.FlagOption;
import com.sun.tgxml.tjtf.tools.options.StringOption;
import com.sun.tgxml.tjtf.tools.options.ArgumentDecoder;
import com.sun.tgxml.tjtf.tools.options.HelpOptionException;
import com.sun.tgxml.tjtf.tools.options.ParseArgumentException;
import com.sun.tgxml.tjtf.tools.options.StandardOptionHandler;
import com.sun.tgxml.tjtf.tools.options.OperandsValidator;
import com.sun.tgxml.tjtf.tools.options.DefaultOperandsValidator;
import java.io.*;
import java.util.ArrayList;
// </importgen>
 
 
/** 
 * ToolBase -
 *   This is the lowest base-class for implementing a tool-shell. 
 *   ToolBase handles standards for command-line arg parsing, 
 *   Error handling, and debugging.
 * 
 * 
 * @version 	1.0, 10/02/97 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    ToolBase 
 * ============================================================================================ 
 */ 
public class ToolBase extends StandardOptionHandler implements Shell {
 
   /* 
    * ============================================================================================ 
    *    Member Fields 
    * ============================================================================================ 
    */ 
 

            /** Print Strings   */
    private static final String str_Term = System.getProperty("line.separator");


    //-------------------------------------------------------
    // CommandLine options

    private static final String ctStr_arg_logging = LibResHandler.getResStr("basetool.loggingarg.mnem"); 
    private static final String ctStr_arg_logfile = LibResHandler.getResStr("basetool.logfilearg.mnem"); 
    private static final String ctStr_arg_verbose = LibResHandler.getResStr("basetool.verbosearg.mnem"); 
    private static final String ctStr_arg_version = LibResHandler.getResStr("basetool.versionarg.mnem"); 
    private static final String ctStr_arg_help = LibResHandler.getResStr("basetool.helparg.mnem");
    private static final String ctStr_arg_help1 = LibResHandler.getResStr("basetool.helparg.mnem1");
    private static final String ctStr_arg_help2 = LibResHandler.getResStr("basetool.helparg.mnem2");
    private static final String ctStr_arg_help3 = LibResHandler.getResStr("basetool.helparg.mnem3");
    private static final String ctStr_arg_help4 = LibResHandler.getResStr("basetool.helparg.mnem4");
    private static final String ctStr_arg_help5 = LibResHandler.getResStr("basetool.helparg.mnem5");
    private static final String ctStr_arg_help6 = LibResHandler.getResStr("basetool.helparg.mnem6");

 

    protected String[] helpSwitches = 
        {ctStr_arg_help, ctStr_arg_help1, ctStr_arg_help2, ctStr_arg_help3, ctStr_arg_help4, ctStr_arg_help5, ctStr_arg_help6};

    protected HelpOption helpOption = new HelpOption(helpSwitches, 
        "  " + ctStr_arg_help + LibResHandler.getResStr("basetool.helparg.desc"), 
        OPTIONAL);

    protected FlagOption verboseOption = new FlagOption(
        new String[]{ctStr_arg_verbose, ctStr_arg_verbose},
        "  " + ctStr_arg_verbose + LibResHandler.getResStr("basetool.verbosearg.desc"), 
        OPTIONAL);

    protected FlagOption versionOption = new FlagOption(ctStr_arg_version,
        "  " + ctStr_arg_version + LibResHandler.getResStr("basetool.versionarg.desc"),
        OPTIONAL);

    protected FlagOption enableLogingOption = new FlagOption(ctStr_arg_logging,
        "  " + ctStr_arg_logging + LibResHandler.getResStr("basetool.loggingarg.desc"),
        OPTIONAL);

    protected StringOption logFileOption = new StringOption(ctStr_arg_logfile,
        "  " + ctStr_arg_logfile + LibResHandler.getResStr("basetool.logfilearg.desc"),
        OPTIONAL);


    //-------------------------------------------------------
    //  other fields

    /** Log file write retry limit.  */
    public static final int ctInt_log_retryTimes = 5;

    /** Default Return Code.  */
    public static final int ctInt_ErrorCode_NoError = 0;
    /** Error Return Code.  */
    public static final int ctInt_ErrorCode_Error = 1;

   /** Default Precedence:  break the program when a class with the same name.  */
    private static int m_ResultCode = ctInt_ErrorCode_NoError;

    
    /** Default output stream.  */
    private static PrintStream m_standardOut;

    /** Default error stream.  */
    private static PrintStream m_standardErr;


    /** Program name string.  */
    private String progname = LibResHandler.getResStr("basetool.name");

    /** Program version string.  */
    private String m_versionStr = LibResHandler.getResStr("basetool.version");


    /** Present verbose information.  */
    protected boolean m_verbose;
    /** Only give the version information.  */
    protected boolean m_version;

    /** Does this program require any command line arguments (GUI program's do not).  */
    protected boolean m_needsCommandLineArguments;

    /** Does this program allow verbose output messages.  */
    protected boolean m_handlesVerbose;

    /** Does this program allow verbose output messages.  */
    protected boolean m_loggingEnabled;

    /** Does this program allow verbose output messages.  */
    protected String m_logFile;



    /** ArgumentDecoder used to parse arguments */
    protected final ArgumentDecoder decoder = new ArgumentDecoder();

    /** Tool operands will be initialized by decoder after args parsing */
    protected String[] operands = null;

    /** OperandsValidator will be used to validate tool operands */
    protected OperandsValidator operandsValidator = null;

    /** Tool usage header, used to print tool usage info */
    protected String toolUsageHeader = "";

    /** Tool usage bottom, used to print tool usage info */
    protected String toolUsageButtom = "";


   /* 
    * ============================================================================================ 
    *    Methods 
    * ============================================================================================ 
    */ 
    
    /**
     * All sub-classes must override the main.
     *  They should replace the creation of "ToolBase" with the name of their sub-class.
     * <p>
     * @param args The command line arguments to  this tool.
     */

    public static void main(String args[]) {
        ToolBase c = new ToolBase(System.out, System.err);
        System.exit(c.run(args));
    }

   /** 
    * Calls BuildProperties.loadProperties(), setup() and doIt() methods
    * @return exit status of a tool.
    */
    public final int run(String args[]) {
        try {
            BuildProperties.loadProperties();
	    setup();
	    doIt(args);
        } catch (Exception e) {
	    handleShellException(e);
        }
        return getResultCode();
    }

   /**
    * Prints usage info if ParseArgumentException is thrown.
    * Sub-classes should override this method to handle 
    * other specific exceptions.
    */
    protected void handleShellException(Exception e) {
	if (e instanceof HelpOptionException) {
            usage();
	} else if (e instanceof ParseArgumentException) {
            setResultCode(ctInt_ErrorCode_Error);
            getStandardErr().println(e.getMessage());
            usage();
        } else {
            setResultCode(ctInt_ErrorCode_Error);
            System.err.println(e);
            e.printStackTrace(System.err);
        }
    }

    /* 
     * -----------------------------------------------------------
     * -----------------------------------------------------------
     *    Public Methods (outside world can call these)
     * -----------------------------------------------------------
     * -----------------------------------------------------------
     */ 
 
   /* 
    * -------------------------------------------------------------------
    *    Constructors 
    * -------------------------------------------------------------------
    */ 

    /** Constructor (canon.)
     *  
     *  constructs the ToolBase base class.
     *
     * @param out The print stream for writing program information.
     * @param err The print stream for error diagnostics.
     *
     * @see java.io.PrintStream 
     */
    public ToolBase ( PrintStream out, PrintStream err) { 
        setStandardOut(out);
        setStandardErr(err);
	init();
    } 

    /** Constructor (canon.)
     *  
     *  constructs the ToolBase with a program name.
     *
     * @param out The print stream for writing program information.
     * @param err The print stream for error diagnostics.
     * @param name The name of this (sub-class specific) tool.
     *
     * @see java.io.PrintStream 
     */
    public ToolBase ( PrintStream out, PrintStream err, String name) { 
        this(out, err);
	progname = name;
    } 

 
   /* 
    * -------------------------------------------------------------------
    *    Getters/Setters 
    * -------------------------------------------------------------------
    */ 


    /** 
     *  
     *  Get the out stream.
     * @return an output stream (for normal tool messages).
     * @see #setStandardOut
      */
    public PrintStream getStandardOut() {
	return m_standardOut;
    }
 

    /** 
     *  
     *  Set the out stream.
     * @param outStream an output stream (for normal tool messages).
     * @see #getStandardOut
      */
    public void setStandardOut(PrintStream outStream) {
	m_standardOut = outStream;
    }
 

    /** 
     *  
     *  Get the out stream.
     * @return an output stream (for tool error messages).
     * @see #setStandardErr
     */
    public PrintStream getStandardErr() {
	return m_standardErr;
    }
 
    

    /** 
     *  
     *  Set the out stream.
     * @param errStream an output stream (for (tool) error messages).
     * @see #getStandardErr
      */
    public void setStandardErr(PrintStream errStream) {
	m_standardErr = errStream;
    }



  /**
    * Get a string that describes this tool's name.
    * <p> 
    * @return a String with the tool name.
     * @see #setProgramName
    */
    public final String getProgramName() {
	return progname;
    }

  /**
    * Set this tool's name.
    * <p> 
    * @param name A String with the tool name.
     * @see #getProgramName
    */
    public final void setProgramName(String name) {
        progname = name;
    }


  /**
    * Get a string that describes this tool's name.
    * <p> 
    * @return a String with the tool name.
     * @see #setVersionString
    */
    public final String getVersionString() {
	return m_versionStr;
    }

  /**
    * Set this tool's name.
    * <p> 
    * @param version A String with the tool name.
     * @see #getVersionString
    */
    public final void setVersionString(String version) {
        m_versionStr = version;
    }



  /**
    * Get the (current) result-code that this tool returns to the invoking process.
    * <p> 
    * @return an int error-code.
     * @see #setResultCode
    */
    public final int getResultCode() {
	return m_ResultCode;
    }

  /**
    * Set the (current) result-code that this tool returns to the invoking process.
    * <p> 
    * @param errorCode an int error-code.
     * @see #getResultCode
    */
    public final void setResultCode(int errorCode) {
        m_ResultCode = errorCode;
    }

 
   /* 
    * -------------------------------------------------------------------
    *    I/O
    * -------------------------------------------------------------------
    */   

 
  /**
    * output a message.
    * @param msg the message to output.
    */
    public void reportOutputMsg(String msg) {
	getStandardOut().println(msg);
	// Always log output messages.
	try {
	    log(msg);
	} catch (TestFileException e) {
	    // just go on if log fails.
	}
    }


  /**
    * output an error.
    * @param msg the error message to output.
    */
    public void reportErrorMsg(String msg) {
	getStandardErr().println(msg);
	try {
	    // Log the message (only if logging to a file).
	    if (m_loggingEnabled) {
		if (m_logFile != null) {
		    logToFile(msg);
		}	
	    }
	} catch (TestFileException e) {
	    // just go on if log fails.
	}
    }

 
  /**
    * output a message.
    * @param msg the message to output.
    */
    public void log(String msg) throws TestFileException {
	if (m_loggingEnabled) {
	    if (m_logFile != null) {
		logToFile(msg);
	    } else {
		// log to StandardOut if a file is not specified
		getStandardOut().println(msg);
	    }
	}
    }

   /* 
    * -------------------------------------------------------------------
    *    Shell methods
    * -------------------------------------------------------------------
    */   
  /**
    * Start the tool.
    *<p>
    * This function initiates the argument verification, and
    * starts the tool-process functions
    * <p> 
    * @param args the commandline args.
    */
    public final void doIt(String args[]) 
            throws TestFileException, ParseArgumentException {

        setResultCode(ctInt_ErrorCode_NoError);

        decoder.resetOptions();

        operands = decoder.parseArguments(args);
        decoder.applyOptionsValues();

        _startTool();
        
    }


   /**
    * Setup ArgumentDecoder and the inner-components of a tool.
    *<p>
    */
    public final void setup() throws TestFileException, IOException {
	//	setupArguments();

	setupTool();
       
        // usage info setup
        setToolUsageHeader();
        setToolUsageBottom();

        // operands setup
        setOperandsValidator();

        // decoder setup
        decoder.setOperandsValidator(getOperandsValidator());
        decoder.setUsageHeader(getToolUsageHeader());            
        decoder.setUsageBottom(getToolUsageBottom());            
        decoder.addOptionHandler(this);
        decoder.registerOptions();

    }

  /**
    * Setup the inner-components of a tool.
    *<p>
    * Sub-classes override this method.
    * <p> 
    * If an error condition has been detected in the sub-class,
    * the tool must set an error-code: <br>
    *   i.e. setResultCode(ctInt_ErrorCode_Error);
    */
    public void setupTool() throws TestFileException, IOException {
    }

  /**
    * Start the tool.
    *<p>
    * Sub-classes override this method.
    * This method assumes that all argument 
    * verification has happened, and that
    * arguments have been parsed into local variables.
    * <p> 
    * If an error condition has been detected in the sub-class,
    * the tool must set an error-code: <br>
    *   i.e. setResultCode(ctInt_ErrorCode_Error);
    */
    public void startTool() {
    }


   /* 
    * ----------------------------------------------------------------------
    *    Options parsing methods 
    * ----------------------------------------------------------------------
    */



    /** 
     * Registers tool options at ArgumentDecoder. 
     * <p>Subclasses should override this method to register own options
     * <p> ArgumentDecoder uses FIFO order of options parsing:
     * first registered options will be used first for parsing.
     * Option are registered by <tt>decoder.addOption(toolOption)</tt> call.
     * To use options registered by superclass subclass should call 
     * <tt>super.registerOptions()</tt>.
     * Options registered before this call
     * will be processed before superclass options. 
     * Options registered after this call will be processed after
     * superclass options. 
     * If tool does not invoke <tt>super.registerOptions()</tt>
     * no superclass options will be registered.
     * <p><code>Notes: tool initialization work is doing by applyOptionsValues()
     * after decoding is done. So tool options objects should be defined outside 
     * of registerOptions() to be visible from applyOptionsValues() </code>
     */
    public void registerOptions() {

        addOption(helpOption);
        if (m_handlesVerbose) {
            addOption(verboseOption);
        }
        addOption(versionOption);
        addOption(enableLogingOption);
        addOption(logFileOption);

    }

    /**
     * Does tool initialization work
     * This method is invoked after all options parsing is done and
     * the set of registered Option objects are initialized.
     * Subclasses should override this method if to apply values of own options.
     * To apply values of superclass options <tt>super.applyOptionsValues()</tt>      
     * should be called from this method.
     */
    public void applyOptionsValues() throws ParseArgumentException {

        if (verboseOption.isSet()) {
            m_verbose = true;
        }
      
        if (versionOption.isSet()) {
            m_version = true;
        }

        if (enableLogingOption.isSet()) {
            m_loggingEnabled = true;
        }
     
        if (logFileOption.isSet()) {
            m_logFile = logFileOption.getStringValue();
        }

    }

    /* 
     * -----------------------------------------------------------
     * -----------------------------------------------------------
     *    Protected Methods (only sub-classes can call these)
     * -----------------------------------------------------------
     * -----------------------------------------------------------
     */ 

    /** 
     * Initialize the options. 
     * <p>
     * Tool Sub-classes should override this to initialize their state.
     * They should also make sure that they call "super.init();"
     */ 
     protected void init() { 
	m_handlesVerbose = false;
     } 
 

  /**
    * Print a banner (Tool name and version)
    * <p> 
    * sub-classes should override this to output their specific name and version.
    */
    protected void printHeader()  {
        if (m_verbose)
	  reportOutputMsg(LibResHandler.getResStr("basetool.version.message.default", getProgramName(), getVersionString()));
    }

    /**
     * Sets default operand validator that expects no operands.
     * This method is invoked before argument decoding starts.
     * Subclasses should override this method if they need to set own validator
     */
    protected void setOperandsValidator() {
        operandsValidator = new DefaultOperandsValidator(0, 0, null);
    }

    /**
     * Returns tool operand validator
     */
    protected OperandsValidator getOperandsValidator() {
        return operandsValidator;
    }

    /**
     * Sets default tool usage header
     * This method is invoked before argument decoding starts.
     * Subclasses should override this method if they need to set own usage header
     */
    protected void setToolUsageHeader() {
        toolUsageHeader = LibResHandler.getResStr("basetool.usage", getProgramName()) +
	    str_Term +
	    LibResHandler.getResStr("basetool.usage1");
    }

    /**
     * Returns tool usage header
     */
    protected String getToolUsageHeader() {
        return toolUsageHeader;
    }

    /**
     * Sets default tool usage header
     * This method is invoked before argument decoding starts.
     * Subclasses should override this method if they need to set own usage header
     */
    protected void setToolUsageBottom() {
        toolUsageButtom = null;
    }

    /**
     * Returns tool usage header
     */
    protected String getToolUsageBottom() {
        return toolUsageButtom;
    }
   
    /* 
     * -----------------------------------------------------------
     * -----------------------------------------------------------
     *    Private Methods 
     * -----------------------------------------------------------
     * -----------------------------------------------------------
     */ 
    

 
  /**
    * output a message.
    * @param msg the message to output.
    */
    public void logToFile(String msg) throws TestFileException {
	boolean append = false;

        File log = new File(m_logFile);

	if (log.exists()) {
	    if (! log.canRead())
		throw new TestFileException(LibResHandler.getResStr("basetool.log.cantread", getProgramName(), m_logFile));
	    
	    if (! log.canWrite())
		throw new TestFileException(LibResHandler.getResStr("basetool.log.cantwrite", getProgramName(), m_logFile));
	    
	    append = true;
	} 


	int retry = 0;
	boolean exit = false;
	while(! exit) {
	    try {
		FileOutputStream fos = new FileOutputStream(m_logFile, append);
		PrintStream out = new PrintStream(fos);
		out.println(msg);
		fos.flush();
		fos.getFD().sync();
		fos.close();
		exit = true;
	    } catch (IOException e) {
		retry++;
		if (retry > ctInt_log_retryTimes)
		    throw new TestFileException(LibResHandler.getResStr("basetool.log.retry.exceeded", getProgramName(), m_logFile));
		
		try {
		    wait(50);
		} catch (InterruptedException ie) {

		}
	    }
	    
	}
	
    }

   /**
    * Command to print the usage message to the output stream.
    */
    protected void usage()  {
        getStandardErr().println();
        decoder.setUsageHeader(getToolUsageHeader());
        decoder.printUsageInfo(getStandardErr());
        getStandardErr().println();
    }


  /**
    * Start the parsing process
    */
    private void _startTool( )  {
	startTool();
    }

}

