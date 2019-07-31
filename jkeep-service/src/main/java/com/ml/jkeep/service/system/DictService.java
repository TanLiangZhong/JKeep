package com.ml.jkeep.service.system;

import com.ml.jkeep.common.bo.PageBo;
import com.ml.jkeep.common.vo.PageVo;
import com.ml.jkeep.jpa.system.entity.Dict;
import com.ml.jkeep.jpa.system.entity.DictLst;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 数据字典 - Service
 *
 * @author 谭良忠
 * @date 2019/7/27 18:03
 */
public interface DictService {

    /**
     * 保存
     *
     * @param entity 角色实体
     * @return 所保存实体
     */
    Dict save(Dict entity);

    /**
     * 根据角色Id查询
     *
     * @param id 角色Id
     * @return id对应的实体
     */
    Optional<Dict> findById(Long id);

    /**
     * 返回具有给定ID的所有类型的实例。
     *
     * @param ids 角色Ids
     * @return ids所对应的实体
     */
    List<Dict> findAllById(Set<Long> ids);

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
    PageVo<Dict> findPage(PageBo<Dict> pageBo);

    /**
     * 查询数据字典列表
     *
     * @param dictId
     * @return
     */
    List<DictLst> findDictLst(Long dictId);

    /**
     * 保存数据字典列表
     *
     * @param dictLst
     * @return
     */
    DictLst saveDictLst(DictLst dictLst);
}
