package com.boot.dandelion.health.care.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EHeart {

    private Integer heartId;
    private String watchId;
    private String heartbeat;
    private String lastUpdate;

}
