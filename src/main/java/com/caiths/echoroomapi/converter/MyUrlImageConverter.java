package com.caiths.echoroomapi.converter;

import com.alibaba.excel.converters.Converter;  // EasyExcel 的转换器接口，用于定义数据转换逻辑
import com.alibaba.excel.enums.CellDataTypeEnum;  // Excel 单元格数据类型的枚举，用于指定单元格数据类型
import com.alibaba.excel.metadata.CellData;       // 表示 Excel 单元格数据，封装单元格内容
import com.alibaba.excel.metadata.GlobalConfiguration;  // EasyExcel 的全局配置，提供全局设置
import com.alibaba.excel.metadata.property.ExcelContentProperty;  // Excel 内容属性，描述字段信息
import com.alibaba.excel.util.IoUtils;  // EasyExcel 提供的 IO 工具类，用于流操作
import com.caiths.echoroomapi.utils.ImgUtil;  // 自定义图片处理工具类，提供图片压缩功能

import java.io.FileNotFoundException;  // 文件未找到异常类
import java.io.IOException;  // IO 异常类，用于处理输入输出异常
import java.io.InputStream;  // 输入流类，用于读取数据
import java.net.ConnectException;  // 连接异常类，用于处理网络连接失败
import java.net.HttpURLConnection;  // HTTP 连接类，用于建立 HTTP 请求
import java.net.URL;  // URL 类，表示网络资源地址
import java.net.URLConnection;  // URL 连接抽象类，提供与 URL 的连接功能
import java.util.UUID;  // UUID 生成器，用于生成唯一标识符

/**
 * 将 URL 图片转换为 Excel 图片数据的格式转换器。
 * <p>
 * 如果 URL 有效，则下载并压缩图片嵌入 Excel；如果 URL 无效或异常，则使用默认图片替代。
 * </p>
 *
 * @author poboll
 * @since 2025-02-26
 */
public class MyUrlImageConverter implements Converter<URL> {

    /**
     * 指定此转换器支持的 Java 数据类型。
     *
     * @return 返回 URL.class，表示支持 URL 类型
     */
    @Override
    public Class supportJavaTypeKey() {
        return URL.class;
    }

    /**
     * 指定此转换器支持的 Excel 单元格数据类型。
     *
     * @return 返回 CellDataTypeEnum.IMAGE，表示转换为图片类型
     */
    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.IMAGE;
    }

    /**
     * 将 Excel 数据转换为 Java 数据（从 Excel 读取时调用）。
     * 本实现不支持此功能，直接抛出异常。
     *
     * @param cellData Excel 单元格数据
     * @param contentProperty Excel 内容属性
     * @param globalConfiguration 全局配置
     * @return 不支持此操作，直接抛出异常
     */
    @Override
    public URL convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
                                 GlobalConfiguration globalConfiguration) {
        throw new UnsupportedOperationException("无法将图片转换为 URL");
    }

    /**
     * 将 Java 数据（URL）转换为 Excel 数据（写入 Excel 时调用）。
     *
     * @param value 输入的 URL 对象，表示图片的网络地址
     * @param contentProperty Excel 内容属性（本例未使用）
     * @param globalConfiguration 全局配置（本例未使用）
     * @return 返回 CellData 对象，包含图片的字节数据
     * @throws IOException 如果 IO 操作失败
     */
    @Override
    public CellData convertToExcelData(URL value, ExcelContentProperty contentProperty,
                                       GlobalConfiguration globalConfiguration) throws IOException {
        InputStream inputStream = null;  // 用于读取图片数据的输入流
        try {
            // 开启与 URL 的连接
            URLConnection uc = value.openConnection();
            HttpURLConnection httpConn = (HttpURLConnection) uc;
            URL defaultUrl = null;  // 备用 URL，用于存放默认图片地址

            // 获取 HTTP 响应状态码
            int statusCode = httpConn.getResponseCode();
            switch (statusCode) {
                case HttpURLConnection.HTTP_OK:  // 请求成功 (200)
                    inputStream = value.openStream();  // 打开输入流读取图片数据
                    break;
                case HttpURLConnection.HTTP_NOT_FOUND:  // 资源未找到 (404)
                    // 使用默认图片替代
                    defaultUrl = new URL("https://c-ssl.duitang.com/uploads/blog/202402/17/N5SG04P6iPy7qXw.jpg");
                    inputStream = defaultUrl.openStream();  // 打开默认图片的输入流
                    break;
                default:  // 其他状态码
                    // 使用相同的默认图片替代
                    defaultUrl = new URL("https://c-ssl.duitang.com/uploads/blog/202402/17/N5SG04P6iPy7qXw.jpg");
                    inputStream = defaultUrl.openStream();  // 打开默认图片的输入流
                    break;
            }

            // 将输入流转换为字节数组
            byte[] bytes = IoUtils.toByteArray(inputStream);
            // 使用 ImgUtil 压缩图片，最大大小 200KB，文件名使用随机 UUID
            byte[] compressBytes = ImgUtil.compressPicForScale(bytes, 200, UUID.randomUUID().toString());
            // 返回包含压缩后图片数据的 CellData 对象
            return new CellData(compressBytes);

        } catch (ConnectException exception) {
            // 处理连接异常，使用默认图片
            URL defaultUrl = new URL("https://c-ssl.duitang.com/uploads/blog/202402/17/N5SG04P6iPy7qXw.jpg");
            inputStream = defaultUrl.openStream();  // 打开默认图片的输入流
            byte[] bytes = IoUtils.toByteArray(inputStream);  // 转换为字节数组
            return new CellData(bytes);  // 返回包含默认图片数据的 CellData

        } catch (FileNotFoundException fileNotFoundException) {
            // 处理文件未找到异常，使用默认图片
            URL defaultUrl = new URL("https://c-ssl.duitang.com/uploads/blog/202402/17/N5SG04P6iPy7qXw.jpg");
            inputStream = defaultUrl.openStream();  // 打开默认图片的输入流
            byte[] bytes = IoUtils.toByteArray(inputStream);  // 转换为字节数组
            return new CellData(bytes);  // 返回包含默认图片数据的 CellData

        } finally {
            // 确保输入流被关闭，避免资源泄漏
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // 关闭流失败时的异常处理，通常记录日志，此处省略
                }
            }
        }
    }
}
