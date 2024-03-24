package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.common.condition.UserCondition;
import com.boot.dandelion.health.care.dao.entity.User;

import java.util.List;

public interface UserMapper {
    int insert(User user);

    User selectUserByName(String userName);
    User selectUserByUserId(Integer userId);
    int deleteByPrimaryKey(Integer uid);

    User selectByPrimaryKey(Integer uid);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User getUserByLoginTel(String loginTel);

    int deleteByUserPhone(String userPhone);

    int updateUserPasswordByPhone(User user);

    List<User> queryUserList(UserCondition params);

    int queryUserCount(UserCondition params);

    List<User> queryAllUser();

    int updateUser(User user);

    int getNum();

    List<User> selectUsersByName(String name);
}