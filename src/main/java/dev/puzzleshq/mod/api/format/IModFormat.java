package dev.puzzleshq.mod.api.format;

import dev.puzzleshq.mod.info.ModInfoBuilder;
import org.hjson.JsonObject;

// TODO: Document Class
public interface IModFormat {

    void parse(ModInfoBuilder builder, JsonObject object);
    String name();
    int spec();

}
