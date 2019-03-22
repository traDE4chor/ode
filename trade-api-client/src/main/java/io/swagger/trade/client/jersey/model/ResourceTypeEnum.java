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


package io.swagger.trade.client.jersey.model;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets ResourceTypeEnum
 */
public enum ResourceTypeEnum {
  
  DATADEPENDENCYGRAPH("DataDependencyGraph"),
  
  DATAMODEL("DataModel"),
  
  DATAOBJECT("DataObject"),
  
  DATAELEMENT("DataElement"),
  
  DATAOBJECTINSTANCE("DataObjectInstance"),
  
  DATAELEMENTINSTANCE("DataElementInstance"),
  
  DATAVALUE("DataValue");

  private String value;

  ResourceTypeEnum(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static ResourceTypeEnum fromValue(String text) {
    for (ResourceTypeEnum b : ResourceTypeEnum.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
