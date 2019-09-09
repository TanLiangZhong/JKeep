package com.ml.jkeep.internal.system;

import com.ml.jkeep.common.bo.PageBo;
import com.ml.jkeep.common.vo.RestVo;
import com.ml.jkeep.jpa.system.bo.RoleSearchBo;
import com.ml.jkeep.jpa.system.entity.Role;
import com.ml.jkeep.service.system.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色 - 控制器
 *
 * @author 谭良忠
 * @date 2019/7/17 9:53
 */
@Controller
@RequestMapping("system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("page.html")
    public String html() {
        return "views/system/role";
    }

    @ResponseBody
    @GetMapping("find/{id}")
    public RestVo findById(@PathVariable Long id) {
        return RestVo.SUCCESS(roleService.findById(id).orElse(null));
    }

    @ResponseBody
    @PostMapping("find/page")
    public RestVo findPage(@RequestBody PageBo<RoleSearchBo> pageBo) {
        return RestVo.SUCCESS(roleService.findPage(pageBo));
    }

    @ResponseBody
    @PostMapping("save")
    public RestVo save(@RequestBody Role role) {
        roleService.save(role);
        return RestVo.SUCCESS();
    }

    @ResponseBody
    @DeleteMapping("delete/{id}")
    public RestVo deleteById(@PathVariable Long id) {
        roleService.deleteById(id);
        return RestVo.SUCCESS();
    }

    @ResponseBody
    @PostMapping("add/link")
    public RestVo batchAddRoleLink(Long roleId, List<Long> linkIds, Byte roleLinkType) {
        return RestVo.SUCCESS(roleService.batchAddRoleLink(roleId, linkIds, roleLinkType));
    }

    @ResponseBody
    @DeleteMapping("delete/link")
    public RestVo batchDeleteRoleLink(@RequestBody List<Long> roleLinkIds) {
        roleService.batchDeleteRoleLink(roleLinkIds);
        return RestVo.SUCCESS();
    }


}
