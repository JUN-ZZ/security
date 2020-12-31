package com.jun.reservation.service;


import com.jun.reservation.entity.User;
import com.jun.reservation.response.Pagination;

import java.util.List;

/**
 *  user 增删改查接口
 */
public interface UserService {

    public User findUserByName(String username);

    public void deleteUserById(Long id);

    public Pagination findAllUsers();

    public Pagination findUsers(int pageNum, int pageSize);

    public User saveUser(User user);

    public User updateUser(User user);


}
