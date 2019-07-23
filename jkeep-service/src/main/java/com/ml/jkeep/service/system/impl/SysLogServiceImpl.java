package com.ml.jkeep.service.system.impl;

import com.ml.jkeep.common.service.impl.BaseServiceImpl;
import com.ml.jkeep.jpa.system.entity.sys.SysLog;
import com.ml.jkeep.jpa.system.repository.SysLogRepository;
import com.ml.jkeep.service.system.SysLogService;
import org.springframework.stereotype.Service;

/**
 * 系统日志
 *
 * @author 谭良忠
 * @date 2019/7/23 11:02
 */
@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLogRepository, SysLog, Long> implements SysLogService {
}