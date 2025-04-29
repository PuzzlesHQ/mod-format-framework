package dev.puzzleshq.mod;

import dev.puzzleshq.mod.api.IModContainer;
import dev.puzzleshq.mod.api.format.IModFormat;
import dev.puzzleshq.mod.api.format.impl.ModFormatV3;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Use for managing mod formats and containers.
 *
 * @since 1.0.0
 * @author Mr-Zombii
 */
public class ModFormats {

    private static Function<String, IModContainer> containerFunction;
    private static final Map<Integer, IModFormat> formatMap = new HashMap<>();

    /**
     * Initialise the default mod formats.
     */
    public static void initDefaultFormats() {
        System.out.println("Initializing default mod formats.");
        register(3, new ModFormatV3());
    }

    /**
     * Registers a function that gets the {@link IModContainer} of a mod ID.
     * @param containerFunction the function to register.
     */
    public static void register(Function<String, IModContainer> containerFunction) {
        ModFormats.containerFunction = containerFunction;
    }

    /**
     * Registers a mod format.
     * @param spec the formats version.
     * @param format the {@link IModFormat} format.
     */
    public static void register(Integer spec, IModFormat format) {
        formatMap.put(spec, format);
    }

    /**
     * Gets the {@link IModContainer} of a mod.
     * @param id the id of the mod.
     * @return {@link IModContainer}
     */
    public static IModContainer getContainer(String id) {
        return containerFunction.apply(id);
    }

    /**
     * Gets the format of the given version.
     * @param spec the version of the format.
     */
    public static IModFormat getFormat(Integer spec) {
        return formatMap.get(spec);
    }

}
