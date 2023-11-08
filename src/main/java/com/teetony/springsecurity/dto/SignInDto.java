package com.teetony.springsecurity.dto;

import lombok.Data;



@Data
public class SignInDto {

    private String email;

    private String password;
}
