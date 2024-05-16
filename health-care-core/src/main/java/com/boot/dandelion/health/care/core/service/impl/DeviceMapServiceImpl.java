package com.boot.dandelion.health.care.core.service.impl;

import com.boot.dandelion.health.care.common.util.AuthorizedUtils;
import com.boot.dandelion.health.care.core.service.DeviceMapService;
import com.boot.dandelion.health.care.core.service.MattressInfoService;
import com.boot.dandelion.health.care.dao.entity.DeviceMap;
import com.boot.dandelion.health.care.dao.entity.MattressInfo;
import com.boot.dandelion.health.care.dao.mapper.DeviceMapMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class DeviceMapServiceImpl implements DeviceMapService {
    @Resource
    private DeviceMapMapper deviceMapMapper;
    @Resource
    private MattressInfoService mattressInfoService;
    private static final String API_URL = "http://www.sdhtjr.com:5859/vms/getparam.asp";
    private final String REGIONAL = "TestUnit";

    @Override
    public int insert(DeviceMap deviceMap) {
        return deviceMapMapper.insert(deviceMap);
    }

    @Override
    public int insertMattress(MattressInfo mattressInfo) {

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // 构建参数字符串
            String authorized = AuthorizedUtils.generateAuthorizationCode("info");
            String info = String.format("[{\"Community\":\"%s\",\"ID\":\"%s\",\"Name\":\"%s\",\"Address\":\"%s\",\"Sex\":\"%s\",\"MinResp\":\"%s\",\"MaxResp\":\"%s\",\"MinHR\":\"%s\",\"MaxHR\":\"%s\",\"flage1\":\"%s\",\"TimeStar1\":\"%s\",\"TimeEnd1\":\"%s\",\"OffBed1\":\"%s\",\"Mov1\":\"%s\",\"flage2\":\"%s\",\"TimeStar2\":\"%s\",\"TimeEnd2\":\"%s\",\"OffBed2\":\"%s\",\"Mov2\":\"%s\",\"flage3\":\"%s\",\"TimeStar3\":\"%s\",\"TimeEnd3\":\"%s\",\"OffBed3\":\"%s\",\"Mov3\":\"%s\",\"flage4\":\"%s\",\"TimeStar4\":\"%s\",\"TimeEnd4\":\"%s\",\"OffBed4\":\"%s\",\"Mov4\":\"%s\",\"flage5\":\"%s\",\"TimeStar5\":\"%s\",\"TimeEnd5\":\"%s\",\"OffBed5\":\"%s\",\"Mov5\":\"%s\",\"Age\":\"%s\",\"TrunTime\":\"%s\",\"Turn\":\"%s\"}]",
                    mattressInfo.getCommunity(), mattressInfo.getMattressId(), mattressInfo.getName(), mattressInfo.getAddress(), mattressInfo.getSex(), mattressInfo.getMinResp(), mattressInfo.getMaxResp(), mattressInfo.getMinHR(), mattressInfo.getMaxHR(), mattressInfo.getFlage1(), mattressInfo.getTimeStar1(), mattressInfo.getTimeEnd1(), mattressInfo.getOffBed1(), mattressInfo.getMov1(), mattressInfo.getFlage2(), mattressInfo.getTimeStar2(), mattressInfo.getTimeEnd2(), mattressInfo.getOffBed2(), mattressInfo.getMov2(), mattressInfo.getFlage3(), mattressInfo.getTimeStar3(), mattressInfo.getTimeEnd3(), mattressInfo.getOffBed3(), mattressInfo.getMov3(), mattressInfo.getFlage4(), mattressInfo.getTimeStar4(), mattressInfo.getTimeEnd4(), mattressInfo.getOffBed4(), mattressInfo.getMov4(), mattressInfo.getFlage5(), mattressInfo.getTimeStar5(), mattressInfo.getTimeEnd5(), mattressInfo.getOffBed5(), mattressInfo.getMov5(), mattressInfo.getAge(), mattressInfo.getTrunTime(), mattressInfo.getTurn());
            // 构建完整的API URL
            String fullUrl = API_URL + "?Authorized=" + authorized +
                    "&Regional=" + REGIONAL +
                    "&Info=" + info;

            // 发起HTTP GET请求
            HttpGet httpGet = new HttpGet(fullUrl);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String responseString = EntityUtils.toString(entity);
                // 处理响应数据，可以根据需要解析JSON等操作

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(responseString);
                System.out.println(rootNode);
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 1;
    }

    @Override
    public DeviceMap selectByDeviceTypeAndId(Map<String, Object> params) {
        return deviceMapMapper.selectByDeviceTypeAndId(params);
    }

    @Override
    public List<DeviceMap> selectByFamilyId(String familyId) {
        return deviceMapMapper.selectByFamilyId(familyId);
    }

    @Override
    public DeviceMap selectByDeviceId(String deviceId) {
        return deviceMapMapper.selectByDeviceId(deviceId);
    }


    @Override
    public List<DeviceMap> selectByUserId(String userId) {

        return deviceMapMapper.selectByUserId(userId);
    }

    @Override
    public int updateByDeviceId(DeviceMap deviceMap) {
        return deviceMapMapper.updateByDeviceId(deviceMap);
    }
}
