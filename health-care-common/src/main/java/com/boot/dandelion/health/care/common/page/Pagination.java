package com.boot.dandelion.health.care.common.page;

import com.boot.dandelion.health.care.common.enums.ResultCodeEnum;
import com.boot.dandelion.health.care.common.wrapper.ResponseWrapper;

import java.io.Serializable;

/**
 * @ClassName Pagination
 * @Description 分页条目
 * @Author shr
 * @Date 2022/07/14
 */
public class Pagination<T> extends ResponseWrapper<T> implements Serializable {

    /**
     * 当前下标
     */
    private int startIndex;

    /**
     * 总行数
     */
    private int total;

    /**
     * 当前页
     */
    private int page;

    /**
     * 每页最大行数
     */
    private int limit;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
        if (this.page <= 0) {
            this.page = 1;
        }
        this.startIndex = (this.page - 1) * limit;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
        if (this.limit <= 0) {
            // 默认10条
            this.limit = 10;
        }
    }

    public static <T> Pagination<T> ok(T data) {
        Pagination<T> pagination = new Pagination<T>();
        pagination.setData(data);
        pagination.setCode(String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
        pagination.setMsg(ResultCodeEnum.SUCCESS.getName());
        return pagination;
    }
}
