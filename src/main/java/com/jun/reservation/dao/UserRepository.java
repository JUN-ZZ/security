package com.jun.reservation.dao;

import com.jun.reservation.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor {

    public User findUserByUsername(String username);

    @Query(value = "update tb_user set username= ?2 ,set password= ?3 where id=?1",nativeQuery = true)
    boolean updateUserById(Long id,String username,String password);


}
