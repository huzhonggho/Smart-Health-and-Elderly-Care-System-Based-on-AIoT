package com.boot.dandelion.health.care.dao.entity;

import com.boot.dandelion.health.care.common.entity.UserInfo;
import lombok.Data;

@Data
public class User {
    private Integer uid;

    private String tel;

    private String password;

    //该字段仅用于接受数据,数据库中不存在
    private String oldPassword;

    private String dept;

    private String name;

    private String status;

    private Integer gender;

    private Integer authority;
    public User(){
        new UserInfo();
    }
}