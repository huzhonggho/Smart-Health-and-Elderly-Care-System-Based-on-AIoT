package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.dao.entity.UserFamilyMember;

import java.util.List;

public interface UserFamilyMemberMapper {
    //查找用户
    List<UserFamilyMember> selectAll();

    //添加用户
    int addUser(UserFamilyMember userFamilyMember);

    //更新用户信息
    int updateUser(UserFamilyMember userFamilyMember);

    /**
     * 根据电话查找用户信息
     * @param rafAccount
     * @return
     */
    UserFamilyMember getUserByLoginTel(String rafAccount);

    /**
     * 通过手机号删除用户
     */
    int delUserBytel(String rafAccount);
}
