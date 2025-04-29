package dev.puzzleshq.mod;

import dev.puzzleshq.mod.api.IModContainer;
import dev.puzzleshq.mod.api.format.IModFormat;
import dev.puzzleshq.mod.api.format.impl.ModFormatV3;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

// TODO: Document Class
public class ModFormats {

    private static Function<String, IModContainer> containerFunction;
    private static final Map<Integer, IModFormat> formatMap = new HashMap<>();

    public static void initDefaultFormats() {
        System.out.println("Initializing default mod formats.");
        register(3, new ModFormatV3());
    }

    public static void register(Function<String, IModContainer> containerFunction) {
        ModFormats.containerFunction = containerFunction;
    }

    public static void register(Integer spec, IModFormat format) {
        formatMap.put(spec, format);
    }

    public static IModContainer getContainer(String id) {
        return containerFunction.apply(id);
    }

    public static IModFormat getFormat(Integer spec) {
        return formatMap.get(spec);
    }

}
