# DataObjectInstanceApi

All URIs are relative to *https://localhost:8081/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addDataObjectInstance**](DataObjectInstanceApi.md#addDataObjectInstance) | **POST** /dataObjects/{dataObjectId}/instances | Creates a data object instance for the specified dataObject. In addition a corresponding data element instance for all data elements of the specified dataObject are created.
[**deleteDataObjectInstance**](DataObjectInstanceApi.md#deleteDataObjectInstance) | **DELETE** /dataObjectInstances/{instanceId} | Deletes a data object instance
[**getDataObjectInstance**](DataObjectInstanceApi.md#getDataObjectInstance) | **GET** /dataObjectInstances/{instanceId} | Find a data object instance by Id
[**getDataObjectInstances**](DataObjectInstanceApi.md#getDataObjectInstances) | **GET** /dataObjects/{dataObjectId}/instances | 
[**queryDataObjectInstance**](DataObjectInstanceApi.md#queryDataObjectInstance) | **GET** /dataObjectInstances | 


<a name="addDataObjectInstance"></a>
# **addDataObjectInstance**
> DataObjectInstanceWithLinks addDataObjectInstance(dataObjectId, dataObjectInstanceData)

Creates a data object instance for the specified dataObject. In addition a corresponding data element instance for all data elements of the specified dataObject are created.

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataObjectInstanceApi;


DataObjectInstanceApi apiInstance = new DataObjectInstanceApi();
String dataObjectId = "dataObjectId_example"; // String | Id of the data object that needs to be fetched
DataObjectInstanceData dataObjectInstanceData = new DataObjectInstanceData(); // DataObjectInstanceData | The creating entity and correlation properties of the data object instance that will be created.
try {
    DataObjectInstanceWithLinks result = apiInstance.addDataObjectInstance(dataObjectId, dataObjectInstanceData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataObjectInstanceApi#addDataObjectInstance");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataObjectId** | **String**| Id of the data object that needs to be fetched |
 **dataObjectInstanceData** | [**DataObjectInstanceData**](DataObjectInstanceData.md)| The creating entity and correlation properties of the data object instance that will be created. |

### Return type

[**DataObjectInstanceWithLinks**](DataObjectInstanceWithLinks.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="deleteDataObjectInstance"></a>
# **deleteDataObjectInstance**
> deleteDataObjectInstance(instanceId)

Deletes a data object instance

Deletes a complete data object instance and its referenced data element instances from the TraDE middleware

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataObjectInstanceApi;


DataObjectInstanceApi apiInstance = new DataObjectInstanceApi();
String instanceId = "instanceId_example"; // String | Id of the data object instance that needs to be fetched
try {
    apiInstance.deleteDataObjectInstance(instanceId);
} catch (ApiException e) {
    System.err.println("Exception when calling DataObjectInstanceApi#deleteDataObjectInstance");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instanceId** | **String**| Id of the data object instance that needs to be fetched |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getDataObjectInstance"></a>
# **getDataObjectInstance**
> DataObjectInstanceWithLinks getDataObjectInstance(instanceId)

Find a data object instance by Id

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataObjectInstanceApi;


DataObjectInstanceApi apiInstance = new DataObjectInstanceApi();
String instanceId = "instanceId_example"; // String | Id of the data object instance that needs to be fetched
try {
    DataObjectInstanceWithLinks result = apiInstance.getDataObjectInstance(instanceId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataObjectInstanceApi#getDataObjectInstance");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instanceId** | **String**| Id of the data object instance that needs to be fetched |

### Return type

[**DataObjectInstanceWithLinks**](DataObjectInstanceWithLinks.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getDataObjectInstances"></a>
# **getDataObjectInstances**
> DataObjectInstanceArrayWithLinks getDataObjectInstances(dataObjectId, start, size, status)



Gets all available &#x60;DataObjectInstance&#x60; resources. Optional query parameter of **start** and **size** enable pagination of the collection of data object instance resources and param **status** filters result list by status of the data object instances.

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataObjectInstanceApi;


DataObjectInstanceApi apiInstance = new DataObjectInstanceApi();
String dataObjectId = "dataObjectId_example"; // String | Id of the data object that needs to be fetched
Integer start = 1; // Integer | Start index of returned collection of resources for pagination.
Integer size = 50; // Integer | Size of the returned sub-collection of resources for pagination.
String status = "status_example"; // String | Status of data object instances to return
try {
    DataObjectInstanceArrayWithLinks result = apiInstance.getDataObjectInstances(dataObjectId, start, size, status);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataObjectInstanceApi#getDataObjectInstances");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataObjectId** | **String**| Id of the data object that needs to be fetched |
 **start** | **Integer**| Start index of returned collection of resources for pagination. | [optional] [default to 1]
 **size** | **Integer**| Size of the returned sub-collection of resources for pagination. | [optional] [default to 50]
 **status** | **String**| Status of data object instances to return | [optional]

### Return type

[**DataObjectInstanceArrayWithLinks**](DataObjectInstanceArrayWithLinks.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="queryDataObjectInstance"></a>
# **queryDataObjectInstance**
> DataObjectInstanceWithLinks queryDataObjectInstance(dataModelName, dataObjectName, correlationProperties)



Get an available &#x60;DataObjectInstance&#x60; resource based on specified parameters. Query parameters **dataModelName** and **dataObjectName** as well as **correlationProperties** transfered as request body are used as a query to find a matching data object instance.

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataObjectInstanceApi;


DataObjectInstanceApi apiInstance = new DataObjectInstanceApi();
String dataModelName = "dataModelName_example"; // String | Name of the data model a data object instance should belong to.
String dataObjectName = "dataObjectName_example"; // String | The name of the data object the instance should belong to.
CorrelationPropertyArray correlationProperties = new CorrelationPropertyArray(); // CorrelationPropertyArray | An array of correlation properties associated with the searched data object instance.
try {
    DataObjectInstanceWithLinks result = apiInstance.queryDataObjectInstance(dataModelName, dataObjectName, correlationProperties);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataObjectInstanceApi#queryDataObjectInstance");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataModelName** | **String**| Name of the data model a data object instance should belong to. |
 **dataObjectName** | **String**| The name of the data object the instance should belong to. |
 **correlationProperties** | [**CorrelationPropertyArray**](CorrelationPropertyArray.md)| An array of correlation properties associated with the searched data object instance. |

### Return type

[**DataObjectInstanceWithLinks**](DataObjectInstanceWithLinks.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

