package com.boot.dandelion.health.care.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceTransferM {
    private String memberIdFrom;
    private String deviceId;
    private String memberIdTo;
    private String deviceType;

}
