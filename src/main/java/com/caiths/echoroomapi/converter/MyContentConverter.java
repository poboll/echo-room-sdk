package com.caiths.echoroomapi.converter;

import com.alibaba.excel.converters.Converter;  // EasyExcel 转换器接口，用于定义 Excel 数据转换逻辑
import com.alibaba.excel.enums.CellDataTypeEnum;  // Excel 单元格数据类型枚举，用于指定单元格类型
import com.alibaba.excel.metadata.CellData;       // Excel 单元格数据对象，表示单元格内容
import com.alibaba.excel.metadata.GlobalConfiguration;  // EasyExcel 全局配置，提供全局设置
import com.alibaba.excel.metadata.property.ExcelContentProperty;  // Excel 内容属性，描述字段信息
import com.alibaba.excel.util.IoUtils;  // EasyExcel 的 IO 工具类，用于处理输入输出流
import com.caiths.echoroomapi.utils.ImgUtil;  // 自定义图片处理工具类，提供图片压缩等功能
import org.slf4j.Logger;  // 日志记录接口，用于记录运行时信息
import org.slf4j.LoggerFactory;  // 日志工厂，用于创建 Logger 实例

import java.io.IOException;  // IO 异常类，用于处理输入输出相关错误
import java.io.InputStream;  // 输入流类，用于读取数据
import java.net.HttpURLConnection;  // HTTP 连接类，用于发起网络请求
import java.net.URL;  // URL 类，用于表示网络资源地址
import java.util.UUID;  // UUID 生成器，用于生成唯一标识符

/**
 * 处理聊天记录内容导出到 Excel 的转换器。
 * <p>
 * 如果内容是图片 URL，则下载并压缩后作为图片嵌入 Excel；否则作为普通字符串处理。
 * </p>
 *
 * @author poboll
 * @since 2025-02-26
 */
public class MyContentConverter implements Converter<String> {

    private static final Logger logger = LoggerFactory.getLogger(MyContentConverter.class);  // 日志记录器，用于记录转换过程中的日志
    private static final int TIMEOUT = 5000;  // 连接和读取超时时间（单位：毫秒）

    /**
     * 指定支持的 Java 数据类型。
     * @return 返回 String.class，表示此转换器处理字符串类型的 Java 数据
     */
    @Override
    public Class supportJavaTypeKey() {
        return String.class;
    }

    /**
     * 指定支持的 Excel 数据类型。
     * @return 返回 CellDataTypeEnum.IMAGE，表示默认支持图片类型（但也可能返回字符串类型）
     */
    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.IMAGE;
    }

    /**
     * 从 Excel 数据转换为 Java 数据（读取 Excel 时调用）。
     * @param cellData Excel 单元格数据，包含单元格的值和类型
     * @param contentProperty Excel 内容属性，描述字段的元信息
     * @param globalConfiguration 全局配置，提供全局设置
     * @return 不支持此操作，直接抛出异常
     * @throws UnsupportedOperationException 表示不支持从 Excel 图片转换为 Java 字符串
     */
    @Override
    public String convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
                                    GlobalConfiguration globalConfiguration) {
        throw new UnsupportedOperationException("无法将图片转换为字符串");
    }

    /**
     * 将 Java 数据（字符串）转换为 Excel 数据（写入 Excel 时调用）。
     * @param value 输入的字符串，可能是图片 URL 或普通文本
     * @param contentProperty Excel 内容属性（本例未使用）
     * @param globalConfiguration 全局配置（本例未使用）
     * @return 返回 CellData 对象，包含图片字节数据或字符串数据
     * @throws IOException 如果下载图片或处理数据时发生 IO 错误
     */
    @Override
    public CellData convertToExcelData(String value, ExcelContentProperty contentProperty,
                                       GlobalConfiguration globalConfiguration) throws IOException {
        // 如果输入为 null，转换为空字符串处理
        if (value == null) {
            logger.debug("输入为 null，转换为空字符串处理");
            return new CellData("");  // EasyExcel 不允许 StringValue 为 null，使用空字符串
        }

        // 如果是空字符串或不以 "http" 开头，作为普通字符串处理
        String trimmedValue = value.trim();
        if (trimmedValue.isEmpty() || !trimmedValue.toLowerCase().startsWith("http")) {
            logger.debug("非 URL 内容或空字符串，直接作为字符串处理: {}", trimmedValue);
            return new CellData(trimmedValue);  // 直接返回字符串类型的 CellData
        }

        // 检查是否可能是图片 URL
        if (!isImageUrl(trimmedValue)) {
            logger.debug("URL 不符合图片格式，直接作为字符串处理: {}", trimmedValue);
            return new CellData(trimmedValue);  // 非图片 URL 作为字符串处理
        }

        InputStream inputStream = null;
        try {
            URL imageUrl = new URL(trimmedValue);
            HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
            connection.setConnectTimeout(TIMEOUT);  // 设置连接超时时间
            connection.setReadTimeout(TIMEOUT);     // 设置读取超时时间
            connection.setRequestMethod("GET");     // 设置请求方法为 GET

            int statusCode = connection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream();
                byte[] bytes = IoUtils.toByteArray(inputStream);  // 将输入流转换为字节数组
                byte[] compressBytes = ImgUtil.compressPicForScale(bytes, 200, UUID.randomUUID().toString());  // 压缩图片
                logger.info("成功转换图片 URL 为 Excel 图片数据: {}", trimmedValue);
                return new CellData(compressBytes);  // 返回图片数据的 CellData
            } else {
                logger.warn("图片 URL 请求失败，状态码: {}，URL: {}", statusCode, trimmedValue);
                return new CellData(trimmedValue);  // 请求失败时作为字符串处理
            }
        } catch (Exception e) {
            logger.error("处理图片 URL 失败: {}，错误: {}", trimmedValue, e.getMessage());
            return new CellData(trimmedValue);  // 异常时作为字符串处理
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();  // 关闭输入流
                } catch (IOException e) {
                    logger.error("关闭输入流失败: {}", e.getMessage());
                }
            }
        }
    }

    /**
     * 检查字符串是否可能是图片 URL。
     * @param url 输入的字符串，表示可能的图片 URL
     * @return 如果以常见图片格式结尾（如 .jpg, .png 等），返回 true；否则返回 false
     */
    private boolean isImageUrl(String url) {
        if (url == null) {
            return false;
        }
        String lowerUrl = url.toLowerCase();
        return lowerUrl.endsWith(".jpg") || lowerUrl.endsWith(".png") ||
                lowerUrl.endsWith(".jpeg") || lowerUrl.endsWith(".gif");
    }
}
