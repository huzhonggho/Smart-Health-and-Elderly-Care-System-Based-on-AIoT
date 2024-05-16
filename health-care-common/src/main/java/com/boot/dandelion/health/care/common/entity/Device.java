package com.boot.dandelion.health.care.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    private String DeviceName;

    private String deviceId;

    private String deviceType;
}
