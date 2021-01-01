package com.jun.reservation.controller;

import com.jun.reservation.entity.Goods;
import com.jun.reservation.response.Pagination;
import com.jun.reservation.response.ResponseResult;
import com.jun.reservation.response.Result;
import com.jun.reservation.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author jun
 * @date 2021/1/1
 */
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;


    @RequestMapping(value = "/goods",method = RequestMethod.GET)
    @ResponseBody
    public Result findGoods(@RequestParam int pageNum, @RequestParam int pageSize){
        Pagination pagination = null;

        try {
            pagination = goodsService.findGoods(pageNum,pageSize);

        }catch (Exception e){
            return ResponseResult.failure(e.getMessage());
        }
        return ResponseResult.success(pagination);
    }



    @RequestMapping(value = "/goods",method = RequestMethod.POST)
    @ResponseBody
    public Result saveGoods(@RequestBody Goods goods){
        Goods goods1 = null;
        try {
            goods1 = goodsService.saveGoods(goods);
        }catch (Exception e){
            return ResponseResult.failure(e.getMessage());
        }

        return ResponseResult.success(goods1);
    }


    @RequestMapping(value = "/goods",method = RequestMethod.PUT)
    @ResponseBody
    public Result updateShop(@RequestBody Goods goods){
        Goods goods1 = null;
        try {
            goods1 = goodsService.updateGoods(goods);
        }catch (Exception e){
            return ResponseResult.failure(e.getMessage());
        }
        return ResponseResult.success(goods1);
    }


    @RequestMapping(value = "/goods/img",method = RequestMethod.PUT)
    @ResponseBody
    public Result updateShopImg(@RequestBody Map<String,Object> map){
//七牛云

        Goods goods1 = null;
        try {

            Long id = Long.valueOf((Long) map.get("id"));
            String img = (String) map.get("imgUrl");

            goods1 = goodsService.updateGoodsImg(id,img);

        }catch (Exception e){
            return ResponseResult.failure(e.getMessage());
        }


        return ResponseResult.success(goods1);
    }



}