package com.boot.dandelion.health.care.common.entity;

/**
 * @ClassName StaticBasicInfo
 * @Description 用户登录态信息
 * @Author shr
 * @Date 2022/07/14
 */
public class StaticBasicInfo {

    /**
     * 本地ip
     */
    public static final String LOCAL_IP = "127.0.0.1";
    /**
     * linuxIp
     */
    public static final String LINUX_LOCAL_IP = "0:0:0:0:0:0:0:1";
    /**
     * 逻辑码
     */
    public static final String CODE_STR = "code";
    /**
     * 消息
     */
    public static final String MSG = "msg";
    /**
     * ,符号
     */
    public static final String COMMA = ",";
    /**
     * $符号
     */
    public static final String DOLLAR = "$";
    /**
     * @符号
     */
    public static final String ITEL = "@";
    public static final String RUNNING = "RUNNING";
    /**
     * 最后一个节点
     */
    public static final String END = "END";
    /**
     * 第一个节点
     */
    public static final String INIT = "INIT";
    /**
     * 任务完成状态
     */
    public static final String CONFIRM = "CONFIRM";
    /**
     * 任务取消状态
     */
    public static final String CANCEL = "CANCEL";
    /**
     * 任务退回状态
     */
    public static final String REJECT = "REJECT";
    /**
     * session设置当前登陆人的key标识
     */
    public static final String LOGIN_USER = "LOGIN_USER";

    /**
     * 登录URI
     */
    public static final String LOGIN_URI = "api/user/login";
}
