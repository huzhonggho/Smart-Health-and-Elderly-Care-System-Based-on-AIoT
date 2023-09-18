package com.boot.dandelion.health.care.core.controller;


import com.boot.dandelion.health.care.common.enums.ResultCodeEnum;
import com.boot.dandelion.health.care.common.wrapper.ResponseWrapper;
import com.boot.dandelion.health.care.core.service.UserWeightDetailsService;
import com.boot.dandelion.health.care.dao.entity.UserWeightDetails;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/userWeightDetails")
public class UserWeightDetailsController {
    @Autowired
    private UserWeightDetailsService userWeightDetailsService;

    /**
     * 展示信息
     * @return
     */
    @GetMapping(value = "/showAll")
    public ResponseWrapper<List<UserWeightDetails>> showUserList() {
        ResponseWrapper<List<UserWeightDetails>> responseWrapper = new ResponseWrapper<>();
        try {
            List<UserWeightDetails> userList = userWeightDetailsService.selectAll();
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

}
