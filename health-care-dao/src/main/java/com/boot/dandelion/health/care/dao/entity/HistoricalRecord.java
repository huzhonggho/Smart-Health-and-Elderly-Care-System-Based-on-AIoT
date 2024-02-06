package com.boot.dandelion.health.care.dao.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricalRecord {

    private Integer id;
    private String RR;
    private String HR;
    private String wet;
    private String state;
    private String turn;
    private String date;
    private String mattressId;

}
