package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.dao.entity.WatchSteps;

import java.util.List;
import java.util.Map;

public interface WatchStepsMapper {
    int insert(WatchSteps watchSteps);
    int update(WatchSteps watchSteps);
    int updateByHour(WatchSteps watchSteps);
    int deleteByDateAndWatchId(Map<String, Object> params);
    List<WatchSteps> selectByDateAndWatchId(Map<String, Object> params);
}
