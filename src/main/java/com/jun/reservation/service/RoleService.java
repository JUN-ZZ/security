package com.jun.reservation.service;

import com.jun.reservation.DTO.UserDTO;
import com.jun.reservation.entity.Role;
import com.jun.reservation.response.Pagination;

import java.util.List;

/**
 * @author jun
 * @date 2021/1/1
 */
public interface RoleService {


    public Role findRoleByName(String name);

    public void deleteRoleById(Long id);

    public List<Role> findAllRoles();

    public Pagination findRoles(int pageNum, int pageSize);

    public Role saveRole(Role role);

    public Role updateRole(Role role);



}
