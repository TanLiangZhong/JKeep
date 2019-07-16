package com.ml.jkeep.jpa.system.entity.sys;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * Date: 2019/7/9-20:10
 *
 * @author meng
 * Description: 角色权限关联表
 */
@Data
@ToString
@Entity
@Table(name = "sys_role_link",
        indexes = {
                @Index(name = "index_sys_role_link_role_id", columnList = "role_id"),
                @Index(name = "index_sys_role_link_menu_id", columnList = "menu_id"),
                @Index(name = "index_sys_role_link_element_id", columnList = "element_id")
        })
public class RoleLink {

    /**
     * 角色关系表主键
     */
    @TableGenerator(
            name = "ID_GENERATOR",
            table = "sys_sequence",
            pkColumnName = "seq_name",
            pkColumnValue = "seq_sys_role_link",
            valueColumnName = "current_value",
            allocationSize = 20
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GENERATOR")
    @Column(name = "role_link_id")
    @Id
    private Long roleLinkId;

    /**
     * 角色Id
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 菜单
     */
    @Column(name = "menu_id")
    private Long menuId;

    /**
     * 页面元素Id
     */
    @Column(name = "element_id")
    private String elementId;

    /**
     * 类型( 0:菜单 1:页面元素 )
     */
    @Column(name = "type")
    private Integer type;

    /**
     * null
     */
    @Column(name = "gmt_created", updatable = false)
    private Date gmtCreated;

}
