package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.dao.entity.MattressInfo;

public interface MattressInfoMapper {
    int updateByMattressId(MattressInfo mattressInfo);
    int insertMattressInfo(MattressInfo mattressInfo);

    MattressInfo selectByMattressId(String mattressId);
}
