package com.boot.dandelion.health.care.dao.entity;

import lombok.Data;

@Data
public class UserBloodSugar {
    public Integer tenantId;
    public String userId;
    public String thermometerId;
    public String time;
    public Integer sugar;
    public Integer sortOrder;
    public String state;
    public String remarks;
    public String createTime;
    public String createBy;
    public String updateTime;
    public String updateBy;
    public String dataSource;
}

