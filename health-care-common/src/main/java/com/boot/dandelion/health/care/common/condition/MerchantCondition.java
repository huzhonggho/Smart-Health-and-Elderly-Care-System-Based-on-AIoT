package com.boot.dandelion.health.care.common.condition;

import lombok.Data;

/**
 * @ClassName MerchantCondition
 * @Description
 */
@Data
public class MerchantCondition extends com.boot.dandelion.health.care.common.condition.PageCondition {
    /**
     * 商品名称
     */
    private String merchant;
    /**
     * 许可证号
     */
    private String licenseId;
    /**
     * 营业执照
     */
    private String business;
    /**
     * 区域
     */
    private String region;
    /**
     * 商铺负责人
     */
    private String name;
    /**
     * 电话
     */
    private String tel;
    /**
     * 品牌
     */
    private String brand;
}
