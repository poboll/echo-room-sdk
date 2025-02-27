package com.caiths.echoroomapi.utils;

import org.springframework.security.core.context.SecurityContextHolder;  // Spring Security 上下文持有者类
import com.caiths.echoroomapi.entity.User;  // 用户实体类

/**
 * 用户工具类 (UserUtil)。
 * <p>
 * 该类提供获取当前登录用户信息的工具方法，基于 Spring Security 的上下文。
 * </p>
 *
 * @author poboll
 * @since 2025-02-26
 */
public class UserUtil {

  /**
   * 获取当前登录的用户实体。
   * 从 Spring Security 的安全上下文中提取当前认证用户的实体对象。
   *
   * @return 当前登录的 User 对象
   */
  public static User getCurrentUser() {
    return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }
}
