package com.ml.jkeep.jpa.system.repository;

import com.ml.jkeep.jpa.BaseRepository;
import com.ml.jkeep.jpa.system.entity.SysLog;
import org.springframework.stereotype.Repository;

/**
 * 系统日志
 *
 * @author 谭良忠
 * @date 2019/7/23 11:05
 */
@Repository
public interface SysLogRepository extends BaseRepository<SysLog, Long> {
}
