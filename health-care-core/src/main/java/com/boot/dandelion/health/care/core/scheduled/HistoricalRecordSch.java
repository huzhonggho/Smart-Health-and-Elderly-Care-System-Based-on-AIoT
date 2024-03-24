package com.boot.dandelion.health.care.core.scheduled;

import com.boot.dandelion.health.care.common.util.AuthorizedUtils;
import com.boot.dandelion.health.care.core.service.MattressHisService;
import com.boot.dandelion.health.care.dao.entity.MattressHistory;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component

public class HistoricalRecordSch {

    @Autowired
    private MattressHisService mattressHistoryService;
    private static final String API_URL = "http://www.sdhtjr.com:5859/vms/getparam.asp";
    private final String MATTRESSID = "B00681";
    private final String REGIONAL = "TestUnit";

    @Scheduled(cron = "1 0 * * * *") // 每小时执行一次                这段代码的意义
    public void callAPIAndProcessResponse() {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // 构建参数字符串
            String authorized = AuthorizedUtils.generateAuthorizationCode("history");
            String startDate = getPreviousWholeHour();

//            String startDate = "2024-03-24 09:00:00";
            System.out.println(startDate);
            int page = 1;
            while (true) {
                String encodedStartDate = URLEncoder.encode(startDate, StandardCharsets.UTF_8.toString());

                // 构建完整的API URL
                String fullUrl = API_URL + "?Authorized=" + authorized +
                        "&Regional=" + REGIONAL +
                        "&id=" + MATTRESSID +
                        "&startDate=" + encodedStartDate +
                        "&page=" + page;

                // 发起HTTP GET请求
                HttpGet httpGet = new HttpGet(fullUrl);
                CloseableHttpResponse response = httpClient.execute(httpGet);
                HttpEntity entity = response.getEntity();

                if (entity != null) {
                    String responseString = EntityUtils.toString(entity);
                    System.out.println("API Response: " + responseString);
                    // 处理响应数据，可以根据需要解析JSON等操作

                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode rootNode = objectMapper.readTree(responseString);

                    JsonNode dataNode = null;
                    JsonNode innerDataNode = null;
                    if (rootNode.has("data")) {
                        dataNode = rootNode.get("data");
                        System.out.println(dataNode);
                        if (dataNode != null && dataNode.isArray()) {
                            for (JsonNode pageNode : dataNode) {
                                innerDataNode = pageNode.get("data");
                                if (innerDataNode != null && innerDataNode.isArray() && innerDataNode.size() > 0) {
                                    for (JsonNode itemNode : innerDataNode) {
                                        MattressHistory mattressHistory = new MattressHistory();

                                        mattressHistory.setRR(itemNode.path("RR").asInt());
                                        mattressHistory.setHR(itemNode.path("HR").asInt());
                                        mattressHistory.setWet(itemNode.path("wet").asText());
                                        mattressHistory.setState(itemNode.path("state").asText());
                                        mattressHistory.setTurn(itemNode.path("turn").asText());
                                        mattressHistory.setDate(getDate());
                                        mattressHistory.setAlam(itemNode.path("alam").asText());
                                        mattressHistory.setMattressId(MATTRESSID);
                                        mattressHistory.setDuration(itemNode.path("date").asText());
                                        mattressHistoryService.insert(mattressHistory);
                                    }
                                }
                            }
                        }
                    }

                    if (innerDataNode.isArray() && innerDataNode.size() != 0) {
                        page++;
                    } else {
                        break;
                    }
                }

                Thread.sleep(1000); // 休眠1秒，避免频繁请求
            }

            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getPreviousWholeHour() {
        LocalDateTime currentTime = LocalDateTime.now();

        int currentMinute = currentTime.getMinute();
        int currentSecond = currentTime.getSecond();
        int currentNano = currentTime.getNano();

        // 将分钟和秒数设置为零，保留小时和日期部分
        LocalDateTime zeroMinuteSecondTime = currentTime.minusMinutes(currentMinute).minusSeconds(currentSecond).minusNanos(currentNano);

        // 减去一个小时
        LocalDateTime prevHourTime = zeroMinuteSecondTime.minusHours(1);

        return String.valueOf(prevHourTime);
    }

    private String getDate() {
        LocalDate currentDate = LocalDate.now();

        // 自定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 格式化当前日期为字符串
        String formattedDate = currentDate.format(formatter);

        return formattedDate;
    }
}
