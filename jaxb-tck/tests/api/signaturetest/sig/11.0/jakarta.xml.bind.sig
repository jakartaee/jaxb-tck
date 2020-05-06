#Signature file v4.3
#Version 

# Copyright (c) 2018, 2020 Oracle and/or its affiliates. All rights reserved.


CLSS public abstract jakarta.xml.bind.Binder<%0 extends java.lang.Object>
cons public <init>()
meth public abstract <%0 extends java.lang.Object> jakarta.xml.bind.JAXBElement<{%%0}> unmarshal({jakarta.xml.bind.Binder%0},java.lang.Class<{%%0}>) throws jakarta.xml.bind.JAXBException
meth public abstract jakarta.xml.bind.ValidationEventHandler getEventHandler() throws jakarta.xml.bind.JAXBException
meth public abstract java.lang.Object getJAXBNode({jakarta.xml.bind.Binder%0})
meth public abstract java.lang.Object getProperty(java.lang.String) throws jakarta.xml.bind.PropertyException
meth public abstract java.lang.Object unmarshal({jakarta.xml.bind.Binder%0}) throws jakarta.xml.bind.JAXBException
meth public abstract java.lang.Object updateJAXB({jakarta.xml.bind.Binder%0}) throws jakarta.xml.bind.JAXBException
meth public abstract javax.xml.validation.Schema getSchema()
meth public abstract void marshal(java.lang.Object,{jakarta.xml.bind.Binder%0}) throws jakarta.xml.bind.JAXBException
meth public abstract void setEventHandler(jakarta.xml.bind.ValidationEventHandler) throws jakarta.xml.bind.JAXBException
meth public abstract void setProperty(java.lang.String,java.lang.Object) throws jakarta.xml.bind.PropertyException
meth public abstract void setSchema(javax.xml.validation.Schema)
meth public abstract {jakarta.xml.bind.Binder%0} getXMLNode(java.lang.Object)
meth public abstract {jakarta.xml.bind.Binder%0} updateXML(java.lang.Object) throws jakarta.xml.bind.JAXBException
meth public abstract {jakarta.xml.bind.Binder%0} updateXML(java.lang.Object,{jakarta.xml.bind.Binder%0}) throws jakarta.xml.bind.JAXBException
supr java.lang.Object

CLSS public jakarta.xml.bind.DataBindingException
cons public <init>(java.lang.String,java.lang.Throwable)
cons public <init>(java.lang.Throwable)
supr java.lang.RuntimeException

CLSS public final jakarta.xml.bind.DatatypeConverter
meth public static boolean parseBoolean(java.lang.String)
meth public static byte parseByte(java.lang.String)
meth public static byte[] parseBase64Binary(java.lang.String)
meth public static byte[] parseHexBinary(java.lang.String)
meth public static double parseDouble(java.lang.String)
meth public static float parseFloat(java.lang.String)
meth public static int parseInt(java.lang.String)
meth public static int parseUnsignedShort(java.lang.String)
meth public static java.lang.String parseAnySimpleType(java.lang.String)
meth public static java.lang.String parseString(java.lang.String)
meth public static java.lang.String printAnySimpleType(java.lang.String)
meth public static java.lang.String printBase64Binary(byte[])
meth public static java.lang.String printBoolean(boolean)
meth public static java.lang.String printByte(byte)
meth public static java.lang.String printDate(java.util.Calendar)
meth public static java.lang.String printDateTime(java.util.Calendar)
meth public static java.lang.String printDecimal(java.math.BigDecimal)
meth public static java.lang.String printDouble(double)
meth public static java.lang.String printFloat(float)
meth public static java.lang.String printHexBinary(byte[])
meth public static java.lang.String printInt(int)
meth public static java.lang.String printInteger(java.math.BigInteger)
meth public static java.lang.String printLong(long)
meth public static java.lang.String printQName(javax.xml.namespace.QName,javax.xml.namespace.NamespaceContext)
meth public static java.lang.String printShort(short)
meth public static java.lang.String printString(java.lang.String)
meth public static java.lang.String printTime(java.util.Calendar)
meth public static java.lang.String printUnsignedInt(long)
meth public static java.lang.String printUnsignedShort(int)
meth public static java.math.BigDecimal parseDecimal(java.lang.String)
meth public static java.math.BigInteger parseInteger(java.lang.String)
meth public static java.util.Calendar parseDate(java.lang.String)
meth public static java.util.Calendar parseDateTime(java.lang.String)
meth public static java.util.Calendar parseTime(java.lang.String)
meth public static javax.xml.namespace.QName parseQName(java.lang.String,javax.xml.namespace.NamespaceContext)
meth public static long parseLong(java.lang.String)
meth public static long parseUnsignedInt(java.lang.String)
meth public static short parseShort(java.lang.String)
meth public static void setDatatypeConverter(jakarta.xml.bind.DatatypeConverterInterface)
supr java.lang.Object
hfds SET_DATATYPE_CONVERTER_PERMISSION,theConverter

CLSS public abstract interface jakarta.xml.bind.DatatypeConverterInterface
meth public abstract boolean parseBoolean(java.lang.String)
meth public abstract byte parseByte(java.lang.String)
meth public abstract byte[] parseBase64Binary(java.lang.String)
meth public abstract byte[] parseHexBinary(java.lang.String)
meth public abstract double parseDouble(java.lang.String)
meth public abstract float parseFloat(java.lang.String)
meth public abstract int parseInt(java.lang.String)
meth public abstract int parseUnsignedShort(java.lang.String)
meth public abstract java.lang.String parseAnySimpleType(java.lang.String)
meth public abstract java.lang.String parseString(java.lang.String)
meth public abstract java.lang.String printAnySimpleType(java.lang.String)
meth public abstract java.lang.String printBase64Binary(byte[])
meth public abstract java.lang.String printBoolean(boolean)
meth public abstract java.lang.String printByte(byte)
meth public abstract java.lang.String printDate(java.util.Calendar)
meth public abstract java.lang.String printDateTime(java.util.Calendar)
meth public abstract java.lang.String printDecimal(java.math.BigDecimal)
meth public abstract java.lang.String printDouble(double)
meth public abstract java.lang.String printFloat(float)
meth public abstract java.lang.String printHexBinary(byte[])
meth public abstract java.lang.String printInt(int)
meth public abstract java.lang.String printInteger(java.math.BigInteger)
meth public abstract java.lang.String printLong(long)
meth public abstract java.lang.String printQName(javax.xml.namespace.QName,javax.xml.namespace.NamespaceContext)
meth public abstract java.lang.String printShort(short)
meth public abstract java.lang.String printString(java.lang.String)
meth public abstract java.lang.String printTime(java.util.Calendar)
meth public abstract java.lang.String printUnsignedInt(long)
meth public abstract java.lang.String printUnsignedShort(int)
meth public abstract java.math.BigDecimal parseDecimal(java.lang.String)
meth public abstract java.math.BigInteger parseInteger(java.lang.String)
meth public abstract java.util.Calendar parseDate(java.lang.String)
meth public abstract java.util.Calendar parseDateTime(java.lang.String)
meth public abstract java.util.Calendar parseTime(java.lang.String)
meth public abstract javax.xml.namespace.QName parseQName(java.lang.String,javax.xml.namespace.NamespaceContext)
meth public abstract long parseLong(java.lang.String)
meth public abstract long parseUnsignedInt(java.lang.String)
meth public abstract short parseShort(java.lang.String)

CLSS public abstract interface jakarta.xml.bind.Element

CLSS public final jakarta.xml.bind.JAXB
meth public static <%0 extends java.lang.Object> {%%0} unmarshal(java.io.File,java.lang.Class<{%%0}>)
meth public static <%0 extends java.lang.Object> {%%0} unmarshal(java.io.InputStream,java.lang.Class<{%%0}>)
meth public static <%0 extends java.lang.Object> {%%0} unmarshal(java.io.Reader,java.lang.Class<{%%0}>)
meth public static <%0 extends java.lang.Object> {%%0} unmarshal(java.lang.String,java.lang.Class<{%%0}>)
meth public static <%0 extends java.lang.Object> {%%0} unmarshal(java.net.URI,java.lang.Class<{%%0}>)
meth public static <%0 extends java.lang.Object> {%%0} unmarshal(java.net.URL,java.lang.Class<{%%0}>)
meth public static <%0 extends java.lang.Object> {%%0} unmarshal(javax.xml.transform.Source,java.lang.Class<{%%0}>)
meth public static void marshal(java.lang.Object,java.io.File)
meth public static void marshal(java.lang.Object,java.io.OutputStream)
meth public static void marshal(java.lang.Object,java.io.Writer)
meth public static void marshal(java.lang.Object,java.lang.String)
meth public static void marshal(java.lang.Object,java.net.URI)
meth public static void marshal(java.lang.Object,java.net.URL)
meth public static void marshal(java.lang.Object,javax.xml.transform.Result)
supr java.lang.Object
hfds cache
hcls Cache

CLSS public abstract jakarta.xml.bind.JAXBContext
cons protected <init>()
fld public final static java.lang.String JAXB_CONTEXT_FACTORY = "jakarta.xml.bind.JAXBContextFactory"
meth public !varargs static jakarta.xml.bind.JAXBContext newInstance(java.lang.Class<?>[]) throws jakarta.xml.bind.JAXBException
meth public <%0 extends java.lang.Object> jakarta.xml.bind.Binder<{%%0}> createBinder(java.lang.Class<{%%0}>)
meth public abstract jakarta.xml.bind.Marshaller createMarshaller() throws jakarta.xml.bind.JAXBException
meth public abstract jakarta.xml.bind.Unmarshaller createUnmarshaller() throws jakarta.xml.bind.JAXBException
meth public abstract jakarta.xml.bind.Validator createValidator() throws jakarta.xml.bind.JAXBException
 anno 0 java.lang.Deprecated(boolean forRemoval=false, java.lang.String since="")
meth public jakarta.xml.bind.Binder<org.w3c.dom.Node> createBinder()
meth public jakarta.xml.bind.JAXBIntrospector createJAXBIntrospector()
meth public static jakarta.xml.bind.JAXBContext newInstance(java.lang.Class<?>[],java.util.Map<java.lang.String,?>) throws jakarta.xml.bind.JAXBException
meth public static jakarta.xml.bind.JAXBContext newInstance(java.lang.String) throws jakarta.xml.bind.JAXBException
meth public static jakarta.xml.bind.JAXBContext newInstance(java.lang.String,java.lang.ClassLoader) throws jakarta.xml.bind.JAXBException
meth public static jakarta.xml.bind.JAXBContext newInstance(java.lang.String,java.lang.ClassLoader,java.util.Map<java.lang.String,?>) throws jakarta.xml.bind.JAXBException
meth public void generateSchema(jakarta.xml.bind.SchemaOutputResolver) throws java.io.IOException
supr java.lang.Object

CLSS public abstract interface jakarta.xml.bind.JAXBContextFactory
meth public abstract jakarta.xml.bind.JAXBContext createContext(java.lang.Class<?>[],java.util.Map<java.lang.String,?>) throws jakarta.xml.bind.JAXBException
meth public abstract jakarta.xml.bind.JAXBContext createContext(java.lang.String,java.lang.ClassLoader,java.util.Map<java.lang.String,?>) throws jakarta.xml.bind.JAXBException

CLSS public jakarta.xml.bind.JAXBElement<%0 extends java.lang.Object>
cons public <init>(javax.xml.namespace.QName,java.lang.Class<{jakarta.xml.bind.JAXBElement%0}>,java.lang.Class,{jakarta.xml.bind.JAXBElement%0})
cons public <init>(javax.xml.namespace.QName,java.lang.Class<{jakarta.xml.bind.JAXBElement%0}>,{jakarta.xml.bind.JAXBElement%0})
fld protected boolean nil
fld protected final java.lang.Class scope
fld protected final java.lang.Class<{jakarta.xml.bind.JAXBElement%0}> declaredType
fld protected final javax.xml.namespace.QName name
fld protected {jakarta.xml.bind.JAXBElement%0} value
innr public final static GlobalScope
intf java.io.Serializable
meth public boolean isGlobalScope()
meth public boolean isNil()
meth public boolean isTypeSubstituted()
meth public java.lang.Class getScope()
meth public java.lang.Class<{jakarta.xml.bind.JAXBElement%0}> getDeclaredType()
meth public javax.xml.namespace.QName getName()
meth public void setNil(boolean)
meth public void setValue({jakarta.xml.bind.JAXBElement%0})
meth public {jakarta.xml.bind.JAXBElement%0} getValue()
supr java.lang.Object
hfds serialVersionUID

CLSS public final static jakarta.xml.bind.JAXBElement$GlobalScope
 outer jakarta.xml.bind.JAXBElement
cons public <init>()
supr java.lang.Object

CLSS public jakarta.xml.bind.JAXBException
cons public <init>(java.lang.String)
cons public <init>(java.lang.String,java.lang.String)
cons public <init>(java.lang.String,java.lang.String,java.lang.Throwable)
cons public <init>(java.lang.String,java.lang.Throwable)
cons public <init>(java.lang.Throwable)
meth public java.lang.String getErrorCode()
meth public java.lang.String toString()
meth public java.lang.Throwable getCause()
meth public java.lang.Throwable getLinkedException()
meth public void printStackTrace()
meth public void printStackTrace(java.io.PrintStream)
meth public void printStackTrace(java.io.PrintWriter)
meth public void setLinkedException(java.lang.Throwable)
supr java.lang.Exception
hfds errorCode,linkedException,serialVersionUID

CLSS public abstract jakarta.xml.bind.JAXBIntrospector
cons public <init>()
meth public abstract boolean isElement(java.lang.Object)
meth public abstract javax.xml.namespace.QName getElementName(java.lang.Object)
meth public static java.lang.Object getValue(java.lang.Object)
supr java.lang.Object

CLSS public final jakarta.xml.bind.JAXBPermission
cons public <init>(java.lang.String)
supr java.security.BasicPermission
hfds serialVersionUID

CLSS public jakarta.xml.bind.MarshalException
cons public <init>(java.lang.String)
cons public <init>(java.lang.String,java.lang.String)
cons public <init>(java.lang.String,java.lang.String,java.lang.Throwable)
cons public <init>(java.lang.String,java.lang.Throwable)
cons public <init>(java.lang.Throwable)
supr jakarta.xml.bind.JAXBException

CLSS public abstract interface jakarta.xml.bind.Marshaller
fld public final static java.lang.String JAXB_ENCODING = "jaxb.encoding"
fld public final static java.lang.String JAXB_FORMATTED_OUTPUT = "jaxb.formatted.output"
fld public final static java.lang.String JAXB_FRAGMENT = "jaxb.fragment"
fld public final static java.lang.String JAXB_NO_NAMESPACE_SCHEMA_LOCATION = "jaxb.noNamespaceSchemaLocation"
fld public final static java.lang.String JAXB_SCHEMA_LOCATION = "jaxb.schemaLocation"
innr public abstract static Listener
meth public abstract <%0 extends jakarta.xml.bind.annotation.adapters.XmlAdapter> void setAdapter(java.lang.Class<{%%0}>,{%%0})
meth public abstract <%0 extends jakarta.xml.bind.annotation.adapters.XmlAdapter> {%%0} getAdapter(java.lang.Class<{%%0}>)
meth public abstract jakarta.xml.bind.Marshaller$Listener getListener()
meth public abstract jakarta.xml.bind.ValidationEventHandler getEventHandler() throws jakarta.xml.bind.JAXBException
meth public abstract jakarta.xml.bind.attachment.AttachmentMarshaller getAttachmentMarshaller()
meth public abstract java.lang.Object getProperty(java.lang.String) throws jakarta.xml.bind.PropertyException
meth public abstract javax.xml.validation.Schema getSchema()
meth public abstract org.w3c.dom.Node getNode(java.lang.Object) throws jakarta.xml.bind.JAXBException
meth public abstract void marshal(java.lang.Object,java.io.File) throws jakarta.xml.bind.JAXBException
meth public abstract void marshal(java.lang.Object,java.io.OutputStream) throws jakarta.xml.bind.JAXBException
meth public abstract void marshal(java.lang.Object,java.io.Writer) throws jakarta.xml.bind.JAXBException
meth public abstract void marshal(java.lang.Object,javax.xml.stream.XMLEventWriter) throws jakarta.xml.bind.JAXBException
meth public abstract void marshal(java.lang.Object,javax.xml.stream.XMLStreamWriter) throws jakarta.xml.bind.JAXBException
meth public abstract void marshal(java.lang.Object,javax.xml.transform.Result) throws jakarta.xml.bind.JAXBException
meth public abstract void marshal(java.lang.Object,org.w3c.dom.Node) throws jakarta.xml.bind.JAXBException
meth public abstract void marshal(java.lang.Object,org.xml.sax.ContentHandler) throws jakarta.xml.bind.JAXBException
meth public abstract void setAdapter(jakarta.xml.bind.annotation.adapters.XmlAdapter)
meth public abstract void setAttachmentMarshaller(jakarta.xml.bind.attachment.AttachmentMarshaller)
meth public abstract void setEventHandler(jakarta.xml.bind.ValidationEventHandler) throws jakarta.xml.bind.JAXBException
meth public abstract void setListener(jakarta.xml.bind.Marshaller$Listener)
meth public abstract void setProperty(java.lang.String,java.lang.Object) throws jakarta.xml.bind.PropertyException
meth public abstract void setSchema(javax.xml.validation.Schema)

CLSS public abstract static jakarta.xml.bind.Marshaller$Listener
 outer jakarta.xml.bind.Marshaller
cons public <init>()
meth public void afterMarshal(java.lang.Object)
meth public void beforeMarshal(java.lang.Object)
supr java.lang.Object

CLSS public abstract interface jakarta.xml.bind.NotIdentifiableEvent
intf jakarta.xml.bind.ValidationEvent

CLSS public abstract interface jakarta.xml.bind.ParseConversionEvent
intf jakarta.xml.bind.ValidationEvent

CLSS public abstract interface jakarta.xml.bind.PrintConversionEvent
intf jakarta.xml.bind.ValidationEvent

CLSS public jakarta.xml.bind.PropertyException
cons public <init>(java.lang.String)
cons public <init>(java.lang.String,java.lang.Object)
cons public <init>(java.lang.String,java.lang.String)
cons public <init>(java.lang.String,java.lang.String,java.lang.Throwable)
cons public <init>(java.lang.String,java.lang.Throwable)
cons public <init>(java.lang.Throwable)
supr jakarta.xml.bind.JAXBException

CLSS public abstract jakarta.xml.bind.SchemaOutputResolver
cons public <init>()
meth public abstract javax.xml.transform.Result createOutput(java.lang.String,java.lang.String) throws java.io.IOException
supr java.lang.Object

CLSS public jakarta.xml.bind.TypeConstraintException
cons public <init>(java.lang.String)
cons public <init>(java.lang.String,java.lang.String)
cons public <init>(java.lang.String,java.lang.String,java.lang.Throwable)
cons public <init>(java.lang.String,java.lang.Throwable)
cons public <init>(java.lang.Throwable)
meth public java.lang.String getErrorCode()
meth public java.lang.String toString()
meth public java.lang.Throwable getLinkedException()
meth public void printStackTrace()
meth public void printStackTrace(java.io.PrintStream)
meth public void setLinkedException(java.lang.Throwable)
supr java.lang.RuntimeException
hfds errorCode,linkedException,serialVersionUID

CLSS public jakarta.xml.bind.UnmarshalException
cons public <init>(java.lang.String)
cons public <init>(java.lang.String,java.lang.String)
cons public <init>(java.lang.String,java.lang.String,java.lang.Throwable)
cons public <init>(java.lang.String,java.lang.Throwable)
cons public <init>(java.lang.Throwable)
supr jakarta.xml.bind.JAXBException

CLSS public abstract interface jakarta.xml.bind.Unmarshaller
innr public abstract static Listener
meth public abstract <%0 extends jakarta.xml.bind.annotation.adapters.XmlAdapter> void setAdapter(java.lang.Class<{%%0}>,{%%0})
meth public abstract <%0 extends jakarta.xml.bind.annotation.adapters.XmlAdapter> {%%0} getAdapter(java.lang.Class<{%%0}>)
meth public abstract <%0 extends java.lang.Object> jakarta.xml.bind.JAXBElement<{%%0}> unmarshal(javax.xml.stream.XMLEventReader,java.lang.Class<{%%0}>) throws jakarta.xml.bind.JAXBException
meth public abstract <%0 extends java.lang.Object> jakarta.xml.bind.JAXBElement<{%%0}> unmarshal(javax.xml.stream.XMLStreamReader,java.lang.Class<{%%0}>) throws jakarta.xml.bind.JAXBException
meth public abstract <%0 extends java.lang.Object> jakarta.xml.bind.JAXBElement<{%%0}> unmarshal(javax.xml.transform.Source,java.lang.Class<{%%0}>) throws jakarta.xml.bind.JAXBException
meth public abstract <%0 extends java.lang.Object> jakarta.xml.bind.JAXBElement<{%%0}> unmarshal(org.w3c.dom.Node,java.lang.Class<{%%0}>) throws jakarta.xml.bind.JAXBException
meth public abstract boolean isValidating() throws jakarta.xml.bind.JAXBException
meth public abstract jakarta.xml.bind.Unmarshaller$Listener getListener()
meth public abstract jakarta.xml.bind.UnmarshallerHandler getUnmarshallerHandler()
meth public abstract jakarta.xml.bind.ValidationEventHandler getEventHandler() throws jakarta.xml.bind.JAXBException
meth public abstract jakarta.xml.bind.attachment.AttachmentUnmarshaller getAttachmentUnmarshaller()
meth public abstract java.lang.Object getProperty(java.lang.String) throws jakarta.xml.bind.PropertyException
meth public abstract java.lang.Object unmarshal(java.io.File) throws jakarta.xml.bind.JAXBException
meth public abstract java.lang.Object unmarshal(java.io.InputStream) throws jakarta.xml.bind.JAXBException
meth public abstract java.lang.Object unmarshal(java.io.Reader) throws jakarta.xml.bind.JAXBException
meth public abstract java.lang.Object unmarshal(java.net.URL) throws jakarta.xml.bind.JAXBException
meth public abstract java.lang.Object unmarshal(javax.xml.stream.XMLEventReader) throws jakarta.xml.bind.JAXBException
meth public abstract java.lang.Object unmarshal(javax.xml.stream.XMLStreamReader) throws jakarta.xml.bind.JAXBException
meth public abstract java.lang.Object unmarshal(javax.xml.transform.Source) throws jakarta.xml.bind.JAXBException
meth public abstract java.lang.Object unmarshal(org.w3c.dom.Node) throws jakarta.xml.bind.JAXBException
meth public abstract java.lang.Object unmarshal(org.xml.sax.InputSource) throws jakarta.xml.bind.JAXBException
meth public abstract javax.xml.validation.Schema getSchema()
meth public abstract void setAdapter(jakarta.xml.bind.annotation.adapters.XmlAdapter)
meth public abstract void setAttachmentUnmarshaller(jakarta.xml.bind.attachment.AttachmentUnmarshaller)
meth public abstract void setEventHandler(jakarta.xml.bind.ValidationEventHandler) throws jakarta.xml.bind.JAXBException
meth public abstract void setListener(jakarta.xml.bind.Unmarshaller$Listener)
meth public abstract void setProperty(java.lang.String,java.lang.Object) throws jakarta.xml.bind.PropertyException
meth public abstract void setSchema(javax.xml.validation.Schema)
meth public abstract void setValidating(boolean) throws jakarta.xml.bind.JAXBException

CLSS public abstract static jakarta.xml.bind.Unmarshaller$Listener
 outer jakarta.xml.bind.Unmarshaller
cons public <init>()
meth public void afterUnmarshal(java.lang.Object,java.lang.Object)
meth public void beforeUnmarshal(java.lang.Object,java.lang.Object)
supr java.lang.Object

CLSS public abstract interface jakarta.xml.bind.UnmarshallerHandler
intf org.xml.sax.ContentHandler
meth public abstract java.lang.Object getResult() throws jakarta.xml.bind.JAXBException

CLSS public abstract interface jakarta.xml.bind.ValidationEvent
fld public final static int ERROR = 1
fld public final static int FATAL_ERROR = 2
fld public final static int WARNING = 0
meth public abstract int getSeverity()
meth public abstract jakarta.xml.bind.ValidationEventLocator getLocator()
meth public abstract java.lang.String getMessage()
meth public abstract java.lang.Throwable getLinkedException()

CLSS public abstract interface jakarta.xml.bind.ValidationEventHandler
meth public abstract boolean handleEvent(jakarta.xml.bind.ValidationEvent)

CLSS public abstract interface jakarta.xml.bind.ValidationEventLocator
meth public abstract int getColumnNumber()
meth public abstract int getLineNumber()
meth public abstract int getOffset()
meth public abstract java.lang.Object getObject()
meth public abstract java.net.URL getURL()
meth public abstract org.w3c.dom.Node getNode()

CLSS public jakarta.xml.bind.ValidationException
cons public <init>(java.lang.String)
cons public <init>(java.lang.String,java.lang.String)
cons public <init>(java.lang.String,java.lang.String,java.lang.Throwable)
cons public <init>(java.lang.String,java.lang.Throwable)
cons public <init>(java.lang.Throwable)
supr jakarta.xml.bind.JAXBException

CLSS public abstract interface jakarta.xml.bind.Validator
meth public abstract boolean validate(java.lang.Object) throws jakarta.xml.bind.JAXBException
meth public abstract boolean validateRoot(java.lang.Object) throws jakarta.xml.bind.JAXBException
meth public abstract jakarta.xml.bind.ValidationEventHandler getEventHandler() throws jakarta.xml.bind.JAXBException
meth public abstract java.lang.Object getProperty(java.lang.String) throws jakarta.xml.bind.PropertyException
meth public abstract void setEventHandler(jakarta.xml.bind.ValidationEventHandler) throws jakarta.xml.bind.JAXBException
meth public abstract void setProperty(java.lang.String,java.lang.Object) throws jakarta.xml.bind.PropertyException

CLSS public abstract interface jakarta.xml.bind.annotation.DomHandler<%0 extends java.lang.Object, %1 extends javax.xml.transform.Result>
meth public abstract javax.xml.transform.Source marshal({jakarta.xml.bind.annotation.DomHandler%0},jakarta.xml.bind.ValidationEventHandler)
meth public abstract {jakarta.xml.bind.annotation.DomHandler%0} getElement({jakarta.xml.bind.annotation.DomHandler%1})
meth public abstract {jakarta.xml.bind.annotation.DomHandler%1} createUnmarshaller(jakarta.xml.bind.ValidationEventHandler)

CLSS public jakarta.xml.bind.annotation.W3CDomHandler
cons public <init>()
cons public <init>(javax.xml.parsers.DocumentBuilder)
intf jakarta.xml.bind.annotation.DomHandler<org.w3c.dom.Element,javax.xml.transform.dom.DOMResult>
meth public javax.xml.parsers.DocumentBuilder getBuilder()
meth public javax.xml.transform.Source marshal(org.w3c.dom.Element,jakarta.xml.bind.ValidationEventHandler)
meth public javax.xml.transform.dom.DOMResult createUnmarshaller(jakarta.xml.bind.ValidationEventHandler)
meth public org.w3c.dom.Element getElement(javax.xml.transform.dom.DOMResult)
meth public void setBuilder(javax.xml.parsers.DocumentBuilder)
supr java.lang.Object
hfds builder

CLSS public final !enum jakarta.xml.bind.annotation.XmlAccessOrder
fld public final static jakarta.xml.bind.annotation.XmlAccessOrder ALPHABETICAL
fld public final static jakarta.xml.bind.annotation.XmlAccessOrder UNDEFINED
meth public static jakarta.xml.bind.annotation.XmlAccessOrder valueOf(java.lang.String)
meth public static jakarta.xml.bind.annotation.XmlAccessOrder[] values()
supr java.lang.Enum<jakarta.xml.bind.annotation.XmlAccessOrder>

CLSS public final !enum jakarta.xml.bind.annotation.XmlAccessType
fld public final static jakarta.xml.bind.annotation.XmlAccessType FIELD
fld public final static jakarta.xml.bind.annotation.XmlAccessType NONE
fld public final static jakarta.xml.bind.annotation.XmlAccessType PROPERTY
fld public final static jakarta.xml.bind.annotation.XmlAccessType PUBLIC_MEMBER
meth public static jakarta.xml.bind.annotation.XmlAccessType valueOf(java.lang.String)
meth public static jakarta.xml.bind.annotation.XmlAccessType[] values()
supr java.lang.Enum<jakarta.xml.bind.annotation.XmlAccessType>

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlAccessorOrder
 anno 0 java.lang.annotation.Inherited()
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[PACKAGE, TYPE])
intf java.lang.annotation.Annotation
meth public abstract !hasdefault jakarta.xml.bind.annotation.XmlAccessOrder value() value= UNDEFINED

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlAccessorType
 anno 0 java.lang.annotation.Inherited()
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[PACKAGE, TYPE])
intf java.lang.annotation.Annotation
meth public abstract !hasdefault jakarta.xml.bind.annotation.XmlAccessType value() value= PUBLIC_MEMBER

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlAnyAttribute
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[FIELD, METHOD])
intf java.lang.annotation.Annotation

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlAnyElement
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[FIELD, METHOD])
intf java.lang.annotation.Annotation
meth public abstract !hasdefault boolean lax() value= false
meth public abstract !hasdefault java.lang.Class<? extends jakarta.xml.bind.annotation.DomHandler> value() value= class jakarta.xml.bind.annotation.W3CDomHandler

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlAttachmentRef
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[FIELD, METHOD, PARAMETER])
intf java.lang.annotation.Annotation

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlAttribute
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[FIELD, METHOD])
intf java.lang.annotation.Annotation
meth public abstract !hasdefault boolean required() value= false
meth public abstract !hasdefault java.lang.String name() value= "##default"
meth public abstract !hasdefault java.lang.String namespace() value= "##default"

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlElement
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[FIELD, METHOD, PARAMETER])
innr public final static DEFAULT
intf java.lang.annotation.Annotation
meth public abstract !hasdefault boolean nillable() value= false
meth public abstract !hasdefault boolean required() value= false
meth public abstract !hasdefault java.lang.Class type() value= class jakarta.xml.bind.annotation.XmlElement$DEFAULT
meth public abstract !hasdefault java.lang.String defaultValue() value= " "
meth public abstract !hasdefault java.lang.String name() value= "##default"
meth public abstract !hasdefault java.lang.String namespace() value= "##default"

CLSS public final static jakarta.xml.bind.annotation.XmlElement$DEFAULT
 outer jakarta.xml.bind.annotation.XmlElement
cons public <init>()
supr java.lang.Object

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlElementDecl
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[METHOD])
innr public final static GLOBAL
intf java.lang.annotation.Annotation
meth public abstract !hasdefault java.lang.Class scope() value= class jakarta.xml.bind.annotation.XmlElementDecl$GLOBAL
meth public abstract !hasdefault java.lang.String defaultValue() value= " "
meth public abstract !hasdefault java.lang.String namespace() value= "##default"
meth public abstract !hasdefault java.lang.String substitutionHeadName() value= ""
meth public abstract !hasdefault java.lang.String substitutionHeadNamespace() value= "##default"
meth public abstract java.lang.String name()

CLSS public final static jakarta.xml.bind.annotation.XmlElementDecl$GLOBAL
 outer jakarta.xml.bind.annotation.XmlElementDecl
cons public <init>()
supr java.lang.Object

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlElementRef
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[FIELD, METHOD])
innr public final static DEFAULT
intf java.lang.annotation.Annotation
meth public abstract !hasdefault boolean required() value= true
meth public abstract !hasdefault java.lang.Class type() value= class jakarta.xml.bind.annotation.XmlElementRef$DEFAULT
meth public abstract !hasdefault java.lang.String name() value= "##default"
meth public abstract !hasdefault java.lang.String namespace() value= ""

CLSS public final static jakarta.xml.bind.annotation.XmlElementRef$DEFAULT
 outer jakarta.xml.bind.annotation.XmlElementRef
cons public <init>()
supr java.lang.Object

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlElementRefs
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[FIELD, METHOD])
intf java.lang.annotation.Annotation
meth public abstract jakarta.xml.bind.annotation.XmlElementRef[] value()

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlElementWrapper
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[FIELD, METHOD])
intf java.lang.annotation.Annotation
meth public abstract !hasdefault boolean nillable() value= false
meth public abstract !hasdefault boolean required() value= false
meth public abstract !hasdefault java.lang.String name() value= "##default"
meth public abstract !hasdefault java.lang.String namespace() value= "##default"

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlElements
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[FIELD, METHOD])
intf java.lang.annotation.Annotation
meth public abstract jakarta.xml.bind.annotation.XmlElement[] value()

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlEnum
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[TYPE])
intf java.lang.annotation.Annotation
meth public abstract !hasdefault java.lang.Class<?> value() value= class java.lang.String

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlEnumValue
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[FIELD])
intf java.lang.annotation.Annotation
meth public abstract java.lang.String value()

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlID
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[FIELD, METHOD])
intf java.lang.annotation.Annotation

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlIDREF
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[FIELD, METHOD])
intf java.lang.annotation.Annotation

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlInlineBinaryData
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[FIELD, METHOD, TYPE])
intf java.lang.annotation.Annotation

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlList
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[FIELD, METHOD, PARAMETER])
intf java.lang.annotation.Annotation

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlMimeType
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[FIELD, METHOD, PARAMETER])
intf java.lang.annotation.Annotation
meth public abstract java.lang.String value()

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlMixed
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[FIELD, METHOD])
intf java.lang.annotation.Annotation

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlNs
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[])
intf java.lang.annotation.Annotation
meth public abstract java.lang.String namespaceURI()
meth public abstract java.lang.String prefix()

CLSS public final !enum jakarta.xml.bind.annotation.XmlNsForm
fld public final static jakarta.xml.bind.annotation.XmlNsForm QUALIFIED
fld public final static jakarta.xml.bind.annotation.XmlNsForm UNQUALIFIED
fld public final static jakarta.xml.bind.annotation.XmlNsForm UNSET
meth public static jakarta.xml.bind.annotation.XmlNsForm valueOf(java.lang.String)
meth public static jakarta.xml.bind.annotation.XmlNsForm[] values()
supr java.lang.Enum<jakarta.xml.bind.annotation.XmlNsForm>

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlRegistry
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[TYPE])
intf java.lang.annotation.Annotation

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlRootElement
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[TYPE])
intf java.lang.annotation.Annotation
meth public abstract !hasdefault java.lang.String name() value= "##default"
meth public abstract !hasdefault java.lang.String namespace() value= "##default"

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlSchema
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[PACKAGE])
fld public final static java.lang.String NO_LOCATION = "##generate"
intf java.lang.annotation.Annotation
meth public abstract !hasdefault jakarta.xml.bind.annotation.XmlNsForm attributeFormDefault() value= UNSET
meth public abstract !hasdefault jakarta.xml.bind.annotation.XmlNsForm elementFormDefault() value= UNSET
meth public abstract !hasdefault jakarta.xml.bind.annotation.XmlNs[] xmlns() value= []
meth public abstract !hasdefault java.lang.String location() value= "##generate"
meth public abstract !hasdefault java.lang.String namespace() value= ""

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlSchemaType
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[FIELD, METHOD, PACKAGE])
innr public final static DEFAULT
intf java.lang.annotation.Annotation
meth public abstract !hasdefault java.lang.Class type() value= class jakarta.xml.bind.annotation.XmlSchemaType$DEFAULT
meth public abstract !hasdefault java.lang.String namespace() value= "http://www.w3.org/2001/XMLSchema"
meth public abstract java.lang.String name()

CLSS public final static jakarta.xml.bind.annotation.XmlSchemaType$DEFAULT
 outer jakarta.xml.bind.annotation.XmlSchemaType
cons public <init>()
supr java.lang.Object

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlSchemaTypes
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[PACKAGE])
intf java.lang.annotation.Annotation
meth public abstract jakarta.xml.bind.annotation.XmlSchemaType[] value()

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlSeeAlso
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[TYPE])
intf java.lang.annotation.Annotation
meth public abstract java.lang.Class[] value()

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlTransient
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[FIELD, METHOD, TYPE])
intf java.lang.annotation.Annotation

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlType
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[TYPE])
innr public final static DEFAULT
intf java.lang.annotation.Annotation
meth public abstract !hasdefault java.lang.Class factoryClass() value= class jakarta.xml.bind.annotation.XmlType$DEFAULT
meth public abstract !hasdefault java.lang.String factoryMethod() value= ""
meth public abstract !hasdefault java.lang.String name() value= "##default"
meth public abstract !hasdefault java.lang.String namespace() value= "##default"
meth public abstract !hasdefault java.lang.String[] propOrder() value= [""]

CLSS public final static jakarta.xml.bind.annotation.XmlType$DEFAULT
 outer jakarta.xml.bind.annotation.XmlType
cons public <init>()
supr java.lang.Object

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlValue
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[FIELD, METHOD])
intf java.lang.annotation.Annotation

CLSS public jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter
cons public <init>()
meth protected static boolean isWhiteSpace(char)
meth public java.lang.String marshal(java.lang.String)
meth public java.lang.String unmarshal(java.lang.String)
supr jakarta.xml.bind.annotation.adapters.XmlAdapter<java.lang.String,java.lang.String>

CLSS public final jakarta.xml.bind.annotation.adapters.HexBinaryAdapter
cons public <init>()
meth public byte[] unmarshal(java.lang.String)
meth public java.lang.String marshal(byte[])
supr jakarta.xml.bind.annotation.adapters.XmlAdapter<java.lang.String,byte[]>

CLSS public final jakarta.xml.bind.annotation.adapters.NormalizedStringAdapter
cons public <init>()
meth protected static boolean isWhiteSpaceExceptSpace(char)
meth public java.lang.String marshal(java.lang.String)
meth public java.lang.String unmarshal(java.lang.String)
supr jakarta.xml.bind.annotation.adapters.XmlAdapter<java.lang.String,java.lang.String>

CLSS public abstract jakarta.xml.bind.annotation.adapters.XmlAdapter<%0 extends java.lang.Object, %1 extends java.lang.Object>
cons protected <init>()
meth public abstract {jakarta.xml.bind.annotation.adapters.XmlAdapter%0} marshal({jakarta.xml.bind.annotation.adapters.XmlAdapter%1}) throws java.lang.Exception
meth public abstract {jakarta.xml.bind.annotation.adapters.XmlAdapter%1} unmarshal({jakarta.xml.bind.annotation.adapters.XmlAdapter%0}) throws java.lang.Exception
supr java.lang.Object

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[PACKAGE, FIELD, METHOD, TYPE, PARAMETER])
innr public final static DEFAULT
intf java.lang.annotation.Annotation
meth public abstract !hasdefault java.lang.Class type() value= class jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter$DEFAULT
meth public abstract java.lang.Class<? extends jakarta.xml.bind.annotation.adapters.XmlAdapter> value()

CLSS public final static jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter$DEFAULT
 outer jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter
cons public <init>()
supr java.lang.Object

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapters
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[PACKAGE])
intf java.lang.annotation.Annotation
meth public abstract jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter[] value()

CLSS public abstract jakarta.xml.bind.attachment.AttachmentMarshaller
cons public <init>()
meth public abstract java.lang.String addMtomAttachment(byte[],int,int,java.lang.String,java.lang.String,java.lang.String)
meth public abstract java.lang.String addMtomAttachment(jakarta.activation.DataHandler,java.lang.String,java.lang.String)
meth public abstract java.lang.String addSwaRefAttachment(jakarta.activation.DataHandler)
meth public boolean isXOPPackage()
supr java.lang.Object

CLSS public abstract jakarta.xml.bind.attachment.AttachmentUnmarshaller
cons public <init>()
meth public abstract byte[] getAttachmentAsByteArray(java.lang.String)
meth public abstract jakarta.activation.DataHandler getAttachmentAsDataHandler(java.lang.String)
meth public boolean isXOPPackage()
supr java.lang.Object

CLSS public abstract jakarta.xml.bind.helpers.AbstractMarshallerImpl
cons public <init>()
intf jakarta.xml.bind.Marshaller
meth protected boolean isFormattedOutput()
meth protected boolean isFragment()
meth protected java.lang.String getEncoding()
meth protected java.lang.String getJavaEncoding(java.lang.String) throws java.io.UnsupportedEncodingException
meth protected java.lang.String getNoNSSchemaLocation()
meth protected java.lang.String getSchemaLocation()
meth protected void setEncoding(java.lang.String)
meth protected void setFormattedOutput(boolean)
meth protected void setFragment(boolean)
meth protected void setNoNSSchemaLocation(java.lang.String)
meth protected void setSchemaLocation(java.lang.String)
meth public <%0 extends jakarta.xml.bind.annotation.adapters.XmlAdapter> void setAdapter(java.lang.Class<{%%0}>,{%%0})
meth public <%0 extends jakarta.xml.bind.annotation.adapters.XmlAdapter> {%%0} getAdapter(java.lang.Class<{%%0}>)
meth public final void marshal(java.lang.Object,java.io.OutputStream) throws jakarta.xml.bind.JAXBException
meth public final void marshal(java.lang.Object,java.io.Writer) throws jakarta.xml.bind.JAXBException
meth public final void marshal(java.lang.Object,org.w3c.dom.Node) throws jakarta.xml.bind.JAXBException
meth public final void marshal(java.lang.Object,org.xml.sax.ContentHandler) throws jakarta.xml.bind.JAXBException
meth public jakarta.xml.bind.Marshaller$Listener getListener()
meth public jakarta.xml.bind.ValidationEventHandler getEventHandler() throws jakarta.xml.bind.JAXBException
meth public jakarta.xml.bind.attachment.AttachmentMarshaller getAttachmentMarshaller()
meth public java.lang.Object getProperty(java.lang.String) throws jakarta.xml.bind.PropertyException
meth public javax.xml.validation.Schema getSchema()
meth public org.w3c.dom.Node getNode(java.lang.Object) throws jakarta.xml.bind.JAXBException
meth public void marshal(java.lang.Object,java.io.File) throws jakarta.xml.bind.JAXBException
meth public void marshal(java.lang.Object,javax.xml.stream.XMLEventWriter) throws jakarta.xml.bind.JAXBException
meth public void marshal(java.lang.Object,javax.xml.stream.XMLStreamWriter) throws jakarta.xml.bind.JAXBException
meth public void setAdapter(jakarta.xml.bind.annotation.adapters.XmlAdapter)
meth public void setAttachmentMarshaller(jakarta.xml.bind.attachment.AttachmentMarshaller)
meth public void setEventHandler(jakarta.xml.bind.ValidationEventHandler) throws jakarta.xml.bind.JAXBException
meth public void setListener(jakarta.xml.bind.Marshaller$Listener)
meth public void setProperty(java.lang.String,java.lang.Object) throws jakarta.xml.bind.PropertyException
meth public void setSchema(javax.xml.validation.Schema)
supr java.lang.Object
hfds aliases,encoding,eventHandler,formattedOutput,fragment,noNSSchemaLocation,schemaLocation

CLSS public abstract jakarta.xml.bind.helpers.AbstractUnmarshallerImpl
cons public <init>()
fld protected boolean validating
intf jakarta.xml.bind.Unmarshaller
meth protected abstract java.lang.Object unmarshal(org.xml.sax.XMLReader,org.xml.sax.InputSource) throws jakarta.xml.bind.JAXBException
meth protected jakarta.xml.bind.UnmarshalException createUnmarshalException(org.xml.sax.SAXException)
meth protected org.xml.sax.XMLReader getXMLReader() throws jakarta.xml.bind.JAXBException
meth public <%0 extends jakarta.xml.bind.annotation.adapters.XmlAdapter> void setAdapter(java.lang.Class<{%%0}>,{%%0})
meth public <%0 extends jakarta.xml.bind.annotation.adapters.XmlAdapter> {%%0} getAdapter(java.lang.Class<{%%0}>)
meth public <%0 extends java.lang.Object> jakarta.xml.bind.JAXBElement<{%%0}> unmarshal(javax.xml.stream.XMLEventReader,java.lang.Class<{%%0}>) throws jakarta.xml.bind.JAXBException
meth public <%0 extends java.lang.Object> jakarta.xml.bind.JAXBElement<{%%0}> unmarshal(javax.xml.stream.XMLStreamReader,java.lang.Class<{%%0}>) throws jakarta.xml.bind.JAXBException
meth public <%0 extends java.lang.Object> jakarta.xml.bind.JAXBElement<{%%0}> unmarshal(javax.xml.transform.Source,java.lang.Class<{%%0}>) throws jakarta.xml.bind.JAXBException
meth public <%0 extends java.lang.Object> jakarta.xml.bind.JAXBElement<{%%0}> unmarshal(org.w3c.dom.Node,java.lang.Class<{%%0}>) throws jakarta.xml.bind.JAXBException
meth public boolean isValidating() throws jakarta.xml.bind.JAXBException
meth public final java.lang.Object unmarshal(java.io.File) throws jakarta.xml.bind.JAXBException
meth public final java.lang.Object unmarshal(java.io.InputStream) throws jakarta.xml.bind.JAXBException
meth public final java.lang.Object unmarshal(java.io.Reader) throws jakarta.xml.bind.JAXBException
meth public final java.lang.Object unmarshal(java.net.URL) throws jakarta.xml.bind.JAXBException
meth public final java.lang.Object unmarshal(org.xml.sax.InputSource) throws jakarta.xml.bind.JAXBException
meth public jakarta.xml.bind.Unmarshaller$Listener getListener()
meth public jakarta.xml.bind.ValidationEventHandler getEventHandler() throws jakarta.xml.bind.JAXBException
meth public jakarta.xml.bind.attachment.AttachmentUnmarshaller getAttachmentUnmarshaller()
meth public java.lang.Object getProperty(java.lang.String) throws jakarta.xml.bind.PropertyException
meth public java.lang.Object unmarshal(javax.xml.stream.XMLEventReader) throws jakarta.xml.bind.JAXBException
meth public java.lang.Object unmarshal(javax.xml.stream.XMLStreamReader) throws jakarta.xml.bind.JAXBException
meth public java.lang.Object unmarshal(javax.xml.transform.Source) throws jakarta.xml.bind.JAXBException
meth public javax.xml.validation.Schema getSchema()
meth public void setAdapter(jakarta.xml.bind.annotation.adapters.XmlAdapter)
meth public void setAttachmentUnmarshaller(jakarta.xml.bind.attachment.AttachmentUnmarshaller)
meth public void setEventHandler(jakarta.xml.bind.ValidationEventHandler) throws jakarta.xml.bind.JAXBException
meth public void setListener(jakarta.xml.bind.Unmarshaller$Listener)
meth public void setProperty(java.lang.String,java.lang.Object) throws jakarta.xml.bind.PropertyException
meth public void setSchema(javax.xml.validation.Schema)
meth public void setValidating(boolean) throws jakarta.xml.bind.JAXBException
supr java.lang.Object
hfds eventHandler,reader

CLSS public jakarta.xml.bind.helpers.DefaultValidationEventHandler
cons public <init>()
intf jakarta.xml.bind.ValidationEventHandler
meth public boolean handleEvent(jakarta.xml.bind.ValidationEvent)
supr java.lang.Object

CLSS public jakarta.xml.bind.helpers.NotIdentifiableEventImpl
cons public <init>(int,java.lang.String,jakarta.xml.bind.ValidationEventLocator)
cons public <init>(int,java.lang.String,jakarta.xml.bind.ValidationEventLocator,java.lang.Throwable)
intf jakarta.xml.bind.NotIdentifiableEvent
supr jakarta.xml.bind.helpers.ValidationEventImpl

CLSS public jakarta.xml.bind.helpers.ParseConversionEventImpl
cons public <init>(int,java.lang.String,jakarta.xml.bind.ValidationEventLocator)
cons public <init>(int,java.lang.String,jakarta.xml.bind.ValidationEventLocator,java.lang.Throwable)
intf jakarta.xml.bind.ParseConversionEvent
supr jakarta.xml.bind.helpers.ValidationEventImpl

CLSS public jakarta.xml.bind.helpers.PrintConversionEventImpl
cons public <init>(int,java.lang.String,jakarta.xml.bind.ValidationEventLocator)
cons public <init>(int,java.lang.String,jakarta.xml.bind.ValidationEventLocator,java.lang.Throwable)
intf jakarta.xml.bind.PrintConversionEvent
supr jakarta.xml.bind.helpers.ValidationEventImpl

CLSS public jakarta.xml.bind.helpers.ValidationEventImpl
cons public <init>(int,java.lang.String,jakarta.xml.bind.ValidationEventLocator)
cons public <init>(int,java.lang.String,jakarta.xml.bind.ValidationEventLocator,java.lang.Throwable)
intf jakarta.xml.bind.ValidationEvent
meth public int getSeverity()
meth public jakarta.xml.bind.ValidationEventLocator getLocator()
meth public java.lang.String getMessage()
meth public java.lang.String toString()
meth public java.lang.Throwable getLinkedException()
meth public void setLinkedException(java.lang.Throwable)
meth public void setLocator(jakarta.xml.bind.ValidationEventLocator)
meth public void setMessage(java.lang.String)
meth public void setSeverity(int)
supr java.lang.Object
hfds linkedException,locator,message,severity

CLSS public jakarta.xml.bind.helpers.ValidationEventLocatorImpl
cons public <init>()
cons public <init>(java.lang.Object)
cons public <init>(org.w3c.dom.Node)
cons public <init>(org.xml.sax.Locator)
cons public <init>(org.xml.sax.SAXParseException)
intf jakarta.xml.bind.ValidationEventLocator
meth public int getColumnNumber()
meth public int getLineNumber()
meth public int getOffset()
meth public java.lang.Object getObject()
meth public java.lang.String toString()
meth public java.net.URL getURL()
meth public org.w3c.dom.Node getNode()
meth public void setColumnNumber(int)
meth public void setLineNumber(int)
meth public void setNode(org.w3c.dom.Node)
meth public void setObject(java.lang.Object)
meth public void setOffset(int)
meth public void setURL(java.net.URL)
supr java.lang.Object
hfds columnNumber,lineNumber,node,object,offset,url

CLSS public jakarta.xml.bind.util.JAXBResult
cons public <init>(jakarta.xml.bind.JAXBContext) throws jakarta.xml.bind.JAXBException
cons public <init>(jakarta.xml.bind.Unmarshaller) throws jakarta.xml.bind.JAXBException
meth public java.lang.Object getResult() throws jakarta.xml.bind.JAXBException
supr javax.xml.transform.sax.SAXResult
hfds unmarshallerHandler

CLSS public jakarta.xml.bind.util.JAXBSource
cons public <init>(jakarta.xml.bind.JAXBContext,java.lang.Object) throws jakarta.xml.bind.JAXBException
cons public <init>(jakarta.xml.bind.Marshaller,java.lang.Object) throws jakarta.xml.bind.JAXBException
supr javax.xml.transform.sax.SAXSource
hfds contentObject,marshaller,pseudoParser

CLSS public jakarta.xml.bind.util.ValidationEventCollector
cons public <init>()
intf jakarta.xml.bind.ValidationEventHandler
meth public boolean handleEvent(jakarta.xml.bind.ValidationEvent)
meth public boolean hasEvents()
meth public jakarta.xml.bind.ValidationEvent[] getEvents()
meth public void reset()
supr java.lang.Object
hfds events

CLSS public abstract interface java.io.Serializable

CLSS public abstract interface java.lang.Comparable<%0 extends java.lang.Object>
meth public abstract int compareTo({java.lang.Comparable%0})

CLSS public abstract java.lang.Enum<%0 extends java.lang.Enum<{java.lang.Enum%0}>>
cons protected <init>(java.lang.String,int)
intf java.io.Serializable
intf java.lang.Comparable<{java.lang.Enum%0}>
meth protected final java.lang.Object clone() throws java.lang.CloneNotSupportedException
meth protected final void finalize()
meth public final boolean equals(java.lang.Object)
meth public final int compareTo({java.lang.Enum%0})
meth public final int hashCode()
meth public final int ordinal()
meth public final java.lang.Class<{java.lang.Enum%0}> getDeclaringClass()
meth public final java.lang.String name()
meth public java.lang.String toString()
meth public static <%0 extends java.lang.Enum<{%%0}>> {%%0} valueOf(java.lang.Class<{%%0}>,java.lang.String)
supr java.lang.Object
hfds name,ordinal

CLSS public java.lang.Exception
cons protected <init>(java.lang.String,java.lang.Throwable,boolean,boolean)
cons public <init>()
cons public <init>(java.lang.String)
cons public <init>(java.lang.String,java.lang.Throwable)
cons public <init>(java.lang.Throwable)
supr java.lang.Throwable
hfds serialVersionUID

CLSS public java.lang.Object
cons public <init>()
meth protected java.lang.Object clone() throws java.lang.CloneNotSupportedException
meth protected void finalize() throws java.lang.Throwable
 anno 0 java.lang.Deprecated(boolean forRemoval=false, java.lang.String since="9")
meth public boolean equals(java.lang.Object)
meth public final java.lang.Class<?> getClass()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait() throws java.lang.InterruptedException
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public int hashCode()
meth public java.lang.String toString()

CLSS public java.lang.RuntimeException
cons protected <init>(java.lang.String,java.lang.Throwable,boolean,boolean)
cons public <init>()
cons public <init>(java.lang.String)
cons public <init>(java.lang.String,java.lang.Throwable)
cons public <init>(java.lang.Throwable)
supr java.lang.Exception
hfds serialVersionUID

CLSS public java.lang.Throwable
cons protected <init>(java.lang.String,java.lang.Throwable,boolean,boolean)
cons public <init>()
cons public <init>(java.lang.String)
cons public <init>(java.lang.String,java.lang.Throwable)
cons public <init>(java.lang.Throwable)
intf java.io.Serializable
meth public final java.lang.Throwable[] getSuppressed()
meth public final void addSuppressed(java.lang.Throwable)
meth public java.lang.StackTraceElement[] getStackTrace()
meth public java.lang.String getLocalizedMessage()
meth public java.lang.String getMessage()
meth public java.lang.String toString()
meth public java.lang.Throwable fillInStackTrace()
meth public java.lang.Throwable getCause()
meth public java.lang.Throwable initCause(java.lang.Throwable)
meth public void printStackTrace()
meth public void printStackTrace(java.io.PrintStream)
meth public void printStackTrace(java.io.PrintWriter)
meth public void setStackTrace(java.lang.StackTraceElement[])
supr java.lang.Object
hfds CAUSE_CAPTION,EMPTY_THROWABLE_ARRAY,NULL_CAUSE_MESSAGE,SELF_SUPPRESSION_MESSAGE,SUPPRESSED_CAPTION,SUPPRESSED_SENTINEL,UNASSIGNED_STACK,backtrace,cause,depth,detailMessage,serialVersionUID,stackTrace,suppressedExceptions
hcls PrintStreamOrWriter,SentinelHolder,WrappedPrintStream,WrappedPrintWriter

CLSS public abstract interface java.lang.annotation.Annotation
meth public abstract boolean equals(java.lang.Object)
meth public abstract int hashCode()
meth public abstract java.lang.Class<? extends java.lang.annotation.Annotation> annotationType()
meth public abstract java.lang.String toString()

CLSS public abstract interface !annotation java.lang.annotation.Documented
 anno 0 java.lang.annotation.Documented()
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[ANNOTATION_TYPE])
intf java.lang.annotation.Annotation

CLSS public abstract interface !annotation java.lang.annotation.Inherited
 anno 0 java.lang.annotation.Documented()
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[ANNOTATION_TYPE])
intf java.lang.annotation.Annotation

CLSS public abstract interface !annotation java.lang.annotation.Retention
 anno 0 java.lang.annotation.Documented()
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[ANNOTATION_TYPE])
intf java.lang.annotation.Annotation
meth public abstract java.lang.annotation.RetentionPolicy value()

CLSS public abstract interface !annotation java.lang.annotation.Target
 anno 0 java.lang.annotation.Documented()
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[ANNOTATION_TYPE])
intf java.lang.annotation.Annotation
meth public abstract java.lang.annotation.ElementType[] value()

CLSS public abstract java.security.BasicPermission
cons public <init>(java.lang.String)
cons public <init>(java.lang.String,java.lang.String)
intf java.io.Serializable
meth public boolean equals(java.lang.Object)
meth public boolean implies(java.security.Permission)
meth public int hashCode()
meth public java.lang.String getActions()
meth public java.security.PermissionCollection newPermissionCollection()
supr java.security.Permission
hfds exitVM,path,serialVersionUID,wildcard

CLSS public abstract interface java.security.Guard
meth public abstract void checkGuard(java.lang.Object)

CLSS public abstract java.security.Permission
cons public <init>(java.lang.String)
intf java.io.Serializable
intf java.security.Guard
meth public abstract boolean equals(java.lang.Object)
meth public abstract boolean implies(java.security.Permission)
meth public abstract int hashCode()
meth public abstract java.lang.String getActions()
meth public final java.lang.String getName()
meth public java.lang.String toString()
meth public java.security.PermissionCollection newPermissionCollection()
meth public void checkGuard(java.lang.Object)
supr java.lang.Object
hfds name,serialVersionUID

CLSS public abstract interface javax.xml.transform.Result
fld public final static java.lang.String PI_DISABLE_OUTPUT_ESCAPING = "javax.xml.transform.disable-output-escaping"
fld public final static java.lang.String PI_ENABLE_OUTPUT_ESCAPING = "javax.xml.transform.enable-output-escaping"
meth public abstract java.lang.String getSystemId()
meth public abstract void setSystemId(java.lang.String)

CLSS public abstract interface javax.xml.transform.Source
meth public abstract java.lang.String getSystemId()
meth public abstract void setSystemId(java.lang.String)
meth public boolean isEmpty()

CLSS public javax.xml.transform.sax.SAXResult
cons public <init>()
cons public <init>(org.xml.sax.ContentHandler)
fld public final static java.lang.String FEATURE = "http://javax.xml.transform.sax.SAXResult/feature"
intf javax.xml.transform.Result
meth public java.lang.String getSystemId()
meth public org.xml.sax.ContentHandler getHandler()
meth public org.xml.sax.ext.LexicalHandler getLexicalHandler()
meth public void setHandler(org.xml.sax.ContentHandler)
meth public void setLexicalHandler(org.xml.sax.ext.LexicalHandler)
meth public void setSystemId(java.lang.String)
supr java.lang.Object
hfds handler,lexhandler,systemId

CLSS public javax.xml.transform.sax.SAXSource
cons public <init>()
cons public <init>(org.xml.sax.InputSource)
cons public <init>(org.xml.sax.XMLReader,org.xml.sax.InputSource)
fld public final static java.lang.String FEATURE = "http://javax.xml.transform.sax.SAXSource/feature"
intf javax.xml.transform.Source
meth public boolean isEmpty()
meth public java.lang.String getSystemId()
meth public org.xml.sax.InputSource getInputSource()
meth public org.xml.sax.XMLReader getXMLReader()
meth public static org.xml.sax.InputSource sourceToInputSource(javax.xml.transform.Source)
meth public void setInputSource(org.xml.sax.InputSource)
meth public void setSystemId(java.lang.String)
meth public void setXMLReader(org.xml.sax.XMLReader)
supr java.lang.Object
hfds inputSource,reader

CLSS public abstract interface org.xml.sax.ContentHandler
meth public abstract void characters(char[],int,int) throws org.xml.sax.SAXException
meth public abstract void endDocument() throws org.xml.sax.SAXException
meth public abstract void endElement(java.lang.String,java.lang.String,java.lang.String) throws org.xml.sax.SAXException
meth public abstract void endPrefixMapping(java.lang.String) throws org.xml.sax.SAXException
meth public abstract void ignorableWhitespace(char[],int,int) throws org.xml.sax.SAXException
meth public abstract void processingInstruction(java.lang.String,java.lang.String) throws org.xml.sax.SAXException
meth public abstract void setDocumentLocator(org.xml.sax.Locator)
meth public abstract void skippedEntity(java.lang.String) throws org.xml.sax.SAXException
meth public abstract void startDocument() throws org.xml.sax.SAXException
meth public abstract void startElement(java.lang.String,java.lang.String,java.lang.String,org.xml.sax.Attributes) throws org.xml.sax.SAXException
meth public abstract void startPrefixMapping(java.lang.String,java.lang.String) throws org.xml.sax.SAXException

