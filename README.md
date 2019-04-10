The JAXB-TCK is built using jdk 8 and has been tested against JAXB-RI and Eclipse Glassfish.

Files Defs.mk and Defs.SFBay.mk need to be modified to build the bundle.

The paths of linux utilities and bundle dependencies need to be specified as per the system on which the JAX-B TCK bundle is being built.

Software requirements to build the TCK: 

GNU make (version 3.82 was used to build the TCK)

javatest 5.0

sigtestdev 4.0

Ant 1.10.x or higher

jdk 8

JAXB-RI 2.3.2 or higher

Commands to build: make REPOSITORIES=$TCK_ROOT/xml_schema clean

make REPOSITORIES=$TCK_ROOT/xml_schema nightly

Entries to be modified to build:

Defs.mk

JAVATEST_JAR_LOC (This should point to the directory containing javatest 5.0 jar)

SIGTESTDEV_JAR_LOC (This should point to the directory containing the sigtestdev jar)

ASM_JAR_LOCATION (This should point to the directory containing ASM jars)

Paths for linux utilities

Defs.SFBay.mk

JAXB_HOME (Tested building with JAXB-RI 2.3.2)
ANT_HOME
SIGTEST_DIST
GENERAL_JAVAHOME
JAVAHOME_6

Paths for UNZIP ZIP PERL