package com.jun.reservation.controller;

import com.jun.reservation.entity.Shop;
import com.jun.reservation.response.Pagination;
import com.jun.reservation.response.ResponseResult;
import com.jun.reservation.response.Result;
import com.jun.reservation.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author jun
 * @date 2021/1/1
 */
@RestController
public class ShopController {

    @Autowired
    private ShopService shopService;

    //    @PreAuthorize("hasAuthority('shop:get')")
    @RequestMapping(value = "/shop",method = RequestMethod.GET)
    @ResponseBody
    public Result findShops(@RequestParam int pageNum, @RequestParam int pageSize){
        Pagination pagination = null;

        try {
            pagination = shopService.findShops(pageNum,pageSize);

        }catch (Exception e){
            return ResponseResult.failure(e.getMessage());
        }
        return ResponseResult.success(pagination);
    }



    @RequestMapping(value = "/shop",method = RequestMethod.POST)
    @ResponseBody
    public Result saveShop(@RequestBody Shop shop){
        Shop shop1 = null;
        try {
            shop1 = shopService.saveShop(shop);
        }catch (Exception e){
            return ResponseResult.failure(e.getMessage());
        }

        return ResponseResult.success(shop1);
    }


    @RequestMapping(value = "/shop",method = RequestMethod.PUT)
    @ResponseBody
    public Result updateShop(@RequestBody Shop shop){
        Shop shop1 = null;
        try {
            shop1 = shopService.updateShop(shop);
        }catch (Exception e){
            return ResponseResult.failure(e.getMessage());
        }
        return ResponseResult.success(shop1);
    }


}
