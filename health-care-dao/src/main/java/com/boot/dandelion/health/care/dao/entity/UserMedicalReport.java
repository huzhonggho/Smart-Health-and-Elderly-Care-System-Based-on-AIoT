package com.boot.dandelion.health.care.dao.entity;

import com.boot.dandelion.health.care.common.entity.UserMedicalReportInfo;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserMedicalReport {
    private String userId;
    private int    tenantId;
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
    private int    sortOrder;
    private String state;
    private String remarks;
    private LocalDate createTime;
    private String createBy;
    private LocalDate updateTime;
    private String updateBy;

    public UserMedicalReport(){
        new UserMedicalReportInfo();
    }

}
