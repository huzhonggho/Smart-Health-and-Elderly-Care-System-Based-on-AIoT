package com.boot.dandelion.health.care.core.scheduled;

import com.boot.dandelion.health.care.common.util.CloudPlatformClientUtil;
import com.boot.dandelion.health.care.core.service.TurnBodyService;
import com.boot.dandelion.health.care.dao.entity.TurnBody;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component

public class TurnBodySch {
    @Autowired
    private TurnBodyService turnBodyService;
    private final String MATTRESSID = "B00681";
    private final String COMMAND = "a8";
    private final String CID = "a8323";  //社区id

//    @Scheduled(cron = "20 08 17 * * ?")
    public void fetchDataAndSaveToDatabase() {
        try {
            LocalDate startDate = LocalDate.parse("2023-12-10");
            LocalDate endDate = LocalDate.parse("2023-12-17");
            int page = 1;
            System.out.println("start");
            while (!startDate.isAfter(endDate)) {
                // 使用工具类发送指令

                String command = "fa" + COMMAND + "323|" + MATTRESSID + "|" + startDate + "|00:00:01|" + startDate + "23:59:59" + "|TestUser@T123456";
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
                        for (JsonNode historyNode : dataNode) {
                            TurnBody turnBody = new TurnBody();

                            turnBody.setDatadate(historyNode.path("DATA_DATE").asText());
                            turnBody.setValuee(historyNode.path("Value").asText());
                            turnBody.setMattressId(MATTRESSID);
//                            turnBodyService.insert(turnBody);
                            System.out.println(turnBody);
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
