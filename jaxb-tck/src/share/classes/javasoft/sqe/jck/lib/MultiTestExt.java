/*
 * Copyright (c) 2005, 2020 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.jck.lib;

import javasoft.sqe.javatest.lib.MultiTest;
import javasoft.sqe.javatest.Status;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.StringTokenizer;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

/**
 * Extension of MultiTest class.
 * Contains TestFailException and static helpers assert... declarations.
 * Overrides MultiTest.decodeArg, MultiTest.invokeTestCase methods.
 */
public class MultiTestExt extends MultiTest {

    public static class TestFailureException extends RuntimeException {

        private static final long serialVersionUID = 3544388115143012657L;

        protected Throwable cause;

        /**
         * Construct a new TestFailException object that signals failure
         * with a corresponding message.
         *
         * @param description the string containing a comment
         */
        public TestFailureException(String message) {
            super(message);
        }

        public TestFailureException(String message, Throwable cause) {
            super(message);
            this.cause = cause;
        }

        public TestFailureException(Throwable cause) {
            super(cause.toString());
            this.cause = cause;
        }

        public Throwable getCause() {
            return cause;
        }

        public void printStackTrace() {
            if (cause != null) {
                cause.printStackTrace();
            }
            super.printStackTrace();
        }

        public void printStackTrace(PrintStream stream) {
            if (cause != null) {
                cause.printStackTrace(stream);
            }
            super.printStackTrace(stream);
        }

        public void printStackTrace(PrintWriter writer) {
            if (cause != null) {
                cause.printStackTrace(writer);
            }
            super.printStackTrace(writer);
        }
    }
	

    /**
     * Class path for the contextPath packages. Default value is "".
     */
    private String classPath;

    /**
     * get classPath.
     */
    public String getClassPath() {
        return classPath;
    }

    /**
     * Loader which is to load files of contextPath. If set,
     * newInstance(java.lang.String contextPath, java.lang.ClassLoader
     * classLoader) is used to load classes from classPtah specified by
     * -classpath option of the test, otherwise newInstance(java.lang.String
     * contextPath) is used.
     *
     * @see classPath, getClassPath(), getJAXBContext()
     */
    private ClassLoader classPathLoader;

    /**
     * get class loader which uses classpath specified by -classpath option.
     */
    public ClassLoader getClassPathLoader() {
      if (this.classPathLoader == null) {
        this.classPathLoader = Thread.currentThread().getContextClassLoader();
      }
      return this.classPathLoader;
    }

    /**
     * The package name(s) contains a list of Java package names that contain
     * implementation specific means for mapping XML document instances for the
     * specified schema vocabularies to Java content instances.
     */
    private String packageName = "";

 
	/**
	 * @param classPath the classPath to set
	 */
	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	/**
	 * @param packageName the packageName to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
     * get packageName.
     */
    protected String getPackageName() {
        return packageName;
    }

    
    /**
     * Writer for marshaler to write document, nullWriter if not set.
     */
    private Writer outWriter = new Writer() {
        // null writer
        public void write(char[] chars, int i, int i1) throws IOException {
            // do nothing
        }

        public void flush() throws IOException {
            // do nothing
        }

        public void close() throws IOException {
            // do nothing
        }
    };

    /**
     * get writer to marshal documents to.
     */
    public Writer getOut() {
        return outWriter;
    }
    
    /**
     * set writer to marshal document to. 
     */
    public void setOut(Writer  out) {
        this.outWriter = out;
    }

    /**
     * Returns new instance of JAXBContext. Uses package and class path if set.
     *
     * @see classPath, packageName
     */
    public JAXBContext getJAXBContext() throws JAXBException {
		int counter = 40;
		while (true) {
			try {
				return JAXBContext.newInstance(getPackageName(), getClassPathLoader());
			} catch (ClassFormatError ex) {
				if (--counter == 0)
					throw new JAXBException(ex.getMessage(), ex.getCause());
			}
		}
	}
    
    /**
     * Initializes test arguments: creates classPathLoader. Initializes test
     * arguments: document url. A setup method called after argument decoding is
     * complete, and before the test cases are executed. By default, it does
     * nothing; it may be overridden to provide additional behavior.
     *
     * @throws MultiTest.SetupException
     *             if processing should not continue. This may be due to some
     *             inconsistency in the arguments, or if it is determined the
     *             test should not execute for some reason.
     */
    protected void init() throws MultiTest.SetupException {
    	super.init();
        if ( getClassPath() != null) {
            StringTokenizer tokenizer = new StringTokenizer(getClassPath(), File.pathSeparator);
            int tokNum = tokenizer.countTokens();
            URL[] paths = new URL[tokNum];
            for (int i = 0; i < tokNum; ++i) {
                try {
                    paths[i] = new File(tokenizer.nextToken()).toURI().toURL();
                } catch (java.net.MalformedURLException e) {
                    throw new MultiTest.SetupException(e.toString());
                }
            }
            // Context ClassLoader was set in ExecTestSameJVM run()
    		this.classPathLoader = URLClassLoader.newInstance( paths, Thread.currentThread().getContextClassLoader());
        }
    }  
    
    /**
     * Overrides base class method. If test throws <code>TestFailException</code> exception method
     * translates this exception into <code>Status</code> value.
     *
     * @see MultiTest#invokeTestCase
     */
    protected Status invokeTestCase(java.lang.reflect.Method method) throws IllegalAccessException, InvocationTargetException {
        Status result;
        try {
            result = super.invokeTestCase(method);
        } catch (InvocationTargetException ex) {
            Throwable e = ex.getCause();
            if (e instanceof TestFailureException)
                return Status.failed(e.getMessage());
            throw ex;
        }
        return result;
    }

    /**
     * Throws <code>TestFailException</code> if <code>condition</code> is false.
     *
     * @param condition   if false exception <code>TestFailException</code> will be thrown.
     * @param description error description will be passed with exception.
     */
    public static void assertTrue(boolean condition, String description) {
        if (!condition)
            throw new TestFailureException(description);
    }

    /**
     * Throws <code>TestFailException</code> if objects <code>f</code> and <code>s</code> are not equal.
     *
     * @param f first object
     * @param s second object
     */
    public static void assertEquals(Object f, Object s, String description) {
        if (description == null)
            description = String.format("Objects '%s' and '%s' are not equal",
                    f.toString(), s.toString());
        assertTrue(f.equals(s), description);
    }

    /**
     * Overrides base class method.
     * Fills all public fields of <code>Parameter</code> type.
     *
     * @see MultiTest#decodeArg
     */
    protected int decodeArg(String argv[], int index) throws SetupException {
        int consumedArgsCount = processParameter(argv, index, this);
        if (consumedArgsCount != 0)
            return consumedArgsCount;
        return super.decodeArg(argv, index);
    }

    /**
     * This method processes next parameter in <code>argv</code> string
     * @param argv the command line string
     * @param idx the starting index in <code>argv</code> for the next parameter recognition
     * @param fieldsOwner object-container of <code>Parameter</code> fields
     * @return number of consumed array elements
     */
    public static int processParameter(String[] argv, int idx, Object fieldsOwner) {
        for (Class<?> curr = fieldsOwner.getClass(); curr != null; curr = curr.getSuperclass()) {
            for (Field f : curr.getFields()) {
                try {
                    Object fieldValue = f.get(fieldsOwner);
                    if (fieldValue instanceof Parameter) {
                        Parameter param = (Parameter) fieldValue;
                        String paramName = param.getName();
                        if (argv[idx].equals("-" + paramName)) {
                            String[] tail = new String[ argv.length - (idx + 1) ];
                            System.arraycopy(argv, argv.length - tail.length,
                                        tail, 0, tail.length);
                            return 1 + param.parseValue(tail);
                        }
                    }
                } catch (IllegalAccessException e) {
                    // error: getFields has to return only accessible fields
                    throw new Argument.InvalidValue( "internal error: " + e );
                }
            }
        }
        return 0; // parameter field isn't found
    }

    /**
     * This method parses command line represented by the <code>argv</code> parameter
     * @param argv the command line
     * @param fieldsOwner object-container of <code>Parameter</code> fields
     */
    public static void parseCommandLine(String[] argv, Object fieldsOwner) {
        int idx = 0;
        while (idx < argv.length) {
            int consumedArgsCount = processParameter(argv, idx, fieldsOwner);
            if (consumedArgsCount == 0)
                throw new Argument.InvalidValue(
                        String.format("Unknown token '%s'", argv[idx]));
            idx += consumedArgsCount;
        }
    }

    /**
     * This interface is the base interface for all arguments and parameters implementations.
     * Any argument or parameter has to provide implementation of parseValue method.
     */
    protected interface Argument {
        /**
         * this method has to extract parameter value from the list of strings.
         * It throws <code>InvalidValue</code> exception if parameter value can not be parsed.
         *
         * @param argv tail of test parameters string
         * @return number of consumed String's
         */
        public int parseValue(String[] argv);

        public static class InvalidValue extends RuntimeException {
			private static final long serialVersionUID = 1L;
			public InvalidValue(String msg) {
                super(msg);
            }
        }
    }

    /**
     * This class is a container for a specific value.
     * Generic parameter <code>T</code> is type of value.
     */
    public abstract static class ArgumentValue<T> implements Argument {
        /**
         * parameter value
         * The method <code>parseValue</code> has to fill this value by the <code>set</code> method.
         */
        protected T value = null;

        public T get() {
            return value;
        }

        protected void set(T val) {
            value = val;
        }
    }

    /**
     * The base interface for all parameters types. Every automatically filled named parameter
     * has to be public instance of <code>Parameter</code> subclass.
     */
    protected interface Parameter extends Argument {
        /**
         * This method returns name of parameter
         * @return name of parameter
         */
        public String getName();
    }

    /**
     * This class represents a specific parameter value.
     * Generic parameter <code>T</code> is type of parameter value.
     */
    public abstract static class ParameterValue<T> extends ArgumentValue<T> implements Parameter {
        /**
         * parameter name field
         */
        protected final String name;

        public ParameterValue(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        /**
         * This method has to be called in subclass parseValue(String[]) implementations
         * if corresponding ArgumentValue<T> class there exist
         * @param argv tail of test parameters string
         * @param argObj object of corresponding ArgumentValue<T> class
         * @return the same as the parseValue(String[]) method
         */
        protected int parseValue(String[] argv, ArgumentValue<T> argObj) {
            int count = argObj.parseValue(argv);
            set( argObj.get() );
            return count;
        }
    }
}
