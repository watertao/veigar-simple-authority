package io.github.watertao.veigar.vsa.mgn.service.role;

import java.util.List;

public class RoleCreateParam {

  private String code;
  private String name;
  private String remark;
  private Integer level;

  private List<Integer> resourceIds;

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

  public List<Integer> getResourceIds() {
    return resourceIds;
  }

  public void setResourceIds(List<Integer> resourceIds) {
    this.resourceIds = resourceIds;
  }
}
