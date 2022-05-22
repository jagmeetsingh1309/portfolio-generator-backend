package com.jagmeet.portfoliobuilder.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private final String username;
    private final String password;
    private final String email;
    private final String role;

}
