package dev.puzzleshq.mod.util;

/**
 * A record of the entrypoint.
 *
 * @param entrypoint the entrypoint path.
 * @param adapter the entrypoint adapter.
 */
public record EntrypointPair(
        String entrypoint,
        String adapter
) {}
