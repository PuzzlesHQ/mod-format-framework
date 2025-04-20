package dev.puzzleshq.mod.info;

import dev.puzzleshq.mod.ModFormats;
import dev.puzzleshq.mod.util.EntrypointPair;
import dev.puzzleshq.mod.util.MixinConfig;
import dev.puzzleshq.mod.util.ModDependency;
import dev.puzzleshq.mod.api.format.IModFormat;
import org.hjson.JsonObject;
import org.hjson.JsonValue;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * Represents the mod.json of a mod.
 *
 * @since 1.0.0
 * @author Mr-Zombii
 */
public class ModInfo {

    private final IModFormat format;

    private final String displayName;
    private final String id;
    private final String version;
    private final String description;
    private final String[] authors;
    private final Map<String, JsonValue> metadata;

    private final Map<String, EntrypointPair[]> entrypointMap;
    private final MixinConfig[] mixinConfigs;
    private final String[] accessTransformers;

    private final Map<String, Boolean> loadableSides;
    private final ModDependency[] dependencies;

    public ModInfo(
            @Nullable IModFormat format,
            String displayName,
            String id,
            String version,
            String description,
            String[] authors,
            Map<String, JsonValue> metadata,
            Map<String, EntrypointPair[]> entrypointMap,
            MixinConfig[] mixinConfigs,
            String[] accessTransformers,
            Map<String, Boolean> loadableSides,
            ModDependency[] dependencies
    ) {
        this.format = format;
        this.displayName = displayName;
        this.id = id;
        this.version = version;
        this.description = description;
        this.authors = authors;
        this.metadata = metadata;
        this.entrypointMap = entrypointMap;
        this.mixinConfigs = mixinConfigs;
        this.accessTransformers = accessTransformers;
        this.loadableSides = loadableSides;
        this.dependencies = dependencies;
    }

    /**
     * Gets the mods display name.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the mods ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the mods Version.
     */
    public String getVersion() {
        return version;
    }

    /**
     * Gets the mods description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the mods authors.
     * @return an array of strings.
     */
    public String[] getAuthors() {
        return authors;
    }

    /**
     * Gets the mods meta data.
     * @return an {@link Map} of {@link String} and {@link JsonValue}.
     */
    public Map<String, JsonValue> getMetadata() {
        return metadata;
    }

    /**
     * Gets the mods entrypoint map.
     * @return a {@link Map} the key is the entrypoint name and the value is an array of {@link EntrypointPair}.
     */
    public Map<String, EntrypointPair[]> getEntrypointMap() {
        return entrypointMap;
    }

    /**
     * Gets the mods mixin configs.
     * @return an array of {@link MixinConfig}.
     */
    public MixinConfig[] getMixinConfigs() {
        return mixinConfigs;
    }

    /**
     * Gets the mods access transformers.
     * @return an array of {@link String}.
     */
    public String[] getAccessTransformers() {
        return accessTransformers;
    }

    /**
     * Gets the sides the mod can be loaded.
     * @return a {@link Map} the key is the side and the value is a {@link Boolean}
     */
    public Map<String, Boolean> getLoadableSides() {
        return loadableSides;
    }

    /**
     * Gets the mods dependencies.
     * @return an array of {@link ModDependency}
     */
    public ModDependency[] getDependencies() {
        return dependencies;
    }

    /**
     * Gets the modInfos format.
     * @return {@link IModFormat}
     */
    public IModFormat getFormat() {
        return format;
    }

    /**
     * Reads a string to a manipulation file.
     * @param contents the contents of the manipulator.
     *
     * @see IModFormat
     */
    public static ModInfo readFromString(String contents) {
        JsonObject object = JsonObject.readHjson(contents).asObject();
        IModFormat format = ModFormats.getFormat(object.getInt("formatVersion", 3));
        ModInfoBuilder builder = new ModInfoBuilder();
        format.parse(new ModInfoBuilder(), object);
        return builder.build();
    }
}
