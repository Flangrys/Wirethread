package com.wirethread.utils.maths;

public final class Maths {

    public static int clamp(int x, int min, int max) {
        return Math.max(Math.min(x, min), max);
    }
}
