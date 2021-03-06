package com.ml.jkeep.jpa.system.entity;


import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * Date: 2019/7/9-20:06
 *
 * @author meng
 * Description: 页面元素表
 */
@Data
@ToString
@Entity
@Table(name = "sys_element",
        indexes = {
                @Index(name = "index_sys_element_parent_id", columnList = "parent_id"),
        })
public class Element {

    /**
     * 页面元素Id
     */
    @TableGenerator(
            name = "ID_GENERATOR",
            table = "sys_sequence",
            pkColumnName = "seq_name",
            pkColumnValue = "seq_sys_element",
            valueColumnName = "current_value",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GENERATOR")
    @Column(name = "element_id")
    @Id
    private Long elementId;

    /**
     * 父级Id(顶级为0)
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 名称
     */
    @Column(name = "menu_id")
    private Long menuId;

    /**
     * 类型(0:其他 1:按钮 2:链接)
     */
    @Column(name = "type", length = 1)
    private Byte type;

    /**
     * 唯一编号. 用于鉴权
     */
    @Column(name = "code")
    private String code;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 图标
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 路径
     */
    @Column(name = "href")
    private String href;

    /**
     * 请求类型(GIT POST PUT DELETE)
     */
    @Column(name = "method")
    private String method;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 状态(0:无效 1:有效)
     */
    @Column(name = "status")
    private Byte status;

    /**
     * 创建时间
     */
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

}
