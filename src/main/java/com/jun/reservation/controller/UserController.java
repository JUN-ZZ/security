package com.jun.reservation.controller;

import com.jun.reservation.dao.UserRepository;
import com.jun.reservation.security.UserDetailsInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String home(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        Map map = httpServletRequest.getParameterMap();
        logger.info(String.format("home ", String.valueOf(200)));
        logger.info(map.toString());
        return "..";
    }
    
    @RequestMapping(value = "/loginSuccess",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String loginSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        logger.info(String.format("loginSuccess ", String.valueOf(200)));
        logger.info(authentication.getAuthorities().toString());
        logger.info(authentication.getDetails().toString());
        logger.info(authentication.getPrincipal().toString());

        return "loginSuccess";
    }

    @RequestMapping(value = "/Mylogin",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String Mylogin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        logger.info(String.format("Mylogin 200"));
        logger.info(authentication.getAuthorities().toString());
        logger.info(authentication.getDetails().toString());
        logger.info(authentication.getPrincipal().toString());

        return "Mylogin";
    }


    @PreAuthorize("hasAnyAuthority('VIP2','ROLE_V')")
    @RequestMapping(value = "/h",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String s(){
        logger.info(String.format("hhhhh", String.valueOf(200)));
        return "..";
    }

    @RequestMapping(value = "/fail",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String fail(){
        logger.info(String.format("fail", String.valueOf(200)));
        return "fail";
    }

    @PreAuthorize("hasAnyAuthority('all')")
    @RequestMapping(value = "/auth",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String auth(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        logger.info(String.format("auth 200"));
        logger.info(authentication.getAuthorities().toString());
        logger.info(authentication.getDetails().toString());
        logger.info(authentication.getPrincipal().toString());
        logger.info(String.format("auth {}", String.valueOf(200)));
        return "auth";
    }

    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/auth2",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String auth2(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        UserDetailsInfo userDetailInfo = (UserDetailsInfo) authentication.getPrincipal();

        logger.info(String.format("auth2 200"));
        logger.info(authentication.getAuthorities().toString());
        logger.info(authentication.getDetails().toString());
        logger.info(authentication.getPrincipal().toString());
        logger.info(String.format("auth {}", String.valueOf(200)));
        logger.info(userDetailInfo.getAuthorities().toString(),userDetailInfo.getUsername());
        logger.info(userDetailInfo.getUsername());
        logger.info(userDetailInfo.getPassword());

        return "auth";
    }

    @PreAuthorize("hasAuthority('auth3')")
    @RequestMapping(value = "/auth2",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String auth233(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        UserDetailsInfo userDetailInfo = (UserDetailsInfo) authentication.getPrincipal();

        logger.info(String.format("auth2 200"));
        logger.info(authentication.getAuthorities().toString());
        logger.info(authentication.getDetails().toString());
        logger.info(authentication.getPrincipal().toString());
        logger.info(String.format("auth {}", String.valueOf(200)));
        logger.info(userDetailInfo.getAuthorities().toString(),userDetailInfo.getUsername());
        logger.info(userDetailInfo.getUsername());
        logger.info(userDetailInfo.getPassword());

        return "auth";
    }


}
