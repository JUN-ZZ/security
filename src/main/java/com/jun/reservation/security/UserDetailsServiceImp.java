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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImp.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        UserDetailsInfo userDetailsInfo;
        if (user==null){
            throw new UsernameNotFoundException("user not found");
        }else {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            List<Role> roles = user.getRoles();
            for(Role role:roles){
                List<Authority> authorities = role.getAuthorities();
                for(Authority authority:authorities){
                    grantedAuthorities.add(new SimpleGrantedAuthority(authority.getCode()));
                }
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleCodeName()));
            }

            userDetailsInfo = new UserDetailsInfo(user.getUsername(),user.getPassword(),grantedAuthorities);

        }

        return userDetailsInfo;
    }




}
