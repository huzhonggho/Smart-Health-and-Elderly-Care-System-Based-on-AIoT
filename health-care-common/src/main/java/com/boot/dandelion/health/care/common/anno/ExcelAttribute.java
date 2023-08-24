package com.boot.dandelion.health.care.common.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName ExcelAttribute
 * @Description Excel列-自写注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelAttribute {

    /**Excel列名称*/
    String name() default "";

    /**Excel列索引*/
    int sort();

    /**Cell格式*/
    String format() default "";
}
