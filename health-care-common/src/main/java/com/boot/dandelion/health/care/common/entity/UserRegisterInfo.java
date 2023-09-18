package com.boot.dandelion.health.care.common.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;

@Data
@ToString(exclude = {"password"})
public class UserRegisterInfo implements Serializable {
    private int id;
    private String phoneNumber;
    private String name;
    private int authenticationState;
    private String nickName;
    private String openId;
    private String wechartName;
    private String password;
    private int loginState;
    private String state;
    private String createTime;
    private String createBy;
    private Date updateTime;
    private String updateBy;
    private String userIden;
    private String emergencyBy;
    private String emergencyNumber;

}
