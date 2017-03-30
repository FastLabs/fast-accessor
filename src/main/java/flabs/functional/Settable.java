package flabs.functional;

@FunctionalInterface
public interface Settable<D, V> {
    void set(final D destination, final V value);
}