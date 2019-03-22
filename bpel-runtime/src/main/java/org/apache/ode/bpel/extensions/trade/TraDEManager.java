package org.apache.ode.bpel.extensions.trade;

import io.swagger.trade.client.jersey.ApiClient;
import io.swagger.trade.client.jersey.ApiException;
import io.swagger.trade.client.jersey.api.DataElementInstanceApi;
import io.swagger.trade.client.jersey.api.DataModelApi;
import io.swagger.trade.client.jersey.api.DataObjectApi;
import io.swagger.trade.client.jersey.api.DataObjectInstanceApi;
import io.swagger.trade.client.jersey.api.DataValueApi;
import io.swagger.trade.client.jersey.api.NotificationApi;
import io.swagger.trade.client.jersey.model.CorrelationProperty;
import io.swagger.trade.client.jersey.model.CorrelationPropertyArray;
import io.swagger.trade.client.jersey.model.DataElementInstanceWithLinks;
import io.swagger.trade.client.jersey.model.DataModelArrayWithLinks;
import io.swagger.trade.client.jersey.model.DataModelWithLinks;
import io.swagger.trade.client.jersey.model.DataObjectArrayWithLinks;
import io.swagger.trade.client.jersey.model.DataObjectInstanceData;
import io.swagger.trade.client.jersey.model.DataObjectInstanceWithLinks;
import io.swagger.trade.client.jersey.model.DataObjectWithLinks;
import io.swagger.trade.client.jersey.model.DataValue;
import io.swagger.trade.client.jersey.model.DataValueArrayWithLinks;
import io.swagger.trade.client.jersey.model.DataValueWithLinks;
import io.swagger.trade.client.jersey.model.InstanceStatusEnum;
import io.swagger.trade.client.jersey.model.Notification;
import io.swagger.trade.client.jersey.model.NotificationData;
import io.swagger.trade.client.jersey.model.NotifierServiceParameter;
import io.swagger.trade.client.jersey.model.NotifierServiceParameterArray;
import io.swagger.trade.client.jersey.model.ResourceEventFilter;
import io.swagger.trade.client.jersey.model.ResourceEventFilterArray;
import io.swagger.trade.client.jersey.model.ResourceTypeEnum;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.dao.CorrelationSetDAO;
import org.apache.ode.bpel.o.OScope.Variable;
import org.apache.ode.bpel.o.OTraDEAssociation.ODataModelRef;
import org.apache.ode.bpel.o.OTraDEAssociation.ODataObjectRef;
import org.apache.ode.utils.DOMUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class TraDEManager {

	private static final Log __log = LogFactory.getLog(TraDEManager.class);

	private static final String TRADE_DATA_REF_WRAPPER = "$tradeDataValueRef";

	// TODO: This time should be customizable through the
	// ode-*.properties file!
	// Or even better the TraDE API could offer the possibility to register
	// notifications which allow any external party to specify how to get
	// informed if "something" happens, e.g., a data value is associated to a
	// data element instance.
	// This will allow the WfMS to register such a notification to get notified
	// when the data is
	// available so that the whole process instance can be dehydrated until the
	// data is available?
	private final long WAITING_TIME = 1000;
	// We set MAX_RETRY to 100 since the request triggering a process will
	// timeout after 120000ms
	private final long MAX_RETRY = 120;

	private Long _processInstanceId = 0L;

	private String _host = "localhost";
	private String _port = "8080";

	private ApiClient _client;

	private DataModelApi _dataModelApi;
	private DataObjectApi _dataObjectApi;
	private DataObjectInstanceApi _dataObjectInstanceApi;
	private DataElementInstanceApi _dataElementInstApi;
	private DataValueApi _dataValueApi;
	private NotificationApi _notificationApi;

	private HashMap<String, String> variableToDataElementInstanceIdMap = new HashMap<String, String>();

	public TraDEManager(ApiClient tradeApiClient, Long processInstanceId, String host, String port) {
		_processInstanceId = processInstanceId;

		_client = tradeApiClient;

		_host = host;
		_port = port;

		_dataModelApi = new DataModelApi(_client);
		_dataObjectApi = new DataObjectApi(_client);
		_dataObjectInstanceApi = new DataObjectInstanceApi(_client);
		_dataElementInstApi = new DataElementInstanceApi(_client);
		_dataValueApi = new DataValueApi(_client);
		_notificationApi = new NotificationApi(_client);
	}

	public Node pull(Variable variable, CorrelationSetDAO correlation,
			Integer index) throws FaultException {
		Node result = null;

		ODataObjectRef objRef = variable.tradeAssociation.dataObjectRef;
		ODataModelRef modelRef = variable.getOwner().tradeAssociation.dataModelRef;

		if (__log.isDebugEnabled()) {
			__log.debug("TraDE Manager (pid: " + _processInstanceId
					+ ") tries to pull data value for variable "
					+ variable.name);
		}

		DataElementInstanceWithLinks dEInst = null;
		int retries = 0;

		while ((dEInst == null || dEInst.getInstance() == null)
				&& retries < MAX_RETRY) {
			try {
				// Try to resolve a corresponding data element instance
				dEInst = resolveDataElementInstance(variable.name, modelRef,
						objRef, correlation.getProperties());
			} catch (ApiException e) {
				if (e.getCode() == 404) {
					// Since we were not able to resolve any instance, we assume
					// that the parent data object is not instantiated at all,
					// therefore
					// we have to wait until such an instance is created by a
					// corresponding data producer.

					// Wait some time and try again to resolve a data element
					// instance
					try {
						// TODO: Use notifications to avoid polling!
						Thread.sleep(WAITING_TIME);
						retries++;
					} catch (InterruptedException ie) {
						__log.error("TraDE Manager (pid: " + _processInstanceId
								+ ") pulling data for " + variable.name
								+ " caused an exception.", ie);
					}
				} else {
					__log.error("TraDE Manager (pid: " + _processInstanceId
							+ ") pulling data for " + variable.name
							+ " caused an exception.", e);

					throw new FaultException(
							variable.getOwner().constants.qnUninitializedVariable,
							"The variable \""
									+ variable.name
									+ "\" provided by the TraDE middleware is not resolveable.");
				}
			}
		}

		// If we were not able to resolve a data element instance after the
		// specified number of retries, we return immediately letting the
		// process instance fault
		if (dEInst != null && dEInst.getInstance() != null) {
			String dataElmInstanceId = dEInst.getInstance().getId();

			// Check if the instance has already an associated data value
			// we can pull, else we have to wait for it again
			DataValueArrayWithLinks valueArray = null;

			while ((valueArray == null || valueArray.getDataValues() == null)
					&& retries < MAX_RETRY) {
				try {
					valueArray = _dataValueApi.getDataValues(dataElmInstanceId,
							index);
				} catch (ApiException e) {
					if (e.getCode() == 404) {
						// Since we were not able to resolve any data value, we
						// have to wait until such a data value is created by a
						// corresponding data producer.

						// Wait some time and try again to resolve a data
						// element
						// instance
						try {
							// TODO: Find a better way to handle this
							Thread.sleep(WAITING_TIME);
							retries++;
						} catch (InterruptedException ie) {
							__log.error("TraDE Manager (pid: "
									+ _processInstanceId
									+ ") pulling data for " + variable.name
									+ " caused an exception.", ie);
						}
					} else {
						__log.error("TraDE Manager (pid: " + _processInstanceId
								+ ") pulling data for " + variable.name
								+ " caused an exception.", e);

						throw new FaultException(
								variable.getOwner().constants.qnUninitializedVariable,
								"The variable \""
										+ variable.name
										+ "\" provided by the TraDE middleware is not resolveable.");
					}
				}
			}

			// Pull the data
			if (valueArray.getDataValues() != null
					&& !valueArray.getDataValues().isEmpty()) {
				DataValueWithLinks value = null;

				if (valueArray.getDataValues().size() > 1) {
					__log.error("TraDE Manager (pid: " + _processInstanceId
							+ ") pulling data for " + variable.name
							+ " was not successful.");
				} else {
					// Get the first data value (the correct data value for the
					// provided index is resolved at the
					// server side)
					value = valueArray.getDataValues().get(0);
				}

				byte[] data = null;

				if (value != null) {
					while ((data == null || data.length <= 0)
							&& retries < MAX_RETRY) {
						try {
							data = _dataValueApi.pullDataValue(value
									.getDataValue().getId());
						} catch (ApiException e) {
							if (e.getCode() == 404) {
								// Since we were not able to fetch any data, we
								// have to wait until such a data is uploaded by
								// a
								// corresponding data producer.

								// Wait some time and try again to retrieve the
								// data
								try {
									// TODO: Find a better way to handle this
									Thread.sleep(WAITING_TIME);
									retries++;
								} catch (InterruptedException ie) {
									__log.error("TraDE Manager (pid: "
											+ _processInstanceId
											+ ") pulling data for "
											+ variable.name
											+ " caused an exception.", ie);
								}
							} else {
								__log.error("TraDE Manager (pid: "
										+ _processInstanceId
										+ ") pulling data for " + variable.name
										+ " caused an exception.", e);

								throw new FaultException(
										variable.getOwner().constants.qnUninitializedVariable,
										"The variable \""
												+ variable.name
												+ "\" provided by the TraDE middleware is not resolveable.");
							}
						}
					}

					try {
						result = dataToDom(variable, value.getDataValue(), data);
					} catch (SAXException e) {
						e.printStackTrace();
						__log.error(
								"TraDE Manager (pid: "
										+ _processInstanceId
										+ ") pulling data for "
										+ variable.name
										+ " caused an exception while parsing the data.",
								e);
					} catch (IOException e) {
						e.printStackTrace();
						__log.error(
								"TraDE Manager (pid: "
										+ _processInstanceId
										+ ") pulling data for "
										+ variable.name
										+ " caused an exception while parsing the data.",
								e);
					}
				}
			}
		}

		return result;
	}

	public void push(Variable variable, CorrelationSetDAO correlation,
			Node xmlData, Integer index) {
		ODataObjectRef objRef = variable.tradeAssociation.dataObjectRef;
		ODataModelRef modelRef = variable.getOwner().tradeAssociation.dataModelRef;

		if (__log.isDebugEnabled()) {
			__log.debug("TraDE Manager (pid: " + _processInstanceId
					+ ") tries to push data value of variable " + variable.name);
		}

		// Try to resolve a corresponding data element instance
		DataElementInstanceWithLinks dEInst = null;
		try {
			dEInst = resolveDataElementInstance(variable.name, modelRef,
					objRef, correlation.getProperties());
		} catch (ApiException e) {
			if (e.getCode() == 404) {
				// Nothing to do, since we create a corresponding data element
				// instance in the following
			} else {
				__log.error("TraDE Manager (pid: " + _processInstanceId
						+ ") pushing data from " + variable.name
						+ " caused an exception.", e);
			}
		}

		// Check if we were able to resolve an existing data element
		// instance
		if (dEInst == null || dEInst.getInstance() == null) {
			// Since we were not able to resolve any instance, we assume
			// that
			// the parent data object is not instantiated at all, therefore
			// we
			// create a complete new instance of the data object which also
			// trigger the creation of data element instance.
			DataObjectWithLinks dObj = null;
			try {
				dObj = resolveDataObject(modelRef, objRef);
			} catch (ApiException e) {
				if (e.getCode() == 404) {
					// Nothing to do, since we create a corresponding data
					// object instance in the following
				} else {
					__log.error("TraDE Manager (pid: " + _processInstanceId
							+ ") pushing data from " + variable.name
							+ " caused an exception.", e);
				}
			}

			if (dObj == null) {
				// TODO: If we also do not find any data object, the
				// question is
				// if we want to create one on the fly?
			} else {
				String dObjId = dObj.getDataObject().getId();

				// Instantiate the referenced data object to which the data
				// element belongs
				DataObjectInstanceWithLinks dObjInstance = null;
				try {
					dObjInstance = createNewDataObjectInstance(dObjId,
							_processInstanceId.toString(),
							correlation.getProperties());
				} catch (ApiException e) {
					__log.error("TraDE Manager (pid: " + _processInstanceId
							+ ") pushing data from " + variable.name
							+ " caused an exception.", e);
				}

				if (dObjInstance != null && dObjInstance.getInstance() != null) {
					String dObjInstanceId = dObjInstance.getInstance().getId();

					// Get the correct data element instance from the data
					// object instance
					try {
						dEInst = _dataElementInstApi
								.getDataElementInstanceByDataElementName(
										dObjInstanceId, objRef.dataElementName);
					} catch (ApiException e) {
						__log.error("TraDE Manager (pid: " + _processInstanceId
								+ ") pushing data from " + variable.name
								+ " caused an exception.", e);
					}

					// Register the data element instance Id for later use
					variableToDataElementInstanceIdMap.put(variable.name,
							dEInst.getInstance().getId());
				}
			}
		}

		if (dEInst != null && dEInst.getInstance() != null) {
			String dataElmInstanceId = dEInst.getInstance().getId();

			// TODO: Here we can also apply for sophisticated
			// handling of data values, e.g., versioning, caching (cache
			// validation), etc. to enable corresponding features to reduce
			// data
			// transfer and
			// optimize the usability of the data.

			// Check if the instance has already an associated value
			// we have to update, else create a new one
			DataValueArrayWithLinks valueArray = null;

			boolean notFound = false;
			try {
				valueArray = _dataValueApi.getDataValues(dataElmInstanceId,
						index);
			} catch (ApiException e) {
				if (e.getCode() == 404) {
					notFound = true;
				} else {
					__log.error("TraDE Manager (pid: " + _processInstanceId
							+ ") pushing data from " + variable.name
							+ " caused an exception.", e);
				}
			}

			DataValueWithLinks value = null;

			if (notFound) {
				DataValue dataValueData = new DataValue();

				if (isOfSimpleType(xmlData)) {
					dataValueData.setType("string");
					dataValueData.setContentType("text/plain");
				} else {
					dataValueData.setType("binary");
					dataValueData.setContentType("application/xml");
				}
				dataValueData.setName(variable.name);
				dataValueData.setCreatedBy(_processInstanceId.toString());

				// Create and associate a new data value to the data element
				// instance
				try {
					value = _dataValueApi
							.associateDataValueToDataElementInstance(dEInst
									.getInstance().getId(), dataValueData);
				} catch (ApiException e) {
					__log.error("TraDE Manager (pid: " + _processInstanceId
							+ ") pushing data from " + variable.name
							+ " caused an exception.", e);
				}

			} else {
				value = valueArray.getDataValues().get(0);
			}

			// Push the data
			if (value != null && value.getDataValue() != null) {
				// Implement data linking support by enabling process
				// models to pass links to actual data to TraDE through wrapping a
				// corresponding URL within a string expression:
				// '$tradeDataValueRef(URL)'.
				// By default, the result is always interpreted as data.
				String linkToData = resolveLinkFromNode(xmlData);
				
				byte[] data = null;
				boolean isLinkToData = false;
				if (linkToData != null) {
					data = linkToData.getBytes();
					isLinkToData = true;
				} else {
					data = domToData(value.getDataValue(), xmlData);
				}

				try {
					_dataValueApi.pushDataValue(value.getDataValue().getId(),
							data, isLinkToData, Long.valueOf(data.length));
				} catch (ApiException e) {
					__log.error("TraDE Manager (pid: " + _processInstanceId
							+ ") pushing data from " + variable.name
							+ " caused an exception.", e);
				}
			}
		} else {
			// TODO: What to do if we were not able to resolve an existing
			// nor create a new data element instance?
			// Throw an exception?!
		}
	}

	private DataObjectInstanceWithLinks createNewDataObjectInstance(
			String dObjId, String createdBy, Map<QName, String> corrProperties)
			throws ApiException {
		DataObjectInstanceWithLinks result = null;

		// Instantiate the data object
		DataObjectInstanceData instanceData = new DataObjectInstanceData();
		instanceData.setCreatedBy(createdBy);
		instanceData
				.setCorrelationProperties(translateCorrelationProperties(corrProperties));

		result = _dataObjectInstanceApi.addDataObjectInstance(dObjId,
				instanceData);

		return result;
	}

	private DataObjectWithLinks resolveDataObject(ODataModelRef modelRef,
			ODataObjectRef objRef) throws ApiException {
		DataObjectWithLinks result = null;

		DataModelArrayWithLinks dataModels = _dataModelApi.getDataModels(null,
				null, modelRef.dataModelQName.getNamespaceURI(),
				modelRef.dataModelQName.getLocalPart(), null);

		if (dataModels.getDataModels() != null
				&& !dataModels.getDataModels().isEmpty()) {

			Iterator<DataModelWithLinks> iter = dataModels.getDataModels()
					.iterator();

			while (result == null && iter.hasNext()) {
				DataModelWithLinks model = iter.next();

				// Retrieve the list of data objects which should contain
				// the searched one
				DataObjectArrayWithLinks list = _dataObjectApi.getDataObjects(
						model.getDataModel().getId(), null, null);
				Iterator<DataObjectWithLinks> iterObj = list.getDataObjects()
						.iterator();
				while (result == null && iterObj.hasNext()) {
					DataObjectWithLinks cur = iterObj.next();

					// Check if the data object is the one we are looking
					// for
					if (cur.getDataObject().getName()
							.equals(objRef.dataObjectName)) {
						result = cur;
					}
				}
			}
		}

		return result;
	}

	private DataElementInstanceWithLinks resolveDataElementInstance(
			String variableName, ODataModelRef modelRef, ODataObjectRef objRef,
			Map<QName, String> corrProperties) throws ApiException {
		DataElementInstanceWithLinks dEInst = null;

		// Check if we already know the data element instance from previous
		// push attempts and resolve it
		if (variableToDataElementInstanceIdMap.containsKey(variableName)) {
			String dEInstID = variableToDataElementInstanceIdMap
					.get(variableName);

			dEInst = _dataElementInstApi.getDataElementInstance(dEInstID);
		} else {
			// Translate the correlation properties
			CorrelationPropertyArray corrPropArray = translateCorrelationProperties(corrProperties);

			// Query the instance of the referenced data
			// element according to the provided correlation properties of
			// the referenced correlation set
			dEInst = _dataElementInstApi.queryDataElementInstance(
					modelRef.dataModelQName.getNamespaceURI(),
					modelRef.dataModelQName.getLocalPart(),
					objRef.dataObjectName, objRef.dataElementName,
					corrPropArray);
			if (dEInst != null && dEInst.getInstance() != null) {
				// If there is already an instance we can use, we put its ID
				// to our map to remember it
				variableToDataElementInstanceIdMap.put(variableName, dEInst
						.getInstance().getId());
			}
		}

		return dEInst;
	}

	private CorrelationPropertyArray translateCorrelationProperties(
			Map<QName, String> corrProperties) {

		CorrelationPropertyArray corrPropArray = new CorrelationPropertyArray();
		for (QName key : corrProperties.keySet()) {
			CorrelationProperty prop = new CorrelationProperty();
			// @hahnml: FIXME: Since the namespaces of the correlation
			// properties are normally different for each of the interconnected
			// workflow
			// models implementing the overall choreography, we cannot really
			// use them for the global correlation of data.
			// Therefore, at the moment we assume that the names (local parts of
			// QNames) of the
			// correlation properties used for global (choreography-wide)
			// correlation are IDENTICAL in all participating workflow models.
			prop.setKey(key.getLocalPart());
			// prop.setKey(key.toString());
			prop.setValue(corrProperties.get(key));
			corrPropArray.add(prop);
		}

		return corrPropArray;
	}

	private String translateCorrelationProperties2String(
			Map<QName, String> corrProperties) {

		HashMap<String, String> result = new HashMap<String, String>();
		for (QName key : corrProperties.keySet()) {
			// @hahnml: FIXME: Since the namespaces of the correlation
			// properties are normally different for each of the interconnected
			// workflow
			// models implementing the overall choreography, we cannot really
			// use them for the global correlation of data.
			// Therefore, at the moment we assume that the names (local parts of
			// QNames) of the
			// correlation properties used for global (choreography-wide)
			// correlation are IDENTICAL in all participating workflow models.
			result.put(key.getLocalPart(), corrProperties.get(key));
		}

		return result.toString();
	}

	public void cleanup(Collection<String> notifications) {
		variableToDataElementInstanceIdMap.clear();

		// @hahnml: Delete all notifications of this instance not resolved yet
		for (String notificationID : notifications) {
			deleteNotification(notificationID);
		}
	}

	public String registerNotification(String notificationID,
			Variable variable, CorrelationSetDAO correlation) throws Exception {
		NotificationData notificationData = createNotification(variable,
				correlation, notificationID);

		// Register a notification to avoid polling
		Notification notification = _notificationApi
				.addNotification(notificationData);

		return notification.getId();
	}

	public void deleteNotification(String tradeNotificationID) {
		try {
			if (tradeNotificationID != null) {
				// Delete the previously registered notification
				_notificationApi.deleteNotification(tradeNotificationID);
			}
		} catch (Exception ie) {
			__log.error("TraDE Manager (pid: " + _processInstanceId
					+ ") deletion of notification with ID "
					+ tradeNotificationID
					+ " caused an exception at the TraDE Middleware.", ie);
		}
	}

	public boolean isDataAvailable(Variable variable,
			CorrelationSetDAO correlation) {
		ODataObjectRef objRef = variable.tradeAssociation.dataObjectRef;
		ODataModelRef modelRef = variable.getOwner().tradeAssociation.dataModelRef;

		if (__log.isDebugEnabled()) {
			__log.debug("TraDE Manager (pid: " + _processInstanceId
					+ ") tries to pull data value for variable "
					+ variable.name);
		}

		DataElementInstanceWithLinks dEInst = null;
		boolean isDataAvailable = false;

		try {
			// Try to resolve a corresponding data element instance
			dEInst = resolveDataElementInstance(variable.name, modelRef,
					objRef, correlation.getProperties());
		} catch (ApiException e) {
			if (e.getCode() == 404) {
				// Since we were not able to resolve any instance, we assume
				// that the parent data object is not instantiated at all,
				// therefore the data can not be available.
				isDataAvailable = false;
			} else {
				__log.error("TraDE Manager (pid: " + _processInstanceId
						+ ") pulling data for " + variable.name
						+ " caused an exception.", e);
			}
		}

		// If we were able to resolve a data element instance we have to check
		// if it is initialized, i.e., has an associated data value that
		// contains actual data.
		if (dEInst != null
				&& dEInst.getInstance().getStatus() == InstanceStatusEnum.INITIALIZED) {
			isDataAvailable = true;
		}

		return isDataAvailable;
	}

	private NotificationData createNotification(Variable variable,
			CorrelationSetDAO correlation, String notificationID) {
		String variableName = variable.name;
		Map<QName, String> corProperties = correlation.getProperties();

		// Add information to register a notification
		NotificationData notificationData = new NotificationData();
		notificationData.setName(_processInstanceId + "_" + variableName
				+ "_WaitForData");

		// Select the "http" notifier service to be used to send the
		// notification to ODE
		notificationData.setSelectedNotifierServiceId("http");

		// Configure the selected notifier service through specifying
		// corresponding parameters
		NotifierServiceParameterArray notifierParameterValues = new NotifierServiceParameterArray();

		NotifierServiceParameter hostParam = new NotifierServiceParameter();
		hostParam.setParameterName("hostname");
		// Use the host IP specified in properties or the default value
		// "localhost"
		hostParam.setValue(_host);
		notifierParameterValues.add(hostParam);

		NotifierServiceParameter portParam = new NotifierServiceParameter();
		portParam.setParameterName("port");
		// Use the port of the Tomcat specified in properties or the default
		// value "8080"
		portParam.setValue(_port);
		notifierParameterValues.add(portParam);

		NotifierServiceParameter resourceUriParam = new NotifierServiceParameter();
		resourceUriParam.setParameterName("resourceUri");
		// The relative path to the InstanceManagement WSDL service
		resourceUriParam.setValue("/ode/processes/InstanceManagement");
		notifierParameterValues.add(resourceUriParam);

		NotifierServiceParameter messageFormatParam = new NotifierServiceParameter();
		messageFormatParam.setParameterName("message");
		// TODO: Specify the stringified XML message the corresponding WSDL
		// service operation requires as request/input message
		messageFormatParam
				.setValue(createMessage(variableName, notificationID));
		notifierParameterValues.add(messageFormatParam);

		NotifierServiceParameter soapActionParam = new NotifierServiceParameter();
		soapActionParam.setParameterName("SOAPAction");
		// TODO: Set the correct SOAPAction from the WSDL service operation
		soapActionParam
				.setValue("http://www.apache.org/ode/pmapi/notifyInstanceAboutData");
		notifierParameterValues.add(soapActionParam);

		notificationData.setNotifierParameterValues(notifierParameterValues);

		ResourceEventFilterArray filters = new ResourceEventFilterArray();

		// Create and add resource filters such as modelClass, event,
		// correlationProperties which allow the TraDE Middleware to notify us
		// only for the data element instance we are interested in
		ResourceEventFilter classFilter = new ResourceEventFilter();
		classFilter.setFilterName("ModelClass");
		classFilter
				.setFilterValue("class org.trade.core.model.data.instance.DataElementInstance");
		filters.add(classFilter);

		ResourceEventFilter eventFilter = new ResourceEventFilter();
		eventFilter.setFilterName("NewState");
		eventFilter.setFilterValue("INITIALIZED");
		filters.add(eventFilter);

		// TODO: Check if the transmission and comparison of correlation
		// properties through a String works at the TraDE Middleware
		ResourceEventFilter correlationPropFilter = new ResourceEventFilter();
		correlationPropFilter
				.setFilterName("EventSource#CorrelationProperties");
		correlationPropFilter
				.setFilterValue(translateCorrelationProperties2String(corProperties));
		filters.add(correlationPropFilter);

		notificationData.setResourceFilters(filters);

		notificationData
				.setTypeOfResourceToObserve(ResourceTypeEnum.DATAELEMENTINSTANCE);

		return notificationData;
	}

	private String createMessage(String variableName, String notificationID) {
		String message = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:pmap=\"http://www.apache.org/ode/pmapi\">\n"
				+ "   <soapenv:Header/>\n"
				+ "   <soapenv:Body>\n"
				+ "      <pmap:notifyInstanceAboutData>\n"
				+ "         <iid>"
				+ _processInstanceId
				+ "</iid>\n"
				+ "         <dataContainerName>"
				+ variableName
				+ "</dataContainerName>\n"
				+ "         <notificationID>"
				+ notificationID
				+ "</notificationID>\n"
				+ "      </pmap:notifyInstanceAboutData>\n"
				+ "   </soapenv:Body>\n" + "</soapenv:Envelope>";

		return message;
	}

	private Node dataToDom(Variable variable, DataValue dataValue, byte[] data)
			throws SAXException, IOException {
		Document doc = DOMUtils.newDocument();
		Node val = variable.type.newInstance(doc);

		if (val.getNodeType() == Node.TEXT_NODE) {
			Element tempwrapper = doc.createElementNS(null,
					"temporary-simple-type-wrapper");
			doc.appendChild(tempwrapper);
			tempwrapper.setTextContent(new String(data));
			val = tempwrapper;
		} else {
			doc.appendChild(val);
			val.appendChild(DOMUtils.stringToDOM(data));
		}

		return val;
	}

	private byte[] domToData(DataValue dataValue, Node node) {
		byte[] result = null;

		if ("temporary-simple-type-wrapper".equals(node.getLocalName())) {
			String value = node.getTextContent();

			result = value.getBytes();
		} else if ("xsd-complex-type-wrapper".equals(node.getLocalName())) {
			Node value = node.getFirstChild();

			result = DOMUtils.domToString(value).getBytes();
		} else {
			result = DOMUtils.domToString(node).getBytes();
		}

		return result;
	}

	private boolean isOfSimpleType(Node xmlData) {
		boolean result = false;

		if ("temporary-simple-type-wrapper".equals(xmlData.getLocalName())) {
			result = true;
		}

		return result;
	}

	private String resolveLinkFromNode(Node xmlData) {
		String linkToData = null;

		// First check if the data is of simple type (i.e., string)
		if (isOfSimpleType(xmlData)) {
			String value = xmlData.getTextContent();

			if (value.contains(TRADE_DATA_REF_WRAPPER)) {
				int startIndex = value.indexOf(TRADE_DATA_REF_WRAPPER);
				int endIndex = value.lastIndexOf(")");
				linkToData = value.substring(startIndex
						+ TRADE_DATA_REF_WRAPPER.length() + 1, endIndex);
			}
		}

		return linkToData;
	}

}
