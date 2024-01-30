package com.boot.dandelion.health.care.core.service;

import com.boot.dandelion.health.care.dao.entity.UserBloodOxygen;
import com.boot.dandelion.health.care.dao.entity.UserTemDetails;

import java.util.List;

public interface UserTemDetailsService {

    int addUserTem(UserTemDetails userTemDetails);

    void deleteByPrimaryKey(UserTemDetails userTemDetails);

    // 更新用户体温详细信息
    void updateByPrimaryKey(UserTemDetails userTemDetails);

    // 根据租户ID和用户ID查询用户体温详细信息
    UserTemDetails selectByPrimaryKey(UserTemDetails userTemDetails);

    List<UserTemDetails> selectAll();

    List<UserTemDetails> showBetweenDate(String start, String end);


}
