package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.core.service.UserFamilyMemberService;
import com.boot.dandelion.health.care.dao.entity.UserFamilyMember;
import com.boot.dandelion.health.care.dao.mapper.UserFamilyMemberMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserFamilyMemberServiceImpl implements UserFamilyMemberService {

    @Resource
    private UserFamilyMemberMapper userFamilyMemberMapper;


    //查找全部信息
    @Override
    public List<UserFamilyMember> selectAll() {
        return userFamilyMemberMapper.selectAll();
    }

    //添加用户
    @Override
    public int addUser(UserFamilyMember userFamilyMember) {
        return userFamilyMemberMapper.addUser(userFamilyMember);
    }

    @Override
    public int updateUser(UserFamilyMember userFamilyMember) {
        return userFamilyMemberMapper.updateUser(userFamilyMember);
    }

    @Override
    public UserFamilyMember getUserByLoginTel(String rafAccount) {
        return userFamilyMemberMapper.getUserByLoginTel(rafAccount);
    }

    @Override
    public int delUserBytel(String rafAccount) {
        return userFamilyMemberMapper.delUserBytel(rafAccount);
    }


}
