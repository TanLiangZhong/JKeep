package com.ml.jkeep.jpa.system.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * 角色 - Entity
 *
 * @author liangzhong
 * @date 2019/4/18 17:08
 */
@Data
@ToString
@Entity
@Table(name = "sys_role",
        uniqueConstraints = {@UniqueConstraint(name = "name", columnNames = "name")})
public class Role {

    @TableGenerator(
            name = "ID_GENERATOR",
            table = "sys_sequence",
            pkColumnName = "seq_name",
            pkColumnValue = "seq_sys_role",
            valueColumnName = "current_value",
            allocationSize = 20
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GENERATOR")
    @Id
    @Column(name = "role_id", length = 20)
    private Long roleId;

    private String name;
}
