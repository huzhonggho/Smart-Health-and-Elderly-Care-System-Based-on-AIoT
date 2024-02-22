package com.boot.dandelion.health.care.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MattressSleep {

    private Integer sleepId;
    private String mattressId;
    private String date;
    private String startDate;
    private String endDate;
    private String state;


}
