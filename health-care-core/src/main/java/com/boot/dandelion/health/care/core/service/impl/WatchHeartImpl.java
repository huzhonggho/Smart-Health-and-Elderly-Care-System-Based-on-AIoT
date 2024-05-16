package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.core.service.WatchHeartService;
import com.boot.dandelion.health.care.dao.entity.WatchHeart;
import com.boot.dandelion.health.care.dao.mapper.WatchHeartMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class WatchHeartImpl implements WatchHeartService {
    @Resource
    private WatchHeartMapper mapper;
    @Override
    public int insert(WatchHeart watchHeart) {
        return mapper.insert(watchHeart);
    }

    @Override
    public List<WatchHeart> selectByWatchIdAndDate(Map<String, Object> params) {
        return mapper.selectByWatchIdAndDate(params);
    }
}
