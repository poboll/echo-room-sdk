package com.caiths.echoroomapi.entity;

import java.util.Date;  // Date 类，表示日期和时间

/**
 * 单聊消息 (Message) 实体类。
 * <p>
 * 该类表示单聊中的消息内容，包含发送者、接收者及消息相关信息。
 * </p>
 *
 * @author poboll
 * @since 2025-02-26
 */
public class Message {
  /**
   * 发送者标识，通常为用户 ID。
   */
  private String from;

  /**
   * 接收者标识，通常为用户 ID。
   */
  private String to;

  /**
   * 消息内容，具体的消息文本或数据。
   */
  private String content;

  /**
   * 消息创建时间，表示消息发送的时间。
   */
  private Date createTime;

  /**
   * 发送者昵称，用于显示发送者的名称。
   */
  private String fromNickname;

  /**
   * 发送者头像地址，指向发送者的头像资源。
   */
  private String fromUserProfile;

  /**
   * 消息类型编号，用于区分消息的类型（如文本、图片等）。
   */
  private Integer messageTypeId;

  /**
   * 获取发送者标识。
   *
   * @return 发送者标识
   */
  public String getFrom() {
    return from;
  }

  /**
   * 设置发送者标识。
   *
   * @param from 发送者标识
   */
  public void setFrom(String from) {
    this.from = from;
  }

  /**
   * 获取接收者标识。
   *
   * @return 接收者标识
   */
  public String getTo() {
    return to;
  }

  /**
   * 设置接收者标识。
   *
   * @param to 接收者标识
   */
  public void setTo(String to) {
    this.to = to;
  }

  /**
   * 获取消息内容。
   *
   * @return 消息内容
   */
  public String getContent() {
    return content;
  }

  /**
   * 设置消息内容。
   *
   * @param content 消息内容
   */
  public void setContent(String content) {
    this.content = content;
  }

  /**
   * 获取消息创建时间。
   *
   * @return 消息创建时间
   */
  public Date getCreateTime() {
    return createTime;
  }

  /**
   * 设置消息创建时间。
   *
   * @param createTime 消息创建时间
   */
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  /**
   * 获取发送者昵称。
   *
   * @return 发送者昵称
   */
  public String getFromNickname() {
    return fromNickname;
  }

  /**
   * 设置发送者昵称。
   *
   * @param fromNickname 发送者昵称
   */
  public void setFromNickname(String fromNickname) {
    this.fromNickname = fromNickname;
  }

  /**
   * 获取发送者头像地址。
   *
   * @return 发送者头像地址
   */
  public String getFromUserProfile() {
    return fromUserProfile;
  }

  /**
   * 设置发送者头像地址。
   *
   * @param fromUserProfile 发送者头像地址
   */
  public void setFromUserProfile(String fromUserProfile) {
    this.fromUserProfile = fromUserProfile;
  }

  /**
   * 获取消息类型编号。
   *
   * @return 消息类型编号
   */
  public Integer getMessageTypeId() {
    return messageTypeId;
  }

  /**
   * 设置消息类型编号。
   *
   * @param messageTypeId 消息类型编号
   */
  public void setMessageTypeId(Integer messageTypeId) {
    this.messageTypeId = messageTypeId;
  }

  /**
   * 返回对象的字符串表示形式，便于调试和日志记录。
   *
   * @return 对象的字符串表示
   */
  @Override
  public String toString() {
    return "Message{" +
            "from='" + from + '\'' +
            ", to='" + to + '\'' +
            ", content='" + content + '\'' +
            ", createTime=" + createTime +
            ", fromNickname='" + fromNickname + '\'' +
            ", fromUserProfile='" + fromUserProfile + '\'' +
            ", messageTypeId=" + messageTypeId +
            '}';
  }
}
