package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.dao.entity.WatchLocation;
import com.boot.dandelion.health.care.dao.entity.WatchSteps;

import java.util.List;
import java.util.Map;

public interface WatchLocationMapper {

    int insert(WatchLocation watchLocation);

    List<WatchLocation> selectByDateAndWatchId(Map<String, Object> params);

}
