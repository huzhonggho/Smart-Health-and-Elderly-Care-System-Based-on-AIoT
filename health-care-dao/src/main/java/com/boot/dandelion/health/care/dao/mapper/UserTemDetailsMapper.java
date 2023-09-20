package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.dao.entity.UserBloodSugar;
import com.boot.dandelion.health.care.dao.entity.UserTemDetails;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface UserTemDetailsMapper {

    int insert( UserTemDetails userTemDetails);

    // 根据租户ID和用户ID删除用户体温详细信息
    void deleteByPrimaryKey( UserTemDetails userTemDetails);

    // 更新用户体温详细信息
    void updateByPrimaryKey(UserTemDetails userTemDetails);

    // 根据租户ID和用户ID查询用户体温详细信息
    UserTemDetails selectByPrimaryKey(UserTemDetails userTemDetails);

    List<UserTemDetails> selectAll();

}
