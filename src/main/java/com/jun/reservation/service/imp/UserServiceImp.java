package com.jun.reservation.service.imp;

import com.jun.reservation.dao.UserRepository;
import com.jun.reservation.entity.User;
import com.jun.reservation.response.Pagination;
import com.jun.reservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    /**
     *
     * @param id
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    /**
     *
     * @return
     */
    @Override
    public Pagination findAllUsers() {
        List<User> userList = userRepository.findAll();

        Pagination pagination = new Pagination();
        pagination.setDataList(userList);

        return pagination;
    }

    /**
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Pagination findUsers(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum-1, pageSize);
        Page<User> page = userRepository.findAll(pageable);
        Pagination pagination = Pagination.convert(page);

        return pagination;
    }

    /**
     *
     * @param user
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User saveUser(User user) {
        User user1 = userRepository.save(user);
        return user1;
    }

    /**
     *
     * @param user
     * @return
     */
    @Transactional
    @Override
    public User updateUser(User user) {
        return userRepository.saveAndFlush(user);
    }


}
