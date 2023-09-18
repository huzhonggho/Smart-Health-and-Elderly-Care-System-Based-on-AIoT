package com.boot.dandelion.health.care.core.controller;

import com.boot.dandelion.health.care.common.enums.ResultCodeEnum;
import com.boot.dandelion.health.care.common.wrapper.ResponseWrapper;
import com.boot.dandelion.health.care.core.service.UserTemDetailsService;
import com.boot.dandelion.health.care.dao.entity.UserTemDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin(origins = "*")
@Slf4j

@PropertySources(value = {@PropertySource(value = {"classpath:important.properties", "classpath:application.properties"},
        encoding = "utf-8")})
@RestController
@RequestMapping("/api/userTemDetails")
public class UserTemDetailsController {

    @Resource
    private UserTemDetailsService userTemDetailsService;

    @PostMapping("/add")
    public ResponseWrapper<Object> add(@RequestBody UserTemDetails userTemDetails) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {

            System.out.println(userTemDetails.toString());

            userTemDetailsService.addUserTem(userTemDetails);
            responseWrapper.setMsg("温度添加成功");
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));

        } catch (Exception e) {
            log.error("温度添加失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    @DeleteMapping("/delete")
    public ResponseWrapper<Object> deleteByKey(@RequestBody UserTemDetails userTemDetails) {

        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {

            userTemDetailsService.deleteByPrimaryKey(userTemDetails.getThermometerId());
            responseWrapper.setMsg("温度删除成功");
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));

        } catch (Exception e) {
            log.error("温度删除失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    @PutMapping("/modify")
    public ResponseWrapper<Object> ModifyByKey(@RequestBody UserTemDetails userTemDetails) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {

            userTemDetailsService.updateByPrimaryKey(userTemDetails);
            responseWrapper.setMsg("温度修改成功");
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));

        } catch (Exception e) {
            log.error("温度修改失败：", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    @GetMapping("/show")
    public ResponseWrapper<Object> showByKey(@RequestBody UserTemDetails userTemDetails) {

        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {

            responseWrapper.setData(userTemDetailsService.selectByPrimaryKey(userTemDetails.getThermometerId()));
            responseWrapper.setMsg("温度查找成功");
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));

        } catch (Exception e) {
            log.error("温度查找失败：", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

}
