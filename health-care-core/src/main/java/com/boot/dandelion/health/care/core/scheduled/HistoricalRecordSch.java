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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component

public class HistoricalRecordSch {

    @Autowired
    private MattressHisService mattressHistoryService;
    private static final String API_URL = "http://www.sdhtjr.com:5859/vms/getparam.asp";
    private final String MATTRESSID = "B00681";
    private final String REGIONAL = "TestUnit";

    @Scheduled(fixedRate = 3600000) // 每小时执行一次
    public void callAPIAndProcessResponse() {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // 构建参数字符串
            String authorized = AuthorizedUtils.generateAuthorizationCode("history");
            String startDate = getPreviousWholeHour();

            System.out.println(startDate);

            int page = 1;

            System.out.println(getDate());

            Map<String, Object> params = new HashMap<>();
            params.put("mattressId", MATTRESSID);
            params.put("date", getDate());

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
                                        String[] time = itemNode.path("date").asText().split("-");


                                        mattressHistory.setRR(itemNode.path("RR").asInt());
                                        mattressHistory.setHR(itemNode.path("HR").asInt());
                                        mattressHistory.setWet(itemNode.path("wet").asText());
                                        mattressHistory.setState(itemNode.path("state").asText());
                                        mattressHistory.setTurn(itemNode.path("turn").asText());
                                        mattressHistory.setDate(getDate());
                                        mattressHistory.setAlam(itemNode.path("alam").asText());
                                        mattressHistory.setMattressId(MATTRESSID);
                                        mattressHistory.setDuration(itemNode.path("date").asText());
                                        mattressHistory.setStart(time[0]);
                                        mattressHistory.setEnd(time[1]);

                                        params.put("start", time[0]);
                                        params.put("end", time[1]);
                                        List<MattressHistory> startEndList = mattressHistoryService.selectByMattressIdDateStartAndEnd(params);
                                        List<MattressHistory> startList = mattressHistoryService.selectByMattressIdDateAndStart(params);

                                        //为了更新end节点增加的情况，
                                        if (startEndList.size() == 0) {
                                            if (startList.size() > 0) {
                                                MattressHistory lastMattress = startList.get(0);
                                                mattressHistory.setHistoryId(lastMattress.getHistoryId());
                                                mattressHistoryService.updateByHistoryId(mattressHistory);
                                            } else {
                                                mattressHistoryService.insert(mattressHistory);

                                            }
                                        }

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 减去一个小时
        LocalDateTime prevHourTime = zeroMinuteSecondTime.minusHours(1);
        String formattedDate = prevHourTime.format(formatter);

        return formattedDate;
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
