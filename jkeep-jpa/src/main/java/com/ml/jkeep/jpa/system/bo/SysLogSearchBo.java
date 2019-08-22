package com.ml.jkeep.jpa.system.bo;

import lombok.Data;

import java.util.Date;

/**
 * 系统日志搜索 - bo
 */
@Data
public class SysLogSearchBo {

    /**
     * 关键字 , (操作人, 请求地址, 请求方法)
     */
    private String keyword;

    /**
     * 开始时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;

}
