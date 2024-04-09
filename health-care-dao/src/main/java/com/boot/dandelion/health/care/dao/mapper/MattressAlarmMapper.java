package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.dao.entity.MattressAlarm;

import java.util.List;
import java.util.Map;

public interface MattressAlarmMapper {
    int insert(MattressAlarm mattressAlarm);
    List<MattressAlarm> selectByDateAndMattressId(Map<String, Object> params);

    MattressAlarm selectByAllFields(Map<String, Object> params);

}
