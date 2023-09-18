package com.boot.dandelion.health.care.dao.entity;

import lombok.Data;

@Data
public class UserBloodOxygen {
    private Integer tenantId;
    private String userId;
    private String thermometerId;
    private String time;
    private Integer highestSaturation;
    private Integer lowestSaturation;
    private Integer averageSaturation;
    private Integer sortOrder;
    private String state;
    private String remarks;
    private String createTime;
    private String createBy;
    private String updateTime;
    private String updateBy;
    private String dataSource;
}
