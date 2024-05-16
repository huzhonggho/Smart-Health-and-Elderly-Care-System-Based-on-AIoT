package com.boot.dandelion.health.care.core.controller;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.boot.dandelion.health.care.common.condition.UserCondition;
import com.boot.dandelion.health.care.common.entity.StaticBasicInfo;
import com.boot.dandelion.health.care.common.entity.UserInfo;
import com.boot.dandelion.health.care.common.enums.ResultCodeEnum;
import com.boot.dandelion.health.care.common.page.Pagination;
import com.boot.dandelion.health.care.common.util.DateUtils;
import com.boot.dandelion.health.care.common.util.JwtUtil;
import com.boot.dandelion.health.care.common.util.PhoneNumberValidator;
import com.boot.dandelion.health.care.common.util.RsaUtils;
import com.boot.dandelion.health.care.common.wrapper.ResponseWrapper;
import com.boot.dandelion.health.care.core.config.RsaProperties;
import com.boot.dandelion.health.care.core.service.MemberService;
import com.boot.dandelion.health.care.core.service.UserDeviceService;
import com.boot.dandelion.health.care.core.service.UserFamilyService;
import com.boot.dandelion.health.care.core.service.UserService;
import com.boot.dandelion.health.care.dao.entity.Member;
import com.boot.dandelion.health.care.dao.entity.User;
import com.boot.dandelion.health.care.dao.entity.UserDevice;
import com.boot.dandelion.health.care.dao.entity.UserFamily;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description 用户业务
 * @Author shr
 * @Date 2022/07/14
 */
@CrossOrigin(origins = "*")
@Slf4j
@PropertySources(value = {@PropertySource(value = {"classpath:important.properties", "classpath:application.properties"},
        encoding = "utf-8")})
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private MemberService memberService;
    @Resource
    private UserFamilyService userFamilyService;

    @PostMapping(value = "/login")
    public ResponseWrapper<Object> login(@RequestBody User loginAcc) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            User lawUser = userService.selectUserByTel(loginAcc.getTel());
            Member member = memberService.selectMemberByTel(loginAcc.getTel());

            if (lawUser == null && member == null) {
                responseWrapper.setMsg("手机号不存在");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }
            if (lawUser != null) {
                String password = lawUser.getPassword();
                String loginPassword = loginAcc.getPassword();
                loginPassword = DigestUtils.md5DigestAsHex(loginPassword.getBytes());

                if (!password.equals(loginPassword)) {
                    responseWrapper.setMsg(ResultCodeEnum.PASSWORD_ERROR.getName());
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                    return responseWrapper;

                }
                //密码正确
                Map<String, String> info = new HashMap<>();
                info.put("userId", lawUser.getUserId().toString());
                info.put("userName", lawUser.getUserName());
                String token = JwtUtil.createToken(info);
                HashMap<String, Object> loginInfo = new HashMap<>();

                loginInfo.put("token", token);

                responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
                responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
                responseWrapper.setData(loginInfo);
            } else if (member != null) {
                String password = member.getPassword();
                String loginPassword = loginAcc.getPassword();
                loginPassword = DigestUtils.md5DigestAsHex(loginPassword.getBytes());

                if (!password.equals(loginPassword)) {
                    responseWrapper.setMsg(ResultCodeEnum.PASSWORD_ERROR.getName());
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                    return responseWrapper;

                }
                //密码正确
                Map<String, String> info = new HashMap<>();
                info.put("memberId", member.getMemberId().toString());
                info.put("memberName", member.getMemberName());
                String token = JwtUtil.createToken(info);
                HashMap<String, Object> loginInfo = new HashMap<>();

                loginInfo.put("token", token);

                responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
                responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
                responseWrapper.setData(loginInfo);
            }

        } catch (Exception e) {
            log.error("用户登陆失败：{}", ExceptionUtils.getStackTrace(e));

            responseWrapper.setMsg(ResultCodeEnum.PASSWORD_ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
        }
        return responseWrapper;
    }

    @ApiOperation("检验秘钥")
    @GetMapping(value = "/testToken")
    public ResponseWrapper<Object> testToken(HttpServletRequest request) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            // 处理自己的逻辑业务
            // 此时我们想要获取 token中的用户信息，token经过拦截器拦截绝对是正确的
            String token = request.getHeader("token");

            DecodedJWT tokenInfo = JwtUtil.getTokenInfo(token);
            User user = new User();
            user.setUserId(Integer.parseInt(tokenInfo.getClaim("userId").asString()));
            user.setUserName(tokenInfo.getClaim("userName").asString());
            // 返回用户的相关信息的map集合
            responseWrapper.setData(user);
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
            responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
        } catch (Exception e) {
            log.error("检验秘钥：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ExceptionUtils.getStackTrace(e));
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.TOKEN_FAIL.getCode()));
        }

        return responseWrapper;
    }

    @ApiOperation("用户注册")
    @PostMapping(value = "/add")
    public ResponseWrapper<Object> add(@RequestBody User user) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            String userName = user.getUserName();
            String password = user.getPassword();
            String tel = user.getTel();
            // 检查用户名和密码是否为空
            if (userName == null || userName.isEmpty() || password == null || password.isEmpty()) {
                responseWrapper.setMsg("用户名或密码不能为空");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            } else if (tel == null || tel.isEmpty()) {
                responseWrapper.setMsg("手机号不能为空");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            } else if (!PhoneNumberValidator.isValidPhoneNumber(tel)) {
                responseWrapper.setMsg("手机号填写错误");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;

            }

            User lawUser = userService.selectUserByTel(user.getTel());
            if (lawUser != null) {
                responseWrapper.setMsg("手机号已注册用户");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }
            Member member = memberService.selectMemberByTel(user.getTel());
            if (member != null) {
                responseWrapper.setMsg("手机号已注册成员");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }
            User userLaw = userService.selectUserByName(userName.toString());
            if (userLaw != null) {
                responseWrapper.setMsg("用户名已存在");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                return responseWrapper;
            }
            user.setCreateTime(DateUtils.getNowDate());
            user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            UserFamily family = new UserFamily();
            family.setCreateTime(DateUtils.getNowDate());

            int result = userService.insertUser(user);

            User userID = userService.selectUserByName(userName);
            family.setUserId(String.valueOf(userID.getUserId()));
            int resultF = userFamilyService.addFamily(family);
            if (result > 0 && resultF > 0) {
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
                responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
            } else {
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
            }

        } catch (Exception e) {
            log.error("用户添加失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ExceptionUtils.getStackTrace(e));
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.FAIL.getCode()));
        }
        return responseWrapper;
    }


}
