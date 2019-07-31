package com.ml.jkeep.service.system.impl;

import com.ml.jkeep.common.bo.PageBo;
import com.ml.jkeep.common.vo.PageVo;
import com.ml.jkeep.jpa.system.entity.Dict;
import com.ml.jkeep.jpa.system.entity.DictLst;
import com.ml.jkeep.jpa.system.repository.DictLstRepository;
import com.ml.jkeep.service.system.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 角色 - Impl
 *
 * @author 谭良忠
 * @date 2019/7/27 18:03
 */
@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictLstRepository dictLstRepository;

    @Override
    public Dict save(Dict entity) {
        return null;
    }

    @Override
    public Optional<Dict> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Dict> findAllById(Set<Long> ids) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public PageVo<Dict> findPage(PageBo<Dict> pageBo) {
        return null;
    }

    @Override
    public List<DictLst> findDictLst(Long dictId) {
        return dictLstRepository.findAllByDictId(dictId);
    }

    @Override
    public DictLst saveDictLst(DictLst dictLst) {
        return dictLstRepository.save(dictLst);
    }
}
