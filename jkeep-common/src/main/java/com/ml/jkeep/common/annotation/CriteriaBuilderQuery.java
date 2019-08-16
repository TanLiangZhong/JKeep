package com.ml.jkeep.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.ml.jkeep.common.enums.QueryPolicy;


/**
 * Date: 2019/8/15-15:11
 * @author meng
 * Description: 自定义注解 - 通过注解标识使用CriteriaBuilder哪种查询方式
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CriteriaBuilderQuery {

	QueryPolicy queryPolicy() default QueryPolicy.LIKE;

}
