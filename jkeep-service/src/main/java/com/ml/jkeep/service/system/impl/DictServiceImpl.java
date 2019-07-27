package com.ml.jkeep.service.system.impl;

import com.ml.jkeep.common.service.impl.BaseServiceImpl;
import com.ml.jkeep.jpa.system.entity.sys.Dict;
import com.ml.jkeep.jpa.system.entity.sys.DictLst;
import com.ml.jkeep.jpa.system.repository.DictLstRepository;
import com.ml.jkeep.jpa.system.repository.DictRepository;
import com.ml.jkeep.service.system.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色 - Impl
 *
 * @author 谭良忠
 * @date 2019/7/27 18:03
 */
@Service
public class DictServiceImpl extends BaseServiceImpl<DictRepository, Dict, Long> implements DictService {

    @Autowired
    private DictLstRepository dictLstRepository;

    @Override
    public List<DictLst> findDictLst(Long dictId) {
        return dictLstRepository.findAllByDictId(dictId);
    }

    @Override
    public DictLst saveDictLst(DictLst dictLst) {
        return dictLstRepository.save(dictLst);
    }
}
