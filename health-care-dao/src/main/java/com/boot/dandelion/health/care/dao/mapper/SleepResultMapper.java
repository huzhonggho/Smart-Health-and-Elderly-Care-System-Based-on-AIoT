package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.dao.entity.SleepEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SleepResultMapper {

    int insert(SleepEntity sleepEntity);
    List<SleepEntity> selectByPageAndSearch(Map<String, Object> params);


}
