package com.boot.dandelion.health.care.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WatchHeart {

    private Integer heartId;

    private String watchId;
    private String steps;
    private String heartbeat;
    private String bloodPressure;
    private String diastolic;
    private String shrink;
    private String lastUpdate;
    private String bloodSugar;
    private String bloodOxygen;
}
