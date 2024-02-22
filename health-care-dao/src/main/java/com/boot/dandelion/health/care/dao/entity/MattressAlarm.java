package com.boot.dandelion.health.care.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MattressAlarm {
    private Integer alarmId;
    private String mattressId;
    private String start;
    private String end;
    private String intervals;
    private String date;
    private String ala;
}
