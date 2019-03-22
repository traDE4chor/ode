# DataElementApi

All URIs are relative to *https://localhost:8081/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addDataElement**](DataElementApi.md#addDataElement) | **POST** /dataObjects/{dataObjectId}/dataElements | Add a new dataElement to the dataObject. If the dataObject belongs to a dataModel the create request will be rejected, since data models are immutable resources.
[**deleteDataElement**](DataElementApi.md#deleteDataElement) | **DELETE** /dataElements/{dataElementId} | Deletes a data element. If the parent dataObject belongs to a dataModel the update request will be rejected, since data models are immutable resources.
[**getAllDataElements**](DataElementApi.md#getAllDataElements) | **GET** /dataElements | 
[**getDataElementDirectly**](DataElementApi.md#getDataElementDirectly) | **GET** /dataElements/{dataElementId} | Find a data element by Id
[**getDataElements**](DataElementApi.md#getDataElements) | **GET** /dataObjects/{dataObjectId}/dataElements | 
[**updateDataElement**](DataElementApi.md#updateDataElement) | **PUT** /dataElements/{dataElementId} | Update an existing data element. If the parent dataObject belongs to a dataModel the update request will be rejected, since data models are immutable resources.


<a name="addDataElement"></a>
# **addDataElement**
> DataElementWithLinks addDataElement(dataObjectId, dataElementData)

Add a new dataElement to the dataObject. If the dataObject belongs to a dataModel the create request will be rejected, since data models are immutable resources.

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataElementApi;


DataElementApi apiInstance = new DataElementApi();
String dataObjectId = "dataObjectId_example"; // String | Id of the data object that needs to be fetched
DataElementData dataElementData = new DataElementData(); // DataElementData | The name and creating entity of the data element that will be created.
try {
    DataElementWithLinks result = apiInstance.addDataElement(dataObjectId, dataElementData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataElementApi#addDataElement");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataObjectId** | **String**| Id of the data object that needs to be fetched |
 **dataElementData** | [**DataElementData**](DataElementData.md)| The name and creating entity of the data element that will be created. |

### Return type

[**DataElementWithLinks**](DataElementWithLinks.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="deleteDataElement"></a>
# **deleteDataElement**
> deleteDataElement(dataElementId)

Deletes a data element. If the parent dataObject belongs to a dataModel the update request will be rejected, since data models are immutable resources.

Deletes a complete data element and its referenced instances from the TraDE middleware

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataElementApi;


DataElementApi apiInstance = new DataElementApi();
String dataElementId = "dataElementId_example"; // String | Id of the data element that needs to be deleted
try {
    apiInstance.deleteDataElement(dataElementId);
} catch (ApiException e) {
    System.err.println("Exception when calling DataElementApi#deleteDataElement");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataElementId** | **String**| Id of the data element that needs to be deleted |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getAllDataElements"></a>
# **getAllDataElements**
> DataElementArrayWithLinks getAllDataElements(start, size, name, status)



Gets all available &#x60;DataElement&#x60; resources. Optional query parameter of **start** and **size** enable pagination of the collection of data element resources, param **name** filters result list by name and param **status** filters result list by status of the data elements.

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataElementApi;


DataElementApi apiInstance = new DataElementApi();
Integer start = 1; // Integer | Start index of returned collection of resources for pagination.
Integer size = 50; // Integer | Size of the returned sub-collection of resources for pagination.
String name = "name_example"; // String | Name of data elements to return
String status = "status_example"; // String | Status of data elements to return
try {
    DataElementArrayWithLinks result = apiInstance.getAllDataElements(start, size, name, status);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataElementApi#getAllDataElements");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **start** | **Integer**| Start index of returned collection of resources for pagination. | [optional] [default to 1]
 **size** | **Integer**| Size of the returned sub-collection of resources for pagination. | [optional] [default to 50]
 **name** | **String**| Name of data elements to return | [optional]
 **status** | **String**| Status of data elements to return | [optional]

### Return type

[**DataElementArrayWithLinks**](DataElementArrayWithLinks.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getDataElementDirectly"></a>
# **getDataElementDirectly**
> DataElementWithLinks getDataElementDirectly(dataElementId)

Find a data element by Id

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataElementApi;


DataElementApi apiInstance = new DataElementApi();
String dataElementId = "dataElementId_example"; // String | Id of the data element that needs to be fetched
try {
    DataElementWithLinks result = apiInstance.getDataElementDirectly(dataElementId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataElementApi#getDataElementDirectly");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataElementId** | **String**| Id of the data element that needs to be fetched |

### Return type

[**DataElementWithLinks**](DataElementWithLinks.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getDataElements"></a>
# **getDataElements**
> DataElementArrayWithLinks getDataElements(dataObjectId, start, size, name, status)



Gets all available &#x60;DataElement&#x60; resources. Optional query parameter of **start** and **size** enable pagination of the collection of data element resources, param **name** filters result list by name and param **status** filters result list by status of the data elements.

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataElementApi;


DataElementApi apiInstance = new DataElementApi();
String dataObjectId = "dataObjectId_example"; // String | Id of the data object that needs to be fetched
Integer start = 1; // Integer | Start index of returned collection of resources for pagination.
Integer size = 50; // Integer | Size of the returned sub-collection of resources for pagination.
String name = "name_example"; // String | Name of data elements to return
String status = "status_example"; // String | Status of data elements to return
try {
    DataElementArrayWithLinks result = apiInstance.getDataElements(dataObjectId, start, size, name, status);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataElementApi#getDataElements");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataObjectId** | **String**| Id of the data object that needs to be fetched |
 **start** | **Integer**| Start index of returned collection of resources for pagination. | [optional] [default to 1]
 **size** | **Integer**| Size of the returned sub-collection of resources for pagination. | [optional] [default to 50]
 **name** | **String**| Name of data elements to return | [optional]
 **status** | **String**| Status of data elements to return | [optional]

### Return type

[**DataElementArrayWithLinks**](DataElementArrayWithLinks.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateDataElement"></a>
# **updateDataElement**
> DataElementWithLinks updateDataElement(dataElementId, dataElement)

Update an existing data element. If the parent dataObject belongs to a dataModel the update request will be rejected, since data models are immutable resources.

Update an existing data element

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataElementApi;


DataElementApi apiInstance = new DataElementApi();
String dataElementId = "dataElementId_example"; // String | Id of the data element that needs to be fetched
DataElement dataElement = new DataElement(); // DataElement | The updated data element resource. Only changes to the following fields are reflected **name**, **type** and **contentType**. All other changes are ignored since the corresponding fields of the resource are internally set by the server or are immutable.
try {
    DataElementWithLinks result = apiInstance.updateDataElement(dataElementId, dataElement);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataElementApi#updateDataElement");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataElementId** | **String**| Id of the data element that needs to be fetched |
 **dataElement** | [**DataElement**](DataElement.md)| The updated data element resource. Only changes to the following fields are reflected **name**, **type** and **contentType**. All other changes are ignored since the corresponding fields of the resource are internally set by the server or are immutable. |

### Return type

[**DataElementWithLinks**](DataElementWithLinks.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

