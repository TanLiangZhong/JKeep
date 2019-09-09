package com.ml.jkeep.service.system.impl;

import com.ml.jkeep.common.enums.DFlagEnum;
import com.ml.jkeep.common.enums.SysEnums;
import com.ml.jkeep.jpa.system.entity.Menu;
import com.ml.jkeep.jpa.system.entity.RoleLink;
import com.ml.jkeep.jpa.system.entity.UserRole;
import com.ml.jkeep.jpa.system.repository.MenuRepository;
import com.ml.jkeep.jpa.system.repository.RoleLinkRepository;
import com.ml.jkeep.jpa.system.repository.UserRoleRepository;
import com.ml.jkeep.jpa.system.vo.MenuVo;
import com.ml.jkeep.service.system.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Date: 2019/8/15-15:10
 *
 * @author meng
 * Description:
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final UserRoleRepository userRoleRepository;
    private final RoleLinkRepository roleLinkRepository;
    private final MenuRepository menuRepository;

    @Override
    public List<MenuVo> getTree(Long userId) {
        if (userId == null) {
            return Collections.emptyList();
        }
        Set<Long> roleIds = userRoleRepository.findAllByUserId(userId).stream().map(UserRole::getRoleId).collect(Collectors.toSet());
        Set<Long> menuIds = roleLinkRepository.findAllByRoleIdInAndType(roleIds, SysEnums.RoleLinkType.MENU.getCode()).stream().map(RoleLink::getMenuId).collect(Collectors.toSet());
        List<Menu> menus = menuRepository.findAllByMenuIdInAndDFlag(menuIds, DFlagEnum.NORMAL.getCode());
        List<MenuVo> vos = new ArrayList<>();
        menus.forEach(menu -> {
            MenuVo vo = new MenuVo();
            BeanUtils.copyProperties(menu, vo);
            vos.add(vo);
        });
        TreeMap<Long, List<MenuVo>> menuMap = new TreeMap<>();
        vos.forEach(menu -> {
            Long menuId = menu.getMenuId();
            Long parentId = menu.getParentId();
            if (!menuMap.containsKey(menuId)) {
                menuMap.put(menuId, new ArrayList<>());
            }
            menu.setChildren(menuMap.get(menuId));
            if (!menuMap.containsKey(parentId)) {
                menuMap.put(parentId, new ArrayList<>());
            }
            menuMap.get(parentId).add(menu);
        });
        return Optional.ofNullable(menuMap.get(0L)).orElse(Collections.emptyList());
    }

}
