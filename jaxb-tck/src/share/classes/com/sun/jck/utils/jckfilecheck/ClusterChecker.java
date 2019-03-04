/*
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
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

//
// Program: ClusterChecker.java
// Author : Rampalli Narasimhan
// Purpose: To do all the cluster checks
//

package com.sun.jck.utils.jckfilecheck;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

public class ClusterChecker implements Checker {

    //
    //

    private OrderedList fileList = new OrderedList (60000);
    private File outFileName;
    private Vector clusSizeVector = new Vector();
    private int[] clusterSizes;
    private int[] clusterCounts;
    private int[] clusterMegabytes;

    public ClusterChecker(File outDir, int[] clusterSizes) {
	outFileName = new File(outDir, "cluschek.html");
	this.clusterSizes = clusterSizes;
    }

    public File getOutputFile() {
	return outFileName;
    }

    public String getName() {
	return "Cluster Checker";
    }

    public void run(OrderedList fileList) throws IOException {
	this.fileList = fileList;
	calculateClusters();
	HTMLReportGenerator rep = new HTMLReportGenerator(outFileName);
	rep.generateClusterCheckerReport(clusterSizes, clusterCounts, clusterMegabytes);
    }

    private void calculateClusters() {
	Enumeration fileListIter = fileList.getIterator();
	DirectoryEntry entry;
	long fileSize;
	clusterCounts = new int[clusterSizes.length];
	clusterMegabytes  = new int[clusterSizes.length];
	for (int i = 0; i < clusterSizes.length; ++i) {
	    // clusterSize    : size of a cluster
	    // noOfClusters   : number of clusters
	    // totalSpace     : Amount of total Space in MB
	    int clusterSize = clusterSizes[i];
	    int count = 0;
	    int megabytes = 0;
	    fileListIter = fileList.getIterator();
	    while (fileListIter.hasMoreElements()) {
		entry = (DirectoryEntry) fileListIter.nextElement();
		fileSize = entry.getSize();
		count += fileSize/clusterSize;
		if ((fileSize % clusterSize) > 0)
		    ++ count;
	    }
	    megabytes = (int)(((long)count * clusterSize) / (1024*1024));
	    clusterCounts[i] = count;
	    clusterMegabytes[i] = megabytes;
	}
    }
}
    

