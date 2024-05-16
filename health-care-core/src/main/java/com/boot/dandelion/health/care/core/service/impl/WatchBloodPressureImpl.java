package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.core.service.WatchBloodPressureService;
import com.boot.dandelion.health.care.dao.entity.WatchBloodPressure;
import com.boot.dandelion.health.care.dao.mapper.WatchBloodPressureMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class WatchBloodPressureImpl implements WatchBloodPressureService {
    @Resource
    private WatchBloodPressureMapper mapper;
    @Override
    public int insert(WatchBloodPressure watchBloodPressure) {
        return mapper.insert(watchBloodPressure);
    }

    @Override
    public List<WatchBloodPressure> selectByWatchIdAndDate(Map<String, Object> params) {
        return mapper.selectByWatchIdAndDate(params);
    }
}
