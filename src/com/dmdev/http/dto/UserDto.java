package com.dmdev.http.dto;

import com.dmdev.http.entity.Gender;
import com.dmdev.http.entity.Role;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UserDto {
    Integer id;
    String name;
    String image;
    LocalDate birthday;
    String email;
    Gender gender;
    Role role;
}
