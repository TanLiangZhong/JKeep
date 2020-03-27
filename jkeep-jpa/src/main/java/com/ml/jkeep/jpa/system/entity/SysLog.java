package com.ml.jkeep.jpa.system.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * Date: 2019/7/9-20:07
 *
 * @author meng
 * Description: 系统日志
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "sys_log")
public class SysLog {

    /**
     * 角色关系表主键
     */
    @TableGenerator(
            name = "ID_GENERATOR",
            table = "sys_sequence",
            pkColumnName = "seq_name",
            pkColumnValue = "seq_sys_log",
            valueColumnName = "current_value",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GENERATOR")
    @Column(name = "log_id")
    @Id
    private Long logId;

    /**
     * 用户Id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 用户名
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
     * 耗时
     */
    @Column(name = "time_consuming")
    private Long timeConsuming;

    /**
     * 创建时间
     */
    @CreationTimestamp
    @Column(name = "gmt_created", updatable = false)
    private LocalDateTime gmtCreated;

    public SysLog(Long userId, String username, String modules, String href, String operation, String params, String method, String ip, Long timeConsuming) {
        this.userId = userId;
        this.username = username;
        this.modules = modules;
        this.href = href;
        this.operation = operation;
        this.params = params;
        this.method = method;
        this.ip = ip;
        this.timeConsuming = timeConsuming;
    }
}