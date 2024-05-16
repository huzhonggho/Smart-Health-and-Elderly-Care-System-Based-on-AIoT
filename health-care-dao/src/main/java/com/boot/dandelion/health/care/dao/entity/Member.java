package com.boot.dandelion.health.care.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private Integer memberId;
    private String tel;
    private String memberName;
    private String password;
    private String uuid;
    private String uname;
    private String sortOrder;
    private String state;
    private String createTime;
    private String createBy;
    private String updateTime;
    private String updateBy;
    private String userId;
    private String familyId;
    private String userIden;
    private String emergencyBy;
    private String emergencyNumber;

}
