package dev.puzzleshq.mod.util;

import dev.puzzleshq.mod.ModFormats;
import dev.puzzleshq.mod.api.IModContainer;

import javax.annotation.Nullable;

// TODO: Document Class
public class ModDependency {

    private final String modID;
    private final String constraint;
    private final boolean isOptional;

    public ModDependency(
            String modId,
            String constraint,
            boolean isOptional
    ) {
        this.modID = modId;
        this.constraint = constraint;
        this.isOptional = isOptional;
    }

    public @Nullable IModContainer getContainer() {
        return ModFormats.getContainer(modID);
    }

    public boolean hasCompatibleVersion() {
        IModContainer container = getContainer();
        if (container == null) return false;
        return container.getVersion().satisfies(constraint);
    }

    public String getConstraint() {
        return constraint;
    }

    public boolean isOptional() {
        return isOptional;
    }

    public String getModID() {
        return modID;
    }
}
