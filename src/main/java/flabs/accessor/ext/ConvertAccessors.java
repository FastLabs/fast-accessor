package flabs.accessor.ext;


import flabs.accessor.AccessibleField;
import flabs.accessor.Accessor;
import flabs.accessor.Accessors;
import flabs.accessor.PipeAccessor;
import flabs.functional.Gettable;
import flabs.functional.Nameable;

public class ConvertAccessors {

    public static <T> AccessibleField<T, String> asEnum(final Accessor<T, ? extends Enum> accessor) {
        return Accessors.<T>asString().withGetter(target -> {
            final Enum<?> value = accessor.get(target);
            if (value != null) {
                return value.toString();
            }
            return null;
        }).end();
    }
    
    public static <T> AccessibleField<T, String> asName(final Accessor<T, ? extends Nameable> accessor) {
        return Accessors.<T>asString().withGetter(target -> {
            final Nameable value = accessor.get(target);
            if (value != null) {
                return value.getName();
            }
            return null;
        }).end();
    }

   public static <T> AccessibleField<T, Integer> asInt(final Accessor<T, ?> accessor) {
        return Accessors.<T>asInt().withGetter(target -> {
            final Object value = accessor.get(target);
            if (value != null) {
                if (value instanceof Number) {
                    return ((Number) value).intValue();
                } else if (value instanceof String) {
                    return Integer.parseInt((String) value);
                }
            }
            return null;
        }).end();
}

    public static <T> AccessibleField<T, Number> asNumber(final AccessibleField<T, ?> accessor) {
        return Accessors.<T>asNumber().withGetter(target -> {
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
        }).end();
    }


}
