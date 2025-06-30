package dev.puzzleshq.mod.util;

import java.util.Objects;

/**
 * A record of the entrypoint.
 */
public final class EntrypointPair {
    private final String entrypoint;
    private final String adapter;

    /**
     * @param entrypoint the entrypoint path.
     * @param adapter    the entrypoint adapter.
     */
    public EntrypointPair(
            String entrypoint,
            String adapter
    ) {
        this.entrypoint = entrypoint;
        this.adapter = adapter;
    }

    public String entrypoint() {
        return entrypoint;
    }

    public String adapter() {
        return adapter;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        EntrypointPair that = (EntrypointPair) obj;
        return Objects.equals(this.entrypoint, that.entrypoint) &&
                Objects.equals(this.adapter, that.adapter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entrypoint, adapter);
    }

    @Override
    public String toString() {
        return "EntrypointPair[" +
                "entrypoint=" + entrypoint + ", " +
                "adapter=" + adapter + ']';
    }
}
