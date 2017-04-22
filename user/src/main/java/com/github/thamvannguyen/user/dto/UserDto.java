package com.github.thamvannguyen.user.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;



public class UserDto {
    @NotEmpty
    @Size(max = 45)
    private String username;
    private String password;

}
