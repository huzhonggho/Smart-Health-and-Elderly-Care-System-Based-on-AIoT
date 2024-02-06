package com.boot.dandelion.health.care.core.service;

import com.boot.dandelion.health.care.dao.entity.HistoricalRecord;

import java.util.List;
import java.util.Map;

public interface HistoricalRecordService {

    int insert(HistoricalRecord historicalRecord);

    List<HistoricalRecord> selectByPageAndSearch(Map<String, Object> params);

}
