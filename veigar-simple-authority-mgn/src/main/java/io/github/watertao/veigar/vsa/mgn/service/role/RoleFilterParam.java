package io.github.watertao.veigar.vsa.mgn.service.role;

import io.github.watertao.veigar.vsa.mgn.service.AbstractFilterParam;

public class RoleFilterParam extends AbstractFilterParam {

  private String code;
  private String name;
  private String status;
  private Integer level;

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
