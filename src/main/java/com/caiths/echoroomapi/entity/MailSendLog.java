package com.caiths.echoroomapi.entity;

import java.util.Date;  // Date 类，表示日期和时间
import java.io.Serializable;  // Java 序列化接口，用于支持对象序列化

/**
 * 邮件发送日志 (MailSendLog) 实体类。
 * <p>
 * 该类用于记录邮件发送的相关信息，包括消息状态、内容类型及时间等。
 * </p>
 *
 * @author makejava
 * @since 2025-02-26
 */
public class MailSendLog implements Serializable {
    private static final long serialVersionUID = 740872026109078508L;  // 序列化版本号，用于版本控制

    /**
     * 消息 ID，邮件的唯一标识。
     */
    private String msgId;

    /**
     * 内容类型，0 表示反馈，1 表示验证码。
     */
    private Integer contentType;

    /**
     * 邮件内容，具体的发送内容。
     */
    private String content;

    /**
     * 发送状态，例如投递中、成功或失败。
     */
    private Integer status;

    /**
     * 路由键，用于消息队列的路由。
     */
    private String routeKey;

    /**
     * 交换机名称，用于消息队列的交换。
     */
    private String exchange;

    /**
     * 重试次数，记录邮件发送的重试次数。
     */
    private Integer count;

    /**
     * 下次重试时间，表示下一次尝试发送的时间。
     */
    private Date tryTime;

    /**
     * 创建时间，记录日志的创建时间。
     */
    private Date createTime;

    /**
     * 更新时间，记录日志的最后更新时间。
     */
    private Date updateTime;

    /**
     * 获取消息 ID。
     *
     * @return 消息 ID
     */
    public String getMsgId() {
        return msgId;
    }

    /**
     * 设置消息 ID。
     *
     * @param msgId 消息 ID
     */
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    /**
     * 获取内容类型。
     *
     * @return 内容类型，0 表示反馈，1 表示验证码
     */
    public Integer getContentType() {
        return contentType;
    }

    /**
     * 设置内容类型。
     *
     * @param contentType 内容类型，0 表示反馈，1 表示验证码
     */
    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    /**
     * 获取邮件内容。
     *
     * @return 邮件内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置邮件内容。
     *
     * @param content 邮件内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取发送状态。
     *
     * @return 发送状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置发送状态。
     *
     * @param status 发送状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取路由键。
     *
     * @return 路由键
     */
    public String getRouteKey() {
        return routeKey;
    }

    /**
     * 设置路由键。
     *
     * @param routeKey 路由键
     */
    public void setRouteKey(String routeKey) {
        this.routeKey = routeKey;
    }

    /**
     * 获取交换机名称。
     *
     * @return 交换机名称
     */
    public String getExchange() {
        return exchange;
    }

    /**
     * 设置交换机名称。
     *
     * @param exchange 交换机名称
     */
    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    /**
     * 获取重试次数。
     *
     * @return 重试次数
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设置重试次数。
     *
     * @param count 重试次数
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 获取下次重试时间。
     *
     * @return 下次重试时间
     */
    public Date getTryTime() {
        return tryTime;
    }

    /**
     * 设置下次重试时间。
     *
     * @param tryTime 下次重试时间
     */
    public void setTryTime(Date tryTime) {
        this.tryTime = tryTime;
    }

    /**
     * 获取创建时间。
     *
     * @return 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间。
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间。
     *
     * @return 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间。
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 返回对象的字符串表示形式，便于调试和日志记录。
     *
     * @return 对象的字符串表示
     */
    @Override
    public String toString() {
        return "MailSendLog{" +
                "msgId='" + msgId + '\'' +
                ", contentType=" + contentType +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", routeKey='" + routeKey + '\'' +
                ", exchange='" + exchange + '\'' +
                ", count=" + count +
                ", tryTime=" + tryTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
