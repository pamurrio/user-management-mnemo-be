package com.user.management.repository;

import com.user.management.dto.UserDTO;
import com.user.management.models.Group;
import com.user.management.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.function.Predicate;

public interface UserDao extends JpaRepository<User, Long> {
    Page<User> findAllByNameOrLastNameOrCodeOrGroup(String name, String lastName, String code, Pageable pageable, Group group);
    Page<User> findAll(Pageable pageable);
    List<User> findByNameContainingIgnoreCase(String name);

    User findByCode(String code);
}
