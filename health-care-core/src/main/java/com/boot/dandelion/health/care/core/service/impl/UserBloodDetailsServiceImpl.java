package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.core.service.UserBloodDetailsService;
import com.boot.dandelion.health.care.dao.entity.UserBloodDetails;
import com.boot.dandelion.health.care.dao.mapper.UserBloodDetailsMapper;
import com.boot.dandelion.health.care.dao.mapper.UserTemDetailsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserBloodDetailsServiceImpl implements UserBloodDetailsService {

    @Resource
    private UserBloodDetailsMapper userBloodDetailsMapper;

    @Override
    public int insert(UserBloodDetails userBloodDetails) {
        return userBloodDetailsMapper.insert(userBloodDetails);
    }

    @Override
    public void deleteByPrimaryKey(UserBloodDetails userBloodDetails) {
        userBloodDetailsMapper.deleteByPrimaryKey(userBloodDetails);
    }

    @Override
    public void updateByPrimaryKey(UserBloodDetails userBloodDetails) {
        userBloodDetailsMapper.updateByPrimaryKey(userBloodDetails);
    }

    @Override
    public UserBloodDetails selectByPrimaryKey(UserBloodDetails userBloodDetails) {
        return userBloodDetailsMapper.selectByPrimaryKey(userBloodDetails);
    }

    @Override
    public List<UserBloodDetails> show() {
        return userBloodDetailsMapper.show();
    }
}
