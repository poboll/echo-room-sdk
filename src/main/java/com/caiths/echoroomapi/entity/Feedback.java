package com.caiths.echoroomapi.entity;

import java.io.Serializable;  // Java 序列化接口，用于支持对象序列化

/**
 * 用户反馈 (Feedback) 实体类。
 * <p>
 * 该类表示用户提交的反馈信息，包含反馈编号、用户标识及反馈内容等属性。
 * </p>
 *
 * @author poboll
 * @since 2025-02-26
 */
public class Feedback implements Serializable {
    private static final long serialVersionUID = 550979088670747783L;  // 序列化版本号，用于版本控制

    /**
     * 反馈编号，主键标识。
     */
    private String id;

    /**
     * 用户编号，关联提交反馈的用户。
     */
    private String userId;

    /**
     * 用户登录账号。
     */
    private String username;

    /**
     * 用户昵称。
     */
    private String nickname;

    /**
     * 反馈内容，用户的具体反馈信息。
     */
    private String content;

    /**
     * 获取反馈编号。
     *
     * @return 反馈编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置反馈编号。
     *
     * @param id 反馈编号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取用户编号。
     *
     * @return 用户编号
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户编号。
     *
     * @param userId 用户编号
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取用户登录账号。
     *
     * @return 用户登录账号
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户登录账号。
     *
     * @param username 用户登录账号
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
     * 获取反馈内容。
     *
     * @return 反馈内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置反馈内容。
     *
     * @param content 反馈内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 返回对象的字符串表示形式，便于调试和日志记录。
     *
     * @return 对象的字符串表示
     */
    @Override
    public String toString() {
        return "Feedback{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
