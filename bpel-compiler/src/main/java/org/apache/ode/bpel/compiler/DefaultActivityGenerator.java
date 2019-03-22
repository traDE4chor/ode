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
package org.apache.ode.bpel.compiler;

import org.apache.ode.bpel.compiler.api.CompilerContext;
import org.apache.ode.bpel.compiler.bom.BpelObject;
import org.apache.ode.bpel.compiler.bom.ExtensibilityQNames;
import org.apache.ode.bpel.compiler.bom.FailureHandling;
import org.apache.ode.bpel.compiler.bom.TraDEAssociation;
import org.apache.ode.bpel.o.OActivity;
import org.apache.ode.bpel.o.OFailureHandling;
import org.apache.ode.bpel.o.OScope;
import org.apache.ode.bpel.o.OTraDEAssociation;
import org.apache.ode.bpel.o.OTraDEAssociation.ODataObjectRef;
import org.apache.ode.bpel.o.OTraDEAssociation.OTrigger;
import org.w3c.dom.Element;

/**
 * Base implementation of the {@link ActivityGenerator} interface.
 */
abstract class DefaultActivityGenerator implements ActivityGenerator {
    protected CompilerContext _context;

    public void setContext(CompilerContext context) {
        _context = context;
    }

    static void defaultExtensibilityElements(CompilerContext context, OActivity output, BpelObject src) {
        if (src != null) {
            failureHandlinExtensibilityElement(output, src);
            
            // @hahnml: Handle TraDE Association: Trigger
            tradeAssociationExtensibilityElement(context, output, src);
        }
    }

    static private void failureHandlinExtensibilityElement(OActivity output, BpelObject src) {
        // Failure handling extensibility element.
        Element element = src.getExtensibilityElement(ExtensibilityQNames.FAILURE_HANDLING);
        if (element == null)
            return;
        FailureHandling extElement = new FailureHandling(element);
        OFailureHandling obj = new OFailureHandling();
        obj.retryFor = extElement.getRetryFor();
        obj.retryDelay = extElement.getRetryDelay();
        obj.faultOnFailure = extElement.getFaultOnFailure();
        output.setFailureHandling(obj);
    }

    static private void tradeAssociationExtensibilityElement(CompilerContext context, OActivity output,
            BpelObject src) {
        // @hahnml: TraDE association extensibility element.
        Element element = src
                .getExtensibilityElement(ExtensibilityQNames.ASSOCIATION);
        if (element == null)
            return;
        TraDEAssociation extElement = new TraDEAssociation(element);

        // Check if a trigger is specified
        if (extElement.getTrigger() == null)
            return;

        OTraDEAssociation obj = new OTraDEAssociation();
        OTraDEAssociation.OTrigger trigger = new OTrigger();

        trigger.synchronizationMethod = extElement.getTrigger()
                .getSynchronizationMethod();
        
        if (extElement.getTrigger().getVariable() == null)
            return;
        
        OScope.Variable var = context.resolveVariable(extElement.getTrigger().getVariable());
        trigger.variable = var;

        // Check if a data object reference is specified
        if (extElement.getTrigger().getDataObjectRef() == null)
            return;
        
        OTraDEAssociation.ODataObjectRef objectRef = new ODataObjectRef();
        objectRef.dataObjectName = extElement.getTrigger().getDataObjectRef()
                .getDataObjectName();
        objectRef.dataElementName = extElement.getTrigger().getDataObjectRef()
                .getDataElementName();

        OScope.CorrelationSet cset = context.resolveCorrelationSet(extElement.getTrigger().getDataObjectRef()
                .getCorrelationSet());
        objectRef.correlationSet = cset;
        
        trigger.dataObjectRef = objectRef;

        obj.trigger = trigger;
        output.setTraDEAssociation(obj);
    }
}
