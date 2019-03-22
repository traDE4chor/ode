package io.swagger.trade.client.jersey.api;

import io.swagger.trade.client.jersey.ApiException;
import io.swagger.trade.client.jersey.ApiClient;
import io.swagger.trade.client.jersey.Configuration;
import io.swagger.trade.client.jersey.Pair;

import javax.ws.rs.core.GenericType;

import io.swagger.trade.client.jersey.model.Error;
import io.swagger.trade.client.jersey.model.NotFound;
import io.swagger.trade.client.jersey.model.ResourceEventFilterDescriptionArray;
import io.swagger.trade.client.jersey.model.ResourceEventFilterDescriptionArrayWithLinks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-06-21T14:24:08.511+02:00")
public class ResourceEventFilterApi {
  private ApiClient apiClient;

  public ResourceEventFilterApi() {
    this(Configuration.getDefaultApiClient());
  }

  public ResourceEventFilterApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Find a resource event filter description by Id
   * Provides one or more available &#x60;resourceEventFilter&#x60; descriptions based on the provided parameters. Optional query parameter of **eventType** filters result list by the event types of even filter descriptions.
   * @param resourceEventFilterId Id of the resource event filter that needs to be fetched (required)
   * @param eventType Event type of resource event filter descriptions to return (optional)
   * @return ResourceEventFilterDescriptionArray
   * @throws ApiException if fails to make API call
   */
  public ResourceEventFilterDescriptionArray getResourceEventFilterDirectly(String resourceEventFilterId, String eventType) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'resourceEventFilterId' is set
    if (resourceEventFilterId == null) {
      throw new ApiException(400, "Missing the required parameter 'resourceEventFilterId' when calling getResourceEventFilterDirectly");
    }
    
    // create path and map variables
    String localVarPath = "/resourceEventFilters/{resourceEventFilterId}"
      .replaceAll("\\{" + "resourceEventFilterId" + "\\}", apiClient.escapeString(resourceEventFilterId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "eventType", eventType));

    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<ResourceEventFilterDescriptionArray> localVarReturnType = new GenericType<ResourceEventFilterDescriptionArray>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 
   * Gets all available &#x60;resourceEventFilter&#x60; resources. Optional query parameter of **start** and **size** enable pagination of the collection of resource event filter resources, param **eventType** filters result list by the event types of even filter descriptions.
   * @param start Start index of returned collection of resources for pagination. (optional, default to 1)
   * @param size Size of the returned sub-collection of resources for pagination. (optional, default to 50)
   * @param eventType Event type of resource event filter descriptions to return (optional)
   * @return ResourceEventFilterDescriptionArrayWithLinks
   * @throws ApiException if fails to make API call
   */
  public ResourceEventFilterDescriptionArrayWithLinks getResourceEventFilters(Integer start, Integer size, String eventType) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/resourceEventFilters";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "start", start));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "size", size));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "eventType", eventType));

    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<ResourceEventFilterDescriptionArrayWithLinks> localVarReturnType = new GenericType<ResourceEventFilterDescriptionArrayWithLinks>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}
