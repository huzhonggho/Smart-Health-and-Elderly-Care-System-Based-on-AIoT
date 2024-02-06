package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.core.service.OutBedService;
import com.boot.dandelion.health.care.dao.entity.Outbed;
import com.boot.dandelion.health.care.dao.mapper.OutBedMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class OutBedServiceImpl implements OutBedService {

    @Resource
    private OutBedMapper outBedMapper;
    @Override
    public int insert(Outbed outbed) {
        return outBedMapper.insert(outbed);
    }

    @Override
    public List<Outbed> selectByPageAndSearch(Map<String, Object> params) {
        return outBedMapper.selectByPageAndSearch(params);
    }

}
