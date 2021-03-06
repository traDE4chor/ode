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
 * ResourceEventFilterDescription
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-06-21T14:24:08.511+02:00")
public class ResourceEventFilterDescription implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("href")
  private String href = null;

  @JsonProperty("eventType")
  private String eventType = null;

  @JsonProperty("filterName")
  private String filterName = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("valueDomainConstraints")
  private List<String> valueDomainConstraints = new ArrayList<String>();

  public ResourceEventFilterDescription href(String href) {
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

  public ResourceEventFilterDescription eventType(String eventType) {
    this.eventType = eventType;
    return this;
  }

   /**
   * Get eventType
   * @return eventType
  **/
  public String getEventType() {
    return eventType;
  }

  public void setEventType(String eventType) {
    this.eventType = eventType;
  }

  public ResourceEventFilterDescription filterName(String filterName) {
    this.filterName = filterName;
    return this;
  }

   /**
   * Get filterName
   * @return filterName
  **/
  public String getFilterName() {
    return filterName;
  }

  public void setFilterName(String filterName) {
    this.filterName = filterName;
  }

  public ResourceEventFilterDescription description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ResourceEventFilterDescription valueDomainConstraints(List<String> valueDomainConstraints) {
    this.valueDomainConstraints = valueDomainConstraints;
    return this;
  }

  public ResourceEventFilterDescription addValueDomainConstraintsItem(String valueDomainConstraintsItem) {
    this.valueDomainConstraints.add(valueDomainConstraintsItem);
    return this;
  }

   /**
   * Get valueDomainConstraints
   * @return valueDomainConstraints
  **/
  public List<String> getValueDomainConstraints() {
    return valueDomainConstraints;
  }

  public void setValueDomainConstraints(List<String> valueDomainConstraints) {
    this.valueDomainConstraints = valueDomainConstraints;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResourceEventFilterDescription resourceEventFilterDescription = (ResourceEventFilterDescription) o;
    return Objects.equal(this.href, resourceEventFilterDescription.href) &&
        Objects.equal(this.eventType, resourceEventFilterDescription.eventType) &&
        Objects.equal(this.filterName, resourceEventFilterDescription.filterName) &&
        Objects.equal(this.description, resourceEventFilterDescription.description) &&
        Objects.equal(this.valueDomainConstraints, resourceEventFilterDescription.valueDomainConstraints);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(href, eventType, filterName, description, valueDomainConstraints);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResourceEventFilterDescription {\n");
    
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    eventType: ").append(toIndentedString(eventType)).append("\n");
    sb.append("    filterName: ").append(toIndentedString(filterName)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    valueDomainConstraints: ").append(toIndentedString(valueDomainConstraints)).append("\n");
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

