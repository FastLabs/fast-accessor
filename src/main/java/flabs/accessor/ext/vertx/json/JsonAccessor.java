package flabs.accessor.ext.vertx.json;


import flabs.accessor.AccessibleField;
import flabs.accessor.Accessor;
import flabs.builder.AbstractBuilder;
import flabs.functional.Nameable;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.function.Supplier;


public interface JsonAccessor<T> extends AccessibleField<JsonObject, T> {
    Supplier<JsonObject> objFactory = JsonObject::new;
    Supplier<JsonArray> arrFactory = JsonArray::new;


    static <T> Accessor<JsonObject, T> js$(Nameable name, Supplier<T> defaults) {
        return js$(name.getName(), defaults);
    }

    static <T> Accessor<JsonObject, T> js$(Nameable name) {
        return js$(name.getName(), null);
    }

    @SuppressWarnings("unchecked")
    static <T> Accessor<JsonObject, T> js$(final String fieldName, Supplier<T> defaulter) {
        return new Accessor.Builder<JsonObject, T>()
                .withName(fieldName)
                .withSetter((bucket, value) -> bucket.put(fieldName, value))
                .withGetter(bucket -> (T) bucket.getValue(fieldName))
                .withDefault(defaulter);
    }

    static Accessor<JsonObject, JsonObject> js$(final String fieldName) {
        return js$(fieldName, objFactory);
    }

    static Accessor<JsonObject, JsonArray> jsArr$(final String fieldName) {
        return js$(fieldName, arrFactory);
    }

    static Builder newJs() {
        return new Builder(new JsonObject(), null);
    }

    class Builder extends AbstractBuilder<JsonObject, Builder> {
        Builder(JsonObject template, Accessor<JsonObject, ?>[] mandatory) {
            super(template, mandatory);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }


}
