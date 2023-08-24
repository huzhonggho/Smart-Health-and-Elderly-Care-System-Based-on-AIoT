package com.boot.dandelion.health.care.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName ValidatorUtils
 * @Description 校验工具类
 * @Author shr
 * @Date 2022/07/14
 */
public class ValidatorUtils {

    /**
     * @Description: 手机号格式校验
     * @param: [phone]
     * @return: boolean
     * @author: shr
     * @date: 2022/7/14
     */
    public static boolean checkMobileFormat(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            return isMatch;
        }
    }
}
