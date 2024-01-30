package com.boot.dandelion.health.care.core.controller;

import com.boot.dandelion.health.care.common.enums.ResultCodeEnum;
import com.boot.dandelion.health.care.common.wrapper.ResponseWrapper;
import com.boot.dandelion.health.care.core.service.UserBloodOxygenService;
import com.boot.dandelion.health.care.dao.entity.UserBloodDetails;
import com.boot.dandelion.health.care.dao.entity.UserBloodOxygen;
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
@RequestMapping("/api/userBloodOxygen")
@Api(tags = "标准演示接口")
public class UserBloodOxygenController {

    @Resource
    private UserBloodOxygenService userBloodOxygenService;

    @PostMapping("/add")
    @ApiOperation(value = "接受json参数", notes = "演示json参数是否接受成功")
    public ResponseWrapper<Object> add(@RequestBody UserBloodOxygen userBloodOxygen) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            if (userBloodOxygen.getTenantId() == null || userBloodOxygen.getUserId().isEmpty()) {
                responseWrapper.setMsg(ResultCodeEnum.EXIT_EMPTY_VALUE.getName());
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
            } else {
                userBloodOxygenService.insert(userBloodOxygen);
                responseWrapper.setMsg("血氧添加成功");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.ADD_SUCCESS.getCode()));
            }
        } catch (Exception e) {
            log.error("血氧添加失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    @DeleteMapping("/delete")
    public ResponseWrapper<Object> deleteByKey(@RequestBody UserBloodOxygen userBloodOxygen) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            if (userBloodOxygen.getTenantId() == null || userBloodOxygen.getUserId().isEmpty()) {
                responseWrapper.setMsg(ResultCodeEnum.EXIT_EMPTY_VALUE.getName());
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
            } else {
                userBloodOxygenService.deleteByPrimaryKey(userBloodOxygen);
                responseWrapper.setMsg("血氧删除成功");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
                responseWrapper.setData(userBloodOxygen.toString());
            }
        } catch (Exception e) {
            log.error("血氧删除失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    @PutMapping("/modify")
    public ResponseWrapper<Object> modifyByKey(@RequestBody UserBloodOxygen userBloodOxygen) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            if (userBloodOxygen.getTenantId() == null || userBloodOxygen.getUserId().isEmpty()) {
                responseWrapper.setMsg(ResultCodeEnum.EXIT_EMPTY_VALUE.getName());
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
            } else {
                userBloodOxygenService.updateByPrimaryKey(userBloodOxygen);
                responseWrapper.setMsg("血氧修改成功");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.UPDATE_SUCCESS.getCode()));
                responseWrapper.setData(userBloodOxygen.toString());
            }
        } catch (Exception e) {
            log.error("血氧修改失败：", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.UPDATE_FAIL.getCode()));
        }
        return responseWrapper;
    }

    @GetMapping("/show")
    @CrossOrigin(origins = "*")
    public ResponseWrapper<Object> showByKey(@RequestBody UserBloodOxygen userBloodOxygen) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            if (userBloodOxygen.getTenantId() == null || userBloodOxygen.getUserId().isEmpty()) {
                responseWrapper.setMsg(ResultCodeEnum.EXIT_EMPTY_VALUE.getName());
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
            } else {
                UserBloodOxygen select = userBloodOxygenService.selectByPrimaryKey(userBloodOxygen);
                if (select == null) {
                    responseWrapper.setMsg("血氧查找为空");

                } else {
                    responseWrapper.setMsg("血氧查找成功");
                }
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.RECORDS_GET_SUCCESS.getCode()));
                responseWrapper.setData(select);

            }
        } catch (Exception e) {
            log.error("血氧查找失败：", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    @GetMapping("/showAll")
    public ResponseWrapper<List<UserBloodOxygen>> getAll() {
        ResponseWrapper<List<UserBloodOxygen>> responseWrapper = new ResponseWrapper<>();
        try {
            List<UserBloodOxygen> allData = userBloodOxygenService.selectAll();
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
    public ResponseWrapper<List<UserBloodOxygen>> showBetweenDate(@RequestParam String start, @RequestParam String end) {
        ResponseWrapper<List<UserBloodOxygen>> responseWrapper = new ResponseWrapper<>();
        try {
            List<UserBloodOxygen> allData = userBloodOxygenService.showBetweenDate(start,end);
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
