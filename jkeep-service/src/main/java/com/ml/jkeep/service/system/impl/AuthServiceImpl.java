package com.ml.jkeep.service.system.impl;

import com.ml.jkeep.jpa.system.entity.User;
import com.ml.jkeep.jpa.system.entity.UserAuth;
import com.ml.jkeep.jpa.system.repository.UserRepository;
import com.ml.jkeep.service.system.HrefPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 鉴权 -impl
 *
 * @author 谭良忠
 * @date 2019/6/20 10:58
 */
@Slf4j
@Service
public class AuthServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HrefPermissionService hrefPermissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername, username: {}", username);
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("用户名不存在, {}", username);
            throw new UsernameNotFoundException("请输入正确的用户名");
        }
        UserAuth userAuth = new UserAuth();
        BeanUtils.copyProperties(user,userAuth);
        userAuth.setHrefPer(hrefPermissionService.hrefPermission(user.getUserId()));
        return userAuth;
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
        log.info(bpe.encode("JKeep"));
    }
}
