package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.core.service.MattressOutBedService;
import com.boot.dandelion.health.care.core.service.UserDeviceService;
import com.boot.dandelion.health.care.dao.entity.MattressOutBed;
import com.boot.dandelion.health.care.dao.entity.UserDevice;
import com.boot.dandelion.health.care.dao.mapper.MattressOutBedMapper;
import com.boot.dandelion.health.care.dao.mapper.UserDeviceMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserDeviceServiceImpl implements UserDeviceService {

    @Resource
    private UserDeviceMapper mapper;

    @Override
    public int insert(UserDevice userDevice) {
        return mapper.insert(userDevice);
    }

    @Override
    public List<UserDevice> selectByUserId(Integer userId) {
        return mapper.selectByUserId(userId);
    }
}
