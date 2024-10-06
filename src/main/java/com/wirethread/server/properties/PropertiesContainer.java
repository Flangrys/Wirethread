package com.wirethread.server.properties;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.nio.file.Path;
import java.util.Properties;
import java.util.function.Function;

/**
 * Represents a persistent container for storing the server properties and consuming.
 * <p></p>
 * This hashmap not necessarily stores a concrete value, like a number or a boolean.
 * In some cases, this value is a string which represents a concrete value, like an
 * enum member or some literal object. In that cases, is necessary to use a function
 * called deserializer which takes that string and converts into a canonical value.
 *
 * @param <T> A class which stores contains properties.
 *
 * @author Francisco M. Prieto Giorgis.
 */
public abstract class PropertiesContainer<T extends PropertiesContainer<T>> implements Cloneable {

    protected final Properties properties;

    /**
     * Creates a new persistent properties' container.
     *
     * @param properties The initial properties values.
     * @throws IllegalArgumentException When the properties argument were null.
     */
    public PropertiesContainer(@NotNull Properties properties) throws IllegalArgumentException {
        if (properties == null) {
            throw new IllegalArgumentException("Cannot provide a null property set.");
        }

        this.properties = properties;
    }

    /**
     * Creates a new {@code Properties} given a server.properties file.
     *
     * @return A new {@code Properties}.
     */
    @NotNull
    public static Properties loadFromFile(@NotNull Path filePath) { return null; }

    /**
     * Creates a new file to dump the given properties set.
     *
     * @param properties A {@code Properties} object.
     */
    public static void saveToFile(@NotNull Properties properties, @NotNull Path filePath) {}

    /**
     * Reload server properties at runtime.
     */
    public static void reload() {}

    /**
     * Retrieves a property value given a key.
     *
     * @param key The key whose associated with the property to return.
     * @param deserializer A function that takes the representation of it and return a concrete property.
     * @param serializer A function that takes the concrete property and returns a string.
     * @param defaultValue The default property value.
     * @return The property value.
     * @param <V> A generic type for this property value.
     */
    @NotNull
    protected <V> V get(@NotNull String key, Function<String, V> deserializer, Function<V, String> serializer, @NotNull V defaultValue) {
        if (key == null || key.isBlank()) {
            throw new IllegalArgumentException("the argument 'key' cannot be null.");
        }

        if (defaultValue == null) {
            throw new IllegalArgumentException("the 'defaultValue' argument cannot be null.");
        }

        // Tries to get a canonical value associated with the given key.
        String propertyValue = this.getRawPropertyValue(key);

        // Return the default value if there isn't registered in the hashmap.
        if (propertyValue == null) {
            return defaultValue;
        }

        // Call the method which retrieves a concrete object.
        V property = deserializer.apply(propertyValue);

        // If there's not a candidate for the property object, return a default value.
        if (property == null) {
            return defaultValue;
        }

        // Canonize the property using a serializer which retrieves a
        // string representation of it.
        String canonicalProperty = serializer.apply(property);

        // Update the property in the hashmap.
        this.properties.put(key, canonicalProperty);

        return (V) property;
    }

    protected <V> V get(@NotNull String key, @NotNull V defaultValue) {
        if (key == null || key.isBlank()) {
            throw new IllegalArgumentException("the argument 'key' cannot be null.");
        }

        if (defaultValue == null) {
            throw new IllegalArgumentException("the 'defaultValue' argument cannot be null.");
        }

        V propertyValue = (V) this.properties.get(key);

        if (propertyValue == null) {
            return defaultValue;
        }

        return propertyValue;
    }

    protected <V> V get(String key, Function<V, V> converterSupplier, V defaultValue) {
        if (key == null || key.isBlank()) {
            throw new IllegalArgumentException("the argument 'key' cannot be null.");
        }

        if (defaultValue == null) {
            throw new IllegalArgumentException("the 'defaultValue' argument cannot be null.");
        }

        V propertyValue = (V) this.properties.get(key);

        if (propertyValue == null) {
            return defaultValue;
        }

        // Invoke the conversion supplier.
        V property = converterSupplier.apply(propertyValue);

        // If there's not a candidate for this property return the default value.
        if (property == null) {
            return defaultValue;
        }

        return property;
    }

    protected Properties clone() {
        Properties newProperties = new Properties();

        newProperties.putAll(this.properties);

        return newProperties;
    }

    /**
     * Retrieves a string which represents a canonical value representation which is
     * associated with the given key.
     * <p>
     * i.e: "difficulty": "peaceful" or "difficulty": 0
     * <p>
     * NOTE: In this case, is required to use a conversion method to convert
     * legacy values to newer values.
     *
     * @param key The property key.
     * @return A canonical value.
     *
     * @see <a href="https://minecraft.fandom.com/wiki/Server.properties#Option_keys">server.properties</a>
     */
    @Nullable
    private String getRawPropertyValue(@NotNull String key) {
        return (String) this.properties.get(key);
    }
}
