package io.github.watertao.veigar.vsa.mgn.api.user.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class UserCreateReq {

  @NotEmpty
  private String employee_no;

  @NotEmpty
  private String name;

  private String remark;

  @NotEmpty
  private String password;

  @NotNull
  private Integer dep_id;

  private List<Integer> role_ids;

  public String getEmployee_no() {
    return employee_no;
  }

  public void setEmployee_no(String employee_no) {
    this.employee_no = employee_no;
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

  public Integer getDep_id() {
    return dep_id;
  }

  public void setDep_id(Integer dep_id) {
    this.dep_id = dep_id;
  }

  public List<Integer> getRole_ids() {
    return role_ids;
  }

  public void setRole_ids(List<Integer> role_ids) {
    this.role_ids = role_ids;
  }

}
