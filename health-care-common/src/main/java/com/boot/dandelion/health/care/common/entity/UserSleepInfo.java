package com.boot.dandelion.health.care.common.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = {"password"})
public class UserSleepInfo {
    private int tenantId;
    private String userId;
    private String thermometerId;
    private String time;
    private String startTime;
    private String endTime;
    private String awakeTime;
    private int sortOrder;
    private String state;
    private String remarks;
    private String createTime;
    private String createBy;
    private String updateTime;
    private String updateBy;
    private String dataSources;
}
