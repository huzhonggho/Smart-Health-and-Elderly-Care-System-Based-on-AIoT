package com.boot.dandelion.health.care.core.service;

import com.boot.dandelion.health.care.dao.entity.UserBloodSugar;

import java.util.List;

public interface UserBloodSugarService {
    // 插入用户血糖详细信息
    int insert(UserBloodSugar userBloodSugar);

    // 根据租户ID和用户ID删除用户血糖详细信息
    void deleteByPrimaryKey(UserBloodSugar userBloodSugar);

    // 更新用户血糖详细信息
    void updateByPrimaryKey(UserBloodSugar userBloodSugar);

    // 根据租户ID和用户ID查询用户血糖详细信息
    UserBloodSugar selectByPrimaryKey(UserBloodSugar userBloodSugar);

    List<UserBloodSugar> selectAll();

}
