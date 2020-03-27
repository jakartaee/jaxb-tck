//
// Copyright (c) 2020 Oracle and/or its affiliates. All rights reserved.
//
// This program and the accompanying materials are made available under the
// terms of the Eclipse Public License v. 2.0, which is available at
// http://www.eclipse.org/legal/epl-2.0.
//
// This Source Code may also be made available under the following Secondary
// Licenses when the conditions for such availability set forth in the
// Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
// version 2 with the GNU Classpath Exception, which is available at
// https://www.gnu.org/software/classpath/license.html.
//
// SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
//

pipeline {

    agent {
        kubernetes {
            label 'jaxb-tck-build-pod'
            yaml """
apiVersion: v1
kind: Pod
spec:

  volumes:
  - name: workspace-volume
    emptyDir: {}
  - name: m2-repo
    emptyDir: {}
    
  containers:
  - name: jaxb-tck-build
    image:  jakartaee/jaxbtck-base:0.1
    resources:
      limits:
        memory: "1Gi"
        cpu: "1"
      requests:
        memory: "1Gi"
        cpu: "1"
    volumeMounts:
    - mountPath: "/home/jenkins"
      name: workspace-volume
      readOnly: false
    - name: m2-repo
      mountPath: /home/jenkins/.m2/repository
    tty: true
    command:
    - cat
"""
        }
    }

    stages {
        stage('Build') {
            steps {
                container('jaxb-tck-build') {
                    git branch: '${BRANCH}', url: '${REPOSITORY_URL}'
                    sh """
                        docker/build_jaxbtck.sh
                    """
                }
            }
        }
    }

}
