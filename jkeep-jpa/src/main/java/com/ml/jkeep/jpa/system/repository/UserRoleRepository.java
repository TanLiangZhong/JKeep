package com.ml.jkeep.jpa.system.repository;

import com.ml.jkeep.jpa.BaseRepository;
import com.ml.jkeep.jpa.system.entity.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户角色关联
 *
 * @author 谭良忠
 * @date 2019/7/23 11:05
 */
@Repository
public interface UserRoleRepository extends BaseRepository<UserRole, Long> {

    /**
     * 根据用户Id查询
     *
     * @param userId 用户Id
     * @return UserRole
     */
    List<UserRole> findAllByUserId(Long userId);

}
