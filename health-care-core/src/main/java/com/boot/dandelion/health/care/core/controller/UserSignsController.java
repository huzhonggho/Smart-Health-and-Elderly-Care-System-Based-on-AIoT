package com.boot.dandelion.health.care.core.controller;


import com.boot.dandelion.health.care.common.enums.ResultCodeEnum;
import com.boot.dandelion.health.care.common.wrapper.ResponseWrapper;
import com.boot.dandelion.health.care.core.service.UserSignsService;
import com.boot.dandelion.health.care.dao.entity.UserSigns;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@Slf4j

@PropertySources(value = {@PropertySource(value = {"classpath:important.properties", "classpath:application.properties"},
        encoding = "utf-8")})
@RestController
@ResponseBody
@RequestMapping("/userSigns")
public class UserSignsController {

    @Autowired
    private UserSignsService userSignsService;

    /**
     * 通过用户id展示信息
     * @return
     */
    @PostMapping(value = "/showList")
    public ResponseWrapper<Object> showList(String userId) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            if (StringUtils.isNotBlank(userId) || StringUtils.isNotEmpty(userId)) {
                UserSigns userInfo = userSignsService.selectAllByUid(userId);
                if (userInfo != null) {
                    responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
                    responseWrapper.setData(userInfo);
                }else {
                    log.error("根据用户id：{}，所查用户不存在", userId);
                    responseWrapper.setMsg("根据用户id: " + userId + " 当前" + ResultCodeEnum.USER_NOT_EXIT.getName());
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.USER_NOT_EXIT.getCode()));
                }
            } else {
                log.error("用户id不能为空：{}", userId);
                responseWrapper.setMsg(ResultCodeEnum.LOGINTEL_NOT_EXIT_ERROR.getName());
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.LOGINTEL_NOT_EXIT_ERROR.getCode()));
            }
        } catch (Exception e) {
            log.error("体征信息列表展示失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    /**
     * 添加信息
     * @param userSigns
     * @return
     */
    @PostMapping("/add")
    public ResponseWrapper<Object> add(@RequestBody UserSigns userSigns) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
                if (userSignsService.getUserByIden(userSigns.getUserIden()) == null) {
                    userSignsService.addUser(userSigns);
                    responseWrapper.setMsg("数据添加成功");
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
                } else {
                    log.error("身份证号已存在：{}", userSigns.getUserIden());
                    responseWrapper.setMsg(ResultCodeEnum.PHONE_REPEAT_ERROR.getName());
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.PHONE_REPEAT_ERROR.getCode()));
                }
        } catch (Exception e) {
            log.error("添加数据失败失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    /**
     * 通过身份证号删除数据
     * @param userSigns
     * @return
     */
    @DeleteMapping("/del")
    public ResponseWrapper<Object> remove(@RequestBody UserSigns userSigns) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            if (StringUtils.isNotBlank(userSigns.getUserIden()) || StringUtils.isNotEmpty(userSigns.getUserIden())) {
                if (userSignsService.getUserByIden(userSigns.getUserIden()) != null) {
                    userSignsService.deleteUser(userSigns.getUserIden());
                    responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
                } else {
                    log.error("根据身份证号：{}，所查数据不存在", userSigns.getUserIden());
                    responseWrapper.setMsg("根据身份证号: " + userSigns.getUserIden() + " 当前" + ResultCodeEnum.USER_NOT_EXIT.getName());
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.USER_NOT_EXIT.getCode()));
                }
            } else {
                log.error("身份证号不能为空：{}", userSigns.getUserIden());
                responseWrapper.setMsg(ResultCodeEnum.LOGINTEL_NOT_EXIT_ERROR.getName());
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.LOGINTEL_NOT_EXIT_ERROR.getCode()));
            }
        } catch (Exception e) {
            log.error("数据删除失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    /**
     * 通过身份证号修改信息
     * @param userSigns
     * @return
     */
    @PostMapping("/edit")
    public ResponseWrapper<Object> editUser(@RequestBody UserSigns userSigns){
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            int num = userSignsService.updateUser(userSigns);
            if(num==1){
                responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
            }else{
                responseWrapper.setMsg("不可修改数据或其他错误");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
            }
        } catch (Exception e) {
            log.error("数据信息修改失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }
}
