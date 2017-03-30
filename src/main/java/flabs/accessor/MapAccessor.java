package flabs.accessor;


import flabs.functional.Nameable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static flabs.accessor.TransformAccessors.$;



public class MapAccessor {
    private static Supplier<Map> mapFactory = HashMap::new;
    private static Supplier<ArrayList> listFactory = ArrayList::new;


    private static <T> Accessor<Map, T> map$(Nameable name, Supplier<T> defaults) {
        return map$(name.getName(), defaults);
    }

    public static <T> Accessor<Map, T> map$(Nameable name) {
        return map$(name.getName(), null);
    }



    @SuppressWarnings("unchecked")
    public static <T> AccessibleField<Map, T> map$(PipeAccessor<?, T> pipe) {
        final List<AccessibleField<?, ?>> fields = pipe.getPipe();
        if (fields.size() == 0) {
            return UNKNOWN_FIELD();
        }
        if (fields.size() == 1) {
            return map$(((AccessibleField) fields.get(0)).getName(), null);
        }
        if (fields.size() == 2) {
            if(fields.get(0).isCollection()) {}
            return (PipeAccessor<Map, T>) $(map$(fields.get(0), mapFactory)
                    , map$(fields.get(1)));
        }

        final PipeAccessor<Map, T> result = (PipeAccessor<Map, T>) $(map$(fields.get(0), mapFactory)
                , map$(fields.get(1), mapFactory));
        for (int i = 2; i < fields.size() - 1; i++) {
            result.take(map$(fields.get(i), mapFactory));
        }
        result.take(map$(fields.get(fields.size() - 1), mapFactory));

        return result;
    }

    @SuppressWarnings("unchecked")
    public static <T> Accessor<Map, T> map$(final String fieldName, Supplier<T> defaulter) {
        return new Accessor.Builder<Map, T>()
                .withName(fieldName)
                .withSetter((bucket, value) -> bucket.put(fieldName, value))
                .withGetter(bucket -> (T) bucket.get(fieldName))
                .withDefault(defaulter);
    }

    /**
     * Accessor placeholder for an unknown field
     *
     * @param <T> target entity type where the accessor is mapped
     * @param <P> return type
     * @return empty accessor placeholder
     */
    static <T, P> AccessibleField<T, P> UNKNOWN_FIELD() {
        return new AccessibleField<T, P>() {
            @Override
            public P defaultValue() {
                return null;
            }

            @Override
            public P get(T target) {
                return null;
            }

            @Override
            public String getName() {
                return "UNKNOWN_FIELD";
            }

            @Override
            public void set(T destination, P value) {

            }
        };
    }
}
