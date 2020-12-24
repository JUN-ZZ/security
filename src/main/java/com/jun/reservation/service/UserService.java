package com.jun.reservation.service;


import com.jun.reservation.entity.User;

import java.util.List;

/**
 *  user 增删改查接口
 */
public interface UserService {

    public User findUserByName(String username);

    public void deleteUserById(Long id);

    public List<User> findUsers();

    public List<User> findUsers(int pageNum,int pageSize);

    public User saveUser(User user);

    public User updateUser(User user);


}
