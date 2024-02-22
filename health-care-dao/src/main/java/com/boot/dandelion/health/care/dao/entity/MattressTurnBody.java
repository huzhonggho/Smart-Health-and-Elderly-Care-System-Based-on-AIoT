package com.boot.dandelion.health.care.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MattressTurnBody {
    private Integer turnId;
    private String mattressId;
    private String date;
    private String dataTime;
    private String dataValue;

    private String duration;
}
