package com.boot.dandelion.health.care.core.service;

import com.boot.dandelion.health.care.dao.entity.MattressHistory;

import java.util.List;
import java.util.Map;

public interface MattressHisService {
    int insert(MattressHistory mattressHistory);

    List<MattressHistory> selectByPageAndSearch(Map<String, Object> params);
    List<MattressHistory> selectByDateAndMattressId(Map<String, Object> params);

    int selectCountByMattressIdAndDate(Map<String, Object> params);

}
