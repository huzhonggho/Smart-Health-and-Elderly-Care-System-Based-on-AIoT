package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.dao.entity.RealTimeData;

import java.util.List;
import java.util.Map;

public interface RealTimeDataMapper {
    int insert(RealTimeData realTimeData);
    List<RealTimeData> selectByPageAndSearch(Map<String, Object> params);

}
