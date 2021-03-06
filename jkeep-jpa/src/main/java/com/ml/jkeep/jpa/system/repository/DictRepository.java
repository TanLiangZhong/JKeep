package com.ml.jkeep.jpa.system.repository;

import com.ml.jkeep.jpa.BaseRepository;
import com.ml.jkeep.jpa.system.entity.Dict;
import org.springframework.stereotype.Repository;

/**
 * 数据字典
 *
 * @author 谭良忠
 * @date 2019/7/26
 */
@Repository
public interface DictRepository extends BaseRepository<Dict, Long> {

}
