package io.github.watertao.veigar.vsa.mgn.api.resource.payload;

public class ResourceRes {

  private Integer id;
  private String uri_pattern;
  private String verb;
  private String name;
  private String remark;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUri_pattern() {
    return uri_pattern;
  }

  public void setUri_pattern(String uri_pattern) {
    this.uri_pattern = uri_pattern;
  }

  public String getVerb() {
    return verb;
  }

  public void setVerb(String verb) {
    this.verb = verb;
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

}
