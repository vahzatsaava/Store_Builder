package com.service.impl;

import com.dto.UserDto;
import com.model.Role;
import com.model.User;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import com.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(User user) {
        Role role = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        log.info("return user {}", user.getEmail());
        userRepository.save(user);
        return UserDto.from(user);
    }

    @Override
    public UserDto updateUser(Long id, User user) {
        User userUpd = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("user not found"));
        userUpd.setRoles(user.getRoles());
        userUpd.setEmail(user.getEmail());
        userUpd.setPhone(user.getPhone());
        userUpd.setPassword(user.getPassword());
        userUpd.setName(user.getName());
        userRepository.save(user);

        return UserDto.from(userUpd);
    }

    @Override
    public void deleteUser(Long id) {
        User userUpd = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("user not found"));
        userRepository.delete(userUpd);
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepository.getUsers().stream().map(UserDto::from).collect(Collectors.toList());
    }

    @Override
    public User getUserByEmail(String name) {
        User user = userRepository.findUserByEmail(name);
        if (user == null){
            throw new EntityNotFoundException("not fount Entity");
        }
        return userRepository.findUserByEmail(name);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }
}
