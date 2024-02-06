package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.dao.entity.HistoricalRecord;

import java.util.List;
import java.util.Map;

public interface HistoricalRecordMapper {
    //用来查找呼吸心率记录
    int insert(HistoricalRecord historicalRecord);
    List<HistoricalRecord> selectByPageAndSearch(Map<String, Object> params);

}
