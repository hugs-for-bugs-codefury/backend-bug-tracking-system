package org.example.models;


import java.time.LocalDateTime;

/**
 * The abstract User class represents a general user in the bug-tracking system.
 * This class serves as the base for different types of users, such as Project Managers, Developers, and Testers.
 *
 * The User class provides common properties and methods shared by all user types.
 *
 * Example properties include:
 * - int id: The unique identifier for the user.
 * - String name: The name of the user.
 * - String email: The email address of the user.
 * - String password: The encrypted password for user authentication.
 * - String role: The role of the user (e.g., "ProjectManager", "Developer", "Tester").
 * - LocalDateTime lastLogin: The timestamp of the user's last login.
 *
 * Subclasses should implement specific behaviors and attributes for different types of users.
 */
public class User {

    private int id;
    private String name;
    private String email;
    private String passwordHash;
    private String role;
    private LocalDateTime lastLogin;


    public User(int id, String name, String email, String passwordHash, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;

    }

    // constructor for unregister user
    public User(String name, String email, String passwordHash, String role) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;

    }

    // Getters and Setters for each field

    /**
     * Gets the unique identifier of the user.
     * @return The user's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user.
     * @param id The user's ID.
     */
    public void setId(int id) {
        this.id = id;
    }




    /**
     * Gets the name of the user.
     * @return The user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     * @param name The user's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email address of the user.
     * @return The user's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     * @param email The user's email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the encrypted password of the user.
     * @return The user's password.
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Sets the encrypted password of the user.
     * @param passwordHash The user's password.
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Gets the role of the user.
     * @return The user's role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     * @param role The user's role.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets the timestamp of the user's last login.
     * @return The last login timestamp.
     */
    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    /**
     * Sets the timestamp of the user's last login.
     * @param lastLogin The last login timestamp.
     */
    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }



}