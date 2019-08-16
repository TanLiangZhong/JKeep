package com.ml.jkeep.common.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
    private Integer size = 20;

    /**
     * 查询参数
     */
    private T param;

    /**
     * 多个参数排序
     *
     * key : createDate
     * value : desc
     */
    private Map<String,String> orderby = new HashMap<>();

    /**
     * 单参数排序
     */
    private String sortableField;
}