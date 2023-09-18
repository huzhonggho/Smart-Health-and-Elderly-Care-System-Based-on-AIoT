package com.boot.dandelion.health.care.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.security.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
 public class UserTemDetails {
    private Integer tenantId;
    private String userId;
    private String thermometerId;
    private String time;
    private Integer pulse;
    private BigDecimal temperature;
    private Integer sortOrder;
    private String state;
    private String remarks;
    private String createTime;
    private String createBy;
    private String updateTime;
    private String updateBy;
    private String dataSource;
}
