package com.ml.jkeep.jpa.system.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


/**
 * 菜单 - Entity
 *
 * @author liangzhong
 * @date 2019/5/5 21:50
 */
@Data
@ToString
@Entity
@Table(name = "sys_menu",
        indexes = {
                @Index(name = "index_sys_element_parent_id", columnList = "parent_id"),
        })
public class Menu {

    /**
     * 菜单Id
     */
    @TableGenerator(
            name = "ID_GENERATOR",
            table = "sys_sequence",
            pkColumnName = "seq_name",
            pkColumnValue = "seq_sys_menu",
            valueColumnName = "current_value",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GENERATOR")
    @Column(name = "menu_id")
    @Id
    private Long menuId;

    /**
     * 父级主键(顶级为0）
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 链接
     */
    @Column(name = "href")
    private String href;

    /**
     * 图标
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 唯一编号.
     */
    @Column(name = "code")
    private String code;

    /**
     * 是否显示(1显示,0不显示)
     */
    @Column(name = "show", length = 1)
    private Byte show;

    /**
     * 排序
     */
    @Column(name = "order")
    private Long order;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

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