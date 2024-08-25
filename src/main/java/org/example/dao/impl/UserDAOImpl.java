package org.example.dao.impl;

import org.example.dao.IUserDAO;
import org.example.exceptions.users.UserNotFoundException;
import org.example.models.User;
import org.example.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class UserDAOImpl implements IUserDAO {
    @Override
    public User findByEmail(String email) throws SQLException, UserNotFoundException {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("user_id"), rs.getString("name"), rs.getString("email"), rs.getString("password_hash"), rs.getString("role"));
            }
            throw new UserNotFoundException("User with email " + email + " not found.");
        }

    }

    @Override
    public User findByID(int id) throws SQLException, UserNotFoundException {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("user_id"), rs.getString("name"), rs.getString("email"), rs.getString("password_hash"), rs.getString("role"));
            }
            throw new UserNotFoundException("User with id " + id + " not found.");
        }
    }

    @Override
    public User saveUser(String name, String email, String password, String role) throws SQLException {
        String sql = "INSERT INTO users (name, email, password_hash, role) VALUES (?, ?, ?, ?)";
        try (Connection connection = MySQLConnection.getConnection()) {
            int id;
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, role);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            rs.next();

            id = rs.getInt(1);
            return new User(id, name, email, password, role);


        }
    }

    @Override
    public LocalDateTime updateLastLogin(int user_id) throws SQLException {
        String sql = "UPDATE users SET last_login = ? WHERE user_id = ?";
        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1, LocalDateTime.now());
            ps.setInt(2, user_id);
            ps.executeUpdate();
            return LocalDateTime.now();
        }
    }




}
