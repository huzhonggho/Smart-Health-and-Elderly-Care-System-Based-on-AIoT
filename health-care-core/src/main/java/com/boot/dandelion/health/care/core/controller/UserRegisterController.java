package com.boot.dandelion.health.care.core.controller;


import com.boot.dandelion.health.care.common.condition.UserCondition;
import com.boot.dandelion.health.care.common.enums.ResultCodeEnum;
import com.boot.dandelion.health.care.common.page.Pagination;
import com.boot.dandelion.health.care.common.util.ValidatorUtils;
import com.boot.dandelion.health.care.common.wrapper.ResponseWrapper;
import com.boot.dandelion.health.care.core.service.UserRegisterService;
import com.boot.dandelion.health.care.dao.entity.UserRegister;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/userRegister")
public class UserRegisterController {

    @Autowired
    private UserRegisterService userRegisterService;

    //展示用户
    @PostMapping(value = "/showUserList")
    public ResponseWrapper<Object> showUserList() {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            List<UserRegister> userList = userRegisterService.selectAll();
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

    //添加用户
    @PostMapping("/add")
    public ResponseWrapper<Object> add(@RequestBody UserRegister userRegister) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            if (ValidatorUtils.checkMobileFormat(userRegister.getPhoneNumber())) {
                if (userRegisterService.getUserByLoginTel(userRegister.getPhoneNumber()) == null) {
                    userRegisterService.addUser(userRegister);
                    responseWrapper.setMsg("用户添加成功");
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
                } else {
                    log.error("手机号已存在：{}", userRegister.getPhoneNumber());
                    responseWrapper.setMsg(ResultCodeEnum.PHONE_REPEAT_ERROR.getName());
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.PHONE_REPEAT_ERROR.getCode()));
                }
            } else {
                log.error("手机号格式不正确：{}", userRegister.getPhoneNumber());
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

    //删除用户
    @DeleteMapping("/delUser")
    public ResponseWrapper<Object> remove(@RequestBody UserRegister userRegister) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            if (StringUtils.isNotBlank(userRegister.getPhoneNumber()) || StringUtils.isNotEmpty(userRegister.getPhoneNumber())) {
                if (userRegisterService.getUserByLoginTel(userRegister.getPhoneNumber()) != null) {
                    userRegisterService.deleteUser(userRegister.getPhoneNumber());
                    responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
                } else {
                    log.error("根据手机号：{}，所查用户不存在", userRegister.getPhoneNumber());
                    responseWrapper.setMsg("根据手机号: " + userRegister.getPhoneNumber() + " 当前" + ResultCodeEnum.USER_NOT_EXIT.getName());
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.USER_NOT_EXIT.getCode()));
                }
            } else {
                log.error("用户手机号不能为空：{}", userRegister.getPhoneNumber());
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

    //编辑用户信息
    @PostMapping("/editUser")
    public ResponseWrapper<Object> editUser(@RequestBody UserRegister userRegister){
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            int num = userRegisterService.updateUser(userRegister);
            if(num==1){
                responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
            }else{
                responseWrapper.setMsg("不可修改手机号或其他错误");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
            }

        } catch (Exception e) {
            log.error("用户信息修改失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }

        return responseWrapper;
    }

    //查询展示列表信息
    @PostMapping(value = "/showUserList2")
    public ResponseWrapper<Object> showUserList(@RequestBody UserCondition params) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            List<UserRegister> userList = userRegisterService.queryUserList(params);
            int userTotal = userRegisterService.queryUserCount(params);
            Pagination<List<UserRegister>> userPagination = Pagination.ok(userList);
            userPagination.setTotal(userTotal);
            userPagination.setPage(params.getCurrentPage());
            userPagination.setLimit(params.getLimit());
            responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
            responseWrapper.setData(userPagination);
        } catch (Exception e) {
            log.error("用户列表展示失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    @ApiOperation("条件分页查询用户列表")
    @PostMapping("/getAllUsersByPageForAdmin/{current}/{limit}")
    public ResponseWrapper<Object> getAllUsersByPageForAdmin(@PathVariable("current") Integer current, @PathVariable("limit") Integer limit, @RequestParam(value = "name", required = false) String name) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            List<UserRegister> userList;
            PageHelper.startPage(current, limit);
            if (StringUtils.isNotEmpty(name)) {
                userList = userRegisterService.getUsersByName(name);
            } else {
                userList = userRegisterService.selectAll();
            }
            PageInfo<UserRegister> pageInfo = new PageInfo<UserRegister>(userList);
            responseWrapper.setData(pageInfo);
            responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
        } catch (Exception e) {
            log.error("用户列表分页查询失败：{}", ExceptionUtils.getStackTrace(e));
        }
        return responseWrapper;
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delUsers/{ids}")
    public ResponseWrapper<Object> removes(@PathVariable("ids") List<Integer> ids){
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
                userRegisterService.deleteUserList(ids);
                responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
        } catch (Exception e) {
            log.error("用户删除失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

}
