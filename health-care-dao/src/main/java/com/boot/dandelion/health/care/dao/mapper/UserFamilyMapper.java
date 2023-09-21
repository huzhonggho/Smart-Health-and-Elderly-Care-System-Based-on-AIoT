package com.boot.dandelion.health.care.dao.mapper;


import com.boot.dandelion.health.care.dao.entity.UserFamily;

import java.util.List;

public interface UserFamilyMapper {

    /**
     * 查找全部信息
     *
     */
    List<UserFamily> selectAll();

    /**
     * 根据家庭名称查信息
     * @param familyName
     * @return
     */
    UserFamily getUserByFamilyName(String familyName);

    /**
     * 添加用户
     * @param userFamily
     * @return
     */
    int addUser(UserFamily userFamily);

    /**
     * 更新信息
     * @param userFamily
     * @return
     */
    int updateUser(UserFamily userFamily);

    /**
     * 删除用户
     * @param familyName
     * @return
     */
    int delUserByFamilyName(String familyName);
}
