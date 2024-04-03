package com.boot.dandelion.health.care.core.scheduled;

import com.boot.dandelion.health.care.common.util.AuthorizedUtils;
import com.boot.dandelion.health.care.common.util.CloudPlatformClientUtil;
import com.boot.dandelion.health.care.core.service.MattressOutBedService;
import com.boot.dandelion.health.care.dao.entity.MattressHistory;
import com.boot.dandelion.health.care.dao.entity.MattressOutBed;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component

public class MattressOutBedSch {
    @Autowired
    private MattressOutBedService service;
    private static final String API_URL = "http://www.sdhtjr.com:5859/vms/getparam.asp";
    private final String MATTRESSID = "B00681";
    private final String REGIONAL = "TestUnit";
    private SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Scheduled(fixedRate = 3600000) // 每小时执行一次
    public void fetchDataAndSaveToDatabase() {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // 构建参数字符串
            String authorized = AuthorizedUtils.generateAuthorizationCode("OffBed");
            String searchDate = getPreviousDate();
            String startTime = getPreviousTime();
            String endTime = getNowTime();


            // 构建完整的API URL
            String fullUrl = API_URL + "?Authorized=" + authorized +
                    "&Regional=" + REGIONAL +
                    "&id=" + MATTRESSID +
                    "&searchDate=" + searchDate +
                    "&startTime=" + startTime +
                    "&endTime=" + endTime;
            // 发起HTTP GET请求
            HttpGet httpGet = new HttpGet(fullUrl);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();


            if (entity != null) {
                String responseString = EntityUtils.toString(entity);
                // 处理响应数据，可以根据需要解析JSON等操作

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(responseString);

                if (rootNode.has("data")) {
                    JsonNode dataArray = rootNode.get("data");

                    for (JsonNode data : dataArray) {

                        if (data.has("data")) {
                            JsonNode innerDataArray = data.get("data");

                            for (JsonNode innerData : innerDataArray) {

                                if (data.has("data")) {
                                    JsonNode statusArray = innerData.get("data");

                                    for (JsonNode status : statusArray) {
                                        MattressOutBed outBed = new MattressOutBed();
                                        String[] splitStart = status.path("start").asText().split("\\s+");
                                        String[] splitEnd = status.path("end").asText().split("\\s+");
                                        outBed.setStart(splitStart[1]);
                                        outBed.setEnd(splitEnd[1]);
                                        outBed.setDate(splitStart[0]);
                                        outBed.setMattressId(MATTRESSID);
                                        service.insert(outBed);

                                    }
                                }
                            }
                        }
                    }
                }

            }

            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getPreviousTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        // 减去一个小时
        LocalDateTime prevHourTime = currentTime.minusHours(1);
        String formattedDate = prevHourTime.format(formatter);

        return formattedDate;
    }

    private String getNowTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedDate = currentTime.format(formatter);

        return formattedDate;
    }

    private String getPreviousDate() {
        LocalDateTime currentDate = LocalDateTime.now().minusHours(1);

        // 自定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 格式化当前日期为字符串
        String formattedDate = currentDate.format(formatter);

        return formattedDate;
    }

}
