package com.jun.reservation.dao;

import com.jun.reservation.entity.Goods;
import com.jun.reservation.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {

    Goods findGoodsByName(String name);


}
