package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.core.service.UserTemDetailsService;
import com.boot.dandelion.health.care.dao.entity.UserTemDetails;
import com.boot.dandelion.health.care.dao.mapper.UserMapper;
import com.boot.dandelion.health.care.dao.mapper.UserTemDetailsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserTemDetailsServiceImpl implements UserTemDetailsService {

    @Resource
    private UserTemDetailsMapper userTemDetailsMapper;

    @Override
    public int addUserTem(UserTemDetails userTemDetails) {
        return userTemDetailsMapper.insert(userTemDetails);
    }

    @Override
    public void deleteByPrimaryKey( String params) {
         userTemDetailsMapper.deleteByPrimaryKey(params);
    }

    @Override
    public void updateByPrimaryKey(UserTemDetails userTemDetails) {
        userTemDetailsMapper.updateByPrimaryKey(userTemDetails);
    }

    @Override
    public UserTemDetails selectByPrimaryKey(String params) {
        return userTemDetailsMapper.selectByPrimaryKey(params);
    }
}
