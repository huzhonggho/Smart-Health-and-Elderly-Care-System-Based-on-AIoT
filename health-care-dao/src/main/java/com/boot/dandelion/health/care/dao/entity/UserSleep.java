package com.boot.dandelion.health.care.dao.entity;

import com.boot.dandelion.health.care.common.entity.UserSleepInfo;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class UserSleep {
     private int tenantId;
     private String userId;
     private String thermometerId;
     private String time;
     private Integer startTime;
     private Integer endTime;
     private Integer awakeTime;
     private Integer sortOrder;
     private String state;
     private String remarks;
     private String createTime;
     private String createBy;
     private String updateTime;
     private String updateBy;
     private String dataSource;

     public UserSleep(){
          new UserSleepInfo();
     }

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
