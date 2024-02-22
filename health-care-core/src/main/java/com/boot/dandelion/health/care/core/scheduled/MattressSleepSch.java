package com.boot.dandelion.health.care.core.scheduled;

import com.boot.dandelion.health.care.common.util.CloudPlatformClientUtil;
import com.boot.dandelion.health.care.core.service.MattressSleepService;
import com.boot.dandelion.health.care.dao.entity.MattressSleep;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component

public class MattressSleepSch {
    @Autowired
    private MattressSleepService service;
    private final String MATTRESSID = "B00681";
    private final String COMMAND = "b8";
    // 定义输入日期的格式
    private SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    // 定义输出日期的格式
    private SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

//    @Scheduled(cron = "0 10 16 * * ?")
    public void fetchDataAndSaveToDatabase() {
        try {
            LocalDate startDate = LocalDate.parse("2023-12-07");
            LocalDate endDate = LocalDate.parse("2023-12-17");
            System.out.println("MattressSleepSch");
            while (!startDate.isAfter(endDate)) {
                // 使用工具类发送指令

                String command = "fa" + COMMAND + MATTRESSID + "|" + startDate + "|TestUser@T123456";
                String response = CloudPlatformClientUtil.sendCommand(command);

                // 处理返回数据
                String responseData = CloudPlatformClientUtil.handleResponse(response);
                System.out.println(responseData);
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(responseData);
                JsonNode firstElement = rootNode.get(0);

                if (firstElement.has("data")) {

                    JsonNode dataNode = firstElement.get("data");
                    if (dataNode.isArray() && dataNode.size() > 0) {

                        List<MattressSleep> mattressSleepList = new ArrayList<>();

                        for (JsonNode historyNode : dataNode) {
                            MattressSleep mattressSleep = new MattressSleep();
                            mattressSleep.setEndDate(historyNode.path("enddate").asText().substring(historyNode.path("enddate").asText().indexOf(" ") + 1));
                            mattressSleep.setStartDate(historyNode.path("stardate").asText().substring(historyNode.path("stardate").asText().indexOf(" ") + 1));
                            mattressSleep.setDate(outputFormat.format(inputFormat.parse(historyNode.path("stardate").asText())));
                            mattressSleep.setState(historyNode.path("state").asText());
                            mattressSleep.setMattressId(MATTRESSID);

                            mattressSleep.setStartDate(formatTime(mattressSleep.getStartDate()));
                            mattressSleep.setEndDate(formatTime(mattressSleep.getEndDate()));
                            mattressSleepList.add(mattressSleep);
                        }
                        // 将排序后的数据插入数据库
                        Collections.sort(mattressSleepList, new Comparator<MattressSleep>() {
                            @Override
                            public int compare(MattressSleep o1, MattressSleep o2) {
                                LocalDateTime time1 = LocalDateTime.parse(o1.getDate() +" "+o1.getStartDate(),formatter);
                                LocalDateTime time2 = LocalDateTime.parse(o2.getDate() +" "+o2.getStartDate(),formatter);
                                return time1.compareTo(time2);  //根据age进行升序排序
                            }
                        });

                        mattressSleepList.forEach(service::insert);
                        System.out.println("Data fetched and inserted for date: {}" + startDate);
                    }
                }
                startDate = startDate.plusDays(1);
            }
            System.out.println("end");

        } catch (Exception e) {
            System.err.println("Error fetching or saving data: " + e);
        }
    }

    // 辅助方法，用于格式化时间，补零小时数
    private String formatTime(String time) {
        String[] numSplit = time.split(":");
        int num = Integer.parseInt(numSplit[0]);
        if (num < 10) {
            return "0" + time;
        }
        return time;
    }
}
