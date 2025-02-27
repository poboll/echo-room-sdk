package com.caiths.echoroomapi.entity;

import java.io.Serializable;  // Java 序列化接口，用于支持对象序列化

/**
 * 用户状态 (UserState) 实体类。
 * <p>
 * 该类表示用户状态的实体，包含状态编号和状态名称，支持序列化。
 * </p>
 *
 * @author poboll
 * @since 2025-02-26
 */
public class UserState implements Serializable {
    private static final long serialVersionUID = -38130170610280885L;  // 序列化版本号，用于版本控制

    /**
     * 状态编号，主键标识。
     */
    private Integer id;

    /**
     * 状态名称，描述用户当前的状态。
     */
    private String name;

    /**
     * 获取状态编号。
     *
     * @return 状态编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置状态编号。
     *
     * @param id 状态编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取状态名称。
     *
     * @return 状态名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置状态名称。
     *
     * @param name 状态名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 返回对象的字符串表示形式，便于调试和日志记录。
     *
     * @return 对象的字符串表示
     */
    @Override
    public String toString() {
        return "UserState{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
