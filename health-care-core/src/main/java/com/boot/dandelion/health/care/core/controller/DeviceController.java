package com.boot.dandelion.health.care.core.controller;

import com.boot.dandelion.health.care.common.entity.Device;
import com.boot.dandelion.health.care.common.entity.DeviceTransferM;
import com.boot.dandelion.health.care.common.entity.DeviceTransferU;
import com.boot.dandelion.health.care.common.entity.WearDeviceMap;
import com.boot.dandelion.health.care.common.enums.ResultCodeEnum;
import com.boot.dandelion.health.care.common.util.DateUtils;
import com.boot.dandelion.health.care.common.util.UserUtils;
import com.boot.dandelion.health.care.common.wrapper.ResponseWrapper;
import com.boot.dandelion.health.care.core.service.*;
import com.boot.dandelion.health.care.dao.entity.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Slf4j

@PropertySources(value = {@PropertySource(value = {"classpath:important.properties", "classpath:application.properties"},
        encoding = "utf-8")})
@RestController
@Validated
@RequestMapping("/device")
public class DeviceController {

    @Resource
    private DeviceMapService deviceMapService;
    @Resource
    private MattressInfoService mattressInfoService;
    @Resource
    private MemberService memberService;
    @Resource
    private UserFamilyService userFamilyService;
    @Resource
    private WatchInfoService watchInfoService;

    @Transactional
    @ApiOperation("床垫添加")
    @PostMapping("addMattress")
    public ResponseWrapper<Object> addMattress(@RequestBody MattressInfo mattressInfo) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            int userId = UserUtils.getUserId();

            if (mattressInfo.getMattressId() == null || mattressInfo.getMattressId().isEmpty()) {
                responseWrapper.setMsg("床垫id不能为空");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }

            Map<String, Object> params = new HashMap<>();
            params.put("deviceType", "MATTRESS");
            params.put("deviceId", mattressInfo.getMattressId());

            DeviceMap delectDevice = deviceMapService.selectByDeviceTypeAndId(params);

            if (delectDevice != null) {
                responseWrapper.setMsg("床垫已注册");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }

            UserFamily userFamily = userFamilyService.selectByUserId(String.valueOf(userId));
            DeviceMap deviceMap = new DeviceMap();
            deviceMap.setUserId(String.valueOf(userId));
            deviceMap.setFamilyId(userFamily.getFamilyId());
            deviceMap.setDeviceId(mattressInfo.getMattressId());
            deviceMap.setCreateTime(DateUtils.getNowDate());
            deviceMap.setDeviceType("MATTRESS");
            deviceMap.setCreateUser(String.valueOf(userId));

            int dResult = deviceMapService.insert(deviceMap);
            int mResult = mattressInfoService.insertMattressInfo(mattressInfo);

            if (dResult > 0 && mResult > 0) {
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
                responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
            } else {
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
            }

        } catch (Exception e) {
            log.error("床垫添加失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ExceptionUtils.getStackTrace(e));
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
        }
        return responseWrapper;
    }

    @Transactional
    @ApiOperation("手表添加")
    @PostMapping("addWatch")
    public ResponseWrapper<Object> addWatch(@RequestBody WatchInfo watchInfo) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            int userId = UserUtils.getUserId();

            if (watchInfo.getSn() == null || watchInfo.getSn().isEmpty()) {
                responseWrapper.setMsg("手表sn不能为空");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }

            Map<String, Object> params = new HashMap<>();
            params.put("deviceType", "WATCH");
            params.put("deviceId", watchInfo.getSn());

            DeviceMap delectDevice = deviceMapService.selectByDeviceTypeAndId(params);

            if (delectDevice != null) {
                responseWrapper.setMsg("手表已注册");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }
            //获取familyId
            UserFamily userFamily = userFamilyService.selectByUserId(String.valueOf(userId));
            DeviceMap deviceMap = new DeviceMap();
            deviceMap.setUserId(String.valueOf(userId));
            deviceMap.setFamilyId(userFamily.getFamilyId());
            deviceMap.setDeviceId(watchInfo.getSn());
            deviceMap.setCreateTime(DateUtils.getNowDate());
            deviceMap.setDeviceType("WATCH");
            deviceMap.setCreateUser(String.valueOf(userId));

            watchInfo.setCreateTime(DateUtils.getNowDate());

            int mResult = watchInfoService.insert(watchInfo);
            int dResult = deviceMapService.insert(deviceMap);

            if (dResult > 0 && mResult > 0) {
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
                responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
            } else {
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
            }

        } catch (Exception e) {
            log.error("手表添加失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ExceptionUtils.getStackTrace(e));
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
        }
        return responseWrapper;
    }

    @ApiOperation("查询设备")
    @GetMapping("show")
    public ResponseWrapper<Object> show() {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {

            Integer userId = UserUtils.getUserId();
            Integer memberId = UserUtils.getMemberId();
            List<DeviceMap> deviceMapList = null;

            if (userId != null) {
                deviceMapList = deviceMapService.selectByUserId(String.valueOf(userId));
            } else if (memberId != null) {
                Member member = memberService.selectMemberByMemberId(memberId);
                if (member != null) {
                    deviceMapList = deviceMapService.selectByFamilyId(member.getFamilyId());
                }
            }

            List<Device> deviceList = new ArrayList<>();
            for (DeviceMap info :
                    deviceMapList) {
                Device device = new Device();
                if (info.getDeviceType().equals("MATTRESS")) {
                    MattressInfo mattressInfo = mattressInfoService.selectByMattressId(info.getDeviceId());
                    device.setDeviceType(info.getDeviceType());
                    device.setDeviceId(info.getDeviceId());
                    device.setDeviceName(mattressInfo.getName());
                } else if (info.getDeviceType().equals("WATCH")) {
                    WatchInfo watchInfo = watchInfoService.selectBySN(info.getDeviceId());
                    device.setDeviceType(info.getDeviceType());
                    device.setDeviceId(info.getDeviceId());
                    device.setDeviceName(watchInfo.getName());
                }
                deviceList.add(device);
            }
            responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
            responseWrapper.setData(deviceList);

        } catch (Exception e) {
            log.error("查询设备失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ExceptionUtils.getStackTrace(e));
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
        }
        return responseWrapper;
    }

    @ApiOperation("一个家庭设备佩戴查询")
    @GetMapping("wear")
    public ResponseWrapper<Object> wear() {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            Integer userId = UserUtils.getUserId();
            Integer memberId = UserUtils.getMemberId();
            String familyId = "";

            if (userId != null) {
                familyId = userFamilyService.selectByUserId(userId.toString()).getFamilyId();
            } else if (memberId != null) {
                Member member = memberService.selectMemberByMemberId(memberId);
                if (member != null) {
                    familyId = memberService.selectMemberByMemberId(memberId).getFamilyId();
                }
            }
            List<DeviceMap> deviceMapList = deviceMapService.selectByFamilyId(familyId);
            List<WearDeviceMap> deviceList = new ArrayList<>();
            for (DeviceMap info :
                    deviceMapList) {
                WearDeviceMap device = new WearDeviceMap();
                device.setDeviceId(info.getDeviceId());
                device.setMemberId(info.getMemberId());
                device.setDeviceType(info.getDeviceType());
                if (info.getMemberId() == null || info.getMemberId().equals("")) {
                    device.setUserId(info.getUserId());
                }
                deviceList.add(device);
            }
            responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
            responseWrapper.setData(deviceList);

        } catch (Exception e) {
            log.error("设备佩戴查询：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ExceptionUtils.getStackTrace(e));
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
        }
        return responseWrapper;
    }

    @ApiOperation("user设备转移")
    @PostMapping("transferU")
    public ResponseWrapper<Object> transferU(@RequestBody DeviceTransferU deviceTransferU) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            String userId = deviceTransferU.getUserId();

            String deviceId = deviceTransferU.getDeviceId();
            String memberId = deviceTransferU.getMemberId();
            String deviceType = deviceTransferU.getDeviceType();
            String familyId = userFamilyService.selectByUserId(userId).getFamilyId();

            if (userId == null || userId.isEmpty()) {
                responseWrapper.setMsg("userId不能为空");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }
            if (memberId == null || memberId.isEmpty()) {
                responseWrapper.setMsg("memberId不能为空");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }
            if (deviceId == null || deviceId.isEmpty()) {
                responseWrapper.setMsg("deviceId不能为空");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }
            if (deviceType == null || deviceType.isEmpty()) {
                responseWrapper.setMsg("deviceType不能为空");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }
            Member member = memberService.selectMemberByMemberId(Integer.parseInt(memberId));
            if (member == null) {
                responseWrapper.setMsg("成员不存在");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }
            if (!member.getFamilyId().equals(familyId)) {
                responseWrapper.setMsg("非家庭成员");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }


            Map<String, Object> params = new HashMap<>();
            params.put("deviceType", deviceTransferU.getDeviceType());
            params.put("deviceId", deviceTransferU.getDeviceId());

            DeviceMap deviceMap = deviceMapService.selectByDeviceTypeAndId(params);
            if (deviceMap == null) {
                responseWrapper.setMsg("不存在该设备");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }
            if (!deviceMap.getUserId().equals(userId)) {
                responseWrapper.setMsg("user不是设备的持有者");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }

            deviceMap.setMemberId(memberId);
            deviceMap.setUpdateTime(DateUtils.getNowDate());
            deviceMap.setUpdateUser("USERID:" + userId);

            int dResult = deviceMapService.updateByDeviceId(deviceMap);

            if (dResult > 0) {
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
                responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
            } else {
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
            }
        } catch (Exception e) {
            log.error("user设备转移：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ExceptionUtils.getStackTrace(e));
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
        }
        return responseWrapper;
    }

    @ApiOperation("member设备转移")
    @PostMapping("transferM")
    public ResponseWrapper<Object> transferU(@RequestBody DeviceTransferM deviceTransferM) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            String from = deviceTransferM.getMemberIdFrom();

            String deviceId = deviceTransferM.getDeviceId();

            String to = deviceTransferM.getMemberIdTo();

            Member memberFrom = memberService.selectMemberByMemberId(Integer.parseInt(from));

            String familyId = memberFrom.getFamilyId();
            String deviceType = deviceTransferM.getDeviceType();
            if (memberFrom == null) {
                responseWrapper.setMsg("成员不存在");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }
            if (from == null || from.isEmpty()) {
                responseWrapper.setMsg("from不能为空");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }
            if (deviceId == null || deviceId.isEmpty()) {
                responseWrapper.setMsg("deviceId不能为空");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }
            if (to == null || to.isEmpty()) {
                responseWrapper.setMsg("to不能为空");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }
            if (deviceType == null || deviceType.isEmpty()) {
                responseWrapper.setMsg("deviceType不能为空");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }
            Member member = memberService.selectMemberByMemberId(Integer.parseInt(to));
            if (member == null) {
                responseWrapper.setMsg("成员不存在");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }
            if (!member.getFamilyId().equals(familyId)) {
                responseWrapper.setMsg("非家庭成员");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }
            Map<String, Object> params = new HashMap<>();
            params.put("deviceType", deviceType);
            params.put("deviceId", deviceId);
            DeviceMap deviceMap = deviceMapService.selectByDeviceTypeAndId(params);
            if (deviceMap == null) {
                responseWrapper.setMsg("不存在该设备");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }

            if (!deviceMap.getMemberId().equals(from)) {
                responseWrapper.setMsg("member不是设备的持有者");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }

            deviceMap.setMemberId(to);
            deviceMap.setUpdateTime(DateUtils.getNowDate());
            deviceMap.setUpdateUser("Member:" + to);

            int dResult = deviceMapService.updateByDeviceId(deviceMap);

            if (dResult > 0) {
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
                responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
            } else {
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
            }

            responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
        } catch (Exception e) {
            log.error("member设备转移：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ExceptionUtils.getStackTrace(e));
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
        }
        return responseWrapper;
    }
}
