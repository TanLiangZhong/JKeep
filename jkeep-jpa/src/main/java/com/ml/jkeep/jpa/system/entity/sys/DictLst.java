package com.ml.jkeep.jpa.system.entity.sys;


import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


/**
 * Date: 2019/7/9-20:05
 *
 * @author meng
 * Description: 数据字典列表
 */
@Data
@ToString
@Entity
@Table(name = "sys_dict_lst",
        indexes = {
                @Index(name = "index_sys_dict_lst_dict_id", columnList = "dict_id"),
        })
public class DictLst {

    /**
     * 字典列表Id
     */
    @TableGenerator(
            name = "ID_GENERATOR",
            table = "sys_sequence",
            pkColumnName = "seq_name",
            pkColumnValue = "seq_sys_dict_lst",
            valueColumnName = "current_value",
            allocationSize = 20
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GENERATOR")
    @Column(name = "dict_lst_id")
    @Id
    private Long dictLstId;

    /**
     * 字典Id
     */
    @Column(name = "dict_id")
    private Long dictId;

    /**
     * 字典key值.
     */
    @Column(name = "tag")
    private String tag;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

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

}
