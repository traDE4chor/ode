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
 * DataElement
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-04-05T17:17:13.418+02:00")
public class DataElement implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("href")
  private String href = null;

  @JsonProperty("dataObjectName")
  private String dataObjectName = null;

  @JsonProperty("entity")
  private String entity = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("status")
  private StatusEnum status = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("contentType")
  private String contentType = null;

  @JsonProperty("isCollectionElement")
  private Boolean isCollectionElement = null;

  public DataElement id(String id) {
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

  public DataElement href(String href) {
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

  public DataElement dataObjectName(String dataObjectName) {
    this.dataObjectName = dataObjectName;
    return this;
  }

   /**
   * Get dataObjectName
   * @return dataObjectName
  **/
  public String getDataObjectName() {
    return dataObjectName;
  }

  public void setDataObjectName(String dataObjectName) {
    this.dataObjectName = dataObjectName;
  }

  public DataElement entity(String entity) {
    this.entity = entity;
    return this;
  }

   /**
   * Get entity
   * @return entity
  **/
  public String getEntity() {
    return entity;
  }

  public void setEntity(String entity) {
    this.entity = entity;
  }

  public DataElement name(String name) {
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

  public DataElement status(StatusEnum status) {
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public DataElement type(String type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public DataElement contentType(String contentType) {
    this.contentType = contentType;
    return this;
  }

   /**
   * Get contentType
   * @return contentType
  **/
  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public DataElement isCollectionElement(Boolean isCollectionElement) {
    this.isCollectionElement = isCollectionElement;
    return this;
  }

   /**
   * Get isCollectionElement
   * @return isCollectionElement
  **/
  public Boolean isIsCollectionElement() {
    return isCollectionElement;
  }

  public void setIsCollectionElement(Boolean isCollectionElement) {
    this.isCollectionElement = isCollectionElement;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DataElement dataElement = (DataElement) o;
    return Objects.equal(this.id, dataElement.id) &&
        Objects.equal(this.href, dataElement.href) &&
        Objects.equal(this.dataObjectName, dataElement.dataObjectName) &&
        Objects.equal(this.entity, dataElement.entity) &&
        Objects.equal(this.name, dataElement.name) &&
        Objects.equal(this.status, dataElement.status) &&
        Objects.equal(this.type, dataElement.type) &&
        Objects.equal(this.contentType, dataElement.contentType) &&
        Objects.equal(this.isCollectionElement, dataElement.isCollectionElement);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id, href, dataObjectName, entity, name, status, type, contentType, isCollectionElement);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DataElement {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    dataObjectName: ").append(toIndentedString(dataObjectName)).append("\n");
    sb.append("    entity: ").append(toIndentedString(entity)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    contentType: ").append(toIndentedString(contentType)).append("\n");
    sb.append("    isCollectionElement: ").append(toIndentedString(isCollectionElement)).append("\n");
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

