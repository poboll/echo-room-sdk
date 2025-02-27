package com.caiths.echoroomapi.entity;

/**
 * 邮件相关的常量定义类。
 * <p>
 * 该类定义了邮件投递状态、重试次数、超时时间及消息类型的常量值。
 * </p>
 *
 * @author poboll
 * @since 2025-02-26
 */
public class MailConstants {

    /**
     * 消息投递中状态常量，表示邮件正在投递。
     */
    public static final Integer DELIVERING = 0;

    /**
     * 消息投递成功状态常量，表示邮件已成功投递。
     */
    public static final Integer SUCCESS = 1;

    /**
     * 消息投递失败状态常量，表示邮件投递失败。
     */
    public static final Integer FAILURE = 2;

    /**
     * 最大重试次数常量，表示邮件投递失败后最多重试的次数。
     */
    public static final Integer MAX_TRY_COUNT = 3;

    /**
     * 消息超时时间常量（单位：分钟），表示邮件投递的超时时间。
     */
    public static final Integer MSG_TIMEOUT = 1;

    /**
     * 消息类型常量，表示反馈类型消息。
     */
    public static final Integer FEEDBACK_TYPE = 1;

    /**
     * 消息类型常量，表示验证码类型消息。
     */
    public static final Integer VERIFY_CODE_TYPE = 2;
}
