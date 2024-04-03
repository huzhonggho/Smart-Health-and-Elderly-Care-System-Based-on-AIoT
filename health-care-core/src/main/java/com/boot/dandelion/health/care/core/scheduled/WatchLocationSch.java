package com.boot.dandelion.health.care.core.scheduled;

import com.boot.dandelion.health.care.core.service.WatchLocationService;
import com.boot.dandelion.health.care.dao.entity.WatchLocation;
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
import java.util.ArrayList;
import java.util.List;

@Component
public class WatchLocationSch {

    @Resource
    private WatchLocationService service;

    private static final String API_URL = "http://openapi.xiaoxinhuan.com/Open/Tracking";
    private static final String IMEI = "860870050286116";
    private static final String MAP_TYPE = "Baidu";

//    @Scheduled(fixedRate = 600000) // 每十分钟执行一次
    public void fetchDataAndSave() {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(API_URL);
        try {
            // 构造请求体
            StringBuilder requestBody = new StringBuilder();
            requestBody.append("Imei=").append(IMEI)
                    .append("&MapType=").append(MAP_TYPE);
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
                System.out.println(responseString);
                List<WatchLocation> watchLocationList = convertResponseStringToWatchLocationList(responseString);
                watchLocationList.forEach(service::insert);
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

    private List<WatchLocation> convertResponseStringToWatchLocationList(String responseString) {
        List<WatchLocation> watchLocationList = new ArrayList<>();
        try {
            // 使用 Jackson ObjectMapper 解析 JSON 字符串
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseString);

            // 获取 item 节点
            JsonNode itemNode = rootNode.get("item");
            if (itemNode != null) {
                WatchLocation watchLocation = new WatchLocation();
                watchLocation.setDeviceId(itemNode.get("deviceId").asText());
                watchLocation.setDeviceName(itemNode.get("deviceName").asText());
                watchLocation.setLatitude(itemNode.get("latitude").asText());
                watchLocation.setLongitude(itemNode.get("longitude").asText());
                watchLocation.setOLat(itemNode.get("oLat").asText());
                watchLocation.setOLng(itemNode.get("oLng").asText());
                watchLocation.setShowSpeed(itemNode.get("showSpeed").asText());
                watchLocation.setSpeed(itemNode.get("speed").asText());
                watchLocation.setCourse(itemNode.get("course").asText());
                watchLocation.setIsStop(itemNode.get("isStop").asText());
                watchLocation.setAcc(itemNode.get("acc").asText());
                watchLocation.setStatus(itemNode.get("status").asText());
                watchLocation.setDeviceUtcDate(itemNode.get("deviceUtcDate").asText());
                watchLocation.setLastCommunication(itemNode.get("lastCommunication").asText());
                watchLocation.setShowDataType(itemNode.get("showDataType").asText());
                watchLocation.setDataType(itemNode.get("dataType").asText());
                watchLocation.setShowBattery(itemNode.get("showBattery").asText());
                watchLocation.setBattery(itemNode.get("battery").asText());
                watchLocation.setIcon(itemNode.get("icon").asText());
                watchLocation.setDistance(itemNode.get("distance").asText());
                watchLocation.setWatchId(IMEI);
                // 添加到列表中
                watchLocationList.add(watchLocation);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常
        }

        return watchLocationList;
    }


}