package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.core.service.MattressInfoService;
import com.boot.dandelion.health.care.dao.entity.MattressInfo;
import com.boot.dandelion.health.care.dao.mapper.MattressInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = Exception.class)
public class MattressInfoServiceImpl implements MattressInfoService {

    @Resource
    private MattressInfoMapper mapper;
    @Override
    public int updateByMattressId(MattressInfo mattressInfo) {
        return mapper.updateByMattressId(mattressInfo);
    }

    @Override
    public int insertMattressInfo(MattressInfo mattressInfo) {
        return mapper.insertMattressInfo(mattressInfo);
    }

    @Override
    public MattressInfo selectByMattressId(String mattressId) {
        return mapper.selectByMattressId(mattressId);
    }
}
