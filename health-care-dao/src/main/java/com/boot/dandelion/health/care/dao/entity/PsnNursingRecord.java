package com.boot.dandelion.health.care.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PsnNursingRecord {
    private Integer tenantId;
    private String nursingId; // Assuming nursingId corresponds to userId
    private String iptId; // Assuming iptId corresponds to thermometerId
    private String nursingDate; // Assuming nursingDate corresponds to time
    private Integer pulse;
    private BigDecimal temperature;
    private Integer sortOrder;
    private String state;
    private String details; // Assuming details corresponds to remarks
    private String createTime;
    private String createBy;
    private String updateTime;
    private String updateBy;
    private String dataSource;
}
