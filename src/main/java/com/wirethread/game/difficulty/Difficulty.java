package com.wirethread.game.difficulty;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * Represents the game difficulty.
 *
 * @author Francisco M. Prieto Giorgis.
 */
public enum Difficulty {
    PEACEFUL(0, "peaceful"),
    EASY(1, "easy"),
    NORMAL(2, "normal"),
    HARD(3,"hard");

    private final String key;
    private final int id;

    private final static Map<Number, Difficulty> idHash = Map.of(
            0, PEACEFUL,
            1, EASY,
            2, NORMAL,
            3, HARD
    );

    private final static Map<String, Difficulty> nameHash = Map.of(
            "peaceful", PEACEFUL,
            "easy", EASY,
            "normal", NORMAL,
            "hard", HARD
    );

    Difficulty(int id, String key) {
        this.id = id;
        this.key = key;
    }

    /**
     * Retrieves the {@link Difficulty} id.
     *
     * @return The difficulty id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Retrieves a string which represents this {@link Difficulty} name.
     *
     * @return A difficulty string.
     */
    public String getName() {
        return this.key;
    }

    /**
     * Returns a {@code Difficulty} given the name.
     *
     * @param name A difficulty name, i.e: "normal" or "hard".
     * @return A {@code Difficulty} instance.
     * @throws NullPointerException When the name weren't mapped.
     */
    @NotNull
    public static Difficulty byName(String name) {
        return Difficulty.nameHash.get(name);
    }

    /**
     * Returns a {@code Difficulty} given the id.
     *
     * @param id The difficulty id, i.e: 0 (which represents "peaceful").
     * @return A {@code Difficulty} instance.
     * @throws NullPointerException When the id weren't mapped.
     */
    @NotNull
    public static Difficulty byID(int id) {
        return Difficulty.idHash.get(id);
    }


    @NotNull
    public static <V> Difficulty converter(@NotNull V any) {
        switch (any) {
            case Integer x -> {
                return byID(x);
            }
            case String x -> {
                return byName(x);
            }
            case null, default -> throw new IllegalArgumentException("this is an invalid object");
        }
    }
}
