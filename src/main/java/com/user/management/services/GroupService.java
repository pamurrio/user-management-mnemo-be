package com.user.management.services;


import com.user.management.dto.UserDTO;
import com.user.management.models.Group;
import com.user.management.models.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface GroupService {

    List<Group> findAll();

}
