# swagger-java-client

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>io.swagger</groupId>
    <artifactId>swagger-java-client</artifactId>
    <version>1.0.0</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "io.swagger:swagger-java-client:1.0.0"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/swagger-java-client-1.0.0.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import io.swagger.trade.client.jersey.*;
import io.swagger.trade.client.jersey.auth.*;
import io.swagger.trade.client.jersey.model.*;
import io.swagger.trade.client.jersey.api.DataElementApi;

import java.io.File;
import java.util.*;

public class DataElementApiExample {

    public static void main(String[] args) {
        
        DataElementApi apiInstance = new DataElementApi();
        String dataObjectId = "dataObjectId_example"; // String | Id of the data object that needs to be fetched
        DataElement body = new DataElement(); // DataElement | DataElement object that needs to be added.
        try {
            DataElement result = apiInstance.addDataElement(dataObjectId, body);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DataElementApi#addDataElement");
            e.printStackTrace();
        }
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *https://localhost:8080/api*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*DataElementApi* | [**addDataElement**](docs/DataElementApi.md#addDataElement) | **POST** /dataObjects/{dataObjectId}/dataElements | Add a new dataElement to the dataObject
*DataElementApi* | [**deleteDataElement**](docs/DataElementApi.md#deleteDataElement) | **DELETE** /dataObjects/{dataObjectId}/dataElements/{dataElementId} | Deletes a data element
*DataElementApi* | [**getDataElement**](docs/DataElementApi.md#getDataElement) | **GET** /dataObjects/{dataObjectId}/dataElements/{dataElementId} | Find a data element by Id
*DataElementApi* | [**getDataElementDirectly**](docs/DataElementApi.md#getDataElementDirectly) | **GET** /dataElements/{dataElementId} | Find a data element by Id
*DataElementApi* | [**getDataElements**](docs/DataElementApi.md#getDataElements) | **GET** /dataObjects/{dataObjectId}/dataElements | 
*DataElementApi* | [**getDataElementsDirectly**](docs/DataElementApi.md#getDataElementsDirectly) | **GET** /dataElements | 
*DataElementApi* | [**pullDataElementData**](docs/DataElementApi.md#pullDataElementData) | **GET** /dataElements/{dataElementId}/pull | Pulls data from the data value associated to the data element identified by Id
*DataElementApi* | [**pushDataElementData**](docs/DataElementApi.md#pushDataElementData) | **POST** /dataElements/{dataElementId}/push | Pushes data to the TraDE middleware by attaching a new data value holding the provided data to the data element
*DataElementApi* | [**updateDataElement**](docs/DataElementApi.md#updateDataElement) | **PUT** /dataObjects/{dataObjectId}/dataElements/{dataElementId} | Update an existing data element
*DataElementApi* | [**updateDataElementDirectly**](docs/DataElementApi.md#updateDataElementDirectly) | **PUT** /dataElements/{dataElementId} | Update an existing data element
*DataObjectApi* | [**addDataObject**](docs/DataObjectApi.md#addDataObject) | **POST** /dataObjects | Creates and adds a new data object to the TraDE middleware
*DataObjectApi* | [**deleteDataObject**](docs/DataObjectApi.md#deleteDataObject) | **DELETE** /dataObjects/{dataObjectId} | Deletes a dataObject
*DataObjectApi* | [**getDataObject**](docs/DataObjectApi.md#getDataObject) | **GET** /dataObjects | 
*DataObjectApi* | [**getDataObjectById**](docs/DataObjectApi.md#getDataObjectById) | **GET** /dataObjects/{dataObjectId} | Find a data object by Id
*DataObjectApi* | [**pullDataObjectData**](docs/DataObjectApi.md#pullDataObjectData) | **GET** /dataObjects/{dataObjectId}/pull | Reads data from a data object identified by Id
*DataObjectApi* | [**pushDataObjectData**](docs/DataObjectApi.md#pushDataObjectData) | **POST** /dataObjects/{dataObjectId}/push | Pushes the data object identified by Id to the TraDE middleware
*DataObjectApi* | [**updateDataObject**](docs/DataObjectApi.md#updateDataObject) | **PUT** /dataObjects/{dataObjectId} | Update an existing dataObject
*DataValueApi* | [**addDataValue**](docs/DataValueApi.md#addDataValue) | **POST** /dataValues | Creates and adds a new data value to the TraDE middleware
*DataValueApi* | [**deleteDataValue**](docs/DataValueApi.md#deleteDataValue) | **DELETE** /dataValues/{dataValueId} | Deletes a data value
*DataValueApi* | [**getDataValueDirectly**](docs/DataValueApi.md#getDataValueDirectly) | **GET** /dataValues/{dataValueId} | Find a data value by Id
*DataValueApi* | [**getDataValuesDirectly**](docs/DataValueApi.md#getDataValuesDirectly) | **GET** /dataValues | 
*DataValueApi* | [**pullDataValue**](docs/DataValueApi.md#pullDataValue) | **GET** /dataValues/{dataValueId}/pull | Pulls data from the data value identified by Id
*DataValueApi* | [**pushDataValue**](docs/DataValueApi.md#pushDataValue) | **POST** /dataValues/{dataValueId}/push | Pushes data to the TraDE middleware by attaching it to the data value identified by Id
*DataValueApi* | [**updateDataValueDirectly**](docs/DataValueApi.md#updateDataValueDirectly) | **PUT** /dataValues/{dataValueId} | Update an existing data value
*NetworkApi* | [**addNetwork**](docs/NetworkApi.md#addNetwork) | **POST** /networks | Creates and adds a new network to the TraDE middleware
*NetworkApi* | [**deleteNetwork**](docs/NetworkApi.md#deleteNetwork) | **DELETE** /networks/{networkId} | Deletes a network
*NetworkApi* | [**getNetwork**](docs/NetworkApi.md#getNetwork) | **GET** /networks | 
*NetworkApi* | [**getNetworkById**](docs/NetworkApi.md#getNetworkById) | **GET** /networks/{networkId} | Find a network by Id
*NetworkApi* | [**updateNetwork**](docs/NetworkApi.md#updateNetwork) | **PUT** /networks/{networkId} | Update an existing network
*NodeApi* | [**addNode**](docs/NodeApi.md#addNode) | **POST** /networks/{networkId}/nodes | Add a new node to the network
*NodeApi* | [**deleteNode**](docs/NodeApi.md#deleteNode) | **DELETE** /networks/{networkId}/nodes/{nodeId} | Deletes a node
*NodeApi* | [**getNode**](docs/NodeApi.md#getNode) | **GET** /networks/{networkId}/nodes/{nodeId} | Find a node by Id
*NodeApi* | [**getNodes**](docs/NodeApi.md#getNodes) | **GET** /networks/{networkId}/nodes | 
*NodeApi* | [**updateNode**](docs/NodeApi.md#updateNode) | **PUT** /networks/{networkId}/nodes/{nodeId} | Update an existing node


## Documentation for Models

 - [DataElement](docs/DataElement.md)
 - [DataElementInstance](docs/DataElementInstance.md)
 - [DataObject](docs/DataObject.md)
 - [DataObjectInstance](docs/DataObjectInstance.md)
 - [DataValue](docs/DataValue.md)
 - [DataValueRequest](docs/DataValueRequest.md)
 - [DataValueUpdateRequest](docs/DataValueUpdateRequest.md)
 - [Error](docs/Error.md)
 - [InstanceStatusEnum](docs/InstanceStatusEnum.md)
 - [InvalidInput](docs/InvalidInput.md)
 - [Network](docs/Network.md)
 - [Node](docs/Node.md)
 - [NotFound](docs/NotFound.md)
 - [StatusEnum](docs/StatusEnum.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issue.

## Author

trade4chor@gmail.com

