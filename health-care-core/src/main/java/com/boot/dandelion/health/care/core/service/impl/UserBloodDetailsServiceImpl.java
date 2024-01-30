package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.common.util.DateFilterUtil;
import com.boot.dandelion.health.care.core.service.UserBloodDetailsService;
import com.boot.dandelion.health.care.dao.entity.UserBloodDetails;
import com.boot.dandelion.health.care.dao.mapper.UserBloodDetailsMapper;
import com.boot.dandelion.health.care.dao.mapper.UserTemDetailsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserBloodDetailsServiceImpl implements UserBloodDetailsService {

    @Resource
    private UserBloodDetailsMapper userBloodDetailsMapper;

    @Override
    public int insert(UserBloodDetails userBloodDetails) {
        return userBloodDetailsMapper.insert(userBloodDetails);
    }

    @Override
    public void deleteByPrimaryKey(UserBloodDetails userBloodDetails) {
        userBloodDetailsMapper.deleteByPrimaryKey(userBloodDetails);
    }

    @Override
    public void updateByPrimaryKey(UserBloodDetails userBloodDetails) {
        userBloodDetailsMapper.updateByPrimaryKey(userBloodDetails);
    }

    @Override
    public UserBloodDetails selectByPrimaryKey(UserBloodDetails userBloodDetails) {
        return userBloodDetailsMapper.selectByPrimaryKey(userBloodDetails);
    }

    @Override
    public List<UserBloodDetails> selectAll() {
        return userBloodDetailsMapper.selectAll();
    }

    @Override
    public List<UserBloodDetails> showBetweenDate(String start, String end) {

        DateFilterUtil<UserBloodDetails> util = new DateFilterUtil<>();
        List<UserBloodDetails> allData = userBloodDetailsMapper.selectAll();
        List<UserBloodDetails> filteredData = util.filterDataByDate(allData, start, end, UserBloodDetails::parseTimeToDate);
        return filteredData;
    }


}
