package com.boot.dandelion.health.care.core.controller;


import com.boot.dandelion.health.care.common.enums.ResultCodeEnum;
import com.boot.dandelion.health.care.common.wrapper.ResponseWrapper;
import com.boot.dandelion.health.care.core.service.UserMedicalReportService;
import com.boot.dandelion.health.care.dao.entity.UserMedicalReport;
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
@RequestMapping("/userMedicalReport")
public class UserMedicalReportController {

    @Autowired
    private UserMedicalReportService userMedicalReportService;

    /**
     * 通过用户id查信息
     * @param userId
     * @return
     */
    @PostMapping(value = "/showList")
    public ResponseWrapper<Object> showList(String userId) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            if (StringUtils.isNotBlank(userId) || StringUtils.isNotEmpty(userId)) {
                UserMedicalReport userInfo = userMedicalReportService.selectAllByUid(userId);
                if (userInfo != null) {
                    responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
                    responseWrapper.setData(userInfo);
                }else {
                    log.error("根据用户id：{}，所查用户信息不存在", userId);
                    responseWrapper.setMsg("根据用户id: " + userId + " 当前" + ResultCodeEnum.USER_NOT_EXIT.getName());
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.USER_NOT_EXIT.getCode()));
                }
            } else {
                log.error("用户id不能为空：{}", userId);
                responseWrapper.setMsg(ResultCodeEnum.LOGINTEL_NOT_EXIT_ERROR.getName());
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.LOGINTEL_NOT_EXIT_ERROR.getCode()));
            }
        } catch (Exception e) {
            log.error("体检信息列表展示失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    /**
     * 添加体检信息
     * @param userMedicalReport
     * @return
     */
    @PostMapping("/add")
    public ResponseWrapper<Object> add(@RequestBody UserMedicalReport userMedicalReport) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            if (userMedicalReportService.selectAllByRn(userMedicalReport.getRpotcNo()) == null) {
                userMedicalReportService.addUser(userMedicalReport);
                responseWrapper.setMsg("数据添加成功");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
            } else {
                log.error("报告单号已存在：{}", userMedicalReport.getRpotcNo());
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
     * 根据报告单号删除体检信息
     * @param userMedicalReport
     * @return
     */
    @DeleteMapping("/del")
    public ResponseWrapper<Object> remove(@RequestBody UserMedicalReport userMedicalReport) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            if (StringUtils.isNotBlank(userMedicalReport.getRpotcNo()) || StringUtils.isNotEmpty(userMedicalReport.getRpotcNo())) {
                if (userMedicalReportService.selectAllByRn(userMedicalReport.getRpotcNo()) != null) {
                    userMedicalReportService.deleteUser(userMedicalReport.getRpotcNo());
                    responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
                } else {
                    log.error("根据报告单号：{}，所查数据不存在", userMedicalReport.getRpotcNo());
                    responseWrapper.setMsg("根据报告单号: " + userMedicalReport.getRpotcNo() + " 当前" + ResultCodeEnum.USER_NOT_EXIT.getName());
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.USER_NOT_EXIT.getCode()));
                }
            } else {
                log.error("报告单号不能为空：{}", userMedicalReport.getRpotcNo());
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
     * 根据报告单号修改体检数据
     * @param userMedicalReport
     * @return
     */
    @PostMapping("/edit")
    public ResponseWrapper<Object> editUser(@RequestBody UserMedicalReport userMedicalReport){
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            int num = userMedicalReportService.updateUser(userMedicalReport);
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
