package com.ml.jkeep.jpa.system.repository;

import com.ml.jkeep.jpa.BaseRepository;
import com.ml.jkeep.jpa.system.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * 菜单
 *
 * @author 谭良忠
 * @date 2019/7/23 11:05
 */
@Repository
public interface MenuRepository extends BaseRepository<Menu, Long> {

    /**
     * 根据主键批量查询
     *
     * @param menuIds
     * @return
     */
    List<Menu> findAllByMenuIdIn(Set<Long> menuIds);

}
