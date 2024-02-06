package com.boot.dandelion.health.care.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyAlarm {
    private Integer id;
    private String start;
    private String ende;
    private String intervale;
    private String ala;
    private String mattressId;
}
