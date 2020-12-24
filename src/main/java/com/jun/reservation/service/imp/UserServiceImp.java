package com.jun.reservation.service.imp;

import com.jun.reservation.dao.UserRepository;
import com.jun.reservation.entity.User;
import com.jun.reservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author jun
 * @date 2020/12/24
 */
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByName(String username) {
        return null;
    }

    @Override
    public void deleteUserById(Long id) {

    }

    @Override
    public List<User> findUsers() {
        return null;
    }

    @Override
    public List<User> findUsers(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }
}
