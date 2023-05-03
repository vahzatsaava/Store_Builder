package com.example.store_builder.impl;

import com.model.Role;
import com.model.User;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import com.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class    UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Spy
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);


    @InjectMocks
    private UserServiceImpl userService;


    private User getUserForTest() {
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("johndoe@example.com");
        user.setPhone("1234567890");
        user.setPassword("password");
        user.setRoles(roles);


        return user;
    }


    @Test
    public void testCreateUser() {
        User user = getUserForTest();
        userService.createUser(user);


        verify(passwordEncoder, times(1)).encode(anyString());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testUpdateUser() {
        User userFromDb = getUserForTest();
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userFromDb));
        when(userRepository.save(any(User.class))).thenReturn(userFromDb);

        User userUpdated = getUserForTest();
        userService.updateUser(1L, userUpdated);

        verify(userRepository, times(1)).save(userUpdated);
        assertEquals("John Doe", userService.createUser(getUserForTest()).getName());

    }

    @Test
    public void testDeleteUser() {
        User user = getUserForTest();
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        assertDoesNotThrow(() -> userService.deleteUser(anyLong()));
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void testDeleteUserWithInvalidId() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.deleteUser(anyLong()));
        verify(userRepository, never()).delete(any(User.class));
    }


    @Test
    public void testGetUserById(){
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(getUserForTest()));
        userService.findById(1L);
        verify(userRepository,times(1)).findById(anyLong());
        assertEquals("John Doe", Objects.requireNonNull(userRepository.findById(anyLong()).orElse(null)).getName());
    }
    @Test
    public void testInvalidGetUserById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> userService.findById(anyLong()));

        verify(userRepository, times(1)).findById(anyLong());

    }

    @Test
    public void testGetUserByEmail() {
        User user = getUserForTest();
        when(userRepository.findUserByEmail(anyString())).thenReturn(user);

        User result = userService.getUserByEmail(anyString());

        verify(userRepository, times(2)).findUserByEmail(anyString());
        assertEquals(user.getEmail(), result.getEmail());
    }
    @Test
    public void testInvalidGetUserByEmail() {
        when(userRepository.findUserByEmail(anyString())).thenReturn(null);
        assertThrows(EntityNotFoundException.class, () -> userService.getUserByEmail(anyString()));
        verify(userRepository, times(1)).findUserByEmail(anyString());
    }







}
