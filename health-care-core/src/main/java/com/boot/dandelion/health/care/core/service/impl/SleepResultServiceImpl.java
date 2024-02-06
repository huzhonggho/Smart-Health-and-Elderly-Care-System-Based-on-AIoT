package com.boot.dandelion.health.care.core.service.impl;


import com.boot.dandelion.health.care.core.service.SleepResultService;
import com.boot.dandelion.health.care.dao.entity.SleepEntity;
import com.boot.dandelion.health.care.dao.mapper.SleepResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class SleepResultServiceImpl implements SleepResultService {

    @Resource
    private SleepResultMapper sleepResultMapper;

    @Override
    public int insert(SleepEntity sleepEntity) {
        return sleepResultMapper.insert(sleepEntity);
    }

    @Override
    public List<SleepEntity> selectByPageAndSearch(Map<String,Object> params) {
        return sleepResultMapper.selectByPageAndSearch(params);
    }


}
