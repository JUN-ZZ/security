package com.jun.reservation.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * spring security 的userDetail 认证 授权的所有信息保存在这里了
 * 直接继承spring security 的内置 User实现类
*  */
public class UserDetailsInfo extends User {

    public UserDetailsInfo(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, true, true, true, true, authorities);
    }

    public UserDetailsInfo(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

}
