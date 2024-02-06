package com.boot.dandelion.health.care.core.scheduled;

import com.boot.dandelion.health.care.common.util.CloudPlatformClientUtil;
import com.boot.dandelion.health.care.core.service.MattressDetailService;
import com.boot.dandelion.health.care.dao.entity.MattressDetail;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component

public class MattressDetailSch {
    @Autowired
    private MattressDetailService service;
    private final String MATTRESSID = "B00681";
    private final String COMMAND = "ae";

//    @Scheduled(cron = "0 10 20 * * ?")
    public void fetchDataAndSaveToDatabase() {
        try {

            LocalDate startDate = LocalDate.parse("2023-12-07");
            LocalDate endDate = LocalDate.parse("2023-12-17");
            System.out.println("start:ae");
            while (!startDate.isAfter(endDate)) {
                // 使用工具类发送指令

                String command = "fa" + COMMAND + MATTRESSID + "|TestUnit|" + startDate + "|TestUser@T123456";
                System.out.println(command);
                String response = CloudPlatformClientUtil.sendCommand(command);
                System.out.println(response);
                // 处理返回数据
                String responseData = CloudPlatformClientUtil.handleResponse(response);

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(responseData);
                JsonNode firstElement = rootNode.get(0);
                System.out.println(responseData);
                if (firstElement.has("data")) {
                    JsonNode dataNode = firstElement.get("data");
                    MattressDetail info = new MattressDetail();

                    info.setDetailId(dataNode.path("detailId").asInt());
                    info.setMattressId(MATTRESSID);
                    info.setDate(startDate.toString());
                    info.setAwake(dataNode.path("Awake").asText());
                    info.setInSleep(dataNode.path("InSleep").asText());
                    info.setShallowSleep(dataNode.path("ShallowSleep").asText());
                    info.setDeepSleep(dataNode.path("DeepSleep").asText());
                    info.setTotalSleep(dataNode.path("TotalSleep").asText());
                    info.setNumberOfAlarms(dataNode.path("NumberOfAlarms").asText());
                    info.setNumberOfFreeBeds(dataNode.path("NumberOfFreeBeds").asText());
                    info.setRespirationAverage(dataNode.path("RespirationAverage").asText());
                    info.setRespirationMax(dataNode.path("RespirationMax").asText());
                    info.setRespirationMin(dataNode.path("RespirationMin").asText());
                    info.setHeartRateAverage(dataNode.path("HeartRateAverage").asText());
                    info.setHeartRateMax(dataNode.path("HeartRateMax").asText());
                    info.setHeartRateMin(dataNode.path("HeartRateMin").asText());
                    info.setGeneral(dataNode.path("General").asText());
                    info.setHeartRespirationRatio(dataNode.path("HeartRespirationRatio").asText());
                    info.setScores(dataNode.path("Scores").asText());
                    service.insert(info);
                    System.out.println(info);


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
