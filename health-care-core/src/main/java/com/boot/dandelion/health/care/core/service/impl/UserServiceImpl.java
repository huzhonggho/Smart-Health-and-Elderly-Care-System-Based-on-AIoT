package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.common.condition.UserCondition;
import com.boot.dandelion.health.care.core.service.UserService;
import com.boot.dandelion.health.care.dao.entity.User;
import com.boot.dandelion.health.care.dao.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description
 * @Author shr
 * @Date 2022/07/14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public int insertUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User selectUserByName(String userName) {
        return userMapper.selectUserByName(userName);
    }

    @Override
    public User selectUserByTel(String tel) {
        return userMapper.selectUserByTel(tel);
    }

    @Override
    public User selectUserByUserId(Integer userId) {
        return userMapper.selectUserByUserId(userId);
    }


}
