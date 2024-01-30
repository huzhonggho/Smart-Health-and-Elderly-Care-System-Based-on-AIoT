package com.boot.dandelion.health.care.common.enums;

/**
 * @ClassName ResultCodeEnum
 * @Description 针对Controller的统一枚举类
 * @Author shr
 * @Date 2022/07/14
 */
public enum ResultCodeEnum {

    /**
     *
     */
    COMPANY_ROLE_NOT_MATCH_ERROR("公司角色不匹配", 506),

    ADD_SUCCESS("添加成功",200),
    FAIL("失败",1),

    ADD_FAIL("添加失败",506),

    CANNOT_USE("该用户状态未启用，请联系管理员启用。",506),

    IS_NOT_ADMIN("该账号不是管理员账号",506),


    /**
     *
     */
    EFFICACY_DATE_ERROR("用户有效期不能大于当前公司有效期", 504),

    /**
     *
     */
    ERROR("服务端错误", 500),

    /**
     *
     */
    LOGINED("该用户已在其他设备上登录，请及时修改密码或联系管理员！", 101),

    /**
     *
     */
    LOGINNAME_AND_PHONE_NOR_MATCH_ERROR("登录名和手机号不匹配", 509),

    /**
     *
     */
    LOGINNAME_ERROR("用户登录名重复", 503),

    /**
     *
     */
    LOGINNAME_NOT_EXIT_ERROR("用户名不存在", 510),

    /**
     *
     */
    LOGINTEL_NOT_EXIT_ERROR("手机号不存在", 105),

    EXIT_BLANK_VALUE("存在为空格输入，请仔细检查!",106),

    /**
     *
     */
    LOGIN_SUCCESS("登录成功", 200),

    /**
     *
     */
    MERCHANT_LACK_LICENSE_OR_BUSINESS("当前待添加商户缺少许可证号或工商营业执照编号", 104),

    /**
     *
     */
    MERCHANT_REPEAT_ERROR("当前商户已存在，请校查验许可证号或工商营业质执照编号", 104),

    /**
     *
     */
    NAME_REPEAT_ERROR("名称存在重复", 505),

    /**
     *
     */
    NOT_MANUAL_UPDATE_VALID_STATUS_ERROR("机构已到期，无法手动启用", 507),

    /**
     *
     */
    NOT_NORMAL_REQUEST_ERROR("非法请求", 512),

    /**
     *
     */
    NOT_REPEAT_SEND_MESSAGE_ERROR("请在120秒后再次发送短信", 508),

    /**
     *
     */
    PARAMETER_ERROR("参数校验失败", 501),

    /**
     *
     */
    PASSWORD_ERROR("用户名或密码错误", 502),

    /**
     *
     */
    PHONE_FORMAT_ERROR("手机号格式错误", 103),

    /**
     *
     */
    PHONE_REPEAT_ERROR("手机号已存在", 104),

    /**
     *
     */
    RE_LOGIN("密码过期，请重新登陆！", 100),

    /**
     *
     */
    SEND_MESSAGE_SUCCESS("发送短信成功", 200),

    /**
     *
     */
    SUCCESS("操作成功", 200),

    /**
     *
     */
    USER_NOT_EXIT("用户不存在", 106),

    /**
     *
     */
    VERIFY_CODE_ERROR("验证码错误", 512),

    /**
     *
     */
    NOTICE_ADD_ERROR("发布失败，内容均不能为空",601),

    /**
     *
     */
    SUPERVISION_ADD_ERROR("提交失败，内容不能为空",501),

    /**
     *
     */
    SUPERVISION_ADD_SUCCESS("提交成功",200),

    /**
     *
     */
    EXPRESS_ADD_SUCCESS("提交成功",200),

    /**
     *
     */
    NOTICE_ADD_SUCCESS("发布成功",200),

    /**
     *
     */
    FOLLOW_MERCHANT_EMPTY("当前关注商户为空!",200),
    /**
     *
     */
    FOLLOW_MERCHANT_SUCCESS("获取关注商户内容成功",200),
    /**
     *
     */
    RECORDS_GET_SUCCESS("查询成功",200),

    /**
     *
     */
    RECORD_GET_EMPTY("数据为空",200),
    /**
     *
     */
    VERIFY_CODE_EXPIRE_ERROR("验证码已过期", 511),

    /**
     *
     */
    UPDATE_FAIL("更新字段值失败",500),

    /**
     *
     */
    UPDATE_SUCCESS("更新成功",200),

    /**
     *
     */
    EXIT_EMPTY_VALUE("存在未填的必须字段",500),

    /**
     *
     */
    NOT_EXIT_MERCHANT("不存在该商户，等待管理员添加",500),
    /**
     *
     */
    ILLEGAL_FILE_TYPE_ERROR("文件类型不合法", 512);

    /**
     * 名称
     */
    private String name;

    /**
     * 资源code
     */
    private int code;

    ResultCodeEnum(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
