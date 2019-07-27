package com.ml.jkeep.service.system;


import com.ml.jkeep.common.service.BaseService;
import com.ml.jkeep.jpa.system.entity.sys.SysLog;

/**
 * 系统日志
 *
 * @author 谭良忠
 * @date 2019/7/23 11:02
 */
public interface SysLogService extends BaseService<SysLog, Long> {

    /**
     * 新增系统日志
     *
     * @param log
     * @return
     */
    boolean insertLog(SysLog log);

}
