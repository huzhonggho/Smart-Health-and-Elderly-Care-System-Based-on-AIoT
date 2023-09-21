package com.boot.dandelion.health.care.core.service.impl;

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
    public List<UserBloodDetails> show() {
        return userBloodDetailsMapper.show();
    }

    @Override
    public List<UserBloodDetails> showBetween(String start, String end) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate;
        Date endDate;
        try {
            startDate = dateFormat.parse(start);
            endDate = dateFormat.parse(end);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        List<UserBloodDetails> allData = userBloodDetailsMapper.show();
        List<UserBloodDetails> filteredData = allData.stream()
                .filter(data -> {
                    try {
                        Date dataDate = dateFormat.parse(data.getTime());
                        return dataDate.compareTo(startDate) >= 0 && dataDate.compareTo(endDate) <= 0;
                    } catch (ParseException e) {
                        // 处理日期解析异常
                        e.printStackTrace();
                        return false;
                    }
                })
                .collect(Collectors.toList());
        return filteredData;
    }
}
