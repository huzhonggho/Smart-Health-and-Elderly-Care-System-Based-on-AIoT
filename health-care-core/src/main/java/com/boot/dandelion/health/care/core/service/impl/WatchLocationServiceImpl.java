package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.core.service.WatchLocationService;
import com.boot.dandelion.health.care.dao.entity.WatchLocation;
import com.boot.dandelion.health.care.dao.mapper.WatchLocationMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class WatchLocationServiceImpl implements WatchLocationService {
    @Resource
    private WatchLocationMapper mapper;
    @Override
    public int insert(WatchLocation watchLocation) {
        return mapper.insert(watchLocation);
    }

    @Override
    public List<WatchLocation> selectByDateAndWatchId(Map<String, Object> params) {
        return mapper.selectByDateAndWatchId(params);
    }
}
