package com.dmdev.http.mapper;

import com.dmdev.http.dto.UserDto;
import com.dmdev.http.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper implements Mapper<User, UserDto> {
    private static final UserMapper INSTANCE = new UserMapper();

    @Override
    public UserDto mapFrom(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .image(user.getImage())
                .birthday(user.getBirthday())
                .email(user.getEmail())
                .gender(user.getGender())
                .role(user.getRole())
                .build();
    }

    public static UserMapper getInstance() {
        return INSTANCE;
    }
}
