package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.core.service.MattressHisService;
import com.boot.dandelion.health.care.dao.entity.MattressHistory;
import com.boot.dandelion.health.care.dao.mapper.MattressHisMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class MattressHisServiceImpl implements MattressHisService {
    @Resource
    private MattressHisMapper mapper;
    @Override
    public int insert(MattressHistory mattressHistory) {
        return mapper.insert(mattressHistory);
    }

    @Override
    public List<MattressHistory> selectByPageAndSearch(Map<String, Object> params) {
        return mapper.selectByPageAndSearch(params);
    }

    @Override
    public List<MattressHistory> selectByDateAndMattressId(Map<String, Object> params) {
        return mapper.selectByDateAndMattressId(params);
    }

    @Override
    public int selectCountByMattressIdAndDate(Map<String, Object> params) {
        return mapper.selectCountByMattressIdAndDate(params);
    }
}
