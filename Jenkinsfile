/*
 * Copyright (c) 2019, 2023 Oracle and/or its affiliates. All rights reserved.
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

env.label = "jaxb-tck-ci-pod-${UUID.randomUUID().toString()}"
pipeline {
  options {
    buildDiscarder(logRotator(numToKeepStr: '5'))
  }
  agent {
    kubernetes {
      label "${env.label}"
      defaultContainer 'jnlp'
      yaml """
apiVersion: v1
kind: Pod
metadata:
spec:
  hostAliases:
  - ip: "127.0.0.1"
    hostnames:
    - "localhost.localdomain"
  containers:
  - name: jaxb-tck-ci
    image: jakartaee/jaxbtck-base:0.4
    command:
    - cat
    tty: true
    imagePullPolicy: Always
    resources:
      limits:
        memory: "8Gi"
        cpu: "2.0"
"""
    }
  }
  parameters {
    string(name: 'JAXB_RI_BUNDLE_URL',
           defaultValue: 'https://ci.eclipse.org/jaxb-impl/job/jaxb-ri-master-build/lastSuccessfulBuild/artifact/jaxb-ri/bundles/ri/target/jaxb-ri.zip',
           description: 'URL required for downloading JAXB implementation jar' )
    string(name: 'JAF_BUNDLE_URL',
           defaultValue: 'https://jakarta.oss.sonatype.org/content/repositories/staging/jakarta/activation/jakarta.activation-api/2.1.2/jakarta.activation-api-2.1.2.jar',
           description: 'URL required for downloading JAF implementation jar' )
    string(name: 'TCK_BUNDLE_BASE_URL',
           defaultValue: '',
           description: 'Base URL required for downloading prebuilt binary TCK Bundle from a hosted location' )
    string(name: 'TCK_BUNDLE_FILE_NAME', 
           defaultValue: 'jakarta-xml-binding-tck-4.0.1.zip',
	   description: 'Name of bundle file to be appended to the base url' )
    string(name: 'GF_BUNDLE_URL', 
           defaultValue: 'https://download.eclipse.org/ee4j/glassfish/glassfish-7.0.0.zip', 
           description: 'URL required for downloading GlassFish Full/Web profile bundle' )
    string(name: 'GF_VERSION_URL', 
           defaultValue: '', 
           description: 'URL required for downloading GlassFish version details' )
	choice(name: 'LICENSE', choices: 'EPL\nEFTL',
           description: 'License file to be used to build the TCK bundle(s) either EPL(default) or Eclipse Foundation TCK License' )
  choice(name: 'RUNTIME', choices: 'Glassfish\nStandalone',
           description: 'Run JAXB Tests with Standalone/Glassfish' )
  choice(name: 'JDK', choices: 'JDK11\nJDK17\nJDK21',
           description: 'Java SE Version to be used for running TCK either JDK11 or JDK17 or JDK21' )

  }
  environment {
    ANT_OPTS = "-Djavax.xml.accessExternalStylesheet=all -Djavax.xml.accessExternalSchema=all -Djavax.xml.accessExternalDTD=file,http"
    LANG="en_US.UTF-8"
    DEFAULT_GF_BUNDLE_URL="https://download.eclipse.org/ee4j/glassfish/glassfish-7.0.0.zip" 
  }
  stages {
    stage('jaxb-tck-build') {
      steps {
        container('jaxb-tck-ci') {
          sh """
            env
            bash -x ${WORKSPACE}/docker/build_jaxbtck.sh
          """
          archiveArtifacts artifacts: 'bundles/*.zip'
          stash includes: 'bundles/*.zip', name: 'jaxb-tck-bundles'
        }
      }
    }
	stage('jaxb-tck-run') {
      steps {
        container('jaxb-tck-ci') {
          sh """
            env
            bash -x ${WORKSPACE}/docker/run_jaxbtck.sh
          """
          archiveArtifacts artifacts: "jaxbtck-results.tar.gz"
          junit testResults: 'results/junitreports/*.xml', allowEmptyResults: true
        }
      }
    }
  }
}
