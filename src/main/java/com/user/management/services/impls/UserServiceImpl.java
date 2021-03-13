package com.user.management.services.impls;

import com.user.management.dto.UserDTO;
import com.user.management.exceptions.UserCodeExistsException;
import com.user.management.exceptions.UserNotFoundException;
import com.user.management.models.Group;
import com.user.management.models.User;
import com.user.management.repository.GroupDao;
import com.user.management.repository.UserDao;
import com.user.management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private GroupDao groupDao;

    @Override
    public Page<User> pagingFilteringAndSortingUsers(Map<String, String> allParamsOpt) {
        Map<String, String> allParams = allParamsOpt;
        int page = Integer.parseInt(allParams.getOrDefault("page", "0"));
        int size = Integer.parseInt(allParams.getOrDefault("size", "2"));
        String sortField = allParams.getOrDefault("sort", "id");
        Sort sort = Sort.by(sortField)
                .descending();
        String name =  allParams.getOrDefault("name", null);
        String lastName =  allParams.getOrDefault("lastName", null);
        String code =  allParams.getOrDefault("code", null);
        Pageable requestedPage = PageRequest.of(page, size, sort);
        String valueGroup = allParams.getOrDefault("group", "0");
        Long groupId = Long.parseLong("".equals(valueGroup) ? "0" : valueGroup) ;
        Optional<Group> group = groupDao.findById(groupId);
        if(Objects.isNull(name) && Objects.isNull(lastName) && Objects.isNull(code) && !group.isPresent()){
            return  userDao.findAll(requestedPage);
        }
        return userDao.findAllByNameOrLastNameOrCodeOrGroup(name, lastName, code, requestedPage, group.orElse(null));
    }

    @Override
    public Optional<User> findById(long id) {
        Optional<User> user  = userDao.findById(id);
        if(user.isPresent()){
            return user;
        }
        throw new UserNotFoundException("No existe el usuario");
    }

    @Override
    public User createdUser(UserDTO userDto) throws UserCodeExistsException {
        User user = userDao.findByCode(userDto.getCode());
        if(Objects.nonNull(user)){
            throw  new UserCodeExistsException("Ya existe un usuario con el mismo code");
        }
        Optional<Group> group = groupDao.findById(Objects.nonNull(userDto.getGroup()) ? userDto.getGroup().getId(): 1);
        user = User.builder()
                .name(userDto.getName())
                .lastName(userDto.getLastName())
                .code(userDto.getCode())
                .group(group.get())
                .build();
        user = userDao.save(user);
        return user;
    }

    @Override
    public User updateUser(Long id, UserDTO userDto) throws UserCodeExistsException {
        Optional<User> userOpt = findById(id);
        if(userOpt.isPresent()){
            Boolean isExistsCode = Objects.nonNull(userDao.findByCode(userDto.getCode()));
            if(isExistsCode){
                throw  new UserCodeExistsException("Ya existe un usuario con el mismo code");
            }
            User user = userOpt.get();
            if(Objects.nonNull(userDto.getLastName())){
                user.setLastName(userDto.getLastName());
            }
            if(Objects.nonNull(userDto.getName())){
                user.setName(userDto.getName());
            }
            if(Objects.nonNull(userDto.getCode())){
                user.setCode(userDto.getCode());
            }
            user = userDao.save(user);
            return user;
        }
        throw  new UserNotFoundException("No existe el usuario");
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> user = findById(id);
        if(user.isPresent())
            userDao.delete(user.get());
    }
}
