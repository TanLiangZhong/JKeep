package com.ml.jkeep.service.system;


import com.ml.jkeep.common.bo.PageBo;
import com.ml.jkeep.common.vo.PageVo;
import com.ml.jkeep.jpa.system.entity.SysLog;

/**
 * 系统日志
 *
 * @author 谭良忠
 * @date 2019/7/23 11:02
 */
public interface SysLogService {

    /**
     * 分页查询
     *
     * @param pageBo 分页对象
     * @return {@link PageVo}
     */
    PageVo<SysLog> findPage(PageBo<SysLog> pageBo);

    /**
     * 新增系统日志
     *
     * @param log
     * @return
     */
    void insertLog(SysLog log);

}
