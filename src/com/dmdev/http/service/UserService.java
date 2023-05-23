package com.dmdev.http.service;

import com.dmdev.http.dao.UserDao;
import com.dmdev.http.dto.CreateUserDto;
import com.dmdev.http.entity.User;
import com.dmdev.http.exeption.ValidationException;
import com.dmdev.http.mapper.CreateUserMapper;
import com.dmdev.http.validator.CreateUserValidator;
import com.dmdev.http.validator.ValidationResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    private static final UserService INSTANCE = new UserService();
    private static final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final UserDao userDao = UserDao.getInstance();

    public Integer create(CreateUserDto userDto) {
    //     validate
        ValidationResult validationResult = createUserValidator.isValid(userDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
    //     map dto to entity
        User user = createUserMapper.mapFrom(userDto);
    //     save
        return userDao.save(user).getId();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
