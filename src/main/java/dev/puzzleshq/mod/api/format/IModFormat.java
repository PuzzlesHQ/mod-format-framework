package dev.puzzleshq.mod.api.format;

import dev.puzzleshq.mod.info.ModInfoBuilder;
import org.hjson.JsonObject;

/**
 * The mod.json format.
 *
 * @since 1.0.0
 * @author Mr-Zombii
 */
public interface IModFormat {

    /**
     * Parses the mod.json.
     * @param builder the {@link ModInfoBuilder} to use.
     * @param object the json of the mod.json.
     */
    void parse(ModInfoBuilder builder, JsonObject object);

    /**
     * Gets the format name.
     */
    String name();

    /**
     * Gets the format version.
     */
    int spec();

}
