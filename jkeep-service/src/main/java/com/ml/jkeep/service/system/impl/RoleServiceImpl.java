package com.ml.jkeep.service.system.impl;

import com.ml.jkeep.common.enums.SysEnums;
import com.ml.jkeep.common.service.impl.BaseServiceImpl;
import com.ml.jkeep.jpa.system.entity.sys.Role;
import com.ml.jkeep.jpa.system.entity.sys.RoleLink;
import com.ml.jkeep.jpa.system.repository.RoleLinkRepository;
import com.ml.jkeep.jpa.system.repository.RoleRepository;
import com.ml.jkeep.service.system.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色 - Impl
 *
 * @author 谭良忠
 * @date 2019/7/16 17:42
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleRepository, Role, Long> implements RoleService {

    @Autowired
    private RoleLinkRepository roleLinkRepository;

    @Override
    public int batchAddRoleLink(Long roleId, List<Long> linkIds, Byte roleLinkType) {
        List<RoleLink> roleLinks = new ArrayList<>();
        if (SysEnums.RoleLinkType.MENU.getCode().equals(roleLinkType)) {
            linkIds.forEach(linkId -> roleLinks.add(new RoleLink(roleId, linkId, null, roleLinkType)));
        } else {
            linkIds.forEach(linkId -> roleLinks.add(new RoleLink(roleId, null, linkId, roleLinkType)));
        }
        return roleLinkRepository.saveAll(roleLinks).size();
    }

    @Override
    public void batchDeleteRoleLink(List<Long> roleLinkIds) {
        List<RoleLink> roleLinks = new ArrayList<>();
        roleLinkIds.forEach(roleLinkId ->roleLinks.add(new RoleLink(roleLinkId)));
        roleLinkRepository.deleteAll(roleLinks);
    }
}
