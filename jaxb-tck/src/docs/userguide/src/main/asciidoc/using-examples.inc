///////////////////////////////////////////////////////////////////////
NOTE TO WRITERS:
These CLI examples can be customized as necessary.
///////////////////////////////////////////////////////////////////////

1.  Change to `$TS_HOME/lib` directory.
2.  Start JavaTest using the following command:
+
--
[source,subs="attributes"]
----
${JAVA_HOME}/bin/java -jar ${TS_HOME}/lib/javatest.jar \
  -batch -testsuite ${TS_HOME} -open {jteFileName} \
  -workdir -create ${TS_HOME}/work -runtests
----
--

[[GCMCU]]

Example 5-1 {TechnologyShortName} TCK Signature Tests

To run the {TechnologyShortName} TCK signature tests, enter the
following commands:

[source,subs="attributes"]
----
cd {sigTestDirectoryExample}
ant runclient
----

[[GCMBV]]


Example 5-2 Single Test Directory

To run a single test directory, enter the following commands:

[source,subs="attributes"]
----
cd {singleTestDirectoryExample}
ant runclient
----

[[GCMCA]]


Example 5-3 Subset of Test Directories

To run a subset of test directories, enter the following commands:

[source,subs="attributes"]
----
cd {subsetTestDirectoryExample}
ant runclient
----
