package com.ml.jkeep.jpa.system.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;


/**
 * 菜单 - Entity
 *
 * @author liangzhong
 * @date 2019/5/5 21:50
 */
@Data
@ToString
public class MenuVo {

    /**
     * 菜单Id
     */
    private Long menuId;

    /**
     * 父级主键(顶级为0）
     */
    private Long parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 链接
     */
    private String href;

    /**
     * 图标
     */
    private String icon;

    /**
     * 唯一编号.
     */
    private String code;

    /**
     * 是否显示(1-显示,0-不显示)
     */
    private Integer show;

    private List<MenuVo> children;
}