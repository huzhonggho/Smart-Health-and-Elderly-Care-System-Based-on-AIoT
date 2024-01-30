package com.boot.dandelion.health.care.dao.entity;


import com.sun.prism.shader.Solid_ImagePattern_Loader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBloodDetails {

    private Integer tenantId;

    private String userId;

    private String thermometerId;

    private String time;

    private Integer diastolicPressure;

    private Integer systolicBloodPressure;

    private Integer heartRate;

    private Integer sortOrder;

    private String state;

    private String remarks;

    private String createTime;

    private String createBy;

    private String updateTime;

    private String updateBy;

    private String dataSource;

    public Date parseTimeToDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(this.time);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return  null;
    }
}
