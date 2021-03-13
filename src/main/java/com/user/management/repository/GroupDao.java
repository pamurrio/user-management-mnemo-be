package com.user.management.repository;

import com.user.management.models.Group;
import com.user.management.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupDao extends JpaRepository<Group, Long> {
    Group findByName(String name);
}
