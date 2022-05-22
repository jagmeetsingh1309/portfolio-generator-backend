package com.jagmeet.portfoliobuilder.controller;

import com.jagmeet.portfoliobuilder.dto.UserDto;
import com.jagmeet.portfoliobuilder.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody UserDto userDto){
        userService.createNewUser(
                userDto.getUsername(),
                userDto.getEmail(),
                userDto.getRole(),
                userDto.getPassword()
                );
    }



}
