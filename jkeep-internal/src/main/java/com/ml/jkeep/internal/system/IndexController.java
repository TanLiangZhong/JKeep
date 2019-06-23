package com.ml.jkeep.internal.system;

import lombok.extern.slf4j.Slf4j;
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
@RequestMapping
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "views/index";
    }

    @GetMapping("index")
    public String index(Map<String, String> map) {
        map.put("hello", "Hello World !!!");
        return "views/index";
    }

}
