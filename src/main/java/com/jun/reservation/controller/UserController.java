package com.jun.reservation.controller;

import com.jun.reservation.DTO.UserDTO;
import com.jun.reservation.dao.UserRepository;
import com.jun.reservation.entity.User;
import com.jun.reservation.response.Pagination;
import com.jun.reservation.response.ResponseResult;
import com.jun.reservation.response.Result;
import com.jun.reservation.security.UserDetailsInfo;
import com.jun.reservation.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Result home(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        Map map = httpServletRequest.getParameterMap();
        logger.info(map.toString());
        return ResponseResult.success("welcome to home page");
    }
    
    @RequestMapping(value = "/loginSuccess",method = {RequestMethod.POST})
    @ResponseBody
    public Result loginSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        logger.info(String.format("loginSuccess ", String.valueOf(200)));
        logger.info(authentication.getAuthorities().toString());
        logger.info(authentication.getDetails().toString());
        logger.info(authentication.getPrincipal().toString());

        return ResponseResult.success("loginSuccess");
    }

//  已经有自定义的filter实现了拦截登录认证的请求了 不需要在重新写
//    @RequestMapping(value = "/login",method = RequestMethod.POST)
//    @ResponseBody
//    public Result login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
////        登录成功之后从security 取出 context
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        Authentication authentication = securityContext.getAuthentication();
//
//        UserDetailsInfo userDetailsInfo = (UserDetailsInfo) authentication.getPrincipal();
//
//        return ResponseResult.success(userDetailsInfo);
//    }


    @RequestMapping(value = "/fail",method = {RequestMethod.POST})
    @ResponseBody
    public Result<Object> fail(){
        logger.info(String.format("fail", String.valueOf(200)));
        return ResponseResult.failure();
    }

//    @PreAuthorize("hasAuthority('user:add')")
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    @ResponseBody
    public Result saveUser(@RequestBody User user){
        User user1 = null;
        try {
            user1 = userService.saveUser(user);
        }catch (Exception e){
            return ResponseResult.failure(e.getMessage());
        }

        return ResponseResult.success(user1);
    }

//    @PreAuthorize("hasAuthority('user:edit')")
    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    @ResponseBody
    public Result updateUser(@RequestBody UserDTO userDTO){
        User u;
        try {
           u = userService.updateUser(userDTO);
        }catch (Exception e){
            return ResponseResult.failure(e.getMessage());
        }
        return ResponseResult.success(u);
    }

    @PreAuthorize("hasAuthority('user:delete')")
    @RequestMapping(value = "/user",method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteUser(@RequestParam Long id){
        try {
            userService.deleteUserById(id);
        }catch (Exception e){
            return ResponseResult.failure(e.getMessage());
        }
        return ResponseResult.success();
    }


//    @PreAuthorize("hasAuthority('user:get')")
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    @ResponseBody
    public Result findUser(@RequestParam int pageNum, @RequestParam int pageSize){
        Pagination pagination = null;

        try {
            pagination = userService.findUsers(pageNum,pageSize);

        }catch (Exception e){
            return ResponseResult.failure(e.getMessage());
        }
        return ResponseResult.success(pagination);
    }

    //    @PreAuthorize("hasAuthority('user:get')")
    @RequestMapping(value = "/userAll",method = RequestMethod.GET)
    @ResponseBody
    public Result findAllUsers(){
        Pagination pagination = null;

        try {
            pagination = userService.findAllUsers();

        }catch (Exception e){
            return ResponseResult.failure(e.getMessage());
        }
        return ResponseResult.success(pagination.getDataList());
    }


    @RequestMapping(value = "/user/password",method = RequestMethod.PUT)
    @ResponseBody
    public Result updateUserOfUsernameAndPwd(@RequestParam(name = "id") Long id,@RequestParam String username,@RequestParam String password){
        Boolean u;
        try {
            u = userService.updateUserById(id, username, password);
        }catch (Exception e){
            return ResponseResult.failure(e.getMessage());
        }

        if(!u){
            return ResponseResult.failure();
        }

        return ResponseResult.success(u);
    }


}
