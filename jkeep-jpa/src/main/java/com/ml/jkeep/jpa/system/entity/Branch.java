package com.ml.jkeep.jpa.system.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Date: 2019/7/9-19:57
 *
 * @author meng
 * Description: 组织机构
 */
@Data
@ToString
@Entity
@Table(name = "sys_branch",
        indexes = {
                @Index(name = "index_sys_branch_parent_id", columnList = "parent_id")
        })
public class Branch {

    /**
     * 机构Id
     */
    @TableGenerator(
            name = "ID_GENERATOR",
            table = "sys_sequence",
            pkColumnName = "seq_name",
            pkColumnValue = "seq_sys_branch",
            valueColumnName = "current_value",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GENERATOR")
    @Column(name = "branch_id")
    @Id
    private Long branchId;

    /**
     * 父级Id(最上级为0)
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 唯一编号
     */
    @Column(name = "code")
    private String code;

    /**
     * 机构名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 状态 (1-启用 0-禁用)
     */
    @Column(name = "status", length = 1)
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