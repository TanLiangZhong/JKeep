package com.ml.jkeep.jpa.system.entity.sys;



import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;


/**
 * Date: 2019/7/9-20:11
 * @author meng
 * Description: 用户角色关系表, 赋予用户的角色
 */
@Data
@ToString
@Entity
@Table(name = "sys_user_role")
public class UserRole {

	/**
	 * 用户角色关系表主键
	 */
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GENERATOR")
	@Column(name = "user_role_id")
	@Id
	private Long userRoleId;

	/**
	 * 用户Id
	 */
	@Column(name = "user_id")
	private String userId;

	/**
	 * 角色Id
	 */
	@Column(name = "role_id")
	private String roleId;

	/**
	 * null
	 */
	@Column(name = "gmt_created")
	private Date gmtCreated;

}
