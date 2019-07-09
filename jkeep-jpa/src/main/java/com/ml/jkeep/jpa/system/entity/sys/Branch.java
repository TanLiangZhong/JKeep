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
 * Date: 2019/7/9-19:57
 * @author meng
 * Description: 组织机构
 */
@Data
@ToString
@Entity
@Table(name = "sys_branch")
public class Branch {

	/**
	 * 机构Id
	 */
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GENERATOR")
	@Column(name = "branch_id")
	@Id
	private Long branchId;

	/**
	 * 父级Id(最上级为0)
	 */
	@Column(name = "parent_id")
	private Long parentId;

	/**
	 * 唯一编号
	 */
	@Column(name = "code")
	private String code;

	/**
	 * 机构名称
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 状态
	 */
	@Column(name = "status")
	private Integer status;

	/**
	 * 创建时间
	 */
	@Column(name = "gmt_created")
	private Date gmtCreated;

	/**
	 * 创建人
	 */
	@Column(name = "creator")
	private Long creator;

	/**
	 * 更新时间
	 */
	@Column(name = "gmt_modified")
	private Date gmtModified;

	/**
	 * 更新人
	 */
	@Column(name = "updater")
	private Long updater;

	/**
	 * 删除标记
	 */
	@Column(name = "d_flag")
	private Integer dFlag;

}