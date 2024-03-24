package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.common.condition.UserCondition;
import com.boot.dandelion.health.care.dao.entity.User;
import com.boot.dandelion.health.care.dao.entity.UserDevice;

import java.util.List;
import java.util.Map;

public interface UserDeviceMapper {
    int insert(UserDevice userDevice);

    List<UserDevice> selectByUserId(Integer userId);
}