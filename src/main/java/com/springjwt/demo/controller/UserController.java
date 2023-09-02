package com.springjwt.demo.controller;

import com.springjwt.demo.entity.User;
import com.springjwt.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-new-user")
    public User createNewUser(@RequestBody User user){
        return userService.createNewUser(user);
    }

    @PostConstruct
    public void initRoleAndUser(){
        userService.initRoleAndUser();
    }

    @GetMapping({"/for-admin"})
    @PreAuthorize("hasRole('admin')")
    public String forAdmin(){
        return "this url only accessible for only admin";
    }

    @GetMapping({"/for-user"})
    @PreAuthorize("hasAnyRole('user','admin')")
    public String forUsers(){
        return "this url only accessible for only users";
    }
}
