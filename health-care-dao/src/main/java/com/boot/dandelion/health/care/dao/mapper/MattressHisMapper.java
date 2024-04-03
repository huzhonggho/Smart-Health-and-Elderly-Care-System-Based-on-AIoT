package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.dao.entity.MattressHistory;
import com.boot.dandelion.health.care.dao.entity.PsnNursingRecord;

import java.util.List;
import java.util.Map;

public interface MattressHisMapper {
    int insert(MattressHistory mattressHistory);

    List<MattressHistory> selectByPageAndSearch(Map<String, Object> params);

    List<MattressHistory> selectByDateAndMattressId(Map<String, Object> params);

    int selectCountByMattressIdAndDate(Map<String, Object> params);

    List<MattressHistory> selectMaxHistoryIdByMattressIdAndDate(Map<String, Object> params);

    int updateByHistoryId(MattressHistory mattressHistory);

    List<MattressHistory> selectByMattressIdDateAndStart(Map<String, Object> params);
    List<MattressHistory> selectByMattressIdDateStartAndEnd(Map<String, Object> params);


}
