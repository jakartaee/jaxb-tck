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

package com.sun.tgxml.tjtf.api.common.impl;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.common.impl.ClassUtils
import java.util.Iterator;
import java.util.StringTokenizer;
// </importgen>


/**
 * ClassUtils - 
 *
 * <b>ClassUtils</b> is a collection of static util methods that are useful
 * accross the ClassInfo library, both internally and externally. Since ClassUtils
 * contains no internal data or non-static methods, it is incorrect to construct it.
 * <p>
 * Many of the methods here deal with descriptor
 * (<a href="http://java.sun.com/docs/books/vmspec/2nd-edition/html/ClassFile.doc.html#1169">Java VM Spec, 4.3</a>)
 * strings.
 *
 * @version 	1.0, 04/17/00
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    ClassUtils
 * ============================================================================================
 */


public  final class ClassUtils {
    /*
     * ============================================================================================
     *    Member Fields
     * ============================================================================================
     */

    private static final char intDelimChar = '/';
    private static final char extDelimChar = '.';
    private static final String extDelimCharStr = ".";
    private static final String extDelimCharIllStr = "..";
    private static final String intDelimCharStr = "/";
    private static final String intDelimCharIllStr = "//";
    private static final String  str_innerPackSeparator = "/";
    private static final String  str_innerClassSeparator = "$";
    private static final String  str_innerMembSeparator = ".";
    private static final String str_importAll = ".*";
    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
    // private constructor
    private ClassUtils () {
    }
    
    
    /**
     *  Utility:  Converts a name from ClassFile notation to source notation, as described in
     * (<a href="http://java.sun.com/docs/books/vmspec/2nd-edition/html/ClassFile.doc.html#14757">Java VM Spec, 4.2</a>).
     * <p>
     * @param slashName The internal name (in slash notation).
     * @return The external name (in dot notation).
     */
    public static final String toExternalName(String slashName) {
	return slashName.replace(intDelimChar, extDelimChar);
    }
    
    
    /**
     *  Utility:  Converts a name from source notation to ClassFile notation, as described in
     * (<a href="http://java.sun.com/docs/books/vmspec/2nd-edition/html/ClassFile.doc.html#14757">Java VM Spec, 4.2</a>).
     * <p>
     * @param dottedName The external name (in dot notation).
     * @return The internal name (in slash notation).
     */
    public static final String toInternalName(String dottedName) {
	return dottedName.replace(extDelimChar, intDelimChar);
    }
    
    /**
     *  Utility:  Returns the fully qualified name of a package when given a package or class.
     * <p>
     * @param childname The fully-qualified name of a child class or package.
     * @return The fully-qualified name of the parent package that owns the given child.
     */
   static  public final String getPackageNameString(String childname)    {
	String packageName = childname;
	int innerIndex = packageName.indexOf(str_innerClassSeparator);
	if (innerIndex > 0)
	    packageName = packageName.substring(0,innerIndex);
	int lastDot = packageName.lastIndexOf(str_innerMembSeparator);
	if (lastDot < 1) {
	    int lastSlash = packageName.lastIndexOf(str_innerPackSeparator);
	    if (lastSlash < 1) 
		return ("");
	    else
		return packageName.substring(0,lastSlash);
	}
	else
	    return packageName.substring(0,lastDot);
    }
    
    /**
     *  Utility:  Returns the rightmost name (for a Class or package) from
     *  a fully-qualified name. This preserves inner-class structure, such that
     *  pack1.className$innerClass -> className$innerClass
     * <p>
     * @param childname The fully-qualified name of a child class or package.
     * @return The non-qualified name of the package or class.
     */
   static  public final String getNameFromFQName(String childname)    {
	String packageName = childname;
	int nameLength = childname.length();
	int innerIndex = packageName.indexOf(str_innerClassSeparator);
	if (innerIndex > 0)
	    packageName = packageName.substring(0,innerIndex);
	int lastDot = packageName.lastIndexOf(str_innerMembSeparator);
	if (lastDot < 1) {
	    int lastSlash = packageName.lastIndexOf(str_innerPackSeparator);
	    if (lastSlash < 1) 
		return ("");
	    else
		return childname.substring(lastSlash, nameLength);
	}
	else
	    return childname.substring(lastDot, nameLength);
    }
     


    /**
     *  Utility: (Predicate) Validates an import statement name
     * (<a href="http://java.sun.com/docs/books/vmspec/2nd-edition/html/Concepts.doc.html#21272">Java VM Spec, 2.7</a>) &
     * (<a href="http://java.sun.com/docs/books/vmspec/2nd-edition/html/Concepts.doc.html#29321">Java VM Spec, 2.8</a>).
     * <p>
     * @param classNameIdentifier The String describing a class name identifier.
     * @return true if the classNameIdentifier is a well formed class name.
     */
    public static final boolean validImport(String importNameIdentifier) {
	if (importNameIdentifier == null || importNameIdentifier.equals(""))
	    return false;

	// Truncate any "include-all" extensions
	if (importNameIdentifier.endsWith(str_importAll)) {
	    String importPack = importNameIdentifier;
	    importPack = importNameIdentifier.substring(0, (importNameIdentifier.length() - str_importAll.length()));

	    if (importPack == null || importPack.equals(""))
		return false;

	    return validExtPackagename(importPack);  
	} else {
	    return validExtClassname(importNameIdentifier);  
	}
    }

    /**
     *  Utility: (Predicate) Validates an import statement name
     * (<a href="http://java.sun.com/docs/books/vmspec/2nd-edition/html/Concepts.doc.html#21272">Java VM Spec, 2.7</a>) &
     * (<a href="http://java.sun.com/docs/books/vmspec/2nd-edition/html/Concepts.doc.html#29321">Java VM Spec, 2.8</a>).
     * <p>
     * @param classNameIdentifier The String describing a class name identifier.
     * @return true if the classNameIdentifier is a well formed class name.
     */
    public static final boolean validExport(String importNameIdentifier) {
        return validImport(importNameIdentifier);
    }


    /**
     *  Utility: (Predicate) Validates an identifier as a valid class name 
     * (<a href="http://java.sun.com/docs/books/vmspec/2nd-edition/html/Concepts.doc.html#21272">Java VM Spec, 2.7</a>) &
     * (<a href="http://java.sun.com/docs/books/vmspec/2nd-edition/html/Concepts.doc.html#29321">Java VM Spec, 2.8</a>).
     * <p>
     * @param classNameIdentifier The String describing a class name identifier.
     * @return true if the classNameIdentifier is a well formed class name.
     */
    public static final boolean validExtPackagename(String packNameIdentifier) {
	if (packNameIdentifier == null)
	    return false;
	// First make sure that there are no "double delim chars" i.e. '..'
	if (packNameIdentifier.indexOf(new String(extDelimCharIllStr)) > -1)
	    return false;

	// Next, make sure that the name does not start or end with a separator.
	if (packNameIdentifier.startsWith(str_innerMembSeparator) ||
	    packNameIdentifier.endsWith(str_innerMembSeparator)  )
	    return false;

	StringTokenizer st = new StringTokenizer(packNameIdentifier, extDelimCharStr);
	String token;

	while (st.hasMoreTokens()) {
	    token = st.nextToken();

	    if (! _validIdentifier(token)) {
		return false;
	    }
	}

	return true;
    }


    /**
     *  Utility: (Predicate) Validates an identifier as a valid class name 
     * (<a href="http://java.sun.com/docs/books/vmspec/2nd-edition/html/Concepts.doc.html#21272">Java VM Spec, 2.7</a>) &
     * (<a href="http://java.sun.com/docs/books/vmspec/2nd-edition/html/Concepts.doc.html#29321">Java VM Spec, 2.8</a>).
     * <p>
     * @param classNameIdentifier The String describing a class name identifier.
     * @return true if the classNameIdentifier is a well formed class name.
     */
    public static final boolean validExtClassname(String classNameIdentifier) {
	if (classNameIdentifier == null)
	    return false;

	// First make sure that there are no "double delim chars" i.e. '..'
	if (classNameIdentifier.indexOf(new String(extDelimCharIllStr)) > -1)
	    return false;

	// Next, make sure that the name does not start or end with a separator.
	if (classNameIdentifier.startsWith(str_innerMembSeparator) ||
	    classNameIdentifier.endsWith(str_innerMembSeparator)  )
	    return false;
	
	// Separate the package name from the class name - and validate each.
	String className = classNameIdentifier;
	if (classNameIdentifier.indexOf(str_innerMembSeparator) > -1) {
	    int lastDot = classNameIdentifier.lastIndexOf(str_innerMembSeparator);
	    String packageName = classNameIdentifier.substring(0, lastDot);
	    className = classNameIdentifier.substring(lastDot + 1, classNameIdentifier.length());

	    // can't have '.classname'
	    if (packageName == null || packageName.equals(""))
		return false;
	    
	    if (! validExtPackagename(packageName))
		return false;
	}

	return validIdentifier(className);
    }



    /**
     *  Utility: (Predicate) Validates an identifier as a valid Java identifier 
     * (<a href="http://java.sun.com/docs/books/vmspec/2nd-edition/html/Concepts.doc.html#21272">Java VM Spec, 2.7</a>) &
     * (<a href="http://java.sun.com/docs/books/vmspec/2nd-edition/html/Concepts.doc.html#29321">Java VM Spec, 2.8</a>).
     * <p>
     * @param classNameString The String describing an identifier.
     * @return true if the identifier is a well formed Java identifier.
     */
    public static final boolean isArray(String classNameString) {
	return ( (classNameString != null) &&
		 (classNameString.length() > 0) &&
		 (classNameString.startsWith("[")));
    }

    /**
     *  Utility: (Predicate) Validates an identifier as a valid Java identifier 
     * (<a href="http://java.sun.com/docs/books/vmspec/2nd-edition/html/Concepts.doc.html#21272">Java VM Spec, 2.7</a>) &
     * (<a href="http://java.sun.com/docs/books/vmspec/2nd-edition/html/Concepts.doc.html#29321">Java VM Spec, 2.8</a>).
     * <p>
     * @param identifier The String describing an identifier.
     * @return true if the identifier is a well formed Java identifier.
     */
    public static final boolean validIdentifier(String identifier) {
	return (_validIdentifier(identifier) &&   ! isJavaKeyWord(identifier));
    }
    

    /**
     *  Utility: (Predicate) Validates an identifier as a valid Java identifier 
     * (<a href="http://java.sun.com/docs/books/vmspec/2nd-edition/html/Concepts.doc.html#21272">Java VM Spec, 2.7</a>) &
     * (<a href="http://java.sun.com/docs/books/vmspec/2nd-edition/html/Concepts.doc.html#29321">Java VM Spec, 2.8</a>).
     * <p>
     * @param identifier The String describing an identifier.
     * @return true if the identifier is a well formed Java identifier.
     */
    public static final boolean _validIdentifier(String identifier) {
	if (identifier == null || identifier.length() == 0)
	    return false;
	int length = identifier.length();
	char c;
	for (int i = 0; i < length; i++) {
	    c = identifier.charAt(i);

	    if (i == 0) {
		if ( ! Character.isJavaIdentifierStart(c) && ! Character.isUnicodeIdentifierStart(c)) {
		    return false;
		}
	    }
	    else {
		if ( ! Character.isJavaIdentifierPart(c) && ! Character.isUnicodeIdentifierPart(c)) {
		    return false;
		}
	    }
		
	}
	return true;
    }
    
    
    /**
     *  Utility: (Predicate) Describes if an identifier is a valid Java keyword. 
     * (<a href="http://java.sun.com/docs/books/jls/html/3.doc.html#229308">Java Language Specification, 3.9</a>).
     * <p>
     * @param name The String describing an identifier.
     * @return true if the identifier is a Java Keyword.
     */
    public static final boolean isJavaKeyWord(String name) {
	if (isPrimitiveType(name) ||
	    name.equals("null") ||
	    name.equals("true") ||
	    name.equals("false") ||
	    name.equals("abstract") ||
	    name.equals("break") ||
	    name.equals("case") ||
	    name.equals("catch") ||
	    name.equals("class") ||
	    name.equals("const") ||
	    name.equals("continue") ||
	    name.equals("default") ||
	    name.equals("do") ||
	    name.equals("else") ||
	    name.equals("extends") ||
	    name.equals("final") ||
	    name.equals("finally") ||
	    name.equals("for") ||
	    name.equals("goto") ||
	    name.equals("if") ||
	    name.equals("implements") ||
	    name.equals("import") ||
	    name.equals("instanceof") ||
	    name.equals("interface") ||
	    name.equals("native") ||
	    name.equals("new") ||
	    name.equals("package") ||
	    name.equals("private") ||
	    name.equals("protected") ||
	    name.equals("public") ||
	    name.equals("return") ||
	    name.equals("static") ||
	    name.equals("super") ||
	    name.equals("switch") ||
	    name.equals("synchronized") ||
	    name.equals("this") ||
	    name.equals("throw") ||
	    name.equals("throws") ||
	    name.equals("transient") ||
	    name.equals("try") ||
	    name.equals("volatile") ||
	    name.equals("while"))
	    return true;
	else 
	    return false;
    }
    
    /**
     *  Utility: (Predicate) Describes if an identifier is a valid Java keyword. 
     * (<a href="http://java.sun.com/docs/books/jls/html/4.doc.html#85587">Java Language Specification, 4.2</a>).
     * <p>
     * @param name The String describing an identifier.
     * @return true if the identifier is a Java Keyword.
     */
    public static final boolean isPrimitiveType(String name) {
	if (name.equals("boolean") ||
	    name.equals("char") ||
	    name.equals("int")||
	    name.equals("short")||
	    name.equals("long")||
	    name.equals("float")||
	    name.equals("double")||
	    name.equals("void")||
	    name.equals("byte"))
	    return true;
	else 
	    return false;
    }
    

    /**
     *  Utility: (Predicate) Validates a byte-stream as a valid Utf8 
     * (<a href="http://java.sun.com/docs/books/vmspec/2nd-edition/html/ClassFile.doc.html#3310">Java VM Spec, 4.4.7</a>).
     * <p> 
     * @param byte_contents A byte array representing some Utf8 byte stream.
     * @return true if the byte_contents is a well formed Utf8 byte-stream.
     */
    public static final boolean isValidUtf8(byte byte_contents[])  {

	for (int i = 0; i < byte_contents.length; i++) {
	    if (byte_contents[i] == 0)
		return false;
	    
	    if ((byte_contents[i] & 0x80) > 1) {
		
		if ((byte_contents[i] & 0xF0) == 0xE0) {
		    
		    if ( (i + 2) > byte_contents.length)
			return false;
		    
		    if ((byte_contents[i + 1] & 0xC0) != 0x80)
			return false;
		    
		    if ((byte_contents[i + 2] & 0xC0) != 0x80)
			return false;
		    
		    
		    i += 2;
		}
		
		else if ((byte_contents[i] & 0xE0) == 0xC0) {
		    // got a char that requires 2 bytes 
		    if ( (i + 2) > byte_contents.length)
			return false;
		    
		    if ((byte_contents[i + 1] & 0xC0) != 0x80)
			return false;
		    
		    i+=1;
		    
		}
		
		else
		    return false;
		}
	}
	
	return true;
    }



    /**
     *  Utility: Returns the char lenfth of some Utf8 byte stream
     * (<a href="http://java.sun.com/docs/books/vmspec/2nd-edition/html/ClassFile.doc.html#3310">Java VM Spec, 4.4.7</a>).
     * <p> 
     * @param byte_contents A byte array representing some Utf8 byte stream.
     * @return the char length of a Utf8 byte-stream.
     */
    public static final int Utf8LengthFromBytes(byte byte_contents[])  {
	int char_length = 0;

	for (int i = 0; i < byte_contents.length; i++) {
	    if ((byte_contents[i] & 0x80) > 1) {
		if ((byte_contents[i] & 0xF0) == 0xE0) {
		    // got a char that requires 3 bytes 
		    char_length += 3;
		    i+=2;
		}
		else if ((byte_contents[i] & 0xE0) == 0xC0) {
		    // got a char that requires 2 bytes 
		    char_length += 2;	
		    i+=1;
		}
	    }
	    else {
		char_length++;		    		    
	    }
	}

	return char_length;
    }

    /**
     *  Utility: Returns a raw byte array for some char array of Utf8 chars
     * (<a href="http://java.sun.com/docs/books/vmspec/2nd-edition/html/ClassFile.doc.html#3310">Java VM Spec, 4.4.7</a>).
     * <p> 
     * @param char_contents A char array representing some Utf8 characters.
     * @return a byte array of a Utf8 byte-stream.
     */
    public static final byte[] Utf8ToBytes(char char_contents[])  {
	int byteLength = 0;

	// first - count the number of bytes required
	for (int i = 0; i <= char_contents.length; i++) {
	    if (char_contents[i] >= '\u0800' && char_contents[i] <= '\uFFFF')
		byteLength += 3;
	    else if (char_contents[i] >= '\u0080' && char_contents[i] <= '\u07FF')
		byteLength += 2;
	    else
		byteLength++;
	}

	// allocate the bytes
	byte Utf8Bytes[] = new byte[byteLength];
	int currentByte = 0;

	for (int i = 0; i <= char_contents.length; i++) {
	    if (char_contents[i] >= '\u0800' && char_contents[i] <= '\uFFFF') {
		Utf8Bytes[currentByte++] = ((byte) (0xE0 | ((byte) ((char_contents[i] & 0xF000) >> 12))));
		Utf8Bytes[currentByte++] = ((byte) (0x80 | ((byte) ((char_contents[i] & 0x0FC0) >> 6))));
		Utf8Bytes[currentByte++] = ((byte) (0x80 | ((byte) (char_contents[i] & 0x003F))));
	    }
	    else if (char_contents[i] >= '\u0080' && char_contents[i] <= '\u07FF'){
		Utf8Bytes[currentByte++] = ((byte) (0xC0 | ((byte) ((char_contents[i] & 0x07C0) >> 6))));
		Utf8Bytes[currentByte++] = ((byte) (0x80 | ((byte) (char_contents[i] & 0x003F))));
	    }
	    else {
		Utf8Bytes[currentByte++] = ((byte) (0x80 |  ((byte) (char_contents[i] & 0x007F))));
	    }
	}

        return Utf8Bytes;
    }



    /**
     *  Utility: Returns a char array of Utf8 chars when given a raw byte array. 
     * (<a href="http://java.sun.com/docs/books/vmspec/2nd-edition/html/ClassFile.doc.html#3310">Java VM Spec, 4.4.7</a>).
     * <p> 
     * @param byte_contents A byte array of a Utf8 byte-stream. 
     * @return A char array representing some Utf8 characters.
     */
    public static final char[] Utf8FromBytes(byte byte_contents[])  {
	int charLength = Utf8LengthFromBytes(byte_contents);
	char Utf8Chars[] = new char[charLength];
	int j = 0;

	for (int i = 0; i < byte_contents.length; i++) {
	    if ((byte_contents[i] & 0x80) > 1) {
		if ((byte_contents[i] & 0xF0) == 0xE0) {
		    // got a char that requires 3 bytes 
		    Utf8Chars[j++] = (char) ( ((char) ((byte_contents[i] & 0x0F) << 12)) + 
					      ((char) ((byte_contents[i + 1] & 0x3F) << 6)) + 
					      ((char) (byte_contents[i + 2] & 0x3F)));
		    i+=2;
		    }
		    else if ((byte_contents[i] & 0xE0) == 0xC0) {
			// got a char that requires 2 bytes 
			Utf8Chars[j++] = (char) ( ((char) ((byte_contents[i] & 0x1F) << 6)) + 
			                          ((char) (byte_contents[i + 1] & 0x3F)));
			i+=1;
		    }
		}
		else {
		    Utf8Chars[j++] = (char) (byte_contents[i] & 0x7F);
		}
	    }
	
        return Utf8Chars;
    }


    /**
     *  Utility: Returns a unicode character given four (single byte) characters
     * (<a href="http://java.sun.com/docs/books/vmspec/2nd-edition/html/ClassFile.doc.html#3310">Java VM Spec, 4.4.7</a>).
     * <p> 
     * @param c0 A byte character (hi byte). 
     * @param c1 A byte character (hi middle byte). 
     * @param c2 A byte character (low middle byte). 
     * @param c3 A byte character (low byte). 
     * @return A Unicode char.
     */
    public static final char getUnicodeChar(char c0, char c1, char c2, char c3) {
        return (char) ( (Character.digit(c0, 16) << 12) |  
			(Character.digit(c1, 16) << 8) |  
			(Character.digit(c2, 16) << 4) |  
		        Character.digit(c3, 16) );
    }


}

