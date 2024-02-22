package com.boot.dandelion.health.care.core.scheduled;

import com.boot.dandelion.health.care.common.util.CloudPlatformClientUtil;
import com.boot.dandelion.health.care.core.service.MattressOutBedService;
import com.boot.dandelion.health.care.dao.entity.MattressOutBed;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Component

public class MattressOutBedSch {
    @Autowired
    private MattressOutBedService service;
    private final String MATTRESSID = "B00681";
    private final String COMMAND = "a9";
    private final String CID = "TestUnit";  //社区id
    private SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 定义输出日期的格式
    private SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

//    @Scheduled(cron = "0 35 18 * * ?")
    public void fetchDataAndSaveToDatabase() {
        try {
            LocalDate startDate = LocalDate.parse("2023-12-09");
            LocalDate endDate = LocalDate.parse("2023-12-17");
            System.out.println("MattressOutBedSch");
            while (!startDate.isAfter(endDate)) {
                // 使用工具类发送指令
                String command = "fa" + COMMAND + CID + "|" + MATTRESSID + "|" + startDate + "|00:00:01|" + "23:59:59" + "|TestUser@T123456";
                String response = CloudPlatformClientUtil.sendCommand(command);

                // 处理返回数据
                String responseData = CloudPlatformClientUtil.handleResponse(response);
                System.out.println("数据:" + responseData);
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(responseData);
                JsonNode firstElement = rootNode.get(0);

                if (firstElement.has("data")) {
                    JsonNode dataNode = firstElement.get("data");
                    if (dataNode.isArray() && dataNode.size() > 0) {
                        for (JsonNode historyNode : dataNode) {
                            MattressOutBed outBed = new MattressOutBed();

                            outBed.setStart(historyNode.path("start").asText().substring(historyNode.path("start").asText().indexOf(" ") + 1));
                            outBed.setEnd(historyNode.path("end").asText().substring(historyNode.path("end").asText().indexOf(" ") + 1));
                            outBed.setDate(outputFormat.format(inputFormat.parse(historyNode.path("start").asText())));
                            outBed.setMattressId(MATTRESSID);
                            service.insert(outBed);
                            System.out.println(outBed);
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
