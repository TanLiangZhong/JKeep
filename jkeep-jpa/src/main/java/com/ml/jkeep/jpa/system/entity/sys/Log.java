package com.ml.jkeep.jpa.system.entity.sys;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;


/**
 * Date: 2019/7/9-20:07
 *
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
    @TableGenerator(
            name = "ID_GENERATOR",
            table = "sys_sequence",
            pkColumnName = "seq_name",
            pkColumnValue = "seq_sys_log",
            valueColumnName = "current_value",
            allocationSize = 20
    )
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
    @Column(name = "gmt_created", updatable = false)
    private Date gmtCreated;

}