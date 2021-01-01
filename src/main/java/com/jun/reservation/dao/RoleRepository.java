package com.jun.reservation.dao;

import com.jun.reservation.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findRoleByRoleNickName(String roleNickName);

    Role findRoleByRoleCodeName(String roleCodeName);

    Role findRoleById(Long id);


}
