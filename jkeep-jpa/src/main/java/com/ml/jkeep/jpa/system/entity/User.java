package com.ml.jkeep.jpa.system.entity;

import lombok.Data;
import lombok.ToString;
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
        uniqueConstraints = {@UniqueConstraint(name = "userName", columnNames = "username")})
public class User {

    /**
     * 用户Id
     */
    @TableGenerator(
            name = "ID_GENERATOR",
            table = "sys_sequence",
            pkColumnName = "seq_name",
            pkColumnValue = "seq_sys_user",
            valueColumnName = "current_value",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GENERATOR")
    @Column(name = "user_id")
    @Id
    private Long userId;

    /**
     * 机构Id
     */
    @Column(name = "branch_id")
    private Long branchId;

    /**
     * 昵称
     */
    @Column(name = "nickname")
    private String nickname;

    /**
     * 头像
     */
    @Column(name = "avatar")
    private String avatar;

    /**
     * 登录名
     */
    @Column(name = "username")
    private String username;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 手机
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 状态值 ( 1-正常, 0-禁用 )
     */
    @Column(name = "status", length = 1)
    private Byte status;

    /**
     * 创建时间
     */
    @Column(name = "gmt_created", updatable = false)
    private Date gmtCreated;

    /**
     * 创建人
     */
    @Column(name = "creator", updatable = false)
    private Long creator;

    /**
     * 更新时间
     */
    @UpdateTimestamp
    @Column(name = "gmt_modified")
    private Date gmtModified;

    /**
     * 更新人
     */
    @Column(name = "updater")
    private Long updater;

    /**
     * 删除标记 (1-正常 0-删除)
     */
    @Column(name = "d_flag")
    private Byte dFlag;

}