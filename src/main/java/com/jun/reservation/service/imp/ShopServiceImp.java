package com.jun.reservation.service.imp;

import com.jun.reservation.dao.ShopRepository;
import com.jun.reservation.entity.Shop;
import com.jun.reservation.response.Pagination;
import com.jun.reservation.service.ShopService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author jun
 * @date 2021/1/1
 */
@Service
public class ShopServiceImp implements ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Override
    public Shop findShopByShopName(String shopName) {
        return shopRepository.findShopByShopName(shopName);
    }

    @Transactional
    @Override
    public void deleteShopById(Long id) {
        shopRepository.deleteById(id);
    }

    @Override
    public List<Shop> findAllShops() {
        return shopRepository.findAll();
    }

    @Override
    public Pagination findShops(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
        Page page = shopRepository.findAll(pageable);
        Pagination pagination = Pagination.convert(page);
        return pagination;
    }

    @Transactional
    @Override
    public Shop saveShop(Shop shop) {
        return shopRepository.save(shop);
    }

    @Transactional
    @Override
    public Shop updateShop(Shop updateShop) {

        Optional optional = shopRepository.findById(updateShop.getId());
        Shop shop= (Shop) optional.get();

        BeanUtils.copyProperties(updateShop,shop);

        Shop shop1 = shopRepository.saveAndFlush(shop);

        return shop1;
    }



}
