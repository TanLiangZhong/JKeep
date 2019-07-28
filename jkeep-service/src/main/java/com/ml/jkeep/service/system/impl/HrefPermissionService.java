package com.ml.jkeep.service.system.impl;

import com.ml.jkeep.common.constant.CacheKey;
import com.ml.jkeep.jpa.system.entity.sys.Element;
import com.ml.jkeep.jpa.system.entity.sys.Menu;
import com.ml.jkeep.jpa.system.entity.sys.RoleLink;
import com.ml.jkeep.jpa.system.entity.sys.UserRole;
import com.ml.jkeep.jpa.system.repository.ElementRepository;
import com.ml.jkeep.jpa.system.repository.MenuRepository;
import com.ml.jkeep.jpa.system.repository.RoleLinkRepository;
import com.ml.jkeep.jpa.system.repository.UserRoleRepository;
import com.ml.jkeep.jpa.system.vo.HrefPermissionVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * href 权限服务
 *
 * @author 谭良忠
 * @date 2019/7/24 17:50
 */
@Slf4j
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = CacheKey.HREF_PERMISSION_NAME)
public class HrefPermissionService {

    private final UserRoleRepository userRoleRepository;
    private final RoleLinkRepository roleLinkRepository;
    private final ElementRepository elementRepository;
    private final MenuRepository menuRepository;

    /**
     * 获取用户可访问的 href
     *
     * @param userId 用户Id
     * @return 用户所有权限
     */
    @Cacheable(key = "T(com.ml.jkeep.common.constant.CacheKey).ALL_HREF_PERMISSION_KEY+#userId")
    public Set<HrefPermissionVo> hrefPermission(Long userId) {
        return this.convert(roleLinkRepository.findAllByRoleIdIn(Optional.ofNullable(userRoleRepository.findAllByUserId(userId)).orElse(new ArrayList<>()).stream().map(UserRole::getRoleId).collect(Collectors.toSet())));
    }

    /**
     * 获取所有href权限配置
     *
     * @return 所有权限
     */
    @Cacheable(key = "T(com.ml.jkeep.common.constant.CacheKey).ALL_HREF_PERMISSION_KEY")
    public Set<HrefPermissionVo> hrefPermission() {
        return this.convert(roleLinkRepository.findAll());
    }

    private Set<HrefPermissionVo> convert(List<RoleLink> roleLinkList) {
        if (CollectionUtils.isEmpty(roleLinkList)) {
            return new HashSet<>(0);
        }
        Set<Long> menuIds = new HashSet<>();
        Set<Long> elementIds = new HashSet<>();
        roleLinkList.forEach(roleLink -> {
            menuIds.add(roleLink.getMenuId());
            elementIds.add(roleLink.getElementId());
        });
        List<Menu> menuList = menuRepository.findAllByMenuIdIn(menuIds);
        List<Element> elementList = elementRepository.findAllByElementIdIn(elementIds);
        Set<HrefPermissionVo> vos = new HashSet<>();
        menuList.forEach(menu -> vos.add(new HrefPermissionVo(menu.getCode(), menu.getHref())));
        elementList.forEach(menu -> vos.add(new HrefPermissionVo(menu.getCode(), menu.getHref())));
        return vos;
    }
}