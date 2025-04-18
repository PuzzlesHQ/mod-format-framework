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

//TODO: Document Class
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

    public String getDisplayName() {
        return displayName;
    }

    public String getId() {
        return id;
    }

    public String getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }

    public String[] getAuthors() {
        return authors;
    }

    public Map<String, JsonValue> getMetadata() {
        return metadata;
    }

    public Map<String, EntrypointPair[]> getEntrypointMap() {
        return entrypointMap;
    }

    public MixinConfig[] getMixinConfigs() {
        return mixinConfigs;
    }

    public String[] getAccessTransformers() {
        return accessTransformers;
    }

    public Map<String, Boolean> getLoadableSides() {
        return loadableSides;
    }

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
