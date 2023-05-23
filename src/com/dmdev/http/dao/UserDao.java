package com.dmdev.http.dao;

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
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao implements Dao<Integer, User> {
    private static final UserDao INSTANCE = new UserDao();
    private static final String SAVE_SQL = """
            INSERT INTO users(name, birthday, email, password, gender, role)
            VALUES (?, ?, ?, ?, ?, ?);
            """;
    private static final String EXIST_EMAIL_SQL = """
            SELECT id
            FROM users
            WHERE email = ?;
            """;

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
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
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getGender().name());
            statement.setString(6, user.getRole().name());
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
