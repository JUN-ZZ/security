package com.jun.security;

import com.jun.reservation.ReservationApplication;
import com.jun.reservation.dao.UserRepository;
import com.jun.reservation.entity.Role;
import com.jun.reservation.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.*;
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

//    @Test
    void saveUser(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setUsername("zxj");
        user.setPassword(bCryptPasswordEncoder.encode("zxj"));
        userRepository.save(user);

    }

    @Test
    void page(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<User> page = userRepository.findAll(pageable);
        System.out.println(page);
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
        System.out.println(page.getContent());

    }

    @Test
    void pageG(){
        Sort sort = Sort.by(Sort.Direction.DESC, "username");
        Pageable pageable = PageRequest.of(0, 10,sort);
        Page<User> page = userRepository.findAll(pageable);
        System.out.println(page);
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
        System.out.println(page.getContent());

    }

    @Test
    void pageGG(){

        Specification specification = new Specification<User>(){
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                Path path= root.get("username");
                Predicate equal = criteriaBuilder.equal(path,"zxj");
//                criteriaBuilder.and(equal,equal);

                return equal;
            }
        };

        Sort sort = Sort.by(Sort.Direction.DESC, "username");
        Pageable pageable = PageRequest.of(0, 10,sort);
        Page<User> page;
        page = userRepository.findAll(specification,pageable);
        System.out.println(page);
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
        System.out.println(page.getContent());

    }
}
