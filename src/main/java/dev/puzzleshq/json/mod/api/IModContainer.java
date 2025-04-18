package dev.puzzleshq.json.mod.api;

import com.github.zafarkhaja.semver.Version;
import dev.puzzleshq.json.mod.info.ModInfo;

// TODO: Document Class
public interface IModContainer {

    String getDisplayName();
    String getModID();
    Version getVersion();

    ModInfo getInfo();

    int getPriority();
    void bumpPriority();

}
