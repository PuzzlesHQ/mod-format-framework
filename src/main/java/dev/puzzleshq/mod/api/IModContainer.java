package dev.puzzleshq.mod.api;

import com.github.zafarkhaja.semver.Version;
import dev.puzzleshq.mod.info.ModInfo;

// TODO: Document Class
public interface IModContainer {

    String getDisplayName();
    String getID();
    Version getVersion();

    ModInfo getInfo();

    int getPriority();
    void bumpPriority();

}
