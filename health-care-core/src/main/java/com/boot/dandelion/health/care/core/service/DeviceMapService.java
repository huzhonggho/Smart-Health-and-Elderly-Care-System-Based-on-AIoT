package com.boot.dandelion.health.care.core.service;

import com.boot.dandelion.health.care.dao.entity.DeviceMap;
import com.boot.dandelion.health.care.dao.entity.MattressInfo;

import java.util.List;
import java.util.Map;

public interface DeviceMapService {
    int insert(DeviceMap deviceMap);

    int insertMattress(MattressInfo mattressInfo);
    DeviceMap selectByDeviceTypeAndId(Map<String, Object> params);
    List<DeviceMap> selectByFamilyId(String familyId);
    DeviceMap selectByDeviceId(String deviceId);

    List<DeviceMap> selectByUserId(String userId);
    int updateByDeviceId(DeviceMap deviceMap);


}
