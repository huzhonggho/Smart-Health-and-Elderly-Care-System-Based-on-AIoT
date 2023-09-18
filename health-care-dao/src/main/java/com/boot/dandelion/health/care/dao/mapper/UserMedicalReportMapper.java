package com.boot.dandelion.health.care.dao.mapper;


import com.boot.dandelion.health.care.dao.entity.UserMedicalReport;

public interface UserMedicalReportMapper {


    /**
     * 通过用户id查信息
     * @param userId
     * @return
     */
    UserMedicalReport selectAllByUid(String userId);

    /**
     * 通过体检单号查信息
     * @param rpotcNo
     * @return
     */
    UserMedicalReport selectAllByRn(String rpotcNo);

    /**
     * 添加体检信息
     * @param userMedicalReport
     * @return
     */
    int addUser(UserMedicalReport userMedicalReport);

    /**
     * 根据报告单号删除信息
     * @param rpotcNo
     * @return
     */
    int deleteUser(String rpotcNo);

    /**
     * 修改体检信息
     * @param userMedicalReport
     * @return
     */
    int updateUser(UserMedicalReport userMedicalReport);

}
