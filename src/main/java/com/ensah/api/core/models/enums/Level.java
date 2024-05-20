package com.ensah.api.core.models.enums;

public enum Level {
    L1(1),
    L2(2),
    L3(3);

    private final int level;

    Level(int i) {
        this.level = i;
    }

    int getLevel() {
        return level;
    }
}
