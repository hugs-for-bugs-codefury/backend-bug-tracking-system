package org.example.factory.impl;

import org.example.factory.IServiceFactory;
import org.example.models.User;
import org.example.services.impl.*;

import java.util.Objects;

public abstract class ServiceFactory implements IServiceFactory {
    public static Object getService(String serviceName) {
        if (Objects.equals(serviceName, "UserService")) {
            return new UserServiceImpl();

        } else if (Objects.equals(serviceName, "TesterService")) {
            return new TesterServiceImpl();
        } else if (Objects.equals(serviceName, "DeveloperService")) {
            return new DeveloperServiceImpl();
        } else if (Objects.equals(serviceName, "ProjectManagerService")) {
            return new ProjectManagerServiceImpl();
        }
        return new Object();
    }



}
