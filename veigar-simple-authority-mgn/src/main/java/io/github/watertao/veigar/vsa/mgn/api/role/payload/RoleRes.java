package io.github.watertao.veigar.vsa.mgn.api.role.payload;

import java.util.List;

public class RoleRes {

  private Integer id;
  private String code;
  private Integer level;
  private String name;
  private String status;
  private String remark;

  private List<RoleResourceRes> resources;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public List<RoleResourceRes> getResources() {
    return resources;
  }

  public void setResources(List<RoleResourceRes> resources) {
    this.resources = resources;
  }

  public static class RoleResourceRes {
    private Integer id;
    private String verb;
    private String uriPattern;
    private String name;
    private String remark;

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
  }

}
