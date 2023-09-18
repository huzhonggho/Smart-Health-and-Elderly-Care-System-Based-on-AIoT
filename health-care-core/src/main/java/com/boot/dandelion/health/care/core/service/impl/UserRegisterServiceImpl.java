package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.common.condition.UserCondition;
import com.boot.dandelion.health.care.core.service.UserRegisterService;
import com.boot.dandelion.health.care.dao.entity.UserRegister;
import com.boot.dandelion.health.care.dao.mapper.UserRegisterMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserRegisterServiceImpl implements UserRegisterService {

    @Resource
    private UserRegisterMapper userRegisterMapper;

    @Override
    public List<UserRegister> selectAll() {
        return  userRegisterMapper.selectAll();
    }

    @Override
    public int addUser(UserRegister userRegister) {
        return userRegisterMapper.addUser(userRegister);
    }

    @Override
    public int deleteUser(String phoneNumber) {
        return userRegisterMapper.deleteUserByPhone(phoneNumber);
    }

    @Override
    public int updateUser(UserRegister userRegister) {
        return userRegisterMapper.updateUser(userRegister);
    }

    @Override
    public UserRegister getUserByLoginTel(String phoneNumber) {
        return userRegisterMapper.getUserByLoginTel(phoneNumber);
    }

    @Override
    public List<UserRegister> queryUserList(UserCondition params) {
        return userRegisterMapper.queryUserList(params);
    }

    @Override
    public int queryUserCount(UserCondition params) {
        return userRegisterMapper.queryUserCount(params);
    }

    @Override
    public List<UserRegister> getUsersByName(String name) {
        return userRegisterMapper.selectUsersByName(name);
    }

    @Override
    public int deleteUserList(List<Integer> ids) {
        return userRegisterMapper.deleteUserList(ids);
    }
}
