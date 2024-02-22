package com.boot.dandelion.health.care.core.service;

import com.boot.dandelion.health.care.dao.entity.MattressOutBed;

import java.util.List;
import java.util.Map;

public interface MattressOutBedService {

    int insert(MattressOutBed mattress);

    List<MattressOutBed> selectByDateAndMattressId(Map<String, Object> params);

}
