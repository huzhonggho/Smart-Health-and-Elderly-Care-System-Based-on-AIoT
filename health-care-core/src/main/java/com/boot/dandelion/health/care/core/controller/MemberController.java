package com.boot.dandelion.health.care.core.controller;


import com.boot.dandelion.health.care.common.entity.EMember;
import com.boot.dandelion.health.care.common.enums.ResultCodeEnum;
import com.boot.dandelion.health.care.common.util.DateUtils;
import com.boot.dandelion.health.care.common.util.PhoneNumberValidator;
import com.boot.dandelion.health.care.common.util.UserUtils;
import com.boot.dandelion.health.care.common.util.ValidatorUtils;
import com.boot.dandelion.health.care.common.wrapper.ResponseWrapper;
import com.boot.dandelion.health.care.core.service.MemberService;
import com.boot.dandelion.health.care.core.service.UserFamilyService;
import com.boot.dandelion.health.care.core.service.UserService;
import com.boot.dandelion.health.care.dao.entity.DeviceMap;
import com.boot.dandelion.health.care.dao.entity.Member;
import com.boot.dandelion.health.care.dao.entity.User;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@Slf4j

@PropertySources(value = {@PropertySource(value = {"classpath:important.properties", "classpath:application.properties"},
        encoding = "utf-8")})
@RestController
@RequestMapping("/member")
public class MemberController {
    @Resource
    private MemberService memberService;
    @Resource
    private UserService userService;
    @Resource
    private UserFamilyService userFamilyService;

    @ApiOperation("成员查询")
    @GetMapping(value = "/show")
    public ResponseWrapper<Object> showMember() {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            Integer userId = UserUtils.getUserId();
            Integer memberId = UserUtils.getMemberId();
            List<Member> memberList = null;

            if (userId != null ) {
                memberList = memberService.selectMemberByUserId(userId.toString());
            } else if (memberId != null ) {
                Member member = memberService.selectMemberByMemberId(memberId);
                if (member != null) {
                    memberList = memberService.selectMemberByFamilyId(member.getFamilyId());
                }
            }
            List<EMember> eMemberList = new ArrayList<>();

            for (Member info :
                    memberList) {
                EMember eMember = new EMember();
                eMember.setMemberId(info.getMemberId());
                eMember.setTel(info.getTel());
                eMember.setMemberName(info.getMemberName());
                eMember.setUuid(info.getUuid());
                eMember.setUname(info.getUname());
                eMember.setSortOrder(info.getSortOrder());
                eMember.setFamilyId(info.getFamilyId());
                eMember.setUserIden(info.getUserIden());
                eMember.setEmergencyBy(info.getEmergencyBy());
                eMember.setEmergencyNumber(info.getEmergencyNumber());

                eMemberList.add(eMember);
            }

            responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
            responseWrapper.setData(eMemberList);
        } catch (Exception e) {
            log.error("成员查询失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ExceptionUtils.getStackTrace(e));
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
        }
        return responseWrapper;
    }

    @Transactional
    @ApiOperation("成员添加")
    @PostMapping("/add")
    public ResponseWrapper<Object> add(@RequestBody Member member) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            String password = member.getPassword();
            String tel = member.getTel();
            String memeberName = member.getMemberName();
            Integer userId = UserUtils.getUserId();

            if (userId == null ) {
                responseWrapper.setMsg("非user账户");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }
            if (memeberName == null || memeberName.isEmpty() || password == null || password.isEmpty()) {
                responseWrapper.setMsg("用户名或密码不能为空");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            } else if (tel == null || tel.isEmpty()) {
                responseWrapper.setMsg("手机号不能为空");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            } else if (!PhoneNumberValidator.isValidPhoneNumber(tel)) {
                responseWrapper.setMsg("手机号填写错误");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }

            Member lawMember = memberService.selectMemberByTel(tel);
            if (lawMember != null) {
                responseWrapper.setMsg("手机号已注册成员");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }
            User user = userService.selectUserByTel(tel);
            if (user != null) {
                responseWrapper.setMsg("手机号已注册用户");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }
            member.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            member.setUserId(userId.toString());
            member.setCreateBy("USERID:"+userId);
            member.setCreateTime(DateUtils.getNowDate());
            member.setFamilyId(userFamilyService.selectByUserId(userId.toString()).getFamilyId());

            int result = memberService.insert(member);

            if (result > 0) {
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
                responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
            } else {
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
            }

        } catch (Exception e) {
            log.error("成员注册失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ExceptionUtils.getStackTrace(e));
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
        }
        return responseWrapper;
    }

    @ApiOperation("成员修改")
    @PutMapping("/update")
    public ResponseWrapper<Object> editUser(@RequestBody Member member) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            String tel = member.getTel();
            Integer memberId = member.getMemberId();
            Integer userId = UserUtils.getUserId();

            if (userId == null ) {
                responseWrapper.setMsg("非user账户");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }
            if (tel == null || tel.isEmpty()) {
                responseWrapper.setMsg("手机号不能为空");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }

            if (memberId == null) {
                responseWrapper.setMsg("memberId不能为空");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }

            Member selectMember = memberService.selectMemberByMemberId(memberId);

            if (selectMember == null) {
                responseWrapper.setMsg("成员不存在");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }
            member.setMemberId(selectMember.getMemberId());
            member.setUpdateBy("USERID:"+UserUtils.getUserId().toString());
            member.setUpdateTime(DateUtils.getNowDate());
            int result = memberService.updateMemberByMemberIdId(member);

            if (result > 0) {
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
                responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
            } else {
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
            }

        } catch (Exception e) {
            log.error("成员修改失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }

        return responseWrapper;
    }

    @ApiOperation("成员删除")
    @DeleteMapping("/del")
    public ResponseWrapper<Object> remove(@RequestParam Integer memberId) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            Integer userId = UserUtils.getUserId();

            if (userId == null ) {
                responseWrapper.setMsg("非user账户");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }
            if (memberId == null) {
                responseWrapper.setMsg("memberId不能为空");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }
            Member selectMember = memberService.selectMemberByMemberId(memberId);

            if (selectMember == null) {
                responseWrapper.setMsg("成员不存在");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }
            int result = memberService.deleteMemberByMemberId(memberId);

            if (result > 0) {
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
                responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
            } else {
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
            }
        } catch (Exception e) {
            log.error("用户删除失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }
}
