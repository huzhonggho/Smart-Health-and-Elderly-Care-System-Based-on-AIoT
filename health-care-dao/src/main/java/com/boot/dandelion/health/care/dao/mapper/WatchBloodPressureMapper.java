package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.dao.entity.WatchBloodPressure;
import com.boot.dandelion.health.care.dao.entity.WatchSteps;

import java.util.List;
import java.util.Map;

public interface WatchBloodPressureMapper {
    int insert(WatchBloodPressure watchBloodPressure);
    List<WatchBloodPressure> selectByWatchIdAndDate(Map<String, Object> params);
}
