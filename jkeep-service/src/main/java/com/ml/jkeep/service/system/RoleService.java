package com.ml.jkeep.service.system;

import com.ml.jkeep.common.service.BaseService;
import com.ml.jkeep.jpa.system.entity.sys.Role;

import java.util.List;

/**
 * 角色 - Service
 *
 * @author 谭良忠
 * @date 2019/7/16 17:42
 */
public interface RoleService extends BaseService<Role, Long> {

    /**
     * 批量添加 角色关联
     *
     * @param roleId       角色Id
     * @param linkIds      关联Id (菜单Id or 页面元素Id)
     * @param roleLinkType 类型
     * @return
     * @author 谭良忠
     * @date 2019/7/23 11:05
     */
    int batchAddRoleLink(Long roleId, List<Long> linkIds, Byte roleLinkType);

    /**
     * 批量删除 角色关联
     *
     * @param roleLinkIds 角色关联表主键
     * @author 谭良忠
     * @date 2019/7/23 11:05
     */
    void batchDeleteRoleLink(List<Long> roleLinkIds);

}
