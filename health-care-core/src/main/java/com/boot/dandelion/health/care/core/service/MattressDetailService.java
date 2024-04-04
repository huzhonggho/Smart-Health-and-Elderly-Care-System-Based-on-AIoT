package com.boot.dandelion.health.care.core.service;

import com.boot.dandelion.health.care.dao.entity.MattressDetail;

import java.util.List;
import java.util.Map;

public interface MattressDetailService {
    int insert(MattressDetail mattressDetail);

    MattressDetail selectByDateAndMattressId(Map<String, Object> params);

    int updateByMattressIdAndDate(MattressDetail mattressDetail);

}
