package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.dao.entity.MattressOutBed;
import com.boot.dandelion.health.care.dao.entity.MattressTurnBody;

import java.util.List;
import java.util.Map;

public interface MattressTurnBodyMapper {
    int insert(MattressTurnBody mattressTurnBody);
    List<MattressTurnBody> selectByDateAndMattressId(Map<String, Object> params);
    MattressTurnBody selectMaxDataTimeByMattressIdAndDate(Map<String, Object> params);
}
