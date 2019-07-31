package com.ml.jkeep.jpa.system.entity;

import com.ml.jkeep.common.constant.Common;
import com.ml.jkeep.jpa.system.vo.HrefPermissionVo;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * 用户身份认证信息 - Entity
 *
 * @author 谭良忠
 * @date 2019/6/20 11:00
 */
@Data
public class UserAuth implements UserDetails {

    private Long userId;
    private String username;
    private String password;
    private Set<HrefPermissionVo> hrefPer = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        hrefPer.forEach(per -> authorities.add(new SimpleGrantedAuthority(per.getCode())));
        // 默认角色
        authorities.add(new SimpleGrantedAuthority(Common.ROLE_DEFAULT));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO 帐户是否过期
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO 帐户是否被冻结
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO 帐户密码是否过期
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO 帐号是否可用
        return true;
    }
}
