package com.boot.dandelion.health.care.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceMap {

    private String userId;
    private String familyId;
    private String memberId;

    private String deviceType;
    private String deviceId;
    private String createTime;
    private String createUser;
    private String updateTime;
    private String updateUser;
}
