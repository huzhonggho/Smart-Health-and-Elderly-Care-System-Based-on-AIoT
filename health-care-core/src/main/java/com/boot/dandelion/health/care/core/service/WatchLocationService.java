package com.boot.dandelion.health.care.core.service;

import com.boot.dandelion.health.care.dao.entity.WatchLocation;

import java.util.List;
import java.util.Map;

public interface WatchLocationService {
    int insert(WatchLocation watchLocation);

    List<WatchLocation> selectByDateAndWatchId(Map<String, Object> params);
}
