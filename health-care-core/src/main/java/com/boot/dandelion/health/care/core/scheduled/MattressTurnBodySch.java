package com.boot.dandelion.health.care.core.scheduled;

import com.boot.dandelion.health.care.common.util.CloudPlatformClientUtil;
import com.boot.dandelion.health.care.core.service.MattressTurnBodyService;
import com.boot.dandelion.health.care.dao.entity.MattressTurnBody;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component

public class MattressTurnBodySch {
    @Autowired
    private MattressTurnBodyService service;
    private final String MATTRESSID = "B00681";
    private final String COMMAND = "a8";
    private final String CID = "TestUnit";  //社区id

//    @Scheduled(cron = "0 29 13 * * ?")
    public void fetchDataAndSaveToDatabase() {
        try {
            LocalDate startDate = LocalDate.parse("2023-12-07");
            LocalDate endDate = LocalDate.parse("2023-12-17");
            System.out.println("MattressTurnBodySch");
            while (!startDate.isAfter(endDate)) {
                // 使用工具类发送指令

                String command = "fa" + COMMAND + CID + "|" + MATTRESSID + "|" + startDate + "|00:00:01|" + startDate + "23:59:59" + "|TestUser@T123456";
                String response = CloudPlatformClientUtil.sendCommand(command);

                // 处理返回数据
                String responseData = CloudPlatformClientUtil.handleResponse(response);
                System.out.println("数据" + responseData);
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(responseData);
                JsonNode firstElement = rootNode.get(0);

                if (firstElement.has("data")) {
                    JsonNode dataNode = firstElement.get("data");
                    if (dataNode.isArray() && dataNode.size() > 0) {
                        String lastDataTime = null;

                        for (JsonNode historyNode : dataNode) {
                            MattressTurnBody turnBody = new MattressTurnBody();
                            String getData = historyNode.path("DATA_DATE").asText();
                            String[] splits = getData.split("\\s+");
                            String getValue = historyNode.path("Value").asText();

                            // 计算duration并设置
                            if (lastDataTime != null&&!getValue.equals("on")) {
                                // 计算时间差值
                                int duration = calculateDuration(lastDataTime, getData);
                                turnBody.setDuration(String.valueOf(duration));
                            }

                            turnBody.setMattressId(MATTRESSID);
                            turnBody.setDate(splits[0]);
                            turnBody.setDataTime(splits[1]);
                            turnBody.setDataValue(getValue);
                            service.insert(turnBody);
                            System.out.println(turnBody);

                            lastDataTime = getData;

                        }

                        // 数据不为空，保持日期不变，继续请求下一页
                        System.out.println(startDate);
                    }
                }
                startDate = startDate.plusDays(1);
            }
            System.out.println("end");

        } catch (Exception e) {
            System.err.println("Error fetching or saving data: " + e.getMessage());
        }
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
