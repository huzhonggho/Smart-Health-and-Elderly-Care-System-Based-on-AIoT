package com.boot.dandelion.health.care.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WatchLocation {

    private Integer locationId;
    private String deviceName;
    private String watchId;
    private String deviceId;
    private String latitude;
    private String longitude;
    private String oLat;
    private String oLng;
    private String showSpeed;
    private String speed;
    private String course;
    private String isStop;
    private String acc;
    private String status;
    private String deviceUtcDate;
    private String lastCommunication;
    private String showDataType;
    private String dataType;
    private String showBattery;
    private String battery;
    private String icon;
    private String distance;
}
