/*
 * Copyright (c) 2001, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tools.elgen.javatest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

import com.sun.tgxml.tools.elgen.javatest.DynamicArray;

/**
 * A set of tests to be excluded from a test run.
 */

public class ExcludeTable
{
    /**
     * This exception is used to report problems manipulating an exclude table.
     */
    public static class Fault extends Exception
    {
	Fault(String s) {
	    super(s);
	}
    }

    /**
     * Test if a file appears to be for an exclude list, by checking the extension.
     * @param f The file to be tested.
     * @return <code>true</code if the file appears to be an exclude list.
     */
    public static boolean isExcludeFile(File f) {
	return f.getPath().endsWith(EXCLUDEFILE_EXTN);
    }

    /**
     * Create a new exclude table.
     */
    public ExcludeTable() {
	checksumState = GOOD_CHECKSUM;
    }

    /**
     * Create an ExcludeTable from the data contained in a file.
     * @param f The file to be read.
     * @throws FileNotFoundException if the file cannot be found
     * @throws IOException if any problems occur while reading the file
     * @throws ExcludeTable.Fault if the data in the file is ionconsistent
     * @see #ExcludeTable(File[])
     */
    public ExcludeTable(File f) 
	throws FileNotFoundException, IOException, Fault 
    {
	if (f != null) {
	    BufferedReader in = new BufferedReader(new FileReader(f));
	    Parser p = new Parser(in);
	    try {
		Entry e;
		while ((e = p.readEntry()) != null)
		    addEntry(e);
	    }
	    finally {
		in.close();
	    }
	    long givenChecksum = p.getChecksum();
	    if (givenChecksum == 0)
		checksumState = NO_CHECKSUM;
	    else {
		long actualChecksum = computeChecksum();
		checksumState = (givenChecksum == actualChecksum ? GOOD_CHECKSUM : BAD_CHECKSUM);
	    }
	    
	    title = p.getTitle();
	}
    }


    /**
     * Create an ExcludeTable from the data contained in a series of files.
     * @param files The file to be read.
     * @throws FileNotFoundException if any of the files cannot be found
     * @throws IOException if any problems occur while reading the files.
     * @throws ExcludeTable.Fault if the data in the files is ionconsistent
     * @see #ExcludeTable(File)
     */
    public ExcludeTable(File[] files) 
	throws FileNotFoundException, IOException, Fault 
    {
	checksumState = GOOD_CHECKSUM;
	for (int i = 0; i < files.length; i++) {
	    ExcludeTable et = new ExcludeTable(files[i]);
	    merge(et);
	}
    }


    /**
     * Test if a specific test is completely excluded according to the table.
     * It is completely excluded if there is an entry, and the test case field is null.
     * @param url The test-suite root-relative URL for the test.
     * @return <code>true</code> if the table contains an entry for this test.
     */
    public boolean excludesAllOf(String url) {
	Object o = table.get(new Key(url));
	return (o != null && o instanceof Entry && ((Entry)o).testCase == null);
    }

 
    /**
     * Test if a specific test is partially or completely excluded according to the table.
     * It is so excluded if there is any entry in the table for the test.
     * @param url The test-suite root-relative URL for the test.
     * @return <code>true</code> if the table contains an entry for this test.
     */
    public boolean excludesAnyOf(String url) {
	Object o = table.get(new Key(url));
	return (o != null);
    }

 
    /**
     * Add an entry to the table. 
     * @param e The entry to be added; if an entry already exists for this test
     *	description, it will be replaced.
     * @throws ExcludeTable.Fault if the entry is for the entire test and 
     *   there is already an entry for a test case for this test, or vice versa.
     */
    public void addEntry(Entry e) throws Fault {
	synchronized (table) {
	    Key key = new Key(e.relativeURL);
	    Object o = table.get(key);
	    if (o == null) {
		// easy case: nothing already exists in the table, so just
		// add this one
		table.put(key, e);
	    }
	    else if (o instanceof Entry) {
		// a single entry exists in the table, so need to check for
		// invalid combinations of test cases and tests
		Entry curr = (Entry)o;
		if (curr.testCase == null) {
		    if (e.testCase == null) 
			// overwrite existing entry for entire test
		    	table.put(key, e);
		    else 
			// can't exclude test case when entire test already excluded
			throw new Fault("can't exclude test case when entire test already excluded: " + e.relativeURL);
		}
		else {
		    if (e.testCase == null)
			// can't exclude entire test when test case already excluded
			throw new Fault("can't exclude entire test when test case already excluded: " + e.relativeURL);
		    else if (curr.testCase.equals(e.testCase))
			// overwrite existing entry for the same test case
			table.put(key, e);
		    else 
			// already excluded one test case, now we need to exclude
			// another; make an array to hold both entries against the
			// one key
			table.put(key, new Entry[] {curr, e});
		}
	    }
	    else {
		// if there is an array, it must be for unique test cases
		if (e.testCase == null) 
		    throw new Fault("can't exclude entire test when test case already excluded: " + e.relativeURL);

		Entry[] curr = (Entry[])o;
		for (int i = 0; i < curr.length; i++) {
		    if (curr[i].testCase.equals(e.testCase)) {
			curr[i] = e;
			return;
		    }
		}
		// must be a new test case, add it into the array
		table.put(key, DynamicArray.append(curr, e));
	    }
	    
	    checksumState = NO_CHECKSUM;
	}
    }

    /**
     * Locate an entry for a test.
     * @param url The root relative URL for the test; the URL may include
     * a test case if necessary included in square brackets after the URL proper.
     * @return The entry for the test, or null if there is none.
     */
    public Entry getEntry(String url) {
	String testCase = null;
	if (url.endsWith("]")) {
	    int i = url.lastIndexOf("[");
	    if (i != -1) {
		testCase = url.substring(i+1, url.length()-1);
		url = url.substring(0, i);
	    }
	}
	return getEntry(url, testCase);
    }

    /**
     * Locate an entry for a test.
     *
     * @param url The root relative URL for the test.
     * @param testCase An optional test case to be taken into account.  This cannot
     *  be a comma separated list.  A value of null will match any entry with the given
     *  url.
     * @return The entry for the test, or null if the URL cannot be found.
     */
    public Entry getEntry(String url, String testCase) {
	// XXX what if multiple entries?
	Key key = new Key(url);
	Object o = table.get(key);
	if (o == null)
	    return null;
	else if (o instanceof Entry) {
	    Entry e = (Entry)o;
	    if (testCase == null)
		return e;
	    else
		return (isInList(e.testCase, testCase) ? e : null);
	}
	else {
	    Entry[] entries = (Entry[])o;
	    for (int i = 0; i < entries.length; i++) {
		Entry e = entries[i];
		if (isInList(e.testCase, testCase))
		    return e;
	    }
	    return null;
	}
    }

    /**
     * Merge the contents of another exclude table into this one.
     * The individual entries are merged; the checksum state is set
     * to the "worse" of the two states. The title of the exclude table
     * being merged is ignored.
     * @param other the exclude table to be merged with this one.
     * 
     */
    public void merge(ExcludeTable other) {
	synchronized (table) {
	    for (Iterator iter = other.getIterator(false); iter.hasNext(); ) {
		Entry otherEntry = (Entry) (iter.next());
		Key key = new Key(otherEntry.relativeURL);
		Object o = table.get(key);
		if (o == null) {
		    // Easy case: nothing already exists in the table, so just
		    // add this one
		    table.put(key, otherEntry);
		}
		else if (o instanceof Entry) {
		    // A single entry exists in the table
		    Entry curr = (Entry)o;
		    if (curr.testCase == null || otherEntry.testCase == null) {
			table.put(key, new Entry(curr.relativeURL, null, 
					    mergeBugIds(curr.bugIds, otherEntry.bugIds),
					    mergePlatforms(curr.platforms, otherEntry.platforms),
					    mergeSynopsis(curr.synopsis, otherEntry.synopsis)));
		    }
		    else 
			table.put(key, new Entry[] {curr, otherEntry});
		}
		else if (otherEntry.testCase == null) {
		    // An array of test cases exist in the table, but we're merging
		    // an entry for the complete test, so flatten down to a single entry
		    // for the whole test
		    int[] bugIds = otherEntry.bugIds;
		    String[] platforms = otherEntry.platforms;
		    String synopsis = otherEntry.synopsis;
		    Entry[] curr = (Entry[])o;
		    for (int i = 0; i < curr.length; i++) {
			bugIds = mergeBugIds(bugIds, curr[i].bugIds);
			platforms = mergePlatforms(platforms, curr[i].platforms);
			synopsis = mergeSynopsis(synopsis, curr[i].synopsis);
		    }
		    table.put(key, new Entry(otherEntry.relativeURL, null,
					     bugIds, platforms, synopsis));
		}
		else {
		    // An array of test cases exist in the table, and we're merging
		    // an entry with another set of test cases.
		    // For now, concatenate the arrays.
		    // RFE: Replace Entry[] with Set and merge the sets.
		    table.put(key, (Entry[]) DynamicArray.append((Entry[]) o, otherEntry));
		}
	    }
	}
	checksumState = Math.min(checksumState, other.getChecksumState());
    }

    private static int[] mergeBugIds(int[] a, int[] b) {
	SortedSet s = new TreeSet();
	for (int i = 0; i < a.length; i++)
	    s.add(new Integer(a[i]));
	for (int i = 0; i < b.length; i++)
	    s.add(new Integer(b[i]));
	int[] c = new int[s.size()];
	int n = 0;
	for (Iterator i = s.iterator(); i.hasNext(); ) 
	    c[n++] = ((Integer) (i.next())).intValue();
	return c;
    }

    private static String[] mergePlatforms(String[] a, String[] b) {
	SortedSet s = new TreeSet();
	for (int i = 0; i < a.length; i++)
	    s.add(a[i]);
	for (int i = 0; i < b.length; i++)
	    s.add(b[i]);
	return ((String[]) (s.toArray(new String[s.size()])));
    }

    private static String mergeSynopsis(String a, String b) {
	if (a == null || a.trim().length() == 0)
	    return b;
	else if (b == null || b.trim().length() == 0)
	    return a;
	else if (a.indexOf(b) != -1)
	    return a;
	else if (b.indexOf(a) != -1)
	    return b;
	else
	    return a + "; " + b;
    }


    /**
     * Remove an entry from the table.  
     * @param e the entry to be removed
     */
    public void removeEntry(Entry e) {
	synchronized (table) {
	    Key key = new Key(e.relativeURL);
	    Object o = table.get(key);
	    if (o == null)
		// no such entry
		return;
	    else if (o instanceof Entry) {
		if (o == e)
		    table.remove(key);
	    }
	    else {
		Entry[] o2 = (Entry[])DynamicArray.remove((Entry[])o, e);
		if (o2 == o)
		    // not found
		    return;
		else {
		    if (o2.length == 1)
			table.put(key, o2[0]);
		    else
			table.put(key, o2);
		}
	    }
	}
    }

    /**
     * Check whether an exclude table has any entries or not.
     * @return true if this exclude table has no entries
     * @see #size
     */
    public boolean isEmpty() {
	return table.isEmpty();
    }

    /**
     * Get the number of entries in the table.
     * @return the number of entries in the table
     * @see #isEmpty
     */
    public int size() {
	// ouch, this is now expensive to compute
	int n = 0;
	for (Iterator i = table.values().iterator(); i.hasNext(); ) {
	    Object o = i.next();
	    if (o instanceof Entry[])
		n += ((Entry[])o).length;
	    else
		n++;
	}
	return n;
    }

    /**
     * Iterate over the contents of the table.
     * @param group if <code>true</code>, entries for the same relative
     * URL are grouped together, and if more than one, returned in an
     * array; if <code>false</code>, the iterator always returns
     * separate entries.
     * @see Entry
     * @return an iterator for the table: the entries are either
     * single instances of @link(Entry) or a mixture of @link(Entry)
     * and @link(Entry)[], depending on the <code>group</code>
     * parameter.
     */
    public Iterator getIterator(boolean group) {
	if (group)
	    return table.values().iterator();
	else {
	    // flatten the enumeration into a vector, then
	    // enumerate that
	    Vector v = new Vector(table.size());
	    for (Iterator iter = table.values().iterator(); iter.hasNext(); ) {
		Object o = iter.next();
		if (o instanceof Entry) 
		    v.addElement(o);
		else {
		    Entry[] entries = (Entry[])o;
		    for (int i = 0; i < entries.length; i++)
			v.addElement(entries[i]);
		}
	    }
	    return v.iterator();
	}

    }

    // note these values are deliberated ordered so that checksums can be combined
    // using "worst case" rules.

    /** 
     * A value to indicate that no checksum was found in an exclude list file.
     * @see #getChecksumState
     */
    public static final int NO_CHECKSUM = 0;

    /** 
     * A value to indicate that a bad checksum was found in an exclude list file.
     * @see #getChecksumState
     */
    public static final int BAD_CHECKSUM = 1;

    /** 
     * A value to indicate that a good checksum was found in an exclude list file.
     * @see #getChecksumState
     */
    public static final int GOOD_CHECKSUM = 2;

    /**
     * Get info about the validity of the checksums found when reading
     * the data for this exclude table.
     * @return an integer identifying the validity of the checksums found
     * when reading the data for this file
     * @see #NO_CHECKSUM
     * @see #BAD_CHECKSUM
     * @see #GOOD_CHECKSUM
     */
    public int getChecksumState() {
	return checksumState;
    }

    /**
     * Get the title for this exclude table.
     * @return the title for this exclude table
     * @see #setTitle
     */
    public String getTitle() {
	return title;
    }

    /**
     * Set the title for this exclude table.
     * @param title the title for this exclude table
     * @see #getTitle
     */
    public void setTitle(String title) {
	this.title = title;
    }

    /**
     * Write the table out to a file.
     * @param f The file to which the table should be written.
     * @throws IOException is thrown if any problems occur while the
     * file is being written.
     */
    public void write(File f) throws IOException {
	// sort the entries for convenience, and measure col widths
	int maxURLWidth = 0;
	int maxBugIdWidth = 0;
	int maxPlatformWidth = 0;
	SortedSet entries = new TreeSet();
	for (Iterator iter = getIterator(false); iter.hasNext(); ) {
	    Entry entry = (Entry) (iter.next());
	    entries.add(entry);
	    if (entry.testCase == null)
		maxURLWidth = Math.max(entry.relativeURL.length(), maxURLWidth);
	    else
		maxURLWidth = Math.max(entry.relativeURL.length() + entry.testCase.length() + 2, maxURLWidth);
	    maxBugIdWidth = Math.max(bugIdsToString(entry).length(), maxBugIdWidth);
	    maxPlatformWidth = Math.max(platformsToString(entry).length(), maxPlatformWidth);
	}

	BufferedWriter out = new BufferedWriter(new FileWriter(f));
	out.write("# Exclude List");
	out.newLine();
	out.write("# SCCS %" + 'W' + "% %" + 'E' + "%"); // TAKE CARE WITH SCCS HEADERS
	out.newLine();
	if (title != null) {
	    out.write("### title " + title);
	    out.newLine();
	}
	// negative hex values -- ugh!
	out.write("### checksum ");
	long checkSum = computeChecksum();
	if (checkSum < 0)
	    out.write("-");
	out.write(Long.toHexString(Math.abs(checkSum)));
	out.newLine();
	for (Iterator iter = entries.iterator(); iter.hasNext(); ) {
	    Entry e = (Entry) (iter.next());
	    if (e.testCase == null)
		write(out, e.relativeURL, maxURLWidth + 2);
	    else
		write(out, e.relativeURL + "[" + e.testCase + "]", maxURLWidth + 2);
	    write(out, bugIdsToString(e), maxBugIdWidth + 2);
	    write(out, platformsToString(e), maxPlatformWidth + 2);
	    out.write(e.synopsis);
	    out.newLine();
	}
	out.close();
    }

    private long computeChecksum() {
	long checksum = 0;
	for (Iterator iter = table.values().iterator(); iter.hasNext(); ) {
	    // WARNING: The iteration order of the table is undefined,
	    // so checksumming MUST be commutative and associative
	    Object o = iter.next();
	    if (o instanceof Entry)
		checksum += ((Entry)o).computeChecksum();
	    else {
		Entry[] entries = (Entry[])o;
		for (int i = 0; i < entries.length; i++)
		    checksum += entries[i].computeChecksum();
	    }
	}
	if (checksum == 0)  // zero checksum means undefined
	    checksum = 1;
	return Math.abs(checksum);
    }

    private void write(Writer out, String s, int width) throws IOException {
	out.write(s);
	for (int i = s.length(); i < width; i++)
	    out.write(' ');
    }

    private String bugIdsToString(Entry e) {
	StringBuffer sb = new StringBuffer(e.bugIds.length*10);
	sb.append(e.bugIds[0]);
	for (int i = 1; i < e.bugIds.length; i++) {
	    sb.append(',');
	    sb.append(e.bugIds[i]);
	}
	return sb.toString();
    }

    private String platformsToString(Entry e) {
	StringBuffer sb = new StringBuffer(e.bugIds.length*10);
	sb.append(e.platforms[0]);
	for (int i = 1; i < e.platforms.length; i++) {
	    sb.append(',');
	    sb.append(e.platforms[i]);
	}
	return sb.toString();
    }

    private static boolean equals(String s1, String s2) {
	return (s1 == null && s2 == null
		|| s1 != null && s2 != null && s1.equals(s2));
    }

    /**
     * Is val in the comma separated list.
     *
     * @param list Comma separated list or a single value.
     * @return Null if either parameter is null.
     */
    private static boolean isInList(String list, String val) {
	// check for stupid args  
	if (list == null || val == null)                   
	    return false;
	
	// loop through possible matches
	for (int pos = list.indexOf(val); pos != -1; pos = list.indexOf(val, pos + 1) ) {
	    // check beginning of string
	    if (!(pos == 0 || list.charAt(pos -1) == ','))
		continue;
	
	    // check end of string
	    if (!(pos + val.length() == list.length()  || list.charAt(pos + val.length()) == ','))
		continue;
	
	    // beginning and end are OK; got a match
	    return true;
	}
	
	// no good matches found  
	return false; 
    }

    private Map table = new HashMap();
    private int checksumState;
    private String title;

    private static final class Parser {
	Parser(Reader in) throws IOException {
	    this.in = in;
	    ch = in.read();
	}

	long getChecksum() {
	    return checksum;
	}

	String getTitle() {
	    return title;
	}

	Entry readEntry() throws IOException, Fault {
	    String url = readURL(); // includes optional test case
	    if (url == null)
		return null;
	    String testCase = null; // for now
	    if (url.endsWith("]")) {
		int i = url.lastIndexOf("[");
		if (i != -1) {
		    testCase = url.substring(i+1, url.length()-1);
		    url = url.substring(0, i);
		}
	    }
	    int[] bugIds = readBugIds();
	    String[] platforms = readPlatforms();
	    String synopsis = readRest();
	    return new Entry(url, testCase, bugIds, platforms, synopsis);
	}

	private boolean isEndOfLine(int ch) {
	    return (ch == -1 || ch == '\n' || ch == '\r');
	}

	private boolean isWhitespace(int ch) {
	    return (ch == ' ' || ch == '\t');
	}

	private String readURL() throws IOException, Fault {
	    // skip white space, comments and blank lines until a word is found
	    for (;;) {
		skipWhite();
		switch (ch) {
		case -1:
		    // end of file
		    return null;
		case '#':
		    // comment
		    skipComment();
		    break;
		case '\r':
		case '\n':
		    // blank line (or end of comment)
		    ch = in.read();
		    break;
		default:
		    return readWord();
		}
	    }
	}

	private int[] readBugIds() throws IOException {
	    // skip white space, then read and sort a list of comma-separated
	    // numbers with no embedded white-space
	    skipWhite();
	    TreeSet ts = new TreeSet();
	    int val = 0;
	    for ( ; !isEndOfLine(ch) && !isWhitespace(ch); ch = in.read()) {
		if (Character.isDigit((char)ch)) 
		    val = (val * 10) + Character.digit((char)ch, 10);
		else if (ch == ',') {
		    ts.add(new Integer(val));
		    val = 0;
		}
		// else ??
	    }
	    ts.add(new Integer(val));
	    if (ts.size() == 0)
		ts.add(new Integer(0));
	    int[] bugIds = new int[ts.size()];
	    int n = 0;
	    for (Iterator i = ts.iterator(); i.hasNext(); ) 
		bugIds[n++] = ((Integer) (i.next())).intValue();
	    return bugIds;
	}

	private String[] readPlatforms() throws IOException {
	    // skip white space, then read and sort a list of comma-separated
	    // platform names with no embedded white space. Since the
	    // set of platforms (and their combinations) is small,
	    // share the result amongst all equivalent entries.
	    skipWhite();
	    String s = readWord();
	    String[] platforms = (String[])platformCache.get(s);
	    if (platforms == null) {
		// split string into sorted comma separated pieces
		int n = 0;
		for (int i = 0; i < s.length(); i++) {
		    if (s.charAt(i) == ',')
			n++;
		}
		TreeSet ts = new TreeSet();
		int start = 0;
		int end = s.indexOf(',');
		while (end != -1) {
		    ts.add(s.substring(start, end));
		    start = end + 1;
		    end = s.indexOf(',', start);
		}
		ts.add(s.substring(start));
		platforms = (String[]) (ts.toArray(new String[ts.size()]));
		platformCache.put(s, platforms);
	    }
	    return platforms;
	}

	private String readRest() throws IOException {
	    // skip white space, then read up to the end of the line
	    skipWhite();
	    StringBuffer word = new StringBuffer(80);
	    for ( ; !isEndOfLine(ch); ch = in.read())
		word.append((char)ch);
	    // skip over terminating character
	    ch = in.read();
	    return word.toString();
	}

	private void readCheckSum() throws IOException {
	    skipWhite();
	    String s = readWord();
	    try {
		checksum = Long.parseLong(s, 16);
		skipWhite();
		if (!isEndOfLine(ch))
		    throw new IOException("extra text after checksum in exclude list");
	    }
	    catch (NumberFormatException e) {
		throw new IOException("badly-formed checksum in exclude list: " + s);
	    }
	}

	private String readWord() throws IOException {
	    // read characters up to the next white space
	    StringBuffer word = new StringBuffer(32);
	    for ( ; !isEndOfLine(ch) && !isWhitespace(ch); ch = in.read())
		word.append((char)ch);
	    return word.toString();
	}

	private void skipComment() throws IOException, Fault {
	    ch = in.read();
	    // first # has already been read
	    if (ch == '#') {
		ch = in.read();
		if (ch == '#') {
		    ch = in.read();
		    skipWhite();
		    String s = readWord();
		    if (s.equalsIgnoreCase("checksum")) {
			readCheckSum();
			return;
		    }
		    else if (s.equals("title")) {
			skipWhite();
			title = readRest();
			return;
		    }
		}
	    }
	    while (!isEndOfLine(ch))
		ch = in.read();
	}

	private void skipWhite() throws IOException {
	    // skip horizontal white space
	    // input is line-oriented, so do not skip over end of line
	    while (ch != -1 && isWhitespace(ch))
		ch = in.read();
	}

	private Reader in;	// source stream being read
	private int ch;		// current character
	private Map platformCache = new HashMap();
				// cache of results for readPlatforms
	private long checksum;
	private String title;
    };

    private static class Key {
	Key(String url) {
	    relativeURL = url;
	}

	public int hashCode() {
	    // the hashCode for a key is the hashcode of the normalized URL.
	    // The normalized URL is url.replace(File.separatorChar, '/').toLowerCase();
	    int h = hash;
	    if (h == 0) {
		int len = relativeURL.length();
		
		for (int i = 0; i < len; i++) {
		    char c = Character.toLowerCase(relativeURL.charAt(i));
		    if (c == sep)
			c = '/';
		    h = 31*h + c;
		}
		hash = h;
	    }
	    return h;
	}

	public boolean equals(Object o) {
	    // Two keys are equal if their normalized URLs are equal.
	    // The normalized URL is url.replace(File.separatorChar, '/').toLowerCase();
	    if (o == null || !(o instanceof Key))
		return false;
	    String u1 = relativeURL;
	    String u2 = ((Key) o).relativeURL;
	    int len = u1.length();
	    if (len != u2.length())
		return false;
	    for (int i = 0; i < len; i++) {
		char c1 = Character.toLowerCase(u1.charAt(i));
		if (c1 == sep)
		    c1 = '/';
		char c2 = Character.toLowerCase(u2.charAt(i));
		if (c2 == sep)
		    c2 = '/';
		if (c1 != c2)
		    return false;
	    }
	    return true;
	}

	private static final char sep = File.separatorChar;
	private String relativeURL;
	private int hash;
    }

    /**
     * An entry in the exclude table.
     */
    public static final class Entry implements Comparable {
	/**
	 * Create an ExcludeTable entry.
	 * @param u The URL for the test, specified relative to the test suite root.
	 * @param tc One or more test cases within the test to be excluded.
	 * @param b An array of bug numbers, justifying why the test is excluded.
	 * @param p An array of platform identifiers, on which the faults are
	 * 		known to occur
	 * @param s A short synopsis of the reasons why the test is excluded.
	 */
	public Entry(String u, String tc, int[] b, String[] p, String s) {
	    if (b == null || p == null)
		throw new NullPointerException();

	    // The file format cannot support platforms but no bugids, 
	    // so fault that; other combinations (bugs, no platforms;
	    // no bugs, no platforms etc) are acceptable.
	    if (b.length == 0 &&  p.length > 0)
		throw new IllegalArgumentException();

	    relativeURL = u;
	    testCase = tc;
	    bugIds = b;
	    platforms = p;
	    synopsis = s;
	}

	public int compareTo(Object o) {
	    Entry e = (Entry) o;
	    int n = relativeURL.compareTo(e.relativeURL);
	    if (n == 0) {
		if (testCase == null && e.testCase == null)
		    return 0;
		else if (testCase == null)
		    return -1;
		else if (e.testCase == null)
		    return +1;
		else 
		    return testCase.compareTo(e.testCase);
	    }
	    else
		return n;
	}

	/**
	 * Get the relative URL identifying the test referenced by this entry.
	 * @return the relative URL identifying the test referenced by this entry
	 */
	public String getRelativeURL() {
	    return relativeURL;
	}

	/**
	 * Get the (possibly empty) list of test cases for this entry.
	 * An entry can have zero, one, or a comma separated list of TCs.
	 *
	 * @return List, or null if there are no test cases.
	 */
	public String getTestCases() {
	    return testCase;
	}

	/** 
	 * Get the same data as getTestCases(), but split into many Strings
	 * This method is costly, so use with care.
	 *
	 * @return The parsed comma list, or null if there are no test cases.
	 */
	public String[] getTestCaseList() {
	    // code borrowed from StringArray by jjg
	    // it is a little wasteful to recalc everytime but saves space
	    if (testCase == null)
		return null;

	    Vector v = new Vector();
	    int start = -1;
	    for (int i = 0; i < testCase.length(); i++) {
		if (testCase.charAt(i) == ',') {
		    if (start != -1)
			v.addElement(testCase.substring(start, i));
		    start = -1;
		} else
		    if (start == -1)
			start = i;
	    }
	    if (start != -1)
		v.addElement(testCase.substring(start));

	    if (v.size() == 0)
		return null;

	    String[] a = new String[v.size()];
	    v.copyInto(a);
	    return a;
	}

	/**
	 * Get the set of bug IDs referenced by this entry.
	 * @return the bugs referenced by the entry
	 */
	public int[] getBugIds() {
	    return bugIds;
	}

	/**
	 * Get the set of platforms or keywords associated with this entry.
	 * These should normally give details about why the test has been
	 * excluded.
	 * @return the set of platforms or keywords associated with this entry
	 */
	public String[] getPlatforms() {
	    return platforms;
	}

	/**
	 * Get a short description associated with this entry.
	 * This should normally give details about why the test has been
	 * excluded.
	 * @return a short description associated with this entry
	 */
	public String getSynopsis() {
	    return synopsis;
	}

	/**
	 * Create an entry from a string. The string should be formatted
	 * as though it were a line of text in an exclude file.
	 * @param text The text to be read 
	 * @return the first entry read from the supplied text
	 * @throws ExcludeTable.Fault if there is a problem reading the entry.
	 */
	public static Entry read(String text) throws Fault {
	    try {
		return new Parser(new StringReader(text)).readEntry();
	    }
	    catch (IOException e) {
		throw new Fault("failed to read entry: " + e);
	    }
	}

	/**
	 * Compare this entry against another.
	 * @param o the object to compare against
	 * @return true is the objects are bothe ExcludeTable.Entries containing
	 *    the same details
	 */
	public boolean equals(Object o) {
	    if (o instanceof Entry) {
		Entry e = (Entry)o;
		return equals(relativeURL, e.relativeURL)
		    && equals(testCase, e.testCase)
		    && equals(bugIds, e.bugIds)
		    && equals(platforms, e.platforms)
		    && equals(synopsis, e.synopsis);
	    }
	    else
		return false;
	}

	public int hashCode() {
	    return relativeURL.hashCode();
	}

	public String toString() {
	    StringBuffer sb = new StringBuffer(64);
	    sb.append(relativeURL);
	    if (testCase != null) {
		sb.append('[');
		sb.append(testCase);
		sb.append(']');
	    }
	    if (bugIds != null) {
		for (int i = 0; i<bugIds.length; i++) {
		    sb.append(i == 0 ? ' ' : ',');
		    sb.append(bugIds[i]);
		}
	    }
	    if (platforms != null) {
		for (int i = 0; i<platforms.length; i++) {
		    sb.append(i == 0 ? ' ' : ',');
		    sb.append(platforms[i]);
		}
	    }
	    if (synopsis != null) {
		sb.append(' ');
		sb.append(synopsis);
	    }
	    return new String(sb);
	}

	long computeChecksum() {
	    long cs = computeChecksum(relativeURL);
	    if (testCase != null)
		cs = cs*37 + computeChecksum(testCase);
	    for (int i = 0; i < bugIds.length; i++)
		cs = cs*37 + bugIds[i];
	    for (int i = 0; i < platforms.length; i++)
		cs = cs*37 + computeChecksum(platforms[i]);
	    cs = cs*37 + computeChecksum(synopsis);
	    return cs;
	}

	private long computeChecksum(String s) {
	    long cs = 0;
	    for (int i = 0; i < s.length(); i++) {
		cs = cs * 37 + s.charAt(i);
	    }
	    return cs;
	}

	private static boolean equals(int[] i1, int[] i2) {
	    if (i1 == null || i2 == null)
		return (i1 == null && i2 == null);

	    if (i1.length != i2.length)
		return false;

	    for (int x = 0; x < i1.length; x++)
		if (i1[x] != i2[x])
		    return false;

	    return true;
	}

	private static boolean equals(String[] s1, String[] s2) {
	    if (s1 == null || s2 == null)
		return (s1 == null && s2 == null);

	    if (s1.length != s2.length)
		return false;

	    for (int x = 0; x < s1.length; x++) {
		if (!equals(s1[x], s2[x]))
		    return false;
	    }

	    return true;
	}

	private static boolean equals(String s1, String s2) {
	    return (s1 == null && s2 == null
		    || s1 != null && s2 != null && s1.equals(s2));
	}


	private String relativeURL;
	private String testCase;
	private int[] bugIds;
	private String[] platforms;
	private String synopsis;
    }

    /**
     * The standard extension for exclude-list files. (".jtx")
     */
    public static final String EXCLUDEFILE_EXTN = ".jtx";

}
