package com.caiths.echoroomapi.entity;

/**
 * JSON 响应实体类 (RespBean)。
 * <p>
 * 该类用于封装 API 的返回结果，包含状态码、消息和返回对象，支持链式调用。
 * </p>
 *
 * @author poboll
 * @since 2025-02-26
 */
public class RespBean {
  /**
   * 状态码，例如 200 表示成功，500 表示失败。
   */
  private Integer status;

  /**
   * 返回消息，用于描述操作结果。
   */
  private String msg;

  /**
   * 返回实体，可以是任意类型的对象。
   */
  private Object obj;

  /**
   * 构建一个空的 RespBean 对象。
   *
   * @return 新创建的 RespBean 实例
   */
  public static RespBean build() {
    return new RespBean();
  }

  /**
   * 创建一个表示成功的 RespBean 对象，仅包含消息。
   *
   * @param msg 返回消息
   * @return 表示成功的 RespBean 实例，状态码为 200
   */
  public static RespBean ok(String msg) {
    return new RespBean(200, msg, null);
  }

  /**
   * 创建一个表示成功的 RespBean 对象，包含消息和返回实体。
   *
   * @param msg 返回消息
   * @param obj 返回实体
   * @return 表示成功的 RespBean 实例，状态码为 200
   */
  public static RespBean ok(String msg, Object obj) {
    return new RespBean(200, msg, obj);
  }

  /**
   * 创建一个表示失败的 RespBean 对象，仅包含消息。
   *
   * @param msg 返回消息
   * @return 表示失败的 RespBean 实例，状态码为 500
   */
  public static RespBean error(String msg) {
    return new RespBean(500, msg, null);
  }

  /**
   * 创建一个表示失败的 RespBean 对象，包含消息和返回实体。
   *
   * @param msg 返回消息
   * @param obj 返回实体
   * @return 表示失败的 RespBean 实例，状态码为 500
   */
  public static RespBean error(String msg, Object obj) {
    return new RespBean(500, msg, obj);
  }

  /**
   * 私有构造方法，防止外部直接实例化。
   */
  private RespBean() {
  }

  /**
   * 私有构造方法，用于初始化状态码、消息和返回实体。
   *
   * @param status 状态码
   * @param msg 返回消息
   * @param obj 返回实体
   */
  private RespBean(Integer status, String msg, Object obj) {
    this.status = status;
    this.msg = msg;
    this.obj = obj;
  }

  /**
   * 获取状态码。
   *
   * @return 状态码
   */
  public Integer getStatus() {
    return status;
  }

  /**
   * 设置状态码，支持链式调用。
   *
   * @param status 状态码
   * @return 当前 RespBean 实例
   */
  public RespBean setStatus(Integer status) {
    this.status = status;
    return this;
  }

  /**
   * 获取返回消息。
   *
   * @return 返回消息
   */
  public String getMsg() {
    return msg;
  }

  /**
   * 设置返回消息，支持链式调用。
   *
   * @param msg 返回消息
   * @return 当前 RespBean 实例
   */
  public RespBean setMsg(String msg) {
    this.msg = msg;
    return this;
  }

  /**
   * 获取返回实体。
   *
   * @return 返回实体
   */
  public Object getObj() {
    return obj;
  }

  /**
   * 设置返回实体，支持链式调用。
   *
   * @param obj 返回实体
   * @return 当前 RespBean 实例
   */
  public RespBean setObj(Object obj) {
    this.obj = obj;
    return this;
  }

  /**
   * 返回对象的字符串表示形式，便于调试和日志记录。
   *
   * @return 对象的字符串表示
   */
  @Override
  public String toString() {
    return "RespBean{" +
            "status=" + status +
            ", msg='" + msg + '\'' +
            ", obj=" + obj +
            '}';
  }
}
