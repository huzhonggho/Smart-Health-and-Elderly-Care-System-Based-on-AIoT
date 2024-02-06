package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.core.service.TurnBodyService;
import com.boot.dandelion.health.care.dao.entity.TurnBody;
import com.boot.dandelion.health.care.dao.mapper.TurnBodyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class TurnBodyServiceImpl implements TurnBodyService {

    @Resource
    private TurnBodyMapper turnBodyMapper;

    @Override
    public int insert(TurnBody turnBody) {
        return turnBodyMapper.insert(turnBody);
    }

    @Override
    public List<TurnBody> selectByPageAndSearch(Map<String, Object> params) {
        return turnBodyMapper.selectByPageAndSearch(params);
    }

}
