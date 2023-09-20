package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.core.service.PsnNursingRecordService;
import com.boot.dandelion.health.care.dao.entity.PsnNursingRecord;
import com.boot.dandelion.health.care.dao.mapper.PsnNursingRecordMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class PsnNursingRecordServiceImpl implements PsnNursingRecordService {

    @Resource
    private PsnNursingRecordMapper psnNursingRecordMapper;

    @Override
    public int addPsnNursingRecord(PsnNursingRecord psnNursingRecord) {
        return psnNursingRecordMapper.insert(psnNursingRecord);
    }

    @Override
    public void deleteByPrimaryKey(PsnNursingRecord psnNursingRecord) {
        psnNursingRecordMapper.deleteByPrimaryKey(psnNursingRecord);
    }

    @Override
    public void updateByPrimaryKey(PsnNursingRecord psnNursingRecord) {
        psnNursingRecordMapper.updateByPrimaryKey(psnNursingRecord);
    }

    @Override
    public PsnNursingRecord selectByPrimaryKey(PsnNursingRecord psnNursingRecord) {
        return psnNursingRecordMapper.selectByPrimaryKey(psnNursingRecord);
    }

    @Override
    public List<PsnNursingRecord> selectAll() {
        return psnNursingRecordMapper.selectAll();
    }
}
