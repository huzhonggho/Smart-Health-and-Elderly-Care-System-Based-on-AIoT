package com.boot.dandelion.health.care.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WatchSteps {

    private Integer stepId;

    private String watchId;

    private String date;

    private String hour;

    private String steps;

    private String distance;

    private String calories;




}
