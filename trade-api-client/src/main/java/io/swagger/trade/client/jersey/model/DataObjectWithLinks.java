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
 * DataObjectWithLinks
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-04-05T17:17:13.418+02:00")
public class DataObjectWithLinks implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("dataObject")
  private DataObject dataObject = null;

  @JsonProperty("links")
  private LinkArray links = null;

  public DataObjectWithLinks dataObject(DataObject dataObject) {
    this.dataObject = dataObject;
    return this;
  }

   /**
   * Get dataObject
   * @return dataObject
  **/
  public DataObject getDataObject() {
    return dataObject;
  }

  public void setDataObject(DataObject dataObject) {
    this.dataObject = dataObject;
  }

  public DataObjectWithLinks links(LinkArray links) {
    this.links = links;
    return this;
  }

   /**
   * Get links
   * @return links
  **/
  public LinkArray getLinks() {
    return links;
  }

  public void setLinks(LinkArray links) {
    this.links = links;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DataObjectWithLinks dataObjectWithLinks = (DataObjectWithLinks) o;
    return Objects.equal(this.dataObject, dataObjectWithLinks.dataObject) &&
        Objects.equal(this.links, dataObjectWithLinks.links);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(dataObject, links);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DataObjectWithLinks {\n");
    
    sb.append("    dataObject: ").append(toIndentedString(dataObject)).append("\n");
    sb.append("    links: ").append(toIndentedString(links)).append("\n");
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

