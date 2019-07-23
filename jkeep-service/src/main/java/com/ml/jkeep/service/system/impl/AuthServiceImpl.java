package com.ml.jkeep.service.system.impl;

import com.ml.jkeep.jpa.system.entity.sys.RoleLink;
import com.ml.jkeep.jpa.system.entity.sys.UserAuth;
import com.ml.jkeep.jpa.system.entity.sys.UserRole;
import com.ml.jkeep.jpa.system.repository.RoleLinkRepository;
import com.ml.jkeep.jpa.system.repository.UserRoleRepository;
import com.ml.jkeep.jpa.system.vo.HrefPermissionVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleLinkRepository roleLinkRepository;


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
//        Role role = new Role();
//        role.setName("ROLE_ADMIN");
//        userAuth.setRoles(Collections.singletonList(role));
        return userAuth;
    }

    /**
     * 获取用户可访问的 href
     *
     * @return 可访问的href
     */
    public List<HrefPermissionVo> hrefPermission(Long userId) {
        List<HrefPermissionVo> vos = new ArrayList<>();
        List<RoleLink> roleLinkList = Optional.ofNullable(roleLinkRepository.findAllByRoleIdIn(Optional.ofNullable(userRoleRepository.findAllByUserId(userId)).orElse(new ArrayList<>()).stream().map(UserRole::getRoleId).collect(Collectors.toSet()))).orElse(new ArrayList<>());
        Set<Long> menuIds = new HashSet<>();
        Set<Long> elementIds = new HashSet<>();
        roleLinkList.forEach(roleLink -> {
            menuIds.add(roleLink.getMenuId());
            elementIds.add(roleLink.getElementId());
        });

        // TODO
        return vos;
    }

    /**
     * 获取用户可访问的 href
     *
     * @return 可访问的href
     */
    public List<HrefPermissionVo> hrefPermission() {
        return this.hrefPermission(null);
    }
}
