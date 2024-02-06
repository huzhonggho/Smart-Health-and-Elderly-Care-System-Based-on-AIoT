package com.boot.dandelion.health.care.core.service;

import com.boot.dandelion.health.care.dao.entity.DailyAlarm;

import java.util.List;
import java.util.Map;

public interface DailyAlarmService {
    int insert(DailyAlarm dailyAlarm);

    List<DailyAlarm> selectByPageAndSearch(Map<String, Object> params);
}
