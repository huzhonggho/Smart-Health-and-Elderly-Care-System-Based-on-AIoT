package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.core.service.DailyAlarmService;
import com.boot.dandelion.health.care.dao.entity.DailyAlarm;
import com.boot.dandelion.health.care.dao.mapper.DailyAlarmMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class DailyAlarmServiceImpl implements DailyAlarmService {
    @Resource
    private DailyAlarmMapper dailyAlarmMapper;
    @Override
    public int insert(DailyAlarm dailyAlarm) {
        return dailyAlarmMapper.insert(dailyAlarm);
    }

    @Override
    public List<DailyAlarm> selectByPageAndSearch(Map<String, Object> params) {
        return dailyAlarmMapper.selectByPageAndSearch(params);
    }
}
