package com.ml.jkeep.jpa.system.entity.sys;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;


/**
 * Date: 2019/7/9-20:07
 * @author meng
 * Description: 系统日志
 */
@Data
@Entity
@Table(name = "sys_log")
public class Log {

	/**
	 * 角色关系表主键
	 */
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GENERATOR")
	@Column(name = "log_id")
	@Id
	private Long logId;

	/**
	 * 角色Id
	 */
	@Column(name = "user_id")
	private Long userId;

	/**
	 * 菜单
	 */
	@Column(name = "username")
	private String username;

	/**
	 * 模块名称
	 */
	@Column(name = "modules")
	private String modules;

	/**
	 * 请求地址
	 */
	@Column(name = "href")
	private String href;

	/**
	 * 用户操作
	 */
	@Column(name = "operation")
	private String operation;

	/**
	 * 请求参数
	 */
	@Column(name = "params")
	private String params;

	/**
	 * 请求方法
	 */
	@Column(name = "method")
	private String method;

	/**
	 * IP地址
	 */
	@Column(name = "ip")
	private String ip;

	/**
	 * null
	 */
	@Column(name = "gmt_created")
	private Date gmtCreated;

}