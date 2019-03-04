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
 *
 * */

package javasoft.sqe.javatest.lib;

import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.sun.javatest.JavaTestError;
import com.sun.javatest.Script;
import com.sun.javatest.Status;
import com.sun.javatest.TestResult;
import com.sun.javatest.TestDescription;
import com.sun.javatest.TestEnvironment;
import com.sun.javatest.util.BackupPolicy;
import com.sun.javatest.util.StringArray;

/**
 * Default script, which delegates to one of a number of scripts defined in
 * environment entries, according to the keywords on the test description.
 */
public class KeywordScript extends Script 
{
    /** Run the script.
     *
     * @param env	The test environment giving the details of how to run the test
     * @param log	A reporting channel to report messages and errors while running
     *			the test
     * @param work      The working directory for results and logging.
     * @param refPath   Where to find previous results to compare against in the
     *                  event that golden-files are being used.
     * @return		The result of running the script.
     */
    public void run() { 

        PrintWriter trOut = getTestResult().getTestCommentWriter();
        TestDescription td = getTestDescription();

	for (int i = 0; i < scriptArgs.length; i++) {
	    if (scriptArgs[i].equals("-debug"))
		debug = true;
	    else {
                setStatus(Status.error("bad args for script: " + scriptArgs[i]));
		return;
            }   // else
	}   // for

	String prefix = "script.";
	Set testKeys = td.getKeywordTable();
	Vector choices = new Vector(); // the set of choices
	Vector matches = new Vector(); // the set of matches
	int wordsMatchingInMatches = 0;// the number of words matching

    findMatch:
	for (Iterator iter = env.keys().iterator(); iter.hasNext(); ) {
	    String key = (String) (iter.next());

	    // if the key does not begin with the `script.' prefix, ignore key
	    if (!key.startsWith(prefix)) 
		continue;

	    if (debug)
		trOut.println("CHECKING " + key);

	    String keyList = key.substring(prefix.length()).replace('_', ' ').toLowerCase();
	    String[] keys = StringArray.split(keyList);

	    choices.addElement(keyList);

	    if (debug)
		trOut.println("keys: " + StringArray.join(keys));

	    // if there are no words after the `script.' prefix,
	    // or if it has fewer words than the best match so far, ignore key
	    if (keys == null || keys.length < wordsMatchingInMatches) 
		continue;

	    for (int i = 0; i < keys.length; i++) {
		// if key has a word that is not for the test, ignore key
		if (!testKeys.contains(keys[i])) {

		    if (debug)
			trOut.println("discarding, because of " + keys[i]);

		    continue findMatch;
		}
	    }

	    // see if key is better than best so far
	    if (keys.length > wordsMatchingInMatches) {
		// update best so far

		if (debug)
		    trOut.println("new best match, " + keys.length + " keys");

		matches = new Vector();
		wordsMatchingInMatches = keys.length;
	    } 
	    
	    // this key deserves note
	    matches.addElement(key);
	}   // for

	// check we have a unique script selected
	String name = env.getName();
	String envName = (name.length() == 0 ? 
			  "The anonymous environment" : 
			  "Environment `" + env.getName() + "'");
	if (matches.size() == 0) {
	    if (choices.size() == 0) {
		String s = envName + " has no `script' entries";
		trOut.println(s);
		setStatus(Status.error(s));
		return;
	    }
	    else {
		String s = envName + " has no suitable `script' entry";
		trOut.println(s);
		trOut.println("The keyword combinations for scripts in this environment are: ");
		for (int i = 0; i < choices.size(); i++) {
		    trOut.println((String)choices.elementAt(i));
                }   // for

		setStatus(Status.error(s));
		return;
	    }   // inner else
	} else if (matches.size() > 1) {
	    String s = envName + " has ambiguous `script' entries";
	    trOut.println(s);
	    for (int i = 0; i < matches.size(); i++) {
		trOut.println(i + ": " + matches.elementAt(i));
            }   // for

	    setStatus(Status.error(s));
	    return;
	}   // else if

	String bestScript = (String)matches.elementAt(0);
	//trOut.report.println("BEST " + bestScript);

        try {
	    String[] command = env.lookup(bestScript);
	    if (command.length == 0) {
		String s = "INTERNAL ERROR: failed to lookup key: " + bestScript;
		trOut.println(s);
		setStatus(Status.error(s));
		return;
	    }

	    trOut.println("test: " + td.getRootRelativeURL());
	    trOut.println("script: " + this.getClass().getName() + " " +
			  StringArray.join(scriptArgs));
	    
	    String[] msgs = {
		"Based on these keywords:    " + 
		bestScript.substring(prefix.length()).replace('_', ' ').toLowerCase(),
		"this script has now been selected: " + "   " +
		StringArray.join(command) };
	    printStrArr(trOut, msgs);
	    
	    try {
		Class c = Class.forName(command[0]);

		Script script = (Script)c.newInstance();
		String[] scriptArgs = new String[command.length - 1];
		System.arraycopy(command, 1, scriptArgs, 0, scriptArgs.length);
		initDelegate(script, scriptArgs);

		script.run();
	    }
	    catch (ClassNotFoundException ex) {
		setStatus(Status.error("Can't find class `" +
                                     command[0] + "' for `" + env.getName() + "'"));
	    } 
	    catch (IllegalAccessException ex) {
		setStatus(Status.error("Illegal access to class `" +
                                     command[0] + "' for `" + env.getName() + "'"));
	    } 
	    catch (InstantiationException ex) {
		setStatus(Status.error("Can't instantiate class`" +
                                     command[0] + "' for `" + env.getName() + "'"));
	    }
	} 
	catch (TestEnvironment.Fault ex) {
	    setStatus(Status.error("environment `" +
                                      env.getName() +
                                      "' has bad `script' entry for `" +
                                      bestScript +"'"));
	}
    }

    public Status run(String[] args, TestDescription td, TestEnvironment env) {
        throw new Error("Method not applicable.");
    }

    private static void printStrArr(PrintWriter pw, String[] data) {
        if(data == null) return;

        for(int i = 0; i < data.length; i++) {
            pw.println(data[i]);
        }
    }

    private void setStatus(Status s) {
	TestResult tr = getTestResult();
	tr.setEnvironment(env);
	tr.setStatus(s);
        try {
            tr.writeResults(workDir, backupPolicy);
        }
	catch (IOException e) {
	    if (debug)
		e.printStackTrace();
	    throw new JavaTestError("Unable to write test result to " + workDir.getPath());
        }
    }

    private static boolean debug = false;
}
