package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.dao.entity.Outbed;

import java.util.List;
import java.util.Map;

public interface OutBedMapper {
    int insert(Outbed outbed);
    List<Outbed> selectByPageAndSearch(Map<String, Object> params);
}
