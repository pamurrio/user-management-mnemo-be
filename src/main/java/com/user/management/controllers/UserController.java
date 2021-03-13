package com.user.management.controllers;

import com.user.management.dto.UserDTO;
import com.user.management.models.User;
import com.user.management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/abm/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findById/{id}")
    public ResponseEntity<User> findById(@PathVariable long id){
        Optional<User> users  = userService.findById(id);
            return ResponseEntity.ok(users.get());
    }

    @PostMapping("/created")
    public ResponseEntity<?> createdUser(@Valid @RequestBody UserDTO user){
        return ResponseEntity.ok(userService.createdUser(user));
    }
    @CrossOrigin(origins="*")
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO user){
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @CrossOrigin(origins="*")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/pagingfilteringandsorting")
    public Page<User> pagingFilteringAndSortingCustomersByAge(@RequestParam(required = false) Map<String, String> allParams){
        Page<User> users  = userService.pagingFilteringAndSortingUsers(allParams);
        return users;
    }
}
