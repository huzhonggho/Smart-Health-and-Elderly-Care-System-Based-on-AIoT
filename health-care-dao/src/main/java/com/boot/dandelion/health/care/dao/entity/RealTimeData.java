package com.boot.dandelion.health.care.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RealTimeData {
    private Integer id;
    private String RR;
    private String HR;
    private String sleep;
    private String state;
    private String alam;
    private String mattressId;
    private String pressure;
}
