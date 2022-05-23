package com.jagmeet.portfoliobuilder.controller;

import com.jagmeet.portfoliobuilder.dto.JwtRequest;
import com.jagmeet.portfoliobuilder.dto.JwtResponse;
import com.jagmeet.portfoliobuilder.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public JwtResponse authenticateUser(@RequestBody JwtRequest jwtRequest) throws Exception{
        final String token = authenticationService.authenticateUser(jwtRequest);
        return new JwtResponse(token);
    }

}
