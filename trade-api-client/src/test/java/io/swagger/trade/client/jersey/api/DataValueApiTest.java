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
import io.swagger.trade.client.jersey.model.DataValue;
import io.swagger.trade.client.jersey.model.DataValueArrayWithLinks;
import io.swagger.trade.client.jersey.model.DataValueData;
import io.swagger.trade.client.jersey.model.DataValueWithLinks;

import org.junit.Ignore;
import org.junit.Test;

/**
 * API tests for DataValueApi
 */
@Ignore
public class DataValueApiTest {

    private final DataValueApi api = new DataValueApi();

    
    /**
     * Creates and adds a new data value to the TraDE middleware
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addDataValueTest() throws ApiException {
        DataValueData dataValueData = null;
        DataValue response = api.addDataValue(dataValueData);

        // TODO: test validations
    }
    
    /**
     * Creates a new or associates an existing dataValue to the data element instance.
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void associateDataValueToDataElementInstanceTest() throws ApiException {
        String instanceId = null;
        DataValue dataValueData = null;
        DataValueWithLinks response = api.associateDataValueToDataElementInstance(instanceId, dataValueData);

        // TODO: test validations
    }
    
    /**
     * Deletes a data value
     *
     * Deletes a data value and its associated data from the TraDE middleware. All references from data element instances are invalidated accordingly.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteDataValueTest() throws ApiException {
        String dataValueId = null;
        api.deleteDataValue(dataValueId);

        // TODO: test validations
    }
    
    /**
     * Find a data value by Id
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getDataValueDirectlyTest() throws ApiException {
        String dataValueId = null;
        DataValueWithLinks response = api.getDataValueDirectly(dataValueId);

        // TODO: test validations
    }
    
    /**
     * Get the data value(s) associated to the data element instance.
     *
     * Returns the complete list of &#x60;DataValue&#x60; resources associated to a &#x60;DataElementInstance&#x60; resource. With the optional query parameter **indexOfDataValue** one specific &#x60;DataValue&#x60; resource can be retrieved, if the underlying data element instance belongs to a collection data element. If the related data element is not a collection the index query parameter has no effect since at most one data value is associated to the data element instance. The current number of &#x60;DataValue&#x60; resources associated to a &#x60;DataElementInstance&#x60; resource are provided by the **numberOfDataValues** property of the &#x60;DataElementInstance&#x60; resource. The value of the query parameter **indexOfDataValue** has to be in the range of [1,**numberOfDataValues**].
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getDataValuesTest() throws ApiException {
        String instanceId = null;
        Integer indexOfDataValue = null;
        DataValueArrayWithLinks response = api.getDataValues(instanceId, indexOfDataValue);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * Gets all available &#x60;DataValue&#x60; resources. Optional query parameter of **start** and **size** enable pagination of the collection of data value resources, param **createdBy** filters result list by the creator and param **status** filters result list by status of the data values.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getDataValuesDirectlyTest() throws ApiException {
        Integer start = null;
        Integer size = null;
        String status = null;
        String createdBy = null;
        DataValueArrayWithLinks response = api.getDataValuesDirectly(start, size, status, createdBy);

        // TODO: test validations
    }
    
    /**
     * Pulls data from the data value identified by Id
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void pullDataValueTest() throws ApiException {
        String dataValueId = null;
        byte[] response = api.pullDataValue(dataValueId);

        // TODO: test validations
    }
    
    /**
     * Pushes data to the TraDE middleware by attaching it to the data value identified by Id
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void pushDataValueTest() throws ApiException {
        String dataValueId = null;
        Long contentLength = null;
        byte[] data = null;
        api.pushDataValue(dataValueId, data, false, contentLength);

        // TODO: test validations
    }
    
    /**
     * Removes an associated dataValue from a data element instance.
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void removeDataValueFromDataElementInstanceTest() throws ApiException {
        String instanceId = null;
        String dataValueId = null;
        api.removeDataValueFromDataElementInstance(instanceId, dataValueId);

        // TODO: test validations
    }
    
    /**
     * Update an existing data value
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateDataValueDirectlyTest() throws ApiException {
        String dataValueId = null;
        DataValue dataValue = null;
        DataValueWithLinks response = api.updateDataValueDirectly(dataValueId, dataValue);

        // TODO: test validations
    }
    
}
