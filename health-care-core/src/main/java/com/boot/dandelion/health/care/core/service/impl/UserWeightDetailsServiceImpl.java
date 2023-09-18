package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.core.service.UserWeightDetailsService;
import com.boot.dandelion.health.care.dao.entity.UserWeightDetails;
import com.boot.dandelion.health.care.dao.mapper.UserWeightDetailsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserWeightDetailsServiceImpl implements UserWeightDetailsService {

    @Resource
    private UserWeightDetailsMapper userWeightDetailsMapper;

    @Override
    public List<UserWeightDetails> selectAll() {
        return userWeightDetailsMapper.selectAll();
    }
}
