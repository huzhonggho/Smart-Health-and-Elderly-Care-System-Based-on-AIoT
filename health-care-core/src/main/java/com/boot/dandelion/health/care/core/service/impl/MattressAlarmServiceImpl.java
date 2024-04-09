package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.core.service.MattressAlarmService;
import com.boot.dandelion.health.care.dao.entity.MattressAlarm;
import com.boot.dandelion.health.care.dao.mapper.MattressAlarmMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class MattressAlarmServiceImpl implements MattressAlarmService {
    @Resource
    private MattressAlarmMapper mapper;
    @Override
    public int insert(MattressAlarm mattressAlarm) {
        return mapper.insert(mattressAlarm);
    }

    @Override
    public List<MattressAlarm> selectByDateAndMattressId(Map<String, Object> params) {
        return mapper.selectByDateAndMattressId(params);
    }

    @Override
    public MattressAlarm selectByAllFields(Map<String, Object> params) {
        return mapper.selectByAllFields(params);
    }

}
