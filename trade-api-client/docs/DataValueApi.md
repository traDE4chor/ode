# DataValueApi

All URIs are relative to *https://localhost:8081/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addDataValue**](DataValueApi.md#addDataValue) | **POST** /dataValues | Creates and adds a new data value to the TraDE middleware
[**deleteDataValue**](DataValueApi.md#deleteDataValue) | **DELETE** /dataValues/{dataValueId} | Deletes a data value
[**getDataValue**](DataValueApi.md#getDataValue) | **GET** /dataElementInstances/{instanceId}/dataValue | Get the data value associated to the data element instance.
[**getDataValueDirectly**](DataValueApi.md#getDataValueDirectly) | **GET** /dataValues/{dataValueId} | Find a data value by Id
[**getDataValuesDirectly**](DataValueApi.md#getDataValuesDirectly) | **GET** /dataValues | 
[**pullDataValue**](DataValueApi.md#pullDataValue) | **GET** /dataValues/{dataValueId}/data | Pulls data from the data value identified by Id
[**pushDataValue**](DataValueApi.md#pushDataValue) | **POST** /dataValues/{dataValueId}/data | Pushes data to the TraDE middleware by attaching it to the data value identified by Id
[**setDataValue**](DataValueApi.md#setDataValue) | **POST** /dataElementInstances/{instanceId}/dataValue | Creates a new or associates an existing dataValue to the data element instance.
[**updateDataValueDirectly**](DataValueApi.md#updateDataValueDirectly) | **PUT** /dataValues/{dataValueId} | Update an existing data value


<a name="addDataValue"></a>
# **addDataValue**
> DataValue addDataValue(dataValueData)

Creates and adds a new data value to the TraDE middleware

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataValueApi;


DataValueApi apiInstance = new DataValueApi();
DataValueData dataValueData = new DataValueData(); // DataValueData | The name, type and who created the data value are required. The specification of a contentType is only required if the data type is binary.
try {
    DataValue result = apiInstance.addDataValue(dataValueData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataValueApi#addDataValue");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataValueData** | [**DataValueData**](DataValueData.md)| The name, type and who created the data value are required. The specification of a contentType is only required if the data type is binary. |

### Return type

[**DataValue**](DataValue.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="deleteDataValue"></a>
# **deleteDataValue**
> deleteDataValue(dataValueId)

Deletes a data value

Deletes a data value and its associated data from the TraDE middleware. All references from data element instances are invalidated accordingly.

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataValueApi;


DataValueApi apiInstance = new DataValueApi();
String dataValueId = "dataValueId_example"; // String | Id of the data value that needs to be fetched
try {
    apiInstance.deleteDataValue(dataValueId);
} catch (ApiException e) {
    System.err.println("Exception when calling DataValueApi#deleteDataValue");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataValueId** | **String**| Id of the data value that needs to be fetched |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getDataValue"></a>
# **getDataValue**
> DataValueWithLinks getDataValue(instanceId)

Get the data value associated to the data element instance.

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataValueApi;


DataValueApi apiInstance = new DataValueApi();
String instanceId = "instanceId_example"; // String | Id of the data element instance that needs to be fetched
try {
    DataValueWithLinks result = apiInstance.getDataValue(instanceId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataValueApi#getDataValue");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instanceId** | **String**| Id of the data element instance that needs to be fetched |

### Return type

[**DataValueWithLinks**](DataValueWithLinks.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getDataValueDirectly"></a>
# **getDataValueDirectly**
> DataValueWithLinks getDataValueDirectly(dataValueId)

Find a data value by Id

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataValueApi;


DataValueApi apiInstance = new DataValueApi();
String dataValueId = "dataValueId_example"; // String | Id of the data value that needs to be fetched
try {
    DataValueWithLinks result = apiInstance.getDataValueDirectly(dataValueId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataValueApi#getDataValueDirectly");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataValueId** | **String**| Id of the data value that needs to be fetched |

### Return type

[**DataValueWithLinks**](DataValueWithLinks.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getDataValuesDirectly"></a>
# **getDataValuesDirectly**
> DataValueArrayWithLinks getDataValuesDirectly(start, size, status, createdBy)



Gets all available &#x60;DataValue&#x60; resources. Optional query parameter of **start** and **size** enable pagination of the collection of data value resources, param **createdBy** filters result list by the creator and param **status** filters result list by status of the data values.

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataValueApi;


DataValueApi apiInstance = new DataValueApi();
Integer start = 1; // Integer | Start index of returned collection of resources for pagination.
Integer size = 50; // Integer | Size of the returned sub-collection of resources for pagination.
String status = "status_example"; // String | Status of data values to return
String createdBy = "createdBy_example"; // String | Name of the data element to which instance a data value belongs
try {
    DataValueArrayWithLinks result = apiInstance.getDataValuesDirectly(start, size, status, createdBy);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataValueApi#getDataValuesDirectly");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **start** | **Integer**| Start index of returned collection of resources for pagination. | [optional] [default to 1]
 **size** | **Integer**| Size of the returned sub-collection of resources for pagination. | [optional] [default to 50]
 **status** | **String**| Status of data values to return | [optional]
 **createdBy** | **String**| Name of the data element to which instance a data value belongs | [optional]

### Return type

[**DataValueArrayWithLinks**](DataValueArrayWithLinks.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="pullDataValue"></a>
# **pullDataValue**
> byte[] pullDataValue(dataValueId)

Pulls data from the data value identified by Id

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataValueApi;


DataValueApi apiInstance = new DataValueApi();
String dataValueId = "dataValueId_example"; // String | Id of the data value that needs to be fetched
try {
    byte[] result = apiInstance.pullDataValue(dataValueId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataValueApi#pullDataValue");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataValueId** | **String**| Id of the data value that needs to be fetched |

### Return type

**byte[]**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/octet-stream

<a name="pushDataValue"></a>
# **pushDataValue**
> pushDataValue(dataValueId, contentLength, data)

Pushes data to the TraDE middleware by attaching it to the data value identified by Id

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataValueApi;


DataValueApi apiInstance = new DataValueApi();
String dataValueId = "dataValueId_example"; // String | Id of the data value to attach data to
Long contentLength = 789L; // Long | The size of the data passed as header
byte[] data = BINARY_DATA_HERE; // byte[] | The data to push.
try {
    apiInstance.pushDataValue(dataValueId, contentLength, data);
} catch (ApiException e) {
    System.err.println("Exception when calling DataValueApi#pushDataValue");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataValueId** | **String**| Id of the data value to attach data to |
 **contentLength** | **Long**| The size of the data passed as header |
 **data** | **byte[]**| The data to push. |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/octet-stream
 - **Accept**: application/json

<a name="setDataValue"></a>
# **setDataValue**
> DataValueWithLinks setDataValue(instanceId, dataValueData)

Creates a new or associates an existing dataValue to the data element instance.

### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataValueApi;


DataValueApi apiInstance = new DataValueApi();
String instanceId = "instanceId_example"; // String | Id of the data element instance that needs to be fetched
DataValue dataValueData = new DataValue(); // DataValue | DataValue object that needs to be created or associated. In case of updating the associated data value providing the Id is enough. To create and associate a new DataValue, a name, type and who created the data value are required. The specification of a contentType is only required if the data type of the data value is binary.
try {
    DataValueWithLinks result = apiInstance.setDataValue(instanceId, dataValueData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataValueApi#setDataValue");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instanceId** | **String**| Id of the data element instance that needs to be fetched |
 **dataValueData** | [**DataValue**](DataValue.md)| DataValue object that needs to be created or associated. In case of updating the associated data value providing the Id is enough. To create and associate a new DataValue, a name, type and who created the data value are required. The specification of a contentType is only required if the data type of the data value is binary. | [optional]

### Return type

[**DataValueWithLinks**](DataValueWithLinks.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateDataValueDirectly"></a>
# **updateDataValueDirectly**
> DataValueWithLinks updateDataValueDirectly(dataValueId, dataValue)

Update an existing data value



### Example
```java
// Import classes:
//import io.swagger.trade.client.jersey.ApiException;
//import io.swagger.trade.client.jersey.api.DataValueApi;


DataValueApi apiInstance = new DataValueApi();
String dataValueId = "dataValueId_example"; // String | Id of the data value that needs to be fetched
DataValue dataValue = new DataValue(); // DataValue | The updated data value resource. Only changes to the following fields are reflected **name**, **type** and **contentType**. All other changes are ignored since the corresponding fields of the resource are internally set by the server or are immutable.
try {
    DataValueWithLinks result = apiInstance.updateDataValueDirectly(dataValueId, dataValue);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataValueApi#updateDataValueDirectly");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataValueId** | **String**| Id of the data value that needs to be fetched |
 **dataValue** | [**DataValue**](DataValue.md)| The updated data value resource. Only changes to the following fields are reflected **name**, **type** and **contentType**. All other changes are ignored since the corresponding fields of the resource are internally set by the server or are immutable. |

### Return type

[**DataValueWithLinks**](DataValueWithLinks.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

