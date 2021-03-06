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
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

/**
 * DataTransformation
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-09-27T10:57:01.528+02:00")
public class DataTransformation implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("transformerQName")
  private String transformerQName = null;

  @JsonProperty("transformerParameters")
  private List<DataTransformationTransformerParameters> transformerParameters = null;

  public DataTransformation name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public DataTransformation transformerQName(String transformerQName) {
    this.transformerQName = transformerQName;
    return this;
  }

   /**
   * Get transformerQName
   * @return transformerQName
  **/
  public String getTransformerQName() {
    return transformerQName;
  }

  public void setTransformerQName(String transformerQName) {
    this.transformerQName = transformerQName;
  }

  public DataTransformation transformerParameters(List<DataTransformationTransformerParameters> transformerParameters) {
    this.transformerParameters = transformerParameters;
    return this;
  }

  public DataTransformation addTransformerParametersItem(DataTransformationTransformerParameters transformerParametersItem) {
    if (this.transformerParameters == null) {
      this.transformerParameters = new ArrayList<DataTransformationTransformerParameters>();
    }
    this.transformerParameters.add(transformerParametersItem);
    return this;
  }

   /**
   * Get transformerParameters
   * @return transformerParameters
  **/
  public List<DataTransformationTransformerParameters> getTransformerParameters() {
    return transformerParameters;
  }

  public void setTransformerParameters(List<DataTransformationTransformerParameters> transformerParameters) {
    this.transformerParameters = transformerParameters;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DataTransformation dataTransformation = (DataTransformation) o;
    return Objects.equal(this.name, dataTransformation.name) &&
        Objects.equal(this.transformerQName, dataTransformation.transformerQName) &&
        Objects.equal(this.transformerParameters, dataTransformation.transformerParameters);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(name, transformerQName, transformerParameters);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DataTransformation {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    transformerQName: ").append(toIndentedString(transformerQName)).append("\n");
    sb.append("    transformerParameters: ").append(toIndentedString(transformerParameters)).append("\n");
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

