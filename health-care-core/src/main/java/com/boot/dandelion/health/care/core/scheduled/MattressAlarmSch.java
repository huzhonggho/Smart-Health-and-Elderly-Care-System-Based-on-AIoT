package com.boot.dandelion.health.care.core.scheduled;

import com.boot.dandelion.health.care.common.util.CloudPlatformClientUtil;
import com.boot.dandelion.health.care.core.service.MattressAlarmService;
import com.boot.dandelion.health.care.dao.entity.MattressAlarm;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Component

public class MattressAlarmSch {
    @Autowired
    private MattressAlarmService service;
    private final String MATTRESSID = "B00681";
    private final String COMMAND = "g2";

//    @Scheduled(cron = "0 10 17 * * ?")
    public void fetchDataAndSaveToDatabase() {
        try {
            LocalDate startDate = LocalDate.parse("2023-12-08");
            LocalDate endDate = LocalDate.parse("2023-12-17");
            System.out.println("MattressAlarmSch");
            while (!startDate.isAfter(endDate)) {
                // 使用工具类发送指令

                String command = "fa" + COMMAND + MATTRESSID + "|" + startDate + "|TestUser@T123456";
                String response = CloudPlatformClientUtil.sendCommand(command);

                // 处理返回数据
                String responseData = CloudPlatformClientUtil.handleResponse(response);
                System.out.println("数据" + responseData);
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(responseData);
                JsonNode firstElement = rootNode.get(0);

                LocalDate currentDate = LocalDate.now();
                String currentYear = String.valueOf(currentDate.getYear());
                if (firstElement.has("data")) {
                    JsonNode dataNode = firstElement.get("data");
                    if (dataNode.isArray() && dataNode.size() > 0) {
                        for (JsonNode historyNode : dataNode) {
                            MattressAlarm mattressAlarm = new MattressAlarm();

                            mattressAlarm.setMattressId(MATTRESSID);
                            mattressAlarm.setStart(historyNode.path("star").asText().substring(historyNode.path("star").asText().indexOf(" ") + 1));
                            mattressAlarm.setEnd(historyNode.path("end").asText().substring(historyNode.path("end").asText().indexOf(" ") + 1));
                            mattressAlarm.setIntervals(historyNode.path("interval").asText());
                            mattressAlarm.setAla(historyNode.path("ala").asText());
                            mattressAlarm.setDate("2023-"+historyNode.path("star").asText().split("\\s+")[0]);
                            service.insert(mattressAlarm);
                            System.out.println(mattressAlarm);
                        }

                        // 数据不为空，保持日期不变，继续请求下一页
                        System.out.println(startDate);
                    }
                } else {
                    // 如果没有data节点，处理错误或者其他情况
                    System.err.println("Invalid response format: " + responseData);
                    break;
                }
                startDate = startDate.plusDays(1);
            }
            System.out.println("end");

        } catch (Exception e) {
            System.err.println("Error fetching or saving data: " + e.getMessage());
        }
    }

}
