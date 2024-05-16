package com.boot.dandelion.health.care.core.service;

import com.boot.dandelion.health.care.dao.entity.WatchBloodOxygen;

import java.util.List;
import java.util.Map;

public interface WatchBloodOxygenService {
    int insert(WatchBloodOxygen watchBloodOxygen);
    List<WatchBloodOxygen> selectByWatchIdAndDate(Map<String, Object> params);
}
