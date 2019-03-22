/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.ode.bpel.o;

import java.io.Serializable;

import javax.xml.namespace.QName;

/**
 * Holds information about the TraDE associations of a process, variable or
 * activity.
 * 
 * @author hahnml
 */
public class OTraDEAssociation implements Serializable {

	private static final long serialVersionUID = 7311724990628982005L;

	// A reference to an associated TraDE data model
	public ODataModelRef dataModelRef;

	// A reference to an associated TraDE data object
	public ODataObjectRef dataObjectRef;

	// A trigger definition, pushing or pulling data from/to TraDE data objects
	public OTrigger trigger;
	
	public static class ODataModelRef implements Serializable {
		private static final long serialVersionUID = 1L;

		// The name of the reference data model
		public QName dataModelQName;
	}

	public static class ODataObjectRef implements Serializable {
		private static final long serialVersionUID = 1L;

		// The name of the reference data object
		public String dataObjectName;
		// The name of one of the data elements of the above referenced data
		// object
		public String dataElementName;

		// A correlationSet defined by the process model used to enable the
		// correlation of the TraDE data with a collection of process instances
		// forming a choreography instance
		public OScope.CorrelationSet correlationSet;
	}

	public static class OTrigger implements Serializable {
		private static final long serialVersionUID = 1L;

		// The synchronization method to apply: PUSH, PULL
		public String synchronizationMethod;
		
		// The variable to write to or read from
		public OScope.Variable variable;

		// A reference to a data object to trigger the data exchange on
		public ODataObjectRef dataObjectRef;
	}
}
