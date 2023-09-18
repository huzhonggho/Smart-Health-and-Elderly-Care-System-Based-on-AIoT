package com.boot.dandelion.health.care.common.entity;

import lombok.Data;
import lombok.ToString;

import java.sql.Date;

@Data
@ToString(exclude = {"password"})
public class UserFamilyMemberInfo {
    private String rafId;
    private String rafAccount;
    private String rafName;
    private String rafPassword;
    private String uuid;
    private String uname;
    private int sortOrder;
    private String state;
    private Date createTime;
    private String createBy;
    private Date updateTime;
    private String updateBy;
    private String userId;
    private String familyId;
    private String userIden;
    private String emergencyBy;
    private String emergencyNumber;
}
