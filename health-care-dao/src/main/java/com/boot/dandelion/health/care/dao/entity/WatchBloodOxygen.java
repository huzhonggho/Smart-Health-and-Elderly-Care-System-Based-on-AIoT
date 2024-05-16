package com.boot.dandelion.health.care.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WatchBloodOxygen {

    private Integer oxygenId;

    private String watchId;

    private String lastUpdate;

    private String bloodOxygen;
}
