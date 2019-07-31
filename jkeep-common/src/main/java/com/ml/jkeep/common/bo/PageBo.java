package com.ml.jkeep.common.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页 - bo
 *
 * @author 谭良忠
 * @date 2019/7/17 17:38
 */
@Data
public class PageBo<T> implements Serializable {

    /**
     * 页码
     */
    private Integer page;

    /**
     * 页码大小
     */
    private Integer size;

    /**
     * 查询参数
     */
    private T param;

    /**
     * 排序字段
     */
    private String sortableField = "gmtCreated";
}