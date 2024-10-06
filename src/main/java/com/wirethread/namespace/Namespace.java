package com.wirethread.namespace;

import com.wirethread.plugins.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.regex.Pattern;

/**
 * @see <a href="https://minecraft.fandom.com/wiki/Resource_location">Namespaces</a>
 *
 * @author Francisco M. Prieto Giorgis.
 */
public final class Namespace {
    /**
     * Represents the max characters for a single {@code Namespace}.
     */
    public static final int MAX_NAMESPACE_LENGTH = 256;

    /**
     * Represents the Minecraft namespace.
     */
    public static final String MINECRAFT_NAMESPACE = "minecraft";

    /**
     * Represents the Wirethread namespace.
     */
    public static final String WIRETHREAD_NAMESPACE = "wirethread";

    private static final Pattern NAMESPACE_MATCH_DOMAIN_PATTERN = Pattern.compile("^[a-z0-9.-_]$");
    private static final Pattern NAMESPACE_MATCH_PATH_PATTERN = Pattern.compile("^[a-z0-9.-_/]$");

    private final String domain;
    private final String path;

    /**
     * Construct a new namespace given a domain and a key.
     *
     * @param domain The owner which the namespace belows to.
     * @param path The key which identifies an element.
     * @throws IllegalArgumentException When the given argument values were not valid.
     */
    Namespace(@NotNull String domain, @NotNull String path) throws IllegalArgumentException {
        if (domain==null || domain.isBlank()) {
            throw new IllegalArgumentException("The 'domain' argument cannot be null or empty.");
        }

        if (path == null || path.isBlank()) {
            throw new IllegalArgumentException("The 'key' parameter cannot be null or empty.");
        }

        this.domain = domain;
        this.path = path;

        if (this.toString().length() > MAX_NAMESPACE_LENGTH) {
            throw new IllegalStateException("Namespace length may be less than 255 characters.");
        }
    }

    /**
     * Check if a namespace domain is valid.
     *
     * @return True when the domain is valid, False otherwise.
     */
    public static boolean isValidDomain(@NotNull String domain) {
        return domain != null && NAMESPACE_MATCH_DOMAIN_PATTERN.matcher(domain).matches();
    }

    /**
     * Check if a namespace path is valid.
     *
     * @return True when the path is valid, False otherwise.
     */
    public static boolean isValidPath(@NotNull String path) {
        return path != null && NAMESPACE_MATCH_PATH_PATTERN.matcher(path).matches();
    }

    /**
     * Creates a new namespace belonging to the Wirethread namespace.
     *
     * @param path The namespace path.
     * @return A new namespace.
     * @throws IllegalArgumentException When the {@code path} argument were invalid.
     */
    @NotNull
    public static Namespace wirethread(@NotNull String path) throws IllegalArgumentException {
        return new Namespace(WIRETHREAD_NAMESPACE, path);
    }

    /**
     * Creates a new namespace belonging to the Minecraft namespace.
     *
     * @param path The namespace path.
     * @return A new namespace.
     * @throws IllegalArgumentException When the {@code path} argument were invalid.
     */
    @NotNull
    public static Namespace minecraft(@NotNull String path) throws IllegalArgumentException {
        return  new Namespace(MINECRAFT_NAMESPACE, path);
    }

    /**
     * Create a new {@link Namespace} given a string.
     *
     * @param namespace A namespace as text.
     * @return A new {@link Namespace}.
     * @throws IllegalArgumentException When the given argument were not valid.
     */
    @Nullable
    public static Namespace fromString(@NotNull String namespace) throws IllegalArgumentException {
        if (namespace == null || namespace.isBlank()) {
            throw new IllegalArgumentException("The 'namespace' parameter cannot be null or empty.");
        }

        String[] wildcards = namespace.split(":", 1);

        if (wildcards[0].isBlank() && isValidPath(wildcards[1])) {
            return wirethread(wildcards[1]);
        }

        else if (isValidDomain(wildcards[0]) && isValidPath(wildcards[1])) {
            return new Namespace(wildcards[0], wildcards[1]);
        }

        return null;
    }

    /**
     * Creates a new {@link Namespace} given a string and .
     * @param domain The plugin that owns the namespace.
     * @param namespace A text-like namespace.
     * @return A new namespace.
     */
    @Nullable
    public static Namespace fromString(@Nullable Plugin domain, @NotNull String namespace) {
        if (namespace == null || namespace.isBlank()) {
            throw new IllegalArgumentException("The 'namespace' argument cannot be null or empty.");
        }

        String[] wildcards = namespace.split(":", 1);

        if (domain != null && isValidDomain(domain.getName(Locale.ENGLISH)) && isValidPath(wildcards[1])) {
            return new Namespace(domain.getName(Locale.ENGLISH), wildcards[1]);
        }
        else if (!isValidDomain(wildcards[0]) && isValidPath(wildcards[1])) {
            return new Namespace(WIRETHREAD_NAMESPACE, wildcards[1]);
        }
        else if (isValidDomain(wildcards[0]) && isValidPath(wildcards[1])) {
            return new Namespace(wildcards[0], wildcards[1]);
        }

        return null;
    }

    @Override
    public String toString() {
        return this.domain + ":" + this.path;
    }
}