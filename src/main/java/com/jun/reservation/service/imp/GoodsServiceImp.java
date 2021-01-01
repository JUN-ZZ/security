package com.jun.reservation.service.imp;

import com.jun.reservation.dao.GoodsRepository;
import com.jun.reservation.entity.Goods;
import com.jun.reservation.response.Pagination;
import com.jun.reservation.service.GoodsService;
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
public class GoodsServiceImp implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public Goods findGoodsByName(String name) {
        return goodsRepository.findGoodsByName(name);
    }

    @Transactional
    @Override
    public void deleteGoodsById(Long id) {
        goodsRepository.deleteById(id);
    }

    @Override
    public List<Goods> findAllGoods() {
        return goodsRepository.findAll();
    }

    @Override
    public Pagination findGoods(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
        Page page = goodsRepository.findAll(pageable);
        Pagination pagination = Pagination.convert(page);
        return pagination;
    }

    @Transactional
    @Override
    public Goods saveGoods(Goods goods) {
        return goodsRepository.save(goods);
    }


    @Transactional
    @Override
    public Goods updateGoods(Goods updateGoods) {
        Optional optional = goodsRepository.findById(updateGoods.getId());
        Goods goods= (Goods) optional.get();

        BeanUtils.copyProperties(updateGoods,goods);

        Goods goods1 = goodsRepository.saveAndFlush(goods);

        return goods1;
    }



}
