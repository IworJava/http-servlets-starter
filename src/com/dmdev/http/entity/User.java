package com.dmdev.http.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class User {
    private Integer id;
    private String name;
    private String image;
    private LocalDate birthday;
    private String email;
    private String password;
    private Gender gender;
    private Role role;
}
