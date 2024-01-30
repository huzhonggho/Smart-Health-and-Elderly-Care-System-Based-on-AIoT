package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.common.util.DateFilterUtil;
import com.boot.dandelion.health.care.core.service.UserBloodSugarService;
import com.boot.dandelion.health.care.dao.entity.UserBloodDetails;
import com.boot.dandelion.health.care.dao.entity.UserBloodSugar;
import com.boot.dandelion.health.care.dao.mapper.UserBloodSugarMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserBloodSugarServiceImpl implements UserBloodSugarService {

    @Resource
    private UserBloodSugarMapper userBloodSugarMapper;

    @Override
    public int insert(UserBloodSugar userBloodSugar) {
        return userBloodSugarMapper.insert(userBloodSugar);
    }

    @Override
    public void deleteByPrimaryKey(UserBloodSugar userBloodSugar) {
        userBloodSugarMapper.deleteByPrimaryKey(userBloodSugar);
    }

    @Override
    public void updateByPrimaryKey(UserBloodSugar userBloodSugar) {
        userBloodSugarMapper.updateByPrimaryKey(userBloodSugar);
    }

    @Override
    public UserBloodSugar selectByPrimaryKey(UserBloodSugar userBloodSugar) {
        return userBloodSugarMapper.selectByPrimaryKey(userBloodSugar);
    }

    @Override
    public List<UserBloodSugar> selectAll() {
        return userBloodSugarMapper.selectAll();
    }

    @Override
    public List<UserBloodSugar> showBetweenDate(String start, String end) {
        DateFilterUtil<UserBloodSugar> util = new DateFilterUtil<>();
        List<UserBloodSugar> allData = userBloodSugarMapper.selectAll();
        List<UserBloodSugar> filteredData = util.filterDataByDate(allData, start, end, UserBloodSugar::parseTimeToDate);
        return filteredData;    }

}
