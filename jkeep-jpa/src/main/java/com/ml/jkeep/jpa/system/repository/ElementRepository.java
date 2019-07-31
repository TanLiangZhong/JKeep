package com.ml.jkeep.jpa.system.repository;

import com.ml.jkeep.jpa.BaseRepository;
import com.ml.jkeep.jpa.system.entity.Element;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * 页面元素
 *
 * @author 谭良忠
 * @date 2019/7/23 11:05
 */
@Repository
public interface ElementRepository extends BaseRepository<Element, Long> {

    /**
     * 根据主键批量查询
     *
     * @param elementIds
     * @return
     */
    List<Element> findAllByElementIdIn(Set<Long> elementIds);

}
