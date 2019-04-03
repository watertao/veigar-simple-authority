package io.github.watertao.veigar.vsa.mgn.service;

import io.github.watertao.veigar.core.exception.BadRequestException;

public abstract class AbstractFilterParam {

  private static final int MAX_COUNT = 100;

  private Integer lastCursor = 0;
  private Integer count = 20;
  boolean infiniteCount = false;
  private String sortField;
  private String sortOrder = "ascend";   // ascend / descend

  public Integer getLastCursor() {
    return this.lastCursor;
  }

  public void setLastCursor(Integer lastCursor) {
    if (lastCursor == null || lastCursor < 0) {
      return;
    }
    this.lastCursor = lastCursor;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    if (count == null || count < 0) {
      return;
    }
    if (count > MAX_COUNT) {
      if (!infiniteCount) {
        count = MAX_COUNT;
      }
    }
    this.count = count;
  }

  public boolean isInfiniteCount() {
    return infiniteCount;
  }

  public void setInfiniteCount(boolean infiniteCount) {
    this.infiniteCount = infiniteCount;
  }

  public String getSortField() {
    return sortField;
  }

  public void setSortField(String sortField) {
    this.sortField = sortField;
  }

  public String getSortOrder() {
    return sortOrder;
  }

  public void setSortOrder(String sortOrder) {
    if (sortOrder == null) {
      this.sortOrder = "ascend";
    } else {
      if (sortOrder.equals("ascend") || sortOrder.equals("descend")) {
        this.sortOrder = sortOrder;
      } else {
        throw new BadRequestException("sort_order should be 'ascend' or 'descend'");
      }
    }

  }

  /**
   * 若返回为 Null，则认为本次无需排序
   *
   * @return
   */
  public String makeOrderClause() {
    if (this.sortField == null) {
      return null;
    }
    String realField = getRealSortField(this.sortField);
    if (realField == null) {
      return null;
    }
    return realField + " " + (this.sortOrder.equals("ascend") ? "asc" : "desc");
  }

  /**
   * 若返回为 Null， 则认为本次无需排序
   *
   * @param sortFieldInQueryParam
   * @return
   */
  protected abstract String getRealSortField(String sortFieldInQueryParam);

}
