package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.core.service.UserSignsService;
import com.boot.dandelion.health.care.dao.entity.UserSigns;
import com.boot.dandelion.health.care.dao.mapper.UserSignsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserSignsServiceImpl implements UserSignsService {


    @Resource
    private UserSignsMapper userSignsMapper;

    @Override
    public UserSigns selectAllByUid(String userIde) {
        return userSignsMapper.selectByUid(userIde);
    }

    @Override
    public UserSigns getUserByIden(String userIden) {
        return userSignsMapper.getUserByIden(userIden);
    }

    @Override
    public int addUser(UserSigns userSigns) {
        return userSignsMapper.addUser(userSigns);
    }

    @Override
    public int deleteUser(String userIden) {
        return userSignsMapper.deleteUser(userIden);
    }

    @Override
    public int updateUser(UserSigns userSigns) {
        return userSignsMapper.updateUser(userSigns);
    }
}
