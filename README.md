The Jakarta XML Binding TCK is built using Java SE 21/25
and has been tested against [JAXB RI](https://github.com/eclipse-ee4j/jaxb-ri)
and [Eclipse GlassFish](https://github.com/eclipse-ee4j/glassfish/).

### Required Software

* linux or macos
* make 3.82+/GNU make
* perl
* Java SE 11+
* apache ant 1.10.x
* apache maven 3.9.x

### Quick Build

* edit [build_jaxbtck.sh](docker/build_jaxbtck.sh):
  - add `export WORKSPACE=<reporoot>`
  - add `export JDK11_HOME=${JAVA_HOME}`
  - edit `sed` calls to follow the build environment
* run [build_jaxbtck.sh](docker/build_jaxbtck.sh)

This will:
* download and configure the right versions of `JAXB RI`, `Jakarta Activation API` and `GlassFish` for use by the build
* update `Defs.mk` and `Defs.SFBay.mk`
* clean up possible relicts from previous builds
* call maven to build java sources and documentation
* call make to build tests
* call ant to zip everything together

### Quick Run

* edit [run_jaxbtck.sh](docker/run_jaxbtck.sh):
  - add `export WORKSPACE=<reporoot>`
  - add `export JDK11_HOME=${JAVA_HOME}`
  - edit `sed` calls to follow the build environment
* run [run_jaxbtck.sh](docker/run_jaxbtck.sh) (assuming build run first)

### Resources

* [Jakarta XML Binding Specification](https://jakarta.ee/specifications/xml-binding/)
* [XML Binding API project](https://github.com/jakartaee/jaxb-api)
* [JT Harness documentation](https://wiki.openjdk.org/spaces/CodeTools/pages/18448454/JT+Harness)
* [JT Harness project](https://github.com/openjdk/jtharness)
