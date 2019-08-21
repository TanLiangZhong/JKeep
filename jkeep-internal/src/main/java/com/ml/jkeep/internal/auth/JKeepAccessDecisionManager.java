package com.ml.jkeep.internal.auth;

import com.alibaba.fastjson.JSON;
import com.ml.jkeep.common.constant.Common;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 鉴权策略
 *
 * @author 谭良忠
 * @date 2019/6/20 13:24
 */
@Slf4j
@Component
public class JKeepAccessDecisionManager implements AccessDecisionManager {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        String requestUrl = filterInvocation.getRequestUrl();
        log.info("requestUrl : {} , configAttributes: {} , authentication: {}", requestUrl, JSON.toJSONString(configAttributes), JSON.toJSONString(authentication));
        if (!antPathMatcher.match(Common.LOGIN_PAGE_URL, requestUrl)) {
            if (authentication instanceof AnonymousAuthenticationToken) {
                throw new BadCredentialsException("未登录");
            }
        } else {
            // 登陆页面无需鉴权可访问
            return;
        }
        // 验证用户是否有权访问当前地址
        // configAttributes 可以访问 url 的角色
        // authentication.getAuthorities() 用户所有角色
        List<GrantedAuthority> isPermission = new ArrayList<>();
        configAttributes.forEach(configAttribute -> isPermission.addAll(authentication.getAuthorities().stream().filter(authority -> authority.getAuthority().equals(configAttribute.getAttribute())).collect(Collectors.toList())));
        if (isPermission.isEmpty()) {
            log.info("用户权限不足, 无法访问!");
            throw new AccessDeniedException("用户权限不足, 无法访问!");
        }

    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
