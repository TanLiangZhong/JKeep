package com.ml.jkeep.service.system.impl;

import com.ml.jkeep.jpa.system.entity.Role;
import com.ml.jkeep.jpa.system.entity.UserAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * 用户验证 -impl
 *
 * @author 谭良忠
 * @date 2019/6/20 10:58
 */
@Slf4j
@Service
public class UserAuthServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        // TODO get user

        log.info("username: {}", s);
        if (!"jkeep".equals(s)) {
            throw new UsernameNotFoundException("请输入正确的用户名");
        }
        UserAuth userAuth = new UserAuth();
        userAuth.setUsername("jkeep");
        userAuth.setPassword(new BCryptPasswordEncoder().encode("jkeep"));
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        userAuth.setRoles(Collections.singletonList(role));
        return userAuth;
    }
}
