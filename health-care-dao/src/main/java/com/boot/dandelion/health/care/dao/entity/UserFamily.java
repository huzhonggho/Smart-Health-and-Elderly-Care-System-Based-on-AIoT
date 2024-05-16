package com.boot.dandelion.health.care.dao.entity;

import com.boot.dandelion.health.care.common.entity.UserFamilyInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFamily {
    private String familyId;
    private String userId;
    private String familyName;
    private String createTime;
    private String updateTime;

}
