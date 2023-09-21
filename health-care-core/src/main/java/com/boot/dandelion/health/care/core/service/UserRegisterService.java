package com.boot.dandelion.health.care.core.service;

import com.boot.dandelion.health.care.common.condition.UserCondition;
import com.boot.dandelion.health.care.dao.entity.UserRegister;

import java.util.List;

public interface UserRegisterService {

    //查找全部信息
    List<UserRegister> selectAll();

    //添加用户
    int addUser(UserRegister userRegister);

    //删除用户
    int deleteUser(String phoneNumber);

    //更新用户信息
    int updateUser(UserRegister userRegister);

    //得到用户电话
    UserRegister getUserByLoginTel(String phoneNumber);

    List<UserRegister> queryUserList(UserCondition params);

    int queryUserCount(UserCondition params);

    List<UserRegister> getUsersByName(String name);

    /**
     * 批量删除
     * @param ids
     */
    int deleteUserList(List<Integer> ids);
}
