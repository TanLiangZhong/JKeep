package com.ml.jkeep.service.system;

import com.ml.jkeep.common.bo.PageBo;
import com.ml.jkeep.common.vo.PageVo;
import com.ml.jkeep.jpa.system.entity.Role;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 角色 - Service
 *
 * @author 谭良忠
 * @date 2019/7/16 17:42
 */
public interface RoleService {

    /**
     * 保存
     *
     * @param entity 角色实体
     * @return 所保存实体
     */
    @Transactional(rollbackFor = Throwable.class)
    Role save(Role entity);

    /**
     * 根据角色Id查询
     *
     * @param id 角色Id
     * @return id对应的实体
     */
    Optional<Role> findById(Long id);

    /**
     * 返回具有给定ID的所有类型的实例。
     *
     * @param ids 角色Ids
     * @return ids所对应的实体
     */
    List<Role> findAllById(Set<Long> ids);

    /**
     * 根据Id删除, 逻辑删除
     *
     * @param id 角色Id
     */
    void deleteById(Long id);

    /**
     * 分页查询
     *
     * @param pageBo 分页对象
     * @return {@link PageVo}
     */
    PageVo<Role> findPage(PageBo<Role> pageBo);

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
    @Transactional(rollbackFor = Throwable.class)
    int batchAddRoleLink(Long roleId, List<Long> linkIds, Byte roleLinkType);

    /**
     * 批量删除 角色关联
     *
     * @param roleLinkIds 角色关联表主键
     * @author 谭良忠
     * @date 2019/7/23 11:05
     */
    @Transactional(rollbackFor = Throwable.class)
    void batchDeleteRoleLink(List<Long> roleLinkIds);


}
