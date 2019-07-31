package com.ml.jkeep.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页响应实体
 *
 * @author 谭良忠
 * @date 2019/7/30 17:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVo<T> implements Serializable {

    /**
     * 当前页数
     */
    private Integer page;

    /**
     * 每页条数
     */
    private Integer size;

    /**
     * 总数
     */
    private Long total;

    /**
     * 总页数
     */
    private Integer totalPages;

    /**
     * 数据 {@link List}
     */
    private List<T> data;


}
