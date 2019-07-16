package com.ml.jkeep.service.system.impl;

import com.ml.jkeep.jpa.system.repository.RoleRepository;
import com.ml.jkeep.service.system.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 角色 -Impl
 *
 * @author 谭良忠
 * @date 2019/7/16 17:42
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repository;

}
