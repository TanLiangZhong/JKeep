package com.ml.jkeep.internal.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页 - Controller
 *
 * @author liangzhong
 * @date 2019/6/20 9:43
 */
@Controller
@RequestMapping
public class IndexController {

    @GetMapping("index")
    public String index() {
        return "views/system/index";
    }

}
