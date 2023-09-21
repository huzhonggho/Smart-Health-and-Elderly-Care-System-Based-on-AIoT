package com.boot.dandelion.health.care.common.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = {"password"})
public class UserWeightDetailsInfo {
    private int tenantId;
    private String userId;
    private String weightId;
    private String weightTime;
    private int weight;
    private int sortOrder;
    private String state;
    private String remarks;
    private String createTime;
    private String createBy;
    private String updateTime;
    private String updateBy;
    private String dataSources;
}
