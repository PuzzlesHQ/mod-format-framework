package dev.puzzleshq.mod.util;

import java.util.Objects;

/**
 * A record of the mixin config file.
 */
public final class MixinConfig {
    private final String path;
    private final String environment;

    /**
     * @param path        the path to the mixin config file.
     * @param environment the environment this mixin config is intended for.
     */
    public MixinConfig(
            String path,
            String environment /* client, server, unknown */
    ) {
        this.path = path;
        this.environment = environment;
    }

    public String path() {
        return path;
    }

    public String environment() {
        return environment;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        MixinConfig that = (MixinConfig) obj;
        return Objects.equals(this.path, that.path) &&
                Objects.equals(this.environment, that.environment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, environment);
    }

    @Override
    public String toString() {
        return "MixinConfig[" +
                "path=" + path + ", " +
                "environment=" + environment + ']';
    }
}
