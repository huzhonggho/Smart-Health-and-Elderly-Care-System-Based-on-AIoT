package com.boot.dandelion.health.care.common.condition;

import lombok.Data;

/**
 * @ClassName UserCondition
 * @Description
 * @Author shr
 * @Date 2022/07/14
 */
@Data
public class UserCondition extends PageCondition{
    private String name;
    private String userPhone;
    private String dept;
    private String status;
//    private int type;
}
