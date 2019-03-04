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

package com.sun.jck.utils.jtxedit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import com.sun.javatest.ExcludeList;
import com.sun.javatest.util.BackupPolicy;
import com.sun.javatest.util.StringArray;

public class Main {
    public class BadArgs extends Exception {
	BadArgs(String msg) {
	    super(msg);
	}
    }
    
    public class Fault extends Exception {
	Fault(String msg) {
	    super(msg);
	}
    }

    public static void main(String[] args) {
	if(args.length > 0)
	    if(args[0].equals("-usage") || args[0].equals("-help") || args[0].equals("-h") || args[0].equals("/?")) {
		usage();
		System.exit(0);
	    }

	Main m = new Main();
	try {
	    m.run(args);
	}
	catch (BadArgs e) {
	    System.err.println(e.getMessage());
	    usage();
	    System.exit(1);
	}
	catch (Fault e) {
	    System.err.println(e.getMessage());
	    System.exit(1);
	}
    }

    public static void usage() {
	String prog = System.getProperty("program", "java " + Main.class.getName());
	System.err.println("Usage:");
	System.err.println("  " + prog + " [options] [infile]");
	System.err.println("Options are:");
	System.err.println("  -o outfile                                    output file");
	System.err.println("  -a 'url [bugids [platforms [synopsis]]]'      add entry");
	System.err.println("  -c 'url [bugids [platforms [synopsis]]]'      change entry");
	System.err.println("  -m file                                       merge another exclude file");
	System.err.println("  -r 'url'                                      remove entry");
	System.err.println("  -f cmdfile                                    read commands from file");
    }

    public void run(String[] args) throws BadArgs, Fault {

	// Set backup parameters; in time this might become more versatile.
	BackupPolicy backupPolicy = new BackupPolicy() {
	    public int getNumBackupsToKeep(File file) {
		return numBackupsToKeep;
	    }
	    public boolean isBackupRequired(File file) {
		if (ignoreExtns != null) {
		    for (int i = 0; i < ignoreExtns.length; i++) {
			if (file.getPath().endsWith(ignoreExtns[i]))
			    return false;
		    }
		}
		return true;
	    }
	    private int numBackupsToKeep = Integer.getInteger("backup.count", 5).intValue();
	    private String[] ignoreExtns = StringArray.split(System.getProperty("backup.ignore", ".jtr"));
	};

	Vector ops = new Vector();
	File inFile = null;
	File outFile = null;

	for (int i = 0; i < args.length; i++) {
	    if (args[i].equals("-a") && (i + 1 < args.length)) {
		ops.addElement(ADD);
		ops.addElement(parseEntry(args[++i]));
	    }
	    else if (args[i].equals("-c") && (i + 1 < args.length)) {
		ops.addElement(CHANGE);
		ops.addElement(parseEntry(args[++i]));
	    }
	    else if (args[i].equals("-f") && (i + 1 < args.length)) 
		read(new File(args[++i]), ops);
	    else if (args[i].equals("-m") && (i + 1 < args.length)) {
		ops.addElement(MERGE);
		ops.addElement(new File(args[++i]));
	    }
	    else if (args[i].equals("-o") && (i + 1 < args.length)) 
		outFile = new File(args[++i]);
	    else if (args[i].equals("-r") && (i + 1 < args.length)) {
		ops.addElement(REMOVE);
		ops.addElement(parseEntry(args[++i]));
	    }
	    else if (i == args.length - 1 && !args[i].startsWith("-")) 
		inFile = new File(args[i]);
	    else
		throw new BadArgs("Unrecognized argument: " + args[i]);
	}

	if (inFile == null && outFile == null) {
	    throw new BadArgs("no input or output file specified");
	}
	else if (outFile == null)
	    outFile = inFile;

	try {
	    ExcludeList t;
	    if (inFile == null)
		t = new ExcludeList();
	    else {
		try {
		    t = new ExcludeList(inFile);
		}
		catch (FileNotFoundException e) {
		    throw new Fault("can't find file: " + inFile);
		}
		catch (IOException e) {
		    throw new Fault("problem opening file: " + inFile + ": " + e);
		}
	    }

	    for (int i = 0; i < ops.size(); i+= 2) {
		String op = (String)(ops.elementAt(i));

		if (op.endsWith(":"))
		    op = op.substring(0, op.length() - 1).trim();
		
		if (op.equalsIgnoreCase(ADD)) {
		    ExcludeList.Entry e = (ExcludeList.Entry)(ops.elementAt(i+1));
		    t.addEntry(e);
		}
		else if (op.equalsIgnoreCase(CHANGE)) {
		    ExcludeList.Entry e = (ExcludeList.Entry)(ops.elementAt(i+1));
		    ExcludeList.Entry old = 
			t.getEntry(e.getRelativeURL(), e.getTestCases());
		    if (old != null)
			t.removeEntry(old);
		    t.addEntry(e);
		}
		else if (op.equalsIgnoreCase(REMOVE)) {
		    ExcludeList.Entry e = (ExcludeList.Entry)(ops.elementAt(i+1));
		    ExcludeList.Entry old = 
			t.getEntry(e.getRelativeURL(), e.getTestCases());
		    if (old != null)
			t.removeEntry(old);
		}
		else if (op.equalsIgnoreCase(MERGE)) {
		    File f = (File) (ops.elementAt(i+1));
		    try {
			ExcludeList mt = new ExcludeList(f);
			t.merge(mt);
		    }
		    catch (FileNotFoundException e) {
			throw new Fault("can't find file: " + f);
		    }
		    catch (IOException e) {
			throw new Fault("problem opening file: " + f + ": " + e);
		    }
		}
	    }

	    backupPolicy.backup(outFile);
	    t.write(outFile);
	}
	catch (ExcludeList.Fault e) {
	    throw new Fault(e.getMessage());
	}
	catch (IOException e) {
	    throw new Fault("problem opening file: " + e);
	}
    }

    private void read(File f, Vector ops) throws Fault {
	try {
	    BufferedReader r = new BufferedReader(new FileReader(f));
	    String line;
	    String mode = ADD;
	    while ((line = r.readLine()) != null) {
		line = line.trim();
		if (line.length() == 0 || line.startsWith("#"))
		    continue;
		
		if (line.equals(ADD))
		    mode = ADD;
		else if (line.equals(CHANGE))
		    mode = CHANGE;
		else if (line.equals(REMOVE))
		    mode = REMOVE;
		else if (line.equals(MERGE))
		    mode = MERGE;
		else {
		    ops.addElement(mode);
		    if (mode == MERGE)
			ops.addElement(new File(line));
		    else
			ops.addElement(parseEntry(line));
		}
	    }
	}
	catch (FileNotFoundException e) {
	    throw new Fault("Cannot find file " + f);
	}
	catch (IOException e) {
	    throw new Fault("Problem reading file " + f + "; " + e);
	}
    }

    private ExcludeList.Entry parseEntry(String line) throws Fault {
	try {
	    return ExcludeList.Entry.read(line);
	}
	catch (ExcludeList.Fault e) {
	    throw new Fault("Problem parsing entry: `" + line + "': " + e);
	}
    }

    private static final String ADD = "add";
    private static final String CHANGE = "change";
    private static final String MERGE = "merge";
    private static final String REMOVE = "remove";

}

