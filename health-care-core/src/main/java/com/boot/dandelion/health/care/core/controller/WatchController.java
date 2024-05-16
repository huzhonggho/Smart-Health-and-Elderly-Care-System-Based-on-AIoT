package com.boot.dandelion.health.care.core.controller;

import com.boot.dandelion.health.care.common.entity.EHeart;
import com.boot.dandelion.health.care.common.enums.ResultCodeEnum;
import com.boot.dandelion.health.care.common.wrapper.ResponseWrapper;
import com.boot.dandelion.health.care.core.service.*;
import com.boot.dandelion.health.care.dao.entity.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Resource
    private WatchLocationService watchLocationService;
    @Resource
    private WatchBloodPressureService watchBloodPressureService;
    @Resource
    private WatchBloodOxygenService watchBloodOxygenService;
    @Resource
    private WatchHeartService watchHeartService;

    @ApiOperation("查询手表步数")
    @GetMapping(value = "/steps")
    public ResponseWrapper<Object> getSteps(@RequestParam String watchId, @RequestParam String date) {
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

    @ApiOperation("查询手表位置")
    @GetMapping(value = "/location")
    public ResponseWrapper<Object> getLocation(@RequestParam String watchId, @RequestParam String date) {
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
            List<WatchLocation> watchStepsList = watchLocationService.selectByDateAndWatchId(params);
            responseWrapper.setData(watchStepsList);
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
            responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
        } catch (
                Exception e) {
            log.error("查询手表位置：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ExceptionUtils.getStackTrace(e));
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
        }
        return responseWrapper;

    }

    @ApiOperation("查询手表血压")
    @GetMapping(value = "/pressure")
    public ResponseWrapper<Object> getPressure(@RequestParam String watchId, @RequestParam String date) {
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
            List<WatchBloodPressure> watchBloodPressureList = watchBloodPressureService.selectByWatchIdAndDate(params);
            responseWrapper.setData(watchBloodPressureList);
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
            responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
        } catch (
                Exception e) {
            log.error("查询手表血压：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ExceptionUtils.getStackTrace(e));
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
        }
        return responseWrapper;

    }

    @ApiOperation("查询手表血氧")
    @GetMapping(value = "/oxygen")
    public ResponseWrapper<Object> getOxygen(@RequestParam String watchId, @RequestParam String date) {
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
            List<WatchBloodOxygen> watchBloodOxygenList = watchBloodOxygenService.selectByWatchIdAndDate(params);
            responseWrapper.setData(watchBloodOxygenList);
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
            responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
        } catch (
                Exception e) {
            log.error("查询手表血氧：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ExceptionUtils.getStackTrace(e));
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
        }
        return responseWrapper;

    }

    @ApiOperation("查询手表心率")
    @GetMapping(value = "/heart")
    public ResponseWrapper<Object> getHeart(@RequestParam String watchId, @RequestParam String date) {
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
            List<WatchHeart> watchHeartList = watchHeartService.selectByWatchIdAndDate(params);
            List<EHeart> hearts = new ArrayList<>();

            for (WatchHeart info :
                    watchHeartList) {
                EHeart eheart = new EHeart();
                eheart.setWatchId(info.getWatchId());
                eheart.setHeartId(info.getHeartId());
                eheart.setHeartbeat(info.getHeartbeat());
                eheart.setLastUpdate(info.getLastUpdate());

                hearts.add(eheart);
            }


            responseWrapper.setData(hearts);
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
            responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
        } catch (
                Exception e) {
            log.error("查询手表心率：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ExceptionUtils.getStackTrace(e));
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
        }
        return responseWrapper;

    }

}
