package com.ml.jkeep.jpa.system.repository;

import com.ml.jkeep.jpa.BaseRepository;
import com.ml.jkeep.jpa.system.entity.RoleLink;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

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
     * @return RoleLink
     */
    List<RoleLink> findAllByRoleIdIn(Set<Long> roleIds);


    /**
     * 根据角色Id查询
     *
     * @param roleIds 角色Ids
     * @param type    类型
     * @return RoleLink
     */
    List<RoleLink> findAllByRoleIdInAndType(Set<Long> roleIds, Byte type);
}
