package com.user.management.controllers;

import com.user.management.dto.UserDTO;
import com.user.management.models.User;
import com.user.management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/abm/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/pagingfilteringandsorting")
    public Page<User> pagingFilteringAndSortingCustomersByAge(@RequestParam(required = false) Map<String, Object> allParams){
        Page<User> users  = userService.pagingFilteringAndSortingUsers(allParams);
        return users;
    }
}
