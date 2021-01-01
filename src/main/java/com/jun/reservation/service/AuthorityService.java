package com.jun.reservation.service;

import com.jun.reservation.entity.Authority;
import com.jun.reservation.response.Pagination;

import java.util.List;

/**
 * @author jun
 * @date 2021/1/1
 */
public interface AuthorityService {


    public Authority findAuthorityByName(String name);

    public void deleteAuthorityById(Long id);

    public List<Authority> findAllAuthorities();

    public Pagination findAuthorities(int pageNum, int pageSize);

    public Authority saveAuthority(Authority authority);

    public Authority updateAuthority(Authority authority);



}
