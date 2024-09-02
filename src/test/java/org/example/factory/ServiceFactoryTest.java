package org.example.factory;

import org.example.factory.impl.ServiceFactory;
import org.example.services.IDeveloperService;
import org.example.services.IProjectManagerService;
import org.example.services.ITesterService;
import org.example.services.IUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

public class ServiceFactoryTest {

    @DisplayName("Test getting UserService")
    @Test
    void testGetUserService() {

        Object service = ServiceFactory.getService("UserService");
        assertDoesNotThrow(() -> {
            final IUserService userService = (IUserService) service;
            assertNotNull(userService);
        });
    }

    @DisplayName("Test getting Project Manager Service")
    @Test
    void testGetProjectManagerService() {

        Object service = ServiceFactory.getService("ProjectManagerService");
        assertDoesNotThrow(() -> {
           final IProjectManagerService projectManagerService = (IProjectManagerService) service;
        });
    }

    @DisplayName("Test getting Developer Service")
    @Test
    void testGetDeveloperService() {

        Object service = ServiceFactory.getService("DeveloperService");
        assertDoesNotThrow(() -> {
            final IDeveloperService developerService = (IDeveloperService) service;
        });
    }

    @DisplayName("Test getting Tester Service")
    @Test
    void testGetTesterService() {

        Object service = ServiceFactory.getService("TesterService");
        assertDoesNotThrow(() -> {
            final ITesterService testerService = (ITesterService) service;
        });
    }


}
