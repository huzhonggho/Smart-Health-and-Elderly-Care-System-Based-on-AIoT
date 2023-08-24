package com.boot.dandelion.health.care.common.page;

import java.io.Serializable;

/**
 * @ClassName BaseQuery
 * @Description 分页查询基础信息
 * @Author shr
 * @Date 2022/07/14
 */
public class BaseQuery implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3810775911958024990L;

    /**
     * 当前下标
     */
    private int startIndex;

    /**
     * 当前页
     */
    private int page;

    /**
     * 每页最大行数
     */
    private int limit;

    public BaseQuery() {
    }

    public BaseQuery(int page, int limit) {
        setPage(page);
        setLimit(limit);
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
}
