package com.ml.jkeep.internal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring Mvc 配置
 *
 * @author liangzhong
 * @date 2019/6/22 0:39
 */
@Configuration
public class JKeepWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login.html").setViewName("/views/system/login");
        registry.addViewController("/404.html").setViewName("/views/error/404");
        registry.addViewController("/401.html").setViewName("/views/error/401");
    }

}
