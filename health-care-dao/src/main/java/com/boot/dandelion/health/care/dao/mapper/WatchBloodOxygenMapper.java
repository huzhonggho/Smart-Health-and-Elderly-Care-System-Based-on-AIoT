package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.dao.entity.WatchBloodOxygen;
import com.boot.dandelion.health.care.dao.entity.WatchBloodPressure;

import java.util.List;
import java.util.Map;

public interface WatchBloodOxygenMapper {
    int insert(WatchBloodOxygen watchBloodOxygen);
    List<WatchBloodOxygen> selectByWatchIdAndDate(Map<String, Object> params);
}
