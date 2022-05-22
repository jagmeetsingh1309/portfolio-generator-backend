package com.jagmeet.portfoliobuilder.service;

import com.jagmeet.portfoliobuilder.entities.User;
import com.jagmeet.portfoliobuilder.exceptions.UserAlreadyExistsException;
import com.jagmeet.portfoliobuilder.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createNewUser(
            String userName,
            String email,
            String role,
            String password){
        log.info("Creating new user: {}",userName);
        userRepository.findByUserName(userName)
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException("User with username: " + userName + " already exists.");
                });
        User newUser = User.builder()
                .email(email)
                .userName(userName)
                .role(role)
                .password(passwordEncoder.encode(password))
                .build();
        log.info("New user created: {}",newUser);
        userRepository.save(newUser);
    }



}