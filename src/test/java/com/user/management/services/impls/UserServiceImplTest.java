package com.user.management.services.impls;

import com.user.management.models.User;
import com.user.management.repository.UserDao;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserDao userDao;
    @Test
    public void findById() {
        Optional<User> returnUser = Optional.of(User.builder().build());
        Mockito.when(userDao.findById(Mockito.any())).thenReturn(returnUser);
        Optional<User> add = userService.findById(1);
        assertNotNull(add.get());
    }
}