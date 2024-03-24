package com.boot.dandelion.health.care.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDevice {

    private Integer deviceId;

    private String userId;

    private String deviceName;
    private String equipId;
    private String deviceType;

}
