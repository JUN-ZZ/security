package com.jun.reservation.controller;

import com.jun.reservation.response.Pagination;
import com.jun.reservation.response.ResponseResult;
import com.jun.reservation.response.Result;
import com.jun.reservation.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author jun
 * @date 2021/1/1
 *
 */
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    //    @PreAuthorize("hasAuthority('role:get')")
    @RequestMapping(value = "/role",method = RequestMethod.GET)
    @ResponseBody
    public Result findUser(@RequestParam int pageNum, @RequestParam int pageSize){
        Pagination pagination = null;

        try {
            pagination = roleService.findRoles(pageNum,pageSize);

        }catch (Exception e){
            return ResponseResult.failure(e.getMessage());
        }
        return ResponseResult.success(pagination);
    }


}
