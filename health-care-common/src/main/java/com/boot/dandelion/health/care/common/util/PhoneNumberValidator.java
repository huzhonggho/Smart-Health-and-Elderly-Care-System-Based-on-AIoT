package com.boot.dandelion.health.care.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator {

    private static final String PHONE_NUMBER_REGEX = "^1[3456789]\\d{9}$";
    private static final Pattern pattern = Pattern.compile(PHONE_NUMBER_REGEX);

    /**
     * 判断是否是有效的手机号
     *
     * @param phoneNumber 要验证的手机号
     * @return 如果是有效的手机号返回true，否则返回false
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
