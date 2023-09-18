package com.boot.dandelion.health.care.core.service;

import com.boot.dandelion.health.care.dao.entity.UserWeightDetails;

import java.util.List;

public interface UserWeightDetailsService {
    List<UserWeightDetails> selectAll();
}
