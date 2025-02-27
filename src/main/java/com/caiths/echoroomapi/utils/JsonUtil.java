package com.caiths.echoroomapi.utils;

import com.alibaba.fastjson.JSON;  // Alibaba FastJSON 核心类，用于 JSON 序列化和反序列化
import com.alibaba.fastjson.parser.Feature;  // FastJSON 解析特性类

/**
 * JSON 操作工具类 (JsonUtil)。
 * <p>
 * 该类封装了 FastJSON 的常用方法，提供统一的 JSON 序列化和反序列化接口，便于替换其他 JSON 工具。
 * </p>
 *
 * @author poboll
 * @since 2025-02-26
 */
public class JsonUtil extends JSON {

    /**
     * 将对象序列化为 JSON 字符串。
     * 将任意对象转换为 JSON 格式的字符串表示。
     *
     * @param object 要序列化的对象，可以是任意类型
     * @return JSON 格式的字符串
     */
    public static String parseToString(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * 将 JSON 字符串反序列化为指定类型的对象。
     * 根据提供的类类型，将 JSON 字符串解析为对应的对象实例。
     *
     * @param text  JSON 格式的字符串
     * @param clazz 目标对象的类类型
     * @param <T>   泛型类型，表示返回对象的类型
     * @return 解析后的对象实例
     */
    public static <T> T parseToObject(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }
}
