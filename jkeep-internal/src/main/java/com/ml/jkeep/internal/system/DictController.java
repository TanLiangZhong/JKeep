package com.ml.jkeep.internal.system;

import com.ml.jkeep.common.bo.PageBo;
import com.ml.jkeep.common.vo.RestVo;
import com.ml.jkeep.jpa.system.entity.Dict;
import com.ml.jkeep.jpa.system.entity.DictLst;
import com.ml.jkeep.service.system.DictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据字典 - Controller
 *
 * @author liangzhong
 * @date 2019/7/27 18:03
 */
@Slf4j
@RestController
@RequestMapping("dict")
public class DictController {

    @Autowired
    private DictService dictService;

    @GetMapping("find/{id}")
    public RestVo<Dict> findById(@PathVariable Long id) {
        return RestVo.SUCCESS(dictService.findById(id).orElse(null));
    }

    @PostMapping("save")
    public RestVo save(@RequestBody Dict dict) {
        dictService.save(dict);
        return RestVo.SUCCESS();
    }

    @DeleteMapping("delete/{id}")
    public RestVo deleteById(@PathVariable Long id) {
        dictService.deleteById(id);
        return RestVo.SUCCESS();
    }

    @PostMapping("find/page")
    public RestVo<Page<Dict>> findPage(@RequestBody PageBo<Dict> pageBo) {
        return RestVo.SUCCESS(dictService.findPage(pageBo));
    }

    @GetMapping("find/lst/{dictId}")
    public RestVo<List<DictLst>> findDictLst(@PathVariable Long dictId) {
        return RestVo.SUCCESS(dictService.findDictLst(dictId));
    }

    @PostMapping("save/lst")
    public RestVo<List<DictLst>> saveDictLst(@RequestBody DictLst dictLst) {
        return RestVo.SUCCESS(dictService.saveDictLst(dictLst));
    }

    @DeleteMapping("delete/lst/{dictLstId}")
    public RestVo<List<DictLst>> saveDictLst(@PathVariable Long dictLstId) {
        dictService.deleteDictLst(dictLstId);
        return RestVo.SUCCESS();
    }
}
