package com.boot.dandelion.health.care.core.service;

import com.boot.dandelion.health.care.dao.entity.UserTemDetails;

import java.util.Map;

public interface UserTemDetailsService {

    int addUserTem(UserTemDetails userTemDetails);

    void deleteByPrimaryKey( String thermometerId);

    // 更新用户体温详细信息
    void updateByPrimaryKey(UserTemDetails userTemDetails);

    // 根据租户ID和用户ID查询用户体温详细信息
    UserTemDetails selectByPrimaryKey(String thermometerId);
}
