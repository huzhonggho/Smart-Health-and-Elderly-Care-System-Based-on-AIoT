package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.core.service.UserSleepService;
import com.boot.dandelion.health.care.dao.entity.UserSleep;
import com.boot.dandelion.health.care.dao.mapper.UserSleepMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserSleepServiceImpl implements UserSleepService {

    @Resource
    private UserSleepMapper userSleepMapper;

    @Override
    public List<UserSleep> selectAll() {
        return userSleepMapper.selectAll();
    }
}
