package com.wirethread.server.properties;

import com.wirethread.game.difficulty.Difficulty;
import com.wirethread.game.gamemode.GameMode;
import com.wirethread.utils.maths.Maths;

import java.util.concurrent.TimeUnit;

/**
 * Represents the default server properties.
 *
 * @see <a href="https://minecraft.fandom.com/wiki/Server.properties">server.properties</a>
 * @author Francisco M. Prieto Giorgis.
 */
public final class Properties extends PropertiesContainer<Properties> {

    public final boolean enableRelay = this.get("enable-relay", true);
    public final boolean enableRelayBroadcast = this.get("enable-relay-broadcast", true);
    public final boolean enableOnlineMode = this.get("enable-online-mode", true);
    public final boolean enableServerStatus = this.get("enable-server-status", true);
    public final boolean enableWhiteList = this.get("enable-whitelist", false);
    public final boolean enableHardcore = this.get("enable-hardcore", false);
    public final boolean enableChunkSync = this.get("enable-chunk-sync-writes", false);
    public final boolean enableSecureProfileEnforcement = this.get("enable-secure-profile-enforcement", true);

    public final boolean allowProxyConnections = this.get("allow-proxy-connections", true);
    public final boolean allowPlayerAchievementsAnnouncement = this.get("allow-player-achievements-announcement", true);
    public final boolean allowNether = this.get("allow-nether", true);
    public final boolean allowEnd = this.get("allow-end", true);
    public final boolean allowFlight = this.get("allow-flight", true);
    public final boolean allowPvp = this.get("allow-pvp", true);
    public final boolean allowCommandBlocks = this.get("allow-command-blocks", false);
    public final boolean allowStructureGeneration = this.get("allow-structure-generation", true);
    public final boolean allowAnimalsSpawn = this.get("allow-animals-spawn", true);
    public final boolean allowMonstersSpawn = this.get("allow-monsters-spawn", true);
    public final boolean allowNPCsSpawn = this.get("allow-npcs-spawn", true);
    public final boolean allowHiddeOnlinePlayers = this.get("allow-hidde-online-players", false);

    public final String setServerMotd = this.get("set-server-motd", "A Wirethread Server");
    public final String setServerIp = this.get("set-server-ip", "localhost");
    public final int setServerPort = this.get("set-server-port", 25565);
    public final int setRelayPort = this.get("set-relay-port", 25575);
    public final String setRelayBroadcastChannels = this.get("set-relay-broadcast-channels", "console;global;ops");
    public final long setMaxTickTime = this.get("set-max-tick-time", TimeUnit.MINUTES.toMillis(1L));
    public final int setSimulationDistance = this.get("set-simulation-distance", 10);
    public final int setRenderDistance = this.get("set-render-distance", 10);
    public final int setSpawnProtectionRadius = this.get("set-spawn-protection-radius", 16);
    public final String setWorldLevel = this.get("set-world-level", "world");
    public final String setWorldLevelSeed = this.get("set-level-seed", "");
    public final String setWorldLevelType = this.get("set-world-level-type", "normal");
    public final Difficulty setDifficulty = this.get("set-difficulty", Difficulty::converter, Difficulty.NORMAL);
    public final GameMode setGamemode = this.get("set-gamemode", GameMode::converter, GameMode.SURVIVAL);
    public final int setMaxPlayers = this.get("set-max-players", 10000);
    public final int setPlayerIdleTimeout = this.get("set-player-idle-timeout", 10);
    public final int setMaxWorldSize = this.get("set-max-world-size", (val) -> {return Maths.clamp(val, 1, 29999984);}, 29999984);
    public final int setMCFunctionsPermissionsLevel = this.get("set-mc-functions-permissions-level", 1);
    public final int setOPPermissionsLevel = this.get("set-op-permissions-level", 4);
    public final int setMaxNeighboursChainUpdates = this.get("set-max-neighbours-chain-updates", 1000000);
    public final int setRateLimitPacketsPerSecond = this.get("set-rate-limit-packets-per-second", 0);

    /**
     * Creates a new persistent properties' container.
     *
     * @param properties The initial properties values.
     */
    public Properties(java.util.Properties properties) {
        super(properties);
    }
}
