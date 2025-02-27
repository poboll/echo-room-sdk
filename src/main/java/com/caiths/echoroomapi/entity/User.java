package com.caiths.echoroomapi.entity;

import org.springframework.security.core.GrantedAuthority;  // Spring Security 的权限接口
import org.springframework.security.core.userdetails.UserDetails;  // Spring Security 的用户详情接口
import java.util.Collection;  // Collection 接口，用于存储权限集合

/**
 * 用户 (User) 实体类。
 * <p>
 * 该类表示系统中的用户实体，实现了 Spring Security 的 UserDetails 接口，用于认证和授权。
 * </p>
 *
 * @author poboll
 * @since 2025-02-26
 */
public class User implements UserDetails {

    /**
     * 用户编号，主键标识。
     */
    private Integer id;

    /**
     * 登录账号，用户登录时使用的唯一标识。
     */
    private String username;

    /**
     * 昵称，用户显示名称。
     */
    private String nickname;

    /**
     * 密码，用户登录时的加密密码。
     */
    private String password;

    /**
     * 用户头像，指向用户头像的资源地址。
     */
    private String userProfile;

    /**
     * 用户状态编号，表示用户当前状态的标识。
     */
    private Integer userStateId;

    /**
     * 是否可用，表示用户账号是否启用。
     */
    private Boolean isEnabled;

    /**
     * 是否被锁定，表示用户账号是否被锁定。
     */
    private Boolean isLocked;

    /**
     * 用户年龄。
     */
    private Integer age;

    /**
     * 获取用户编号。
     *
     * @return 用户编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置用户编号。
     *
     * @param id 用户编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取登录账号。
     *
     * @return 登录账号
     */
    public String getUsername() {
        return username;
    }

    /**
     * 判断账号是否未过期。
     * 当前实现始终返回 true，表示账号永不过期。
     *
     * @return true 表示账号未过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 判断账号是否未被锁定。
     * 根据 isLocked 字段判断，返回相反值。
     *
     * @return true 表示账号未被锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    /**
     * 判断凭证（密码）是否未过期。
     * 当前实现始终返回 true，表示凭证永不过期。
     *
     * @return true 表示凭证未过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 判断账号是否可用。
     * 根据 isEnabled 字段判断。
     *
     * @return true 表示账号可用
     */
    @Override
    public boolean isEnabled() {
        return isEnabled;
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
     * 获取用户昵称。
     *
     * @return 用户昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置用户昵称。
     *
     * @param nickname 用户昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取用户拥有的所有角色权限。
     * 当前实现返回 null，可根据需求扩展为返回实际权限集合。
     *
     * @return 用户的权限集合，若无则返回 null
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * 获取用户密码。
     *
     * @return 用户密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置用户密码。
     *
     * @param password 用户密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取用户头像地址。
     *
     * @return 用户头像地址
     */
    public String getUserProfile() {
        return userProfile;
    }

    /**
     * 设置用户头像地址。
     *
     * @param userProfile 用户头像地址
     */
    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    /**
     * 获取用户状态编号。
     *
     * @return 用户状态编号
     */
    public Integer getUserStateId() {
        return userStateId;
    }

    /**
     * 设置用户状态编号。
     *
     * @param userStateId 用户状态编号
     */
    public void setUserStateId(Integer userStateId) {
        this.userStateId = userStateId;
    }

    /**
     * 设置账号是否可用。
     *
     * @param enabled 是否可用
     */
    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    /**
     * 设置账号是否被锁定。
     *
     * @param locked 是否被锁定
     */
    public void setLocked(Boolean locked) {
        isLocked = locked;
    }

    /**
     * 获取用户年龄。
     *
     * @return 用户年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置用户年龄。
     *
     * @param age 用户年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 返回对象的字符串表示形式，便于调试和日志记录。
     *
     * @return 对象的字符串表示
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", userProfile='" + userProfile + '\'' +
                ", userStateId=" + userStateId +
                ", isEnabled=" + isEnabled +
                ", isLocked=" + isLocked +
                ", age=" + age +
                '}';
    }
}
