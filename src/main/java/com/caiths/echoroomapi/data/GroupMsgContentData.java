package com.caiths.echoroomapi.data;

import com.alibaba.excel.annotation.ExcelIgnore;  // EasyExcel 忽略字段注解，表示字段不导出到 Excel
import com.alibaba.excel.annotation.ExcelProperty;  // EasyExcel 属性注解，指定 Excel 列名
import com.alibaba.excel.annotation.write.style.ColumnWidth;  // EasyExcel 列宽注解，设置列宽度
import com.alibaba.excel.annotation.write.style.ContentRowHeight;  // EasyExcel 行高注解，设置内容行高度
import com.alibaba.excel.converters.url.UrlImageConverter;  // EasyExcel URL 图片转换器（未使用，仅导入）
import com.fasterxml.jackson.annotation.JsonFormat;  // Jackson 日期格式化注解，指定 JSON 序列化格式
import com.caiths.echoroomapi.converter.MyUrlImageConverter;  // 自定义图片 URL 转换器

import java.net.URL;  // URL 类，表示网络资源地址
import java.util.Date;  // Date 类，表示日期和时间

/**
 * 用于 Excel 导入导出的群聊消息数据类。
 * 该类定义了群聊消息的属性，并使用注解配置 Excel 导出时的列宽、行高和数据转换规则。
 *
 * @author poboll
 * @since 2025-02-26 23:52
 */
@ColumnWidth(25)  // 设置默认列宽为 25
@ContentRowHeight(30)  // 设置内容行高为 30
public class GroupMsgContentData {

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
     * 发送者的头像 URL，在 Excel 导出中忽略。
     */
    @ExcelIgnore  // 不导出到 Excel
    private URL fromProfile;

    /**
     * 消息发送时间，格式化为 "yyyy-MM-dd HH:mm:ss"，使用 GMT+8 时区。
     */
    @ExcelProperty("发送时间")  // Excel 列名为 "发送时间"
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")  // JSON 序列化为指定格式
    private Date createTime;

    /**
     * 文本内容，列宽设置为 50。
     */
    @ExcelProperty(value = {"内容", "文本"})  // 多级表头，Excel 显示为 "内容" -> "文本"
    @ColumnWidth(50)  // 单独设置列宽为 50
    private String textContent;

    /**
     * 图片内容 URL，使用自定义转换器 MyUrlImageConverter，列宽设置为 50。
     */
    @ExcelProperty(value = {"内容", "图片"}, converter = MyUrlImageConverter.class)  // 多级表头，使用自定义转换器
    @ColumnWidth(50)  // 单独设置列宽为 50
    private URL imageContent;

    /**
     * 消息类型 ID，在 Excel 导出中忽略。
     */
    @ExcelIgnore  // 不导出到 Excel
    private Integer messageTypeId;

    // Getter 和 Setter 方法

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
     * 获取发送者的头像 URL。
     *
     * @return 头像 URL
     */
    public URL getFromProfile() {
        return fromProfile;
    }

    /**
     * 设置发送者的头像 URL。
     *
     * @param fromProfile 头像 URL
     */
    public void setFromProfile(URL fromProfile) {
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
     * 获取文本内容。
     *
     * @return 文本内容
     */
    public String getTextContent() {
        return textContent;
    }

    /**
     * 设置文本内容。
     *
     * @param textContent 文本内容
     */
    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    /**
     * 获取图片内容 URL。
     *
     * @return 图片内容 URL
     */
    public URL getImageContent() {
        return imageContent;
    }

    /**
     * 设置图片内容 URL。
     *
     * @param imageContent 图片内容 URL
     */
    public void setImageContent(URL imageContent) {
        this.imageContent = imageContent;
    }

    /**
     * 获取消息类型 ID。
     *
     * @return 消息类型 ID
     */
    public Integer getMessageTypeId() {
        return messageTypeId;
    }

    /**
     * 设置消息类型 ID。
     *
     * @param messageTypeId 消息类型 ID
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
        return "GroupMsgContentData{" +
                "id=" + id +
                ", fromId=" + fromId +
                ", fromName='" + fromName + '\'' +
                ", fromProfile=" + fromProfile +
                ", createTime=" + createTime +
                ", textContent='" + textContent + '\'' +
                ", imageContent=" + imageContent +
                ", messageTypeId=" + messageTypeId +
                '}';
    }
}
