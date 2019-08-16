package com.ml.jkeep.jpa.system.bo;

import com.ml.jkeep.common.annotation.CriteriaBuilderQuery;
import com.ml.jkeep.common.enums.QueryPolicy;
import lombok.Data;

/**
 * Date: 2019/8/15-15:28
 * @author meng
 * Description: 用户搜索 - bo
 */
@Data
public class UserSearchBo {

	/**
	 * 机构
	 */
	@CriteriaBuilderQuery(queryPolicy= QueryPolicy.EQUAL)
	private String branchId;

	/**
	 * 账号
	 */
	@CriteriaBuilderQuery(queryPolicy= QueryPolicy.LIKE)
	private String username;

	/**
	 * 昵称
	 */
	@CriteriaBuilderQuery(queryPolicy= QueryPolicy.LIKE)
	private String nickname;

	/**
	 * 邮箱
	 */
	@CriteriaBuilderQuery(queryPolicy= QueryPolicy.LIKE)
	private String email;

	/**
	 * 电话
	 */
	@CriteriaBuilderQuery(queryPolicy= QueryPolicy.LIKE)
	private String phone;

	/**
	 * 姓名
	 */
	@CriteriaBuilderQuery(queryPolicy= QueryPolicy.LIKE)
	private String name;

	/**
	 * 状态
	 */
	@CriteriaBuilderQuery(queryPolicy= QueryPolicy.EQUAL)
	private String status;

}
