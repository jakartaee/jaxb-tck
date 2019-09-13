.  The zip file above contains the XML Binding TCK installer jar file;
use this installer to install the XML Binding TCK.
  a.  Extract the {TechnologyShortName} TCK {TechnologyVersion} product bundle
      in the directory of your choice: +
      +`java -jar PATH-TO-XML-BINDING-TCK-JAR options [-o outputdir]`+ +
      The following descriptions are provided for the installation command:
      * `java`: Java SE {SEversion} or later platform reference implementation.
      * `PATH-TO-XML-BINDING-TCK-JAR`: The path of the {TechnologyShortName}
	TCK {TechnologyVersion} JAR file to install.
      * `options`: Unless a user specifies options, the default
	behavior installs {TechnologyShortName} TCK {TechnologyVersion}
	in verbose mode (writes the file names it is installing to
	standard out) into the current directory. The installation
	utility accepts the following options:
        **  `-q`: Installs XML Binding TCK 2.3 in quiet mode.
	**  `-l`: Prints a list to standard output of the options that
	    will be installed.
	**  `-onError mode` : Sets the mode of sensitivity that the
	      installer has to errors.  The `–onError` : Instructs
	      what to do when it encounters an error while installing
	      {TechnologyShortName} TCK {TechnologyVersion}.  This
	      option has three valid modes:
	      *** `ignore`: Instructs the installer to ignore any
	          errors and continue installation.
	      *** `report`: Instructs the installer to continue with
	          the installation but report any errors when the
	          installation is completed.
	      *** `fail`: Instructs the installer to stop installation
	          once an error is encountered.
      **  `–usage`: Prints a usage message to standard out and exits (not listed
            as an option in the usage message).
      **  `–help`: Prints a help message to standard out and exits
	    (not listed as an option in the help message).
    * `[-o outputdir]`: Installs {TechnologyShortName} TCK {TechnologyVersion}
      into the directory specified by `outputdir`.
  b.  This action creates the `jaxb-tck23` directory. The topmost
      {TechnologyShortName} TCK installation directory is referred to
      as the Test Suite Home, or `<TS_HOME>`.
