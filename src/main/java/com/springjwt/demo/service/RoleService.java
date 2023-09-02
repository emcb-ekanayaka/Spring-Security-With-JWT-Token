package com.springjwt.demo.service;

import com.springjwt.demo.entity.Role;
import com.springjwt.demo.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepo;
    public Role createNewRole(Role role){
        return roleRepo.save(role);
    }
}
