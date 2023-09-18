package com.boot.dandelion.health.care.dao.entity;

import com.boot.dandelion.health.care.common.entity.UserRegisterInfo;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRegister {
    private int id;
    private String phoneNumber;
    private String name;
    private int    authenticationState;
    private String nickName;
    private String openId;
    private String wechartName;
    private String password;
    private int    loginState;
    private String state;
    private String createTime;
    private String createBy;
    private LocalDate updateTime;
    private String updateBy;
    private String userIden;
    private String emergencyBy;
    private String emergencyNumber;

    public UserRegister(){
        new UserRegisterInfo();
    }
}
