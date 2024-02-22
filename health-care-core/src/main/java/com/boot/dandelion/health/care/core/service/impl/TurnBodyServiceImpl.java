package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.core.service.MattressTurnBodyService;
import com.boot.dandelion.health.care.dao.entity.MattressOutBed;
import com.boot.dandelion.health.care.dao.entity.MattressTurnBody;
import com.boot.dandelion.health.care.dao.mapper.MattressTurnBodyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class TurnBodyServiceImpl implements MattressTurnBodyService {

    @Resource
    private MattressTurnBodyMapper mattressTurnBodyMapper;

    @Override
    public int insert(MattressTurnBody turnBody) {
        return mattressTurnBodyMapper.insert(turnBody);
    }

    @Override
    public List<MattressTurnBody> selectByDateAndMattressId(Map<String, Object> params) {
        return mattressTurnBodyMapper.selectByDateAndMattressId(params);
    }


}
