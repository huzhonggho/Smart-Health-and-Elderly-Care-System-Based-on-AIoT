package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.core.service.MattressDetailService;
import com.boot.dandelion.health.care.dao.entity.MattressDetail;
import com.boot.dandelion.health.care.dao.mapper.MattressDetailMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class MattressDetailServiceImpl implements MattressDetailService {

    @Resource
    private MattressDetailMapper mapper;
    @Override
    public int insert(MattressDetail mattressDetail) {
        return mapper.insert(mattressDetail);
    }

    @Override
    public MattressDetail selectByDateAndMattressId(Map<String, Object> params) {
        return mapper.selectByDateAndMattressId(params);
    }

    @Override
    public int updateByMattressIdAndDate(MattressDetail mattressDetail) {
        return mapper.updateByMattressIdAndDate(mattressDetail);
    }
}
