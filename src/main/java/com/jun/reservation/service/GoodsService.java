package com.jun.reservation.service;

import com.jun.reservation.entity.Goods;
import com.jun.reservation.entity.Shop;
import com.jun.reservation.response.Pagination;

import java.util.List;

/**
 * @author jun
 * @date 2021/1/1
 */
public interface GoodsService {


    public Goods findGoodsByName(String name);

    public void deleteGoodsById(Long id);

    public List<Goods> findAllGoods();

    public Pagination findGoods(int pageNum, int pageSize);

    public Goods saveGoods(Goods goods);

    public Goods updateGoods(Goods goods);





}
