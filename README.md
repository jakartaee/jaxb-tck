The JAXB-TCK is built using jdk 8 and has been tested against JAXB-RI.
Files Defs.mk and Defs.SFBay.mk need to be modified to build the bundle.The paths of linux utilities and bundle dependencies need to be specified as per the system on which the JAX-B TCK bundle is being built.

Software requirements:
GNU make (version 3.82 was used to build the TCK)
javatest 5.0
sigtestdev 4.0
Ant 1.10.x or higher
jdk 8/9

Commands to build:
make -m serial REPOSITORIES=$TCK_ROOT/xml_schema clean
make -d -m serial REPOSITORIES=$TCK_ROOT/xml_schema nightly

Entries to be modified to build:
Defs.mk

1) JAVATEST_JAR_LOC (This should point to the directory containing javatest 5.0 jar)
2) Paths for linux utilities