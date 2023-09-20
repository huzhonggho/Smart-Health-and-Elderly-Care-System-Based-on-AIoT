package com.boot.dandelion.health.care.core.controller;

import com.boot.dandelion.health.care.common.enums.ResultCodeEnum;
import com.boot.dandelion.health.care.common.wrapper.ResponseWrapper;
import com.boot.dandelion.health.care.core.service.PsnNursingRecordService;
import com.boot.dandelion.health.care.dao.entity.PsnNursingRecord;
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
@RequestMapping("/api/psnNursingRecord")
public class PsnNursingRecordController {

    @Resource
    private PsnNursingRecordService psnNursingRecordService;

    @PostMapping("/add")
    public ResponseWrapper<Object> add(@RequestBody PsnNursingRecord psnNursingRecord) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {

            System.out.println(psnNursingRecord.toString());

            psnNursingRecordService.addPsnNursingRecord(psnNursingRecord);
            responseWrapper.setMsg("护理记录添加成功");
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));

        } catch (Exception e) {
            log.error("护理记录添加失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    @DeleteMapping("/delete")
    public ResponseWrapper<Object> deleteByKey(@RequestBody PsnNursingRecord psnNursingRecord) {

        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {

            psnNursingRecordService.deleteByPrimaryKey(psnNursingRecord);
            responseWrapper.setMsg("护理记录删除成功");
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));

        } catch (Exception e) {
            log.error("护理记录删除失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    @PutMapping("/modify")
    public ResponseWrapper<Object> ModifyByKey(@RequestBody PsnNursingRecord psnNursingRecord) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {

            psnNursingRecordService.updateByPrimaryKey(psnNursingRecord);
            responseWrapper.setMsg("护理记录修改成功");
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));

        } catch (Exception e) {
            log.error("护理记录修改失败：", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    @GetMapping("/show")
    public ResponseWrapper<Object> showByKey(@RequestBody PsnNursingRecord psnNursingRecord) {

        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {

            responseWrapper.setData(psnNursingRecordService.selectByPrimaryKey(psnNursingRecord));
            responseWrapper.setMsg("护理记录查找成功");
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));

        } catch (Exception e) {
            log.error("护理记录查找失败：", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    @GetMapping("/showAll")
    public ResponseWrapper<List<PsnNursingRecord>> getAll() {
        ResponseWrapper<List<PsnNursingRecord>> responseWrapper = new ResponseWrapper<>();
        try {
            List<PsnNursingRecord> allData = psnNursingRecordService.selectAll();
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
