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
 * Date: 2019/7/9-20:10
 * @author meng
 * Description: 角色权限关联表
 */
@Data
@ToString
@Entity
@Table(name = "sys_role_link")
public class RoleLink {

	/**
	 * 角色关系表主键
	 */
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GENERATOR")
	@Column(name = "role_link_id")
	@Id
	private Long roleLinkId;

	/**
	 * 角色Id
	 */
	@Column(name = "role_id")
	private Long roleId;

	/**
	 * 菜单
	 */
	@Column(name = "menu_id")
	private Long menuId;

	/**
	 * 页面元素Id
	 */
	@Column(name = "element_id")
	private String elementId;

	/**
	 * 类型( 0:菜单 1:页面元素 )
	 */
	@Column(name = "type")
	private Integer type;

	/**
	 * null
	 */
	@Column(name = "gmt_created")
	private Date gmtCreated;

}
