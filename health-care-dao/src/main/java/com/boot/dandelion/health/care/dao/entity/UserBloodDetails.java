package com.boot.dandelion.health.care.dao.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBloodDetails {

    private Integer tenantId;

    private String userId;

    private String thermometerId;

    private String time;

    private Integer diastolicPressure;

    private Integer systolicBloodPressure;

    private Integer heartRate;

    private Integer sortOrder;

    private String state;

    private String remarks;

    private String createTime;

    private String createBy;

    private String updateTime;

    private String updateBy;

    private String dataSource;

    // 添加构造函数、Getter和Setter方法等
}
