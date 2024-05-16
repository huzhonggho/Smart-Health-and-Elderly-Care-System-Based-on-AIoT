package com.boot.dandelion.health.care.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WatchInfo {
//watchId不是其他表中的watchId，是厂商给的watchId
    private String deviceId;
    private String name;
    private String sn;
    private String modelName;
    private String phoneNum;
    private String userName;
    private String cellPhone;
    private String expireTime;
    private String createTime;
    private String deviceStatus;
    private String dataContext;
    private String model;
    //手表厂商提供的userId
    private String userID;
    private String wear;
    private String battery;
}
