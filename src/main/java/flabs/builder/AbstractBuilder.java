package flabs.builder;


import flabs.accessor.Accessor;
import flabs.accessor.LookupService;

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

    protected boolean isValidSetup() {
        return true;
    }

    @Deprecated
    protected T newInstance() {
        throw new RuntimeException("this method is deprecated");
    }

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
