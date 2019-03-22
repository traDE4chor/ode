package io.swagger.trade.client.jersey.api;

import io.swagger.trade.client.jersey.ApiException;
import io.swagger.trade.client.jersey.ApiClient;
import io.swagger.trade.client.jersey.Configuration;
import io.swagger.trade.client.jersey.Pair;

import javax.ws.rs.core.GenericType;

import io.swagger.trade.client.jersey.model.Error;
import io.swagger.trade.client.jersey.model.NotFound;
import io.swagger.trade.client.jersey.model.NotifierService;
import io.swagger.trade.client.jersey.model.NotifierServiceArrayWithLinks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-06-21T14:24:08.511+02:00")
public class NotifierServiceApi {
  private ApiClient apiClient;

  public NotifierServiceApi() {
    this(Configuration.getDefaultApiClient());
  }

  public NotifierServiceApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Find a notifier service by Id
   * 
   * @param notifierServiceId Id of the notifier service that needs to be fetched (required)
   * @return NotifierService
   * @throws ApiException if fails to make API call
   */
  public NotifierService getNotifierServiceDirectly(String notifierServiceId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'notifierServiceId' is set
    if (notifierServiceId == null) {
      throw new ApiException(400, "Missing the required parameter 'notifierServiceId' when calling getNotifierServiceDirectly");
    }
    
    // create path and map variables
    String localVarPath = "/notifierServices/{notifierServiceId}"
      .replaceAll("\\{" + "notifierServiceId" + "\\}", apiClient.escapeString(notifierServiceId.toString()));

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

    GenericType<NotifierService> localVarReturnType = new GenericType<NotifierService>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Get the associated notifier service of this notification
   * 
   * @param notificationId Id of the notification for which the notifier service should to be fetched (required)
   * @return NotifierService
   * @throws ApiException if fails to make API call
   */
  public NotifierService getNotifierServiceOfNotification(String notificationId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'notificationId' is set
    if (notificationId == null) {
      throw new ApiException(400, "Missing the required parameter 'notificationId' when calling getNotifierServiceOfNotification");
    }
    
    // create path and map variables
    String localVarPath = "/notifications/{notificationId}/notifierService"
      .replaceAll("\\{" + "notificationId" + "\\}", apiClient.escapeString(notificationId.toString()));

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

    GenericType<NotifierService> localVarReturnType = new GenericType<NotifierService>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 
   * Gets all available &#x60;NotifierService&#x60; resources. Optional query parameter of **start** and **size** enable pagination of the collection of notifier service resources.
   * @param start Start index of returned collection of resources for pagination. (optional, default to 1)
   * @param size Size of the returned sub-collection of resources for pagination. (optional, default to 50)
   * @return NotifierServiceArrayWithLinks
   * @throws ApiException if fails to make API call
   */
  public NotifierServiceArrayWithLinks getNotifierServices(Integer start, Integer size) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/notifierServices";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "start", start));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "size", size));

    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<NotifierServiceArrayWithLinks> localVarReturnType = new GenericType<NotifierServiceArrayWithLinks>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}
