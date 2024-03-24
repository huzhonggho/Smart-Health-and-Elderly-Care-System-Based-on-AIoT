package com.boot.dandelion.health.care.core.controller;

import com.boot.dandelion.health.care.common.enums.ResultCodeEnum;
import com.boot.dandelion.health.care.common.wrapper.ResponseWrapper;
import com.boot.dandelion.health.care.core.service.WatchStepsService;
import com.boot.dandelion.health.care.dao.entity.MattressDetail;
import com.boot.dandelion.health.care.dao.entity.WatchSteps;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Slf4j

@PropertySources(value = {@PropertySource(value = {"classpath:important.properties", "classpath:application.properties"},
        encoding = "utf-8")})
@RestController
@Validated
@RequestMapping("/watch")
public class WatchController {

    @Resource
    private WatchStepsService watchStepsService;

    @ApiOperation("查询手表步数")
    @GetMapping(value = "/steps")
    public ResponseWrapper<Object> getSreps(@RequestParam String watchId, @RequestParam String date) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            // Validate input parameters
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
            if (watchId == null || watchId.equals("")) {
                responseWrapper.setMsg("watchId不能为空");
                return responseWrapper;
            }
            if (date == null || date.equals("")) {
                responseWrapper.setMsg("date不能为空");
                return responseWrapper;
            }

            Map<String, Object> params = new HashMap<>();
            params.put("watchId", watchId);
            params.put("date", date);
            List<WatchSteps> watchStepsList = watchStepsService.selectByDateAndWatchId(params);
            responseWrapper.setData(watchStepsList);
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
            responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
        } catch (
                Exception e) {
            log.error("查询手表步数：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ExceptionUtils.getStackTrace(e));
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
        }
        return responseWrapper;

    }



}
