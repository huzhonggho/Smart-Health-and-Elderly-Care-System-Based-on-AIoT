package com.boot.dandelion.health.care.core.service.impl;


import com.boot.dandelion.health.care.core.service.RealTimeDataService;
import com.boot.dandelion.health.care.dao.entity.RealTimeData;
import com.boot.dandelion.health.care.dao.mapper.RealTimeDataMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class RealTimeDataServiceImpl implements RealTimeDataService {

    @Resource
    private RealTimeDataMapper realTimeDataMapper;

    @Override
    public int insert(RealTimeData realTimeData) {
        return realTimeDataMapper.insert(realTimeData);
    }

    @Override
    public List<RealTimeData> selectByPageAndSearch(Map<String, Object> params) {
        return realTimeDataMapper.selectByPageAndSearch(params);
    }
}