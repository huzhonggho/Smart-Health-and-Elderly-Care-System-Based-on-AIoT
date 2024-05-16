package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.common.condition.UserCondition;
import com.boot.dandelion.health.care.dao.entity.User;

import java.util.List;

public interface UserMapper {
    int insert(User user);

    User selectUserByName(String userName);
    User selectUserByUserId(Integer userId);
    User selectByPrimaryKey(Integer uid);
    User selectUserByTel(String tel);
    List<User> selectAll();

}