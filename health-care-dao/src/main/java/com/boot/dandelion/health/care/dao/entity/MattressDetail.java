package com.boot.dandelion.health.care.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MattressDetail {
    private Integer detailId;
    private String mattressId;
    private String date;
    private String awake;
    private String inSleep;
    private String shallowSleep;
    private String deepSleep;
    private String totalSleep;
    private String numberOfAlarms;
    private String numberOfFreeBeds;
    private String respirationAverage;
    private String respirationMax;
    private String respirationMin;
    private String heartRateAverage;
    private String heartRateMax;
    private String heartRateMin;
    private String general;
    private String heartRespirationRatio;
    private String scores;

}
