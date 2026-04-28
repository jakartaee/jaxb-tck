The Jakarta XML Binding TCK is built using Java SE 11 or newer and has been tested against JAXB RI and Eclipse Glassfish.

### Required Software

* linux or macos
* make 3.82+/GNU make
* perl
* Java SE 11+
* apache ant 1.10.x
* apache maven 3.9.x

### Quick Build

* Edit [build_jaxbtck.sh](docker/build_jaxbtck.sh):
  - add `export WORKSPACE=<reporoot>`
  - add `export JDK11_HOME=${JAVA_HOME}`
  - edit `sed` calls to follow the build environment
* run [build_jaxbtck.sh](docker/build_jaxbtck.sh)

This will:
* download and configure the right versions of `JAXB RI`, `Jakarta Activation API` and `Glassfish` for use by the build
* update `Defs.mk` and `Defs.SFBay.mk`
* clean up possible relicts from previous builds
* call maven to build java sources and documentation
* call make to build tests
* call ant to zip everything together

### Quick Run

* Edit [run_jaxbtck.sh](docker/run_jaxbtck.sh):
  - add `export WORKSPACE=<reporoot>`
  - add `export JDK11_HOME=${JAVA_HOME}`
  - edit `sed` calls to follow the build environment
* run [run_jaxbtck.sh](docker/run_jaxbtck.sh)
