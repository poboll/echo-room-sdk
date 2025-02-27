package com.caiths.echoroomapi.utils;

import net.coobird.thumbnailator.Thumbnails;  // Thumbnailator 库，用于图片压缩和处理
import org.slf4j.Logger;  // SLF4J 日志接口
import org.slf4j.LoggerFactory;  // SLF4J 日志工厂类

import java.io.ByteArrayInputStream;  // 字节数组输入流类
import java.io.ByteArrayOutputStream;  // 字节数组输出流类

/**
 * 图片处理工具类 (ImgUtil)。
 * <p>
 * 该类封装了基于 Thumbnailator 的图片压缩功能，用于将图片压缩到指定大小。
 * </p>
 *
 * @author poboll
 * @since 2025-02-26
 */
public class ImgUtil {

    /**
     * 日志记录器，用于记录图片压缩过程中的信息和异常。
     */
    private static Logger logger = LoggerFactory.getLogger(ImgUtil.class);

//    public static void main(String[] args) throws IOException {
//        byte[] bytes = FileUtils.readFileToByteArray(new File("D:\\1.jpg"));
//        long l = System.currentTimeMillis();
//        bytes = ImgUtil.compressPicForScale(bytes, 300, "x");// 图片小于300kb
//        System.out.println(System.currentTimeMillis() - l);
//        FileUtils.writeByteArrayToFile(new File("D:\\dd1.jpg"), bytes);
//    }

    /**
     * 根据指定大小压缩图片。
     * 将输入的图片字节数组压缩到指定大小（单位 KB），并返回压缩后的字节数组。
     *
     * @param imageBytes  源图片字节数组
     * @param desFileSize 指定图片大小，单位 KB
     * @param imageId     影像编号，用于日志记录
     * @return 压缩后的图片字节数组
     */
    public static byte[] compressPicForScale(byte[] imageBytes, long desFileSize, String imageId) {
        // 检查输入参数是否有效，若图片为空或已小于目标大小，则直接返回
        if (imageBytes == null || imageBytes.length <= 0 || imageBytes.length < desFileSize * 1024) {
            return imageBytes;
        }

        long srcSize = imageBytes.length;  // 源图片大小（字节）
        double accuracy = 0.4;  // 默认压缩精度，可根据需求调整

        try {
            // 当图片大小超过目标大小时，循环压缩
            while (imageBytes.length > desFileSize * 1024) {
                ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);  // 创建输入流
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream(imageBytes.length);  // 创建输出流

                // 使用 Thumbnailator 进行压缩
                Thumbnails.of(inputStream)
                        .scale(accuracy)  // 按比例缩放
                        .outputQuality(accuracy)  // 设置输出质量
                        .toOutputStream(outputStream);  // 输出到流

                imageBytes = outputStream.toByteArray();  // 更新图片字节数组
            }

            // 记录压缩信息
            logger.info("【图片压缩】imageId={} | 图片原大小={}kb | 压缩后大小={}kb",
                    imageId, srcSize / 1024, imageBytes.length / 1024);
        } catch (Exception e) {
            // 记录压缩失败的异常信息
            logger.error("【图片压缩】msg=图片压缩失败!", e);
        }

        return imageBytes;
    }

    /**
     * 自动调节压缩精度（经验数值）。
     * 根据源图片大小返回合适的压缩质量比。
     *
     * @param size 源图片大小，单位 KB
     * @return 图片压缩质量比，范围在 0.0 到 1.0 之间
     */
    private static double getAccuracy(long size) {
        double accuracy;
        if (size < 900) {
            accuracy = 0.5;  // 小于 900KB，使用 0.5 精度
        } else if (size < 2047) {
            accuracy = 0.6;  // 小于 2047KB，使用 0.6 精度
        } else if (size < 3275) {
            accuracy = 0.44;  // 小于 3275KB，使用 0.44 精度
        } else {
            accuracy = 0.4;  // 3275KB 以上，使用 0.4 精度
        }
        return accuracy;
    }

    /**
     * 返回对象的字符串表示形式，便于调试和日志记录。
     *
     * @return 对象的字符串表示
     */
    @Override
    public String toString() {
        return "ImgUtil{}";
    }
}
