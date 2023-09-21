package com.boot.dandelion.health.care.dao.entity;

import com.boot.dandelion.health.care.common.entity.UserWeightDetailsInfo;
import lombok.Data;

@Data
public class UserWeightDetails {
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

    public UserWeightDetails(){
        new UserWeightDetailsInfo();
    }

}
