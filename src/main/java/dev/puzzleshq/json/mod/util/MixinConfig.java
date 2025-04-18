package dev.puzzleshq.json.mod.util;

// TODO: Document Class
public record MixinConfig(
        String path,
        String environment /* client, server, unknown */
) {}
