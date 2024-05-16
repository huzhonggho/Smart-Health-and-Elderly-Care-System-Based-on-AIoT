package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.dao.entity.WatchInfo;

public interface WatchInfoMapper {

    int insert(WatchInfo watchInfo);

    WatchInfo selectBySN(String sn);
}
