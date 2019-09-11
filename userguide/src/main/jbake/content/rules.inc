
Rules for Products that include Support for Software Development
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

The following Rules, in addition to the Rules for {TechnologyShortName}
{TechnologyVersion} Products, apply for each version of an operating
system, software component, and hardware platform Documented as
supporting the Product:

*{techID}SD1* For each Product Configuration that causes the Schema
Compiler to produce Schema Compiler Output, the Product must be able to
satisfy all applicable compatibility requirements, including passing
all Conformance Tests, except only as specifically exempted by these
Rules.

*{techID}SD2* For each Product Configuration that causes the Schema
Compiler to produce Schema Compiler Output, in cases where such Schema
Compiler Output contains only value classes, that Schema Compiler
Output, when combined with the XML Binding Compatibile Implementation
Libraries, must execute properly when run on all Java SE Reference Runtime
versions that correspond to versions of Java SE Documented as supporting
the Product.

*{techID}SD3* The Schema Compiler must not produce Schema Compiler
Output from schema that do not conform to the W3C XML Schema
recommendation [XSD Part 1][XSD Part 2].

*{techID}SD3.1* The Schema Compiler in non-default Product
Configurations may produce Schema Compiler Output when an XML Schema
contains constructs for which a binding has not been defined by the
Specification.

*{techID}SD3.2* The Schema Compiler in non-default Product
Configurations may produce Schema Compiler Output when a custom binding
declaration is encountered.

*{techID}SD4* The Schema Compiler Output of the Schema Compiler must be
either in class format defined by the Java Virtual Machine (JVM)
Specifications or in source file format defined by Java Language
Specification (JLS).

*{techID}SD5* For each Product Configuration that causes the Schema
Generator to produce Schema Generator Output, the Product must be able
to satisfy all applicable compatibility requirements, including passing
all Conformance Tests, except only as specifically exempted by these
Rules.

*{techID}SD6* For each Product Configuration that causes the Schema
Generator to produce Schema Generator Output, the Output of Schema
Generator must fully meet W3C requirements for the XML schema
language.

*{techID}SD7* The Schema Generator must not produce Schema Generator
Output when a program element violates mapping constraints defined by
Specifications.

*{techID}SD8* The Schema Generator may produce Schema Generator Output
when a program element contains constructs for which a mapping has not
been defined by the Specification.

*{techID}SD9* A schema produced by the Schema Generator from source
code containing a comment or a directive, or from a class file
containing non-standard attributes, must be equivalent to a schema
produced by the Schema Generator from the same source code with the
comment or directive removed, or from the class file with the
attributes removed. Two schemas are equivalent if and only if they
validate the same set of XML documents (any XML document is either
valid against both schemas or invalid against both schemas).

*{techID}SD10* Except for tests specifically required by this TCK to be
modified (if any) the source for Schema Compiler and Schema Generator
Conformance Tests supplied as part of the Test Suite or as updated by
the Maintenance Lead must be used to certify compliance.
