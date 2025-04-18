package dev.puzzleshq.json.mod.info;

import dev.puzzleshq.json.mod.util.EntrypointPair;
import dev.puzzleshq.json.mod.util.MixinConfig;
import dev.puzzleshq.json.mod.util.ModDependency;
import dev.puzzleshq.json.mod.api.format.IModFormat;
import org.hjson.JsonValue;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: Document Class
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

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addAuthor(String... author) {
        this.authors.addAll(List.of(author));
    }

    public void addMeta(String key, JsonValue value) {
        this.metadata.put(key, value);
    }

    public void addMixinConfig(MixinConfig config) {
        this.mixinConfigs.add(config);
    }

    public void addAccessWriter(String transformer) {
        this.accessWriters.add(transformer);
    }

    public void setLoadableSide(String side, Boolean shouldLoadOn) {
        this.loadableSides.put(side, shouldLoadOn);
    }

    public void addEntrypoint(String key, String value) {
        addEntrypoint(key, new EntrypointPair(value, "java"));
    }

    public void addEntrypoint(String key, EntrypointPair value) {
        List<EntrypointPair> entrypoints = this.entrypointMap.get(key);
        if (entrypoints == null) entrypoints = new ArrayList<>();

        entrypoints.add(value);
        this.entrypointMap.put(key, entrypoints);
    }

    public void addDependency(ModDependency dependency) {
        this.dependencies.add(dependency);
    }

    public void setFormat(IModFormat format) {
        this.format = format;
    }

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

    public List<String> getAuthors() {
        return authors;
    }

    public Map<String, JsonValue> getMetadata() {
        return metadata;
    }

    public Map<String, List<EntrypointPair>> getEntrypointMap() {
        return entrypointMap;
    }

    public List<MixinConfig> getMixinConfigs() {
        return mixinConfigs;
    }

    public List<String> getAccessWriters() {
        return accessWriters;
    }

    public Map<String, Boolean> getLoadableSides() {
        return loadableSides;
    }

    public List<ModDependency> getDependencies() {
        return dependencies;
    }

    @Nullable
    public IModFormat getFormat() {
        return format;
    }

}
