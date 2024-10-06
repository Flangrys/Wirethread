package com.wirethread.game.gamemode;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public enum GameMode {
    SURVIVAL(0, "survival"),
    CREATIVE(1, "creative"),
    ADVENTURE(2, "adventure"),
    SPECTATOR(3, "spectator"),
    ;

    private static final Map<Number, GameMode> idHash = Map.of(
            0, SURVIVAL,
            1, CREATIVE,
            2, ADVENTURE,
            3, SPECTATOR
    );

    private static final Map<String, GameMode> nameHash = Map.of(
            "survival", SURVIVAL,
            "creative", CREATIVE,
            "adventure", ADVENTURE,
            "spectator", SPECTATOR
    );

    private final int id;
    private final String name;

    GameMode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private static GameMode byID(Integer x) {
        return GameMode.idHash.get(x);
    }

    private static GameMode byName(String x) {
        return GameMode.nameHash.get(x);
    }

    public static <V> GameMode converter(@NotNull V any) {
        switch (any) {
            case Integer x -> {
                return byID(x);
            }
            case String x -> {
                return byName(x);
            }
            case null, default -> throw new IllegalArgumentException("cannot convert this object to 'GameMode'");
        }
    }


}
