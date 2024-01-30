package com.boot.dandelion.health.care.core.controller;

import com.boot.dandelion.health.care.common.enums.ResultCodeEnum;
import com.boot.dandelion.health.care.common.wrapper.ResponseWrapper;
import com.boot.dandelion.health.care.core.service.MattressHisService;
import com.boot.dandelion.health.care.dao.entity.MattressHistory;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
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
@RequestMapping("/mattress")
public class MattressController {
    @Resource
    private MattressHisService mattressHisService;

    @ApiOperation("分页和模糊查询")
    @GetMapping(value = "/history/{page}/{size}")
    public ResponseWrapper<Object> getHistory(@PathVariable Integer page, @PathVariable Integer size,@RequestParam String mattressId, @RequestParam String date) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("pageSize", size);
            params.put("offset", page);
            params.put("mattressId", mattressId);
            params.put("date", date);

            List<MattressHistory> reviewList = mattressHisService.selectByPageAndSearch(params);
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
