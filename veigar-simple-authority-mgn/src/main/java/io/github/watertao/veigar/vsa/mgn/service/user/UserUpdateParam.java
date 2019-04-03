package io.github.watertao.veigar.vsa.mgn.service.user;

import java.util.List;

public class UserUpdateParam {

  private Integer id;
  private String employeeNo;
  private String name;
  private String remark;
  private Integer depId;
  private String status;
  private List<Integer> roleIds;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
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

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
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
