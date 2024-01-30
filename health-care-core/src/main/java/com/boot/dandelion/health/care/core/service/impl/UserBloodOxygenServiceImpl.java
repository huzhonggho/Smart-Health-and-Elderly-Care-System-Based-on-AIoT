package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.common.util.DateFilterUtil;
import com.boot.dandelion.health.care.core.service.UserBloodOxygenService;
import com.boot.dandelion.health.care.dao.entity.UserBloodDetails;
import com.boot.dandelion.health.care.dao.entity.UserBloodOxygen;
import com.boot.dandelion.health.care.dao.mapper.UserBloodOxygenMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserBloodOxygenServiceImpl implements UserBloodOxygenService {

    @Resource
    private UserBloodOxygenMapper userBloodOxygenMapper;

    @Override
    public int insert(UserBloodOxygen userBloodOxygen) {
        return userBloodOxygenMapper.insert(userBloodOxygen);
    }

    @Override
    public void deleteByPrimaryKey(UserBloodOxygen userBloodOxygen) {
        userBloodOxygenMapper.deleteByPrimaryKey(userBloodOxygen);
    }

    @Override
    public void updateByPrimaryKey(UserBloodOxygen userBloodOxygen) {
        userBloodOxygenMapper.updateByPrimaryKey(userBloodOxygen);
    }

    @Override
    public UserBloodOxygen selectByPrimaryKey(UserBloodOxygen userBloodOxygen) {
        return userBloodOxygenMapper.selectByPrimaryKey(userBloodOxygen);
    }

    @Override
    public List<UserBloodOxygen> selectAll() {
        return userBloodOxygenMapper.selectAll();
    }

    @Override
    public List<UserBloodOxygen> showBetweenDate(String start, String end) {
        DateFilterUtil<UserBloodOxygen> util = new DateFilterUtil<>();
        List<UserBloodOxygen> allData = userBloodOxygenMapper.selectAll();
        List<UserBloodOxygen> filteredData = util.filterDataByDate(allData, start, end, UserBloodOxygen::parseTimeToDate);
        return filteredData;
    }
}
