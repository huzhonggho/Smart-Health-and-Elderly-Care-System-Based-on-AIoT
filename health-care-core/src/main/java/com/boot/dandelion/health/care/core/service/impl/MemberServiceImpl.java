package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.core.service.MemberService;
import com.boot.dandelion.health.care.dao.entity.Member;
import com.boot.dandelion.health.care.dao.mapper.MemberMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberMapper memberMapper;


    @Override
    public int insert(Member member) {
        return memberMapper.insert(member);
    }

    @Override
    public int updateMemberByMemberIdId(Member member) {
        return memberMapper.updateMemberByMemberIdId(member);
    }

    @Override
    public List<Member> selectMemberByUserId(String userId) {
        return memberMapper.selectMemberByUserId(userId);
    }

    @Override
    public List<Member> selectMemberByFamilyId(String familyId) {
        return memberMapper.selectMemberByFamilyId(familyId);
    }

    @Override
    public Member selectMemberByTel(String tel) {
        return memberMapper.selectMemberByTel(tel);
    }

    @Override
    public int deleteMemberByMemberId(Integer memberId) {
        return memberMapper.deleteMemberByMemberId(memberId);
    }

    @Override
    public Member selectMemberByMemberId(Integer memberId) {
        return memberMapper.selectMemberByMemberId(memberId);
    }


}
