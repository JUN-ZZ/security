package com.jun.reservation.service.imp;

import com.jun.reservation.dao.RoleRepository;
import com.jun.reservation.entity.Role;
import com.jun.reservation.response.Pagination;
import com.jun.reservation.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author jun
 * @date 2021/1/1
 */
@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findRoleByName(String name) {
        Role role = roleRepository.findRoleByRoleNickName(name);
        return role;
    }

    @Transactional
    @Override
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }


    @Override
    public List<Role> findAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles;
    }

    @Override
    public Pagination findRoles(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
        Page page = roleRepository.findAll(pageable);
        Pagination pagination = Pagination.convert(page);
        return pagination;
    }

    @Transactional
    @Override
    public Role saveRole(Role role) {
        Role role1 = roleRepository.save(role);
        return role1;
    }

    @Transactional
    @Override
    public Role updateRole(Role updateRole) {
        Role role = roleRepository.findRoleById(updateRole.getId());
        BeanUtils.copyProperties(updateRole,role);

        roleRepository.saveAndFlush(role);
        return role;
    }



}
