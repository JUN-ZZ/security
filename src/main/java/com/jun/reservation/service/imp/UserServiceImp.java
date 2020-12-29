package com.jun.reservation.service.imp;

import com.jun.reservation.dao.UserRepository;
import com.jun.reservation.entity.User;
import com.jun.reservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author jun
 * @date 2020/12/24
 */
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByName(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public List<User> findAllUsers(int pageNum, int pageSize) {
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User saveUser(User user) {
        User user1 = userRepository.save(user);
        return user1;
    }

    @Transactional
    @Override
    public User updateUser(User user) {
        return userRepository.saveAndFlush(user);
    }


}
