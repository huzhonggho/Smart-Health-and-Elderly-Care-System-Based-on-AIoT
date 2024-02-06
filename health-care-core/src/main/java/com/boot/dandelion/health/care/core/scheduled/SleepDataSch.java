package com.boot.dandelion.health.care.core.scheduled;

import com.boot.dandelion.health.care.common.util.CloudPlatformClientUtil;
import com.boot.dandelion.health.care.core.service.SleepResultService;
import com.boot.dandelion.health.care.dao.entity.SleepEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component

public class SleepDataSch {
    @Autowired
    private SleepResultService sleepResultService;
    private final String MATTRESSID = "B00681";
    private final String COMMAND = "b8";

//    @Scheduled(cron = "0 20 14 * * ?")
    public void fetchDataAndSaveToDatabase() {
        try {
            LocalDate startDate = LocalDate.parse("2023-12-16");
            LocalDate endDate = LocalDate.parse("2023-12-17");
            int page = 1;
            System.out.println("start");
            while (!startDate.isAfter(endDate)) {
                // 使用工具类发送指令

                String command = "fa" + COMMAND + MATTRESSID + "|" + startDate  + "|TestUser@T123456";
                String response = CloudPlatformClientUtil.sendCommand(command);

                // 处理返回数据
                String responseData = CloudPlatformClientUtil.handleResponse(response);

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(responseData);
                JsonNode firstElement = rootNode.get(0);

                if (firstElement.has("data")) {
                    JsonNode dataNode = firstElement.get("data");
                    if (dataNode.isArray() && dataNode.size() > 0) {
                        for (JsonNode historyNode : dataNode) {
                            SleepEntity sleepEntity = new SleepEntity();
                            sleepEntity.setEndDate(historyNode.path("enddate").asText());
                            sleepEntity.setStartDate(historyNode.path("stardate").asText());
                            sleepEntity.setState(historyNode.path("state").asText());
                            sleepEntity.setIdDevice(MATTRESSID);
//                            sleepResultService.insert(sleepEntity);
                            System.out.println(sleepEntity);
                        }

                        // 数据不为空，保持日期不变，继续请求下一页
                        System.out.println(startDate + "||||||" + page);
                        page++;
                    } else {
                        // 数据为空，将page归1，日期加一天
                        page = 1;
                        startDate = startDate.plusDays(1);
                    }
                } else {
                    // 如果没有data节点，处理错误或者其他情况
                    System.err.println("Invalid response format: " + responseData);
                    break;
                }
            }
            System.out.println("end");

        } catch (Exception e) {
            System.err.println("Error fetching or saving data: " + e.getMessage());
        }
    }

}
