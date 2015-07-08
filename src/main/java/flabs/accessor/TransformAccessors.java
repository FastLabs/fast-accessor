package flabs.accessor;


import flabs.functional.Gettable;
import flabs.functional.Nameable;

public class TransformAccessors {

    public static <T> AccessibleField<T, String> asEnum(final Accessor<T, ? extends Enum> accessor) {
        return Accessors.<T>asString().withGetter(new Gettable<T, String>() {
            @Override
            public String get(T target) {
                final Enum<?> value = accessor.get(target);
                if (value != null) {
                    return value.toString();
                }
                return null;
            }
        }).end();
    }
    
    public static <T> AccessibleField<T, String> asName(final Accessor<T, ? extends Nameable> accessor) {
        return Accessors.<T>asString().withGetter(new Gettable<T, String>() {
            @Override
            public String get(T target) {
                final Nameable value = accessor.get(target);
                if (value != null) {
                    return value.getName();
                }
                return null;
            }
        }).end();
    }

   public static <T> AccessibleField<T, Integer> asInt(final Accessor<T, ?> accessor) {
        return Accessors.<T>asInt().withGetter(new Gettable<T, Integer>() {
            @Override
            public Integer get(T target) {
                final Object value = accessor.get(target);
                if (value != null) {
                    if (value instanceof Number) {
                        return ((Number) value).intValue();
                    } else if (value instanceof String) {
                        return Integer.parseInt((String) value);
                    }
                }
                return null;
            }
        }).end();
}

    public static <T> AccessibleField<T, Number> asNumber(final AccessibleField<T, ?> accessor) {
        return Accessors.<T>asNumber().withGetter(new Gettable<T, Number>() {
            @Override
            public Number get(T target) {
                final Object value = accessor.get(target);
                if (value != null) {
                    if (value instanceof String) {
                        try {
                            return Double.parseDouble((String) value);
                        } catch (NumberFormatException e) {
                            return null;
                        }
                    } else if (value instanceof Number) {
                        return (Number) value;
                    }
                }
                return null;
            }
        }).end();
    }



    public static <T> PipeAccessor<T,String> from(final AccessibleField<T, ?> first) {
        return new PipeAccessor<>(first);
    }

    public static <T, P, X> PipeAccessor<T, X> $(final AccessibleField<T, P> first, AccessibleField<P, X> second ) {
        return new PipeAccessor<>(first, second);
    }

    public static <T, P, X, Y> PipeAccessor<T, Y> $(final AccessibleField<T, P> first, AccessibleField<P, X> second, AccessibleField<X, Y>  third) {
        return new PipeAccessor<>(new PipeAccessor<>(first, second), third);
    }

    public static <T, P, X, Y, Z> PipeAccessor<T, Z> $(final AccessibleField<T, P> first, AccessibleField<P, X> second, AccessibleField<X, Y>  third, AccessibleField<Y, Z>  fourth ) {
        final PipeAccessor<T, X> p1 = new PipeAccessor<>(first, second);
        final PipeAccessor<X, Z> p2 = new PipeAccessor<>(third, fourth);
        return new PipeAccessor<>(p1, p2);
    }

}
