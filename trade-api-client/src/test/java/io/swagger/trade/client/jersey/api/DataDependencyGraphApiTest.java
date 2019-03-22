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
import io.swagger.trade.client.jersey.model.DataDependencyGraphArrayWithLinks;
import io.swagger.trade.client.jersey.model.DataDependencyGraphData;
import io.swagger.trade.client.jersey.model.DataDependencyGraphWithLinks;
import io.swagger.trade.client.jersey.model.Error;
import io.swagger.trade.client.jersey.model.InvalidInput;
import io.swagger.trade.client.jersey.model.NotFound;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for DataDependencyGraphApi
 */
@Ignore
public class DataDependencyGraphApiTest {

    private final DataDependencyGraphApi api = new DataDependencyGraphApi();

    
    /**
     * Creates and adds a new data dependency graph to the TraDE middleware
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addDataDependencyGraphTest() throws ApiException {
        DataDependencyGraphData dataDependencyGraphData = null;
        DataDependencyGraphWithLinks response = api.addDataDependencyGraph(dataDependencyGraphData);

        // TODO: test validations
    }
    
    /**
     * Deletes a data dependency graph.
     *
     * Deletes a data dependency graph from the TraDE middleware.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteDataDependencyGraphTest() throws ApiException {
        String graphId = null;
        api.deleteDataDependencyGraph(graphId);

        // TODO: test validations
    }
    
    /**
     * Provides the serialized graph model of the data dependency graph
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void downloadGraphModelTest() throws ApiException {
        String graphId = null;
        byte[] response = api.downloadGraphModel(graphId);

        // TODO: test validations
    }
    
    /**
     * Find a data dependency graph by Id
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getDataDependencyGraphDirectlyTest() throws ApiException {
        String graphId = null;
        DataDependencyGraphWithLinks response = api.getDataDependencyGraphDirectly(graphId);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * Gets all available &#x60;DataDependencyGraph&#x60; resources. Optional query parameter of **start** and **size** enable pagination of the collection of data dependency graph resources and param **targetNamespace**, **name** and **entity** filter result list by name, targetNamespace and entity.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getDataDependencyGraphsTest() throws ApiException {
        Integer start = null;
        Integer size = null;
        String targetNamespace = null;
        String name = null;
        String entity = null;
        DataDependencyGraphArrayWithLinks response = api.getDataDependencyGraphs(start, size, targetNamespace, name, entity);

        // TODO: test validations
    }
    
    /**
     * Upload a serialized graph model to the TraDE middleware in order to make it available.
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void uploadGraphModelTest() throws ApiException {
        String graphId = null;
        Long contentLength = null;
        byte[] graph = null;
        api.uploadGraphModel(graphId, contentLength, graph);

        // TODO: test validations
    }
    
}
