package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.core.service.WatchBloodOxygenService;
import com.boot.dandelion.health.care.dao.entity.WatchBloodOxygen;
import com.boot.dandelion.health.care.dao.mapper.WatchBloodOxygenMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class WatchBloodOxygenImpl implements WatchBloodOxygenService {
    @Resource
    private WatchBloodOxygenMapper mapper;


    @Override
    public int insert(WatchBloodOxygen watchBloodOxygen) {
        return mapper.insert(watchBloodOxygen);
    }

    @Override
    public List<WatchBloodOxygen> selectByWatchIdAndDate(Map<String, Object> params) {
        return mapper.selectByWatchIdAndDate(params);
    }


}
