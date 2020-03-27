package com.ml.jkeep.jpa.system.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 角色 - Entity
 *
 * @author liangzhong
 * @date 2019/4/18 17:08
 */
@Data
@ToString
@Entity
@NoArgsConstructor
@Table(name = "sys_role",
        uniqueConstraints = {@UniqueConstraint(name = "name", columnNames = "name")})
public class Role {

    /**
     * 角色Id
     */
    @TableGenerator(
            name = "ID_GENERATOR",
            table = "sys_sequence",
            pkColumnName = "seq_name",
            pkColumnValue = "seq_sys_role",
            valueColumnName = "current_value",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GENERATOR")
    @Column(name = "role_id")
    @Id
    private Long roleId;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 唯一编号.
     */
    @Column(name = "code")
    private String code;

    /**
     * 描述
     */
    @Column(name = "describe")
    private String describe;

    /**
     * 状态(0-停用 1-启用)
     */
    @Column(name = "status")
    private String status;

    /**
     * 创建时间
     */
    @CreationTimestamp
    @Column(name = "gmt_created", updatable = false)
    private LocalDateTime gmtCreated;

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
    private LocalDateTime gmtModified;

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

    public Role(Long roleId, Byte dFlag) {
        this.roleId = roleId;
        this.dFlag = dFlag;
    }
}
