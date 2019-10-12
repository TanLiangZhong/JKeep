package com.ml.jkeep.jpa.system.repository;

import com.ml.jkeep.jpa.BaseRepository;
import com.ml.jkeep.jpa.system.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 角色 - Repository
 *
 * @author 谭良忠
 * @date 2019/7/16 17:34
 */
@Repository
public interface RoleRepository extends BaseRepository<Role, Long> {

    /**
     * 按Id删除 (逻辑删除)
     *
     * @param dFlag  删除标记
     * @param roleId 主键
     */
    @Query("update Role set dFlag = ?1 where roleId = ?2")
    void deleteById(Byte dFlag, Long roleId);
}
