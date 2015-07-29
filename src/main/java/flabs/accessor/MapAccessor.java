package com.db.treasury.tch.core.utils.attr;

import com.db.treasury.tch.core.beans.domain.Nameable;
import com.db.treasury.tch.core.beans.functional.Action;
import com.db.treasury.tch.core.beans.functional.Gettable;
import com.db.treasury.tch.core.beans.functional.Settable;
import com.db.treasury.tch.core.utils.accessor.AccessibleField;
import com.db.treasury.tch.core.utils.accessor.Accessor;
import com.db.treasury.tch.core.utils.accessor.PipeAccessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.db.treasury.tch.core.utils.VarArg.varArg;
import static com.db.treasury.tch.core.utils.accessor.TransformAccessors.$;

public class MapAccessor {
    private static Action<Map> mapFactory = new Action<Map>() {
        @Override
        public HashMap call() {
            return new HashMap();
        }
    };

    @SafeVarargs
    public static <T> Accessor<Map, T> mapAccessor(Nameable name, Action<T>... defaults) {
        return mapAccessor(name.getName(), defaults);
    }

    @SuppressWarnings("unchecked")
    public static <T> AccessibleField<Map, T> mapAccessor(PipeAccessor<Map, T> pipe) {
        final List<AccessibleField<?, ?>> fields = pipe.getPipe();
        if (fields.size() == 0) {
            return UNKNOWN_FIELD();
        }
        if (fields.size() == 1) {
            return mapAccessor(((AccessibleField) fields.get(0)).getName());
        }
        if (fields.size() == 2) {
            return (PipeAccessor<Map, T>) $(mapAccessor(fields.get(0), mapFactory)
                    , mapAccessor(fields.get(1)));
        }

        final PipeAccessor<Map, T> result = (PipeAccessor<Map, T>) $(mapAccessor(fields.get(0), mapFactory)
                , mapAccessor(fields.get(1), mapFactory));
        for (int i = 2; i < fields.size() - 1; i++) {
            result.take(mapAccessor(fields.get(i), mapFactory));
        }
        result.take(mapAccessor(fields.get(fields.size() - 1), mapFactory));

        return result;
    }

    @SuppressWarnings("unchecked")
    @SafeVarargs
    public static <T> Accessor<Map, T> mapAccessor(final String fieldName, Action<T>... defaulter) {
        return new Accessor.Builder<Map, T>()
                .withName(fieldName)
                .withSetter(new Settable<Map, T>() {
                    @Override
                    public void set(final Map bucket, final T value) {
                        bucket.put(fieldName, value);
                    }
                })
                .withGetter(new Gettable<Map, T>() {
                    @Override
                    public T get(final Map bucket) {
                        return (T) bucket.get(fieldName);
                    }
                })
                .withDefault(varArg(defaulter).get(0));
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
