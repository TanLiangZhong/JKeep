package com.ml.jkeep.service.system.impl;

import com.ml.jkeep.common.bo.PageBo;
import com.ml.jkeep.common.vo.PageVo;
import com.ml.jkeep.jpa.system.entity.SysLog;
import com.ml.jkeep.jpa.system.repository.SysLogRepository;
import com.ml.jkeep.service.system.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 系统日志
 *
 * @author 谭良忠
 * @date 2019/7/23 11:02
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogRepository sysLogRepository;

    @Override
    public PageVo<SysLog> findPage(PageBo<SysLog> pageBo) {
        return null;
    }

    @Async
    @Override
    public void insertLog(SysLog log) {
        sysLogRepository.save(log);
    }
}
