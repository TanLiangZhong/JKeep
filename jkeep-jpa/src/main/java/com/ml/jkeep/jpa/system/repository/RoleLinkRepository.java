package com.ml.jkeep.jpa.system.repository;

import com.ml.jkeep.common.repository.BaseRepository;
import com.ml.jkeep.jpa.system.entity.sys.RoleLink;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色关联表
 *
 * @author 谭良忠
 * @date 2019/7/23 11:05
 */
@Repository
public interface RoleLinkRepository extends BaseRepository<RoleLink, Long> {

    /**
     * 根据角色Id查询
     *
     * @param roleIds 角色Ids
     * @return  RoleLink
     */
    List<RoleLink> findAllByRoleIdIn(List<Long> roleIds);
}
