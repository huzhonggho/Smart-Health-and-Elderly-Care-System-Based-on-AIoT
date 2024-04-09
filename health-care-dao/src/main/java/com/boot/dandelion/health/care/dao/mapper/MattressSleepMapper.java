package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.dao.entity.MattressDetail;
import com.boot.dandelion.health.care.dao.entity.MattressSleep;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MattressSleepMapper {

    int insert(MattressSleep sleepEntity);
    List<MattressSleep> selectByDateAndMattressId(Map<String, Object> params);

    MattressSleep selectByAllFields(Map<String, Object> params);
}
