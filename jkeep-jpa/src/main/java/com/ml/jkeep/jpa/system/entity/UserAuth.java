package com.ml.jkeep.jpa.system.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户身份认证信息 - Entity
 *
 * @author 谭良忠
 * @date 2019/6/20 11:00
 */
@Data
public class UserAuth implements UserDetails {

    private String username;
    private String password;
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach( role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO
        return true;
    }
}