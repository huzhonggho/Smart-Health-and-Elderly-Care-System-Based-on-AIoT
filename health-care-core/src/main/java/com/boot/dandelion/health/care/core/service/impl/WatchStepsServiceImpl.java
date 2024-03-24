package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.core.service.WatchStepsService;
import com.boot.dandelion.health.care.dao.entity.WatchSteps;
import com.boot.dandelion.health.care.dao.mapper.WatchStepsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class WatchStepsServiceImpl implements WatchStepsService {

    @Resource
    private WatchStepsMapper mapper;
    @Override
    public int insert(WatchSteps watchSteps) {
        return mapper.insert(watchSteps);
    }

    @Override
    public int update(WatchSteps watchSteps) {
        return mapper.update(watchSteps);
    }

    @Override
    public int updateByHour(WatchSteps watchSteps) {
        return mapper.updateByHour(watchSteps);
    }

    @Override
    public int deleteByDateAndWatchId(Map<String, Object> params) {
        return mapper.deleteByDateAndWatchId(params);
    }

    @Override
    public List<WatchSteps> selectByDateAndWatchId(Map<String, Object> params) {
        return mapper.selectByDateAndWatchId(params);
    }
}
