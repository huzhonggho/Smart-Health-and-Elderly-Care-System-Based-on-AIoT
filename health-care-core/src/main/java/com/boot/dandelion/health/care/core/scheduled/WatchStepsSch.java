package com.boot.dandelion.health.care.core.scheduled;

import com.boot.dandelion.health.care.core.service.WatchStepsService;
import com.boot.dandelion.health.care.dao.entity.WatchSteps;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component

public class WatchStepsSch {

    @Resource
    private WatchStepsService service;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final String API_URL = "http://openapi.xiaoxinhuan.com/Open/GetStepsForHour";
    private static final String IMEI = "860870050286116";
    private static final String TIME_OFFSET = "8";

//    @Scheduled(fixedRate = 3600000) // 每小时执行一次
    public void fetchDataAndSave() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String DATE = LocalDate.now().format(DATE_FORMATTER);

        HttpPost httpPost = new HttpPost(API_URL);
        try {
            // 构造请求体
            StringBuilder requestBody = new StringBuilder();
            requestBody.append("Imei=").append(IMEI)
                    .append("&Date=").append(DATE)
                    .append("&TimeOffSet=").append(TIME_OFFSET);
            StringEntity stringEntity = new StringEntity(requestBody.toString());

            // 设置请求头
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.setEntity(stringEntity);

            // 发起请求
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();

            // 处理响应
            if (entity != null) {
                String responseString = EntityUtils.toString(entity);

                List<WatchSteps> watchStepsList = convertResponseStringToWatchStepsList(responseString);
                Map<String, Object> param = new HashMap<>();
                param.put("watchId", IMEI);
                param.put("date", DATE);
                if (service.selectByDateAndWatchId(param).size() != 0) {
                    watchStepsList.forEach(service::updateByHour);
                } else {
                    watchStepsList.forEach(service::insert);
                }
//                System.out.println("Response: " + responseString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private List<WatchSteps> convertResponseStringToWatchStepsList(String responseString) {
        List<WatchSteps> watchStepsList = new ArrayList<>();
        String DATE = LocalDate.now().format(DATE_FORMATTER);
        try {
            // 使用 Jackson ObjectMapper 解析 JSON 字符串
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseString);

            // 获取 Items 数组节点
            JsonNode itemsNode = rootNode.get("Items");
            if (itemsNode != null && itemsNode.isArray() && itemsNode.size() > 0) {
                for (JsonNode itemNode : itemsNode) {
                    WatchSteps watchSteps = new WatchSteps();
                    watchSteps.setHour(itemNode.get("Hour").asText());
                    watchSteps.setSteps(itemNode.get("Steps").asText());
                    watchSteps.setDistance(itemNode.get("Distance").asText());
                    watchSteps.setCalories(itemNode.get("Cariello").asText());
                    watchSteps.setWatchId(IMEI);
                    watchSteps.setDate(DATE);
                    // 这里可以设置其他字段值，如watchId、date等，根据具体情况而定

                    watchStepsList.add(watchSteps);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常
        }

        return watchStepsList;
    }

}
