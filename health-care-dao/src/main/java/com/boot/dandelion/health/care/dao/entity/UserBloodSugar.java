package com.boot.dandelion.health.care.dao.entity;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class UserBloodSugar {

    public Integer tenantId;

    public String userId;

    public String thermometerId;

    public String time;

    public Integer sugar;

    public Integer sortOrder;

    public String state;

    public String remarks;

    public String createTime;

    public String createBy;

    public String updateTime;

    public String updateBy;
    public String dataSource;

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

