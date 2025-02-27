package com.caiths.echoroomapi.entity;

import com.alibaba.excel.annotation.ExcelIgnore;  // EasyExcel 忽略字段注解，表示不导出到 Excel
import com.alibaba.excel.annotation.ExcelProperty;  // EasyExcel 属性注解，指定 Excel 列名
import com.alibaba.excel.annotation.write.style.ColumnWidth;  // EasyExcel 列宽注解，设置列宽度
import com.alibaba.excel.annotation.write.style.ContentRowHeight;  // EasyExcel 行高注解，设置内容行高度
import com.alibaba.excel.converters.string.StringImageConverter;  // EasyExcel 字符串图片转换器（未使用）
import com.fasterxml.jackson.annotation.JsonFormat;  // Jackson 日期格式化注解，指定 JSON 序列化格式
import com.caiths.echoroomapi.converter.MyContentConverter;  // 自定义内容转换器
import com.caiths.echoroomapi.data.GroupMsgContentData;  // Excel 数据实体类

import java.io.Serializable;  // Java 序列化接口
import java.net.MalformedURLException;  // URL 格式异常
import java.net.URL;  // URL 类，表示网络资源地址
import java.util.Date;  // Date 类，表示日期和时间

/**
 * 群聊消息内容 (GroupMsgContent) 实体类。
 * <p>
 * 该类表示群聊中的消息内容，支持序列化和 Excel 导出。
 * </p>
 *
 * @author poboll
 * @since 2025-02-26
 */
@ColumnWidth(25)  // 设置默认列宽为 25
@ContentRowHeight(40)  // 设置内容行高为 40
public class GroupMsgContent implements Serializable {
    private static final long serialVersionUID = 980328865610261046L;  // 序列化版本号

    /**
     * 消息内容编号，主键标识。
     */
    @ExcelProperty("消息内容编号")  // Excel 列名为 "消息内容编号"
    private Integer id;

    /**
     * 发送者的用户编号。
     */
    @ExcelProperty("发送者的编号")  // Excel 列名为 "发送者的编号"
    private Integer fromId;

    /**
     * 发送者的昵称。
     */
    @ExcelProperty("昵称")  // Excel 列名为 "昵称"
    private String fromName;

    /**
     * 发送者的头像地址，在 Excel 导出中忽略。
     */
    @ExcelIgnore  // 不导出到 Excel
    private String fromProfile;

    /**
     * 消息发送时间，格式化为 "yyyy-MM-dd HH:mm:ss"，使用 GMT+8 时区。
     */
    @ExcelProperty("发送时间")  // Excel 列名为 "发送时间"
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")  // JSON 序列化格式
    private Date createTime;

    /**
     * 消息内容，使用自定义转换器 MyContentConverter 处理，列宽设置为 50。
     */
    @ExcelProperty(value = "内容", converter = MyContentConverter.class)  // Excel 列名为 "内容"，使用自定义转换器
    @ColumnWidth(50)  // 单独设置列宽为 50
    private String content;

    /**
     * 消息类型编号，在 Excel 导出中忽略。
     */
    @ExcelIgnore  // 不导出到 Excel
    private Integer messageTypeId;

    /**
     * 获取消息内容编号。
     *
     * @return 消息内容编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置消息内容编号。
     *
     * @param id 消息内容编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取发送者的用户编号。
     *
     * @return 发送者编号
     */
    public Integer getFromId() {
        return fromId;
    }

    /**
     * 设置发送者的用户编号。
     *
     * @param fromId 发送者编号
     */
    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    /**
     * 获取发送者的昵称。
     *
     * @return 发送者昵称
     */
    public String getFromName() {
        return fromName;
    }

    /**
     * 设置发送者的昵称。
     *
     * @param fromName 发送者昵称
     */
    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    /**
     * 获取发送者的头像地址。
     *
     * @return 头像地址
     */
    public String getFromProfile() {
        return fromProfile;
    }

    /**
     * 设置发送者的头像地址。
     *
     * @param fromProfile 头像地址
     */
    public void setFromProfile(String fromProfile) {
        this.fromProfile = fromProfile;
    }

    /**
     * 获取消息发送时间。
     *
     * @return 发送时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置消息发送时间。
     *
     * @param createTime 发送时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        return "GroupMsgContent{" +
                "id=" + id +
                ", fromId=" + fromId +
                ", fromName='" + fromName + '\'' +
                ", fromProfile='" + fromProfile + '\'' +
                ", createTime=" + createTime +
                ", content='" + content + '\'' +
                ", messageTypeId=" + messageTypeId +
                '}';
    }

    /**
     * 将数据库实体转化为 Excel 数据实体。
     * 该方法将 GroupMsgContent 转换为 GroupMsgContentData，以便导出到 Excel。
     *
     * @param groupMsgContent 需要转换的 GroupMsgContent 实体
     * @return 转换后的 GroupMsgContentData 对象
     * @throws MalformedURLException 如果 URL 格式不正确
     */
    public static GroupMsgContentData convertEntityToData(GroupMsgContent groupMsgContent) throws MalformedURLException {
        GroupMsgContentData groupMsgContentData = new GroupMsgContentData();
        groupMsgContentData.setFromId(groupMsgContent.getFromId());  // 设置发送者编号
        groupMsgContentData.setId(groupMsgContent.getId());  // 设置消息编号
        groupMsgContentData.setFromName(groupMsgContent.getFromName());  // 设置发送者昵称
        groupMsgContentData.setCreateTime(groupMsgContent.getCreateTime());  // 设置发送时间
        // 将头像地址转换为 URL 以便 Excel 导出图片
        groupMsgContentData.setFromProfile(new URL(groupMsgContent.getFromProfile()));
        // 根据消息类型设置内容
        if (groupMsgContent.getMessageTypeId() == 1) {
            groupMsgContentData.setTextContent(groupMsgContent.getContent());  // 类型 1 为文本内容
        }
        if (groupMsgContent.getMessageTypeId() == 2) {
            groupMsgContentData.setImageContent(new URL(groupMsgContent.getContent()));  // 类型 2 为图片内容
        }
        return groupMsgContentData;
    }
}
