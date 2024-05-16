package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.dao.entity.WatchBloodOxygen;
import com.boot.dandelion.health.care.dao.entity.WatchHeart;

import java.util.List;
import java.util.Map;

public interface WatchHeartMapper {
    int insert(WatchHeart watchHeart);
    List<WatchHeart> selectByWatchIdAndDate(Map<String, Object> params);
}
