package com.boot.dandelion.health.care.common.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName UserInfo
 * @Description
 * @Author shr
 * @Date 2022/07/14
 */
@Data
@ToString(exclude = {"password"})
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1683531308128966170L;

    private String uid;

    private String tel;

    private String password;

    private String dept;

    private String name;

    private String status;
}
