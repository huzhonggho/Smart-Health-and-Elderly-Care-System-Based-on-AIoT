package com.boot.dandelion.health.care.core.service;

import com.boot.dandelion.health.care.dao.entity.Outbed;

import java.util.List;
import java.util.Map;

public interface OutBedService {

    int insert(Outbed outbed);

    List<Outbed> selectByPageAndSearch(Map<String, Object> params);

}
