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
    public User getUserByLoginTel(String loginTel) {
        return userMapper.getUserByLoginTel(loginTel);
    }

    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int deleteUser(String userPhone){
        return userMapper.deleteByUserPhone(userPhone);
    }

    @Override
    public int updateUserPassword(User user){
        return userMapper.updateUserPasswordByPhone(user);
    }

    @Override
    public List<User> queryUserList(UserCondition params){
        return userMapper.queryUserList(params);
    }

    @Override
    public int queryUserCount(UserCondition params) {
        return userMapper.queryUserCount(params);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int delUser(User user) {
        return userMapper.deleteByUserPhone(user.getTel());
    }

    @Override
    public List<User> queryAllUser() {
        return userMapper.queryAllUser();
    }

    @Override
    public int getNum() {
        return userMapper.getNum();
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userMapper.selectUsersByName(name);
    }
}
