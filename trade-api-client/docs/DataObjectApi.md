# DataObjectApi

All URIs are relative to *https://localhost:8081/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addDataObject**](DataObjectApi.md#addDataObject) | **POST** /dataObjects | Creates and adds a new data object to the TraDE middleware
[**deleteDataObject**](DataObjectApi.md#deleteDataObject) | **DELETE** /dataObjects/{dataObjectId} | Deletes a dataObject. If the dataObject belongs to a dataModel the delete request will be rejected, since data models are immutable resources.
[**getAllDataObjects**](DataObjectApi.md#getAllDataObjects) | **GET** /dataObjects | 
[**getDataObjectById**](DataObjectApi.md#getDataObjectById) | **GET** /dataObjects/{dataObjectId} | Find a data object by Id
[**getDataObjects**](DataObjectApi.md#getDataObjects) | **GET** /dataModels/{dataModelId}/dataObjects | 
[**updateDataObject**](DataObjectApi.md#updateDataObject) | **PUT** /dataObjects/{dataObjectId} | Update an existing dataObject. If the dataObject belongs to a dataModel the update request will be rejected, since data models are immutable resources.


<a name="addDataObject"></a>
# **addDataObject**
> DataObject addDataObject(dataObjectData)

Creates and adds a new data object to the TraDE middleware

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataObjectApi;


DataObjectApi apiInstance = new DataObjectApi();
DataObjectData dataObjectData = new DataObjectData(); // DataObjectData | The name and creating entity of the data object that will be created.
try {
    DataObject result = apiInstance.addDataObject(dataObjectData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataObjectApi#addDataObject");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataObjectData** | [**DataObjectData**](DataObjectData.md)| The name and creating entity of the data object that will be created. |

### Return type

[**DataObject**](DataObject.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="deleteDataObject"></a>
# **deleteDataObject**
> deleteDataObject(dataObjectId)

Deletes a dataObject. If the dataObject belongs to a dataModel the delete request will be rejected, since data models are immutable resources.

Deletes a complete data object and its referenced data elements as well as all instances of conntected to both the data object and its data elements from the TraDE middleware

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataObjectApi;


DataObjectApi apiInstance = new DataObjectApi();
String dataObjectId = "dataObjectId_example"; // String | Id of the data object that needs to be fetched
try {
    apiInstance.deleteDataObject(dataObjectId);
} catch (ApiException e) {
    System.err.println("Exception when calling DataObjectApi#deleteDataObject");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataObjectId** | **String**| Id of the data object that needs to be fetched |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getAllDataObjects"></a>
# **getAllDataObjects**
> DataObjectArrayWithLinks getAllDataObjects(start, size, name, status)



Gets all available &#x60;DataObject&#x60; resources. Optional query parameter of **start** and **size** enable pagination of the collection of data object resources, param **name** filters result list by name and param **status** filters result list by status of the data objects.

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataObjectApi;


DataObjectApi apiInstance = new DataObjectApi();
Integer start = 1; // Integer | Start index of returned collection of resources for pagination.
Integer size = 50; // Integer | Size of the returned sub-collection of resources for pagination.
String name = "name_example"; // String | Name of data objects to return
String status = "status_example"; // String | Status of data objects to return
try {
    DataObjectArrayWithLinks result = apiInstance.getAllDataObjects(start, size, name, status);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataObjectApi#getAllDataObjects");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **start** | **Integer**| Start index of returned collection of resources for pagination. | [optional] [default to 1]
 **size** | **Integer**| Size of the returned sub-collection of resources for pagination. | [optional] [default to 50]
 **name** | **String**| Name of data objects to return | [optional]
 **status** | **String**| Status of data objects to return | [optional]

### Return type

[**DataObjectArrayWithLinks**](DataObjectArrayWithLinks.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getDataObjectById"></a>
# **getDataObjectById**
> DataObjectWithLinks getDataObjectById(dataObjectId)

Find a data object by Id

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataObjectApi;


DataObjectApi apiInstance = new DataObjectApi();
String dataObjectId = "dataObjectId_example"; // String | Id of the data object that needs to be fetched
try {
    DataObjectWithLinks result = apiInstance.getDataObjectById(dataObjectId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataObjectApi#getDataObjectById");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataObjectId** | **String**| Id of the data object that needs to be fetched |

### Return type

[**DataObjectWithLinks**](DataObjectWithLinks.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getDataObjects"></a>
# **getDataObjects**
> DataObjectArrayWithLinks getDataObjects(dataModelId, start, size)



Gets all &#x60;DataObject&#x60; resources which belong the data model. Optional query parameter of **start** and **size** enable pagination of the collection of data object resources.

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataObjectApi;


DataObjectApi apiInstance = new DataObjectApi();
String dataModelId = "dataModelId_example"; // String | Id of the data model that needs to be fetched
Integer start = 1; // Integer | Start index of returned collection of resources for pagination.
Integer size = 50; // Integer | Size of the returned sub-collection of resources for pagination.
try {
    DataObjectArrayWithLinks result = apiInstance.getDataObjects(dataModelId, start, size);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataObjectApi#getDataObjects");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataModelId** | **String**| Id of the data model that needs to be fetched |
 **start** | **Integer**| Start index of returned collection of resources for pagination. | [optional] [default to 1]
 **size** | **Integer**| Size of the returned sub-collection of resources for pagination. | [optional] [default to 50]

### Return type

[**DataObjectArrayWithLinks**](DataObjectArrayWithLinks.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateDataObject"></a>
# **updateDataObject**
> DataObjectWithLinks updateDataObject(dataObjectId, dataObject)

Update an existing dataObject. If the dataObject belongs to a dataModel the update request will be rejected, since data models are immutable resources.

Update an existing dataObject

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataObjectApi;


DataObjectApi apiInstance = new DataObjectApi();
String dataObjectId = "dataObjectId_example"; // String | Id of the data object that needs to be updated
DataObject dataObject = new DataObject(); // DataObject | The updated data object resource. Only changes to the following fields are reflected **name** and creating **entity**. All other changes are ignored since the corresponding fields of the resource are internally set by the server or are immutable.
try {
    DataObjectWithLinks result = apiInstance.updateDataObject(dataObjectId, dataObject);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataObjectApi#updateDataObject");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataObjectId** | **String**| Id of the data object that needs to be updated |
 **dataObject** | [**DataObject**](DataObject.md)| The updated data object resource. Only changes to the following fields are reflected **name** and creating **entity**. All other changes are ignored since the corresponding fields of the resource are internally set by the server or are immutable. |

### Return type

[**DataObjectWithLinks**](DataObjectWithLinks.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

