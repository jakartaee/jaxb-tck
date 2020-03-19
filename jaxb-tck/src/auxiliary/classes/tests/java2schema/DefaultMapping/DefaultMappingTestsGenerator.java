/*
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
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

package tests.java2schema.defaultmapping;

import java.io.*;
import java.lang.reflect.*;
import java.util.Arrays;
import javasoft.sqe.jck.lib.*;
import javasoft.sqe.tests.api.jakarta.xml.bind.SchemaGenTestExt;
import javax.xml.XMLConstants;
import jakarta.xml.bind.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import javasoft.sqe.jaxb.tgxml.testgen.*;
import javasoft.sqe.jaxb.tgxml.testgen.TestCase;

public class DefaultMappingTestsGenerator {
    TestGroup tg = null;
    File outDir = null;

    public DefaultMappingTestsGenerator( File outDir ){
        this.outDir = outDir;

        tg = new TestGroup();
        tg.setID( "DefaultMappingTest" );
        tg.setTestGroupDocumentation( new TestGroupDocumentation() );
        tg.getTestGroupDocumentation().setTitle( "Tests to check default mapping" );
        Author author = new Author();
        author.setvalue("auto generated");
        tg.getTestGroupDocumentation().getAuthor().add( author );

        tg.setTestGroupAttributes( new TestGroupAttributes() );

        AttrElem ae;
        ae = new AttrElem(); ae.setName( "scInfo" ); ae.setvalue( "%W% %E%" );
        tg.getTestGroupAttributes().getAttrElem().add( ae );
        ae = new AttrElem(); ae.setName( "testType" ); ae.setvalue( "J2XTestWTB" );
        tg.getTestGroupAttributes().getAttrElem().add( ae );

        Keyword kw;
        kw = new Keyword(); kw.setvalue( "positive" );       tg.getTestGroupAttributes().getKeyword().add( kw );
        kw = new Keyword(); kw.setvalue( "java_to_schema" ); tg.getTestGroupAttributes().getKeyword().add( kw );
        kw = new Keyword(); kw.setvalue( "runtime" );        tg.getTestGroupAttributes().getKeyword().add( kw );

        tg.setCodeSet( new CodeSet() );
        tg.getCodeSet().setBaseClass( SchemaGenTestExt.class.getName() );
    }

    static class ElementID extends Pair<String, String> {
        public ElementID( String namespaceURI, String qualifiedName ){
            super( namespaceURI, qualifiedName );
        }
    };
    static class Class2Elem extends Pair< Class, ElementID > {
        public Class2Elem( Class clazz, ElementID elem ){
            super( clazz, elem );
        }
    };

    static final String xs = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    static final Class2Elem[] correspondence = {
        new Class2Elem( Boolean.class,  new ElementID( xs, "boolean" ) ),
        new Class2Elem( Byte.class,     new ElementID( xs, "byte" ) ),
        new Class2Elem( Short.class,    new ElementID( xs, "short" ) ),
        new Class2Elem( Integer.class,  new ElementID( xs, "int" ) ),
        new Class2Elem( Long.class,     new ElementID( xs, "long" ) ),
        new Class2Elem( Float.class,    new ElementID( xs, "float" ) ),
        new Class2Elem( Double.class,   new ElementID( xs, "double" ) ),

        new Class2Elem( java.lang.String.class,     new ElementID( xs, "string" ) ),
        new Class2Elem( java.math.BigInteger.class, new ElementID( xs, "integer" ) ),
        new Class2Elem( java.math.BigDecimal.class, new ElementID( xs, "decimal" ) ),
        new Class2Elem( java.util.Calendar.class,   new ElementID( xs, "dateTime" ) ),
        new Class2Elem( java.util.Date.class,       new ElementID( xs, "dateTime" ) ),
        new Class2Elem( javax.xml.namespace.QName.class,    new ElementID( xs, "QName" ) ),
        new Class2Elem( java.net.URI.class,         new ElementID( xs, "string" ) ),
        new Class2Elem( javax.xml.datatype.XMLGregorianCalendar.class,  new ElementID( xs, "anySimpleType" ) ),
        new Class2Elem( javax.xml.datatype.Duration.class,  new ElementID( xs, "duration" ) ),
        new Class2Elem( java.lang.Object.class,     new ElementID( xs, "anyType" ) ),
        new Class2Elem( java.awt.Image.class,       new ElementID( xs, "base64Binary" ) ),
        new Class2Elem( javax.activation.DataHandler.class, new ElementID( xs, "base64Binary" ) ),
        new Class2Elem( javax.xml.transform.Source.class,   new ElementID( xs, "base64Binary" ) ),
        new Class2Elem( java.util.UUID.class,       new ElementID( xs, "string" ) )
    };

    static Class<? extends java.util.Collection>[]
                    createCollectionsClassesArray(Class<? extends java.util.Collection>... clazz){
        return clazz;
    }
    static final Class<? extends java.util.Collection>[] collectionClasses =
        createCollectionsClassesArray(
            java.util.Collection.class,
            // interfaces
            java.beans.beancontext.BeanContext.class,
            java.beans.beancontext.BeanContextServices.class,
//            java.util.concurrent.BlockingDeque.class,
            java.util.concurrent.BlockingQueue.class,
//            java.util.Deque.class,
            java.util.List.class,
//            java.util.NavigableSet.class,
            java.util.Queue.class,
            java.util.Set.class,
            java.util.SortedSet.class,
            // classes
            java.util.AbstractCollection.class,
            java.util.AbstractList.class,
            java.util.AbstractQueue.class,
            java.util.AbstractSequentialList.class,
            java.util.AbstractSet.class,
            java.util.concurrent.ArrayBlockingQueue.class,
//            java.util.ArrayDeque.class,
            java.util.ArrayList.class,
            javax.management.AttributeList.class,
            java.beans.beancontext.BeanContextServicesSupport.class,
            java.beans.beancontext.BeanContextSupport.class,
            java.util.concurrent.ConcurrentLinkedQueue.class,
//            java.util.concurrent.ConcurrentSkipListSet.class,
            java.util.concurrent.CopyOnWriteArrayList.class,
            java.util.concurrent.CopyOnWriteArraySet.class,
            // java.util.concurrent.DelayQueue.class,
            java.util.EnumSet.class,
            java.util.HashSet.class,
            // javax.print.attribute.standard.JobStateReasons.class,
//            java.util.concurrent.LinkedBlockingDeque.class,
            java.util.concurrent.LinkedBlockingQueue.class,
            java.util.LinkedHashSet.class,
            java.util.LinkedList.class,
            java.util.concurrent.PriorityBlockingQueue.class,
            java.util.PriorityQueue.class,
            javax.management.relation.RoleList.class,
            javax.management.relation.RoleUnresolvedList.class,
            java.util.Stack.class,
            java.util.concurrent.SynchronousQueue.class,
            java.util.TreeSet.class,
            java.util.Vector.class
        );
    static Class<? extends java.util.Map>[]
                    createMapsClassesArray(Class<? extends java.util.Map>... clazz){
        return clazz;
    }
    static final Class<? extends java.util.Map>[] mapClasses =
        createMapsClassesArray(
            java.util.Map.class,
            // interfaces
//            javax.script.Bindings.class,
            java.util.concurrent.ConcurrentMap.class,
//            java.util.concurrent.ConcurrentNavigableMap.class,
//            javax.xml.ws.handler.LogicalMessageContext.class,
//            javax.xml.ws.handler.MessageContext.class,
//            java.util.NavigableMap.class,
//            javax.xml.ws.handler.soap.SOAPMessageContext.class,
            java.util.SortedMap.class,
            // classes
            java.util.AbstractMap.class,
            java.util.jar.Attributes.class,
            java.security.AuthProvider.class,
            java.util.concurrent.ConcurrentHashMap.class,
//            java.util.concurrent.ConcurrentSkipListMap.class,
            java.util.EnumMap.class,
            java.util.HashMap.class,
            java.util.Hashtable.class,
            java.util.IdentityHashMap.class,
            java.util.LinkedHashMap.class,
            // javax.print.attribute.standard.PrinterStateReasons.class,
            java.util.Properties.class,
            java.security.Provider.class,
            java.awt.RenderingHints.class,
//            javax.script.SimpleBindings.class,
            javax.management.openmbean.TabularDataSupport.class,
            java.util.TreeMap.class,
            javax.swing.UIDefaults.class,
            java.util.WeakHashMap.class
        );

    TestCase createTestCase( String testCaseID, String title, String sourceFileName, String code ) {

        title = title.replaceAll( "<", "&lt;" ).replaceAll( ">", "&gt;'" );

        testCaseID = testCaseID.replace( ".", "$" );
        TestCase tc = new TestCase();
        tc.setID( testCaseID );
        tc.setTestCaseDocumentation( new TestCaseDocumentation() );
        tc.getTestCaseDocumentation().setTitle( title );

        InlineAssertion ia = new InlineAssertion();
        ia.setvalue( title );
        TestCaseSpec tcs = new TestCaseSpec();
        tcs.getAssertionRefOrInlineAssertion().add( ia );
        tc.getTestCaseDocumentation().getTestCaseSpec().add( tcs );

        tc.setCodeSet( new CodeSet() );
        //ExternalData ed = new ExternalData(); ed.setSourceName( sourceFileName ); ed.setType( "iodata" );
        //tc.getCodeSet().getExternalDataOrInlineData().add( ed );
        ExternalSupportClass suppClass = new ExternalSupportClass();
        suppClass.setSourceLang( "java" ); suppClass.setSourceName( sourceFileName );
        tc.getCodeSet().getExternalSupportClassOrInlineSupportClass().add( suppClass );
        TestCode testCode = new TestCode();
        testCode.setSourceLang( "java" );
        testCode.setvalue( code + " return Status.passed(\"OK\");" );
        tc.setTestCode( testCode );
        return tc;
    }

    TestCase createElementTypeTestCase( String testCaseID, String varType, String objType, ElementID elementID ) throws IOException {
        String srcFileName = SchemaGenTestExt.generateJavaSource( outDir, testCaseID, varType, objType );
        return createTestCase( testCaseID, String.format("tests mapping '%s' to '%s'", varType, objType), srcFileName,
                SchemaGenTestExt.generateCheckElementTypetCode( testCaseID, objType, elementID.first, elementID.second ) );
    }

    TestCase createContainerTestCase( String classFileName, String testClassName, String containerType, String fieldName ) {
        String testCaseID = containerType + "Test";
        return createTestCase( testCaseID, String.format("tests mapping of '%s' container", containerType), classFileName,
                SchemaGenTestExt.generateCheckContainerCode( testCaseID, testClassName, fieldName ) );
    }


    TestCase createMapTestCase( String classFileName, String mapType, String fieldName ) {
        String testCaseID = mapType + "Test";
        String schemaName = new File( classFileName ).getName();
        schemaName = schemaName.substring(0, schemaName.length() - ".java".length());
        return createTestCase( testCaseID, String.format("tests mapping of '%s' map", mapType), classFileName,
                SchemaGenTestExt.generateCheckMapCode( testCaseID, schemaName, fieldName ) );
    }

    DefaultMappingTestsGenerator createJavaStandardClassesMappingTests() throws IOException, JAXBException, TransformerException {
        // append test cases
        for( Class2Elem c : correspondence ){
            tg.getTestCase().add( createElementTypeTestCase( c.first.getSimpleName() + "Test", null, c.first.getName(), c.second ) );
        }

        // A byte[] must map to xs:base64Binary by default.
        tg.getTestCase().add( createElementTypeTestCase( "ByteArrayTest", null, "byte[]", new ElementID( xs, "base64Binary" ) ) );

        // generics
        tg.getTestCase().add( createElementTypeTestCase( "GenericTTest", "<T>", "T", new ElementID( xs, "anyType" ) ) );
        String refType = javax.xml.namespace.QName.class.getName();
        tg.getTestCase().add( createElementTypeTestCase( "GenericTExtendsTest",
                                String.format( "<T extends %s>", refType ), "T", new ElementID( xs, "QName" ) ) );
        // generic field
        String listType = java.util.List.class.getName();
        tg.getTestCase().add( createElementTypeTestCase( "ListGenericExtendsTest", null,
                                String.format( "%s<? extends %s>", listType, refType ), new ElementID( xs, "QName" ) ) );
        tg.getTestCase().add( createElementTypeTestCase( "ListGenericSuperTest", null,
                                String.format( "%s<? super %s>", listType, refType ), new ElementID( xs, "anyType" ) ) );
        return this;
    }

    DefaultMappingTestsGenerator createContainerMappingTests() throws IOException {
        String testClassName = "ContainersTest";
        String[] type = new String[ collectionClasses.length ];
        for( int i = 0; i < collectionClasses.length; i++){
            Class<? extends java.util.Collection> clazz = collectionClasses[ i ];
            try {
                StringBuilder str = new StringBuilder( clazz.getName() );
                TypeVariable[] typeVariable = clazz.getTypeParameters();
                if( typeVariable.length > 0 ){
                    str.append( '<' );
                    for( int j = 0; j < typeVariable.length; j++){
                        Type[] bounds = typeVariable[ j ].getBounds();
                        switch( bounds.length ){
                            case 0:
                                str.append( java.lang.String.class.getName() ); // any Object descendant
                                break;
                            case 1: {
                                Type t = bounds[ 0 ];
                                if( !(t instanceof Class) )
                                    throw new RuntimeException( "Type is not a Class" );
                                Class c = (Class)t;
                                str.append( c.getName() );
                            }   break;
                            default:
                                throw new RuntimeException( "too mach bounds!!!" );
                        }
                        str.append( ", " );
                    }
                    str.delete( str.length() - 2, str.length() );
                    str.append( '>' );
                }
                type[ i ] = str.toString();
            } catch( RuntimeException ex ){
                type[ i ] = null;
                System.out.println(
                    String.format( "Problem with '%s': '%s'. Skipped", clazz.toString(), ex.getMessage() ) );
            }
        }
        String sourceFN = SchemaGenTestExt.generateJavaSource( outDir, testClassName, null, type );
        for( int i = 0; i < collectionClasses.length; i++){
            if( type[ i ] != null )
                tg.getTestCase().add( createContainerTestCase( sourceFN, testClassName, collectionClasses[ i ].getName(),
                                                    SchemaGenTestExt.getFieldName( i ) ) );
        }
        return this;
    }

    DefaultMappingTestsGenerator createMapMappingTests() throws IOException {
        for( int i = 0; i < mapClasses.length; i++){
            Class<? extends java.util.Map> clazz = mapClasses[ i ];
            try {
                StringBuilder typeStr = new StringBuilder( clazz.getName() );
                TypeVariable[] typeVariable = clazz.getTypeParameters();
                if( typeVariable.length > 0 ){
                    typeStr.append( '<' );
                    for( int j = 0; j < typeVariable.length; j++){
                        Type[] bounds = typeVariable[ j ].getBounds();
                        switch( bounds.length ){
                            case 0:
                                typeStr.append( java.lang.String.class.getName() ); // any Object descendant
                                break;
                            case 1: {
                                Type t = bounds[ 0 ];
                                if( !(t instanceof Class) )
                                    throw new RuntimeException( "Type is not a Class" );
                                Class c = (Class)t;
                                typeStr.append( c.getName() );
                            }   break;
                            default:
                                throw new RuntimeException( "too mach bounds!!!" );
                        }
                        typeStr.append( ", " );
                    }
                    typeStr.delete( typeStr.length() - 2, typeStr.length() );
                    typeStr.append( '>' );
                }
                String sourceFN = SchemaGenTestExt.generateJavaSource( outDir, clazz.getSimpleName() + "Test",
                                                                            null, typeStr.toString() );
                tg.getTestCase().add( createMapTestCase( sourceFN, clazz.getName(), SchemaGenTestExt.getFieldName( 0 ) ) );
            } catch( RuntimeException ex ){
                System.out.println(
                    String.format( "Problem with '%s': '%s'. Skipped", clazz.toString(), ex.getMessage() ) );
            }
        }
        return this;
    }

     void writeTestGroup() throws TransformerException, JAXBException {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Binder<Node> binder =
                JAXBContext.newInstance( "javasoft.sqe.jaxb.tgxml.testgen" ).createBinder();
            binder.marshal( tg, doc );

            SchemaGenTestExt.writeTestXml( doc, new StreamResult( new File( outDir, tg.getID() + ".test.xml" ) ) );
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        }
    }

   static public void main( String argv[] ){

        try {
            if( argv.length == 1 ){
                new DefaultMappingTestsGenerator( new File( argv[ 0 ] ) ).
                                createJavaStandardClassesMappingTests().
                                createContainerMappingTests().
                                createMapMappingTests().
                    writeTestGroup();
            /*
            } else {
                // argv[ 0 ] is a schema file name
                SchemaGenTestExt t = new SchemaGenTestExt();
                t.run( new String[]{"-schema", argv[0]}, System.out, System.err );
                for( Class2Elem c : correspondence ){
                    try {
                        t.checkElementType( c.first, c.second.first, c.second.second );
                    } catch( Exception x ){ x.printStackTrace(); }
                }
                String refType = javax.xml.namespace.QName.class.getName();
                try {
                    t.checkElementType( "GenericTTest", "T", xs, "anyType" );
                } catch( Exception x ){ x.printStackTrace(); }
                try {
                    t.checkElementType( "GenericTExtendsTest",
                        String.format( "T extends %s", javax.xml.namespace.QName.class.getName() ), xs, "QName" );
                } catch( Exception x ){ x.printStackTrace(); }
                String listType = java.util.List.class.getName();
                try {
                    t.checkElementType( "ListGenericExtendsTest",
                        String.format( "%s<? extends %s>", listType, refType ), xs, "QName" );
                } catch( Exception x ){ x.printStackTrace(); }
                try {
                    t.checkElementType( "ListGenericSuperTest",
                        String.format( "%s<? super %s>", listType, refType ), xs, "anyType" );
                } catch( Exception x ){ x.printStackTrace(); }
                System.out.println( "OK" );
            */
            } else {
                System.err.printf( "Usage: DefaultMappingTestGenerator <output directory> (%s)\n",
                                        Arrays.toString( argv ) );
            }
        } catch( Exception x ) {
            x.printStackTrace();
        }
    }
}
