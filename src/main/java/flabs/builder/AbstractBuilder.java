package flabs.builder;


import flabs.accessor.Accessor;
import flabs.accessor.LookupService;
import flabs.functional.Settable;

/**
 * Abstract class for the flabs.builder that defines the template for a flabs.builder
 *
 * @param <T> - flabs.builder's entity type
 */
public abstract class AbstractBuilder<T, X extends AbstractBuilder<T, ?>> implements Builder<T> {
    protected T template;

    private Accessor<T, ?>[] mandatory;

    @SafeVarargs
    protected AbstractBuilder(T template, Accessor<T, ?>... mandatory) {
        this.template = template;
        this.mandatory = mandatory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T build() {
        if (mandatory != null) {
            for (Accessor accessor : mandatory) {
                Object x = accessor.get(template);
                if (x == null) {
                    final Object defaultValue = accessor.defaultValue();
                    if (defaultValue != null) {
                        accessor.set(template, defaultValue);
                    } else {
                        throw new RuntimeException("A mandatory field " + accessor + "is not populated and no default value is set for the flabs.accessor");
                    }
                }
            }
        }
        return template;
    }

    public <Z> X with(Settable<T, Z> setter, Z value) {
        setter.set(template, value);
        return self();
    }
//TODO: experimental
    public <Z> X $(Settable<T, Z> setter, Z value) {
       return with(setter, value);
    }

    public <Z> X $(Settable<T, Z> setter, AbstractBuilder<Z, ?> value) {
        return with(setter, value);
    }


    public <Z> X with(Settable<T, Z> setter, AbstractBuilder<Z, ?> value) {
        if(value != null) {
            setter.set(template, value.build());
        }
        return self();
    }

    protected boolean isValidSetup() {
        return true;
    }


//TODO: move this outside, it may be used as accessor
    public <P> X lookup(Accessor<T, P> accessor, LookupService<P, P> lookupService) {
        final P value = accessor.get(template);
        if (value != null) {
            P p = lookupService.find(value);
            if (p != null) {
                accessor.set(template, p);
            }
        }

        return self();
    }


    protected abstract X self();
}
