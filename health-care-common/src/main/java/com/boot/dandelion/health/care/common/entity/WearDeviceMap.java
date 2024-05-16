package com.boot.dandelion.health.care.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WearDeviceMap {

    private String deviceId;
    private String userId;
    private String memberId;
    private String deviceType;
}
