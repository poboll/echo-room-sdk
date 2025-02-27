package com.caiths.echoroomapi.converter;

import org.slf4j.Logger;  // 日志接口，用于记录日志信息
import org.slf4j.LoggerFactory;  // 日志工厂，用于创建 Logger 实例
import org.springframework.core.convert.converter.Converter;  // Spring 提供的转换器接口，用于类型转换
import org.springframework.stereotype.Component;  // Spring 组件注解，标记此类为 Spring 管理的 Bean

import java.text.ParseException;  // 日期解析异常类，用于处理解析过程中的错误
import java.text.SimpleDateFormat;  // 日期格式化工具类，用于解析和格式化日期
import java.util.Date;  // Java 的日期类，表示日期和时间

/**
 * 自定义日期转换器，将字符串转换为 Date 对象。
 * <p>
 * 支持多种日期格式，优先尝试 "yyyy-MM-dd HH:mm:ss"，后备格式包括 "yyyy-MM-dd" 等。
 * 对于无效日期，返回 null。
 * </p>
 *
 * @author poboll
 * @since 2025-02-26
 */
@Component
public class DateConverter implements Converter<String, Date> {

  private static final Logger logger = LoggerFactory.getLogger(DateConverter.class);  // 日志记录器，用于记录转换过程中的日志

  // 支持的日期格式数组，按顺序尝试解析
  private static final String[] SUPPORTED_FORMATS = {
          "yyyy-MM-dd HH:mm:ss",  // 完整日期时间格式，例如 "2025-02-26 16:28:00"
          "yyyy-MM-dd",           // 仅日期格式，例如 "2025-02-26"
          "yyyy/MM/dd HH:mm:ss",  // 斜杠分隔的日期时间格式，例如 "2025/02/26 16:28:00"
          "yyyy/MM/dd"            // 斜杠分隔的仅日期格式，例如 "2025/02/26"
  };

  // 主日期格式化器，默认使用 "yyyy-MM-dd HH:mm:ss"
  private final SimpleDateFormat defaultDateFormat;

  /**
   * 构造函数，初始化默认日期格式化器并设置为严格模式。
   */
  public DateConverter() {
    this.defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    this.defaultDateFormat.setLenient(false);  // 设置为严格模式，避免自动调整无效日期
  }

  /**
   * 将字符串转换为 Date 对象。
   *
   * @param source 输入的字符串，可能是日期格式，例如 "2025-02-26 16:28:00"
   * @return 转换后的 Date 对象，如果解析失败或输入为空则返回 null
   */
  @Override
  public Date convert(String source) {
    // 如果输入为空或空字符串，直接返回 null
    if (source == null || source.trim().isEmpty()) {
      logger.debug("输入字符串为空，无法转换为 Date: {}", source);
      return null;
    }

    String trimmedSource = source.trim();  // 去除首尾空格
    Date date = null;

    // 首先尝试使用默认格式解析（严格模式）
    try {
      date = defaultDateFormat.parse(trimmedSource);
      logger.debug("成功使用默认格式解析日期: {} -> {}", trimmedSource, date);
      return date;
    } catch (ParseException e) {
      logger.debug("默认格式 {} 解析失败，尝试其他格式: {}", defaultDateFormat.toPattern(), trimmedSource);
    }

    // 如果默认格式失败，依次尝试其他支持的格式
    for (String pattern : SUPPORTED_FORMATS) {
      if (pattern.equals(defaultDateFormat.toPattern())) {
        continue;  // 跳过已尝试的默认格式
      }

      try {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setLenient(false);  // 严格解析，避免误判
        date = sdf.parse(trimmedSource);
        logger.info("成功使用格式 {} 解析日期: {} -> {}", pattern, trimmedSource, date);
        return date;
      } catch (ParseException e) {
        logger.debug("格式 {} 解析失败: {}", pattern, trimmedSource);
      }
    }

    // 所有格式都失败，记录警告并返回 null
    logger.warn("无法解析日期字符串，所有支持的格式均失败: {}", trimmedSource);
    return null;
  }
}
