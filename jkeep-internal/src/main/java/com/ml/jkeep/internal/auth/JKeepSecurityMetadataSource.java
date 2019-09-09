package com.ml.jkeep.internal.auth;

import com.alibaba.fastjson.JSON;
import com.ml.jkeep.common.constant.Common;
import com.ml.jkeep.jpa.system.vo.HrefPermissionVo;
import com.ml.jkeep.service.system.HrefPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
    private HrefPermissionService hrefPermissionService;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        String requestUrl = filterInvocation.getRequestUrl();
        Set<HrefPermissionVo> allPer = hrefPermissionService.hrefPermission();
        Set<String> roles = new HashSet<>();
        allPer.forEach(per -> {
            if (antPathMatcher.match(Optional.ofNullable(per.getHref()).orElse(""), requestUrl)) {
                roles.add(per.getCode());
            }
        });
        if (!CollectionUtils.isEmpty(roles)) {
            log.info("JKeepSecurityMetadataSource, requestUrl : {}, roles: {}", requestUrl, JSON.toJSONString(roles));
            return SecurityConfig.createList(roles.toArray(new String[0]));
        }
        log.info("JKeepSecurityMetadataSource, requestUrl : {}, roles: {}", requestUrl, Common.ROLE_DEFAULT);
        // 当 url 没有配置权限控制时, 赋予默认角色可访问.即所有用户都可访问
        return SecurityConfig.createList(Common.ROLE_DEFAULT);
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
