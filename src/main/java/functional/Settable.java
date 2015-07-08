package functional;

public interface Settable<D, V> {
    void set(final D destination, final V value);
}