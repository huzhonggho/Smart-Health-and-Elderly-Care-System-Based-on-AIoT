package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.core.service.MattressOutBedService;
import com.boot.dandelion.health.care.dao.entity.MattressOutBed;
import com.boot.dandelion.health.care.dao.mapper.MattressOutBedMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class OutBedServiceImpl implements MattressOutBedService {

    @Resource
    private MattressOutBedMapper mapper;
    @Override
    public int insert(MattressOutBed outbed) {
        return mapper.insert(outbed);
    }

    @Override
    public List<MattressOutBed> selectByDateAndMattressId(Map<String, Object> params) {
        return mapper.selectByDateAndMattressId(params);
    }


}
