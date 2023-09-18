package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.common.condition.UserCondition;
import com.boot.dandelion.health.care.dao.entity.UserRegister;

import java.util.List;

public interface UserRegisterMapper {
    /**
     * 列表展示所有用户信息
     * @return
     */
    List<UserRegister> selectAll();

    /**
     * 添加用户
     * @param userRegister
     * @return
     */
    int addUser(UserRegister userRegister);

    /**
     * 根据电话查找用户信息
     * @param phoneNumber
     * @return
     */
    UserRegister getUserByLoginTel(String phoneNumber);

    /**
     * 通过电话号码删除用户
     * @param phoneNumber
     * @return
     */
    int deleteUserByPhone(String phoneNumber);

    /**
     * 更新用户信息（通过电话）
     * @param userRegister
     * @return
     */
    int updateUser(UserRegister userRegister);

    /**
     * 通过电话查找信息，分页展示
     * @param params
     * @return
     */
    List<UserRegister> queryUserList(UserCondition params);

    /**
     * 统计符合条件数量
     * @param params
     * @return
     */
    int queryUserCount(UserCondition params);

    /**
     * 通过名字实现模糊查找
     * @param name
     * @return
     */
    List<UserRegister> selectUsersByName(String name);


    /**
     * 批量删除
     * @param ids
     */
    int deleteUserList(List<Integer> ids);
}
