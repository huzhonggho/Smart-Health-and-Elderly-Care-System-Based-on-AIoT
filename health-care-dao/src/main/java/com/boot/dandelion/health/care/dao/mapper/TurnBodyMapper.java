package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.dao.entity.TurnBody;

import java.util.List;
import java.util.Map;

public interface TurnBodyMapper {
    int insert(TurnBody turnBody);
    List<TurnBody> selectByPageAndSearch(Map<String, Object> params);
}
