package dev.puzzleshq.mod.api;

import dev.puzzleshq.mod.util.EntrypointPair;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Consumer;

/**
 * A container for entrypoint.
 *
 * @since 1.0.0
 * @author Mr-Zombii
 */
public interface IEntrypointContainer {

    /**
     * @param key the entrypoint name.
     * @param type the entrypoint type.
     * @param invoker the entrypoint invoker.
     * @param <T> the entrypoint class.
     */
    <T> void invoke(String key, Class<T> type, Consumer<? super T> invoker);

    /**
     * Gets the entrypoints.
     * @param key the entrypoint name.
     * @return {@link EntrypointPair}
     */
    @Nullable EntrypointPair[] getEntrypoints(String key);

    /**
     * Gets the entrypoint map.
     * @return a {@link Map} the key is the entrypoint name and value is an array of {@link EntrypointPair}.
     */
    Map<String, EntrypointPair[]> getEntrypointMap();

    /**
     * Gets the Mod container.
     * @return {@link IModContainer}
     */
    IModContainer getContainer();

}
