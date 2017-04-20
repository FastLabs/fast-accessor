package flabs.accessor.ext.vertx.json;


import flabs.accessor.AccessibleField;
import flabs.accessor.Accessors;
import io.vertx.core.json.JsonArray;

import java.util.Optional;
import java.util.function.Predicate;

public class JsonArrayAccessor {

    public static <T> AccessibleField<JsonArray, T> nth(int position) {
        return Accessors.<JsonArray, T>asObject()
                .withGetter(arr -> (T) arr.getValue(position))
                .withSetter((arr, val) -> {throw new UnsupportedOperationException("Updating an json array is not supported");} )
                .end();
    }

    public static <T> AccessibleField<JsonArray, T> first() {
        return nth(0);
    }

    public static <T>AccessibleField<JsonArray, T> first(Predicate<T> predicate) {
        return Accessors.<JsonArray, T>asObject()
                .withGetter(arr -> {
                    final Optional<Object> any = arr.stream().filter(obj -> predicate.test((T) obj)).findAny();
                    return (T)any.orElseGet(null);
                })
                .end();
    }
}
