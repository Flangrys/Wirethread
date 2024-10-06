package com.wirethread.server;

import com.wirethread.server.properties.Properties;
import com.wirethread.server.properties.PropertiesContainer;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a properties container which stores
 * a set of configurations for the game server.
 *
 * @see PropertiesContainer
 * @author Francisco M. Prieto Giorgis.
 */
public interface ServerProperties {

    /**
     * Retrieves the server properties.
     *
     * @see PropertiesContainer
     * @return A set of properties.
     */
    @NotNull
    Properties getProperties();
}
