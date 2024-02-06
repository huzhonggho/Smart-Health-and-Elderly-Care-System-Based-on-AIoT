package com.boot.dandelion.health.care.core.controller;

import com.boot.dandelion.health.care.common.enums.ResultCodeEnum;
import com.boot.dandelion.health.care.common.wrapper.ResponseWrapper;
import com.boot.dandelion.health.care.core.service.SleepResultService;
import com.boot.dandelion.health.care.dao.entity.SleepEntity;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Slf4j

@PropertySources(value = {@PropertySource(value = {"classpath:important.properties", "classpath:application.properties"},
        encoding = "utf-8")})
@RestController
@RequestMapping("/mattress")
public class SleepDataController {
    @Resource
    private SleepResultService sleepResultService;

    @ApiOperation("分页和模糊查询")
    @GetMapping(value = "/sleepdata/{page}/{size}")
    public ResponseWrapper<Object> getHistory(@PathVariable Integer page, @PathVariable Integer size,@RequestParam String mattressId,@RequestParam String date) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
//        date类型为2023-12-17 8:00:00，查询睡觉时间为前日的8时---今日8时
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime endDateTime = LocalDateTime.parse(date, formatter);
            LocalDateTime startDateTime = endDateTime.minusHours(24);
            String startdate = startDateTime.format(formatter);
            Map<String, Object> params = new HashMap<>();
            params.put("pageSize", size);
            params.put("offset", page);
            params.put("mattressId", mattressId);
            params.put("startDate", startdate);
            params.put("endDate", date);

            List<SleepEntity> reviewList = sleepResultService.selectByPageAndSearch(params);
            responseWrapper.setData(reviewList);
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
            responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
        } catch (Exception e) {
            log.error("分页和模糊查询失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ExceptionUtils.getStackTrace(e));
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
        }
        return responseWrapper;
    }

}
