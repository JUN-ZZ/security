package com.jun.reservation.service;

import com.jun.reservation.entity.Shop;
import com.jun.reservation.response.Pagination;

import java.util.List;

/**
 * @author jun
 * @date 2021/1/1
 */
public interface ShopService {

    public Shop findShopByShopName(String shopName);

    public void deleteShopById(Long id);

    public List<Shop> findAllShops();

    public Pagination findShops(int pageNum, int pageSize);

    public Shop saveShop(Shop shop);

    public Shop updateShop(Shop shop);



}
