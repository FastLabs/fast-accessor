package flabs.accessor;


import flabs.functional.Action;
import flabs.functional.Gettable;
import flabs.functional.Settable;

/**
 * @param <T> flabs.accessor for type
 * @param <P> for property
 */
public class Accessor<T, P> implements AccessibleField<T, P>, Settable<T, P> {
    Gettable<T, P> getter;
    Settable<T, P> setter;
    Action<P> defaulter;
    /**
     * Name is used as documentation only, for rule based applications
     * it is required a human readable description of the field
     */
    private final String name;

    public Accessor(Gettable<T, P> getter
            , Settable<T, P> setter
            , Action<P> defaulter
            , String name) {
        this.getter = getter;
        this.setter = setter;
        this.defaulter = defaulter;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public P defaultValue() {
        if (defaulter != null) {
            return defaulter.call();
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
        private Action<P> defaulter;
        private String name;

        public Builder(){}

        public Builder(Accessor<T,P> accessor){
            this.getter = accessor.getter;
            this.setter = accessor.setter;
            this.defaulter = accessor.defaulter;
            this.name = accessor.name;
        }

        public Accessor<T, P> end() {
            return new Accessor<>(getter, setter, defaulter, name);
        }

        public Builder<T, P> withGetter(Gettable<T, P> getter) {
            this.getter = getter;
            return this;
        }

        public Builder<T, P> withSetter(Settable<T, P> setter) {
            this.setter = setter;
            return this;
        }

        public Accessor<T, P> withDefault(Action<P> defaultGen) {
            defaulter = defaultGen;
            return end();
        }

        public Builder<T, P> withName(String name) {
            this.name = name;
            return this;
        }


        public Accessor<T, P> withDefault(final P defaultVal) {
            return withDefault(new Action<P>() {
                @Override
                public P call() {
                    return defaultVal;
                }
            });
        }
    }

    @Override
    public String toString() {
        return name+" with getter "+getter;
    }
}
