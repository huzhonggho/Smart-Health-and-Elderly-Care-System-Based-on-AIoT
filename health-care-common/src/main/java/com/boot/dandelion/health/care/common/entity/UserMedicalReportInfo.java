package com.boot.dandelion.health.care.common.entity;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString(exclude = {"password"})
public class UserMedicalReportInfo {
    private String userId;
    private int tenantId;
    private String rpotcNo;
    private String appyNo;
    private String examMtd;
    private String refVal;
    private String examUnt;
    private String examRsltVal;
    private String examRsltDicm;
    private String examItemDetlCode;
    private String examItemDetlName;
    private String examRsltAbn;
    private int sortOrder;
    private String state;
    private String remarks;
    private LocalDate createTime;
    private String createBy;
    private LocalDate updateTime;
    private String updateBy;
}
