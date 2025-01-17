package com.boot.dandelion.health.care.core.scheduled;

import com.boot.dandelion.health.care.common.util.DateUtils;
import com.boot.dandelion.health.care.core.service.WatchBloodOxygenService;
import com.boot.dandelion.health.care.core.service.WatchBloodPressureService;
import com.boot.dandelion.health.care.core.service.WatchHeartService;
import com.boot.dandelion.health.care.dao.entity.WatchBloodOxygen;
import com.boot.dandelion.health.care.dao.entity.WatchBloodPressure;
import com.boot.dandelion.health.care.dao.entity.WatchHeart;
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
import java.util.List;

@Component

public class WatchHealthInfoSch {
    @Resource
    private WatchBloodPressureService watchBloodPressureService;
    @Resource
    private WatchBloodOxygenService watchBloodOxygenService;
    @Resource
    private WatchHeartService watchHeartService;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final String API_URL = "http://openapi.xiaoxinhuan.com/Open/GetHealth";
    private static final String IMEI = "860870050286116";
    private static final String TIME_OFFSET = "8";

    @Scheduled(fixedRate = 3600000) // 每小时执行一次
    public void fetchDataAndSave() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String DATE = LocalDate.now().format(DATE_FORMATTER);

        HttpPost httpPost = new HttpPost(API_URL);
        try {
            // 构造请求体
            StringBuilder requestBody = new StringBuilder();
            requestBody.append("Imei=").append(IMEI)
                    .append("&StartTime=").append(DateUtils.getPreviousDate())
                    .append("&EndTime=").append(DateUtils.getNowDate())
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
                convertBloodPressure(responseString).forEach(watchBloodPressureService::insert);
                convertBloodOxygen(responseString).forEach(watchBloodOxygenService::insert);
                convertHeart(responseString).forEach(watchHeartService::insert);
                System.out.println(responseString);
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

    private List<WatchBloodPressure> convertBloodPressure(String responseString) {
        List<WatchBloodPressure> list = new ArrayList<>();
        try {
            // 使用 Jackson ObjectMapper 解析 JSON 字符串
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseString);

            // 获取 Items 数组节点
            JsonNode itemsNode = rootNode.get("BloodPressureItems");
            if (itemsNode != null && itemsNode.isArray() && itemsNode.size() > 0) {
                for (JsonNode itemNode : itemsNode) {
                    WatchBloodPressure watch = new WatchBloodPressure();
                    watch.setWatchId(IMEI);
                    watch.setDiastolic(itemNode.get("Diastolic").asText());
                    watch.setShrink(itemNode.get("Shrink").asText());
                    watch.setLastUpdate(itemNode.get("LastUpdate").asText());

                    list.add(watch);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常
        }

        return list;
    }

    private List<WatchBloodOxygen> convertBloodOxygen(String responseString) {
        List<WatchBloodOxygen> list = new ArrayList<>();
        try {
            // 使用 Jackson ObjectMapper 解析 JSON 字符串
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseString);

            // 获取 Items 数组节点
            JsonNode itemsNode = rootNode.get("BloodOxygenItems");
            if (itemsNode != null && itemsNode.isArray() && itemsNode.size() > 0) {
                for (JsonNode itemNode : itemsNode) {
                    WatchBloodOxygen watch = new WatchBloodOxygen();
                    watch.setWatchId(IMEI);
                    watch.setLastUpdate(itemNode.get("LastUpdate").asText());
                    watch.setBloodOxygen(itemNode.get("BloodOxygen").asText());

                    list.add(watch);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常
        }

        return list;
    }

    private List<WatchHeart> convertHeart(String responseString) {
        List<WatchHeart> list = new ArrayList<>();
        try {
            // 使用 Jackson ObjectMapper 解析 JSON 字符串
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseString);

            // 获取 Items 数组节点
            JsonNode itemsNode = rootNode.get("Items");
            if (itemsNode != null && itemsNode.isArray() && itemsNode.size() > 0) {
                for (JsonNode itemNode : itemsNode) {
                    WatchHeart watch = new WatchHeart();
                    watch.setWatchId(IMEI);
                    watch.setSteps(itemNode.get("Steps").asText());
                    watch.setHeartbeat(itemNode.get("Heartbeat").asText());
                    watch.setBloodPressure(itemNode.get("BloodPressure").asText());
                    watch.setDiastolic(itemNode.get("Diastolic").asText());
                    watch.setShrink(itemNode.get("Shrink").asText());
                    watch.setLastUpdate(itemNode.get("LastUpdate").asText());
                    watch.setBloodSugar(itemNode.get("BloodSugar").asText());
                    watch.setBloodOxygen(itemNode.get("BloodOxygen").asText());

                    list.add(watch);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常
        }

        return list;
    }
}
