package com.boot.dandelion.health.care.dao.entity;

import com.boot.dandelion.health.care.common.entity.UserSignsInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class UserSigns {
    private String userId;
    private String name;
    private LocalDate dateBirth;
    private String mailbox;
    private String userIden;
    private Double height;
    private int    weight;
    private String gender;
    private String ethnicGroup;
    private String bloodType;
    private String rhBlood;
    private String career;
    private String historyIllness;
    private String historySurgery;
    private String disability;
    private String historyDrug;
    private String geneticHistory;
    private int    exercisesNumber;
    private String eatingHabits;
    private String smoking;
    private String drinking;
    private int    diastolicPressure;
    private int    systolicBloodPressure;
    private int    heartRate;
    private int    bloodSugar;
    private String bodyFat;
    private String bloodOxygenSaturation;
    private BigDecimal temperature;
    private LocalDate createTime;
    private String createBy;
    private String remarks;
    private int    tenantId;

    public UserSigns(){
        new UserSignsInfo();
    }

}
