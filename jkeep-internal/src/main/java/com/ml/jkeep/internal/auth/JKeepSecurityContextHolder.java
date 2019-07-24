package com.ml.jkeep.internal.auth;

import com.alibaba.fastjson.JSONObject;
import com.ml.jkeep.jpa.system.entity.sys.UserAuth;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 令牌持有者(身份认证相关信息)
 *
 * @author 谭良忠
 * @date 2019/7/17 15:10
 */
public class JKeepSecurityContextHolder {

    /**
     * 获取 sessionId
     *
     * @return sessionId
     */
    public static String getSessionId() {
        return getDetails().getString("sessionId");
    }

    /**
     * 存储有关身份验证请求的其他详细信息 IP地址 , sessionId 等
     *
     * @return {"remoteAddress":"0:0:0:0:0:0:0:1","sessionId":"DECEC3BE2FC3F2D2002624E155939F35"}
     */
    public static JSONObject getDetails() {
        return (JSONObject) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }

    /**
     * 获得用户信息
     *
     * @return 用户信息
     */
    public static UserAuth getUserInfo() {
        return (UserAuth) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 是否已经登陆
     *
     * @return 是否已经登陆
     */
    public static Object isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }

    /**
     * 获得用户所持有的角色
     *
     * @return 角色集合
     */
    public static Set<String> getRoles() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getAuthorities()).orElse(new ArrayList<>()).stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
    }
}
