package com.boot.dandelion.health.care.core.service;

import com.boot.dandelion.health.care.dao.entity.PsnNursingRecord;

import java.util.List;

public interface PsnNursingRecordService {

    int addPsnNursingRecord(PsnNursingRecord psnNursingRecord);

    void deleteByPrimaryKey(PsnNursingRecord psnNursingRecord);

    // 更新护理记录信息
    void updateByPrimaryKey(PsnNursingRecord psnNursingRecord);

    // 根据租户ID和护理人员编号查询护理记录信息
    PsnNursingRecord selectByPrimaryKey(PsnNursingRecord psnNursingRecord);

    List<PsnNursingRecord> selectAll();
}
