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
package io.swagger.trade.client.jersey.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
/**
 * DataElementInstance
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-04-05T17:17:13.418+02:00")
public class DataElementInstance implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("href")
  private String href = null;

  @JsonProperty("createdBy")
  private String createdBy = null;

  @JsonProperty("dataElementName")
  private String dataElementName = null;

  @JsonProperty("numberOfDataValues")
  private Integer numberOfDataValues = null;

  @JsonProperty("correlationProperties")
  private CorrelationPropertyArray correlationProperties = null;

  @JsonProperty("status")
  private InstanceStatusEnum status = null;

  public DataElementInstance id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public DataElementInstance href(String href) {
    this.href = href;
    return this;
  }

   /**
   * Get href
   * @return href
  **/
  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public DataElementInstance createdBy(String createdBy) {
    this.createdBy = createdBy;
    return this;
  }

   /**
   * Get createdBy
   * @return createdBy
  **/
  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public DataElementInstance dataElementName(String dataElementName) {
    this.dataElementName = dataElementName;
    return this;
  }

   /**
   * Get dataElementName
   * @return dataElementName
  **/
  public String getDataElementName() {
    return dataElementName;
  }

  public void setDataElementName(String dataElementName) {
    this.dataElementName = dataElementName;
  }

  public DataElementInstance numberOfDataValues(Integer numberOfDataValues) {
    this.numberOfDataValues = numberOfDataValues;
    return this;
  }

   /**
   * Get numberOfDataValues
   * @return numberOfDataValues
  **/
  public Integer getNumberOfDataValues() {
    return numberOfDataValues;
  }

  public void setNumberOfDataValues(Integer numberOfDataValues) {
    this.numberOfDataValues = numberOfDataValues;
  }

  public DataElementInstance correlationProperties(CorrelationPropertyArray correlationProperties) {
    this.correlationProperties = correlationProperties;
    return this;
  }

   /**
   * Get correlationProperties
   * @return correlationProperties
  **/
  public CorrelationPropertyArray getCorrelationProperties() {
    return correlationProperties;
  }

  public void setCorrelationProperties(CorrelationPropertyArray correlationProperties) {
    this.correlationProperties = correlationProperties;
  }

  public DataElementInstance status(InstanceStatusEnum status) {
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  public InstanceStatusEnum getStatus() {
    return status;
  }

  public void setStatus(InstanceStatusEnum status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DataElementInstance dataElementInstance = (DataElementInstance) o;
    return Objects.equal(this.id, dataElementInstance.id) &&
        Objects.equal(this.href, dataElementInstance.href) &&
        Objects.equal(this.createdBy, dataElementInstance.createdBy) &&
        Objects.equal(this.dataElementName, dataElementInstance.dataElementName) &&
        Objects.equal(this.numberOfDataValues, dataElementInstance.numberOfDataValues) &&
        Objects.equal(this.correlationProperties, dataElementInstance.correlationProperties) &&
        Objects.equal(this.status, dataElementInstance.status);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id, href, createdBy, dataElementName, numberOfDataValues, correlationProperties, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DataElementInstance {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    createdBy: ").append(toIndentedString(createdBy)).append("\n");
    sb.append("    dataElementName: ").append(toIndentedString(dataElementName)).append("\n");
    sb.append("    numberOfDataValues: ").append(toIndentedString(numberOfDataValues)).append("\n");
    sb.append("    correlationProperties: ").append(toIndentedString(correlationProperties)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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
