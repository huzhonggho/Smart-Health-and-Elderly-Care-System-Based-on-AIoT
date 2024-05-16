package com.boot.dandelion.health.care.core.service;

import com.boot.dandelion.health.care.dao.entity.UserFamily;

import java.util.List;

public interface UserFamilyService {
    int addFamily(UserFamily userFamily);

    int updateFamily(UserFamily userFamily);

    UserFamily selectByUserId(String userId);

}
