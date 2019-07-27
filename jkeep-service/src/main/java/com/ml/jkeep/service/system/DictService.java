package com.ml.jkeep.service.system;

import com.ml.jkeep.common.service.BaseService;
import com.ml.jkeep.jpa.system.entity.sys.Dict;
import com.ml.jkeep.jpa.system.entity.sys.DictLst;

import java.util.List;

/**
 * 数据字典 - Service
 *
 * @author 谭良忠
 * @date 2019/7/27 18:03
 */
public interface DictService extends BaseService<Dict, Long> {

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
