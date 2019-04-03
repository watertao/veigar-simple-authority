package io.github.watertao.veigar.vsa.session;


import io.github.watertao.veigar.session.spi.AuthenticationObject;

import java.util.List;
import java.util.Map;

public class VsaAuthenticationObject extends AuthenticationObject {

  private String employee_no;
  private Integer id;
  private String name;
  private HtRsrvAuthenticationObjectDepartment department;
  private List<Map> roles;
  private List<Map> resources;
  private List<String> attributes;

  public String getEmployee_no() {
    return employee_no;
  }

  public void setEmployee_no(String employee_no) {
    this.employee_no = employee_no;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public HtRsrvAuthenticationObjectDepartment getDepartment() {
    return department;
  }

  public void setDepartment(HtRsrvAuthenticationObjectDepartment department) {
    this.department = department;
  }

  public List<Map> getRoles() {
    return roles;
  }

  public void setRoles(List<Map> roles) {
    this.roles = roles;
  }

  public List<Map> getResources() {
    return resources;
  }

  public void setResources(List<Map> resources) {
    this.resources = resources;
  }

  @Override
  public List<String> getAttributes() {
    return attributes;
  }

  public void setAttributes(List<String> attributes) {
    this.attributes = attributes;
  }


  public static class HtRsrvAuthenticationObjectDepartment {

    private Integer id;
    private String code;
    private String name;
    private Integer level;

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

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Integer getLevel() {
      return level;
    }

    public void setLevel(Integer level) {
      this.level = level;
    }
  }

}
