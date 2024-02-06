package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.core.service.HistoricalRecordService;
import com.boot.dandelion.health.care.dao.entity.HistoricalRecord;
import com.boot.dandelion.health.care.dao.mapper.HistoricalRecordMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class HistoricalRecordServiceImpl implements HistoricalRecordService {

    @Resource
    private HistoricalRecordMapper historicalRecordMapper;
    @Override
    public int insert(HistoricalRecord historicalRecord) {
        return historicalRecordMapper.insert(historicalRecord);
    }

    @Override
    public List<HistoricalRecord> selectByPageAndSearch(Map<String, Object> params) {
        return historicalRecordMapper.selectByPageAndSearch(params);
    }

}
