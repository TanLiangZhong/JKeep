package com.ml.jkeep.internal.system;

import com.ml.jkeep.common.controller.BaseController;
import com.ml.jkeep.common.vo.RestVo;
import com.ml.jkeep.jpa.system.entity.sys.Role;
import com.ml.jkeep.service.system.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 角色 - 控制器
 *
 * @author 谭良忠
 * @date 2019/7/17 9:53
 */
@RestController
@RequestMapping("system/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @GetMapping("find/{id}")
    public RestVo findById(@PathVariable Long id) {
        return RestVo.SUCCESS(roleService.findById(id).orElse(null));
    }

    @PostMapping("save")
    public RestVo save(@RequestBody Role role) {
        roleService.save(role);
        return RestVo.SUCCESS();
    }

    @PostMapping("delete/{id}")
    public RestVo deleteById(@PathVariable Long id) {
        roleService.deleteById(id);
        return RestVo.SUCCESS();
    }
}
