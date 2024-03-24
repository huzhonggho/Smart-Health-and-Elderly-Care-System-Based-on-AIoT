package com.boot.dandelion.health.care.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * JWT工具类
 */
public class JwtUtil {


    private static final String SECRET = "!Q@W#E$R^Y&U";
    //token签发者
    private static final String ISSUSRE = "HZSTYGZPT";
    //token过期时间
    public static final Long EXPIRE_DATE = 1000*60L;

    /**
     *  生成token
     * @param map
     * @return
     */
    public static String createToken(Map<String,String> map){
        //创建过期时间
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.HOUR,1);  //一个小时过期

        //创建builder对象
        JWTCreator.Builder builder = JWT.create();
        //遍历map
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        String token = builder.withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    /**
     *  验证token
     *  验证过程中如果有异常，则抛出；
     *  如果没有,则返回 DecodedJWT 对象来获取用户信息;
     * @param token
     */
//    public static JsonResult verifyToken(String token, String username){
//        Algorithm algorithm = Algorithm.HMAC256(SECRET);
//        try {
//            JWTVerifier jwtVerifier = JWT.require(algorithm).withClaim("username", username).build();
//            jwtVerifier.verify(token);
//            return new JsonResult();
//        }catch (SignatureVerificationException e) {
//            //验证的签名不一致
//            throw new SignatureVerificationException(algorithm);
//        }catch (TokenExpiredException e){
//            throw new TokenExpiredException("token is alreadey expired");
//        }catch (AlgorithmMismatchException e){
//            throw new AlgorithmMismatchException("签名算法不匹配");
//        }catch (InvalidClaimException e){
//            throw new InvalidClaimException("校验的claims内容不匹配");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return new JsonResult().error("用户和jwt-token校验失败");
//    }

    /**
     *  验证token
     *  验证过程中如果有异常，则抛出；
     *  如果没有,则返回 DecodedJWT 对象来获取用户信息;
     * @param token
     */
    public static DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }

    /**
     * 解析token中的内容,先验证token合法性饭后返回token解码的对象
     */
    public static DecodedJWT getTokenInfo(String token){
        DecodedJWT verify =JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
        // 使用JWT的require，生成一个验证对象
        return verify;
    }


}