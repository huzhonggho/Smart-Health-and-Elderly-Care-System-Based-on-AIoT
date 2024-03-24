package com.boot.dandelion.health.care.common.util;

import com.boot.dandelion.health.care.common.entity.StaticBasicInfo;
import com.boot.dandelion.health.care.common.entity.UserInfo;
import io.jsonwebtoken.Claims;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.UUID;

/**
 * @ClassName UserUtils
 * @Description
 * @Author shr
 * @Date 2022/07/19
 */
public class UserUtils {

    /**
     * @Description: 获取登录用户个人信息
     * @param: []
     * @return: com.polaris.dandelion.asset.common.entity.UserInfo
     * @author: shr
     * @date: 2022/7/19
     */
    public static UserInfo getLoginUserInfo(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        return (UserInfo) request.getSession().getAttribute(StaticBasicInfo.LOGIN_USER);
    }

    /**
     * 根据请求头携带的token，解析获取用户手机号
     * @param token
     * @return
     */
//    public static String getUserTel(String token){
//        Claims claims = null;
//        try {
//            claims = JwtUtil.parseJWT(token);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String tel = claims.getSubject();
//        return tel;
//    }

}
