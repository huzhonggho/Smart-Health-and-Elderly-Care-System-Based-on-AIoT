package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.dao.entity.UserBloodOxygen;

import java.util.List;

public interface UserBloodOxygenMapper {
    // 插入用户血氧详细信息
    int insert(UserBloodOxygen userBloodOxygen);

    // 根据租户ID和用户ID删除用户血氧详细信息
    void deleteByPrimaryKey(UserBloodOxygen userBloodOxygen);

    // 更新用户血氧详细信息
    void updateByPrimaryKey(UserBloodOxygen userBloodOxygen);

    // 根据租户ID和用户ID查询用户血氧详细信息
    UserBloodOxygen selectByPrimaryKey(UserBloodOxygen userBloodOxygen);

    List<UserBloodOxygen> selectAll();
}
