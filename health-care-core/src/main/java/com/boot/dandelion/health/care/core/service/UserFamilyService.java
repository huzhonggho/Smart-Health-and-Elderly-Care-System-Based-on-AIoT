package com.boot.dandelion.health.care.core.service;

import com.boot.dandelion.health.care.dao.entity.UserFamily;

import java.util.List;

public interface UserFamilyService {
    /**
     * 查找全部信息
     * @return
     */
    List<UserFamily> selectAll();

    /**
     * 根据家庭名称查用户
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
