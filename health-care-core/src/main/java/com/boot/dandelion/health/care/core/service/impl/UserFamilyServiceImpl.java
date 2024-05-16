package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.core.service.UserFamilyService;
import com.boot.dandelion.health.care.dao.entity.UserFamily;
import com.boot.dandelion.health.care.dao.mapper.UserFamilyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserFamilyServiceImpl implements UserFamilyService {

    @Resource
    private UserFamilyMapper userFamilyMapper;


    @Override
    public int addFamily(UserFamily userFamily) {
        return userFamilyMapper.addFamily(userFamily);
    }

    @Override
    public int updateFamily(UserFamily userFamily) {
        return userFamilyMapper.updateFamily(userFamily);
    }

    @Override
    public UserFamily selectByUserId(String userId) {
        return userFamilyMapper.selectByUserId(userId);
    }
}
