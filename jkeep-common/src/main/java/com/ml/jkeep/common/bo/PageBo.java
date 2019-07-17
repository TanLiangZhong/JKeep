package com.ml.jkeep.common.bo;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "起始页为空")
    @Min(value = 1, message = "起始页最小1")
    private Integer page;

    /**
     * 页码大小
     */
    @NotNull(message = "页大小为空")
    @Min(value = 5, message = "页大小最小5")
    @Max(value = 100, message = "页大小最大100")
    private Integer size;

    /**
     * 查询参数
     */
    private T param;

    /**
     * 排序字段
     */
    private String sortableField;
}