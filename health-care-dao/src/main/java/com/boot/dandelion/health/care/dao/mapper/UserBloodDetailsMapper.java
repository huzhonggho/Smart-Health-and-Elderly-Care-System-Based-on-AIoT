package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.dao.entity.UserBloodDetails;

import java.util.List;

public interface UserBloodDetailsMapper {

    // 插入用户血压详细信息
    int insert(UserBloodDetails userBloodDetails);

    // 根据租户ID和用户ID删除用户血压详细信息
    void deleteByPrimaryKey(UserBloodDetails userBloodDetails);

    // 更新用户血压详细信息
    void updateByPrimaryKey(UserBloodDetails userBloodDetails);

    // 根据租户ID和用户ID查询用户血压详细信息
    UserBloodDetails selectByPrimaryKey(UserBloodDetails userBloodDetails);

    List<UserBloodDetails> show();
}