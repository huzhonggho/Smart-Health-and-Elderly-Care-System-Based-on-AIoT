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

    /**
     * @Description: 添加用户
     * @param: [user]
     * @return: int
     * @author: shr
     * @date: 2022/7/14
     */
    int addUser(User user);

    User selectUserByName(String userName);
    User selectUserByUserId(Integer userId);

    /**
     * @Description: 通过登录手机号获取用户信息
     * @param: [loginTel]
     * @return: com.boot.dandelion.health.care.dao.entity.User
     * @author: shr
     * @date: 2022/7/14
     */
    User getUserByLoginTel(String loginTel);

    /**
     * @Description: 删除用户
     * @param: [userPhone]
     * @return: int
     * @author: shr
     * @date: 2022/7/14
     */
    int deleteUser(String userPhone);

    /**
     * @Description: 修改用户密码
     * @param: [user]
     * @return: int
     * @author: shr
     * @date: 2022/7/14
     */
    int updateUserPassword(User user);

   /**
    * @Description: 获取用户列表
    * @param: [params]
    * @return: java.util.List<com.boot.dandelion.health.care.dao.entity.User>
    * @author: shr
    * @date: 2022/7/14
    */
    List<User> queryUserList(UserCondition params);

    /**
     * @Description: 查询用户总数
     * @param: [params]
     * @return: int
     * @author: shr
     * @date: 2022/7/14
     */
    int queryUserCount(UserCondition params);

    /**
     * @description 获取全部用户信息
     * @return java.util.List<com.boot.dandelion.health.care.dao.entity.User>
     * @create 2023/1/8 17:12
     */
    List<User> queryAllUser();
    /**
     * @description 根据手机号修改用户信息
     * @param user
     * @return int
     * @create 2023/1/9 20:00
     */
    int updateUser(User user);

    /**
     * @description 根据手机号删除用户
     * @param user
     * @return int
     * @create 2023/1/9 23:28
     */
    int delUser(User user);

/**
 * @description 获取用户数量
 * @return int 
 * @create 2023/1/12 15:59
 */
    int getNum();

    /**
     * 根据用户名称模糊查询
     */
    List<User> getUsersByName(String name);

}
