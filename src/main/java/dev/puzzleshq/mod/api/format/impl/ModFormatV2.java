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
            JsonArray authors = object.get("authors").asArray();
            for (JsonValue v : authors) builder.addAuthor(v.asString());
        } catch (Exception ignore) {}

        try {
            JsonObject meta = object.get("meta").asObject();
            for (String s : meta.names()) builder.addMeta(s, meta.get(s));
        } catch (Exception ignore) {}

        try {
            JsonObject entrypoints = object.get("entrypoints").asObject();
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
        } catch (Exception ignore) {}

        try {
            JsonObject loadableSides = object.get("allowedSides").asObject();
            builder.setLoadableSide("client", loadableSides.getBoolean("client", true));
            builder.setLoadableSide("server", loadableSides.getBoolean("server", true));
        } catch (Exception ignore) {
            builder.setLoadableSide("client", true);
            builder.setLoadableSide("server", true);
        }

        try {
            JsonArray mixins = object.get("mixins").asArray();
            for (JsonValue value : mixins) {
                if (!value.isObject()) {
                    builder.addMixinConfig(new MixinConfig(value.asString(), "unknown"));
                } else {
                    JsonObject mixin = value.asObject();
                    builder.addMixinConfig(new MixinConfig(mixin.get("config").asString(), mixin.get("environment").asString()));
                }
            }
        } catch (Exception ignore) {}

        try {
            JsonObject dependencies = object.get("depends").asObject();
            for (String s : dependencies.names()) {
                JsonValue value = dependencies.get(s);
                if (!value.isObject()) {
                    builder.addDependency(new ModDependency(s, value.asString(), false));
                } else {
                    JsonObject dependency = value.asObject();
                    builder.addDependency(new ModDependency(s, dependency.get("ver").asString(), !dependency.getBoolean("isRequired", true)));
                }
            }
        } catch (Exception ignore) {}

        try {
            JsonArray accessWriter = object.get("accessTransformers").asArray();
            for (JsonValue v : accessWriter) builder.addAccessWriter(v.asString());
        } catch (Exception ignore) {}
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
