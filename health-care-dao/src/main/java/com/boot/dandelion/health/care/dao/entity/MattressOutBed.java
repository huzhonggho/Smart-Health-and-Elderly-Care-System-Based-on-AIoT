package com.boot.dandelion.health.care.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MattressOutBed {
    private Integer outId;
    private String start;
    private String end;
    private String mattressId;
    private String date;
}
