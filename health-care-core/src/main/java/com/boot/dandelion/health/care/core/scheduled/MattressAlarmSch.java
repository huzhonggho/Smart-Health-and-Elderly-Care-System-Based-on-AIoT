package com.boot.dandelion.health.care.core.scheduled;

import com.boot.dandelion.health.care.common.util.AuthorizedUtils;
import com.boot.dandelion.health.care.common.util.CloudPlatformClientUtil;
import com.boot.dandelion.health.care.core.service.MattressAlarmService;
import com.boot.dandelion.health.care.dao.entity.MattressAlarm;
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
import java.util.HashMap;
import java.util.Map;

@Component

public class MattressAlarmSch {
    @Autowired
    private MattressAlarmService service;
    private static final String API_URL = "http://www.sdhtjr.com:5859/vms/getparam.asp";
    private final String MATTRESSID = "B00681";
    private final String REGIONAL = "TestUnit";

    @Scheduled(fixedRate = 3600000) // 每小时执行一次
    public void fetchDataAndSaveToDatabase() {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // 构建参数字符串
            String authorized = AuthorizedUtils.generateAuthorizationCode("AlarmHistory");

            String encodedSearchDate = URLEncoder.encode(getPreviousDate(), StandardCharsets.UTF_8.toString());

            // 构建完整的API URL
            String fullUrl = API_URL + "?Authorized=" + authorized +
                    "&Regional=" + REGIONAL +
                    "&id=" + MATTRESSID +
                    "&searchDate=" + encodedSearchDate;

            // 发起HTTP GET请求
            HttpGet httpGet = new HttpGet(fullUrl);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            Map<String, Object> params = new HashMap<>();
            params.put("mattressId", MATTRESSID);
            params.put("date", getPreviousDate());

            if (entity != null) {
                String responseString = EntityUtils.toString(entity);
                // 处理响应数据，可以根据需要解析JSON等操作

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(responseString);

                System.out.println(responseString);
                if (rootNode.has("data")) {
                    JsonNode dataNode = rootNode.get("data");

                    for (JsonNode data : dataNode) {
                        System.out.println("data" + data);

                        if (data.isArray() && data.size() > 0) {
                            for (JsonNode innerData : data) {
                                if (innerData.has("data")) {
                                    JsonNode alarmArray = innerData.get("data");

                                    for (JsonNode alarm : alarmArray) {
                                        MattressAlarm mattressAlarm = new MattressAlarm();
                                        String[] splitStart = alarm.path("star").asText().split("\\s+");
                                        String[] splitEnd = alarm.path("end").asText().split("\\s+");
                                        params.put("start", splitStart[1]);
                                        params.put("end", splitEnd[1]);

                                        MattressAlarm select = service.selectByAllFields(params);

                                        mattressAlarm.setMattressId(MATTRESSID);
                                        mattressAlarm.setStart(splitStart[1]);
                                        mattressAlarm.setEnd(splitEnd[1]);
                                        mattressAlarm.setIntervals(alarm.get("interval").asText());
                                        mattressAlarm.setDate(getPreviousDate());
                                        mattressAlarm.setAla(alarm.get("ala").asText());
                                        if (select == null) {
                                            service.insert(mattressAlarm);
                                        }


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

    private String getPreviousDate() {
        LocalDateTime currentDate = LocalDateTime.now().minusHours(1);

        // 自定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 格式化当前日期为字符串
        String formattedDate = currentDate.format(formatter);

        return formattedDate;
    }
}
