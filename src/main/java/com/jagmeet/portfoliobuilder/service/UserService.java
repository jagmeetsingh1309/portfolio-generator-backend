package com.jagmeet.portfoliobuilder.service;

import com.jagmeet.portfoliobuilder.entities.User;
import com.jagmeet.portfoliobuilder.exceptions.NotFoundException;
import com.jagmeet.portfoliobuilder.exceptions.UserAlreadyExistsException;
import com.jagmeet.portfoliobuilder.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public User findUserByUserName(String username){
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new NotFoundException("User with username: " + username + " not found"));
    }

    public User findUserById(String id){
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id: " + id + " not found"));
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

    public User loadUserFromSecurityContext(){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new NotFoundException("User with username: " + username + " not found"));
    }



}
