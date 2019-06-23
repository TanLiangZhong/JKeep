package com.ml.jkeep.internal.auth;

import com.ml.jkeep.service.system.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 过滤器调用安全元数据源
 *
 * @author 谭良忠
 * @date 2019/6/20 11:48
 */
@Slf4j
@Component
public class JKeepSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private MenuService menuService;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
//        FilterInvocation filterInvocation = (FilterInvocation) object;
//        String requestUrl = filterInvocation.getRequestUrl();
//        log.info("requestUrl : {}", requestUrl);
//        if (antPathMatcher.match("/login.html", requestUrl)) {
//            return SecurityConfig.createList("ROLE_ANONYMOUS");
//        }
//        // TODO 赋予当前 URL 可以访问的角色
//        List<Menu> allMenu = menuService.getAllMenu();
//        for (Menu menu : allMenu) {
//            if (antPathMatcher.match(menu.getPath(), requestUrl)) {
//                // TODO
//            }
//        }
        return new ArrayList<>(0);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
