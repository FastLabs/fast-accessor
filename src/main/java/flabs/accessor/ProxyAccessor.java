package flabs.accessor;

public abstract class ProxyAccessor<T, I, O> implements AccessibleField<T, O> {
    protected AccessibleField<T, I> accessor;

    protected ProxyAccessor(AccessibleField<T, I> accessor) {
        this.accessor = accessor;
    }

    @Override
    public String getName() {
        return accessor.getName();
    }
}
