package com.caiths.echoroomapi.entity;

import org.springframework.security.core.GrantedAuthority;  // Spring Security 授予权限接口
import org.springframework.security.core.userdetails.UserDetails;  // Spring Security 用户详情接口

import java.io.Serializable;  // Java 序列化接口
import java.util.Collection;  // Java 集合接口

/**
 * 管理员 (Admin) 实体类。
 * <p>
 * 该类实现了 UserDetails 接口，用于 Spring Security 认证，包含管理员的基本信息。
 * </p>
 *
 * @author makejava
 * @since 2025-02-26
 */
public class Admin implements Serializable, UserDetails {
    private static final long serialVersionUID = -75235725571250857L;  // 序列化版本号

    /**
     * 管理员 ID，主键标识。
     */
    private Integer id;

    /**
     * 登录账号。
     */
    private String username;

    /**
     * 昵称。
     */
    private String nickname;

    /**
     * 密码。
     */
    private String password;

    /**
     * 管理员头像地址。
     */
    private String userProfile;

    /**
     * 获取管理员 ID。
     *
     * @return 管理员 ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置管理员 ID。
     *
     * @param id 管理员 ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取登录账号。
     *
     * @return 登录账号
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 检查账户是否未过期。
     * 本实现始终返回 true，表示账户永不过期。
     *
     * @return true 表示账户未过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 检查账户是否未被锁定。
     * 本实现始终返回 true，表示账户未被锁定。
     *
     * @return true 表示账户未被锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 检查凭证（如密码）是否未过期。
     * 本实现始终返回 true，表示凭证永不过期。
     *
     * @return true 表示凭证未过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 检查账户是否启用。
     * 本实现始终返回 true，表示账户已启用。
     *
     * @return true 表示账户已启用
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * 设置登录账号。
     *
     * @param username 登录账号
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取昵称。
     *
     * @return 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称。
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取用户的权限集合。
     * 本实现返回 null，表示未设置具体权限。
     *
     * @return 权限集合，当前为 null
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * 获取密码。
     *
     * @return 密码
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码。
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取管理员头像地址。
     *
     * @return 头像地址
     */
    public String getUserProfile() {
        return userProfile;
    }

    /**
     * 设置管理员头像地址。
     *
     * @param userProfile 头像地址
     */
    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    /**
     * 返回对象的字符串表示形式，便于调试和日志记录。
     *
     * @return 对象的字符串表示
     */
    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", userProfile='" + userProfile + '\'' +
                '}';
    }
}
