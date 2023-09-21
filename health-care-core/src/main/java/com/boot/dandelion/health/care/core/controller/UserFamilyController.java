package com.boot.dandelion.health.care.core.controller;

import com.boot.dandelion.health.care.common.enums.ResultCodeEnum;
import com.boot.dandelion.health.care.common.wrapper.ResponseWrapper;
import com.boot.dandelion.health.care.core.service.UserFamilyService;
import com.boot.dandelion.health.care.dao.entity.UserFamily;
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
@ResponseBody
@RequestMapping("/userFamily")
public class UserFamilyController {

    @Autowired
    private UserFamilyService userFamilyService;


    /**
     * 展示所有用户信息
     * @return
     */
    @PostMapping(value = "/showAll")
    public ResponseWrapper<Object> showUserList() {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            List<UserFamily> userList = userFamilyService.selectAll();
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

    /**
     * 添加用户
     * @param userFamily
     * @return
     */
    @PostMapping("/add")
    public ResponseWrapper<Object> add(@RequestBody UserFamily userFamily) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
                if (userFamilyService.getUserByFamilyName(userFamily.getFamilyName()) == null) {
                    userFamilyService.addUser(userFamily);
                    responseWrapper.setMsg("用户添加成功");
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
                } else {
                    log.error("家庭名称已存在：{}", userFamily.getFamilyName());
                    responseWrapper.setMsg(ResultCodeEnum.PHONE_REPEAT_ERROR.getName());
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.PHONE_REPEAT_ERROR.getCode()));
                }
        } catch (Exception e) {
            log.error("用户添加失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    /**
     * 修改信息
     * @param userFamily
     * @return
     */
    @PostMapping("/editUser")
    public ResponseWrapper<Object> editUser(@RequestBody UserFamily userFamily){
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            int num = userFamilyService.updateUser(userFamily);
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
     * 通过家庭名称删除用户
     * @param userFamily
     * @return
     */
    @DeleteMapping("/delUser")
    public ResponseWrapper<Object> remove(@RequestBody UserFamily userFamily) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            if (StringUtils.isNotBlank(userFamily.getFamilyName()) || StringUtils.isNotEmpty(userFamily.getFamilyName())) {
                if (userFamilyService.getUserByFamilyName(userFamily.getFamilyName()) != null) {
                    userFamilyService.delUserByFamilyName(userFamily.getFamilyName());
                    responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
                } else {
                    log.error("根据家庭名称：{}，所查用户不存在", userFamily.getFamilyName());
                    responseWrapper.setMsg("根据家庭名称: " + userFamily.getFamilyName() + " 当前" + ResultCodeEnum.USER_NOT_EXIT.getName());
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.USER_NOT_EXIT.getCode()));
                }
            } else {
                log.error("家庭信息不能为空：{}", userFamily.getFamilyName());
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
