package com.boot.dandelion.health.care.core.service;

import com.boot.dandelion.health.care.dao.entity.TurnBody;

import java.util.List;
import java.util.Map;

public interface TurnBodyService {

    int insert(TurnBody turnBody);

    List<TurnBody> selectByPageAndSearch(Map<String, Object> params);

}
