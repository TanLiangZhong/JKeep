package com.ml.jkeep.jpa.system.repository;

import com.ml.jkeep.jpa.system.entity.sys.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 角色 - Repository
 *
 * @author 谭良忠
 * @date 2019/7/16 17:34
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

}
