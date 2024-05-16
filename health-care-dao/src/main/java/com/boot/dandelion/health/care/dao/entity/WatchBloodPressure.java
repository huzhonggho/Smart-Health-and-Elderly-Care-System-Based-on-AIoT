package com.boot.dandelion.health.care.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WatchBloodPressure {

    private Integer pressureId;
    private String diastolic;
    private String shrink;
    private String lastUpdate;
    private String watchId;
}
