package com.wirethread.server;

import org.jetbrains.annotations.NotNull;

public interface ServerInfo {

    @NotNull
    String getServerMOTD();

    int getServerPort();

    @NotNull
    String getServerIp();

    @NotNull
    String getServerName();

    @NotNull
    String getServerVersion();

    @NotNull
    String getCanonicalVersion();

    @NotNull
    String[] getPlayersNames();

    int getPlayersCount();

    int getMaxPlayers();

    @NotNull
    String getWorldId();

    @NotNull
    String[] getPluginNames();
}
