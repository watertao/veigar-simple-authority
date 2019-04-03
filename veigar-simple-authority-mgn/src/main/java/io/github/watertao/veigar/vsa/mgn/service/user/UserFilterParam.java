package io.github.watertao.veigar.vsa.mgn.service.user;

import io.github.watertao.veigar.vsa.mgn.service.AbstractFilterParam;

public class UserFilterParam extends AbstractFilterParam {

  private String employeeNo;
  private String name;
  private String status;

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

  @Override
  protected String getRealSortField(String sortFieldInQueryParam) {
    return null;
  }
}
