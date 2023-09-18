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
    public List<UserFamily> selectAll() {
        return userFamilyMapper.selectAll();
    }

    @Override
    public UserFamily getUserByFamilyName(String familyName) {
        return userFamilyMapper.getUserByFamilyName(familyName);
    }

    @Override
    public int addUser(UserFamily userFamily) {
        return userFamilyMapper.addUser(userFamily);
    }

    @Override
    public int updateUser(UserFamily userFamily) {
        return userFamilyMapper.updateUser(userFamily);
    }

    @Override
    public int delUserByFamilyName(String familyName) {
        return userFamilyMapper.delUserByFamilyName(familyName);
    }
}
