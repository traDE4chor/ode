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

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

/**
 * DataTransformationTransformerParameters
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-09-27T10:57:01.528+02:00")
public class DataTransformationTransformerParameters implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("parameterName")
  private String parameterName = null;

  @JsonProperty("parameterValue")
  private String parameterValue = null;

  public DataTransformationTransformerParameters parameterName(String parameterName) {
    this.parameterName = parameterName;
    return this;
  }

   /**
   * Get parameterName
   * @return parameterName
  **/
  public String getParameterName() {
    return parameterName;
  }

  public void setParameterName(String parameterName) {
    this.parameterName = parameterName;
  }

  public DataTransformationTransformerParameters parameterValue(String parameterValue) {
    this.parameterValue = parameterValue;
    return this;
  }

   /**
   * Get parameterValue
   * @return parameterValue
  **/
  public String getParameterValue() {
    return parameterValue;
  }

  public void setParameterValue(String parameterValue) {
    this.parameterValue = parameterValue;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DataTransformationTransformerParameters dataTransformationTransformerParameters = (DataTransformationTransformerParameters) o;
    return Objects.equal(this.parameterName, dataTransformationTransformerParameters.parameterName) &&
        Objects.equal(this.parameterValue, dataTransformationTransformerParameters.parameterValue);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(parameterName, parameterValue);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DataTransformationTransformerParameters {\n");
    
    sb.append("    parameterName: ").append(toIndentedString(parameterName)).append("\n");
    sb.append("    parameterValue: ").append(toIndentedString(parameterValue)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

