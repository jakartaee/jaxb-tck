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

package javasoft.sqe.tests.api.jakarta.xml.bind;

import java.io.*;
import java.util.*;

import javasoft.sqe.jck.lib.*;

import javax.xml.XMLConstants;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.SchemaOutputResolver;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.InputSource;

public class SchemaGenTestExt extends MultiTestExt {
    // available parameters
    public SchemaAsDocumentParameterValue schema = new SchemaAsDocumentParameterValue( "schemas" );
    public StringValue.Parameter schemaClasses = new StringValue.Parameter( "classes" );

    static protected final DocumentBuilderFactory docBldFactory = DocumentBuilderFactory.newInstance();
    static {
        docBldFactory.setNamespaceAware( true );
    }
    
    static interface IFileResolver {
        File[] getFile( String... fNames );
    }
    
            
    static class TrivialFileResolver implements IFileResolver {
        private TrivialFileResolver() {}
        
        public File[] getFile(String... fNames) {
        	int len = fNames.length;
        	File[] files = new File[len];
        	for(int i = 0; i<len; i++) {
        		files[i] = new File(fNames[i]);
        	}
            return files;
        }
        
        static private TrivialFileResolver instance = new TrivialFileResolver();
        static IFileResolver getInstance() {
            return instance;
        }
    }
    
    static class XMLDocumentParameterValue extends MultiTestExt.ParameterValue<Document> {
        final protected IFileResolver fr;
        public XMLDocumentParameterValue(  String name,IFileResolver fr ){
            super( name );
            this.fr = fr;
        }
        
        public XMLDocumentParameterValue( String name ){
            this(  name,TrivialFileResolver.getInstance() );
        }
        
        public int parseValue(String[] argv) {
            StringValue.Argument arg = new StringValue.Argument();
            int count = arg.parseValue(argv);
            try {
            	File file = fr.getFile(arg.get())[0];
                set( docBldFactory.newDocumentBuilder().parse(file) );
            } catch (Exception ex) {
                throw new InvalidValue( ex.toString() );
            }
            return count;
        }
    }
    
    static class SchemaAsDocumentParameterValue extends XMLDocumentParameterValue {
        protected Schema asSchema;

        public SchemaAsDocumentParameterValue(  String name,IFileResolver fr ) {
            super( name, fr );
        }
        public SchemaAsDocumentParameterValue( String name ) {
            super( name );
        }
        public void set( Document doc ) {
            super.set( doc );
            try {
                // Since sometime isn't possible to load a schema asSchema may be null.
                asSchema = SchemaLoader.loadSchema(new DOMSource(get()));
            } catch( Exception ex ) {
                throw new InvalidValue( ex.toString() );
            }
        }
        public Schema getAsSchema() {
            return asSchema;
        }
    } 

    static class SchemaParameterValue extends MultiTestExt.ParameterValue<Source[]> {
        final protected IFileResolver fr;
        protected Schema asSchema;

        public SchemaParameterValue(  String name,IFileResolver fr ) {
            super( name );
            this.fr = fr;
        }
        
        public SchemaParameterValue( String name ) {
        	this(  name,TrivialFileResolver.getInstance() );
        }
        
        public void set( Source[] sources ) {
            super.set( sources );
            try {
                // Since sometime isn't possible to load a schema asSchema may be null.
                asSchema = SchemaLoader.loadSchema(sources);
            } catch( Exception ex ) {
                throw new InvalidValue( ex.toString() );
            }
        }
        
        public Schema getAsSchema() {
            return asSchema;
        }

        public int parseValue(String[] argv) {
            ArrayStringValue.Argument arg = new ArrayStringValue.Argument();
            int count = arg.parseValue(argv);
            try {
            	set(getSchemaSources( fr.getFile(arg.get()) ) );
            } catch (Exception ex) {
                throw new InvalidValue( ex.toString() );
            }
            return count;
        }
        
        private Source[] getSchemaSources(File[] fileList) {
        	int len = fileList.length;
            Source[] srcList = new Source[len];
            for (int i = 0; i < len; i++) {
            	srcList[i] = new StreamSource(fileList[i]);
			}
            return srcList;
        }        
        
    }
    
    protected void init() throws SetupException {
        super.init();
        
        if( schema.get() == null ){
            if( schemaClasses.get() == null )
                throw new SetupException( "nor -schemas, nor -classes parameter isn't specified" );
            try {
                List<Class<?>> classes = new ArrayList<Class<?>>();
                StringTokenizer tokenizer = new StringTokenizer(schemaClasses.get(), ":", false);
                while (tokenizer.hasMoreElements()) {
                    classes.add( Class.forName( tokenizer.nextToken() ) );
                }
                class SOR extends SchemaOutputResolver {
                    List<Document> schemas = new LinkedList<Document>();
                    public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
                        try {
                            Document doc = docBldFactory.newDocumentBuilder().newDocument();
                            schemas.add( doc );
                            return new DOMResult( doc, suggestedFileName );
                        } catch( ParserConfigurationException x ){
                            x.printStackTrace( ref );
                            throw new IOException( x.getMessage() );
                        }
                    }
                };
                SOR resolver = new SOR();
                JAXBContext context = JAXBContext.newInstance(classes.toArray( new Class[classes.size()] ));
                context.generateSchema(resolver);
                if( resolver.schemas.size() != 1 )
                    throw new SetupException( 
                        String.format( "SchemaGenTestExt: Wrong number of generated schemas (%d)", resolver.schemas.size() ) );
                // 
                schema.set( resolver.schemas.iterator().next() );
            } catch( Exception x ){
                x.printStackTrace( ref );
                throw new SetupException( x.getMessage() );
            }
        } 
    }

    public static void writeTestXml(Document doc, StreamResult out) throws TransformerException {
        // Prepare the DOM document for writing
        Source source = new DOMSource(doc);

        // Write the DOM document to the file
        Transformer xformer = TransformerFactory.newInstance().newTransformer();
        // Set the system id
        xformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "testgenspec.dtd");
        xformer.setOutputProperty(OutputKeys.INDENT, "yes");
        xformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        xformer.transform(source, out);
    }

    static String toXmlName( String javaName ) {
        char[] name = javaName.toCharArray();
        int i;
        for( i = 0; i < name.length && Character.isUpperCase( name[ i ] ); i++){
            name[ i ] = Character.toLowerCase( name[ i ] );
        }
        if( i < name.length && i > 1 )
            name[ i - 1 ] = Character.toUpperCase( name[ i - 1 ] );
        return new String( name );
            //java.beans.Introspector.decapitalize( clazz.getSimpleName() );
    }

    public static String getFieldName(int i) {
        return "f" + i;
    }

    static public String generateJavaSource( File outDir, String className, String typeParameter,
                                                String... objType ) throws IOException {
        String header =
            "/*\n" +
            " * @(#)SchemaGenTestExt.java	1.8 08/10/20\n" +
            " *\n" +
            " * Copyright (c) 2008, 2018 Oracle and/or its affiliates. All rights reserved.\n" +
            " */\n\n";
        File out = new File( outDir, className + ".java" );
        OutputStreamWriter writer = new FileWriter( out );
        if( typeParameter == null ) typeParameter = "";
        writer.write( header );
        writer.write( String.format( "public class %s%s {\n", className, typeParameter ) );
        for( int i = 0; i < objType.length; i++){
            if( objType[ i ] != null )
                writer.write( String.format( "\tpublic %s\t%s;\n", objType[ i ], getFieldName( i ) ) );
        }
        writer.write( "}\n" );
        writer.close();
        return out.getName();
    }

    public static String generateCheckElementTypetCode( String testCaseID, String objTypeDescription, String namespace, String element ) {
        return String.format( "checkElementType( \"%s\", \"%s\", \"%s\", \"%s\" );",
                        toXmlName( testCaseID ), objTypeDescription, namespace, element );
    }

    public static String generateCheckContainerCode( String testCaseID, String containerType, String fieldName ) {
        return String.format( "checkContainer( \"%s\", \"%s\", \"%s\" );",
                        toXmlName( testCaseID ), containerType, fieldName );
    }

    public static String generateCheckMapCode( String testCaseID, String mapType, String fieldName ) {
        return String.format( "checkMap( \"%s\", \"%s\", \"%s\" );",
                        toXmlName( testCaseID ), mapType, fieldName );
    }

    static XPath xpath;
    static {
        xpath = XPathFactory.newInstance().newXPath();
        xpath.setNamespaceContext( new NamespaceContext() {
            final String xs = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            public String getNamespaceURI(String prefix) {
                if( "xs".equals( prefix ) )
                    return xs;
                return "";
            }
            public String getPrefix(String namespaceURI) {
                if( xs.equals( namespaceURI ) )
                    return "xs";
                return "";
            }
            public Iterator<?> getPrefixes(String namespaceURI) {
                return Collections.singleton( getPrefix(namespaceURI) ).iterator();
            }
        } );
    }
    public void checkElementType( String testCaseID, String objTypeDescription, String namespace, String element ) {
        ref.print( testCaseID + " ..." );
        String xpathStr = String.format( "xs:schema/xs:complexType[@name='%s']/xs:sequence/xs:element/@type",
                                            toXmlName( testCaseID ) );
        try {
            XPathExpression xpathExpr = xpath.compile( xpathStr );
            NodeList l = (NodeList) xpathExpr.evaluate( schema.get(), XPathConstants.NODESET );
            /*for( int i = 0; i < l.getLength(); i++)
                TransformerFactory.newInstance().newTransformer().transform(new DOMSource(l.item( i )), new StreamResult( System.out ) );
            */
            assertTrue( l.getLength() == 1,
                String.format( "schema should contain only one '%s' node. %d nodes are found", xpathStr, l.getLength() ) );
            String type = l.item( 0 ).getNodeValue();
            int idx = type.indexOf(':');
            if( idx == -1 ){
                String errMsg =
                    String.format( "Object of type '%s' should be converted into xml type '%s:%s'.\n\tConverted into '%s'",
                            objTypeDescription, namespace, element, type );
                assertTrue( namespace == null || "".equals( namespace ), errMsg );
                assertEquals( type, element, errMsg );
            } else {
                String elemType = type.substring( idx + 1 );
                String ns = schema.get().lookupNamespaceURI( type.substring( 0, idx ) );
                String errMsg =
                    String.format( "Object of type '%s' should be converted into xml type '%s:%s'.\n\tConverted into '%s:%s'",
                            objTypeDescription, namespace, element, ns, elemType );
                assertEquals( elemType, element, errMsg );
                assertEquals( ns, namespace, errMsg );
            }
            ref.print( " OK" );
        } catch (XPathExpressionException ex) {
            ex.printStackTrace( ref );
            throw new TestFailureException( ex );
        } finally {
            ref.println();
        }
    }
    public void checkElementType( Class<?> clazz, String namespace, String element ) {
        checkElementType( clazz.getSimpleName() + "Test", clazz.toString(), namespace, element );
    }

    public void checkContainer( String testCaseID, String testClassName, String fieldName ) {
        ref.print( testCaseID + " ..." );
        String xpathStr = String.format( "xs:schema/xs:complexType[@name='%s']/xs:sequence/xs:element[@name='%s']",
                                            toXmlName( testClassName ), fieldName );
        try {
            XPathExpression xpathExpr = xpath.compile( xpathStr );
            NodeList l = (NodeList) xpathExpr.evaluate( schema.get(), XPathConstants.NODESET );
            /*for( int i = 0; i < l.getLength(); i++)
                TransformerFactory.newInstance().newTransformer().transform(new DOMSource(l.item( i )), new StreamResult( System.out ) );
            */
            assertTrue( l.getLength() == 1,
                String.format( "schema should contain only one '%s' node. %d nodes are found", xpathStr, l.getLength() ) );
            Element elem = (Element) l.item( 0 );
            // assertEquals( elem.getAttribute( "nillable" ), "true", "the attribute 'nillable' must have value 'true'" );
            assertEquals( elem.getAttribute( "minOccurs" ), "0", "the attribute 'minOccurs' must have value '0'" );
            assertEquals( elem.getAttribute( "maxOccurs" ), "unbounded", "the attribute 'maxOccurs' must have value 'unbounded'" );
            ref.println( " OK" );
        } catch (XPathExpressionException ex) {
            ex.printStackTrace( ref );
            throw new TestFailureException( ex );
        }
    }

    static protected Node createValue( Document doc, String elementName, Object value ){
        Element elem = (Element)doc.createElement( elementName );
        String type;
        if( value instanceof Integer ){
            type = "xs:int";
        } else if( value instanceof Double ){
            type = "xs:double";
        } else if( value instanceof String ){
            type = "xs:string";
        } else {
            throw new IllegalArgumentException( String.format( "object '%s' has unsupported type", value ) );
        }
        elem.setAttributeNS( XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI, "xsi:type", type );
        elem.appendChild( doc.createTextNode( value.toString() ) );
        return elem;
    }

    static protected Node createMapEntry( Document doc, Object key, Object value ) {
        Node entry = doc.createElement( "entry" );
        entry.appendChild( createValue( doc, "key", key ) );
        entry.appendChild( createValue( doc, "value", value ) );
        return entry;
    }

    public void checkMap( String testCaseID, String schemaName, String fieldName ) {
        ref.printf( "TC %s (schemaName = %s) ... ", testCaseID, schemaName );
        try {
            // create XML document to validate
            Document doc = docBldFactory.newDocumentBuilder().newDocument();
            Element root = (Element)doc.appendChild( doc.createElement("root" )  );
            root.setAttributeNS( XMLConstants.XMLNS_ATTRIBUTE_NS_URI, "xmlns:xsi", XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI );
            root.setAttributeNS( XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI, "xsi:type", toXmlName( schemaName ) );
            Element mapElem = (Element)root.appendChild( doc.createElement( fieldName ) );
            mapElem.setAttributeNS( XMLConstants.XMLNS_ATTRIBUTE_NS_URI, "xmlns:xs", XMLConstants.W3C_XML_SCHEMA_NS_URI );
            mapElem.appendChild( createMapEntry( doc, 0, 1 ) );
            mapElem.appendChild( createMapEntry( doc, "key", "value" ) );
            mapElem.appendChild( createMapEntry( doc, 2.5, "value" ) );

            // create validator
            Validator validator = schema.getAsSchema().newValidator();
            try {
                /*TransformerFactory.newInstance().newTransformer().transform(
                    new DOMSource( doc ), new StreamResult( new File( testCaseID + ".xml" ) ) );*/

                // validator.validate( new DOMSource( doc ) ); // this check fails because of bug in RI
                StringWriter intermediate = new StringWriter();
                TransformerFactory.newInstance().newTransformer().transform(
                                        new DOMSource( doc ), new StreamResult( intermediate ) );
                validator.validate( new SAXSource( new InputSource( new StringReader( intermediate.toString() ) ) ) );
                ref.print( "OK" );
            } catch( Exception x ){
                x.printStackTrace( ref );
                throw new TestFailureException( String.format( "%s: validation is failed (schema name = '%s')\n", testCaseID, schemaName ) );
            }
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
            throw new TestFailureException( ex );
        } finally {
            ref.println();
        }
    }

    public static void main(String[] args) {
        PrintWriter log = new PrintWriter(System.err, true);
        PrintWriter ref = new PrintWriter(System.out, true);
        try {
            new SchemaGenTestExt().run(args, log, ref).exit();
        } finally {
            ref.flush();
            log.flush();
        }
    }
}
