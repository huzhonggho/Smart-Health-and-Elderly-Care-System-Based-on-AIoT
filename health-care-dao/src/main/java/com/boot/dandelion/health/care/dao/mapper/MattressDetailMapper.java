package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.dao.entity.MattressDetail;
import com.boot.dandelion.health.care.dao.entity.MattressHistory;

import java.util.List;
import java.util.Map;

public interface MattressDetailMapper {
    int insert(MattressDetail mattressDetail);
    MattressDetail selectByDateAndMattressId(Map<String, Object> params);

}
