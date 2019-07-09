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
 * Date: 2019/7/9-20:05
 * @author meng
 * Description: 数据字典列表
 */
@Data
@ToString
@Entity
@Table(name = "sys_dict_lst")
public class DictLst {

	/**
	 * 字典列表Id
	 */
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GENERATOR")
	@Column(name = "dict_lst_id")
	@Id
	private Long dictLstId;

	/**
	 * 字典Id
	 */
	@Column(name = "dict_id")
	private Long dictId;

	/**
	 * 字典key值.
	 */
	@Column(name = "tag")
	private String tag;

	/**
	 * 名称
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 备注
	 */
	@Column(name = "remark")
	private String remark;

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

}
