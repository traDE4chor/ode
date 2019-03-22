package io.swagger.trade.client.jersey.api;

import io.swagger.trade.client.jersey.ApiException;
import io.swagger.trade.client.jersey.ApiClient;
import io.swagger.trade.client.jersey.Configuration;
import io.swagger.trade.client.jersey.Pair;

import javax.ws.rs.core.GenericType;

import io.swagger.trade.client.jersey.model.Error;
import io.swagger.trade.client.jersey.model.InvalidInput;
import io.swagger.trade.client.jersey.model.NotFound;
import io.swagger.trade.client.jersey.model.Notification;
import io.swagger.trade.client.jersey.model.NotificationArrayWithLinks;
import io.swagger.trade.client.jersey.model.NotificationData;
import io.swagger.trade.client.jersey.model.NotificationWithLinks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-06-21T14:24:08.511+02:00")
public class NotificationApi {
  private ApiClient apiClient;

  public NotificationApi() {
    this(Configuration.getDefaultApiClient());
  }

  public NotificationApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Creates and adds a new notification to the TraDE middleware
   * 
   * @param notificationData A name, notifier service id and an array of corresponding notifier parameter values are required. Furthermore, the id of the resource to observe, i.e., the resource for which this notification is registered or an array of resource filtes has to be provided. The resource filters allow to specify filter criteria which is evaluated in order to decide if a notification is triggered as a result of an event (e.g., state of a resource changed) or not. (required)
   * @return Notification
   * @throws ApiException if fails to make API call
   */
  public Notification addNotification(NotificationData notificationData) throws ApiException {
    Object localVarPostBody = notificationData;
    
    // verify the required parameter 'notificationData' is set
    if (notificationData == null) {
      throw new ApiException(400, "Missing the required parameter 'notificationData' when calling addNotification");
    }
    
    // create path and map variables
    String localVarPath = "/notifications";

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

    GenericType<Notification> localVarReturnType = new GenericType<Notification>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Deletes a notification
   * Deletes a notification from the TraDE middleware.
   * @param notificationId Id of the notification that needs to be fetched (required)
   * @throws ApiException if fails to make API call
   */
  public void deleteNotification(String notificationId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'notificationId' is set
    if (notificationId == null) {
      throw new ApiException(400, "Missing the required parameter 'notificationId' when calling deleteNotification");
    }
    
    // create path and map variables
    String localVarPath = "/notifications/{notificationId}"
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


    apiClient.invokeAPI(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
  }
  /**
   * Find a notification by Id
   * 
   * @param notificationId Id of the notification that needs to be fetched (required)
   * @return NotificationWithLinks
   * @throws ApiException if fails to make API call
   */
  public NotificationWithLinks getNotificationDirectly(String notificationId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'notificationId' is set
    if (notificationId == null) {
      throw new ApiException(400, "Missing the required parameter 'notificationId' when calling getNotificationDirectly");
    }
    
    // create path and map variables
    String localVarPath = "/notifications/{notificationId}"
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

    GenericType<NotificationWithLinks> localVarReturnType = new GenericType<NotificationWithLinks>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 
   * Gets all available &#x60;Notification&#x60; resources. Optional query parameter of **start** and **size** enable pagination of the collection of notification resources, param **name** filters result list by the name and param **notifierServiceId** filters result list by the associated notifier service of the notification.
   * @param start Start index of returned collection of resources for pagination. (optional, default to 1)
   * @param size Size of the returned sub-collection of resources for pagination. (optional, default to 50)
   * @param name Name of notifications to return (optional)
   * @param notifierServiceId The id of the notifier service used by a notification (optional)
   * @return NotificationArrayWithLinks
   * @throws ApiException if fails to make API call
   */
  public NotificationArrayWithLinks getNotifications(Integer start, Integer size, String name, String notifierServiceId) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/notifications";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "start", start));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "size", size));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "name", name));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "notifierServiceId", notifierServiceId));

    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<NotificationArrayWithLinks> localVarReturnType = new GenericType<NotificationArrayWithLinks>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Update an existing notification
   * 
   * @param notificationId Id of the notification that needs to be fetched (required)
   * @param notification The updated notification resource. Changes to the fields **id** and **href** are ignored since the corresponding fields of the resource are internally set by the server. (required)
   * @return NotificationWithLinks
   * @throws ApiException if fails to make API call
   */
  public NotificationWithLinks updateNotificationDirectly(String notificationId, Notification notification) throws ApiException {
    Object localVarPostBody = notification;
    
    // verify the required parameter 'notificationId' is set
    if (notificationId == null) {
      throw new ApiException(400, "Missing the required parameter 'notificationId' when calling updateNotificationDirectly");
    }
    
    // verify the required parameter 'notification' is set
    if (notification == null) {
      throw new ApiException(400, "Missing the required parameter 'notification' when calling updateNotificationDirectly");
    }
    
    // create path and map variables
    String localVarPath = "/notifications/{notificationId}"
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

    GenericType<NotificationWithLinks> localVarReturnType = new GenericType<NotificationWithLinks>() {};
    return apiClient.invokeAPI(localVarPath, "PUT", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}
