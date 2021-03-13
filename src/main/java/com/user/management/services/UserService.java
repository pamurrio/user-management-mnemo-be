package com.user.management.services;


import com.user.management.dto.UserDTO;
import com.user.management.models.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface UserService {

    Page<User>  pagingFilteringAndSortingUsers(Map<String, Object> body);

    /*User createdSuperUser(UserDTO superUser);

    User updateSuperUser(Long id, UserDTO superUser);

    void deleteSuperUser(Long id);

    List<User> findContainsNameSuperUser(String name);*/
}
