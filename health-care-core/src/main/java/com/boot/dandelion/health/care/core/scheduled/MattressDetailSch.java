package com.boot.dandelion.health.care.core.scheduled;

import com.boot.dandelion.health.care.common.util.AuthorizedUtils;
import com.boot.dandelion.health.care.core.service.MattressDetailService;
import com.boot.dandelion.health.care.dao.entity.MattressDetail;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component

public class MattressDetailSch {
    @Autowired
    private MattressDetailService service;
    private static final String API_URL = "http://www.sdhtjr.com:5859/vms/getparam.asp";
    private final String MATTRESSID = "B00681";
    private final String REGIONAL = "TestUnit";

//    @Scheduled(fixedRate = 86400000) // 每天执行一次
    public void fetchDataAndSaveToDatabase() {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // 构建参数字符串
            String authorized = AuthorizedUtils.generateAuthorizationCode("report");
            String startDate = getDate();

            System.out.println(startDate);

            String encodedStartDate = URLEncoder.encode(startDate, StandardCharsets.UTF_8.toString());

            // 构建完整的API URL
            String fullUrl = API_URL + "?Authorized=" + authorized +
                    "&Regional=" + REGIONAL +
                    "&id=" + MATTRESSID +
                    "&startDate=" + encodedStartDate;
            // 发起HTTP GET请求
            HttpGet httpGet = new HttpGet(fullUrl);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();


            if (entity != null) {
                String responseString = EntityUtils.toString(entity);
                // 处理响应数据，可以根据需要解析JSON等操作

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(responseString);

                JsonNode dataNode = null;
                JsonNode innerDataNode = null;
                System.out.println(responseString);
                if (rootNode.has("data")) {
                    dataNode = rootNode.get("data");
                    System.out.println(dataNode);
                    if (dataNode != null && dataNode.isArray()) {
                        System.out.println(dataNode.get(0));
                        innerDataNode = dataNode.get(0);


                        MattressDetail info = new MattressDetail();

                        info.setDetailId(innerDataNode.path("detailId").asInt());
                        info.setMattressId(MATTRESSID);
                        info.setDate(startDate.toString());
                        info.setAwake(innerDataNode.path("Awake").asText());
                        info.setInSleep(innerDataNode.path("InSleep").asText());
                        info.setShallowSleep(innerDataNode.path("ShallowSleep").asText());
                        info.setDeepSleep(innerDataNode.path("DeepSleep").asText());
                        info.setTotalSleep(innerDataNode.path("TotalSleep").asText());
                        info.setNumberOfAlarms(innerDataNode.path("NumberOfAlarms").asText());
                        info.setNumberOfFreeBeds(innerDataNode.path("NumberOfFreeBeds").asText());
                        info.setRespirationAverage(innerDataNode.path("RespirationAverage").asText());
                        info.setRespirationMax(innerDataNode.path("RespirationMax").asText());
                        info.setRespirationMin(innerDataNode.path("RespirationMin").asText());
                        info.setHeartRateAverage(innerDataNode.path("HeartRateAverage").asText());
                        info.setHeartRateMax(innerDataNode.path("HeartRateMax").asText());
                        info.setHeartRateMin(innerDataNode.path("HeartRateMin").asText());
                        info.setGeneral(innerDataNode.path("General").asText());
                        info.setHeartRespirationRatio(innerDataNode.path("HeartRespirationRatio").asText());
                        info.setScores(innerDataNode.path("Scores").asText());
                        service.insert(info);


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

    private String getDate() {
        LocalDateTime currentDate = LocalDateTime.now().minusHours(1);

        // 自定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 格式化当前日期为字符串
        String formattedDate = currentDate.format(formatter);

        return formattedDate;
    }
}