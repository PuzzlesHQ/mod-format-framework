package dev.puzzleshq.mod.api;

import com.github.villadora.semver.Version;
import dev.puzzleshq.mod.info.ModInfo;

/**
 * A container for a mod.
 *
 * @since 1.0.0
 * @author Mr-Zombii
 */
public interface IModContainer {

    /**
     * Gets the display name.
     */
    String getDisplayName();

    /**
     * Gets the ID.
     */
    String getID();

    /**
     * Gets the version.
     */
    Version getVersion();

    /**
     * Gets the version string.
     */
    String getVersionStr();

    /**
     * Gets the mod info.
     */
    ModInfo getInfo();

    /**
     * Gets the load priority.
     */
    int getPriority();

    /**
     * Ups the priority.
     */
    void bumpPriority();

    /**
     * Gets the entrypoint container.
     * @see IEntrypointContainer
     */
    IEntrypointContainer getEntrypointContainer();

}
