package dev.puzzleshq.mod.info;

import dev.puzzleshq.mod.util.EntrypointPair;
import dev.puzzleshq.mod.util.MixinConfig;
import dev.puzzleshq.mod.util.ModDependency;
import dev.puzzleshq.mod.api.format.IModFormat;
import org.hjson.JsonValue;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A builder class for {@link ModInfo}.
 *
 * @since 1.0.0
 * @author Mr-Zombii
 */
public class ModInfoBuilder {

    private String displayName;
    private String id;
    private String version;
    private String description;
    private final List<String> authors;
    private final Map<String, JsonValue> metadata;

    private final Map<String, List<EntrypointPair>> entrypointMap;

    private final List<MixinConfig> mixinConfigs;
    private final List<String> accessWriters;

    private final Map<String, Boolean> loadableSides;
    private final List<ModDependency> dependencies;
    private IModFormat format;

    public ModInfoBuilder() {
        this.loadableSides = new HashMap<>();
        this.metadata = new HashMap<>();
        this.entrypointMap = new HashMap<>();
        this.authors = new ArrayList<>();
        this.mixinConfigs = new ArrayList<>();
        this.accessWriters = new ArrayList<>();
        this.dependencies = new ArrayList<>();
    }

    /**
     * Sets the display name.
     * @param displayName the new display name.
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Sets the ID.
     * @param id the new ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets the version.
     * @param version the new version.
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Sets the description.
     * @param description the new description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Adds one or more authors.
     * @param author the author to add.
     */
    public void addAuthor(String... author) {
        this.authors.addAll(List.of(author));
    }

    /**
     * Adds to the meta data map.
     * @param key the key of the data.
     * @param value the value of the data.
     */
    public void addMeta(String key, JsonValue value) {
        this.metadata.put(key, value);
    }

    /**
     * Adds a mixin config.
     * @param config the new config to add.
     * @see MixinConfig
     */
    public void addMixinConfig(MixinConfig config) {
        this.mixinConfigs.add(config);
    }

    /**
     * Adds an Access writer file.
     * @param transformer the new access writer file name to add.
     */
    public void addAccessWriter(String transformer) {
        this.accessWriters.add(transformer);
    }

    /**
     * Adds what sides the mod should it load on.
     * @param side the sides to load on.
     * @param shouldLoadOn if it should load on.
     */
    public void setLoadableSide(String side, Boolean shouldLoadOn) {
        this.loadableSides.put(side, shouldLoadOn);
    }

    /**
     * Adds a new entrypoint.
     * @param key the entrypoint name.
     * @param value the entrypoint class.
     */
    public void addEntrypoint(String key, String value) {
        addEntrypoint(key, new EntrypointPair(value, "java"));
    }

    /**
     * Adds a new entrypoint.
     * @param key the entrypoint name.
     * @param value the new {@link EntrypointPair}.
     */
    public void addEntrypoint(String key, EntrypointPair value) {
        List<EntrypointPair> entrypoints = this.entrypointMap.get(key);
        if (entrypoints == null) entrypoints = new ArrayList<>();

        entrypoints.add(value);
        this.entrypointMap.put(key, entrypoints);
    }

    /**
     * Adds a new dependency.
     * @param dependency the new {@link ModDependency}.
     * @see ModDependency
     */
    public void addDependency(ModDependency dependency) {
        this.dependencies.add(dependency);
    }

    /**
     * Sets the format of the mod.json.
     * @param format the {@link IModFormat}.
     */
    public void setFormat(IModFormat format) {
        this.format = format;
    }

    /**
     * Builds the modInfo.
     * @return {@link ModInfo}
     */
    public ModInfo build() {
        return new ModInfo(
                format,
                displayName,
                id,
                version,
                description,
                authors.toArray(String[]::new),
                metadata,
                ModInfoBuilder.buildMap(entrypointMap),
                mixinConfigs.toArray(MixinConfig[]::new),
                accessWriters.toArray(String[]::new),
                loadableSides,
                dependencies.toArray(ModDependency[]::new)
        );
    }

    private static Map<String, EntrypointPair[]> buildMap(Map<String, List<EntrypointPair>> entrypointIndexMap) {
        Map<String, EntrypointPair[]> map = new HashMap<>();

        for (Map.Entry<String, List<EntrypointPair>> entry : entrypointIndexMap.entrySet()) {
            map.put(entry.getKey(), entry.getValue().toArray(EntrypointPair[]::new));
        }

        return map;
    }

    /**
     * Gets the display name.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the version.
     */
    public String getVersion() {
        return version;
    }

    /**
     * Gets the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the authors.
     */
    public List<String> getAuthors() {
        return authors;
    }

    /**
     * Gets the MetaData.
     */
    public Map<String, JsonValue> getMetadata() {
        return metadata;
    }

    /**
     * Gets the Entrypoint map.
     * @return a {@link Map} the key is the entrypoint name and the value is a {@link List} of {@link EntrypointPair}.
     */
    public Map<String, List<EntrypointPair>> getEntrypointMap() {
        return entrypointMap;
    }

    /**
     * Gets the MixinConfigs.
     * @return a {@link List} of {@link MixinConfig}.
     */
    public List<MixinConfig> getMixinConfigs() {
        return mixinConfigs;
    }

    /**
     * Gets the access writers.
     * @return a {@link List}
     */
    public List<String> getAccessWriters() {
        return accessWriters;
    }

    /**
     * Gets the loadable sides.
     * @return a {@link Map} the key is the side
     * and the value is {@code true} if it should load
     * and {@code false} if it shouldn't load.
     */
    public Map<String, Boolean> getLoadableSides() {
        return loadableSides;
    }

    /**
     * Gets the dependencies.
     * @return a {@link List} of {@link ModDependency}.
     */
    public List<ModDependency> getDependencies() {
        return dependencies;
    }

    /**
     * Gets the format.
     * @return {@link IModFormat}
     */
    @Nullable
    public IModFormat getFormat() {
        return format;
    }

}
