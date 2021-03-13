package com.user.management.services.impls;

import com.user.management.dto.UserDTO;
import com.user.management.models.User;
import com.user.management.repository.UserDao;
import com.user.management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Page<User> pagingFilteringAndSortingUsers(Map<String, Object> allParamsOpt) {
        Map<String, Object> allParams = allParamsOpt;
        int page = (int) allParams.getOrDefault("page", 0);
        int size = (int) allParams.getOrDefault("size", 2);
        String sortField = (String) allParams.getOrDefault("sort", "id");
        Sort sort = Sort.by(sortField)
                .descending();
        String name = (String) allParams.getOrDefault("name", null);
        String lastName = (String) allParams.getOrDefault("lastName", null);
        String code = (String) allParams.getOrDefault("code", null);
        Pageable requestedPage = PageRequest.of(page, size, sort);
        if(Objects.isNull(name) && Objects.isNull(lastName) && Objects.isNull(code)){
            return  userDao.findAll(requestedPage);
        }
        return userDao.findAllByNameOrLastNameOrCode(name, lastName, code, requestedPage);
    }
}
