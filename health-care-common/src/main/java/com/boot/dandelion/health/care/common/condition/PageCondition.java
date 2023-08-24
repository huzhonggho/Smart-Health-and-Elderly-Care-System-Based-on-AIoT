package com.boot.dandelion.health.care.common.condition;

import com.boot.dandelion.health.care.common.page.BaseQuery;
import lombok.Data;

/**
 * @ClassName PageCondition
 * @Description
 * @Author shr
 * @Date 2022/07/14
 */
@Data
public class PageCondition {

    private int currentPage;
    private int pageSize;
    private int start;
    private int limit;

    public Integer getStart() {
        BaseQuery baseQuery = new BaseQuery();
        baseQuery.setLimit(getPageSize());
        baseQuery.setPage(getCurrentPage());
        setPageSize(baseQuery.getLimit());
        setLimit(baseQuery.getLimit());
        return baseQuery.getStartIndex();
    }
}
