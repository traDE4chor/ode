# DataModelApi

All URIs are relative to *https://localhost:8081/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addDataModel**](DataModelApi.md#addDataModel) | **POST** /dataModels | Creates and adds a new data model to the TraDE middleware
[**deleteDataModel**](DataModelApi.md#deleteDataModel) | **DELETE** /dataModels/{dataModelId} | Deletes a data model and all related resources.
[**downloadDataModel**](DataModelApi.md#downloadDataModel) | **GET** /dataModels/{dataModelId}/serialized-model | Provides the serialized data model
[**getDataModel**](DataModelApi.md#getDataModel) | **GET** /dataDependencyGraphs/{graphId}/dataModel | Get the data model associated to the data dependency graph.
[**getDataModelDirectly**](DataModelApi.md#getDataModelDirectly) | **GET** /dataModels/{dataModelId} | Find a data model by Id
[**getDataModels**](DataModelApi.md#getDataModels) | **GET** /dataModels | 
[**setDataModel**](DataModelApi.md#setDataModel) | **POST** /dataDependencyGraphs/{graphId}/dataModel | Associates an existing dataModel to the data dependency graph.
[**uploadDataModel**](DataModelApi.md#uploadDataModel) | **POST** /dataModels/{dataModelId}/serialized-model | Upload a serialized data model to the TraDE middleware in order to make it available.


<a name="addDataModel"></a>
# **addDataModel**
> DataModel addDataModel(dataModelData)

Creates and adds a new data model to the TraDE middleware

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataModelApi;


DataModelApi apiInstance = new DataModelApi();
DataModelData dataModelData = new DataModelData(); // DataModelData | The name and creating entity of the data model that will be created.
try {
    DataModel result = apiInstance.addDataModel(dataModelData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataModelApi#addDataModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataModelData** | [**DataModelData**](DataModelData.md)| The name and creating entity of the data model that will be created. |

### Return type

[**DataModel**](DataModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="deleteDataModel"></a>
# **deleteDataModel**
> deleteDataModel(dataModelId)

Deletes a data model and all related resources.

Deletes a data model and all resources related to it from the TraDE middleware. This means that all data objects and their data elements defined through this data model as well as their instances will be deleted. Therefore, the deletion of a data model should be handled with care since it is one of the core artifacts used by the TraDE middleware.

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataModelApi;


DataModelApi apiInstance = new DataModelApi();
String dataModelId = "dataModelId_example"; // String | Id of the data model that needs to be fetched
try {
    apiInstance.deleteDataModel(dataModelId);
} catch (ApiException e) {
    System.err.println("Exception when calling DataModelApi#deleteDataModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataModelId** | **String**| Id of the data model that needs to be fetched |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="downloadDataModel"></a>
# **downloadDataModel**
> byte[] downloadDataModel(dataModelId)

Provides the serialized data model

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataModelApi;


DataModelApi apiInstance = new DataModelApi();
String dataModelId = "dataModelId_example"; // String | Id of the data model that needs to be fetched
try {
    byte[] result = apiInstance.downloadDataModel(dataModelId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataModelApi#downloadDataModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataModelId** | **String**| Id of the data model that needs to be fetched |

### Return type

**byte[]**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/octet-stream

<a name="getDataModel"></a>
# **getDataModel**
> DataModelWithLinks getDataModel(graphId)

Get the data model associated to the data dependency graph.

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataModelApi;


DataModelApi apiInstance = new DataModelApi();
String graphId = "graphId_example"; // String | Id of the data dependency graph that needs to be fetched
try {
    DataModelWithLinks result = apiInstance.getDataModel(graphId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataModelApi#getDataModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **graphId** | **String**| Id of the data dependency graph that needs to be fetched |

### Return type

[**DataModelWithLinks**](DataModelWithLinks.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getDataModelDirectly"></a>
# **getDataModelDirectly**
> DataModelWithLinks getDataModelDirectly(dataModelId)

Find a data model by Id

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataModelApi;


DataModelApi apiInstance = new DataModelApi();
String dataModelId = "dataModelId_example"; // String | Id of the data model that needs to be fetched
try {
    DataModelWithLinks result = apiInstance.getDataModelDirectly(dataModelId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataModelApi#getDataModelDirectly");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataModelId** | **String**| Id of the data model that needs to be fetched |

### Return type

[**DataModelWithLinks**](DataModelWithLinks.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getDataModels"></a>
# **getDataModels**
> DataModelArrayWithLinks getDataModels(start, size, name)



Gets all available &#x60;DataModel&#x60; resources. Optional query parameter of **start** and **size** enable pagination of the collection of data model resources and param **name** filters result list by name.

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataModelApi;


DataModelApi apiInstance = new DataModelApi();
Integer start = 1; // Integer | Start index of returned collection of resources for pagination.
Integer size = 50; // Integer | Size of the returned sub-collection of resources for pagination.
String name = "name_example"; // String | Name of data model to return
try {
    DataModelArrayWithLinks result = apiInstance.getDataModels(start, size, name);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataModelApi#getDataModels");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **start** | **Integer**| Start index of returned collection of resources for pagination. | [optional] [default to 1]
 **size** | **Integer**| Size of the returned sub-collection of resources for pagination. | [optional] [default to 50]
 **name** | **String**| Name of data model to return | [optional]

### Return type

[**DataModelArrayWithLinks**](DataModelArrayWithLinks.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="setDataModel"></a>
# **setDataModel**
> setDataModel(graphId, dataModel)

Associates an existing dataModel to the data dependency graph.

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataModelApi;


DataModelApi apiInstance = new DataModelApi();
String graphId = "graphId_example"; // String | Id of the data dependency graph that needs to be fetched
DataModel dataModel = new DataModel(); // DataModel | DataModel object that needs to be associated. In order to realize the association the provided data model should at least contain a valid Id of a known DataModel.
try {
    apiInstance.setDataModel(graphId, dataModel);
} catch (ApiException e) {
    System.err.println("Exception when calling DataModelApi#setDataModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **graphId** | **String**| Id of the data dependency graph that needs to be fetched |
 **dataModel** | [**DataModel**](DataModel.md)| DataModel object that needs to be associated. In order to realize the association the provided data model should at least contain a valid Id of a known DataModel. | [optional]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="uploadDataModel"></a>
# **uploadDataModel**
> uploadDataModel(dataModelId, contentLength, model)

Upload a serialized data model to the TraDE middleware in order to make it available.

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataModelApi;


DataModelApi apiInstance = new DataModelApi();
String dataModelId = "dataModelId_example"; // String | Id of the data model that needs to be fetched
Long contentLength = 789L; // Long | The size of the serialized data model passed as header
byte[] model = BINARY_DATA_HERE; // byte[] | The serialized data model to upload.
try {
    apiInstance.uploadDataModel(dataModelId, contentLength, model);
} catch (ApiException e) {
    System.err.println("Exception when calling DataModelApi#uploadDataModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataModelId** | **String**| Id of the data model that needs to be fetched |
 **contentLength** | **Long**| The size of the serialized data model passed as header |
 **model** | **byte[]**| The serialized data model to upload. |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/octet-stream
 - **Accept**: application/json

