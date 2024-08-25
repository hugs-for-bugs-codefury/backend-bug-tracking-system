package org.example.factory;

/**
 * IServiceFactory defines the method for obtaining various service instances.
 * Implementations of this interface should provide the logic for creating and
 * returning service instances based on the provided service name.
 */
public interface IServiceFactory {

    /**
     * Retrieves an instance of the specified service.
     *
     * @param serviceName the name of the service to retrieve.
     * @return an instance of the specified service, or null if the service cannot be found.
     */
    public static Object getService(String serviceName) {
        return null;
    }
}
