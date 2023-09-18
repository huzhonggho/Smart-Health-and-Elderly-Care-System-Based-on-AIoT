package com.boot.dandelion.health.care.dao.entity;

import com.boot.dandelion.health.care.common.entity.UserFamilyInfo;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserFamily {
    private String familyId;
    private String familyName;
    private String userId;
    private LocalDate createTime;
    private String createBy;

    public UserFamily(){
        new UserFamilyInfo();
    }
}
