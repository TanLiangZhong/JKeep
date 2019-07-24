package com.ml.jkeep.jpa.system.repository;

import com.ml.jkeep.common.repository.BaseRepository;
import com.ml.jkeep.jpa.system.entity.sys.User;

/**
 * 用户信息
 *
 * @author 谭良忠
 * @date 2019/6/19 17:19
 */
public interface UserRepository extends BaseRepository<User, Long> {

    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

}
