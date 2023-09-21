package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.core.service.UserMedicalReportService;
import com.boot.dandelion.health.care.dao.entity.UserMedicalReport;
import com.boot.dandelion.health.care.dao.mapper.UserMedicalReportMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserMedicalReportServiceImpl implements UserMedicalReportService {

    @Resource
    private UserMedicalReportMapper userMedicalReportMapper;


    @Override
    public UserMedicalReport selectAllByUid(String userId) {
        return userMedicalReportMapper.selectAllByUid(userId);
    }

    @Override
    public UserMedicalReport selectAllByRn(String rpotcNo) {
        return userMedicalReportMapper.selectAllByRn(rpotcNo);
    }

    @Override
    public int addUser(UserMedicalReport userMedicalReport) {
        return userMedicalReportMapper.addUser(userMedicalReport);
    }

    @Override
    public int deleteUser(String rpotcNo) {
        return userMedicalReportMapper.deleteUser(rpotcNo);
    }

    @Override
    public int updateUser(UserMedicalReport userMedicalReport) {
        return userMedicalReportMapper.updateUser(userMedicalReport);
    }
}
