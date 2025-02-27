package com.caiths.echoroomapi.utils;

import com.fasterxml.jackson.core.JsonProcessingException;  // Jackson JSON 处理异常类
import com.fasterxml.jackson.databind.ObjectMapper;  // Jackson 对象映射类，用于 JSON 解析
import java.io.BufferedReader;  // 缓冲读取类
import java.io.IOException;  // 输入输出异常类
import java.io.InputStreamReader;  // 输入流读取类
import java.net.HttpURLConnection;  // HTTP 连接类
import java.net.URL;  // URL 类
import java.net.URLEncoder;  // URL 编码类
import java.util.HashMap;  // HashMap 类，用于存储解析后的 JSON 数据

/**
 * 智能回复机器人工具类 (TuLingUtil)。
 * <p>
 * 该类封装了与图灵机器人 API 的交互功能，用于发送消息并获取智能回复。
 * </p>
 *
 * @author poboll
 * @since 2025-02-26
 */
public class TuLingUtil {

  /**
   * Jackson 对象映射器，用于解析 JSON 字符串。
   */
  private static ObjectMapper MAPPER = new ObjectMapper();

  /**
   * 发送消息并获取图灵机器人的回复。
   * 通过 HTTP 请求调用图灵机器人 API，返回回复消息的 JSON 字符串。
   *
   * @param message 要发送的消息内容
   * @return 图灵机器人返回的 JSON 格式回复字符串
   * @throws IOException 如果网络连接或读取操作失败
   */
  public static String replyMessage(String message) throws IOException {
    String APIKEY = "40445cf23e2144828218d7fc95d6f05a";  // 图灵机器人 API 密钥
    String INFO = URLEncoder.encode(message, "utf-8");  // 对消息进行 URL 编码
    String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO;  // 构造请求 URL
    URL getUrl = new URL(getURL);  // 创建 URL 对象
    HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();  // 打开 HTTP 连接
    connection.connect();  // 建立连接

    // 读取响应流
    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
    StringBuffer sb = new StringBuffer();
    String line;
    while ((line = reader.readLine()) != null) {
      sb.append(line);  // 逐行读取响应内容
    }
    reader.close();  // 关闭读取流
    connection.disconnect();  // 断开连接

    return parseMess(sb.toString());  // 解析并返回回复内容
  }

  /**
   * 解析图灵机器人返回的 JSON 字符串，提取回复文本。
   * 将 JSON 字符串转换为 HashMap 并获取其中的 "text" 字段内容。
   *
   * @param jsonStr 图灵机器人返回的 JSON 字符串
   * @return 提取的回复文本
   * @throws JsonProcessingException 如果 JSON 解析失败
   */
  public static String parseMess(String jsonStr) throws JsonProcessingException {
    HashMap resultMap = MAPPER.readValue(jsonStr, HashMap.class);  // 将 JSON 解析为 HashMap
    String result = (String) resultMap.get("text");  // 获取 "text" 字段值
    return result;
  }

  /**
   * 主方法，用于测试图灵机器人回复功能。
   * 示例代码，发送消息并打印返回的回复文本。
   *
   * @param args 命令行参数（未使用）
   * @throws IOException 如果网络请求或解析失败
   */
  public static void main(String[] args) throws IOException {
    String jsonStr = replyMessage("https://c-ssl.duitang.com/uploads/blog/202402/25/jJS1pp9GuemG74d.jpg");
    HashMap resultMap = MAPPER.readValue(jsonStr, HashMap.class);  // 解析 JSON
    System.out.println(resultMap.get("text"));  // 打印回复文本
  }

  /**
   * 返回对象的字符串表示形式，便于调试和日志记录。
   *
   * @return 对象的字符串表示
   */
  @Override
  public String toString() {
    return "TuLingUtil{}";
  }
}
