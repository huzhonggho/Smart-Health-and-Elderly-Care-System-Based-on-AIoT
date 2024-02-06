package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.dao.entity.DailyAlarm;

import java.util.List;
import java.util.Map;

public interface DailyAlarmMapper {
    int insert(DailyAlarm dailyAlarm);
    List<DailyAlarm> selectByPageAndSearch(Map<String, Object> params);

}
