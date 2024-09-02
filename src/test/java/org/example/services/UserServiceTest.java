package org.example.services;

import org.example.models.User;
import org.example.services.impl.UserServiceImpl;
import org.example.util.Config;
import org.example.util.MySQLConnection;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Paths;

public class UserServiceTest {


    String user_name = "name";
    String user_email = "user1@mail.com";
    String user_password = "password";
    String user_role = "project_manager";


    @BeforeEach
    public  void setUp() {
        Config.setProperty("db.url", "jdbc:mysql://localhost:3306/test_db");
        MySQLConnection.setup();
        MySQLConnection.seed();

    }




    @DisplayName("Test register user")
    @Test
    void testRegisterUser() {

        IUserService userService = new UserServiceImpl();
        assertDoesNotThrow(() -> {
            User u = userService.registerUser(user_name, user_email, user_password, user_role);
            assertNotNull(u);
            assertEquals(user_name, u.getName());
            assertEquals(user_email, u.getEmail());
            assertEquals(user_role, u.getRole());


        });

    }

    @DisplayName("Test get user")
    @Test

    void testGetUser(){
        IUserService userService = new UserServiceImpl();


        User registeredUser = userService.registerUser(user_name, user_email, user_password, user_role);


        assertDoesNotThrow(() -> {
            User u = userService.getUser(registeredUser.getId());
            assertNotNull(u);
            assertEquals(user_name, u.getName());
            assertEquals(user_email, u.getEmail());
            assertEquals(user_role, u.getRole());
        });
    }

    @DisplayName("Test Login")
    @Test
    void testLogin(){
        IUserService userService = new UserServiceImpl();
        User registeredUser = userService.registerUser(user_name, user_email, user_password, user_role);

        assertDoesNotThrow(() -> {
            User loggedInUser = userService.login(user_email, user_password);
            assertNotNull(loggedInUser);
            assertEquals(registeredUser.getId(), loggedInUser.getId());

        });
    }

    @DisplayName("Test register user with invalid role")
    @Test
    void testRegisterUserWithInvalidRole() {
        IUserService userService = new UserServiceImpl();
        assertThrows(RuntimeException.class, () -> {
            userService.registerUser(user_name, user_email, user_password, "invalid_role");
        });
    }

    @DisplayName("Test register with duplicate email")
    @Test
    void testRegisterWithDuplicateEmail() {
        IUserService userService = new UserServiceImpl();
        assertDoesNotThrow(() -> {
            userService.registerUser(user_name, user_email, user_password, user_role);
            assertThrows(RuntimeException.class, () -> {
                userService.registerUser(user_name, user_email, user_password, user_role);
            });
        });
    }

    @DisplayName("Test get user with invalid id")
    @Test
    void testGetUserWithInvalidId() {
        IUserService userService = new UserServiceImpl();
        assertThrows(RuntimeException.class, () -> {
            userService.getUser(100);
        });
    }




}
