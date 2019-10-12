package com.ml.jkeep.service.system.impl;

import com.ml.jkeep.common.bo.PageBo;
import com.ml.jkeep.common.enums.DFlagEnum;
import com.ml.jkeep.common.enums.SysEnums;
import com.ml.jkeep.common.utils.JpaUtils;
import com.ml.jkeep.common.vo.PageVo;
import com.ml.jkeep.jpa.system.bo.RoleSearchBo;
import com.ml.jkeep.jpa.system.entity.Role;
import com.ml.jkeep.jpa.system.entity.RoleLink;
import com.ml.jkeep.jpa.system.repository.RoleLinkRepository;
import com.ml.jkeep.jpa.system.repository.RoleRepository;
import com.ml.jkeep.service.system.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 角色 - Impl
 *
 * @author 谭良忠
 * @date 2019/7/16 17:42
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleLinkRepository roleLinkRepository;
    private final RoleRepository roleRepository;

    @Override
    public Role save(Role entity) {
        return roleRepository.save(entity);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public List<Role> findAllById(Set<Long> ids) {
        return roleRepository.findAllById(ids);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(DFlagEnum.NORMAL.getCode(), id);
    }

    @Override
    public PageVo<Role> findPage(PageBo<RoleSearchBo> pageBo) {
        Page<Role> page = roleRepository.findAll(convertSearchParam(pageBo.getParam()),
                PageRequest.of(pageBo.getPage() - 1, pageBo.getSize(), Sort.Direction.DESC, Optional.ofNullable(pageBo.getSortableField()).orElse("roleId")));
        return new PageVo<>(pageBo.getPage(), pageBo.getSize(), page.getTotalElements(), page.getTotalPages(), page.getContent());
    }

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
        roleLinkIds.forEach(roleLinkId -> roleLinks.add(new RoleLink(roleLinkId)));
        roleLinkRepository.deleteAll(roleLinks);
    }

    private Specification<Role> convertSearchParam(RoleSearchBo param) {
        if (param == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("dFlag"), DFlagEnum.NORMAL.getCode()));
            if (!StringUtils.isEmpty(param.getKeyword())) {
                String keyword = JpaUtils.addWildcard(param.getKeyword());
                predicates.add(criteriaBuilder.or(criteriaBuilder.like(root.get("name"), keyword),
                        criteriaBuilder.like(root.get("code"), keyword)));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
