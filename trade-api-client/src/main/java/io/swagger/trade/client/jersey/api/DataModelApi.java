/*
 * TraDE Middleware API
 * This is the API of the TraDE middleware.
 *
 * OpenAPI spec version: 0.0.1
 * Contact: trade4chor@gmail.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.swagger.trade.client.jersey.api;

import io.swagger.trade.client.jersey.ApiClient;
import io.swagger.trade.client.jersey.ApiException;
import io.swagger.trade.client.jersey.Configuration;
import io.swagger.trade.client.jersey.Pair;
import io.swagger.trade.client.jersey.model.DataModel;
import io.swagger.trade.client.jersey.model.DataModelArrayWithLinks;
import io.swagger.trade.client.jersey.model.DataModelData;
import io.swagger.trade.client.jersey.model.DataModelWithLinks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.GenericType;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-04-05T17:17:13.418+02:00")
public class DataModelApi {
  private ApiClient apiClient;

  public DataModelApi() {
    this(Configuration.getDefaultApiClient());
  }

  public DataModelApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Creates and adds a new data model to the TraDE middleware
   * 
   * @param dataModelData The name and creating entity of the data model that will be created. (required)
   * @return DataModel
   * @throws ApiException if fails to make API call
   */
  public DataModel addDataModel(DataModelData dataModelData) throws ApiException {
    Object localVarPostBody = dataModelData;
    
    // verify the required parameter 'dataModelData' is set
    if (dataModelData == null) {
      throw new ApiException(400, "Missing the required parameter 'dataModelData' when calling addDataModel");
    }
    
    // create path and map variables
    String localVarPath = "/dataModels";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<DataModel> localVarReturnType = new GenericType<DataModel>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Deletes a data model and all related resources.
   * Deletes a data model and all resources related to it from the TraDE middleware. This means that all data objects and their data elements defined through this data model as well as their instances will be deleted. Therefore, the deletion of a data model should be handled with care since it is one of the core artifacts used by the TraDE middleware.
   * @param dataModelId Id of the data model that needs to be fetched (required)
   * @throws ApiException if fails to make API call
   */
  public void deleteDataModel(String dataModelId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'dataModelId' is set
    if (dataModelId == null) {
      throw new ApiException(400, "Missing the required parameter 'dataModelId' when calling deleteDataModel");
    }
    
    // create path and map variables
    String localVarPath = "/dataModels/{dataModelId}"
      .replaceAll("\\{" + "dataModelId" + "\\}", apiClient.escapeString(dataModelId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };


    apiClient.invokeAPI(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
  }
  /**
   * Provides the serialized data model
   * 
   * @param dataModelId Id of the data model that needs to be fetched (required)
   * @return byte[]
   * @throws ApiException if fails to make API call
   */
  public byte[] downloadDataModel(String dataModelId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'dataModelId' is set
    if (dataModelId == null) {
      throw new ApiException(400, "Missing the required parameter 'dataModelId' when calling downloadDataModel");
    }
    
    // create path and map variables
    String localVarPath = "/dataModels/{dataModelId}/serialized-model"
      .replaceAll("\\{" + "dataModelId" + "\\}", apiClient.escapeString(dataModelId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      "application/octet-stream", "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<byte[]> localVarReturnType = new GenericType<byte[]>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Get the data model associated to the data dependency graph.
   * 
   * @param graphId Id of the data dependency graph that needs to be fetched (required)
   * @return DataModelWithLinks
   * @throws ApiException if fails to make API call
   */
  public DataModelWithLinks getDataModel(String graphId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'graphId' is set
    if (graphId == null) {
      throw new ApiException(400, "Missing the required parameter 'graphId' when calling getDataModel");
    }
    
    // create path and map variables
    String localVarPath = "/dataDependencyGraphs/{graphId}/dataModel"
      .replaceAll("\\{" + "graphId" + "\\}", apiClient.escapeString(graphId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<DataModelWithLinks> localVarReturnType = new GenericType<DataModelWithLinks>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Find a data model by Id
   * 
   * @param dataModelId Id of the data model that needs to be fetched (required)
   * @return DataModelWithLinks
   * @throws ApiException if fails to make API call
   */
  public DataModelWithLinks getDataModelDirectly(String dataModelId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'dataModelId' is set
    if (dataModelId == null) {
      throw new ApiException(400, "Missing the required parameter 'dataModelId' when calling getDataModelDirectly");
    }
    
    // create path and map variables
    String localVarPath = "/dataModels/{dataModelId}"
      .replaceAll("\\{" + "dataModelId" + "\\}", apiClient.escapeString(dataModelId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<DataModelWithLinks> localVarReturnType = new GenericType<DataModelWithLinks>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 
   * Gets all available &#x60;DataModel&#x60; resources. Optional query parameter of **start** and **size** enable pagination of the collection of data model resources and param **targetNamespace**, **name** and **entity** filter result list by name, targetNamespace and entity.
   * @param start Start index of returned collection of resources for pagination. (optional, default to 1)
   * @param size Size of the returned sub-collection of resources for pagination. (optional, default to 50)
   * @param targetNamespace Target namespace of data model to return (optional)
   * @param name Name of data model to return (optional)
   * @param entity Creating entity of data dependency graph to return (optional)
   * @return DataModelArrayWithLinks
   * @throws ApiException if fails to make API call
   */
  public DataModelArrayWithLinks getDataModels(Integer start, Integer size, String targetNamespace, String name, String entity) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/dataModels";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "start", start));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "size", size));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "targetNamespace", targetNamespace));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "name", name));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "entity", entity));

    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<DataModelArrayWithLinks> localVarReturnType = new GenericType<DataModelArrayWithLinks>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Associates an existing dataModel to the data dependency graph.
   * 
   * @param graphId Id of the data dependency graph that needs to be fetched (required)
   * @param dataModelId The id of a data model that should be associated to this data dependency graph. (required)
   * @throws ApiException if fails to make API call
   */
  public void setDataModel(String graphId, String dataModelId) throws ApiException {
    Object localVarPostBody = dataModelId;
    
    // verify the required parameter 'graphId' is set
    if (graphId == null) {
      throw new ApiException(400, "Missing the required parameter 'graphId' when calling setDataModel");
    }
    
    // verify the required parameter 'dataModelId' is set
    if (dataModelId == null) {
      throw new ApiException(400, "Missing the required parameter 'dataModelId' when calling setDataModel");
    }
    
    // create path and map variables
    String localVarPath = "/dataDependencyGraphs/{graphId}/dataModel"
      .replaceAll("\\{" + "graphId" + "\\}", apiClient.escapeString(graphId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };


    apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
  }
  /**
   * Upload a serialized data model to the TraDE middleware in order to make it available.
   * 
   * @param dataModelId Id of the data model that needs to be fetched (required)
   * @param contentLength The size of the serialized data model passed as header (required)
   * @param model The serialized data model to upload. (required)
   * @throws ApiException if fails to make API call
   */
  public void uploadDataModel(String dataModelId, Long contentLength, byte[] model) throws ApiException {
    Object localVarPostBody = model;
    
    // verify the required parameter 'dataModelId' is set
    if (dataModelId == null) {
      throw new ApiException(400, "Missing the required parameter 'dataModelId' when calling uploadDataModel");
    }
    
    // verify the required parameter 'contentLength' is set
    if (contentLength == null) {
      throw new ApiException(400, "Missing the required parameter 'contentLength' when calling uploadDataModel");
    }
    
    // verify the required parameter 'model' is set
    if (model == null) {
      throw new ApiException(400, "Missing the required parameter 'model' when calling uploadDataModel");
    }
    
    // create path and map variables
    String localVarPath = "/dataModels/{dataModelId}/serialized-model"
      .replaceAll("\\{" + "dataModelId" + "\\}", apiClient.escapeString(dataModelId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    if (contentLength != null)
      localVarHeaderParams.put("Content-Length", apiClient.parameterToString(contentLength));

    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/octet-stream"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };


    apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
  }
}
