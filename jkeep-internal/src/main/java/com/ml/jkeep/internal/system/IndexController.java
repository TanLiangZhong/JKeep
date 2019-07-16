package com.ml.jkeep.internal.system;

import com.ml.jkeep.common.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/")
    public String index() {
        return "views/index";
    }

}
