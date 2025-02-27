//package com.caiths.echoroomapi.converter;
//
//import com.alibaba.excel.enums.CellDataTypeEnum;
//import com.alibaba.excel.metadata.CellData;
//import com.alibaba.excel.metadata.GlobalConfiguration;
//import com.alibaba.excel.metadata.property.ExcelContentProperty;
//import com.alibaba.excel.util.IoUtils;
//import com.caiths.echoroomapi.utils.ImgUtil;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.MockedStatic;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.ConnectException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLConnection;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
///**
// * @author poboll
// * @description: 测试 MyUrlImageConverter 的功能
// * @date 2025/2/26
// */
//class MyUrlImageConverterTest {
//
//    private MyUrlImageConverter converter;
//    private URL validUrl;
//    private URL invalidUrl;
//    private ExcelContentProperty contentProperty;
//    private GlobalConfiguration globalConfiguration;
//
//    @BeforeEach
//    void setUp() throws Exception {
//        converter = new MyUrlImageConverter();
//        validUrl = new URL("https://example.com/valid-image.jpg");
//        invalidUrl = new URL("https://example.com/invalid-image.jpg");
//        contentProperty = null;  // 本例中未使用
//        globalConfiguration = null;  // 本例中未使用
//    }
//
//    /**
//     * 测试支持的 Java 类型是否为 URL
//     */
//    @Test
//    void testSupportJavaTypeKey() {
//        assertEquals(URL.class, converter.supportJavaTypeKey(), "支持的 Java 类型应为 URL");
//    }
//
//    /**
//     * 测试支持的 Excel 类型是否为 IMAGE
//     */
//    @Test
//    void testSupportExcelTypeKey() {
//        assertEquals(CellDataTypeEnum.IMAGE, converter.supportExcelTypeKey(), "支持的 Excel 类型应为 IMAGE");
//    }
//
//    /**
//     * 测试 convertToJavaData 方法是否抛出不支持的异常
//     */
//    @Test
//    void testConvertToJavaData() {
//        CellData cellData = new CellData(new byte[]{});
//        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, () -> {
//            converter.convertToJavaData(cellData, contentProperty, globalConfiguration);
//        });
//        assertEquals("Cannot convert images to url.", exception.getMessage(), "应抛出正确的异常信息");
//    }
//
//    /**
//     * 测试正常图片 URL 的转换（状态码 200）
//     */
//    @Test
//    void testConvertToExcelDataWithValidUrl() throws IOException {
//        // 模拟 HttpURLConnection
//        HttpURLConnection mockConnection = mock(HttpURLConnection.class);
//        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
//        InputStream mockInputStream = new ByteArrayInputStream(new byte[]{1, 2, 3});
//        when(mockConnection.getInputStream()).thenReturn(mockInputStream);
//
//        // 使用 MockedStatic 模拟 URL 的行为
//        try (MockedStatic<URL> urlMock = mockStatic(URL.class)) {
//            // 仅模拟特定 URL 的 openConnection()
//            urlMock.when(() -> validUrl.openConnection()).thenReturn(mockConnection);
//
//            // 模拟 IoUtils 和 ImgUtil
//            try (MockedStatic<IoUtils> ioUtils = mockStatic(IoUtils.class);
//                 MockedStatic<ImgUtil> imgUtil = mockStatic(ImgUtil.class)) {
//                ioUtils.when(() -> IoUtils.toByteArray(any(InputStream.class))).thenReturn(new byte[]{1, 2, 3});
//                imgUtil.when(() -> ImgUtil.compressPicForScale(any(byte[].class), eq(200), anyString()))
//                        .thenReturn(new byte[]{4, 5, 6});
//
//                CellData result = converter.convertToExcelData(validUrl, contentProperty, globalConfiguration);
//
//                assertNotNull(result, "转换结果不应为空");
//                assertArrayEquals(new byte[]{4, 5, 6}, result.getImageValue(), "图片数据应正确压缩并返回");
//                verify(mockConnection).getInputStream();  // 验证输入流被调用
//            }
//        }
//    }
//
//    /**
//     * 测试 404 状态码时使用默认图片
//     */
//    @Test
//    void testConvertToExcelDataWith404Url() throws IOException {
//        // 模拟网络连接返回 404
//        HttpURLConnection mockConnection = mock(HttpURLConnection.class);
//        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_NOT_FOUND);
//
//        // 模拟默认图片的连接
//        HttpURLConnection mockDefaultConnection = mock(HttpURLConnection.class);
//        InputStream mockDefaultInputStream = new ByteArrayInputStream(new byte[]{7, 8, 9});
//        when(mockDefaultConnection.getInputStream()).thenReturn(mockDefaultInputStream);
//        URL defaultUrl = new URL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1598096095144&di=9a72ad26e83effb9341c711c9818b85f&imgtype=0&src=http%3A%2F%2Fpic.616pic.com%2Fys_bnew_img%2F00%2F11%2F69%2Fj2AjnHspwT.jpg");
//
//        // 模拟 URL 的行为
//        try (MockedStatic<URL> urlMock = mockStatic(URL.class)) {
//            urlMock.when(() -> validUrl.openConnection()).thenReturn(mockConnection);
//            urlMock.when(() -> defaultUrl.openStream()).thenReturn(mockDefaultInputStream);
//
//            // 模拟 IoUtils 和 ImgUtil
//            try (MockedStatic<IoUtils> ioUtils = mockStatic(IoUtils.class);
//                 MockedStatic<ImgUtil> imgUtil = mockStatic(ImgUtil.class)) {
//                ioUtils.when(() -> IoUtils.toByteArray(any(InputStream.class))).thenReturn(new byte[]{7, 8, 9});
//                imgUtil.when(() -> ImgUtil.compressPicForScale(any(byte[].class), eq(200), anyString()))
//                        .thenReturn(new byte[]{10, 11, 12});
//
//                CellData result = converter.convertToExcelData(validUrl, contentProperty, globalConfiguration);
//
//                assertNotNull(result, "转换结果不应为空");
//                assertArrayEquals(new byte[]{10, 11, 12}, result.getImageValue(), "应返回默认图片的压缩数据");
//                verify(mockConnection).getResponseCode();  // 验证状态码被检查
//            }
//        }
//    }
//
//    /**
//     * 测试连接异常时使用默认图片
//     */
//    @Test
//    void testConvertToExcelDataWithConnectException() throws IOException {
//        // 模拟默认图片的连接
//        InputStream mockDefaultInputStream = new ByteArrayInputStream(new byte[]{13, 14, 15});
//        URL defaultUrl = new URL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1598096095144&di=9a72ad26e83effb9341c711c9818b85f&imgtype=0&src=http%3A%2F%2Fpic.616pic.com%2Fys_bnew_img%2F00%2F11%2F69%2Fj2AjnHspwT.jpg");
//
//        // 模拟 URL 的行为
//        try (MockedStatic<URL> urlMock = mockStatic(URL.class)) {
//            urlMock.when(() -> validUrl.openConnection()).thenThrow(new ConnectException("连接失败"));
//            urlMock.when(() -> defaultUrl.openStream()).thenReturn(mockDefaultInputStream);
//
//            // 模拟 IoUtils
//            try (MockedStatic<IoUtils> ioUtils = mockStatic(IoUtils.class)) {
//                ioUtils.when(() -> IoUtils.toByteArray(any(InputStream.class))).thenReturn(new byte[]{13, 14, 15});
//
//                CellData result = converter.convertToExcelData(validUrl, contentProperty, globalConfiguration);
//
//                assertNotNull(result, "转换结果不应为空");
//                assertArrayEquals(new byte[]{13, 14, 15}, result.getImageValue(), "应返回默认图片数据");
//            }
//        }
//    }
//
//    /**
//     * 测试文件未找到异常时使用默认图片
//     */
//    @Test
//    void testConvertToExcelDataWithFileNotFoundException() throws IOException {
//        // 模拟网络连接
//        HttpURLConnection mockConnection = mock(HttpURLConnection.class);
//        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
//        when(mockConnection.getInputStream()).thenThrow(new java.io.FileNotFoundException("文件未找到"));
//
//        // 模拟默认图片的连接
//        InputStream mockDefaultInputStream = new ByteArrayInputStream(new byte[]{16, 17, 18});
//        URL defaultUrl = new URL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1598096095144&di=9a72ad26e83effb9341c711c9818b85f&imgtype=0&src=http%3A%2F%2Fpic.616pic.com%2Fys_bnew_img%2F00%2F11%2F69%2Fj2AjnHspwT.jpg");
//
//        // 模拟 URL 的行为
//        try (MockedStatic<URL> urlMock = mockStatic(URL.class)) {
//            urlMock.when(() -> validUrl.openConnection()).thenReturn(mockConnection);
//            urlMock.when(() -> defaultUrl.openStream()).thenReturn(mockDefaultInputStream);
//
//            // 模拟 IoUtils
//            try (MockedStatic<IoUtils> ioUtils = mockStatic(IoUtils.class)) {
//                ioUtils.when(() -> IoUtils.toByteArray(any(InputStream.class))).thenReturn(new byte[]{16, 17, 18});
//
//                CellData result = converter.convertToExcelData(validUrl, contentProperty, globalConfiguration);
//
//                assertNotNull(result, "转换结果不应为空");
//                assertArrayEquals(new byte[]{16, 17, 18}, result.getImageValue(), "应返回默认图片数据");
//                verify(mockConnection).getInputStream();  // 验证异常被触发
//            }
//        }
//    }
//}