package dev.puzzleshq.mod.util;

import com.github.villadora.semver.SemVer;
import com.github.villadora.semver.ranges.Range;
import dev.puzzleshq.mod.ModFormats;
import dev.puzzleshq.mod.api.IModContainer;

import javax.annotation.Nullable;

/**
 * Represents a dependency on another mod.
 *
 * @since 1.0.0
 * @author Mr-Zombii
 */
public class ModDependency {

    private final String modID;
    private final String constraintStr;
    private final Range constraint;
    private final boolean isOptional;

    public ModDependency(
            String modId,
            String constraint,
            boolean isOptional
    ) {
        constraint = constraint.replaceAll("\\+.*", "");

        this.modID = modId;
        this.constraint = Range.valueOf(constraint);
        this.constraintStr = constraint;
        this.isOptional = isOptional;
    }

    /**
     * Gets the {@link IModContainer} of the mod.
     * @return {@link IModContainer}
     */
    public @Nullable IModContainer getContainer() {
        return ModFormats.getContainer(modID);
    }

    /**
     * Checks if a compatible version of the mod is loaded.
     */
    public boolean hasCompatibleVersion() {
        IModContainer container = getContainer();
        if (container == null) return false;
        return SemVer.satisfies(container.getVersion(), getConstraint());
    }

    /**
     * Gets the version constraints.
     */
    public Range getConstraint() {
        return constraint;
    }

    /**
     * Gets the version constraints as a string.
     */
    public String getConstraintStr() {
        return constraintStr;
    }

    /**
     * Checks if the mod is optional.
     */
    public boolean isOptional() {
        return isOptional;
    }

    /**
     * Gets the mod ID.
     */
    public String getModID() {
        return modID;
    }
}
