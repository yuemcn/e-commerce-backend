package com.revature;

import com.revature.models.User;
import com.revature.repositories.UserRepository;
import com.revature.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {
    @Mock
    static UserRepository ur;
    @InjectMocks
    static UserService us;

    @BeforeEach
    public void registrationBeforeTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findByCredentialsTest() {
        us = new UserService(ur);
        Optional<User> u = Optional.of(new User(0, "test@gmail.com", "password", "test_first", "test_last", false));
        when((ur).findByEmailAndPassword(any(), any())).thenReturn(u);

        Optional<User> test = us.findByCredentials("a", "b");

        assertEquals("test_first", test.get().getFirstName(), "pass");
        assertEquals("test_last", test.get().getLastName(), "pass");
        assertEquals(false, test.get().isAdmin(), "pass");
    }

    @Test
    public void saveUserTest() {
        User u = new User(1, "a", "b", "c", "d", false);
        us = new UserService(ur);
        Optional<User> p = Optional.empty();
        when((ur).save(any())).thenReturn(u);
        when((ur).findByEmail(any())).thenReturn(p);

        User test = us.save(u);

        assertEquals("a", test.getEmail(), "pass");
        assertEquals("b", test.getPassword(), "pass");
        assertEquals("c", test.getFirstName(), "pass");
        assertEquals("d", test.getLastName(), "pass");
        assertEquals(false, test.isAdmin(), "pass");
    }
}
