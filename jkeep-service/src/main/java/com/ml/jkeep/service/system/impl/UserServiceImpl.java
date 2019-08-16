package com.ml.jkeep.service.system.impl;

import com.ml.jkeep.jpa.system.entity.User;
import com.ml.jkeep.jpa.system.repository.UserRepository;
import com.ml.jkeep.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Date: 2019/8/15-15:11
 * @author meng
 * Description:
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserRepository,User> implements UserService {

	@Autowired
	private UserRepository userRepository;

}
