package com.ml.jkeep.jpa.system.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


/**
 * 用户 - Entity
 *
 * @author 谭良忠
 * @date 2019/6/19 17:19
 */
@Data
@ToString
@Entity
@Table(name = "sys_user",
        uniqueConstraints = {@UniqueConstraint(name = "userName", columnNames = "user_name")})
public class User {

    @TableGenerator(
            name = "ID_GENERATOR",
            table = "sys_sequence",
            pkColumnName = "seq_name",
            pkColumnValue = "seq_sys_user",
            valueColumnName = "current_value",
            allocationSize = 20
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GENERATOR")
    @Id
    @Column(name = "user_id", length = 20)
    private Long userId;

    /**
     * 昵称
     */
    @Column(name = "nickname", length = 24)
    private String nickname;

    /**
     * 用户名
     */
    @Column(name = "user_name", length = 24)
    private String userName;

    /**
     * 密码
     */
    @Column(name = "password", length = 64, updatable = false)
    private String password;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 手机
     */
    @Column(name = "phone", length = 11)
    private String phone;

    /**
     * 状态
     */
    @Column(name = "status", length = 1)
    private Byte status;

    /**
     * 建立时间
     */
    @CreationTimestamp
    @Column(name = "gmt_created", updatable = false)
    private Date gmtCreated;

    /**
     * 创建者
     */
    @Column(name = "creator", updatable = false, length = 20)
    private Long creator;

    /**
     * 更新时间
     */
    @UpdateTimestamp
    @Column(name = "gmt_modified")
    private Date gmtModified;

    /**
     * 更新者
     */
    @Column(name = "updater", length = 20)
    private Long updater;

    /**
     * 删除标记
     */
    @Column(name = "d_flag", length = 1)
    private Byte dFlag;
}