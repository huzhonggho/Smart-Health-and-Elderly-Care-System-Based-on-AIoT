package com.boot.dandelion.health.care.core.controller;

import com.boot.dandelion.health.care.common.condition.UserCondition;
import com.boot.dandelion.health.care.common.entity.StaticBasicInfo;
import com.boot.dandelion.health.care.common.entity.UserInfo;
import com.boot.dandelion.health.care.common.enums.ResultCodeEnum;
import com.boot.dandelion.health.care.common.page.Pagination;
import com.boot.dandelion.health.care.common.util.JwtUtil;
import com.boot.dandelion.health.care.common.util.RsaUtils;
import com.boot.dandelion.health.care.common.util.ValidatorUtils;
import com.boot.dandelion.health.care.common.wrapper.ResponseWrapper;
import com.boot.dandelion.health.care.core.config.RsaProperties;
import com.boot.dandelion.health.care.core.service.UserService;
import com.boot.dandelion.health.care.dao.entity.User;
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
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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
@RequestMapping("/api/user")
public class UserController {

    private static final String USEING = "启用";

    @Value("${resetPwd}")
    private String resetPwd;

    @Resource
    private UserService userService;

    /**
     * @Description: 用户登录, 手机号就是账号，管理端登录
     * @param: [user]
     * @return: java.lang.String
     * @author: shr
     * @date: 2022/7/14
     */
    @PostMapping(value = "/login")
    public ResponseWrapper<Object> login(@RequestBody User user) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            User lawUser = userService.getUserByLoginTel(user.getTel());
            if (lawUser != null) {
                //rsa解密
                String decryptPassword = RsaUtils.decryptByPrivateKey(RsaProperties.rsaPrivateKey, lawUser.getPassword());
                if (decryptPassword.equals(user.getPassword())) {
                    //密码正确
                    //由于手机号唯一,直接拿手机号进行jwt编码
                    String token = JwtUtil.createJWT(UUID.randomUUID().toString(), user.getTel(), null);

                    HashMap<String, Object> loginInfo = new HashMap<>();

                    loginInfo.put("token", token);

                    lawUser.setPassword(null);
                    loginInfo.put("loginUser", lawUser);

                    // 根据user状态和权限判断
                    if (USEING.equals(lawUser.getStatus())) {
                        if (lawUser.getAuthority() == 1) {
                            responseWrapper.setCode(String.valueOf(ResultCodeEnum.LOGIN_SUCCESS.getCode()));
                            responseWrapper.setMsg(ResultCodeEnum.LOGIN_SUCCESS.getName());
                            responseWrapper.setData(loginInfo);
                        } else {
                            responseWrapper.setCode(String.valueOf(ResultCodeEnum.IS_NOT_ADMIN.getCode()));
                            responseWrapper.setMsg(ResultCodeEnum.IS_NOT_ADMIN.getName());
                        }

                    } else {
                        responseWrapper.setCode(String.valueOf(ResultCodeEnum.CANNOT_USE.getCode()));
                        responseWrapper.setMsg(ResultCodeEnum.CANNOT_USE.getName());
                    }


                } else {
                    log.error("账号密码错误");
                    responseWrapper.setMsg(ResultCodeEnum.PASSWORD_ERROR.getName());
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.PASSWORD_ERROR.getCode()));
                }
            } else {
                log.error("用户名不存在");
                responseWrapper.setMsg(ResultCodeEnum.LOGINNAME_NOT_EXIT_ERROR.getName());
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.LOGINNAME_NOT_EXIT_ERROR.getCode()));
            }
        } catch (Exception e) {
            log.error("用户登陆失败：{}", ExceptionUtils.getStackTrace(e));

            responseWrapper.setMsg(ResultCodeEnum.PASSWORD_ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.PASSWORD_ERROR.getCode()));
        }
        return responseWrapper;
    }

    @PostMapping(value = "/appLogin")
    public ResponseWrapper<Object> appLogin(@RequestBody User user) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            User lawUser = userService.getUserByLoginTel(user.getTel());
            if (lawUser != null) {
                //rsa解密
                String decryptPassword = RsaUtils.decryptByPrivateKey(RsaProperties.rsaPrivateKey, lawUser.getPassword());
                if (decryptPassword.equals(user.getPassword())) {
                    //密码正确
                    //由于手机号唯一,直接拿手机号进行jwt编码
                    String token = JwtUtil.createJWT(UUID.randomUUID().toString(), user.getTel(), null);

                    HashMap<String, Object> loginInfo = new HashMap<>();

                    loginInfo.put("token", token);

                    lawUser.setPassword(null);
                    loginInfo.put("loginUser", lawUser);

                    // 根据user状态和权限判断
                    if (USEING.equals(lawUser.getStatus())) {
                        responseWrapper.setCode(String.valueOf(ResultCodeEnum.LOGIN_SUCCESS.getCode()));
                        responseWrapper.setMsg(ResultCodeEnum.LOGIN_SUCCESS.getName());
                        responseWrapper.setData(loginInfo);

                    } else {
                        responseWrapper.setCode(String.valueOf(ResultCodeEnum.CANNOT_USE.getCode()));
                        responseWrapper.setMsg(ResultCodeEnum.CANNOT_USE.getName());
                    }

                } else {
                    log.error("账号密码错误");
                    responseWrapper.setMsg(ResultCodeEnum.PASSWORD_ERROR.getName());
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.PASSWORD_ERROR.getCode()));
                }
            } else {
                log.error("用户名不存在");
                responseWrapper.setMsg(ResultCodeEnum.LOGINNAME_NOT_EXIT_ERROR.getName());
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.LOGINNAME_NOT_EXIT_ERROR.getCode()));
            }
        } catch (Exception e) {
            log.error("用户登陆失败：{}", ExceptionUtils.getStackTrace(e));

            responseWrapper.setMsg(ResultCodeEnum.PASSWORD_ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.PASSWORD_ERROR.getCode()));
        }
        return responseWrapper;
    }

    private void copyUser(User lawUser, HttpServletRequest request) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(lawUser, userInfo);
        request.getSession().setAttribute(StaticBasicInfo.LOGIN_USER, userInfo);
    }


    /**
     * @Description: 添加用户
     * @param: [user]
     * @return: com.boot.dandelion.health.care.common.wrapper.ResponseWrapper<java.lang.String>
     * @author: shr
     * @date: 2022/7/14
     */
    @PostMapping("/add")
    public ResponseWrapper<Object> add(@RequestBody User user) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            if (ValidatorUtils.checkMobileFormat(user.getTel())) {
                if (userService.getUserByLoginTel(user.getTel()) == null) {
                    //公钥加密，数据库存放的是加密后的密码
                    String encryPassword = RsaUtils.encryptByPublicKey(RsaProperties.rsaPublicKey, user.getPassword());
                    user.setPassword(encryPassword);
                    userService.addUser(user);
                    responseWrapper.setMsg("用户添加成功");
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
                } else {
                    log.error("手机号已存在：{}", user.getTel());
                    responseWrapper.setMsg(ResultCodeEnum.PHONE_REPEAT_ERROR.getName());
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.PHONE_REPEAT_ERROR.getCode()));
                }
            } else {
                log.error("手机号格式不正确：{}", user.getTel());
                responseWrapper.setMsg(ResultCodeEnum.PHONE_FORMAT_ERROR.getName());
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.PHONE_FORMAT_ERROR.getCode()));
            }
        } catch (Exception e) {
            log.error("用户注册失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    /**
     * @Description: 删除用户
     * @param: [user]
     * @return: com.boot.dandelion.health.care.common.wrapper.ResponseWrapper<java.lang.Object>
     * @author: shr
     * @date: 2022/7/14
     */
    @DeleteMapping("/remove")
    public ResponseWrapper<Object> remove(@RequestBody User user) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            if (StringUtils.isNotBlank(user.getTel()) || StringUtils.isNotEmpty(user.getTel())) {
                if (userService.getUserByLoginTel(user.getTel()) != null) {
                    userService.deleteUser(user.getTel());
                    responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
                } else {
                    log.error("根据手机号：{}，所查用户不存在", user.getTel());
                    responseWrapper.setMsg("根据手机号: " + user.getTel() + " 当前" + ResultCodeEnum.USER_NOT_EXIT.getName());
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.USER_NOT_EXIT.getCode()));
                }
            } else {
                log.error("用户手机号不能为空：{}", user.getTel());
                responseWrapper.setMsg(ResultCodeEnum.LOGINTEL_NOT_EXIT_ERROR.getName());
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.LOGINTEL_NOT_EXIT_ERROR.getCode()));
            }
        } catch (Exception e) {
            log.error("用户删除失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    /**
     * @Description: 修改用户密码
     * @param: [user]
     * @return: com.boot.dandelion.health.care.common.wrapper.ResponseWrapper<java.lang.Object>
     * @author: shr
     * @date: 2022/7/14
     */
    @PostMapping("/modifyPassword")
    public ResponseWrapper<Object> modifyPassword(@RequestBody User user) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            if (StringUtils.isNotBlank(user.getTel()) || StringUtils.isNotEmpty(user.getTel())) {
                User userByLoginTel = userService.getUserByLoginTel(user.getTel());
                if (userByLoginTel != null) {
                    //私钥解密数据库中的密码和传递过来的旧密码进行比对
                    String decryptPassword = RsaUtils.decryptByPrivateKey(RsaProperties.rsaPrivateKey, userByLoginTel.getPassword());
                    if (decryptPassword.equals(user.getOldPassword())) {
                        //密码一致
                        String encryNewPassword = RsaUtils.encryptByPublicKey(RsaProperties.rsaPublicKey, user.getPassword());
                        user.setPassword(encryNewPassword);
                        userService.updateUserPassword(user);
                        responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
                        responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
                        log.info("修改手机号为{}的用户修改密码成功", user.getTel());
                    } else {

                        log.error("修改手机号为{}的密码,但是旧密码不正确", user.getTel());
                        responseWrapper.setMsg("旧密码不正确");
                        responseWrapper.setCode(String.valueOf(500));
                    }


                } else {
                    log.error("根据手机号：{}，当前用户不存在", user.getTel());
                    responseWrapper.setMsg("根据手机号: " + user.getTel() + " 当前" + ResultCodeEnum.USER_NOT_EXIT.getName());
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.USER_NOT_EXIT.getCode()));
                }
            } else {
                log.error("用户手机号不能为空：{}", user.getTel());
                responseWrapper.setMsg(ResultCodeEnum.LOGINTEL_NOT_EXIT_ERROR.getName());
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.LOGINTEL_NOT_EXIT_ERROR.getCode()));
            }
        } catch (Exception e) {
            log.error("用户信息修改失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }


    /**
     * @Description: 查看某一个用户的详细信息
     * @param: [userPhone]
     * @return: com.boot.dandelion.health.care.common.wrapper.ResponseWrapper<java.lang.Object>
     * @author: shr
     * @date: 2022/7/14
     */
    @GetMapping(value = "/searchUserInfo")
    public ResponseWrapper<Object> searchUserInfo(String userPhone) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            if (StringUtils.isNotBlank(userPhone) || StringUtils.isNotEmpty(userPhone)) {
                User userInfo = userService.getUserByLoginTel(userPhone);
                if (userInfo != null) {
                    responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
                    responseWrapper.setData(userInfo);
                } else {
                    log.error("根据手机号：{}，当前用户不存在", userPhone);
                    responseWrapper.setMsg("根据手机号: " + userPhone + " 当前" + ResultCodeEnum.USER_NOT_EXIT.getName());
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.USER_NOT_EXIT.getCode()));
                }
            } else {
                log.error("用户手机号不能为空：{}", userPhone);
                responseWrapper.setMsg(ResultCodeEnum.LOGINTEL_NOT_EXIT_ERROR.getName());
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.LOGINTEL_NOT_EXIT_ERROR.getCode()));
            }
        } catch (Exception e) {
            log.error("用户信息查询失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    /**
     * @Description: 展示用户列表
     * @param: [userPhone]
     * @return: com.boot.dandelion.health.care.common.wrapper.ResponseWrapper<java.lang.Object>
     * @author: shr
     * @date: 2022/7/14
     */
    @PostMapping(value = "/showUserList")
    public ResponseWrapper<Object> showUserList(@RequestBody UserCondition params) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            List<User> userList = userService.queryUserList(params);
            int userTotal = userService.queryUserCount(params);
            Pagination<List<User>> userPagination = Pagination.ok(userList);
            userPagination.setTotal(userTotal);
            userPagination.setPage(params.getCurrentPage());
            userPagination.setLimit(params.getLimit());
            responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
            responseWrapper.setData(userPagination);
        } catch (Exception e) {
            log.error("用户列表展示失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    @PostMapping("/showAllUser")
    public ResponseWrapper<Object> showAllUser() {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            List<User> userList = userService.queryAllUser();

            responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
            responseWrapper.setData(userList);
        } catch (Exception e) {
            log.error("用户列表展示失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }
        return responseWrapper;
    }

    /**
     * @param user
     * @return com.boot.dandelion.health.care.common.wrapper.ResponseWrapper<java.lang.Object>
     * @description 编辑用户信息
     * @create 2023/1/9 23:27
     */
    @PostMapping("/editUser")
    public ResponseWrapper<Object> editUser(@RequestBody User user) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            int num = userService.updateUser(user);
            if (num == 1) {
                responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
            } else {
                responseWrapper.setMsg("不可修改手机号或其他错误");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
            }

        } catch (Exception e) {
            log.error("用户信息修改失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }


        return responseWrapper;
    }

    /**
     * @param user
     * @return com.boot.dandelion.health.care.common.wrapper.ResponseWrapper<java.lang.Object>
     * @description 根据手机号删除用户
     * @create 2023/1/9 23:28
     */
    @PostMapping("/delUser")
    public ResponseWrapper<Object> delUser(@RequestBody User user) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            int num = userService.delUser(user);
            if (num == 1) {
                responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
            }

        } catch (Exception e) {
            log.error("用户删除失败：{}", ExceptionUtils.getStackTrace(e));
            responseWrapper.setMsg(ResultCodeEnum.ERROR.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        }


        return responseWrapper;
    }


    /**
     * @Description: 用户注销
     * @param: [request]
     * @return: java.lang.String
     * @author: shr
     * @date: 2022/7/14
     */
    @ResponseBody
    @DeleteMapping(value = "/logout")
    public ResponseWrapper<Object> logout(HttpServletRequest request) {
        request.getSession().removeAttribute(StaticBasicInfo.LOGIN_USER);
        return ResponseWrapper.ok();
    }

    @ApiOperation("条件分页查询用户列表")
    @PostMapping("/getAllUsersByPageForAdmin/{current}/{limit}")
    public ResponseWrapper<Object> getAllUsersByPageForAdmin(@PathVariable("current") Integer current, @PathVariable("limit") Integer limit, @RequestParam(value = "name", required = false) String name) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            List<User> userList;
            PageHelper.startPage(current, limit);
            if (StringUtils.isNotEmpty(name)) {
                userList = userService.getUsersByName(name);
            } else {
                userList = userService.queryAllUser();
            }
            PageInfo<User> pageInfo = new PageInfo<User>(userList);
            responseWrapper.setData(pageInfo);
            responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
        } catch (Exception e) {
            log.error("用户列表分页查询失败：{}", ExceptionUtils.getStackTrace(e));
        }
        return responseWrapper;
    }

    @ApiOperation("重置密码")
    @PostMapping("/resetPwd")
    public ResponseWrapper<Object> resetPwd(@RequestBody User user) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();
        try {
            String tel = user.getTel();
            String name = user.getName();
            String dept = user.getDept();
            boolean telNotEmpty = StringUtils.isNotEmpty(tel);
            boolean telNotBlank = StringUtils.isNotBlank(tel);
            boolean nameNotEmpty = StringUtils.isNotEmpty(name);
            boolean nameNotBlank = StringUtils.isNotBlank(name);
            boolean deptNotEmpty = StringUtils.isNotEmpty(dept);
            boolean deptNotBlank = StringUtils.isNotBlank(dept);
            boolean Flag1 = telNotEmpty && telNotBlank;
            boolean Flag2 = nameNotEmpty && nameNotBlank;
            boolean Flag3 = deptNotEmpty && deptNotBlank;
            if (Flag3 && Flag2 && Flag1) {
                User userByLoginTel = userService.getUserByLoginTel(tel);
                if (userByLoginTel != null) {
                    String name1 = userByLoginTel.getName();
                    String dept1 = userByLoginTel.getDept();
                    if (name1.equals(name)) {
                        if (dept1.equals(dept)) {
                            String encryPassword = RsaUtils.encryptByPublicKey(RsaProperties.rsaPublicKey, resetPwd);
                            userByLoginTel.setPassword(encryPassword);
                            userService.updateUser(userByLoginTel);
                            responseWrapper.setMsg("重置密码：123456");
                            responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
                        } else {
                            responseWrapper.setMsg("该用户部门错误!");
                            responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
                        }
                    } else {
                        responseWrapper.setMsg("该用户名称错误!");
                        responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
                    }
                } else {
                    responseWrapper.setMsg("该手机号关联用户不存在，请联系管理员!");
                    responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
                }


            } else {
                responseWrapper.setMsg("输入字段不能为空或空行");
                responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
            }

        } catch (Exception e) {
            log.error("用户列表分页查询失败：{}", ExceptionUtils.getStackTrace(e));
        }
        return responseWrapper;
    }
}
