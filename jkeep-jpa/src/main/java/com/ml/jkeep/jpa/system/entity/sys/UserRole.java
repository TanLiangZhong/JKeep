package com.ml.jkeep.jpa.system.entity.sys;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;


/**
 * Date: 2019/7/9-20:11
 *
 * @author meng
 * Description: 用户角色关系表, 赋予用户的角色
 */
@Data
@ToString
@Entity
@Table(name = "sys_user_role",
        indexes = {
                @Index(name = "index_sys_user_role_user_id", columnList = "user_id"),
                @Index(name = "index_sys_user_role_role_id", columnList = "role_id")
        })
public class UserRole {

    /**
     * 用户角色关系表主键
     */
    @TableGenerator(
            name = "ID_GENERATOR",
            table = "sys_sequence",
            pkColumnName = "seq_name",
            pkColumnValue = "seq_sys_user_role",
            valueColumnName = "current_value",
            allocationSize = 20
    )
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
    @Column(name = "gmt_created", updatable = false)
    private Date gmtCreated;

}
