package com.boot.dandelion.health.care.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EMember {

    private Integer memberId;
    private String tel;
    private String memberName;
    private String uuid;
    private String uname;
    private String sortOrder;
    private String familyId;
    private String userIden;
    private String emergencyBy;
    private String emergencyNumber;

}
