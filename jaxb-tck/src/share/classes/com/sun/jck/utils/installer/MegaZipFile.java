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


package com.sun.jck.utils.installer;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.IllegalStateException;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.jar.Attributes;
import java.util.jar.Attributes.Name;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.zip.CRC32;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class MegaZipFile {

    /**
     * Exception to report general miscellaneous faults.
     */
     public static class Fault extends Exception {
	 Fault(String msg) {
	     super(msg);
	 }
     }

    /**
     * Exception to report general deflat faults
     */
     public static class DeflateFault extends Exception {
	 DeflateFault(String msg) {
	     super(msg);
	 }
     }

    /**
     * A fault class used to store expeceptions encountered while processing zip entries
     */
    class EntryFault extends Exception {
	private EntryFault() {
	}

	public EntryFault(String entryName, Exception exception) {
	    m_entryName = entryName;
	    m_exceptionMessage = exception.getMessage();
	}

	public String getEntry () {
	    return m_entryName;
	}

	public String getMessage () {
	    return m_exceptionMessage;
	}

	private String m_entryName;
	private String m_exceptionMessage; 
    }

    private MegaZipFile () {
    }

    public MegaZipFile (String fileName)  throws Fault {
	this(new File(fileName), System.out);
    }

    public MegaZipFile (String fileName, PrintStream ps)  throws Fault {
	this(new File(fileName), ps);
    }

    public MegaZipFile (File fileName)  throws Fault {
	this(fileName, System.out);
    }

    public MegaZipFile (File fileName, PrintStream ps)  throws Fault {
	m_archiveFile = fileName;
	m_log = ps;
    }

    /*=============================================================================================
     *
     * PUBLIC METHODS
     *
     *=============================================================================================
     */


    /*=============================================================================================
     *
     *  Setter and Getters
     *
     *=============================================================================================
     */

    public void setVerbosity(int vLevel) throws IllegalArgumentException {
	switch (vLevel) {
	    case VERBOSE_LOUD:
		if (m_verbosity == VERBOSE_LOUD)
		    m_log.println("Setting Verbosity to Loud");
		m_verbosity = VERBOSE_LOUD;
		break;
	    case VERBOSE_NORMAL:
		if (m_verbosity == VERBOSE_LOUD)
		    m_log.println("Setting Verbosity to Normal");
		m_verbosity = VERBOSE_NORMAL;
		break;
	    case VERBOSE_QUIET:
		if (m_verbosity == VERBOSE_LOUD)
		    m_log.println("Setting Verbosity to Quiet");
		m_verbosity = VERBOSE_QUIET;
		break;
	    default:
		if (m_verbosity == VERBOSE_LOUD)
		    m_log.println("Attempt to set verbosity to unkown level");
		throw new IllegalArgumentException("Unkown verbosity level");
	}
    }




    public int getVerbosity() {
	return m_verbosity;
    }




    public void setLog(PrintStream ps) {
	m_log = ps;
	if (m_verbosity == VERBOSE_LOUD)
	    m_log.println("Log set to " + m_log.toString());
    }




    public PrintStream getLog() {
	return m_log;
    }




    public void setSubZipSize(long subZipSize) throws IllegalArgumentException {
	if (subZipSize < 0) 
	    throw new IllegalArgumentException("subZip Size value must be greater than 0");

	m_maxSubzipSize = subZipSize;

	if (m_verbosity == VERBOSE_LOUD)
	    m_log.println("SubZip Maximum size set to " + m_maxSubzipSize);
    }




    public void setSubZipEntries(int numEntries) throws IllegalArgumentException {
	if (numEntries > MEGAZIP_MAX_ENTRIES) 
	    throw new IllegalArgumentException("The number of Zip Entries can not exceed " + MEGAZIP_MAX_ENTRIES);

	m_maxSubzipEntries = numEntries;

	if (m_verbosity == VERBOSE_LOUD)
	    m_log.println("SubZip Maximum number of entries to " + m_maxSubzipEntries);
    }




    public void setMainClass(String MainClass) {

	m_mainClass = MainClass;

	if (m_verbosity == VERBOSE_LOUD)
	    m_log.println("Main class set to " + m_mainClass);
	
	m_exportClasses.add(m_mainClass);
    }




    public String getMainClass() {
	return m_mainClass;
    }

   

    public void setExportClasses(File classList)  throws IllegalArgumentException, Fault {

	if (!classList.exists())
	    throw new IllegalStateException ("Can not find " + classList.getPath());

	if (!classList.isFile())
	    throw new IllegalStateException (classList.getPath() + " is not a regular file.");

	if (!classList.canRead())
	    throw new IllegalStateException ("Do not have permission to read " + classList.getPath());

	try {
	    m_exportClasses.addAll(generateClassList(classList));
	}
	catch (IOException ioe) {
	    throw new Fault("An error occured while processing export-class file.\n" + 
			    ioe.getMessage());
	}
    }

    public HashSet getExportClasses() {
	return m_exportClasses;
    }





    public void addTopLevelEntry (File [] entries) throws IllegalArgumentException {
	for (int i = 0; i < entries.length; i++) {
	    this.addTopLevelEntry(entries[i]);
	}
    }



    // a top level entry is an item that will be added to the MegaZipFile during zip
    // but will be ignored during unzip
    public void addTopLevelEntry (File entry) throws IllegalArgumentException {
	    
	if (!entry.exists())
	    throw new IllegalStateException ("Can not find " + entry.getPath());

	if (!entry.canRead())
	    throw new IllegalStateException ("Do not have permission to read " + entry.getPath());

	m_topLevelEntries.add(entry);

	if (m_verbosity == VERBOSE_LOUD)
	    m_log.println(entry.getAbsolutePath() + " was added to list of top level entries");
    }




    public void setCopyRightFile (File copyright) throws IllegalArgumentException {
	    
	if (!copyright.exists())
	    throw new IllegalStateException ("Can not find " + copyright.getPath());

	if (!copyright.canRead())
	    throw new IllegalStateException ("Do not have permission to read " + copyright.getPath());

	m_copyRightFile = copyright;

	if (m_verbosity == VERBOSE_LOUD)
	    m_log.println(m_copyRightFile.getAbsolutePath() + " was added a copyRight file");
    }




    public File getCopyRightFile () {
	return m_copyRightFile;
    }




    public void setTempDir(File tempDir)  throws IllegalArgumentException, IOException {
	if (!tempDir.isDirectory()) 
	    throw new IllegalArgumentException("The temporary directory " + tempDir.getAbsolutePath() + 
						"does not exist or is not a directory");
	
	if (!tempDir.canWrite()) 
	    throw new IllegalArgumentException("The temporary directory " + tempDir.getAbsolutePath() + 
						"is not writable.");
	
	m_tempDirectory = tempDir;

	m_deleteTempDir = false;

	if (m_verbosity == VERBOSE_LOUD)
	    m_log.println("Temproray Directory set to " + m_tempDirectory.getAbsolutePath());
    }




    public File getTempDir() {
	return m_tempDirectory;
    }




    public void setErrorMode (int mode) throws IllegalArgumentException {

	if (mode < ON_ERROR_IGNORE || mode > ON_ERROR_FAIL) {
	    throw new IllegalArgumentException("Invalid Error Handeling Mode");
	}

	m_errorHandleMode = mode;
    }




    public int getErrorMode () {

	return m_errorHandleMode;

    }

    public Map getErrors () {

	return m_errorsMap;

    }


    /*=============================================================================================
     *
     * worker methods
     *
     *=============================================================================================
     */

    public boolean zip (File [] entries) throws IllegalStateException, IOException, Fault {

	Collection subZips;
	Iterator subZipsIterator;	
	JarOutputStream jos;
	Manifest man;
	Attributes mainAttr;
	Node currentNode = null;

	//need to make sure that archive file does not already exist, if so throw IO exception, otherwise set up zip
	if ( m_archiveFile.exists())  
	    throw new IOException ("Zip file "+ m_archiveFile.getAbsolutePath() +" already exists.");
	
	jos = new JarOutputStream(new BufferedOutputStream(new FileOutputStream(m_archiveFile)));
	man = new Manifest();

	try {

	    // copy right should be first and written uncompressed
	    if (m_copyRightFile != null) {
		try {
		deflate (jos, m_copyRightFile, m_copyRightFile.getName(), ZipEntry.STORED);
		}
		catch (DeflateFault d) {
		    m_zipSucceeded = false;
		}

	    }

	    //add top level entries 
	    if (m_topLevelEntries.size() != 0) {

		Iterator it = m_topLevelEntries.iterator();
		File currentEntry;

		while (it.hasNext()) {
			currentEntry = (File) it.next();
			try {
			deflate(jos, currentEntry, currentEntry.getName(),  ZipEntry.DEFLATED);
			}
			catch (DeflateFault d) {
				m_zipSucceeded = false;
			}
		}

	    }
	    
	    //zip up nodes
	    subZips = generateSubzips(generateEntries(entries));
	    subZipsIterator = subZips.iterator();

	    
	    while (subZipsIterator.hasNext()) {

		currentNode = (Node) subZipsIterator.next();
		File currentFile = currentNode.getFile();
		try {
		    deflate(jos, currentFile, currentFile.getName(), ZipEntry.DEFLATED);
		}
		catch (DeflateFault d) {
		    m_zipSucceeded = false;
		}

		if(!currentNode.getFile().delete()) {
		    if (m_verbosity >= VERBOSE_NORMAL)
			m_log.println("Unable to delete temporary zip file " + currentFile.getAbsolutePath());
		}
		else  {
		    if (m_verbosity == VERBOSE_LOUD)
			m_log.println("Deleted temporary zip file " + currentFile.getAbsolutePath());
		}

	    }

	    //zip any needed classes
	    mainAttr =  man.getMainAttributes();
	    mainAttr.put(Attributes.Name.MANIFEST_VERSION, "1");
	    if (m_mainClass != null) {

		if (exportClasses(jos))
		    mainAttr.put(Attributes.Name.MAIN_CLASS, m_mainClass);
		else
		    m_zipSucceeded = false;

	    }

	    //generate manifest
	    try {
		jos.putNextEntry(new ZipEntry(MANIFEST));
		man.write(jos);
		jos.closeEntry();
	    }
	    catch (IOException ioe) {
		m_zipSucceeded = false;
		handleEntryError(MANIFEST, ioe);
	    }

	    //delete the temp directory
	    if (m_deleteTempDir) {
		if (!m_tempDirectory.delete())
		    m_log.println("Unable to delete temporary directory " + m_tempDirectory);
	    }


	    jos.close();

	}
	catch (Root.Fault f) {
		throw new Fault ("Fault thrown while generating master zip" +
		"\n" + f.getMessage());
	}
	if (m_verbosity == VERBOSE_LOUD) {
	    if (m_zipSucceeded)
		    m_log.println("Master Zip file " + m_archiveFile.getAbsolutePath() + " completed with no errors");
	    else
		    m_log.println("Master Zip file " + m_archiveFile.getAbsolutePath() + " completed with errors");
	}

	m_log.flush();


	return m_zipSucceeded;


    }




    public boolean unzip (File target) throws IllegalStateException, IOException, Fault {

	ZipFile me;
	ZipEntry currentEntry;
	Enumeration entries;

	//make sure that archive file has been set, else fault
	if (m_archiveFile == null) 
	    throw new Fault("The archive file to be unzipped has not been set.");


	//make sure that I am a zip file
	try {

	    me = new ZipFile(m_archiveFile);
	}
	catch (ZipException z) {
	    throw new IOException (m_archiveFile.getAbsolutePath() + "does not appear to be a zip or jar file");
	}


	if (m_mode != MODE_LIST) {
	    //make sure that target is acceptable
	    if (target == null) 
		throw new Fault("The target directory can not be null");
	    if (!target.isDirectory() )
		throw new IllegalStateException ("Target directory " +target.getAbsolutePath() + 
					    " does not appear to be a directory ");

	    if (!target.canWrite() )
		throw new IllegalStateException ("Target directory " +target.getAbsolutePath() + 
					    "is not writable.");
	}

	//get top level entries and inflate them one at a time
	entries = me.entries();
	while (entries.hasMoreElements()) {

	    try {

		currentEntry = (ZipEntry) entries.nextElement();

		if (m_verbosity == VERBOSE_LOUD) 
		    m_log.println("Examining " + currentEntry.getName() + " for sub-entries.");

		try {
		    if (! inflate(new ZipInputStream(me.getInputStream(currentEntry)), target) ) {
			m_unZipSucceeded = false;
		    }
		}
		catch (IOException ioe) {
		    m_unZipSucceeded = false;
		    handleEntryError(currentEntry.getName(), ioe);
		}
	    }
	    catch (NoSuchElementException nse) {
		throw new Fault ("No such element thrown while unzipping entries.");
		    
	    }
	}


	m_log.flush();

	return m_unZipSucceeded;
    }




    public boolean list ()  throws IllegalStateException, IOException, Fault {
	
	m_mode = MODE_LIST;
	return unzip(null);
	
    }


    /*=============================================================================================
     *
     * PRIVATE METHODS
     *
     *=============================================================================================
     */


    private Iterator generateEntries (File [] entries)  throws IllegalStateException,IOException,Root.Fault {

	TreeMap fileMap = new TreeMap();

	for (int i = 0; i < entries.length; i++) {
	    fileMap.putAll(new Root(entries[i]).getNodes());
	}

	m_totalNumberOfFiles = fileMap.size();


	return fileMap.entrySet().iterator();
    }


    private ArrayList generateSubzips (Iterator entries)  throws IllegalStateException, IOException,Fault {
	ArrayList subZips;
	Node currentSubZip = null;
	Node currentNode = null;
	Map.Entry currentMapEntry;
	ZipOutputStream zos = null;
	int numOfSubZips;
	int counter = 0;
	BigInteger [] result;
	BigInteger BItotalNumberOfFiles = BigInteger.valueOf(m_totalNumberOfFiles);
	BigInteger BImaxSubzipEntries = BigInteger.valueOf(m_maxSubzipEntries);
	boolean subZipFull = true;
	boolean subZipClosed = false;
	long entrySize = 0;


	//create temp directory if one had not already been specified but only make it if in execute mode
	if (m_tempDirectory == null  ) {

		long now = System.currentTimeMillis();
		File sysTempDir = new File(System.getProperty("java.io.tmpdir"));

		m_tempDirectory = new File(sysTempDir, MessageFormat.format("megazip-{0,date,yyMMdd-HHmmss}-{1}.dir", 
						new Object[] {new Long(now), new Long(now%1000)}));
		if (!m_tempDirectory.mkdir())
		    throw new IOException ("Unable to create tempory directory " + m_tempDirectory.getAbsolutePath());
	}


	//determine the number of subzips I will need and make sure it can be accomadated
	result = BItotalNumberOfFiles.divideAndRemainder(BImaxSubzipEntries);
	if (result[1].compareTo(BigInteger.ZERO) > 0)
	    numOfSubZips=result[0].intValue() + 1;
	else
	    numOfSubZips=result[0].intValue();

	if (numOfSubZips > MEGAZIP_MAX_ENTRIES) 
	    throw new IllegalStateException("The current subzip file limit will result in the creation of more subzip files than can exist in megazip file");

	subZips = new ArrayList(numOfSubZips);


	//create subzips
	while (entries.hasNext()) {

	    if (subZipFull) {
		subZipClosed = false;
		counter = 0;
		m_subzipSize = 0;
		subZipFull = false;
		
		currentSubZip = new Node (m_tempDirectory.getAbsolutePath(), 
					    new File(m_tempDirectory, "Data" + String.valueOf(subZips.size() + 1) +".zip"));
		zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(currentSubZip.getFile())));

		if (m_verbosity == VERBOSE_LOUD)
		    m_log.println("Opening SubZip " + currentSubZip.getRelativePath());
	    }


	    currentMapEntry = (Map.Entry) entries.next();
	    currentNode = (Node) currentMapEntry.getValue();

	    try {

		entrySize = deflate(zos, currentNode, ZipEntry.DEFLATED);

		if (entrySize == -1 && m_maxSubzipSize > 0) 
		    throw new Fault ("Unable to determine compressed size of entry " + currentNode.getRelativePath());

		if (m_verbosity == VERBOSE_LOUD)
		    m_log.println("   Added Entry " + currentNode.getRelativePath() + " to subZip " + currentSubZip.getAbsolutePath());

		if (++counter == m_maxSubzipEntries || exceedsSizeMax(entrySize)) {
		    subZipFull = true;
		    zos.close();
		    subZipClosed = true;
		    subZips.add(currentSubZip);
		    if (m_verbosity == VERBOSE_LOUD)
			m_log.println("Closing SubZip " + currentSubZip.getRelativePath() + 
					"with " + counter + " entries");
		}
	    }
	    catch (DeflateFault d) {
		m_zipSucceeded = false;
	    }
	    catch (IOException e) {
		throw new IOException("An IOException was thrown while attempting to add " + currentNode.getRelativePath() +
					" to subzip " + currentSubZip.getAbsolutePath() + "\n" + e.getMessage());
	    }

	    //no longer need to entry, so remove it from map so it can be garbage collected
	    entries.remove();

	}

	//finalize last subzip
	if (!subZipClosed) {
	    subZipFull = true;
	    zos.close();
	    subZipClosed = true;
	    subZips.add(currentSubZip);
	    if (m_verbosity == VERBOSE_LOUD)
		m_log.println("Closing SubZip " + currentSubZip.getRelativePath() + 
				"with " + counter + " entries");
	}

	return subZips;


    }




    private long deflate(ZipOutputStream zos, Node source, int CompressionMode)  throws IOException,DeflateFault {
	return deflate(zos, source.getFile(), source.getRelativePath(), CompressionMode);
    }




    private long deflate(ZipOutputStream zos, File source, String entryName, int CompressionMode)  throws IOException,DeflateFault {
	BufferedInputStream bis = null;
	try {
	    bis =  new BufferedInputStream(new FileInputStream(source));
	}
	catch (IOException ioe) {
	    handleEntryError(entryName, ioe);
	    throw new DeflateFault("A fault was encountered while deflating " + entryName );
	}

	return deflate(zos, bis, entryName, CompressionMode);
    }




    private long deflate(ZipOutputStream zos, InputStream source, String entryName, int CompressionMode)  throws IOException,DeflateFault {

	long totalBytesRead = 0;
	boolean succeeded = true; 
	ZipEntry entry = null;
	int inputStreamSize;

	try {
	    entry = new ZipEntry(entryName);

	    if (m_verbosity >= VERBOSE_NORMAL)
		m_log.println("Deflating " + entryName );
   
	    if (CompressionMode == ZipEntry.STORED) {

	    /*  The Zip api requires that the crc and entry size for any un-compressed entry be set prior to the entry 
	     *  be added to the zip output stream. In order to determine these values, the entire file being added needs
	     *  to be read. To prevent the need to read the file twice, I will read the file into a byte "buffer" and use
	     *  the buffer to create these values and as foundation for the source input stream....d'oh!
	     */

		int bytesRead;
		byte [] dataAccumulated = new byte[0]; //all data read will be placed in here
		byte [] tempBuff1 = new byte[4096]; //temp buff used to hold the bytes grabbed during each read 
		byte [] tempBuff2 = null; //buffer used to construct new data Accumulated array

		while (true) {

		    bytesRead = source.read(tempBuff1,0,tempBuff1.length);

		    if (bytesRead == -1)
			break;

		    tempBuff2 = new byte [bytesRead + dataAccumulated.length];

		    System.arraycopy(dataAccumulated,0,tempBuff2,0,dataAccumulated.length); //data buffer into temp2
		    System.arraycopy(tempBuff1,0,tempBuff2,dataAccumulated.length,bytesRead); //add temp1 to temp2
		    
		    dataAccumulated = tempBuff2; //switch them

		}

		CRC32 crc = new CRC32();
		crc.update(dataAccumulated);
		entry.setMethod(ZipEntry.STORED);
		entry.setCrc(crc.getValue());
		entry.setSize(dataAccumulated.length);
		source = new BufferedInputStream(new ByteArrayInputStream(dataAccumulated));
	    }

	    zos.putNextEntry(entry);
	    totalBytesRead = copyBytes( source, zos);

	    entry.setSize(totalBytesRead);
	    entry.setTime(System.currentTimeMillis());
	
	    source.close();
	    zos.closeEntry();
	}
	catch (IOException ioe) {
	    succeeded = false;
	    handleEntryError(entryName, ioe);
	}

	if (succeeded) {
	    if (m_verbosity == VERBOSE_LOUD)
		m_log.println(entry.getName() + " deflated");
	    return entry.getCompressedSize();
	}
	else 
	    throw new DeflateFault("A fault was encountered while deflating " + entryName );
    }




    private boolean inflate(ZipInputStream zis, File targetDir)  throws IOException {

	File targetFile;
	File targetFileParent;
	ZipEntry currentEntry;
	BufferedOutputStream bos;
	String currentEntryName;
	boolean inflateSucceeded = true;

	while (true) {

	    currentEntry = zis.getNextEntry();
	    if (currentEntry == null)
		return inflateSucceeded;

	    currentEntryName = currentEntry.getName();

	    if (m_mode == MODE_LIST) {
		m_log.println(currentEntryName );
		continue;
	    }

	    try {

		targetFile = new File(targetDir, currentEntryName);
		targetFileParent = new File (targetFile.getParent());
		if (!targetFileParent.exists()) {
		    if (!targetFileParent.mkdirs()) {
			inflateSucceeded = false;
			throw new IOException("Unable to make parent directories for zip entry " + currentEntry.getName() + 
					    " in the target directory " + targetDir.getAbsolutePath());
		    }
		}

		if (currentEntry.isDirectory()) {
		    if (!targetFile.mkdir())
			inflateSucceeded = false;
			throw new IOException("Unable to generate directory for zip entry " + currentEntry.getName() + 
					    " in the target directory " + targetDir.getAbsolutePath());
		}

		bos = new BufferedOutputStream(new FileOutputStream(targetFile));

		copyBytes( zis, bos);

		if (m_verbosity >= VERBOSE_NORMAL)
		    m_log.println("Inflated " + currentEntry.getName());

		bos.close();
		zis.closeEntry();

		if (!targetFile.isDirectory()) {
		    if(!targetFile.setReadOnly()) {
			throw new IOException("Unable to set " + targetFile.getAbsolutePath() 
						+ " file permissions to read only.");
		    }
		}
	    }
	    catch (IOException ioe) {
		inflateSucceeded = false;
		handleEntryError(currentEntryName, ioe);
	    }

	}

    }




    private boolean exceedsSizeMax(long increment) {

	if (m_maxSubzipSize == 0)
	    return false;
	
	m_subzipSize += increment;

	if (m_subzipSize >= m_maxSubzipSize) 
	    return true;
	else
	    return false;
	    
    }




    private boolean exportClasses (JarOutputStream jos)  throws IOException,Fault {
	
	String fs = System.getProperty("file.separator");
	String ps = System.getProperty("path.separator");
	String classPath = System.getProperty("java.class.path");
	Iterator mainClassPathIter = m_exportClasses.iterator();
	StringTokenizer classPathElement;
	boolean succeeded = true;
	boolean classFound;
	String currentMainClass_dn = null; //class name in dot notation format. Ex. foo.bar
	String currentMainClass_fn = null; //class name in file name format Ex. foo/bar
	File currentCPElement;
	File currentClassFile;
	JarEntry jarEntry;
	JarFile currentJarFile;
	InputStream sourceInputStream;

	while (mainClassPathIter.hasNext()) {

	    classPathElement = new StringTokenizer(classPath, ps);
	    classFound = false;
	    sourceInputStream = null;

	    currentMainClass_dn = (String) mainClassPathIter.next();
	    currentMainClass_fn = currentMainClass_dn.replace('.', fs.charAt(0));

	    try {

		if (m_verbosity == VERBOSE_LOUD)
		    m_log.println("looking for " + currentMainClass_fn + " on classpath");

		while (classPathElement.hasMoreElements()) {

		    currentCPElement = new File( (String) classPathElement.nextElement());

		    // the classpath element can't be located, skip it
		    if (!currentCPElement.exists())
			continue;

		    // if the classpath element is a dir, check to see if it holds my class
		    if (currentCPElement.isDirectory()) {

			currentClassFile = new File (currentCPElement, currentMainClass_fn + ".class");

			// found it, put it into the jar file
			if (currentClassFile.exists()) {

			    classFound = true;
			    sourceInputStream = new BufferedInputStream(new FileInputStream(currentClassFile));
			    break;
			} 


		    } 

		    // if the classpath element is a jar, check to see if one of the elements is my class
		    else {

			//this should be a jar file, validate and then check for any entry by that name
			currentJarFile= new JarFile(currentCPElement);

			jarEntry = (JarEntry) currentJarFile.getEntry(currentMainClass_fn + ".class"); 

			if(jarEntry != null) {

			    classFound = true;
			    sourceInputStream = currentJarFile.getInputStream(jarEntry);
			    break;

			} 
			
		    } 
		} 

		if (classFound) {
			try {
			    deflate(jos, sourceInputStream, currentMainClass_fn+".class",ZipEntry.DEFLATED);
			}
			catch (DeflateFault d) {
			    succeeded = false;
			}
		}
		else 
		    throw new IOException("Unable to find main or supporting class " + currentMainClass_dn + " on the class path.");

	    }
	    catch (IOException ioe) {
		succeeded = false;
		handleEntryError(currentMainClass_dn, ioe);
	    }

	}

	return succeeded;


    }

    private ArrayList generateClassList(File classList) throws IOException {
	ArrayList classes = new ArrayList();
	BufferedReader br = new BufferedReader(new FileReader(classList));
	String currentLine;

	while (true) {

	    currentLine = br.readLine();

	    if (currentLine == null)
		break;

	    classes.add(currentLine);

	    if (m_verbosity == VERBOSE_LOUD)
		m_log.println(currentLine + " was added to list of classes to be exported");
	}

	return classes;

    }

    private long copyBytes (InputStream is, OutputStream os) throws IOException {
	BufferedInputStream bis;
	int bytesRead;
	long totalBytesRead = 0;
	byte [] buffer = new byte [4096];

	while (true) {
	    bytesRead = is.read(buffer,0,buffer.length);
	    if (bytesRead == -1)
		break;
	    os.write(buffer,0,bytesRead);
	    totalBytesRead += bytesRead;
	}

	return totalBytesRead;
    }

    private void handleEntryError (String entryName, IOException e) throws IOException {

	m_errorsMap.put(entryName, new EntryFault(entryName, e));
	
	if (m_errorHandleMode == ON_ERROR_IGNORE) 
	    return;

	m_log.println("An error occured while processing entry " + entryName);
	m_log.println(e.getMessage());
	if (m_verbosity == VERBOSE_LOUD)
	    e.printStackTrace(m_log);
	

	if (m_errorHandleMode == ON_ERROR_FAIL) 
	    throw e;

    }

    

    private File m_archiveFile;
    private int m_verbosity = VERBOSE_NORMAL;
    private int m_mode = MODE_EXECUTE;
    private PrintStream m_log;
    private int m_maxSubzipEntries = MEGAZIP_MAX_ENTRIES;
    private long m_subzipSize = 0;
    private long m_maxSubzipSize = 0;
    private String m_mainClass; 
    private HashSet m_exportClasses = new HashSet(); 
    private HashSet m_topLevelEntries = new HashSet();
    private int m_totalNumberOfFiles = 0;
    private File m_tempDirectory;
    private File m_copyRightFile;
    private int m_errorHandleMode = ON_ERROR_REPORT;
    private HashMap m_errorsMap = new HashMap();
    private boolean m_deleteTempDir = true;
    private boolean m_unZipSucceeded = true;
    private boolean m_zipSucceeded = true;
    

    public static final String MANIFEST = "META-INF/MANIFEST.MF";
    public static final int ON_ERROR_IGNORE = 0;
    public static final int ON_ERROR_REPORT = 1;
    public static final int ON_ERROR_FAIL = 2;
    public static final int VERBOSE_QUIET = 0;
    public static final int VERBOSE_NORMAL = 1;
    public static final int VERBOSE_LOUD = 2;
    public static final int MODE_EXECUTE = 0;
    public static final int MODE_LIST = 1;
    public static final int MEGAZIP_MAX_ENTRIES = 65534;
    public static final int MEGAZIP_MAX_FILES = java.lang.Integer.MAX_VALUE;

}
