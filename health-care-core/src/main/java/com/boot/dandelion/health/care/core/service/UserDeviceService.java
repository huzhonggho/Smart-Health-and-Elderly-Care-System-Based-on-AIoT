package com.boot.dandelion.health.care.core.service;

import com.boot.dandelion.health.care.dao.entity.MattressOutBed;
import com.boot.dandelion.health.care.dao.entity.UserDevice;

import java.util.List;
import java.util.Map;

public interface UserDeviceService {

    int insert(UserDevice userDevice);

    List<UserDevice> selectByUserId(Integer userId);
}
