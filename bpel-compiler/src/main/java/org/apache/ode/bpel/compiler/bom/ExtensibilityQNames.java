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
package org.apache.ode.bpel.compiler.bom;

import javax.xml.namespace.QName;


public abstract class ExtensibilityQNames {
    /*
     * Activity Recovery extensibility elements.
     */
    public static final String NS_ACTIVITY_RECOVERY         = "http://ode.apache.org/activityRecovery";
    public static final QName FAILURE_HANDLING              = new QName(NS_ACTIVITY_RECOVERY, "failureHandling");
    public static final QName FAILURE_HANDLING_RETRY_FOR    = new QName(NS_ACTIVITY_RECOVERY, "retryFor");
    public static final QName FAILURE_HANDLING_RETRY_DELAY  = new QName(NS_ACTIVITY_RECOVERY, "retryDelay");
    public static final QName FAILURE_HANDLING_FAULT_ON     = new QName(NS_ACTIVITY_RECOVERY, "faultOnFailure");



    //
    // External variables 
    //
    
    /** Namespace for external variables. */
    private static final String EXTVAR_NS = "http://ode.apache.org/externalVariables";
    
    /** Attribute name for external variable id. */
    public static final QName EXTVAR_ATTR = new QName(EXTVAR_NS, "id");
    
    /** Attribute holding the name of the "related" variable. */
	public static final QName EXTVAR_RELATED = new QName(EXTVAR_NS, "relates-to");

    public static final String NS_BPEL_EXTENSIBILITY = "http://ode.apache.org/bpelExtensibility";
    	
    public static final QName UNKNOWN_EA_FAULT_NAME = new QName(NS_BPEL_EXTENSIBILITY, "unknownExtensionImplementation");
    	
    public static final QName INVALID_EXTENSION_ELEMENT = new QName(NS_BPEL_EXTENSIBILITY, "invalidExtensionElement");
    
    
    /*
     * @hahnml: TraDE extensibility elements.
     */
    public static final String NS_TRADE_ASSOCIATION = "http://iaas.uni-stuttgart.de/trade/annotations";
    public static final QName ASSOCIATION = new QName(
            NS_TRADE_ASSOCIATION, "tradeAssociation");
    public static final QName DATA_MODEL_REFERENCE = new QName(
            NS_TRADE_ASSOCIATION, "dataModelRef");
    public static final QName DATA_OBJECT_REFEREBCE = new QName(
            NS_TRADE_ASSOCIATION, "dataObjectRef");
    public static final QName TRIGGER = new QName(
            NS_TRADE_ASSOCIATION, "trigger");
    public static final String ATTR_DATA_MODEL_NAMESPACE = "dataModelNamespace";
    public static final String ATTR_DATA_MODEL_NAME = "dataModelName";
    public static final String ATTR_DATA_OBJ_NAME = "dataObjectName";
    public static final String ATTR_DATA_ELEMENT_NAME = "dataElementName";
    public static final String ATTR_CORRELATION_SET = "correlationSet";
    public static final String ATTR_VARIABLE = "variable";
    public static final String ATTR_TRIGGER_SYNC_METHOD = "syncMethod";
}

