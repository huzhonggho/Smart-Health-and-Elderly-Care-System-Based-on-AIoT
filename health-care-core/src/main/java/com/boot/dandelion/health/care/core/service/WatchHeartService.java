package com.boot.dandelion.health.care.core.service;

import com.boot.dandelion.health.care.dao.entity.WatchHeart;

import java.util.List;
import java.util.Map;

public interface WatchHeartService {

    int insert(WatchHeart watchHeart);
    List<WatchHeart> selectByWatchIdAndDate(Map<String, Object> params);
}
