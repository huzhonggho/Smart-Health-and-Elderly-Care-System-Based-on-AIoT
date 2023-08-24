package com.boot.dandelion.health.care.common.wrapper;

import com.boot.dandelion.health.care.common.enums.ResultCodeEnum;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

/**
 * @ClassName ResponseWrapper
 * @Description 接口返回数据的包装器
 * @Author shr
 * @Date 2022/07/14
 */
@Data
public class ResponseWrapper<T> {

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    public ResponseWrapper() {
    }

    public static <T> ResponseWrapper<T> ok() {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<T>();
        responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
        responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
        return responseWrapper;
    }

    public static <T> ResponseWrapper<T> ok(T data) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<T>();
        responseWrapper.setData(data);
        responseWrapper.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
        responseWrapper.setMsg(ResultCodeEnum.SUCCESS.getName());
        return responseWrapper;
    }

    public static <T> ResponseWrapper<T> fail(String msg) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<T>();
        responseWrapper.setCode(String.valueOf(ResultCodeEnum.ERROR.getCode()));
        responseWrapper.setMsg(msg == null ? ResultCodeEnum.ERROR.getName() : msg);
        return responseWrapper;
    }

}

