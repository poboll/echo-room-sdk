package com.caiths.echoroomapi;

import com.alibaba.excel.util.IoUtils;  // EasyExcel 的 IO 工具类
import com.caiths.echoroomapi.utils.ImgUtil;  // 图片处理工具类
import java.io.IOException;  // 输入输出异常类
import java.io.InputStream;  // 输入流类
import java.net.HttpURLConnection;  // HTTP 连接类
import java.net.URL;  // URL 类
import java.net.URLConnection;  // URL 连接类
import java.util.UUID;  // UUID 生成类

/**
 * 测试类 (Test)。
 * <p>
 * 该类用于测试从 URL 下载图片并进行压缩的功能。
 * </p>
 *
 * @author poboll
 * @since 2025-02-27
 */
public class Test {

    /**
     * 主方法，用于测试图片处理功能。
     * 调用 test 方法并打印结果。
     *
     * @param args 命令行参数（未使用）
     * @throws IOException 如果网络请求或流操作失败
     */
    public static void main(String[] args) throws IOException {
        System.out.println(test("http://"));
    }

    /**
     * 测试方法，处理输入值并返回结果。
     * 如果输入是以 "http" 开头的 URL，则尝试下载图片并压缩；否则直接返回原值。
     *
     * @param value 输入值，可以是 URL 或普通字符串
     * @return 处理结果，成功压缩返回 "success"，否则返回原值
     * @throws IOException 如果网络请求或流操作失败
     */
    public static String test(String value) throws IOException {
        if (value.toLowerCase().startsWith("http")) {  // 检查是否为 URL
            InputStream inputStream = null;
            URL imageUrl = new URL(value);  // 创建 URL 对象

            try {
                // 开启 URL 连接
                URLConnection uc = imageUrl.openConnection();
                HttpURLConnection httpConn = (HttpURLConnection) uc;
                httpConn.connect();  // 建立连接

                // 获取 HTTP 响应状态码
                int statusCode = httpConn.getResponseCode();
                switch (statusCode) {
                    case 200:  // 响应成功
                        inputStream = imageUrl.openStream();  // 获取图片输入流
                        break;
                    default:  // 其他状态码
                        return value;  // 直接返回原值
                }

                // 将输入流转换为字节数组
                byte[] bytes = IoUtils.toByteArray(inputStream);
                // 压缩图片至 200KB 以下，使用随机 UUID 作为图片编号
                byte[] compressBytes = ImgUtil.compressPicForScale(bytes, 200, UUID.randomUUID().toString());
                return "success";  // 压缩成功返回

            } catch (Exception exception) {
                // 捕获异常（如网络错误），返回原值
                return value;
            } finally {
                // 确保输入流关闭
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        } else {
            return value;  // 非 URL，直接返回原值
        }
    }
}
