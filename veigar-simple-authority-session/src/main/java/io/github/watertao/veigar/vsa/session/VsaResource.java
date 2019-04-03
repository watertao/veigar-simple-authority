package io.github.watertao.veigar.vsa.session;


import io.github.watertao.veigar.session.spi.Resource;

import java.util.List;

public class VsaResource implements Resource {

  private Integer id;
  private String verb;
  private String type;
  private String uriPattern;
  private String name;
  private String remark;
  private List<String> attributes;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getVerb() {
    return verb;
  }

  public void setVerb(String verb) {
    this.verb = verb;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getUriPattern() {
    return uriPattern;
  }

  public void setUriPattern(String uriPattern) {
    this.uriPattern = uriPattern;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public List<String> getAttributes() {
    return attributes;
  }

  public void setAttributes(List<String> attributes) {
    this.attributes = attributes;
  }

}
