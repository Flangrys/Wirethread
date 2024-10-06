package com.wirethread.plugins;

import com.wirethread.server.Server;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public interface Plugin {

    @NotNull
    Server getServer();

    /**
     * Retrieves the plugin name.
     *
     * @param localization The region chosen for displaying the text.
     * @return The plugin name.
     */
    @NotNull String getName(Locale localization);
}
