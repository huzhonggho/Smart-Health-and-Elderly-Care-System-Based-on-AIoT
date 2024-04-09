package com.boot.dandelion.health.care.core.scheduled;

import com.boot.dandelion.health.care.common.util.AuthorizedUtils;
import com.boot.dandelion.health.care.common.util.CloudPlatformClientUtil;
import com.boot.dandelion.health.care.core.service.MattressSleepService;
import com.boot.dandelion.health.care.dao.entity.MattressSleep;
import com.boot.dandelion.health.care.dao.entity.MattressTurnBody;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component

public class MattressSleepSch {
    @Autowired
    private MattressSleepService service;
    private static final String API_URL = "http://www.sdhtjr.com:5859/vms/getparam.asp";
    private final String MATTRESSID = "B00681";
    private final String REGIONAL = "TestUnit";

    @Scheduled(fixedRate = 3600000) // 每小时执行一次
    public void fetchDataAndSaveToDatabase() {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // 构建参数字符串
            String authorized = AuthorizedUtils.generateAuthorizationCode("sleep");

            String encodedStartDate = URLEncoder.encode(getDate(), StandardCharsets.UTF_8.toString());

            // 构建完整的API URL
            String fullUrl = API_URL + "?Authorized=" + authorized +
                    "&Regional=" + REGIONAL +
                    "&id=" + MATTRESSID +
                    "&startDate=" + encodedStartDate;

            // 发起HTTP GET请求
            HttpGet httpGet = new HttpGet(fullUrl);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            Map<String, Object> params = new HashMap<>();
            params.put("mattressId", MATTRESSID);

            if (entity != null) {
                String responseString = EntityUtils.toString(entity);
                // 处理响应数据，可以根据需要解析JSON等操作

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(responseString);
                System.out.println(responseString);
                if (rootNode.has("data")) {
                    JsonNode dataNode = rootNode.get("data");

                    for (JsonNode data : dataNode) {
                        MattressSleep mattressSleep = new MattressSleep();

                        String[] splitStart = data.path("stardate").asText().split("\\s+");
                        String[] splitEnd = data.path("enddate").asText().split("\\s+");
//                        params.put("date", splitStart[0]);
                        params.put("date", getDate());
                        params.put("startDate", formatTime(splitStart[1]));
                        params.put("endDate", formatTime(splitEnd[1]));

                        mattressSleep.setMattressId(MATTRESSID);
                        mattressSleep.setState(data.path("state").asText());
                        mattressSleep.setDate(getDate());
                        mattressSleep.setStartDate(formatTime(splitStart[1]));
                        mattressSleep.setEndDate(formatTime(splitEnd[1]));

                        MattressSleep select = service.selectByAllFields(params);
                        if (select == null) {
                            service.insert(mattressSleep);
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

    private String getDate() {
        LocalDateTime currentDate = LocalDateTime.now().minusHours(1);

        // 自定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 格式化当前日期为字符串
        String formattedDate = currentDate.format(formatter);

        return formattedDate;
    }

    // 辅助方法，用于格式化时间，补零小时数
    private String formatTime(String time) {
        String[] numSplit = time.split(":");
        int num = Integer.parseInt(numSplit[0]);
        if (num < 10) {
            return "0" + time;
        }
        return time;
    }
}
