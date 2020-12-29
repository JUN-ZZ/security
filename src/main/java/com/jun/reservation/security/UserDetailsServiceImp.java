package com.jun.reservation.security;

import com.jun.reservation.dao.UserRepository;
import com.jun.reservation.entity.Authority;
import com.jun.reservation.entity.Role;
import com.jun.reservation.entity.User;
import com.jun.reservation.security.UserDetailsInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImp.class);
    private String defaultRolePrefix = "ROLE_";

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        UserDetailsInfo userDetailsInfo;
        if (user==null){
            throw new UsernameNotFoundException("user not found");
        }else {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            Set<Role> roles = user.getRoles();
            for(Role role:roles){
                Set<Authority> authorities = role.getAuthorities();
                for(Authority authority:authorities){
                    if (authority.getCode() != null && !"".equals(authority.getCode())){
                        grantedAuthorities.add(new SimpleGrantedAuthority(authority.getCode()));
                    }
                }
                if(role.getRoleCodeName() != null && !"".equals(role.getRoleCodeName())){
                    grantedAuthorities.add(new SimpleGrantedAuthority(this.defaultRolePrefix + role.getRoleCodeName()));
                }
            }

            userDetailsInfo = new UserDetailsInfo(user.getUsername(),user.getPassword(),grantedAuthorities);

        }

        return userDetailsInfo;
    }




}
