package com.boot.dandelion.health.care.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HRAndRR {
    private String RR;
    private String HR;
    private String state;
    private String start;
    private String date;
}
