package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.dao.entity.DeviceMap;
import com.boot.dandelion.health.care.dao.entity.MattressDetail;

import java.util.List;
import java.util.Map;

public interface DeviceMapMapper {

    int insert(DeviceMap deviceMap);
    DeviceMap selectByDeviceTypeAndId(Map<String, Object> params);

    List<DeviceMap> selectByUserId(String userId);
    List<DeviceMap> selectByFamilyId(String familyId);
    DeviceMap selectByDeviceId(String deviceId);

    int updateByDeviceId(DeviceMap deviceMap);
}
