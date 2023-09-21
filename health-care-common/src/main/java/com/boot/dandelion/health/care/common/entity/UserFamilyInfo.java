package com.boot.dandelion.health.care.common.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@ToString(exclude = {"password"})
public class UserFamilyInfo implements Serializable {
    private String familyId;
    private String family_name;
    private String user_id;
    private LocalDate create_time;
    private String create_by;

}
