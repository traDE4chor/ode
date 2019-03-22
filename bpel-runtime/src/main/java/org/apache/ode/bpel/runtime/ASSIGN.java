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
package org.apache.ode.bpel.runtime;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.namespace.QName;

import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.compiler.bom.ExtensibilityQNames;
import org.apache.ode.bpel.eapi.ExtensionContext;
import org.apache.ode.bpel.eapi.ExtensionOperation;
import org.apache.ode.bpel.elang.xpath10.o.OXPath10Expression;
import org.apache.ode.bpel.evar.ExternalVariableModuleException;
import org.apache.ode.bpel.evt.PartnerLinkModificationEvent;
import org.apache.ode.bpel.evt.ScopeEvent;
import org.apache.ode.bpel.evt.VariableModificationEvent;
import org.apache.ode.bpel.explang.EvaluationContext;
import org.apache.ode.bpel.explang.EvaluationException;
import org.apache.ode.bpel.o.OAssign;
import org.apache.ode.bpel.o.OAssign.DirectRef;
import org.apache.ode.bpel.o.OAssign.LValueExpression;
import org.apache.ode.bpel.o.OAssign.OAssignOperation;
import org.apache.ode.bpel.o.OAssign.PropertyRef;
import org.apache.ode.bpel.o.OAssign.VariableRef;
import org.apache.ode.bpel.o.OElementVarType;
import org.apache.ode.bpel.o.OExpression;
import org.apache.ode.bpel.o.OLink;
import org.apache.ode.bpel.o.OMessageVarType;
import org.apache.ode.bpel.o.OMessageVarType.Part;
import org.apache.ode.bpel.o.OProcess.OProperty;
import org.apache.ode.bpel.o.OScope;
import org.apache.ode.bpel.o.OScope.Variable;
import org.apache.ode.bpel.runtime.channels.DataNotificationChannel;
import org.apache.ode.bpel.runtime.channels.DataNotificationChannelListener;
import org.apache.ode.bpel.runtime.channels.FaultData;
import org.apache.ode.utils.DOMUtils;
import org.apache.ode.utils.Namespaces;
import org.apache.ode.utils.msg.MessageBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 * Assign activity run-time template.
 * 
 * @author Ode team
 * @author Tammo van Lessen (University of Stuttgart) - extensionAssignOperation
 */
class ASSIGN extends ACTIVITY {
	private static final long serialVersionUID = 1L;

	private static final Logger __log = LoggerFactory.getLogger(ASSIGN.class);
	
	// @hahnml: Flag to prevent re-initialization of variables
    private boolean _firstTime = true;
	
	// @hahnml: We have to keep track of the remaining operations we have to
    // process.
    /** Remaining assign operations. */
    private List<OAssign.OAssignOperation> _remainingAssignOps = new ArrayList<OAssign.OAssignOperation>();

    // @hahnml: We have to keep track of the registered notifications and their
    // state by mapping an counter to a variable name. We use an atomic integer
    // to count outstanding notifications we have
    // to wait for before starting the execution of the ASSIGN copy operations
    /** Variables for which a notification was already registered. */
    private Map<String, AtomicInteger> _registeredNotifications = new HashMap<String, AtomicInteger>();

    // @hahnml: We have to identify the set of variables that are associated
    // with a data element at the TraDE Middleware. In this case only variables
    // read from are relevant since there we have to wait until a corresponding
    // value can be retrieved from the middleware in order to start processing
    // the related assignment opertation.
    /** Variables associated with a data element at the TraDE Middleware. */
    private Map<OScope.Variable, CorrelationSetInstance> _tradeVars = new HashMap<OScope.Variable, CorrelationSetInstance>();

	private static final ASSIGNMessages __msgs = MessageBundle
			.getMessages(ASSIGNMessages.class);

	public ASSIGN(ActivityInfo self, ScopeFrame scopeFrame, LinkFrame linkFrame) {
		super(self, scopeFrame, linkFrame);
	}

	public void run() {
	    if (_firstTime) {
    	    // @hahnml: Resolve the TraDE supported variables
            _tradeVars = identifyTraDESupportedVariables();
    
            // @hahnml: Initialize the remaining list
            _remainingAssignOps.addAll(getOAssign().operations);
            
            _firstTime = false;
	    }
        
		FaultData faultData = null;

		Iterator<OAssign.OAssignOperation> iter = _remainingAssignOps
                .iterator();
        boolean proceed = true;
        while (iter.hasNext() && proceed) {
            OAssign.OAssignOperation operation = iter.next();
            
			try {
				if (operation instanceof OAssign.Copy) {
				    OAssign.Copy ocopy = (OAssign.Copy) operation;

                    // @hahnml: Get all variables that are associated with the
                    // TraDE Middlware
                    Set<OScope.Variable> vars = resolveTraDESupportedVariables(ocopy);
                    // Check if the copy reads from such a variable
                    if (vars != null
                            && vars.size() > 0
                            && (isNotificationPending(vars) || shouldRegisterNotifications(vars))) {
                        if (shouldRegisterNotifications(vars)) {
                            // Remember the variables for which notifications
                            // were registered
                            for (OScope.Variable var : vars) {
                                _registeredNotifications.put(var.name,
                                        new AtomicInteger(0));
                            }

                            // If so, register a corresponding notification and
                            // wait until the data is available.
                            // Stop processing copy operations if one or more
                            // notifications are created since the later
                            // ones might depend on the result from the current
                            // copy
                            // operation which cannot be executed at the moment.
                            int notificationCount = createDataNotificationChannels(vars);
                            if (notificationCount > 0) {
                                proceed = false;
                            } else {
                                // Execute the copy statement
                                copy(ocopy);

                                // @hahnml: Remove the processed copy operation
                                // from the
                                // list of remaining operations
                                iter.remove();

                                // @hahnml: Remove the variables for which
                                // notifications
                                // were registered before
                                for (OScope.Variable var : vars) {
                                    _registeredNotifications.remove(var.name);
                                }
                            }
                        } else {
                            // Stop processing copy operations since the later
                            // ones might depend on the result from the current
                            // copy
                            // operation which cannot be executed at the moment.
                            proceed = false;
                        }
                    } else {
                        copy(ocopy);

                        // @hahnml: Remove the processed copy operation from the
                        // list of remaining operations
                        iter.remove();

                        // @hahnml: Remove the variables for which notifications
                        // were registered before
                        if (vars != null && !vars.isEmpty()) {
                            for (OScope.Variable var : vars) {
                                _registeredNotifications.remove(var.name);
                            }
                        }
                    }
				} else if (operation instanceof OAssign.ExtensionAssignOperation) {
					invokeExtensionAssignOperation((OAssign.ExtensionAssignOperation) operation);
					
					// @hahnml: Remove the processed extension operation from
                    // the list of remaining operations
                    iter.remove();
				}
			} catch (FaultException fault) {
				if (operation instanceof OAssign.Copy) {
					if (((OAssign.Copy) operation).ignoreMissingFromData) {
						if (fault
								.getQName()
								.equals(getOAssign().getOwner().constants.qnSelectionFailure)
								&& (fault.getCause() != null && "ignoreMissingFromData"
										.equals(fault.getCause().getMessage()))) {
							continue;
						}
					}
					if (((OAssign.Copy) operation).ignoreUninitializedFromVariable) {
						if (fault
								.getQName()
								.equals(getOAssign().getOwner().constants.qnUninitializedVariable)
								&& (fault.getCause() == null || !"throwUninitializedToVariable"
										.equals(fault.getCause().getMessage()))) {
							continue;
						}
					}
				}
				faultData = createFault(fault.getQName(), operation,
						fault.getMessage());
				break;
			} catch (ExternalVariableModuleException e) {
				__log.error("Exception while initializing external variable", e);
				_self.parent.failure(e.toString(), null);
				return;
			}
		}

		if (faultData != null) {
			__log.info("Assignment Fault: " + faultData.getFaultName()
					+ ",lineNo=" + faultData.getFaultLineNo()
					+ ",faultExplanation=" + faultData.getExplanation());
			_self.parent.completed(faultData, CompensationHandler.emptySet());
		} else {
		    // @hahnml: Only complete if we do not wait for outstanding
            // notifications
            // (_notificationCount > 0), i.e., all required data is available.
            if (_registeredNotifications.isEmpty()) {
                // Waiting
                _self.parent.completed(null, CompensationHandler.emptySet());
            }
		}
	}

	protected Logger log() {
		return __log;
	}

	private OAssign getOAssign() {
		return (OAssign) _self.o;
	}

	private Node evalLValue(OAssign.LValue to) throws FaultException,
			ExternalVariableModuleException {
		final BpelRuntimeContext napi = getBpelRuntimeContext();
		Node lval = null;
		if (!(to instanceof OAssign.PartnerLinkRef)) {
			VariableInstance lvar;
			try {
				lvar = _scopeFrame.resolve(to.getVariable());
			} catch (RuntimeException e) {
				__log.error("iid: " + getBpelRuntimeContext().getPid()
						+ " error evaluating lvalue");
				throw new FaultException(
						getOAssign().getOwner().constants.qnSelectionFailure,
						e.getMessage());
			}
			if (lvar == null) {
				String msg = __msgs.msgEvalException(to.toString(),
						"Could not resolve variable in current scope");
				if (__log.isDebugEnabled())
					__log.debug(to + ": " + msg);
				throw new FaultException(
						getOAssign().getOwner().constants.qnSelectionFailure,
						msg);
			}
			if (!napi.isVariableInitialized(lvar)) {
				Document doc = DOMUtils.newDocument();
				Node val = to.getVariable().type.newInstance(doc);
				if (val.getNodeType() == Node.TEXT_NODE) {
					Element tempwrapper = doc.createElementNS(null,
							"temporary-simple-type-wrapper");
					doc.appendChild(tempwrapper);
					tempwrapper.appendChild(val);
					val = tempwrapper;
				} else
					doc.appendChild(val);
				// Only external variables need to be initialized, others are
				// new and going to be overwtitten
				if (lvar.declaration.extVar != null)
					lval = initializeVariable(lvar, val);
				else
					lval = val;
			} else
				lval = fetchVariableData(lvar, true);
		}
		return lval;
	}

	/**
	 * Get the r-value. There are several possibilities:
	 * <ul>
	 * <li>a message is selected - an element representing the whole message is
	 * returned.</li>
	 * <li>a (element) message part is selected - the element is returned.</li>
	 * <li>a (typed) message part is select - a wrapper element is returned.</li>
	 * <li>an attribute is selected - an attribute node is returned.</li>
	 * <li>a text node/string expression is selected - a text node is returned.</li>
	 * </ul>
	 * 
	 * @param from
	 * 
	 * @return Either {@link Element}, {@link org.w3c.dom.Text}, or
	 *         {@link org.w3c.dom.Attr} node representing the r-value.
	 * 
	 * @throws FaultException
	 *             DOCUMENTME
	 * @throws UnsupportedOperationException
	 *             DOCUMENTME
	 * @throws IllegalStateException
	 *             DOCUMENTME
	 */
	private Node evalRValue(OAssign.RValue from) throws FaultException,
			ExternalVariableModuleException {
		if (__log.isDebugEnabled())
			__log.debug("Evaluating FROM expression \"" + from + "\".");

		Node retVal;
		if (from instanceof DirectRef) {
			OAssign.DirectRef dref = (OAssign.DirectRef) from;
			sendVariableReadEvent(_scopeFrame.resolve(dref.variable));
			Node data = fetchVariableData(_scopeFrame.resolve(dref.variable),
					false);
			retVal = DOMUtils.findChildByName((Element) data, dref.elName);
		} else if (from instanceof OAssign.VariableRef) {
			OAssign.VariableRef varRef = (OAssign.VariableRef) from;
			sendVariableReadEvent(_scopeFrame.resolve(varRef.variable));
			Node data = fetchVariableData(_scopeFrame.resolve(varRef.variable),
					false);
			retVal = evalQuery(data, varRef.part != null ? varRef.part
					: varRef.headerPart, varRef.location,
					getEvaluationContext());
		} else if (from instanceof OAssign.PropertyRef) {
			OAssign.PropertyRef propRef = (OAssign.PropertyRef) from;
			sendVariableReadEvent(_scopeFrame.resolve(propRef.variable));
			Node data = fetchVariableData(
					_scopeFrame.resolve(propRef.variable), false);
			retVal = evalQuery(data, propRef.propertyAlias.part,
					propRef.propertyAlias.location, getEvaluationContext());
		} else if (from instanceof OAssign.PartnerLinkRef) {
			OAssign.PartnerLinkRef pLinkRef = (OAssign.PartnerLinkRef) from;
			PartnerLinkInstance pLink = _scopeFrame
					.resolve(pLinkRef.partnerLink);
			Node tempVal = pLinkRef.isMyEndpointReference ? getBpelRuntimeContext()
					.fetchMyRoleEndpointReferenceData(pLink)
					: getBpelRuntimeContext()
							.fetchPartnerRoleEndpointReferenceData(pLink);
			if (__log.isDebugEnabled())
				__log.debug("RValue is a partner link, corresponding endpoint "
						+ tempVal.getClass().getName() + " has value "
						+ DOMUtils.domToString(tempVal));
			retVal = tempVal;
		} else if (from instanceof OAssign.Expression) {
			List<Node> l;
			OExpression expr = ((OAssign.Expression) from).expression;
			try {
				l = getBpelRuntimeContext().getExpLangRuntime().evaluate(expr,
						getEvaluationContext());
				if (l.size() == 0 || l.get(0) == null
						|| !(l.get(0) instanceof Element)) {
					if (__log.isTraceEnabled()) {
						__log.trace("evalRValue: OAssign.Expression: eval reult not Element or node=null");
					}
				} else {
					Element element = (Element) l.get(0);
					for (Map.Entry<String, String> entry : DOMUtils
							.getMyNSContext(element).toMap().entrySet()) {
						String key = entry.getKey();
						String value = entry.getValue();
						if (entry.getKey() == null
								|| entry.getKey().length() == 0) {
							element.setAttributeNS(DOMUtils.NS_URI_XMLNS,
									"xmlns", value);
						} else {
							element.setAttributeNS(DOMUtils.NS_URI_XMLNS,
									"xmlns:" + key, value);
						}
					}
				}
			} catch (EvaluationException e) {
				String msg = __msgs.msgEvalException(from.toString(),
						e.getMessage());
				if (__log.isDebugEnabled())
					__log.debug(from + ": " + msg);
				if (e.getCause() instanceof FaultException)
					throw (FaultException) e.getCause();
				throw new FaultException(
						getOAssign().getOwner().constants.qnSelectionFailure,
						msg);
			}
			if (l.size() == 0) {
				String msg = __msgs.msgRValueNoNodesSelected(expr.toString());
				if (__log.isDebugEnabled())
					__log.debug(from + ": " + msg);
				throw new FaultException(
						getOAssign().getOwner().constants.qnSelectionFailure,
						msg, new Throwable("ignoreMissingFromData"));
			} else if (l.size() > 1) {
				String msg = __msgs.msgRValueMultipleNodesSelected(expr
						.toString());
				if (__log.isDebugEnabled())
					__log.debug(from + ": " + msg);
				throw new FaultException(
						getOAssign().getOwner().constants.qnSelectionFailure,
						msg);
			}
			retVal = (Node) l.get(0);
		} else if (from instanceof OAssign.Literal) {
			String literal = ((OAssign.Literal) from).getXmlLiteral();
			Element literalRoot;
			try {
				literalRoot = DOMUtils.stringToDOM(literal);
			} catch (Exception e) {
				throw new RuntimeException("XML literal parsing failed "
						+ literal, e);
			}
			assert literalRoot.getLocalName().equals("literal");
			// We'd like a single text node...

			literalRoot.normalize();
			retVal = literalRoot.getFirstChild();

			// Adjust for whitespace before an element.
			if (retVal != null && retVal.getNodeType() == Node.TEXT_NODE
					&& retVal.getTextContent().trim().length() == 0
					&& retVal.getNextSibling() != null) {
				retVal = retVal.getNextSibling();
			}

			if (retVal == null) {
				// Special case, no children --> empty TII
				retVal = literalRoot.getOwnerDocument().createTextNode("");
			} else if (retVal.getNodeType() == Node.ELEMENT_NODE) {
				// Make sure there is no more elements.
				Node x = retVal.getNextSibling();
				while (x != null) {
					if (x.getNodeType() == Node.ELEMENT_NODE) {
						String msg = __msgs.msgLiteralContainsMultipleEIIs();
						if (__log.isDebugEnabled())
							__log.debug(from + ": " + msg);
						throw new FaultException(
								getOAssign().getOwner().constants.qnSelectionFailure,
								msg);

					}
					x = x.getNextSibling();
				}
			} else if (retVal.getNodeType() == Node.TEXT_NODE) {
				// Make sure there are no elements following this text node.
				Node x = retVal.getNextSibling();
				while (x != null) {
					if (x.getNodeType() == Node.ELEMENT_NODE) {
						String msg = __msgs.msgLiteralContainsMixedContent();
						if (__log.isDebugEnabled())
							__log.debug(from + ": " + msg);
						throw new FaultException(
								getOAssign().getOwner().constants.qnSelectionFailure,
								msg);

					}
					x = x.getNextSibling();
				}

			}

			if (retVal == null) {
				String msg = __msgs.msgLiteralMustContainTIIorEII();
				if (__log.isDebugEnabled())
					__log.debug(from + ": " + msg);
				throw new FaultException(
						getOAssign().getOwner().constants.qnSelectionFailure,
						msg);
			}
		} else {
			String msg = __msgs
					.msgInternalError("Unknown RVALUE type: " + from);
			if (__log.isErrorEnabled())
				__log.error(from + ": " + msg);
			throw new FaultException(
					getOAssign().getOwner().constants.qnSelectionFailure, msg);
		}

		// Now verify we got something.
		if (retVal == null) {
			String msg = __msgs.msgEmptyRValue();
			if (__log.isDebugEnabled())
				__log.debug(from + ": " + msg);
			throw new FaultException(
					getOAssign().getOwner().constants.qnSelectionFailure, msg);
		}

		// Now check that we got the right thing.
		switch (retVal.getNodeType()) {
		case Node.TEXT_NODE:
		case Node.ATTRIBUTE_NODE:
		case Node.ELEMENT_NODE:
		case Node.CDATA_SECTION_NODE:
			break;
		default:
			String msg = __msgs.msgInvalidRValue();
			if (__log.isDebugEnabled())
				__log.debug(from + ": " + msg);

			throw new FaultException(
					getOAssign().getOwner().constants.qnSelectionFailure, msg);

		}

		return retVal;
	}

	private void copy(OAssign.Copy ocopy) throws FaultException,
			ExternalVariableModuleException {

		if (__log.isDebugEnabled())
			__log.debug("Assign.copy(" + ocopy + ")");

		ScopeEvent se;

		// Check for message to message - copy, we can do this efficiently in
		// the database.
		if ((ocopy.to instanceof VariableRef && ((VariableRef) ocopy.to)
				.isMessageRef())
				|| (ocopy.from instanceof VariableRef && ((VariableRef) ocopy.from)
						.isMessageRef())) {

			if ((ocopy.to instanceof VariableRef && ((VariableRef) ocopy.to)
					.isMessageRef())
					&& ocopy.from instanceof VariableRef
					&& ((VariableRef) ocopy.from).isMessageRef()) {

				final VariableInstance lval = _scopeFrame.resolve(ocopy.to
						.getVariable());
				final VariableInstance rval = _scopeFrame
						.resolve(((VariableRef) ocopy.from).getVariable());
				Element lvalue = (Element) fetchVariableData(rval, false);
				initializeVariable(lval, lvalue);
				se = new VariableModificationEvent(lval.declaration.name);
				((VariableModificationEvent) se).setNewValue(lvalue);
			} else {
				// This really should have been caught by the compiler.
				__log.error("Message/Non-Message Assignment, should be caught by compiler:"
						+ ocopy);
				throw new FaultException(
						ocopy.getOwner().constants.qnSelectionFailure,
						"Message/Non-Message Assignment:  " + ocopy);
			}
		} else {
			// Conventional Assignment logic.
			Node rvalue = evalRValue(ocopy.from);
			Node lvalue = evalLValue(ocopy.to);
			if (__log.isDebugEnabled()) {
				__log.debug("lvalue after eval " + lvalue);
				if (lvalue != null)
					__log.debug("content " + DOMUtils.domToString(lvalue));
			}

			// Get a pointer within the lvalue.
			Node lvaluePtr = lvalue;
			boolean headerAssign = false;
			if (ocopy.to instanceof OAssign.DirectRef) {
				DirectRef dref = ((DirectRef) ocopy.to);
				Element el = DOMUtils.findChildByName((Element) lvalue,
						dref.elName);
				if (el == null) {
					el = (Element) ((Element) lvalue).appendChild(lvalue
							.getOwnerDocument().createElementNS(
									dref.elName.getNamespaceURI(),
									dref.elName.getLocalPart()));
				}
				lvaluePtr = el;
			} else if (ocopy.to instanceof OAssign.VariableRef) {
				VariableRef varRef = ((VariableRef) ocopy.to);
				if (varRef.headerPart != null)
					headerAssign = true;
				lvaluePtr = evalQuery(
						lvalue,
						varRef.part != null ? varRef.part : varRef.headerPart,
						varRef.location,
						new EvaluationContextProxy(varRef.getVariable(), lvalue));
			} else if (ocopy.to instanceof OAssign.PropertyRef) {
				PropertyRef propRef = ((PropertyRef) ocopy.to);
				lvaluePtr = evalQuery(lvalue, propRef.propertyAlias.part,
						propRef.propertyAlias.location,
						new EvaluationContextProxy(propRef.getVariable(),
								lvalue));
			} else if (ocopy.to instanceof OAssign.LValueExpression) {
				LValueExpression lexpr = (LValueExpression) ocopy.to;
				lexpr.setInsertMissingToData(ocopy.insertMissingToData);
				lvaluePtr = evalQuery(lvalue, null, lexpr.expression,
						new EvaluationContextProxy(lexpr.getVariable(), lvalue));
				if (__log.isDebugEnabled())
					__log.debug("lvaluePtr expr res " + lvaluePtr);
			}

			// For partner link assignmenent, the whole content is assigned.
			if (ocopy.to instanceof OAssign.PartnerLinkRef) {
				OAssign.PartnerLinkRef pLinkRef = ((OAssign.PartnerLinkRef) ocopy.to);
				PartnerLinkInstance plval = _scopeFrame
						.resolve(pLinkRef.partnerLink);
				replaceEndpointRefence(plval, rvalue);
				se = new PartnerLinkModificationEvent(
						((OAssign.PartnerLinkRef) ocopy.to).partnerLink
								.getName());
			} else {
				// Sneakily converting the EPR if it's not the format expected
				// by the lvalue
				if (ocopy.from instanceof OAssign.PartnerLinkRef) {
					rvalue = getBpelRuntimeContext().convertEndpointReference(
							(Element) rvalue, lvaluePtr);
					if (rvalue.getNodeType() == Node.DOCUMENT_NODE)
						rvalue = ((Document) rvalue).getDocumentElement();
				}

				Node parentNode = lvaluePtr.getParentNode();
				if (headerAssign && parentNode != null
						&& "message".equals(parentNode.getNodeName())
						&& rvalue.getNodeType() == Node.ELEMENT_NODE) {
					lvalue = copyInto((Element) lvalue, (Element) lvaluePtr,
							(Element) rvalue);
				} else if (rvalue.getNodeType() == Node.ELEMENT_NODE
						&& lvaluePtr.getNodeType() == Node.ELEMENT_NODE) {
					lvalue = replaceElement((Element) lvalue,
							(Element) lvaluePtr, (Element) rvalue,
							ocopy.keepSrcElementName);
				} else {
					lvalue = replaceContent(lvalue, lvaluePtr,
							rvalue.getTextContent());
				}
				final VariableInstance lval = _scopeFrame.resolve(ocopy.to
						.getVariable());
				if (__log.isDebugEnabled())
					__log.debug("ASSIGN Writing variable '"
							+ lval.declaration.name + "' value '"
							+ DOMUtils.domToString(lvalue) + "'");
				commitChanges(lval, lvalue);
				se = new VariableModificationEvent(lval.declaration.name);
				((VariableModificationEvent) se).setNewValue(lvalue);
			}
		}

		if (ocopy.debugInfo != null)
			se.setLineNo(ocopy.debugInfo.startLine);
		sendEvent(se);
	}

	@Override
	Node fetchVariableData(VariableInstance variable, boolean forWriting)
			throws FaultException {
		try {
			return super.fetchVariableData(variable, forWriting);
		} catch (FaultException fe) {
			if (forWriting) {
				fe = new FaultException(fe.getQName(), fe.getMessage(),
						new Throwable("throwUninitializedToVariable"));
			}
			throw fe;
		}
	}

	private void replaceEndpointRefence(PartnerLinkInstance plval, Node rvalue)
			throws FaultException {
		if (rvalue.getNodeType() == Node.ATTRIBUTE_NODE) {
			rvalue = rvalue.getOwnerDocument().createTextNode(
					((Attr) rvalue).getValue());
		}
		// Eventually wrapping with service-ref element if we've been directly
		// assigned some
		// value that isn't wrapped.
		if (rvalue.getNodeType() == Node.TEXT_NODE
				|| (rvalue.getNodeType() == Node.ELEMENT_NODE && !rvalue
						.getLocalName().equals("service-ref"))) {
			Document doc = DOMUtils.newDocument();
			Element serviceRef = doc.createElementNS(
					Namespaces.WSBPEL2_0_FINAL_SERVREF, "service-ref");
			doc.appendChild(serviceRef);
			serviceRef.appendChild(doc.importNode(rvalue, true));
			rvalue = serviceRef;
		}

		getBpelRuntimeContext().writeEndpointReference(plval, (Element) rvalue);
	}

	private Element replaceElement(Element lval, Element ptr, Element src,
			boolean keepSrcElement) {
		Document doc = ptr.getOwnerDocument();
		Node parent = ptr.getParentNode();
		if (keepSrcElement) {
			Element replacement = (Element) doc.importNode(src, true);
			parent.replaceChild(replacement, ptr);
			return (lval == ptr) ? replacement : lval;
		}

		Element replacement = doc.createElementNS(ptr.getNamespaceURI(),
				ptr.getTagName());
		NodeList nl = src.getChildNodes();
		for (int i = 0; i < nl.getLength(); ++i)
			replacement.appendChild(doc.importNode(nl.item(i), true));
		copyAttributes(doc, ptr, replacement);
		copyAttributes(doc, src, replacement);
		parent.replaceChild(replacement, ptr);
		DOMUtils.copyNSContext(ptr, replacement);

		return (lval == ptr) ? replacement : lval;
	}

	private void copyAttributes(Document doc, Element original,
			Element replacement) {
		NamedNodeMap attrs = original.getAttributes();
		for (int i = 0; i < attrs.getLength(); ++i) {
			Attr attr = (Attr) attrs.item(i);
			replacement.setAttributeNodeNS((Attr) doc.importNode(attr, true));
		}
	}

	private Element copyInto(Element lval, Element ptr, Element src) {
		ptr.appendChild(ptr.getOwnerDocument().importNode(src, true));
		return lval;
	}

	/**
	 * isInsert flag desginates this as an 'element' type insertion, which
	 * requires insert the actual element value, rather than it's children
	 * 
	 * @return
	 * @throws FaultException
	 */
	private Node replaceContent(Node lvalue, Node lvaluePtr, String rvalue)
			throws FaultException {
		Document d = lvaluePtr.getOwnerDocument();

		if (__log.isDebugEnabled()) {
			__log.debug("lvaluePtr type " + lvaluePtr.getNodeType());
			__log.debug("lvaluePtr " + DOMUtils.domToString(lvaluePtr));
			__log.debug("lvalue " + lvalue);
			__log.debug("rvalue " + rvalue);
		}

		switch (lvaluePtr.getNodeType()) {
		case Node.ELEMENT_NODE:

			// Remove all the children.
			while (lvaluePtr.hasChildNodes())
				lvaluePtr.removeChild(lvaluePtr.getFirstChild());

			// Append a new text node.
			lvaluePtr.appendChild(d.createTextNode(rvalue));

			// If lvalue is a text, removing all lvaluePtr children had just
			// removed it
			// so we need to rebuild it as a child of lvaluePtr
			if (lvalue instanceof Text)
				lvalue = lvaluePtr.getFirstChild();
			break;

		case Node.TEXT_NODE:

			Node newval = d.createTextNode(rvalue);
			// Replace ourselves .
			lvaluePtr.getParentNode().replaceChild(newval, lvaluePtr);

			// A little kludge, let our caller know that the root element has
			// changed.
			// (used for assignment to a simple typed variable)
			if (lvalue.getNodeType() == Node.ELEMENT_NODE) {
				// No children, adding an empty text children to point to
				if (lvalue.getFirstChild() == null) {
					Text txt = lvalue.getOwnerDocument().createTextNode("");
					lvalue.appendChild(txt);
				}
				if (lvalue.getFirstChild().getNodeType() == Node.TEXT_NODE)
					lvalue = lvalue.getFirstChild();
			}
			if (lvalue.getNodeType() == Node.TEXT_NODE
					&& ((Text) lvalue).getWholeText().equals(
							((Text) lvaluePtr).getWholeText()))
				lvalue = lvaluePtr = newval;
			break;

		case Node.ATTRIBUTE_NODE:

			((Attr) lvaluePtr).setValue(rvalue);
			break;

		default:
			// This could occur if the expression language selects something
			// like
			// a PI or a CDATA.
			String msg = __msgs.msgInvalidLValue();
			if (__log.isDebugEnabled())
				__log.debug(lvaluePtr + ": " + msg);
			throw new FaultException(
					getOAssign().getOwner().constants.qnSelectionFailure, msg);
		}

		return lvalue;
	}

	private Node evalQuery(Node data, OMessageVarType.Part part,
			OExpression expression, EvaluationContext ec) throws FaultException {
		assert data != null;

		if (part != null) {
			QName partName = new QName(null, part.name);
			Node qualLVal = DOMUtils.findChildByName((Element) data, partName);
			if (part.type instanceof OElementVarType) {
				QName elName = ((OElementVarType) part.type).elementType;
				qualLVal = DOMUtils.findChildByName((Element) qualLVal, elName);
			} else if (part.type == null) {
				// Special case of header parts never referenced in the WSDL def
				if (qualLVal != null
						&& qualLVal.getNodeType() == Node.ELEMENT_NODE
						&& ((Element) qualLVal).getAttribute("headerPart") != null
						&& DOMUtils.getTextContent(qualLVal) == null)
					qualLVal = DOMUtils
							.getFirstChildElement((Element) qualLVal);
				// The needed part isn't there, dynamically creating it
				if (qualLVal == null) {
					qualLVal = data.getOwnerDocument().createElementNS(null,
							part.name);
					((Element) qualLVal).setAttribute("headerPart", "true");
					data.appendChild(qualLVal);
				}
			}
			data = qualLVal;
		}

		if (expression != null) {
			// Neat little trick....
			try {
				data = ec.evaluateQuery(data, expression);
			} catch (EvaluationException e) {
				String msg = __msgs.msgEvalException(expression.toString(),
						e.getMessage());
				if (__log.isDebugEnabled())
					__log.debug("", expression + ": " + msg);
				if (e.getCause() instanceof FaultException)
					throw (FaultException) e.getCause();
				throw new FaultException(
						getOAssign().getOwner().constants.qnSubLanguageExecutionFault,
						msg);
			}
		}

		return data;
	}
	
	private void invokeExtensionAssignOperation(OAssign.ExtensionAssignOperation eao)
            throws FaultException {
        final ExtensionContext context =
                new ExtensionContextImpl(this._self, this._scopeFrame, getBpelRuntimeContext());
        final QName extensionId = eao.extensionName;

        try {
            ExtensionOperation ea =
                    getBpelRuntimeContext().createExtensionActivityImplementation(extensionId);
            if (ea == null) {
                if (eao.getOwner().hasMustUnderstandExtension(extensionId.getNamespaceURI())) {
                    __log.warn("Lookup of extension activity " + extensionId + " failed.");
                    throw new FaultException(ExtensibilityQNames.UNKNOWN_EA_FAULT_NAME,
                            "Lookup of extension assign operation " + extensionId
                                    + " failed. No implementation found.");
                } else {
                    // act like <empty> - do nothing
                    context.complete();
                    return;
                }
            }

            ea.run(context, DOMUtils.stringToDOM(eao.nestedElement));

        } catch (FaultException fault) {
            __log.error("Exception while invoking extension assign operation", fault);
            context.completeWithFault(fault);
        } catch (SAXException e) {
            __log.error("Exception while invoking extension assign operation",
                    e);
            FaultException faultData =
                    new FaultException(ExtensibilityQNames.INVALID_EXTENSION_ELEMENT,
                            "The nested element of extension assign operation '"
                                    + eao.extensionName + "' is no valid XML.", e);
            context.completeWithFault(faultData);
        } catch (IOException e) {
            __log.error("Exception while invoking extension assign operation",
                    e);
            FaultException faultData =
                    new FaultException(ExtensibilityQNames.INVALID_EXTENSION_ELEMENT,
                            "The nested element of extension assign operation '"
                                    + eao.extensionName + "' is no valid XML.", e);
            context.completeWithFault(faultData);
        }
    }


	private class EvaluationContextProxy implements EvaluationContext {

		private Variable _var;

		private Node _varNode;

		private Node _rootNode;

		private EvaluationContext _ctx;

		private EvaluationContextProxy(Variable var, Node varNode) {
			_var = var;
			_varNode = varNode;
			_ctx = getEvaluationContext();
		}

		public Node readVariable(OScope.Variable variable,
				OMessageVarType.Part part) throws FaultException {
			if (variable.name.equals(_var.name)) {
				if (part == null)
					return _varNode;
				return _ctx.getPartData((Element) _varNode, part);

			} else
				return _ctx.readVariable(variable, part);

		}

		/**
		 * @see org.apache.ode.bpel.explang.EvaluationContext#readMessageProperty(org.apache.ode.bpel.o.OScope.Variable,
		 *      org.apache.ode.bpel.o.OProcess.OProperty)
		 */
		public String readMessageProperty(Variable variable, OProperty property)
				throws FaultException {
			return _ctx.readMessageProperty(variable, property);
		}

		/**
		 * @see org.apache.ode.bpel.explang.EvaluationContext#isLinkActive(org.apache.ode.bpel.o.OLink)
		 */
		public boolean isLinkActive(OLink olink) throws FaultException {
			return _ctx.isLinkActive(olink);
		}

		/**
		 * @see org.apache.ode.bpel.explang.EvaluationContext#getRootNode()
		 */
		public Node getRootNode() {
			return _rootNode;
		}

		/**
		 * @see org.apache.ode.bpel.explang.EvaluationContext#evaluateQuery(org.w3c.dom.Node,
		 *      org.apache.ode.bpel.o.OExpression)
		 */
		public Node evaluateQuery(Node root, OExpression expr)
				throws FaultException {
			_rootNode = root;
			try {
				return getBpelRuntimeContext().getExpLangRuntime()
						.evaluateNode(expr, this);
			} catch (org.apache.ode.bpel.explang.EvaluationException e) {
				throw new FaultException(
						expr.getOwner().constants.qnSubLanguageExecutionFault,
						e);
			}
		}

		public Node getPartData(Element message, Part part)
				throws FaultException {
			return _ctx.getPartData(message, part);
		}

		public Long getProcessId() {
			return _ctx.getProcessId();
		}

		public boolean narrowTypes() {
			return false;
		}

		public URI getBaseResourceURI() {
			return _ctx.getBaseResourceURI();
		}

		public Node getPropertyValue(QName propertyName) {
			return _ctx.getPropertyValue(propertyName);
		}

		public QName getProcessQName() {
			return _ctx.getProcessQName();
		}

		public Date getCurrentEventDateTime() {
			return Calendar.getInstance().getTime();
		}
	}

	// @hahnml: TraDE-specific extensions
	private int createDataNotificationChannels(Set<OScope.Variable> variables) {
        // Count the created notifications so that we know if we need to wait
        // for them or not
        int createdNotifications = 0;

        for (OScope.Variable variable : variables) {
            // Check if the data is already accessible and we should register a
            // notification or not
            if (!getBpelRuntimeContext().isTradeDataAvailaibe(variable,
                    _tradeVars.get(variable))) {
                // Data required by the activity is not available, register a
                // notification channel
                DataNotificationChannel signal = newChannel(DataNotificationChannel.class);

                object(false, new DataNotificationChannelListener(signal) {
                    private static final long serialVersionUID = -5000311966260939664L;

                    public void onNotification(String variableName) {
                        // Decrement our notification counter for the received
                        // notification
                        if (_registeredNotifications.containsKey(variableName)) {
                            _registeredNotifications.get(variableName)
                                    .getAndDecrement();
                        }

                        // Try to execute the remaining operations and complete
                        // the
                        // ASSIGN
                        ASSIGN.this.run();
                    }

                    public void onCancel(String variableName) {
                        // Decrement our notification counter for the received
                        // notification
                        if (_registeredNotifications.containsKey(variableName)) {
                            _registeredNotifications.get(variableName)
                                    .getAndDecrement();
                        }

                        // Also try to execute the activity to let it fault if
                        // for
                        // one or more variables no data is available but their
                        // notifications are canceled
                        ASSIGN.this.run();
                    }
                });
                
                // Increment the counter for the variable
                _registeredNotifications.get(variable.name).getAndIncrement();

                // Register the notification at the runtime context
                getBpelRuntimeContext().registerNotification(signal, variable,
                        _tradeVars.get(variable));

                createdNotifications++;
            }
        }

        return createdNotifications;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.ode.bpel.runtime.ACTIVITY#identifyRelevantVariables()
     * 
     * In the context of an ASSIGN activity the following group of variables is
     * relevant: 1. Only variables the ASSIGN reads from 2. Only element and
     * type-based variables, no message, etc. variables since they will not be
     * linked to the TraDE Middleware. 3. ?
     */
    @Override
    protected Set<OScope.Variable> identifyRelevantVariables() {
        Set<OScope.Variable> result = new HashSet<OScope.Variable>();

        OAssign assign = (OAssign) _self.o;

        // Check all copy operations for relevant variables
        for (OAssignOperation operation : assign.operations) {
            if (operation instanceof OAssign.Copy) {
                OAssign.Copy copy = (OAssign.Copy) operation;

                result.addAll(identifyRelevantVariables(copy));
            }
        }

        return result;
    }

    private Set<OScope.Variable> identifyRelevantVariables(OAssign.Copy copy) {
        Set<OScope.Variable> result = new HashSet<OScope.Variable>();

        // We are only interested in the left part (variables to
        // read from)

        // If the copy from is a VariableRef we can directly resolve the
        // variable
        if (copy.from instanceof VariableRef) {
            // ... but we ignore variables holding a message
            if (!((VariableRef) copy.from).isMessageVariableRef()) {
                result.add(((VariableRef) copy.from).getVariable());
            }
        } else {
            // We have to check the conventional Assignment logic to
            // resolve relevant variables.
            if (copy.from instanceof OAssign.PropertyRef) {
                OAssign.PropertyRef propRef = (OAssign.PropertyRef) copy.from;

                result.add(propRef.variable);
            } else if (copy.from instanceof OAssign.Expression) {
                OAssign.Expression expr = (OAssign.Expression) copy.from;

                if (expr.expression instanceof OXPath10Expression) {
                    OXPath10Expression exp = (OXPath10Expression) expr.expression;

                    Collection<OScope.Variable> varRefs = exp.vars.values();

                    result.addAll(varRefs);
                }
            }
        }

        return result;
    }

    private Set<OScope.Variable> resolveTraDESupportedVariables(
            OAssign.Copy copy) {
        Set<OScope.Variable> vars = new HashSet<OScope.Variable>();

        for (OScope.Variable var : identifyRelevantVariables(copy)) {
            // If the variable is supported by the TraDE Middlware, we return
            // true since the whole copy statement has to wait for the data.
            if (isTraDESupportedVariable(var)) {
                vars.add(var);
            }
        }

        return vars;
    }

    private boolean isNotificationPending(Set<Variable> vars) {
        boolean result = false;

        for (OScope.Variable var : vars) {
            if (_registeredNotifications.containsKey(var.name)) {
                result = result
                        || _registeredNotifications.get(var.name).get() > 0;
            }
        }

        return result;
    }

    private boolean shouldRegisterNotifications(Set<Variable> vars) {
        boolean result = true;

        for (OScope.Variable var : vars) {
            if (_registeredNotifications.containsKey(var.name)) {
                result = false;
            }
        }

        return result;
    }
}
