package com.boot.dandelion.health.care.core.controller;


import com.boot.dandelion.health.care.common.enums.ResultCodeEnum;
import com.boot.dandelion.health.care.common.wrapper.ResponseWrapper;
import com.boot.dandelion.health.care.core.service.UserBloodDetailsService;
import com.boot.dandelion.health.care.dao.entity.UserBloodDetails;
import com.boot.dandelion.health.care.dao.entity.UserBloodSugar;
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
@RequestMapping("/api/userBloodDetails")
public class UserBloodDetailsController {

    @Resource
    private UserBloodDetailsService userBloodDetailsService;

    @PostMapping("/add")
    public ResponseWrapper<Object> add(@RequestBody UserBloodDetails userBloodDetails){
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {

            System.out.println(userBloodDetails.toString());

            userBloodDetailsService.insert(userBloodDetails);
            responseWrapper.setMsg("血压添加成功");
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));

        } catch (Exception e) {
            log.error("血压添加失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    @DeleteMapping("/delete")
    public ResponseWrapper<Object> deleteByKey(@RequestBody  UserBloodDetails userBloodDetails) {

        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {

            userBloodDetailsService.deleteByPrimaryKey(userBloodDetails);
            responseWrapper.setMsg("血压删除成功");
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));

        } catch (Exception e) {
            log.error("血压删除失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    @PutMapping("/modify")
    public ResponseWrapper<Object> ModifyByKey(@RequestBody UserBloodDetails userBloodDetails) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {

            userBloodDetailsService.updateByPrimaryKey(userBloodDetails);
            responseWrapper.setMsg("血压修改成功");
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));

        } catch (Exception e) {
            log.error("血压修改失败：", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

//    @GetMapping("/show")
//    public ResponseWrapper<Object> showByKey(@RequestBody  UserBloodDetails userBloodDetails) {
//
//        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
//        try {
//
//            responseWrapper.setData(userBloodDetailsService.selectByPrimaryKey(userBloodDetails));
//            responseWrapper.setMsg("血压查找成功");
//            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
//
//        } catch (Exception e) {
//            log.error("血压查找失败：", ExceptionUtils.getStackTrace(e));
//            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
//            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
//        }
//        return responseWrapper;
//    }
@GetMapping("/showAll")
public ResponseWrapper<List<UserBloodDetails>> getAll() {
    ResponseWrapper<List<UserBloodDetails>> responseWrapper = new ResponseWrapper<>();
    try {
        List<UserBloodDetails> allData = userBloodDetailsService.show();
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
