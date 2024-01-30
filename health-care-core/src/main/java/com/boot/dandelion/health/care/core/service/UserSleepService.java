package com.boot.dandelion.health.care.core.service;

import com.boot.dandelion.health.care.dao.entity.UserSleep;
import com.boot.dandelion.health.care.dao.entity.UserTemDetails;

import java.util.List;

public interface UserSleepService {
    List<UserSleep> selectAll();

    List<UserSleep> showBetweenDate(String start, String end);

}
