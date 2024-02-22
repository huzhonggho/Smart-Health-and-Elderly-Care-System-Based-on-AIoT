package com.boot.dandelion.health.care.core.service;

import com.boot.dandelion.health.care.dao.entity.MattressSleep;

import java.util.List;
import java.util.Map;

public interface MattressSleepService {

    int insert(MattressSleep sleepEntity);
    List<MattressSleep> selectByDateAndMattressId(Map<String, Object> params);

}
