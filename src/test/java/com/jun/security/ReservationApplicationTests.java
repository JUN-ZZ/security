package com.jun.security;

import com.jun.reservation.ReservationApplication;
import com.jun.reservation.dao.UserRepository;
import com.jun.reservation.entity.Role;
import com.jun.reservation.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@SpringBootTest(classes = ReservationApplication.class)
@RunWith(SpringRunner.class)
class ReservationApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {

    }

    @Test
    void test(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123456"));
//        System.out.println(bCryptPasswordEncoder.);
    }

    @Test
    void tsetUser(){
      User user = userRepository.findUserByUsername("uu");
      Set<Role> roles = user.getRoles();
      for(Role role:roles){
          Set list = role.getUsers();
          System.out.println(list);
      }




    }

}
