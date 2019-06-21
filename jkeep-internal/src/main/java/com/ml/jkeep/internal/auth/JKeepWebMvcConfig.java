package com.ml.jkeep.internal.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author liangzhong
 * @date 2019/6/22 0:39
 */
@Configuration
public class JKeepWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        TODO 后续添加 Login 静态页面
//        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/404").setViewName("/views/error/404");
        registry.addViewController("/401").setViewName("/views/error/401");
    }

}
