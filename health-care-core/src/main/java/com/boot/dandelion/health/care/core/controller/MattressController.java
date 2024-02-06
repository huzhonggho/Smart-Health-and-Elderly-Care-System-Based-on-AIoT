package com.boot.dandelion.health.care.core.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.boot.dandelion.health.care.common.enums.ResultCodeEnum;
import com.boot.dandelion.health.care.common.wrapper.ResponseWrapper;
import com.boot.dandelion.health.care.core.service.MattressDetailService;
import com.boot.dandelion.health.care.core.service.MattressHisService;
import com.boot.dandelion.health.care.dao.entity.MattressDetail;
import com.boot.dandelion.health.care.dao.entity.MattressHistory;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.util.annotation.NonNull;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@Slf4j

@PropertySources(value = {@PropertySource(value = {"classpath:important.properties", "classpath:application.properties"},
        encoding = "utf-8")})
@RestController
@Validated
@RequestMapping("/mattress")
public class MattressController {
    @Resource
    private MattressHisService mattressHisService;
    @Resource
    private MattressDetailService mattressDetailService;

    @ApiOperation("分页和模糊查询")
    @GetMapping(value = "/history/{page}/{size}")
    public ResponseWrapper<Object> getHistory(@PathVariable Integer page, @PathVariable Integer size, @RequestParam
    String mattressId, @RequestParam String date, @RequestParam String state, @RequestParam String alarm) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            // Validate input parameters
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
            if (mattressId == null || mattressId.equals("")) {
                responseWrapper.setMsg("MattressId不能为空");
                return responseWrapper;
            }
            if (date == null || date.equals("")) {
                responseWrapper.setMsg("date不能为空");
                return responseWrapper;
            }
            if (page < 0) {
                responseWrapper.setMsg("page不能小于零");
                return responseWrapper;
            }
            if (size < 0) {
                responseWrapper.setMsg("size不能小于零");
                return responseWrapper;
            }
            Map<String, Object> params = new HashMap<>();

            params.put("pageSize", size);
            params.put("offset", page * size);
            params.put("mattressId", mattressId);
            params.put("date", date);
            params.put("state", state);
            params.put("alam", alarm);

            List<MattressHistory> getList = mattressHisService.selectByPageAndSearch(params);


            Map<String, Object> responseData = new HashMap<>();
            responseData.put("total", mattressHisService.selectCountByMattressIdAndDate(params));
            responseData.put("list", getList);

            responseWrapper.setData(responseData);
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
            responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
        } catch (Exception e) {
            log.error("分页和模糊查询失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ExceptionUtils.getStackTrace(e));
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
        }
        return responseWrapper;
    }

    @ApiOperation("根据mattressId和日期查询")
    @GetMapping(value = "/detail")
    public ResponseWrapper<Object> getDetail(@RequestParam String mattressId, @RequestParam String date) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            // Validate input parameters
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
            if (mattressId == null || mattressId.equals("")) {
                responseWrapper.setMsg("MattressId不能为空");
                return responseWrapper;
            }
            if (date == null || date.equals("")) {
                responseWrapper.setMsg("date不能为空");
                return responseWrapper;
            }

            Map<String, Object> params = new HashMap<>();
            params.put("mattressId", mattressId);
            params.put("date", date);
            MattressDetail mattressDetail = mattressDetailService.selectByDateAndMattressId(params);
            responseWrapper.setData(mattressDetail);
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
            responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
        } catch (
                Exception e) {
            log.error("查询基本信息评审记录：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ExceptionUtils.getStackTrace(e));
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
        }
        return responseWrapper;

    }

    @ApiOperation("导出")
    @PostMapping("export")
    public ResponseWrapper<Object> exportExcel(HttpServletResponse response, @RequestParam String mattressId, @RequestParam String date) throws Exception {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            response.setContentType("application/octet-stream;charset=UTF-8");
            String fileNameUtf = URLEncoder.encode(date + "-" + mattressId, "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileNameUtf + ".xlsx");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

            //designate template
//        String templateFileName = System.getProperty("user.dir") + "/excelTemplate/mattressHis.xlsx";
            String templateFileName = "C:/wwwroot/health_care" + "/excelTemplate/mattressHis.xlsx";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // 获取当前时间
            LocalDateTime currentTime = LocalDateTime.parse(date + " 08:00:00", formatter);
            // 获取昨天的当前时间
            LocalDateTime yesterday = currentTime.minusDays(1);

            Map<String, Object> todayParams = new HashMap<>();
            todayParams.put("mattressId", mattressId);
            todayParams.put("date", date);
            List<MattressHistory> todayList = mattressHisService.selectByDateAndMattressId(todayParams);

            Map<String, Object> yesParams = new HashMap<>();
            yesParams.put("mattressId", mattressId);
            yesParams.put("date", LocalDate.parse(date).minusDays(1).toString());
            List<MattressHistory> yesList = mattressHisService.selectByDateAndMattressId(yesParams);

            // 获取昨天早上八点的时间
            LocalDateTime yesterdayEightAM = LocalDateTime.of(yesterday.toLocalDate(), LocalTime.of(8, 0));

            // 获取今天早上八点的时间
            LocalDateTime todayEightAM = LocalDateTime.of(currentTime.toLocalDate(), LocalTime.of(8, 0));

            List<MattressHistory> allList = new ArrayList<>(yesList);
            allList.addAll(todayList);
            // 定义日期时间格式化器
            if (allList.size() == 0) {
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                responseWrapper.setMsg("该时段无数据");
                return responseWrapper;
            }
            // 使用迭代器遍历并修改集合中每个对象的属性
            Iterator<MattressHistory> iterator = allList.iterator();
            while (iterator.hasNext()) {
                MattressHistory obj = iterator.next();

                if (obj.getTurn().equals("n")) {
                    obj.setTurn("没有翻身");
                }
                if (obj.getTurn().equals("y")) {
                    obj.setTurn("翻身");
                }
                if (obj.getWet().equals("n")) {
                    obj.setWet("没有尿湿");
                }
                if (obj.getWet().equals("y")) {
                    obj.setWet("尿湿");
                }
                if (obj.getState().equals("20")) {
                    obj.setState("在床");
                }
                if (obj.getState().equals("41")) {
                    obj.setState("体动");
                }
                if (obj.getState().equals("50")) {
                    obj.setState("离床");
                }
                if (obj.getState().equals("31")) {
                    obj.setState("护士呼叫");
                }
            }
            // 筛选昨天早上八点到今天早上八点的数据
            List<MattressHistory> filteredList = allList.stream()
                    .filter(history -> {
                        LocalDateTime historyDateTime = LocalDateTime.parse(history.getDate() + " " + history.getDuration().split("-")[0], formatter);
                        return historyDateTime.isAfter(yesterdayEightAM) && historyDateTime.isBefore(todayEightAM);
                    })
                    .collect(Collectors.toList());
            //往response 的输出流中写入数据
            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).withTemplate(templateFileName).build();
            WriteSheet writeSheet = EasyExcel.writerSheet().build();
            FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();

            //写入materils中的数据
            excelWriter.fill(filteredList, fillConfig, writeSheet);

            //关闭流
            excelWriter.finish();
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
            responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
        } catch (Exception e) {
            log.error("导出失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ExceptionUtils.getStackTrace(e));
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
        }
        return responseWrapper;
    }
}
