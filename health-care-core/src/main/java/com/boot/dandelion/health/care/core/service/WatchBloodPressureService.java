package com.boot.dandelion.health.care.core.service;

import com.boot.dandelion.health.care.dao.entity.WatchBloodPressure;

import java.util.List;
import java.util.Map;

public interface WatchBloodPressureService {

    int insert(WatchBloodPressure watchBloodPressure);
    List<WatchBloodPressure> selectByWatchIdAndDate(Map<String, Object> params);
}
