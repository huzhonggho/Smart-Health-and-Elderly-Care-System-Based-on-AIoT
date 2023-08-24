package com.boot.dandelion.health.care.common.util;

import org.springframework.util.StringUtils;

import java.util.Base64;

/**
 * @ClassName Base64Utils
 * @Description
 * @Author shr
 * @Date 2022/07/19
 */
public class Base64Utils {

    public static String imgToBase64(byte[] source) {
        Base64.Encoder encoder = Base64.getEncoder();
        return "data:image/jpeg;base64," + encoder.encodeToString(source);
    }

    public static String imgToBase64(String fileType, byte[] source) {
        Base64.Encoder encoder = Base64.getEncoder();
        return "data:image/" + (StringUtils.isEmpty(fileType) ? "jpeg" : fileType) + ";base64," + encoder.encodeToString(source);
    }
}
