package com.boot.dandelion.health.care.core.service;

import com.boot.dandelion.health.care.dao.entity.WatchInfo;

public interface WatchInfoService {

    int insert(WatchInfo watchInfo);

    WatchInfo selectBySN(String sn);
}
