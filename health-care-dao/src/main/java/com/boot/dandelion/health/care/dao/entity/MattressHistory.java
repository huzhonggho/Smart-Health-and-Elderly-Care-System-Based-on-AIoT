package com.boot.dandelion.health.care.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MattressHistory {
    private Integer historyId;
    private Integer RR;
    private Integer HR;
    private String wet;
    private Integer state;
    private String turn;
    private String date;
    private String alam;
    private String mattressId;
    private String duration;
}
