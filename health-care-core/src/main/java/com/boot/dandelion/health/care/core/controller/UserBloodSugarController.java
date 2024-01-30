package com.boot.dandelion.health.care.core.controller;


import com.boot.dandelion.health.care.common.enums.ResultCodeEnum;
import com.boot.dandelion.health.care.common.wrapper.ResponseWrapper;
import com.boot.dandelion.health.care.core.service.UserBloodSugarService;
import com.boot.dandelion.health.care.dao.entity.UserBloodDetails;
import com.boot.dandelion.health.care.dao.entity.UserBloodSugar;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = "*")
@Slf4j
@PropertySources(value = {
        @PropertySource(value = {"classpath:important.properties", "classpath:application.properties"},
                encoding = "utf-8")
})
@RestController
@RequestMapping("/api/userBloodSugar")
@Api(tags = "标准演示接口")
public class UserBloodSugarController {

    @Resource
    private UserBloodSugarService userBloodSugarService;

    @PostMapping("/add")
    @ApiOperation(value = "接受json参数", notes = "演示json参数是否接受成功")
    public ResponseWrapper<Object> add(@RequestBody UserBloodSugar userBloodSugar) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            if (userBloodSugar.tenantId == null || userBloodSugar.userId.isEmpty()) {
                responseWrapper.setMsg(ResultCodeEnum.EXIT_EMPTY_VALUE.getName());
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
            } else {
                userBloodSugarService.insert(userBloodSugar);
                responseWrapper.setMsg("血糖添加成功");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.ADD_SUCCESS.getCode()));
            }
        } catch (Exception e) {
            log.error("血糖添加失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    @DeleteMapping("/delete")
    public ResponseWrapper<Object> deleteByKey(@RequestBody UserBloodSugar userBloodSugar) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            if (userBloodSugar.tenantId == null || userBloodSugar.userId.isEmpty()) {
                responseWrapper.setMsg(ResultCodeEnum.EXIT_EMPTY_VALUE.getName());
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
            } else {
                userBloodSugarService.deleteByPrimaryKey(userBloodSugar);
                responseWrapper.setMsg("血糖删除成功");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
                responseWrapper.setData(userBloodSugar.toString());
            }
        } catch (Exception e) {
            log.error("血糖删除失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    @PutMapping("/modify")
    public ResponseWrapper<Object> modifyByKey(@RequestBody UserBloodSugar userBloodSugar) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            if (userBloodSugar.tenantId == null || userBloodSugar.userId.isEmpty()) {
                responseWrapper.setMsg(ResultCodeEnum.EXIT_EMPTY_VALUE.getName());
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
            } else {
                userBloodSugarService.updateByPrimaryKey(userBloodSugar);
                responseWrapper.setMsg("血糖修改成功");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.UPDATE_SUCCESS.getCode()));
                responseWrapper.setData(userBloodSugar.toString());
            }
        } catch (Exception e) {
            log.error("血糖修改失败：", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.UPDATE_FAIL.getCode()));
        }
        return responseWrapper;
    }

    @GetMapping("/show")
    @CrossOrigin(origins = "*")
    public ResponseWrapper<Object> showByKey(@RequestBody UserBloodSugar userBloodSugar) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            if (userBloodSugar.tenantId == null || userBloodSugar.userId.isEmpty()) {
                responseWrapper.setMsg(ResultCodeEnum.EXIT_EMPTY_VALUE.getName());
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
            } else {
                UserBloodSugar select = userBloodSugarService.selectByPrimaryKey(userBloodSugar);
                if (select == null) {
                    responseWrapper.setMsg("血糖查找为空");

                } else {
                    responseWrapper.setMsg("血糖查找成功");
                }
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.RECORDS_GET_SUCCESS.getCode()));
                responseWrapper.setData(select);

            }
        } catch (Exception e) {
            log.error("血糖查找失败：", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    @GetMapping("/showAll")
    public ResponseWrapper<List<UserBloodSugar>> getAll() {
        ResponseWrapper<List<UserBloodSugar>> responseWrapper = new ResponseWrapper<>();
        try {
            List<UserBloodSugar> allData = userBloodSugarService.selectAll();
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
    public ResponseWrapper<List<UserBloodSugar>> showBetweenDate(@RequestParam String start, @RequestParam String end) {
        ResponseWrapper<List<UserBloodSugar>> responseWrapper = new ResponseWrapper<>();
        try {
            List<UserBloodSugar> allData = userBloodSugarService.showBetweenDate(start,end);
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