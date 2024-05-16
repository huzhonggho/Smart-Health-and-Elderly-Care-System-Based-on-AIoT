package com.boot.dandelion.health.care.core.service;

import com.boot.dandelion.health.care.common.condition.UserCondition;
import com.boot.dandelion.health.care.dao.entity.User;

import java.util.List;

/**
 * @ClassName UserService
 * @Description 用户业务接口
 * @Author shr
 * @Date 2022/07/14
 */
public interface UserService {

    int insertUser(User user);

    User selectUserByName(String userName);

    User selectUserByTel(String tel);

    User selectUserByUserId(Integer userId);


}
