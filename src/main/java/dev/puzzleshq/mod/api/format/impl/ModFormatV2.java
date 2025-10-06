package dev.puzzleshq.mod.api.format.impl;

import dev.puzzleshq.mod.api.format.IModFormat;
import dev.puzzleshq.mod.info.ModInfoBuilder;
import dev.puzzleshq.mod.util.EntrypointPair;
import dev.puzzleshq.mod.util.MixinConfig;
import dev.puzzleshq.mod.util.ModDependency;
import org.hjson.JsonArray;
import org.hjson.JsonObject;
import org.hjson.JsonValue;

public class ModFormatV2 implements IModFormat {

    @Override
    public void parse(ModInfoBuilder builder, JsonObject object) {
        builder.setId(object.get("id").asString());
        builder.setDisplayName(object.getString("name", builder.getId()));
        builder.setDescription(object.getString("description", ""));
        builder.setVersion(object.getString("version", "0.0.0"));

        try {
            JsonValue authors = object.get("authors");
            if (authors != null)
                for (JsonValue v : authors.asArray()) builder.addAuthor(v.asString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            JsonValue metaValue = object.get("meta");
            if (metaValue != null) {
                JsonObject meta = metaValue.asObject();
                for (String s : meta.names()) builder.addMeta(s, meta.get(s));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            JsonValue entrypointsValue = object.get("entrypoints");
            if (entrypointsValue != null) {
                JsonObject entrypoints = entrypointsValue.asObject();
                for (String s : entrypoints.names()) {
                    JsonValue value = entrypoints.get(s);
                    if (value.isString()) {
                        builder.addEntrypoint(s, new EntrypointPair(value.asString(), "java"));
                        continue;
                    }
                    if (value.isObject()) {
                        JsonObject entrypoint = value.asObject();
                        builder.addEntrypoint(s, new EntrypointPair(entrypoint.get("value").asString(), entrypoint.get("adapter").asString()));
                        continue;
                    }
                    for (JsonValue v : value.asArray()) {
                        if (v.isString()) {
                            builder.addEntrypoint(s, new EntrypointPair(v.asString(), "java"));
                            continue;
                        }
                        JsonObject entrypoint = v.asObject();
                        builder.addEntrypoint(s, new EntrypointPair(entrypoint.get("value").asString(), entrypoint.get("adapter").asString()));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            JsonObject loadableSides = object.get("allowedSides").asObject();
            builder.setLoadableSide("client", loadableSides.getBoolean("client", true));
            builder.setLoadableSide("server", loadableSides.getBoolean("server", true));
        } catch (Exception ignore) {
            builder.setLoadableSide("client", true);
            builder.setLoadableSide("server", true);
        }
        builder.setLoadableSide("unknown", true);

        try {
            JsonValue mixinsValue = object.get("mixins");
            if (mixinsValue != null) {
                JsonArray mixins = mixinsValue.asArray();
                for (JsonValue value : mixins) {
                    if (!value.isObject()) {
                        builder.addMixinConfig(new MixinConfig(value.asString(), "unknown"));
                    } else {
                        JsonObject mixin = value.asObject();
                        builder.addMixinConfig(new MixinConfig(mixin.get("config").asString(), mixin.get("environment").asString()));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            JsonValue dependenciesValue = object.get("depends");
            if (dependenciesValue != null) {
                JsonObject dependencies = dependenciesValue.asObject();
                for (String s : dependencies.names()) {
                    JsonValue value = dependencies.get(s);
                    if (!value.isObject()) {
                        builder.addDependency(new ModDependency(s, value.asString(), false));
                    } else {
                        JsonObject dependency = value.asObject();
                        builder.addDependency(new ModDependency(s, dependency.get("ver").asString(), dependency.getBoolean("isOptional", false)));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            JsonValue accessWriterValue = object.get("accessTransformers");
            if (accessWriterValue != null) {
                JsonArray accessWriter = accessWriterValue.asArray();
                for (JsonValue v : accessWriter) builder.addAccessWriter(v.asString());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String name() {
        return "ModJson format v2";
    }

    @Override
    public int spec() {
        return 2;
    }
}
