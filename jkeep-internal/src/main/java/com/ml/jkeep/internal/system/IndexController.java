package com.ml.jkeep.internal.system;

import com.ml.jkeep.internal.auth.JKeepSecurityContextHolder;
import com.ml.jkeep.service.system.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * 主页 - Controller
 *
 * @author liangzhong
 * @date 2019/6/20 9:43
 */
@Slf4j
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public String index(Map<String, Object> map) {
        map.put("menu", menuService.getTree(JKeepSecurityContextHolder.getUserId()));
        return "views/index";
    }

}
