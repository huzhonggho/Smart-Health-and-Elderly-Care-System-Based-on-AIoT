package com.boot.dandelion.health.care.core.scheduled;

import com.boot.dandelion.health.care.common.util.AuthorizedUtils;
import com.boot.dandelion.health.care.common.util.CloudPlatformClientUtil;
import com.boot.dandelion.health.care.core.service.MattressTurnBodyService;
import com.boot.dandelion.health.care.dao.entity.MattressDetail;
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
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component

public class MattressTurnBodySch {
    @Autowired
    private MattressTurnBodyService service;
    private static final String API_URL = "http://www.sdhtjr.com:5859/vms/getparam.asp";
    private final String MATTRESSID = "B00681";
    private final String REGIONAL = "TestUnit";

    @Scheduled(fixedRate = 3600000) // 每小时执行一次
    public void fetchDataAndSaveToDatabase() {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // 构建参数字符串
            String authorized = AuthorizedUtils.generateAuthorizationCode("turn");

            String encodedStartDate = URLEncoder.encode(getPreviousDate(), StandardCharsets.UTF_8.toString());
            String encodedEndDate = URLEncoder.encode(getDate(), StandardCharsets.UTF_8.toString());

            // 构建完整的API URL
            String fullUrl = API_URL + "?Authorized=" + authorized +
                    "&Regional=" + REGIONAL +
                    "&id=" + MATTRESSID +
                    "&startDate=" + encodedStartDate +
                    "&endDate=" + encodedEndDate;

            // 发起HTTP GET请求
            HttpGet httpGet = new HttpGet(fullUrl);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            Map<String, Object> params = new HashMap<>();
            params.put("mattressId", MATTRESSID);
//            params.put("date", "2024-04-04");
            params.put("date", getPreviousDate().split("\\s+")[0]);

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
                                if (innerData.has("data")) {
                                    JsonNode statusArray = innerData.get("data");

                                    for (JsonNode status : statusArray) {
                                        String dataDate = status.get("DATA_DATE").asText();
                                        String value = status.get("Value").asText();
                                        String[] splits = dataDate.split("\\s+");

                                        MattressTurnBody mattressTurnBody = new MattressTurnBody();
                                        MattressTurnBody foreHead = service.selectMaxDataTimeByMattressIdAndDate(params);
                                        if (foreHead!=null){
                                            String lastDataTime = foreHead.getDate() + " " + foreHead.getDataTime();
                                            if (lastDataTime != null && !value.equals("on")) {
                                                // 计算时间差值
                                                int duration = calculateDuration(lastDataTime, dataDate);
                                                mattressTurnBody.setDuration(String.valueOf(duration));
                                            }
                                        }

                                        mattressTurnBody.setDate(splits[0]);
                                        mattressTurnBody.setDataTime(splits[1]);
                                        mattressTurnBody.setDataValue(value);
                                        mattressTurnBody.setMattressId(MATTRESSID);

                                        service.insert(mattressTurnBody);
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

    private String getDate() {
        LocalDateTime currentDate = LocalDateTime.now();

        // 自定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 格式化当前日期为字符串
        String formattedDate = currentDate.format(formatter);

        return formattedDate;
    }

    private String getPreviousDate() {
        LocalDateTime currentDate = LocalDateTime.now().minusHours(1);

        // 自定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 格式化当前日期为字符串
        String formattedDate = currentDate.format(formatter);

        return formattedDate;
    }

    // 计算时间差值的方法
    private int calculateDuration(String lastDataTime, String currentDataTime) {
        // 假设时间格式为"yyyy-MM-dd HH:mm:ss"
        LocalDateTime lastDateTime = LocalDateTime.parse(lastDataTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime currentDateTime = LocalDateTime.parse(currentDataTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // 计算时间差值（单位：秒）
        return (int) Duration.between(lastDateTime, currentDateTime).getSeconds();
    }
}
