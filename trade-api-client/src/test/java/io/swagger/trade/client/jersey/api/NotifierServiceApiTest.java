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
 */


package io.swagger.trade.client.jersey.api;

import io.swagger.trade.client.jersey.ApiException;
import io.swagger.trade.client.jersey.model.Error;
import io.swagger.trade.client.jersey.model.NotFound;
import io.swagger.trade.client.jersey.model.NotifierService;
import io.swagger.trade.client.jersey.model.NotifierServiceArrayWithLinks;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for NotifierServiceApi
 */
@Ignore
public class NotifierServiceApiTest {

    private final NotifierServiceApi api = new NotifierServiceApi();

    
    /**
     * Find a notifier service by Id
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getNotifierServiceDirectlyTest() throws ApiException {
        String notifierServiceId = null;
        NotifierService response = api.getNotifierServiceDirectly(notifierServiceId);

        // TODO: test validations
    }
    
    /**
     * Get the associated notifier service of this notification
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getNotifierServiceOfNotificationTest() throws ApiException {
        String notificationId = null;
        NotifierService response = api.getNotifierServiceOfNotification(notificationId);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * Gets all available &#x60;NotifierService&#x60; resources. Optional query parameter of **start** and **size** enable pagination of the collection of notifier service resources.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getNotifierServicesTest() throws ApiException {
        Integer start = null;
        Integer size = null;
        NotifierServiceArrayWithLinks response = api.getNotifierServices(start, size);

        // TODO: test validations
    }
    
}
