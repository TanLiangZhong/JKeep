package com.ml.jkeep.common.enums;


/**
 * Date: 2019/8/15-15:11
 * @author meng
 * Description: 指定某些查询条件
 */
public enum QueryPolicy {

	/**
	 * CriteriaBuilder.equal
	 */
	EQUAL ,


	/**
	 * CriteriaBuilder.like
	 */
	LIKE,

	/**
	 * CriteriaBuilder.greaterThanOrEqualTo
	 */
	GREATERTHANOREQUALTO,

	/**
	 * CriteriaBuilder.lessThanOrEqualTo
	 */
	LESSTHANOREQUALTO
}
