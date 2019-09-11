
Here are few tips to verify the errors:

1. Check your `.jti` file settings in the Configuration Editor. You may
have launched the JavaTest harness with prior status set to `any`
instead of `ignore`.
2. Check if the `env.html` report file is generated, else, the JavaTest
GUI provides the ability to view evaluated variables used by the tests.
Select *Configure* and then *Show Test Environment* from the Test
manager menu to view the contents of the test environment.
3. Verify if the Configuration Editor has generated a configuration
question log when you complete a configuration interview. You can use
the configuration question log to review your answers to all the
questions in the current configuration. Select *Configure* and *Show
Question Log* from the *Test Manager* menu to view the current
configuration interview.

[[GBFYA]][[test-manager-properties]]

6.7 Test Manager Properties
~~~~~~~~~~~~~~~~~~~~~~~~~~~

You can view the properties of a test manager by selecting *View* and
then *Properties* from the *Test Manager* menu. The Test Manager
Properties dialog box contains Test Suite, Work Directory,
Configuration, and Plugin information.

See JavaTest User's Guide , Graphical User Interface, or JavaTest
online help for a detailed description of the Test Manager Properties
dialog box.

[[GBFYB]][[test-suite-errors]]

6.8 Test Suite Errors
~~~~~~~~~~~~~~~~~~~~~

If the JavaTest harness detects test suite errors, then it displays an
advisory dialog box. You can view detailed information about the test
suite errors by selecting View and then *Test Suite Errors* from the
Test Manager menu.

See JavaTest User's Guide , Graphical User Interface, or JavaTest
online help for a detailed description of the *Test Manager: Test Suite
Errors* dialog box.


[[GBFYD]][[how-tests-are-executed]]

6.9 How Tests are Executed
~~~~~~~~~~~~~~~~~~~~~~~~~~

To better understand and debug the test failures, this section
describes how the tests are selected for a test run and how they are
then executed.

Test Results
^^^^^^^^^^^^
Test Execution results are reported as one of the three states:

* Pass: A test passes when the functionality being tested behaves as
expected. All tests are expected to pass.
* Fail: A test fails when the functionality being tested does not
behave as expected.
* Error: A test is considered to cause error when something (usually a
configuration problem) keeps the test from being executed as expected.
Errors often indicate a systemic problem - a single configuration
problem can cause many tests to fail.  For example, if the path to the
Java runtime is configured incorrectly, then no tests can run and will
result in an error.

How Tests Are Selected For a Test Run
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Prior to the start of a test run, the JavaTest harness selects tests
for the run based on the following factors:

* Tests to be Run: The JavaTest harness finds tests listed in the Tests
to be Run field of the JavaTest configuration. You can specify the
sub-branches of the tree as a way of limiting the tests that are
executed during a test run. The JavaTest harness browses through the
tree starting with its sub-branches or tests you specify, and executes
all tests that it finds.
* Exclude List: Tests listed in the appropriate exclude list are
deselected prior to the start of a test run. For details about exclude
lists and their role in the certification process, see Procedure for
Certification.
* Keywords: A test can be selected based on keywords specified in the
Keywords field in the test description. The possible keywords are the
following: schema, document, runtime, positive, negative, bindinfo,
jaxb_not_required, java_to_schema.
* Prior Status: Use the combo box and check boxes to select tests in a
test run based on their outcome on a prior test run. Prior status is
evaluated on a test-bytest basis using information stored in result
files (.jtr) written in the work directory.

Test Execution
^^^^^^^^^^^^^^

Based on the test keywords and the `executeClass` parameter of the test
description, all the tests are divided in four execution models:

* Schema Tests: The keyword schema indicates this test execution model. The
schema tests are the Schema Compiler Tests. These tests have a schema file
(*.xsd) in the test description parameter source. The schema is compiled to the
work directory, and the output directory option of the XML Binding schema compiler is
used. +
The package specified in the test description parameter package is used
to set the output package parameter to the schema compiler. If the test
has the keyword negative, then the schema compiler must report an error
compiling the test schema. Otherwise, the schema is positive and may
have XML Binding custom binding information if the keyword bindinfo is
present. + The positive schema is expected to compile successfully.
After the compilation passes, the signature test (the class specified
in the test description parameter executeClass) runs with arguments
specified in the test description parameter executeArgs.
* Document Tests: The keyword document along with the class JAXBTest
specified in the test description parameter executeClass indicate this
test execution model.  The document tests are the subset of the Content
Tree Tests, with the exception of the content tree modification stage. +
The last two stages are applicable only to valid XML documents. The
first stage (schema compilation) is performed as for the schema tests.
All stages except the first one are performed by executing appropriate
test cases (unmarshal, compareContent, and marshal respectively) of the
class specified in test description parameter executeClass, with
arguments specified in the test description parameter executeArgs. +
All parameter entries starting with '$' are resolved in the
environment, but no errors occur if any of these values are not
defined. If the exclude list identifies test cases to be excluded, then
these cases are added to the test arguments using the -exclude option.
Note that all schemas used in the document tests are valid and the
tests may not have the keyword negative. Invalid documents are
indicated by option -invalid in the parameter executeClass.
* XML Binding Framework API Tests: The XML Binding framework API tests have their own
classes (other than the signature test or JAXBTest) specified in the
test description parameter executeClass. The tests may or may not have
schema file specified in parameter schema. +
First, if the schema is specified, it is compiled as described for the
schema tests.  Then the test executes the class specified in test
description parameter executeClass with arguments specified in the test
description parameter executeArgs. +
All parameter entries starting with '$' are resolved in the
environment, but no errors occur if any of these values are not
defined. If the exclude list identifies test cases to be excluded, then
these cases are added to the test arguments using the -exclude option.
* Java -to-Schema Tests: The keyword /java_to_schema/ indicates this
test execution model. The Java-to-Schema Tests are the same as
described in Schema Compiler Tests. These tests have a Java files
(*.java) in the test description parameter source. The generated schema
is placed in the work directory, and the output directory option of the
XML Binding schema generator is used. If the test has the attribute negative,
then the schema generator must report an error processing the test Java
files. Otherwise, the Java code is correctly annotated and should be
processed successfully. After the generation passes, the validation
test (the class specified in the test description parameter
/executeClass/) runs with arguments specified in the test description
parameter /executeArgs/. The validation test checks that a valid XML
document, taken from the test description parameter /executeArgs/
follows the just-generated schema, and fails to check invalid XML
documents.
* XML Validation Tests: XML Validation can be implemented through JAXP
or some other mechanism. If the validation is implemented through JAXP,
then validation checking tests can be skipped by deselecting them in
the test interview.  If you use some other tools for schema validation,
then XML validation tests are mandatory.
