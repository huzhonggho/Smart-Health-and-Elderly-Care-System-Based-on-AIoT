package com.boot.dandelion.health.care.core.service;

import com.boot.dandelion.health.care.dao.entity.MattressInfo;

public interface MattressInfoService {

    int updateByMattressId(MattressInfo mattressInfo);
    int insertMattressInfo(MattressInfo mattressInfo);

    MattressInfo selectByMattressId(String mattressId);
}
