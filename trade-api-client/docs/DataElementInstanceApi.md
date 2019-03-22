# DataElementInstanceApi

All URIs are relative to *https://localhost:8081/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getDataElementInstance**](DataElementInstanceApi.md#getDataElementInstance) | **GET** /dataElementInstances/{instanceId} | Find a data element instance by Id
[**getDataElementInstances**](DataElementInstanceApi.md#getDataElementInstances) | **GET** /dataObjectInstances/{instanceId}/elementInstances | 
[**getDataElementInstancesOfDataElement**](DataElementInstanceApi.md#getDataElementInstancesOfDataElement) | **GET** /dataElements/{dataElementId}/instances | 
[**queryDataElementInstance**](DataElementInstanceApi.md#queryDataElementInstance) | **GET** /dataElementInstances | 


<a name="getDataElementInstance"></a>
# **getDataElementInstance**
> DataElementInstanceWithLinks getDataElementInstance(instanceId)

Find a data element instance by Id

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataElementInstanceApi;


DataElementInstanceApi apiInstance = new DataElementInstanceApi();
String instanceId = "instanceId_example"; // String | Id of the data element instance that needs to be fetched
try {
    DataElementInstanceWithLinks result = apiInstance.getDataElementInstance(instanceId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataElementInstanceApi#getDataElementInstance");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instanceId** | **String**| Id of the data element instance that needs to be fetched |

### Return type

[**DataElementInstanceWithLinks**](DataElementInstanceWithLinks.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getDataElementInstances"></a>
# **getDataElementInstances**
> DataElementInstanceArrayWithLinks getDataElementInstances(instanceId, start, size, status)



Gets all available &#x60;DataElementInstance&#x60; resources. Optional query parameter of **start** and **size** enable pagination of the collection of data element instance resources and param **status** filters result list by status of the data element instances.

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataElementInstanceApi;


DataElementInstanceApi apiInstance = new DataElementInstanceApi();
String instanceId = "instanceId_example"; // String | Id of the data object instance that needs to be fetched
Integer start = 1; // Integer | Start index of returned collection of resources for pagination.
Integer size = 50; // Integer | Size of the returned sub-collection of resources for pagination.
String status = "status_example"; // String | Status of data element instances to return
try {
    DataElementInstanceArrayWithLinks result = apiInstance.getDataElementInstances(instanceId, start, size, status);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataElementInstanceApi#getDataElementInstances");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instanceId** | **String**| Id of the data object instance that needs to be fetched |
 **start** | **Integer**| Start index of returned collection of resources for pagination. | [optional] [default to 1]
 **size** | **Integer**| Size of the returned sub-collection of resources for pagination. | [optional] [default to 50]
 **status** | **String**| Status of data element instances to return | [optional]

### Return type

[**DataElementInstanceArrayWithLinks**](DataElementInstanceArrayWithLinks.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getDataElementInstancesOfDataElement"></a>
# **getDataElementInstancesOfDataElement**
> DataElementInstanceArrayWithLinks getDataElementInstancesOfDataElement(dataElementId, start, size, status)



Gets all available &#x60;DataElementInstance&#x60; resources. Optional query parameter of **start** and **size** enable pagination of the collection of data element instance resources and param **status** filters result list by status of the data element instances.

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataElementInstanceApi;


DataElementInstanceApi apiInstance = new DataElementInstanceApi();
String dataElementId = "dataElementId_example"; // String | Id of the data element that needs to be fetched
Integer start = 1; // Integer | Start index of returned collection of resources for pagination.
Integer size = 50; // Integer | Size of the returned sub-collection of resources for pagination.
String status = "status_example"; // String | Status of data element instances to return
try {
    DataElementInstanceArrayWithLinks result = apiInstance.getDataElementInstancesOfDataElement(dataElementId, start, size, status);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataElementInstanceApi#getDataElementInstancesOfDataElement");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataElementId** | **String**| Id of the data element that needs to be fetched |
 **start** | **Integer**| Start index of returned collection of resources for pagination. | [optional] [default to 1]
 **size** | **Integer**| Size of the returned sub-collection of resources for pagination. | [optional] [default to 50]
 **status** | **String**| Status of data element instances to return | [optional]

### Return type

[**DataElementInstanceArrayWithLinks**](DataElementInstanceArrayWithLinks.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="queryDataElementInstance"></a>
# **queryDataElementInstance**
> DataElementInstanceWithLinks queryDataElementInstance(dataModelName, dataObjectName, dataElementName, correlationProperties)



Get an available &#x60;DataElementInstance&#x60; resource based on specified parameters. Query parameters **dataModelName**, **dataObjectName** and **dataElementName** as well as **correlationProperties** transfered as request body are used as a query to find a matching data element instance.

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataElementInstanceApi;


DataElementInstanceApi apiInstance = new DataElementInstanceApi();
String dataModelName = "dataModelName_example"; // String | Name of the data model a data element instance should belong to.
String dataObjectName = "dataObjectName_example"; // String | The name of the data object the instance should belong to.
String dataElementName = "dataElementName_example"; // String | The name of the data element the instance should belong to.
CorrelationPropertyArray correlationProperties = new CorrelationPropertyArray(); // CorrelationPropertyArray | An array of correlation properties associated with the searched data element instance.
try {
    DataElementInstanceWithLinks result = apiInstance.queryDataElementInstance(dataModelName, dataObjectName, dataElementName, correlationProperties);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataElementInstanceApi#queryDataElementInstance");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataModelName** | **String**| Name of the data model a data element instance should belong to. |
 **dataObjectName** | **String**| The name of the data object the instance should belong to. |
 **dataElementName** | **String**| The name of the data element the instance should belong to. |
 **correlationProperties** | [**CorrelationPropertyArray**](CorrelationPropertyArray.md)| An array of correlation properties associated with the searched data element instance. |

### Return type

[**DataElementInstanceWithLinks**](DataElementInstanceWithLinks.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

