package io.github.watertao.veigar.vsa.mgn.service.user;

import java.util.Date;
import java.util.List;

public class UserResult {

  private Integer id;
  private String employeeNo;
  private String name;
  private String status;
  private String remark;
  private Date lastLoginTime;
  private String lastLoginIp;
  private Date createTime;
  private Date updateTime;
  private List<UserRoleResult> roles;
  private UserDepartmentResult department;

  public List<UserRoleResult> getRoles() {
    return roles;
  }

  public void setRoles(List<UserRoleResult> roles) {
    this.roles = roles;
  }

  public UserDepartmentResult getDepartment() {
    return department;
  }

  public void setDepartment(UserDepartmentResult department) {
    this.department = department;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getEmployeeNo() {
    return employeeNo;
  }

  public void setEmployeeNo(String employeeNo) {
    this.employeeNo = employeeNo;
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

  public Date getLastLoginTime() {
    return lastLoginTime;
  }

  public void setLastLoginTime(Date lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
  }

  public String getLastLoginIp() {
    return lastLoginIp;
  }

  public void setLastLoginIp(String lastLoginIp) {
    this.lastLoginIp = lastLoginIp;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }


  public static class UserRoleResult {
    private Integer id;
    private Integer level;
    private String code;
    private String name;
    private String remark;

    public Integer getId() {
      return id;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public Integer getLevel() {
      return level;
    }

    public void setLevel(Integer level) {
      this.level = level;
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

    public String getRemark() {
      return remark;
    }

    public void setRemark(String remark) {
      this.remark = remark;
    }
  }

  public static class UserDepartmentResult {

    private Integer id;
    private Integer parentId;
    private String code;
    private String name;
    private Integer level;

    public Integer getId() {
      return id;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public Integer getParentId() {
      return parentId;
    }

    public void setParentId(Integer parentId) {
      this.parentId = parentId;
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
