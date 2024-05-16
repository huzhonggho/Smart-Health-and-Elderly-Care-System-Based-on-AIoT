package com.boot.dandelion.health.care.dao.mapper;


import com.boot.dandelion.health.care.dao.entity.UserFamily;

import java.util.List;

public interface UserFamilyMapper {

   int addFamily(UserFamily userFamily);

   int updateFamily(UserFamily userFamily);

   UserFamily selectByUserId(String userId);
}
