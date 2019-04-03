package io.github.watertao.veigar.vsa.mgn.api.role.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class RoleUpdateReq {

  @NotEmpty
  private String code;

  @NotEmpty
  private String name;

  private String remark;

  @NotNull
  private Integer level;

  @NotEmpty
  private String status;

  private List<Integer> resource_ids;

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

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<Integer> getResource_ids() {
    return resource_ids;
  }

  public void setResource_ids(List<Integer> resource_ids) {
    this.resource_ids = resource_ids;
  }
}
