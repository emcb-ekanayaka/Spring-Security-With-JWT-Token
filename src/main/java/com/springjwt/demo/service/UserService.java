package com.springjwt.demo.service;

import com.springjwt.demo.entity.Role;
import com.springjwt.demo.entity.User;
import com.springjwt.demo.repo.RoleRepository;
import com.springjwt.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userDao;

    @Autowired
    private RoleRepository roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createNewUser(User user){
        return userDao.save(user);
    }

    public void initRoleAndUser(){
        Role adminRole = new Role();
        Role userRole = new Role();
        if (!roleDao.existsById("admin")){
            adminRole.setRoleName("admin");
            adminRole.setRoleDescription("Admin Role");
            roleDao.save(adminRole);
        }

        if (!roleDao.existsById("user")){
            userRole.setRoleName("user");
            userRole.setRoleDescription("User Role");
            roleDao.save(userRole);
        }

        if (!userDao.existsById("admin123@gmail.com")){
            User user = new User();
            user.setUserName("admin123@gmail.com");
            user.setUserFirstName("juz");
            user.setUserLastName("moe");
            user.setUserPassword(getEncodedPassword("admin@123"));

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);

            user.setRole(adminRoles);
            userDao.save(user);
        }

        if (!userDao.existsById("user123@gmail.com")){
            User user = new User();
            user.setUserName("user123@gmail.com");
            user.setUserFirstName("joe");
            user.setUserLastName("deo");
            user.setUserPassword(getEncodedPassword("user@123"));

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(userRole);

            user.setRole(userRoles);
            userDao.save(user);
        }

    }

    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }
}
