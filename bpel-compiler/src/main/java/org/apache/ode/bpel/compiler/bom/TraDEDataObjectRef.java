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

import org.w3c.dom.Element;

/**
 * BOM representation of the TraDE association DataObjectRef extensibility element.
 * 
 * @author hahnml
 */
public class TraDEDataObjectRef extends BpelObject {

	public TraDEDataObjectRef(Element el) {
		super(el);
	}

	public String getDataObjectName() {
		return getAttribute(ExtensibilityQNames.ATTR_DATA_OBJ_NAME, null);
	}

	public String getDataElementName() {
		return getAttribute(ExtensibilityQNames.ATTR_DATA_ELEMENT_NAME, null);
	}
	
	public String getCorrelationSet() {
		return getAttribute(ExtensibilityQNames.ATTR_CORRELATION_SET, null);
	}
}
