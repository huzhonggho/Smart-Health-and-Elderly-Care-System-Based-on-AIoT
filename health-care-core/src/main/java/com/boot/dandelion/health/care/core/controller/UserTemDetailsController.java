package com.boot.dandelion.health.care.core.controller;

import com.boot.dandelion.health.care.common.enums.ResultCodeEnum;
import com.boot.dandelion.health.care.common.wrapper.ResponseWrapper;
import com.boot.dandelion.health.care.core.service.UserTemDetailsService;
import com.boot.dandelion.health.care.dao.entity.UserBloodDetails;
import com.boot.dandelion.health.care.dao.entity.UserBloodOxygen;
import com.boot.dandelion.health.care.dao.entity.UserTemDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

            userTemDetailsService.deleteByPrimaryKey(userTemDetails);
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

            responseWrapper.setData(userTemDetailsService.selectByPrimaryKey(userTemDetails));
            responseWrapper.setMsg("温度查找成功");
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));

        } catch (Exception e) {
            log.error("温度查找失败：", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    @GetMapping("/showAll")
    public ResponseWrapper<List<UserTemDetails>> getAll() {
        ResponseWrapper<List<UserTemDetails>> responseWrapper = new ResponseWrapper<>();
        try {
            List<UserTemDetails> allData = userTemDetailsService.selectAll();
            responseWrapper.setData(allData);
            responseWrapper.setMsg("所有信息查询成功");
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
        } catch (Exception e) {
            log.error("所有信息查询失败：", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    @GetMapping("/showBetweenDate")
    public ResponseWrapper<List<UserTemDetails>> showBetweenDate(@RequestParam String start, @RequestParam String end) {
        ResponseWrapper<List<UserTemDetails>> responseWrapper = new ResponseWrapper<>();
        try {
            List<UserTemDetails> allData = userTemDetailsService.showBetweenDate(start,end);
            responseWrapper.setData(allData);
            responseWrapper.setMsg("所有信息查询成功");
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
        } catch (Exception e) {
            log.error("所有信息查询失败：", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }
}
