package dev.puzzleshq.json.mod.api;

import dev.puzzleshq.json.mod.util.EntrypointPair;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Consumer;

//TODO: Document Class
public interface IEntrypointController {

    <T> void invoke(String key, Class<T> type, Consumer<? super T> invoker);

    @Nullable EntrypointPair[] getEntrypoints(String key);
    Map<String, EntrypointPair[]> getEntrypointMap();
    IModContainer getContainer();

}
