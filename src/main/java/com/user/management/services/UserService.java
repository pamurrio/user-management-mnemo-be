package com.user.management.services;


import com.user.management.dto.UserDTO;
import com.user.management.models.User;
import org.springframework.data.domain.Page;

import java.util.Map;
import java.util.Optional;


public interface UserService {

    Page<User>  pagingFilteringAndSortingUsers(Map<String, String> allParams);

    Optional<User> findById(long id);

    User createdUser(UserDTO user);

    User updateUser(Long id, UserDTO user);

    void deleteUser(Long id);

}
