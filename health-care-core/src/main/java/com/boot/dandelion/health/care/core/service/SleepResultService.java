package com.boot.dandelion.health.care.core.service;

import com.boot.dandelion.health.care.dao.entity.SleepEntity;

import java.util.List;
import java.util.Map;

public interface SleepResultService {

    int insert(SleepEntity sleepEntity);

    List<SleepEntity> selectByPageAndSearch(Map<String, Object> params);

}
