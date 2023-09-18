package com.boot.dandelion.health.care.core.controller;


import com.boot.dandelion.health.care.common.enums.ResultCodeEnum;
import com.boot.dandelion.health.care.common.util.ValidatorUtils;
import com.boot.dandelion.health.care.common.wrapper.ResponseWrapper;
import com.boot.dandelion.health.care.core.service.UserFamilyMemberService;
import com.boot.dandelion.health.care.dao.entity.UserFamilyMember;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Slf4j

@PropertySources(value = {@PropertySource(value = {"classpath:important.properties", "classpath:application.properties"},
        encoding = "utf-8")})
@RestController
@RequestMapping("/userFamilyMember")
public class UserFamilyMemberController {
    @Autowired
    private UserFamilyMemberService userFamilyMemberService;

    //展示用户
    @PostMapping(value = "/showUser")
    public ResponseWrapper<Object> showUserList() {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            List<UserFamilyMember> userList = userFamilyMemberService.selectAll();
            responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
            responseWrapper.setData(userList);
        } catch (Exception e) {
            log.error("用户列表展示失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    @PostMapping("/add")
    public ResponseWrapper<Object> add(@RequestBody UserFamilyMember userFamilyMember) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            if (ValidatorUtils.checkMobileFormat(userFamilyMember.getRafAccount())) {
                if (userFamilyMemberService.getUserByLoginTel(userFamilyMember.getRafAccount()) == null) {
                    userFamilyMemberService.addUser(userFamilyMember);
                    responseWrapper.setMsg("用户添加成功");
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
                } else {
                    log.error("手机号已存在：{}", userFamilyMember.getRafAccount());
                    responseWrapper.setMsg(ResultCodeEnum.PHONE_REPEAT_ERROR.getName());
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.PHONE_REPEAT_ERROR.getCode()));
                }
            } else {
                log.error("手机号格式不正确：{}", userFamilyMember.getRafAccount());
                responseWrapper.setMsg(ResultCodeEnum.PHONE_FORMAT_ERROR.getName());
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.PHONE_FORMAT_ERROR.getCode()));
            }
        } catch (Exception e) {
            log.error("用户注册失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    //编辑用户信息
    @PostMapping("/editUser")
    public ResponseWrapper<Object> editUser(@RequestBody UserFamilyMember userFamilyMember){
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            int num = userFamilyMemberService.updateUser(userFamilyMember);
            if(num==1){
                responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
            }else{
                responseWrapper.setMsg("不可修改或其他错误");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
            }

        } catch (Exception e) {
            log.error("用户信息修改失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }

        return responseWrapper;
    }

    /**
     * 通过手机号删除用户信息
     */
    @DeleteMapping("/delUser")
    public ResponseWrapper<Object> remove(@RequestBody UserFamilyMember userFamilyMember) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            if (StringUtils.isNotBlank(userFamilyMember.getRafAccount()) || StringUtils.isNotEmpty(userFamilyMember.getRafAccount())) {
                if (userFamilyMemberService.getUserByLoginTel(userFamilyMember.getRafAccount()) != null) {
                    userFamilyMemberService.delUserBytel(userFamilyMember.getRafAccount());
                    responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
                } else {
                    log.error("根据手机号：{}，所查用户不存在", userFamilyMember.getRafAccount());
                    responseWrapper.setMsg("根据手机号: " + userFamilyMember.getRafAccount() + " 当前" + ResultCodeEnum.USER_NOT_EXIT.getName());
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.USER_NOT_EXIT.getCode()));
                }
            } else {
                log.error("用户手机号不能为空：{}", userFamilyMember.getRafAccount());
                responseWrapper.setMsg(ResultCodeEnum.LOGINTEL_NOT_EXIT_ERROR.getName());
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.LOGINTEL_NOT_EXIT_ERROR.getCode()));
            }
        } catch (Exception e) {
            log.error("用户删除失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }
}
