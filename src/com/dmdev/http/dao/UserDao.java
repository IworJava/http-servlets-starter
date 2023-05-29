package com.dmdev.http.dao;

import com.dmdev.http.entity.Gender;
import com.dmdev.http.entity.Role;
import com.dmdev.http.entity.User;
import com.dmdev.http.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao implements Dao<Integer, User> {
    private static final UserDao INSTANCE = new UserDao();
    private static final String FIND_ALL_SQL = """
            SELECT id, name, birthday, image, email, password, gender, role
            FROM users;
            """;
    private static final String FIND_BY_EMAIL_AND_PASSWORD_SQL = """
            SELECT id, name, birthday, image, email, password, gender, role
            FROM users
            WHERE email = ? AND password = ?;
            """;
    private static final String SAVE_SQL = """
            INSERT INTO users(name, birthday, image, email, password, gender, role)
            VALUES (?, ?, ?, ?, ?, ?, ?);
            """;
    private static final String EXIST_EMAIL_SQL = """
            SELECT id
            FROM users
            WHERE email = ?;
            """;

    @SneakyThrows
    @Override
    public List<User> findAll() {
        try (
                Connection connection = ConnectionManager.open();
                PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL)
        ) {
            List<User> users = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }
            return users;
        }
    }

    @SneakyThrows
    private User buildUser(ResultSet resultSet) {
        return User.builder()
                .id(resultSet.getObject("id", Integer.class))
                .name(resultSet.getObject("name", String.class))
                .birthday(resultSet.getObject("birthday", LocalDate.class))
                .image(resultSet.getObject("image", String.class))
                .email(resultSet.getObject("email", String.class))
                .password(resultSet.getObject("password", String.class))
                .gender(Gender.find(resultSet.getObject("gender", String.class)).orElse(null))
                .role(Role.find(resultSet.getObject("role", String.class)).orElse(null))
                .build();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @SneakyThrows
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (
                Connection connection = ConnectionManager.open();
                PreparedStatement statement = connection.prepareStatement(FIND_BY_EMAIL_AND_PASSWORD_SQL)
        ) {
            statement.setObject(1, email);
            statement.setObject(2, password);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next()
                    ? Optional.of(buildUser(resultSet))
                    : Optional.empty();
        }
    }

    @SneakyThrows
    public boolean checkEmail(String email) {
        try (
                Connection connection = ConnectionManager.open();
                PreparedStatement statement = connection.prepareStatement(EXIST_EMAIL_SQL)
        ) {
            statement.setObject(1, email);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public void update(User entity) {
    }

    @Override
    @SneakyThrows
    public User save(User user) {
        try (
                Connection connection = ConnectionManager.open();
                PreparedStatement statement = connection
                        .prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, user.getName());
            statement.setDate(2, Date.valueOf(user.getBirthday()));
            statement.setString(3, user.getImage());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getGender().name());
            statement.setString(7, user.getRole().name());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            user.setId(generatedKeys.getObject("id", Integer.class));
            return user;
        }
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
