package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.dao.entity.PsnNursingRecord;

import java.util.List;

public interface PsnNursingRecordMapper {

    int insert(PsnNursingRecord userNursingRecord);

    // 根据租户ID和护理人员编号删除护理记录信息
    void deleteByPrimaryKey(PsnNursingRecord userNursingRecord);

    // 更新护理记录信息
    void updateByPrimaryKey(PsnNursingRecord userNursingRecord);

    // 根据租户ID和护理人员编号查询护理记录信息
    PsnNursingRecord selectByPrimaryKey(PsnNursingRecord userNursingRecord);

    List<PsnNursingRecord> selectAll();

}
