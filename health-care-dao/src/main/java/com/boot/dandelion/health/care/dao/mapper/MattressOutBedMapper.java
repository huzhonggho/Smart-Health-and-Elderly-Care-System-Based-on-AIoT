package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.dao.entity.MattressOutBed;
import com.boot.dandelion.health.care.dao.entity.MattressSleep;

import java.util.List;
import java.util.Map;

public interface MattressOutBedMapper {
    int insert(MattressOutBed mattress);
    List<MattressOutBed> selectByDateAndMattressId(Map<String, Object> params);
}
