package com.boot.dandelion.health.care.common.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@ToString(exclude = {"password"})
public class UserSignsInfo implements Serializable{
    private String userId;
    private String name;
    private LocalDate dateBirth;
    private String mailbox;
    private String userIden;
    private Double height;
    private int weight;
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
    private int exercisesNumber;
    private String eatingHabits;
    private String smoking;
    private String drinking;
    private int diastolicPressure;
    private int systolicBloodPressure;
    private int heartRate;
    private int bloodSugar;
    private String bodyFat;
    private String bloodOxygenSaturation;
    private double temperature;
    private LocalDate createTime;
    private String createBy;
    private String remarks;
    private int tenantId;

}
