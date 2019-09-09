package com.ml.jkeep.internal.auth;

import com.alibaba.fastjson.JSONObject;
import com.ml.jkeep.jpa.system.entity.UserAuth;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

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
        return getDetails() == null ? null : getDetails().getString("sessionId");
    }

    /**
     * 存储有关身份验证请求的其他详细信息 IP地址 , sessionId 等
     *
     * @return {"remoteAddress":"0:0:0:0:0:0:0:1","sessionId":"DECEC3BE2FC3F2D2002624E155939F35"}
     */
    public static JSONObject getDetails() {
        return getAuthentication() == null ? null : (JSONObject) getAuthentication().getDetails();
    }

    /**
     * 获得用户信息
     *
     * @return 用户信息
     */
    public static UserAuth getUserInfo() {
        return getAuthentication() == null ? null : (UserAuth) getAuthentication().getPrincipal();
    }

    /**
     * 是否已经登陆
     *
     * @return 是否已经登陆
     */
    public static boolean isAuthenticated() {
        return getAuthentication() != null && getAuthentication().isAuthenticated();
    }

    /**
     * 获得用户Id
     *
     * @return userId
     */
    public static Long getUserId() {
        return Objects.requireNonNull(getUserInfo()).getUserId();
    }

    private static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
