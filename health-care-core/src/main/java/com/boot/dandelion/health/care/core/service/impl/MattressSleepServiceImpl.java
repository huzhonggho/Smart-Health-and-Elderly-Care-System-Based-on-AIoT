package com.boot.dandelion.health.care.core.service.impl;


import com.boot.dandelion.health.care.core.service.MattressSleepService;
import com.boot.dandelion.health.care.dao.entity.MattressSleep;
import com.boot.dandelion.health.care.dao.mapper.MattressSleepMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class MattressSleepServiceImpl implements MattressSleepService {

    @Resource
    private MattressSleepMapper mapper;

    @Override
    public int insert(MattressSleep sleepEntity) {
        return mapper.insert(sleepEntity);
    }

    @Override
    public List<MattressSleep> selectByDateAndMattressId(Map<String, Object> params) {
        return mapper.selectByDateAndMattressId(params);
    }

    @Override
    public MattressSleep selectByAllFields(Map<String, Object> params) {
        return mapper.selectByAllFields(params);
    }


}
