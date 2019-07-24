package com.ml.jkeep.internal.system;

import com.ml.jkeep.common.controller.BaseController;
import com.ml.jkeep.internal.auth.JKeepSecurityContextHolder;
import com.ml.jkeep.service.system.impl.HrefPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 主页 - Controller
 *
 * @author liangzhong
 * @date 2019/6/20 9:43
 */
@Slf4j
@Controller
@RequestMapping
public class IndexController extends BaseController {

    @Autowired
    private HrefPermissionService hrefPermissionService;

    @GetMapping("/")
    public String index() {
        log.info("User:{}", JKeepSecurityContextHolder.getUserInfo().getUsername());
        hrefPermissionService.hrefPermission(1L);
        hrefPermissionService.hrefPermission();
        return "views/index";
    }

    @ResponseBody
    @GetMapping("/hello")
    public String hello(String name) {
        hrefPermissionService.hrefPermission(1L);
        hrefPermissionService.hrefPermission();
        return "Hello " + name;
    }


}
