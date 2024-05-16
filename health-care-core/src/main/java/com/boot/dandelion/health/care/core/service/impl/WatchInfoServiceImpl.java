package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.core.service.WatchInfoService;
import com.boot.dandelion.health.care.dao.entity.WatchInfo;
import com.boot.dandelion.health.care.dao.mapper.WatchInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = Exception.class)
public class WatchInfoServiceImpl implements WatchInfoService {
    @Resource
    private WatchInfoMapper watchInfoMapper;
    @Override
    public int insert(WatchInfo watchInfo) {
        return watchInfoMapper.insert(watchInfo);
    }

    @Override
    public WatchInfo selectBySN(String sn) {
        return watchInfoMapper.selectBySN(sn);
    }
}
