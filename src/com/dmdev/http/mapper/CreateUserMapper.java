package com.dmdev.http.mapper;

import com.dmdev.http.dto.CreateUserDto;
import com.dmdev.http.entity.Gender;
import com.dmdev.http.entity.Role;
import com.dmdev.http.entity.User;
import com.dmdev.http.util.LocalDateFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserMapper implements Mapper<CreateUserDto, User> {
    private static final CreateUserMapper INSTANCE = new CreateUserMapper();
    private static final String IMAGE_FOLDER = "users/";

    @Override
    public User mapFrom(CreateUserDto dto) {
        return User.builder()
                .name(dto.getName())
                .birthday(LocalDateFormatter.format(dto.getBirthday()))
                .image(IMAGE_FOLDER + dto.getImage().getSubmittedFileName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .gender(Gender.valueOf(dto.getGender()))
                .role(Role.valueOf(dto.getRole()))
                .build();
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}
