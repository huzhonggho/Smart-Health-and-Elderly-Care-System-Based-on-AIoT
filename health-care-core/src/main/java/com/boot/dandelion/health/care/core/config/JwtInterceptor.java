package com.boot.dandelion.health.care.core.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.boot.dandelion.health.care.common.util.JwtUtil;
import com.boot.dandelion.health.care.core.service.UserService;
import com.boot.dandelion.health.care.core.service.impl.UserServiceImpl;
import com.boot.dandelion.health.care.dao.entity.User;
import com.boot.dandelion.health.care.dao.entity.UserDevice;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    //    注入service用于验证对象
//    @Autowired
//    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在请求头中获取token
        String token = request.getHeader("token");

        Map<String, Object> map = new HashMap<>();
        try {
            JwtUtil.verify(token);
            return true;
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("msg", "签名不一致");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            map.put("msg", "令牌过期");
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            map.put("msg", "算法不匹配");
        } catch (InvalidClaimException e) {
            e.printStackTrace();
            map.put("msg", "失效的payload");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "token无效");
        }

        map.put("code", "401");

        //响应到前台: 将map转为json
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;

//        //若为空则抛出异常
//        if (token == null) {
//            throw new LoginException("token为空");
//        }
//
//        //获取token中的username
//        Integer userId;
//        try {
//            DecodedJWT tokenInfo = JwtUtil.getTokenInfo(token);
//
//            userId = Integer.parseInt(tokenInfo.getClaim("userId").asString());
//        } catch (Exception e) {
//            throw new LoginException("该token不正确");
//        }
//
//        //判断该id的用户是否存在
//        User user = userService.selectUserByUserId(userId);
//        if (user == null) {
//            throw new LoginException("该用户已经不存在");
//        }
//        //核心：进行验证
//
////        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
//        try {
////            jwtVerifier.verify(token);
//            JwtUtil.verify(token);
//            System.out.println(JwtUtil.verify(token));
//        } catch (Exception e) {
//            throw new LoginException("验证token时出错");
//        }
//
//        //没问题返回true
//        return true;
    }

}