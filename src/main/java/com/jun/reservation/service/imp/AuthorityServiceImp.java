package com.jun.reservation.service.imp;

import com.jun.reservation.dao.AuthorityRepository;
import com.jun.reservation.entity.Authority;
import com.jun.reservation.response.Pagination;
import com.jun.reservation.service.AuthorityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author jun
 * @date 2021/1/1
 */
@Service
public class AuthorityServiceImp implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Authority findAuthorityByName(String name) {
        return authorityRepository.findAuthorityByAuthName(name);
    }

    @Transactional
    @Override
    public void deleteAuthorityById(Long id) {
        authorityRepository.deleteById(id);
    }


    @Override
    public List<Authority> findAllAuthorities() {
        return authorityRepository.findAll();
    }

    @Override
    public Pagination findAuthorities(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
        Page page = authorityRepository.findAll(pageable);
        Pagination pagination = Pagination.convert(page);
        return pagination;
    }

    @Transactional
    @Override
    public Authority saveAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Transactional
    @Override
    public Authority updateAuthority(Authority authority) {
        Authority authority1 = authorityRepository.findAuthorityById(authority.getId());
        BeanUtils.copyProperties(authority,authority1);
        authorityRepository.saveAndFlush(authority1);

        return authority1;
    }
}
