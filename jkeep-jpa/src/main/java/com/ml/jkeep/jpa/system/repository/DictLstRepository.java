package com.ml.jkeep.jpa.system.repository;

import com.ml.jkeep.jpa.BaseRepository;
import com.ml.jkeep.jpa.system.entity.DictLst;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据字典列表
 *
 * @author 谭良忠
 * @date 2019/7/26
 */
@Repository
public interface DictLstRepository extends BaseRepository<DictLst, Long> {

    /**
     * 查询数据字典列表
     *
     * @param dictId
     * @return
     */
    List<DictLst> findAllByDictId(Long dictId);

    /**
     * 根据字典Id删除数据字典列表
     *
     * @param dictId
     */
    void deleteAllByDictId(Long dictId);
}
