package io.github.watertao.veigar.vsa.mgn.service.user;

import java.util.List;

public class UserCreateParam {

  private String employeeNo;
  private String name;
  private String remark;
  private String password;
  private Integer depId;
  private List<Integer> roleIds;

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

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getDepId() {
    return depId;
  }

  public void setDepId(Integer depId) {
    this.depId = depId;
  }

  public List<Integer> getRoleIds() {
    return roleIds;
  }

  public void setRoleIds(List<Integer> roleIds) {
    this.roleIds = roleIds;
  }

}
