package com.caiths.echoroomapi.entity;

import java.util.List;  // List 接口，用于存储数据实体列表

/**
 * 分页响应实体类 (RespPageBean)。
 * <p>
 * 该类用于封装分页查询的返回结果，包含数据总数和数据实体列表。
 * </p>
 *
 * @author poboll
 * @since 2025-02-26
 */
public class RespPageBean {
  /**
   * 数据总数，表示查询结果的总记录数。
   */
  private Long total;

  /**
   * 数据实体列表，存储分页查询返回的具体数据，类型为泛型。
   */
  private List<?> data;

  /**
   * 获取数据总数。
   *
   * @return 数据总数
   */
  public Long getTotal() {
    return total;
  }

  /**
   * 设置数据总数。
   *
   * @param total 数据总数
   */
  public void setTotal(Long total) {
    this.total = total;
  }

  /**
   * 获取数据实体列表。
   *
   * @return 数据实体列表
   */
  public List<?> getData() {
    return data;
  }

  /**
   * 设置数据实体列表。
   *
   * @param data 数据实体列表
   */
  public void setData(List<?> data) {
    this.data = data;
  }

  /**
   * 返回对象的字符串表示形式，便于调试和日志记录。
   *
   * @return 对象的字符串表示
   */
  @Override
  public String toString() {
    return "RespPageBean{" +
            "total=" + total +
            ", data=" + data +
            '}';
  }
}
