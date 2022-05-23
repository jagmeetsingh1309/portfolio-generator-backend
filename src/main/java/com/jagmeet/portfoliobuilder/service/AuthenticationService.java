package com.jagmeet.portfoliobuilder.service;

import com.jagmeet.portfoliobuilder.dto.JwtRequest;
import com.jagmeet.portfoliobuilder.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthenticationService(
            AuthenticationManager authenticationManager,
            CustomUserDetailsService customUserDetailsService,
            JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public String authenticateUser(JwtRequest jwtRequest) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        UserDetails userDetails = customUserDetailsService
                .loadUserByUsername(jwtRequest.getUsername());
        return jwtTokenUtil.generateToken(userDetails);
    }

}
