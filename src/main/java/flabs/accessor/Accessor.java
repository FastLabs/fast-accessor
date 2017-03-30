package flabs.accessor;


import flabs.functional.Gettable;
import flabs.functional.Settable;

import java.util.function.Supplier;

/**
 * @param <T> flabs.accessor for type
 * @param <P> for property
 */
public class Accessor<T, P> implements AccessibleField<T, P>, Settable<T, P> {
    Gettable<T, P> getter;
    Settable<T, P> setter;
    Supplier<P> defaulter;
    private final boolean isCollection;
    /**
     * Name is used as documentation only, for rule based applications
     * it is required a human readable description of the field
     */
    private final String name;

    public Accessor(Gettable<T, P> getter
            , Settable<T, P> setter
            , Supplier<P> defaulter
            , String name
            , boolean isCollection) {
        this.getter = getter;
        this.setter = setter;
        this.defaulter = defaulter;
        this.name = name;
        this.isCollection = isCollection;
    }

    public String getName() {
        return name;
    }

    public P defaultValue() {
        if (defaulter != null) {
            return defaulter.get();
        }
        return null;
    }

    @Override
    public P get(T target) {
        return getter != null ? getter.get(target) : null;
    }

    @Override
    public void set(T destination, P value) {
        if (setter != null) {
            setter.set(destination, value);
        }
    }

    public boolean hasSetter(){
        return setter !=null;
    }

    public boolean isPresent(T target) {
        return get(target) != null;
    }

    public boolean isNotPresent(T target) {
        return !isPresent(target);
    }

    public static class Builder<T, P> {
        private Gettable<T, P> getter;
        private Settable<T, P> setter;
        private Supplier<P> defaulter;
        private String name;
        private boolean isCollection;

        public Builder(){}

        public Builder(Accessor<T,P> accessor){
            this.getter = accessor.getter;
            this.setter = accessor.setter;
            this.defaulter = accessor.defaulter;
            this.name = accessor.name;
        }

        public Accessor<T, P> end() {
            return new Accessor<>(getter, setter, defaulter, name, isCollection);
        }

        public Builder<T, P> withGetter(Gettable<T, P> getter) {
            this.getter = getter;
            return this;
        }

        public Builder<T, P> withSetter(Settable<T, P> setter) {
            this.setter = setter;
            return this;
        }

        public Accessor<T, P> withDefault(Supplier<P> defaultGen) {
            defaulter = defaultGen;
            return end();
        }

        public Builder<T, P> withName(String name) {
            this.name = name;
            return this;
        }

        public Builder<T, P> asCollection() {
            this.isCollection = true;
            return this;
        }


        public Accessor<T, P> withDefault(final P defaultVal) {
            return withDefault(()->defaultVal);
        }
    }

    @Override
    public String toString() {
        return name+" with getter "+getter;
    }
}
