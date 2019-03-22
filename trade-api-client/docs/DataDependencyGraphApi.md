# DataDependencyGraphApi

All URIs are relative to *https://localhost:8081/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addDataDependencyGraph**](DataDependencyGraphApi.md#addDataDependencyGraph) | **POST** /dataDependencyGraphs | Creates and adds a new data dependency graph to the TraDE middleware
[**deleteDataDependencyGraph**](DataDependencyGraphApi.md#deleteDataDependencyGraph) | **DELETE** /dataDependencyGraphs/{graphId} | Deletes a data dependency graph.
[**downloadGraphModel**](DataDependencyGraphApi.md#downloadGraphModel) | **GET** /dataDependencyGraphs/{graphId}/serialized-graph | Provides the serialized graph model of the data dependency graph
[**getDataDependencyGraphDirectly**](DataDependencyGraphApi.md#getDataDependencyGraphDirectly) | **GET** /dataDependencyGraphs/{graphId} | Find a data dependency graph by Id
[**getDataDependencyGraphs**](DataDependencyGraphApi.md#getDataDependencyGraphs) | **GET** /dataDependencyGraphs | 
[**uploadGraphModel**](DataDependencyGraphApi.md#uploadGraphModel) | **POST** /dataDependencyGraphs/{graphId}/serialized-graph | Upload a serialized graph model to the TraDE middleware in order to make it available.


<a name="addDataDependencyGraph"></a>
# **addDataDependencyGraph**
> DataDependencyGraphWithLinks addDataDependencyGraph(dataDependencyGraphData)

Creates and adds a new data dependency graph to the TraDE middleware

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataDependencyGraphApi;


DataDependencyGraphApi apiInstance = new DataDependencyGraphApi();
DataDependencyGraphData dataDependencyGraphData = new DataDependencyGraphData(); // DataDependencyGraphData | The name and creating entity of the data dependency graph that will be created.
try {
    DataDependencyGraphWithLinks result = apiInstance.addDataDependencyGraph(dataDependencyGraphData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataDependencyGraphApi#addDataDependencyGraph");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataDependencyGraphData** | [**DataDependencyGraphData**](DataDependencyGraphData.md)| The name and creating entity of the data dependency graph that will be created. |

### Return type

[**DataDependencyGraphWithLinks**](DataDependencyGraphWithLinks.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="deleteDataDependencyGraph"></a>
# **deleteDataDependencyGraph**
> deleteDataDependencyGraph(graphId)

Deletes a data dependency graph.

Deletes a data dependency graph from the TraDE middleware.

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataDependencyGraphApi;


DataDependencyGraphApi apiInstance = new DataDependencyGraphApi();
String graphId = "graphId_example"; // String | Id of the data dependency graph that needs to be fetched
try {
    apiInstance.deleteDataDependencyGraph(graphId);
} catch (ApiException e) {
    System.err.println("Exception when calling DataDependencyGraphApi#deleteDataDependencyGraph");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **graphId** | **String**| Id of the data dependency graph that needs to be fetched |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="downloadGraphModel"></a>
# **downloadGraphModel**
> byte[] downloadGraphModel(graphId)

Provides the serialized graph model of the data dependency graph

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataDependencyGraphApi;


DataDependencyGraphApi apiInstance = new DataDependencyGraphApi();
String graphId = "graphId_example"; // String | Id of the data dependency graph that needs to be fetched
try {
    byte[] result = apiInstance.downloadGraphModel(graphId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataDependencyGraphApi#downloadGraphModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **graphId** | **String**| Id of the data dependency graph that needs to be fetched |

### Return type

**byte[]**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/octet-stream

<a name="getDataDependencyGraphDirectly"></a>
# **getDataDependencyGraphDirectly**
> DataDependencyGraphWithLinks getDataDependencyGraphDirectly(graphId)

Find a data dependency graph by Id

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataDependencyGraphApi;


DataDependencyGraphApi apiInstance = new DataDependencyGraphApi();
String graphId = "graphId_example"; // String | Id of the data dependency graph that needs to be fetched
try {
    DataDependencyGraphWithLinks result = apiInstance.getDataDependencyGraphDirectly(graphId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataDependencyGraphApi#getDataDependencyGraphDirectly");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **graphId** | **String**| Id of the data dependency graph that needs to be fetched |

### Return type

[**DataDependencyGraphWithLinks**](DataDependencyGraphWithLinks.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getDataDependencyGraphs"></a>
# **getDataDependencyGraphs**
> DataDependencyGraphArrayWithLinks getDataDependencyGraphs(start, size, name)



Gets all available &#x60;DataDependencyGraph&#x60; resources. Optional query parameter of **start** and **size** enable pagination of the collection of data dependency graph resources and param **name** filters result list by name.

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataDependencyGraphApi;


DataDependencyGraphApi apiInstance = new DataDependencyGraphApi();
Integer start = 1; // Integer | Start index of returned collection of resources for pagination.
Integer size = 50; // Integer | Size of the returned sub-collection of resources for pagination.
String name = "name_example"; // String | Name of data dependency graph to return
try {
    DataDependencyGraphArrayWithLinks result = apiInstance.getDataDependencyGraphs(start, size, name);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataDependencyGraphApi#getDataDependencyGraphs");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **start** | **Integer**| Start index of returned collection of resources for pagination. | [optional] [default to 1]
 **size** | **Integer**| Size of the returned sub-collection of resources for pagination. | [optional] [default to 50]
 **name** | **String**| Name of data dependency graph to return | [optional]

### Return type

[**DataDependencyGraphArrayWithLinks**](DataDependencyGraphArrayWithLinks.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="uploadGraphModel"></a>
# **uploadGraphModel**
> uploadGraphModel(graphId, contentLength, graph)

Upload a serialized graph model to the TraDE middleware in order to make it available.

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataDependencyGraphApi;


DataDependencyGraphApi apiInstance = new DataDependencyGraphApi();
String graphId = "graphId_example"; // String | Id of the data dependency graph that needs to be fetched
Long contentLength = 789L; // Long | The size of the serialized graph model passed as header
byte[] graph = BINARY_DATA_HERE; // byte[] | The serialized graph model to upload.
try {
    apiInstance.uploadGraphModel(graphId, contentLength, graph);
} catch (ApiException e) {
    System.err.println("Exception when calling DataDependencyGraphApi#uploadGraphModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **graphId** | **String**| Id of the data dependency graph that needs to be fetched |
 **contentLength** | **Long**| The size of the serialized graph model passed as header |
 **graph** | **byte[]**| The serialized graph model to upload. |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/octet-stream
 - **Accept**: application/json

