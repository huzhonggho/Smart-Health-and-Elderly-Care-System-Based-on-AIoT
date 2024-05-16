package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.dao.entity.Member;

import java.util.List;

public interface MemberMapper {


    int insert(Member member);

    int updateMemberByMemberIdId(Member member);


    List<Member> selectMemberByUserId(String userId);
    List<Member> selectMemberByFamilyId(String familyId);

    Member selectMemberByTel(String tel);
    int deleteMemberByMemberId(Integer memberId);

    Member selectMemberByMemberId(Integer memberId);
}
