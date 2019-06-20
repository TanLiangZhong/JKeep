package com.ml.jkeep.jpa.system.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
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
@Table(name = "sys_menu")
public class Menu {

    @TableGenerator(
            name = "ID_GENERATOR",
            table = "sys_sequence",
            pkColumnName = "seq_name",
            pkColumnValue = "seq_sys_menu",
            valueColumnName = "current_value",
            allocationSize = 20
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GENERATOR")
    @Id
    @Column(name = "id", length = 20)
    private Long id;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 路径
     */
    @Column(name = "path")
    private String path;

    /**
     * 图标
     */
    @Column(name = "icon", length = 50)
    private String icon;

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
