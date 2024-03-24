package com.boot.dandelion.health.care.dao.entity;

import com.boot.dandelion.health.care.common.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer userId;

    private String tel;

    private String password;

    //该字段仅用于接受数据,数据库中不存在
    private String oldPassword;

    private String dept;

    private String userName;

    private String status;

    private String gender;

    private String authority;
}