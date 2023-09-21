package com.boot.dandelion.health.care.common.condition;

import lombok.Data;

@Data
public class UserRegisterCondition extends PageCondition {
    private String name;
    private String phoneNumber;

}
