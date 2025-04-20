package dev.puzzleshq.mod.util;

/**
 * A record of the mixin config file.
 *
 * @param path the path to the mixin config file.
 * @param environment the environment this mixin config is intended for.
 */
public record MixinConfig(
        String path,
        String environment /* client, server, unknown */
) {}
