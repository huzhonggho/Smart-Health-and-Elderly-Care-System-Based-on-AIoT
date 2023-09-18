package com.boot.dandelion.health.care.core.service;

import com.boot.dandelion.health.care.dao.entity.UserFamilyMember;

import java.util.List;

public interface UserFamilyMemberService {
    //查找全部信息
    List<UserFamilyMember> selectAll();

    //添加用户
    int addUser(UserFamilyMember userFamilyMember);

    //更新用户信息
    int updateUser(UserFamilyMember userFamilyMember);

    /**
     * 根据电话号码查询用户
     */
    UserFamilyMember getUserByLoginTel(String rafAccount);

    /**
     * 删除用户
     */
    int delUserBytel(String rafAccount);

}
