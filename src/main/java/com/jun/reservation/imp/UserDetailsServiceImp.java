package com.jun.reservation.imp;

import com.jun.reservation.entity.UserDetailInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImp.class);

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        if ("zxj".equals(s) ){
            String pwd = "123456";
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_admin");
            SimpleGrantedAuthority simpleGrantedAuthority1 = new SimpleGrantedAuthority("admin");
            SimpleGrantedAuthority simpleGrantedAuthority2 = new SimpleGrantedAuthority("all");
            grantedAuthorities.add(simpleGrantedAuthority);
            grantedAuthorities.add(simpleGrantedAuthority1);
            grantedAuthorities.add(simpleGrantedAuthority2);

            userDetails = new UserDetailInfo(s,bCryptPasswordEncoder.encode(pwd),grantedAuthorities);

        }
        else {
            String username = "zhangsan";
            String pwd = "123456";

            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_V");
            SimpleGrantedAuthority simpleGrantedAuthority1 = new SimpleGrantedAuthority("VIP2");
            grantedAuthorities.add(simpleGrantedAuthority);
            grantedAuthorities.add(simpleGrantedAuthority1);

            userDetails = new UserDetailInfo(username,bCryptPasswordEncoder.encode(pwd),grantedAuthorities);
            logger.info("UserDetailServiceImp loadUserByUsername ",username);

        }

        return userDetails;
    }




}
